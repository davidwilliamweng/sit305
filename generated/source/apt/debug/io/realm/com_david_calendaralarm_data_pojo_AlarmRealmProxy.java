package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.ProxyUtils;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsList;
import io.realm.internal.OsObject;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.Property;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.Table;
import io.realm.internal.android.JsonUtils;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("all")
public class com_david_calendaralarm_data_pojo_AlarmRealmProxy extends com.david.calendaralarm.data.pojo.Alarm
    implements RealmObjectProxy, com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface {

    static final class AlarmColumnInfo extends ColumnInfo {
        long idIndex;
        long labelIndex;
        long timeIndex;
        long daysIndex;

        AlarmColumnInfo(OsSchemaInfo schemaInfo) {
            super(4);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Alarm");
            this.idIndex = addColumnDetails("id", "id", objectSchemaInfo);
            this.labelIndex = addColumnDetails("label", "label", objectSchemaInfo);
            this.timeIndex = addColumnDetails("time", "time", objectSchemaInfo);
            this.daysIndex = addColumnDetails("days", "days", objectSchemaInfo);
        }

        AlarmColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new AlarmColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final AlarmColumnInfo src = (AlarmColumnInfo) rawSrc;
            final AlarmColumnInfo dst = (AlarmColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.labelIndex = src.labelIndex;
            dst.timeIndex = src.timeIndex;
            dst.daysIndex = src.daysIndex;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private AlarmColumnInfo columnInfo;
    private ProxyState<com.david.calendaralarm.data.pojo.Alarm> proxyState;

    com_david_calendaralarm_data_pojo_AlarmRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (AlarmColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.david.calendaralarm.data.pojo.Alarm>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$id() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.idIndex);
    }

    @Override
    public void realmSet$id(String value) {
        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'id' cannot be changed after object was created.");
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$label() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.labelIndex);
    }

    @Override
    public void realmSet$label(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.labelIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.labelIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.labelIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.labelIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$time() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.timeIndex);
    }

    @Override
    public void realmSet$time(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.timeIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.timeIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.timeIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.timeIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$days() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.daysIndex);
    }

    @Override
    public void realmSet$days(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.daysIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.daysIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.daysIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.daysIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("Alarm", 4, 0);
        builder.addPersistedProperty("id", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("label", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("time", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("days", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static AlarmColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new AlarmColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "Alarm";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "Alarm";
    }

    @SuppressWarnings("cast")
    public static com.david.calendaralarm.data.pojo.Alarm createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.david.calendaralarm.data.pojo.Alarm obj = null;
        if (update) {
            Table table = realm.getTable(com.david.calendaralarm.data.pojo.Alarm.class);
            AlarmColumnInfo columnInfo = (AlarmColumnInfo) realm.getSchema().getColumnInfo(com.david.calendaralarm.data.pojo.Alarm.class);
            long pkColumnIndex = columnInfo.idIndex;
            long rowIndex = Table.NO_MATCH;
            if (!json.isNull("id")) {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("id"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.david.calendaralarm.data.pojo.Alarm.class), false, Collections.<String> emptyList());
                    obj = new io.realm.com_david_calendaralarm_data_pojo_AlarmRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.com_david_calendaralarm_data_pojo_AlarmRealmProxy) realm.createObjectInternal(com.david.calendaralarm.data.pojo.Alarm.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.com_david_calendaralarm_data_pojo_AlarmRealmProxy) realm.createObjectInternal(com.david.calendaralarm.data.pojo.Alarm.class, json.getString("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }

        final com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface objProxy = (com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface) obj;
        if (json.has("label")) {
            if (json.isNull("label")) {
                objProxy.realmSet$label(null);
            } else {
                objProxy.realmSet$label((String) json.getString("label"));
            }
        }
        if (json.has("time")) {
            if (json.isNull("time")) {
                objProxy.realmSet$time(null);
            } else {
                objProxy.realmSet$time((String) json.getString("time"));
            }
        }
        if (json.has("days")) {
            if (json.isNull("days")) {
                objProxy.realmSet$days(null);
            } else {
                objProxy.realmSet$days((String) json.getString("days"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.david.calendaralarm.data.pojo.Alarm createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.david.calendaralarm.data.pojo.Alarm obj = new com.david.calendaralarm.data.pojo.Alarm();
        final com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface objProxy = (com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$id((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$id(null);
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("label")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$label((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$label(null);
                }
            } else if (name.equals("time")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$time((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$time(null);
                }
            } else if (name.equals("days")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$days((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$days(null);
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
        }
        return realm.copyToRealm(obj);
    }

    public static com.david.calendaralarm.data.pojo.Alarm copyOrUpdate(Realm realm, com.david.calendaralarm.data.pojo.Alarm object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null) {
            final BaseRealm otherRealm = ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm();
            if (otherRealm.threadId != realm.threadId) {
                throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
            }
            if (otherRealm.getPath().equals(realm.getPath())) {
                return object;
            }
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.david.calendaralarm.data.pojo.Alarm) cachedRealmObject;
        }

        com.david.calendaralarm.data.pojo.Alarm realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.david.calendaralarm.data.pojo.Alarm.class);
            AlarmColumnInfo columnInfo = (AlarmColumnInfo) realm.getSchema().getColumnInfo(com.david.calendaralarm.data.pojo.Alarm.class);
            long pkColumnIndex = columnInfo.idIndex;
            long rowIndex = table.findFirstString(pkColumnIndex, ((com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface) object).realmGet$id());
            if (rowIndex == Table.NO_MATCH) {
                canUpdate = false;
            } else {
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.david.calendaralarm.data.pojo.Alarm.class), false, Collections.<String> emptyList());
                    realmObject = new io.realm.com_david_calendaralarm_data_pojo_AlarmRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, realmObject, object, cache) : copy(realm, object, update, cache);
    }

    public static com.david.calendaralarm.data.pojo.Alarm copy(Realm realm, com.david.calendaralarm.data.pojo.Alarm newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.david.calendaralarm.data.pojo.Alarm) cachedRealmObject;
        }

        // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
        com.david.calendaralarm.data.pojo.Alarm realmObject = realm.createObjectInternal(com.david.calendaralarm.data.pojo.Alarm.class, ((com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface) newObject).realmGet$id(), false, Collections.<String>emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);

        com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface realmObjectSource = (com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface) newObject;
        com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface realmObjectCopy = (com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface) realmObject;

        realmObjectCopy.realmSet$label(realmObjectSource.realmGet$label());
        realmObjectCopy.realmSet$time(realmObjectSource.realmGet$time());
        realmObjectCopy.realmSet$days(realmObjectSource.realmGet$days());
        return realmObject;
    }

    public static long insert(Realm realm, com.david.calendaralarm.data.pojo.Alarm object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.david.calendaralarm.data.pojo.Alarm.class);
        long tableNativePtr = table.getNativePtr();
        AlarmColumnInfo columnInfo = (AlarmColumnInfo) realm.getSchema().getColumnInfo(com.david.calendaralarm.data.pojo.Alarm.class);
        long pkColumnIndex = columnInfo.idIndex;
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$label = ((com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface) object).realmGet$label();
        if (realmGet$label != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.labelIndex, rowIndex, realmGet$label, false);
        }
        String realmGet$time = ((com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface) object).realmGet$time();
        if (realmGet$time != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.timeIndex, rowIndex, realmGet$time, false);
        }
        String realmGet$days = ((com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface) object).realmGet$days();
        if (realmGet$days != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.daysIndex, rowIndex, realmGet$days, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.david.calendaralarm.data.pojo.Alarm.class);
        long tableNativePtr = table.getNativePtr();
        AlarmColumnInfo columnInfo = (AlarmColumnInfo) realm.getSchema().getColumnInfo(com.david.calendaralarm.data.pojo.Alarm.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.david.calendaralarm.data.pojo.Alarm object = null;
        while (objects.hasNext()) {
            object = (com.david.calendaralarm.data.pojo.Alarm) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, rowIndex);
            String realmGet$label = ((com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface) object).realmGet$label();
            if (realmGet$label != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.labelIndex, rowIndex, realmGet$label, false);
            }
            String realmGet$time = ((com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface) object).realmGet$time();
            if (realmGet$time != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.timeIndex, rowIndex, realmGet$time, false);
            }
            String realmGet$days = ((com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface) object).realmGet$days();
            if (realmGet$days != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.daysIndex, rowIndex, realmGet$days, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.david.calendaralarm.data.pojo.Alarm object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.david.calendaralarm.data.pojo.Alarm.class);
        long tableNativePtr = table.getNativePtr();
        AlarmColumnInfo columnInfo = (AlarmColumnInfo) realm.getSchema().getColumnInfo(com.david.calendaralarm.data.pojo.Alarm.class);
        long pkColumnIndex = columnInfo.idIndex;
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$label = ((com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface) object).realmGet$label();
        if (realmGet$label != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.labelIndex, rowIndex, realmGet$label, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.labelIndex, rowIndex, false);
        }
        String realmGet$time = ((com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface) object).realmGet$time();
        if (realmGet$time != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.timeIndex, rowIndex, realmGet$time, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.timeIndex, rowIndex, false);
        }
        String realmGet$days = ((com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface) object).realmGet$days();
        if (realmGet$days != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.daysIndex, rowIndex, realmGet$days, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.daysIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.david.calendaralarm.data.pojo.Alarm.class);
        long tableNativePtr = table.getNativePtr();
        AlarmColumnInfo columnInfo = (AlarmColumnInfo) realm.getSchema().getColumnInfo(com.david.calendaralarm.data.pojo.Alarm.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.david.calendaralarm.data.pojo.Alarm object = null;
        while (objects.hasNext()) {
            object = (com.david.calendaralarm.data.pojo.Alarm) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
            }
            cache.put(object, rowIndex);
            String realmGet$label = ((com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface) object).realmGet$label();
            if (realmGet$label != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.labelIndex, rowIndex, realmGet$label, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.labelIndex, rowIndex, false);
            }
            String realmGet$time = ((com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface) object).realmGet$time();
            if (realmGet$time != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.timeIndex, rowIndex, realmGet$time, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.timeIndex, rowIndex, false);
            }
            String realmGet$days = ((com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface) object).realmGet$days();
            if (realmGet$days != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.daysIndex, rowIndex, realmGet$days, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.daysIndex, rowIndex, false);
            }
        }
    }

    public static com.david.calendaralarm.data.pojo.Alarm createDetachedCopy(com.david.calendaralarm.data.pojo.Alarm realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.david.calendaralarm.data.pojo.Alarm unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.david.calendaralarm.data.pojo.Alarm();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.david.calendaralarm.data.pojo.Alarm) cachedObject.object;
            }
            unmanagedObject = (com.david.calendaralarm.data.pojo.Alarm) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface unmanagedCopy = (com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface) unmanagedObject;
        com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface realmSource = (com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$label(realmSource.realmGet$label());
        unmanagedCopy.realmSet$time(realmSource.realmGet$time());
        unmanagedCopy.realmSet$days(realmSource.realmGet$days());

        return unmanagedObject;
    }

    static com.david.calendaralarm.data.pojo.Alarm update(Realm realm, com.david.calendaralarm.data.pojo.Alarm realmObject, com.david.calendaralarm.data.pojo.Alarm newObject, Map<RealmModel, RealmObjectProxy> cache) {
        com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface realmObjectTarget = (com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface) realmObject;
        com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface realmObjectSource = (com_david_calendaralarm_data_pojo_AlarmRealmProxyInterface) newObject;
        realmObjectTarget.realmSet$label(realmObjectSource.realmGet$label());
        realmObjectTarget.realmSet$time(realmObjectSource.realmGet$time());
        realmObjectTarget.realmSet$days(realmObjectSource.realmGet$days());
        return realmObject;
    }

    @Override
    public ProxyState<?> realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long rowIndex = proxyState.getRow$realm().getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com_david_calendaralarm_data_pojo_AlarmRealmProxy aAlarm = (com_david_calendaralarm_data_pojo_AlarmRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aAlarm.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aAlarm.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aAlarm.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
