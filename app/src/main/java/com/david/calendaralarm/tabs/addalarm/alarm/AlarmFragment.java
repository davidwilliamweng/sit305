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

    private CardView cardview;
    private EmptyStateRecyclerView recycler;

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
        View view =  layoutInflater.inflate(R.layout.fragment_alarm, container, false);
        cardview = view.findViewById(R.id.cardview);
        recycler = view.findViewById(R.id.rvSleepnow);
        alarmDAO = new AlarmDAO();
        alarmController = new AlarmController(getActivity());
        return view;
    }

    /**
     * Called when the fragment's activity has been created and this fragment's view hierarchy instantiated
     * @param savedInstanceState If the fragment is being re-created from a previous saved state, this is the state.
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RealmManager.incrementCount();
        setupRecycler();
    }

    /**
     * Component init.
     */
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

    /**
     * To set a alarm
     */
    public void showSetTimeDialog() {
        startActivity(new Intent(getActivity(), AddEditAlarmActivity.class));
    }

    /**
     * To edit a alarm
     * @param alarm Model of alarm
     */
    @Override
    public void showEditDialog(Alarm alarm) {
        MyApplication.getInstance().setAlarm(alarm);
        startActivityForResult(new Intent(getActivity(), AddEditAlarmActivity.class), 2);
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
        if(requestCode == 2 && requestCode == 2){
            activeAlarmsAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Called to have the fragment instantiate its user interface view
     */
    @Override
    public void onDestroyView() {
        alarmDAO.cleanUp();
        RealmManager.decrementCount();
        super.onDestroyView();
    }

}
