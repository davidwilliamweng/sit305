package com.david.calendaralarm.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.david.calendaralarm.app.RealmManager;
import com.david.calendaralarm.data.pojo.Alarm;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;

public class AlarmDAO {

    public AlarmDAO() {
        RealmManager.incrementCount();
    }

    /**
     * Guarantee that there are no duplicate primary keys
     * @param alarm Data object
     */
    public void saveIfNotDuplicate(final Alarm alarm) {
        Realm realm = RealmManager.getRealm();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                if (isNotDuplicate(alarm, realm)) {
                    realm.insertOrUpdate(alarm);
                }
            }
        });
    }

    /**
     * Is there a duplicate primary key
     * @param alarm Data object
     * @param realm DB object
     * @return Return true indicates that there is a return false but there is no return false.
     */
    private boolean isNotDuplicate(Alarm alarm, Realm realm) {
        Alarm duplicateAlarm = realm.where(Alarm.class)
                .equalTo("time", alarm.getTime()).findFirst();
        return duplicateAlarm == null;
    }

    /**
     * Allow duplicate primary keys
     * @param alarm Data object
     */
    public void saveEvenIfDuplicate(final Alarm alarm) {
        Realm realm = RealmManager.getRealm();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                realm.insertOrUpdate(alarm);
            }
        });
    }

    /**
     * Delete data by id
     * @param id Data object of id
     */
    public void removeFromRealmById(final String id) {
        Log.d(getClass().getName(), "Removing from realm...");
        Realm realm = RealmManager.getRealm();

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                Alarm alarm = realm.where(Alarm.class).equalTo("id", id).findFirst();
                if(alarm != null) {
                    alarm.deleteFromRealm();
                }
            }
        });
    }

    /**
     * Query all data
     * @return All data of local db
     */
    public List<Alarm> getListOfAlarms() {
        Realm realm = RealmManager.getRealm();
        RealmQuery<Alarm> query = realm.where(Alarm.class);
        return query.findAll();
    }

    public void cleanUp() {
        RealmManager.decrementCount();
    }

}
