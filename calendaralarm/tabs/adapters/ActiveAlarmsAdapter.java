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

        private Context context;

        ConstraintLayout clRoot;
        ImageView ivIcon;
        TextView tvTitle;
        TextView tvDays;
        TextView tvSubtitle;

        public AlarmViewHolder(View itemView) {
            super(itemView);
            clRoot = itemView.findViewById(R.id.cl_item_alarm_root);
            ivIcon = itemView.findViewById(R.id.iv_item_alarm_icon);
            tvTitle = itemView.findViewById(R.id.tv_item_alarm_title);
            tvDays = itemView.findViewById(R.id.tv_item_alarm_days);
            tvSubtitle = itemView.findViewById(R.id.tv_item_alarm_subtitle);
            this.context = itemView.getContext();
        }

        public void bind(final Alarm alarm) {
            final String id = alarm.getId();
            ivIcon.setImageResource(R.drawable.ic_list_alarm_access_full_shape);
            String alarmExecutionDate = AlarmContentUtils.getTitle(alarm.getTime());
            tvTitle.setText(alarmExecutionDate);
            tvDays.setText(alarm.getDays());
            tvSubtitle.setText(alarm.getLabel());

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
