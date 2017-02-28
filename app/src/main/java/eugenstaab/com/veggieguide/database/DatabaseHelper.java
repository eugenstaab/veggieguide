package eugenstaab.com.veggieguide.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by estaab on 28.01.17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "ObstLotse.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(DatabaseHelper.class.getName(), "onCreate");
        db.execSQL(DatabaseSchema.SQL_CREATE_PLANT);
        db.execSQL(DatabaseSchema.SQL_CREATE_GROWING_ENTRY);
        fillDatabaseInitially(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {

        /*
         * ONLY FOR TESTING:
         */
        Log.d(DatabaseHelper.class.getName(), "onOpen");
        db.execSQL(DatabaseSchema.SQL_DELETE_PLANT);
        db.execSQL(DatabaseSchema.SQL_DELETE_GROWING_ENTRY);
        db.execSQL(DatabaseSchema.SQL_CREATE_PLANT);
        db.execSQL(DatabaseSchema.SQL_CREATE_GROWING_ENTRY);
        fillDatabaseInitially(db);
        db.execSQL("DROP TABLE IF EXISTS plants");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(DatabaseHelper.class.getName(), "onUpgrade from " + oldVersion + " to " + newVersion);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(DatabaseHelper.class.getName(), "onDownGrade from " + oldVersion + " to " + newVersion);
    }

    private void fillDatabaseInitially(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(DatabaseSchema.Plant._ID, 1);
        values.put(DatabaseSchema.Plant.COLUMN_NAME_KEY, "fruit_banana");
        values.put(DatabaseSchema.Plant.COLUMN_PLANT_TYPE, DatabaseSchema.Plant.COLUMN_PLANT_TYPE_FRUIT);
        db.insert(DatabaseSchema.Plant.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(DatabaseSchema.Plant._ID, 2);
        values.put(DatabaseSchema.Plant.COLUMN_NAME_KEY, "veggie_potato");
        values.put(DatabaseSchema.Plant.COLUMN_PLANT_TYPE, DatabaseSchema.Plant.COLUMN_PLANT_TYPE_VEGGIE);
        db.insert(DatabaseSchema.Plant.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(DatabaseSchema.GrowingEntry.COLUMN_PLANT_ID, 1);
        values.put(DatabaseSchema.GrowingEntry.COLUMN_GROWING_TYPE, DatabaseSchema.GrowingEntry.COLUMN_GROWING_TYPE_SUN);
        values.put(DatabaseSchema.GrowingEntry.COLUMN_START, getIntFor(Calendar.JANUARY, 1));
        values.put(DatabaseSchema.GrowingEntry.COLUMN_END,  getIntFor(Calendar.MARCH, 1));
        db.insert(DatabaseSchema.GrowingEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(DatabaseSchema.GrowingEntry.COLUMN_PLANT_ID, 1);
        values.put(DatabaseSchema.GrowingEntry.COLUMN_GROWING_TYPE, DatabaseSchema.GrowingEntry.COLUMN_GROWING_TYPE_COVERED);
        values.put(DatabaseSchema.GrowingEntry.COLUMN_START, getIntFor(Calendar.MARCH, 2));
        values.put(DatabaseSchema.GrowingEntry.COLUMN_END,  getIntFor(Calendar.JUNE, 1));
        db.insert(DatabaseSchema.GrowingEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(DatabaseSchema.GrowingEntry.COLUMN_PLANT_ID, 2);
        values.put(DatabaseSchema.GrowingEntry.COLUMN_GROWING_TYPE, DatabaseSchema.GrowingEntry.COLUMN_GROWING_TYPE_COVERED);
        values.put(DatabaseSchema.GrowingEntry.COLUMN_START, getIntFor(Calendar.JANUARY, 1));
        values.put(DatabaseSchema.GrowingEntry.COLUMN_END,  getIntFor(Calendar.MARCH, 1));
        db.insert(DatabaseSchema.GrowingEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(DatabaseSchema.GrowingEntry.COLUMN_PLANT_ID, 2);
        values.put(DatabaseSchema.GrowingEntry.COLUMN_GROWING_TYPE, DatabaseSchema.GrowingEntry.COLUMN_GROWING_TYPE_SUN);
        values.put(DatabaseSchema.GrowingEntry.COLUMN_START, getIntFor(Calendar.MARCH, 2));
        values.put(DatabaseSchema.GrowingEntry.COLUMN_END,  getIntFor(Calendar.JUNE, 1));
        db.insert(DatabaseSchema.GrowingEntry.TABLE_NAME, null, values);
    }

    private int getIntFor(int month, int day) {
        return 100*month+day;
    }


}
