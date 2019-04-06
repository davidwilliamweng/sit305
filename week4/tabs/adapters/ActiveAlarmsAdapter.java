package com.david.calendaralarm.tabs.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.david.calendaralarm.R;
import com.david.calendaralarm.data.pojo.Alarm;
import com.david.calendaralarm.schedule.AlarmController;
import com.david.calendaralarm.tabs.addalarm.alarm.AlarmInterface;
import com.david.calendaralarm.utils.AlarmContentUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

public class ActiveAlarmsAdapter extends RealmRecyclerViewAdapter<Alarm,
        ActiveAlarmsAdapter.AlarmViewHolder> {

    private AlarmInterface alarmInterface;

    public ActiveAlarmsAdapter(RealmResults<Alarm> alarms, AlarmInterface alarmInterface) {
        super(alarms, true);
        this.alarmInterface = alarmInterface;
    }

    @NonNull
    @Override
    public AlarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlarmViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_alarm, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmViewHolder alarmViewHolder, final int position) {
        final Alarm alarm = getItem(position);
        if(alarm != null) {
            alarmViewHolder.bind(alarm);
        }
    }

    public class AlarmViewHolder extends RecyclerView.ViewHolder {

        private AlarmController alarmController;
        private Context context;

        @BindView(R.id.cl_item_alarm_root)
        ConstraintLayout clRoot;

        @BindView(R.id.iv_item_alarm_icon)
        ImageView ivIcon;

        @BindView(R.id.tv_item_alarm_title)
        TextView tvTitle;

        @BindView(R.id.tv_item_alarm_subtitle)
        TextView tvSubtitle;

        public AlarmViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.context = itemView.getContext();
            this.alarmController = new AlarmController(context);
        }

        public void bind(final Alarm alarm) {
            final String id = alarm.getId();
            ivIcon.setImageResource(R.drawable.ic_list_alarm_access_full_shape);
            String alarmExecutionDate = AlarmContentUtils.getTitle(alarm.getTime());
            tvTitle.setText(alarmExecutionDate);
            tvSubtitle.setText(alarm.getLabel());

            clRoot.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(alarmInterface != null)
                        alarmInterface.deleteAlarmById(id);
                    alarmController.cancelAlarm(alarm);
                    return false;
                }
            });

            clRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(alarmInterface != null)
                        alarmInterface.showEditDialog(alarm);
                }
            });
        }
    }
}
