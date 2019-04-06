package com.david.calendaralarm.tabs.addalarm;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.david.calendaralarm.R;
import com.david.calendaralarm.data.AlarmDAO;
import com.david.calendaralarm.data.pojo.Alarm;
import com.david.calendaralarm.data.pojo.Item;
import com.david.calendaralarm.schedule.AlarmController;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.UUID;

public abstract class AddDialogFragment extends Fragment {

    private static final int RINGTONE_INTENT_REQUEST_CODE = 1;
    private AlertDialog dialog;

    public void showDialog(final Item item) {
        String positiveButtonText = getString(R.string.add);
        String negativeButtonText = getString(R.string.cancel);
        final View content = getLayoutInflater().inflate(R.layout.dialog_item_ringtone, null);
        AlertDialog.Builder builder
                = new AlertDialog.Builder(getActivity(), R.style.Theme_SleepCycleAlarm_Dialog);
        builder.setView(content)
                .setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Alarm alarm = getAlarmFromItem(item);
                        addAlarm(alarm);
                    }
                })
                .setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.cancel();
                    }
                });
        dialog = builder.create();
        dialog.show();
    }

    public void addAlarm(Alarm alarm) {
        Context ctx = getContext();
        if (ctx != null) {
            new AlarmController(ctx).setAlarm(alarm);
            new AlarmDAO().saveIfNotDuplicate(alarm);
        } else {
            Log.e(getClass().getName(), "addAlarm(): ctx == null");
        }
    }

    private void startRingtonePickerActivityForResult() {
//        Uri selectedRingtoneUri = Uri.parse(dialogContract.getRingtone());
//        Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
//        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALARM);
//        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE,
//                getString(R.string.pref_ringtone_select_title));
//        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, selectedRingtoneUri);
//        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, false);
//        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI, selectedRingtoneUri);
//        startActivityForResult(intent, RINGTONE_INTENT_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == Activity.RESULT_OK && requestCode == RINGTONE_INTENT_REQUEST_CODE) {
//            Uri uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
//
//            if (uri != null && dialogContract != null) {
//                String chosenRingtone = uri.toString();
//                dialogContract.setRingtone(chosenRingtone);
//            }
//        }
    }

    public Alarm getAlarmFromItem(Item item) {
        Context ctx = getContext();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(ctx);

        final String id = UUID.randomUUID().toString();
        final String title = item.getTitle();
        final String summary = item.getSummary();
        final String currentDate = item.getCurrentDate().toString();
        final String executionDate = item.getExecutionDate().toString();

        Alarm alarm = new Alarm();
        alarm.setId(id);
//        alarm.setTitle(title);
//        alarm.setSummary(summary);
//        alarm.setCurrentDate(currentDate);
//        alarm.setExecutionDate(executionDate);

        return alarm;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
