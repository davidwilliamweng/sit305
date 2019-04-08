package com.david.calendaralarm.tabs.addalarm.alarm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.david.calendaralarm.R;
import com.david.calendaralarm.app.RealmManager;
import com.david.calendaralarm.data.AlarmDAO;
import com.david.calendaralarm.data.pojo.Alarm;
import com.david.calendaralarm.schedule.AlarmController;
import com.david.calendaralarm.tabs.adapters.ActiveAlarmsAdapter;
import com.david.calendaralarm.tabs.ui.EmptyStateRecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;


/**
 * Fragment of alarm.
 */
public class AlarmFragment extends Fragment implements AlarmInterface {

    private ActiveAlarmsAdapter activeAlarmsAdapter;
    private AlertDialog dialog;
    private AlarmController alarmController;
    private AlarmDAO alarmDAO;

    @BindView(R.id.linearlayout_root)
    LinearLayout linearlayout_root;
    @BindView(R.id.cardview)
    CardView cardview;
    @BindView(R.id.rv_sleepnow)
    EmptyStateRecyclerView recycler;

    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  layoutInflater.inflate(R.layout.fragment_alarm, container, false);
        ButterKnife.bind(this, view);
        alarmController = new AlarmController(view.getContext());
        alarmDAO = new AlarmDAO();
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
        if(rr.size() > 0){
            cardview.setVisibility(View.GONE);
        }
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

    private void showEditAlarmDialog(final Alarm alarm) {
        if(!alarm.isValid()) {
            return;
        }

        String positiveButtonText = getString(R.string.save);
        String negativeButtonText = getString(R.string.cancel);
        final View content = getLayoutInflater().inflate(R.layout.dialog_item_ringtone, null);

        AlertDialog.Builder builder
                = new AlertDialog.Builder(getActivity(), R.style.Theme_SleepCycleAlarm_Dialog);
        builder.setView(content)
                .setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        createNewAlarm(i);
                        dialog.dismiss();
                    }

                    private void createNewAlarm(int i) {
                        alarmController.cancelAlarm(alarm);

                        Alarm newAlarm = new Alarm();
                        newAlarm.setId(alarm.getId());
                        alarmDAO.saveEvenIfDuplicate(newAlarm);
                        alarmController.setAlarm(newAlarm);

                        activeAlarmsAdapter.notifyItemChanged(i);
                    }
                })
                .setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                });
        dialog = builder.create();
        dialog.show();
    }

    @Override
    public void deleteAlarmById(String id) {
        alarmDAO.removeFromRealmById(id);
    }

    @Override
    public void showEditDialog(Alarm alarm) {
        showEditAlarmDialog(alarm);
    }

    @Override
    public void onDestroyView() {
        if(dialog != null) {
            dialog.dismiss();
        }
        alarmDAO.cleanUp();
        RealmManager.decrementCount();
        super.onDestroyView();
    }

}
