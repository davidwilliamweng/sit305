package com.david.calendaralarm.tabs.addalarm.calendar;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.david.calendaralarm.R;
import com.david.calendaralarm.app.MyApplication;
import com.david.calendaralarm.app.RealmManager;
import com.david.calendaralarm.data.AlarmDAO;
import com.david.calendaralarm.data.pojo.Alarm;
import com.david.calendaralarm.schedule.AlarmController;
import com.david.calendaralarm.tabs.addalarm.alarm.AddEditAlarmActivity;
import com.david.calendaralarm.tabs.addalarm.alarm.AlarmInterface;
import com.david.calendaralarm.utils.AlarmContentUtils;
import com.david.calendaralarm.utils.TimeUtils;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import org.joda.time.DateTime;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Fragment of calendar.
 */
public class CalendarFragment extends Fragment {

    private CalendarView calendarView;
    private TextView tvMonth;
    private String date;
    private AlarmController alarmController;
    private AlarmDAO alarmDAO;
    private Realm realm;
    private RealmResults rr;
    private LinearLayout layout;

    /**
     * Called to have the fragment instantiate its user interface view
     * @param layoutInflater The LayoutInflater object that can be used to inflate any views in the fragment
     * @param container If non-null, this is the parent view that the fragment's UI should be attached to
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = layoutInflater
                .inflate(R.layout.fragment_calendar, container, false);
        layout = view.findViewById(R.id.layout);
        calendarView = view.findViewById(R.id.calendarView);
        tvMonth = view.findViewById(R.id.tvMonth);
        return view;
    }

    /**
     * Called when the fragment's activity has been created and this fragment's view hierarchy instantiated
     * @param savedInstanceState If the fragment is being re-created from a previous saved state, this is the state.
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupUi();
    }

    /**
     * Component init.
     */
    private void setupUi() {
        alarmDAO = new AlarmDAO();
        alarmController = new AlarmController(getActivity());
        tvMonth.setText(DateTime.now().getYear() + " . " + DateTime.now().getMonthOfYear());
        calendarView.setOnCalendarSelectListener(new CalendarView.OnCalendarSelectListener() {
            @Override
            public void onCalendarOutOfRange(Calendar calendar) {
            }

            @Override
            public void onCalendarSelect(Calendar calendar, boolean isClick) {
                tvMonth.setText(calendar.getYear() + " . " + calendar.getMonth());
                date = TimeUtils.formatDatatime(calendar.getYear(), calendar.getMonth(), calendar.getDay());
                instanceView();
            }
        });
        date = TimeUtils.formatDatatime(DateTime.now().getYear(), DateTime.now().getMonthOfYear(), DateTime.now().getDayOfMonth());
        instanceView();
    }

    /**
     * Load alarm from local db and refresh view.
     */
    private void instanceView(){
        realm = RealmManager.getRealm();
        rr = realm
                .where(Alarm.class).contains("time", date).findAll().sort("time");
        layout.removeAllViews();
        for (int i = 0; i < rr.size(); i++){
            View itemView = LayoutInflater.from(getActivity()).inflate(R.layout.item_alarm, null);
            layout.addView(itemView);
            ConstraintLayout clRoot = itemView.findViewById(R.id.cl_item_alarm_root);
            ImageView ivIcon = itemView.findViewById(R.id.iv_item_alarm_icon);
            TextView tvTitle = itemView.findViewById(R.id.tv_item_alarm_title);
            TextView tvDays = itemView.findViewById(R.id.tv_item_alarm_days);
            TextView tvSubtitle = itemView.findViewById(R.id.tv_item_alarm_subtitle);
            ivIcon.setImageResource(R.drawable.ic_list_alarm_access_full_shape);
            final Alarm alarm0 = (Alarm)rr.get(i);
            String alarmExecutionDate = AlarmContentUtils.getTitle(alarm0.getTime());
            tvTitle.setText(alarmExecutionDate);
            tvDays.setText(alarm0.getDays());
            tvSubtitle.setText(alarm0.getLabel());

            clRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyApplication.getInstance().setAlarm(alarm0);
                    Intent intent = new Intent(getActivity(), AddEditAlarmActivity.class);
                    startActivityForResult(intent, 1);
                }
            });
        }
    }

    /**
     * To set calendar alarm
     */
    public void showSetTimeDialog() {
        Intent intent = new Intent(getActivity(), AddEditAlarmActivity.class);
        intent.putExtra("date", date);
        startActivityForResult(intent, 1);
    }

    /**
     * Called when an activity you launched exits, giving you the requestCode you started it with,
     * the resultCode it returned, and any additional data from it
     * @param requestCode The integer request code originally supplied to startActivityForResult(),
     *                    allowing you to identify who this result came from.
     * @param resultCode The integer result code returned by the child activity through its setResult().
     * @param data An Intent, which can return result data to the caller (various data can be attached to Intent "extras").
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == 2){
                instanceView();
            }else if(resultCode == 1) {
                instanceView();
            }
        }
    }
}
