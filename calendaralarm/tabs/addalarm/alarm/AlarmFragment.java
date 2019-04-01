package com.david.calendaralarm.tabs.addalarm.alarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.david.calendaralarm.R;
import com.david.calendaralarm.app.MyApplication;
import com.david.calendaralarm.app.RealmManager;
import com.david.calendaralarm.data.AlarmDAO;
import com.david.calendaralarm.data.pojo.Alarm;
import com.david.calendaralarm.schedule.AlarmController;
import com.david.calendaralarm.tabs.adapters.ActiveAlarmsAdapter;
import com.david.calendaralarm.tabs.ui.EmptyStateRecyclerView;
import io.realm.Realm;
import io.realm.RealmResults;


/**
 * Fragment of alarm.
 */
public class AlarmFragment extends Fragment implements AlarmInterface {

    private ActiveAlarmsAdapter activeAlarmsAdapter;
    private AlarmController alarmController;
    private AlarmDAO alarmDAO;

    CardView cardview;
    EmptyStateRecyclerView recycler;

    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  layoutInflater.inflate(R.layout.fragment_alarm, container, false);
        cardview = view.findViewById(R.id.cardview);
        recycler = view.findViewById(R.id.rvSleepnow);
        alarmDAO = new AlarmDAO();
        alarmController = new AlarmController(getActivity());
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RealmManager.incrementCount();
        setupRecycler();
    }

    private void setupRecycler() {
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        Realm realm = RealmManager.getRealm();
        RealmResults rr = realm
                .where(Alarm.class).findAllAsync().sort("time");
        activeAlarmsAdapter = new ActiveAlarmsAdapter(rr, this);
        recycler.setAdapter(activeAlarmsAdapter);
        cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSetTimeDialog();
            }
        });
    }

    public void showSetTimeDialog() {
        startActivity(new Intent(getActivity(), AddEditAlarmActivity.class));
    }

    @Override
    public void showEditDialog(Alarm alarm) {
        MyApplication.getInstance().setAlarm(alarm);
        startActivityForResult(new Intent(getActivity(), AddEditAlarmActivity.class), 2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2 && requestCode == 2){
            activeAlarmsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroyView() {
        alarmDAO.cleanUp();
        RealmManager.decrementCount();
        super.onDestroyView();
    }

}
