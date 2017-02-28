package eugenstaab.com.veggieguide.database;

import android.provider.BaseColumns;

import eugenstaab.com.veggieguide.domain.PlantType;

/**
 * Created by estaab on 28.01.17.
 */

public class DatabaseSchema {

    private DatabaseSchema() {
    }

    public static class Plant implements BaseColumns {
        public static final String TABLE_NAME = "plant";
        public static final String COLUMN_NAME_KEY = "name_key";
        public static final String COLUMN_PLANT_TYPE = "plant_type";

        public static final String COLUMN_PLANT_TYPE_FRUIT = PlantType.FRUIT.toString();
        public static final String COLUMN_PLANT_TYPE_VEGGIE = PlantType.VEGGIE.toString();
    }

    public static final String SQL_CREATE_PLANT =
            "CREATE TABLE " + Plant.TABLE_NAME + " (" +
                    Plant._ID + " INTEGER PRIMARY KEY," +
                    Plant.COLUMN_NAME_KEY + " TEXT," +
                    Plant.COLUMN_PLANT_TYPE + " TEXT)";

    public static final String SQL_DELETE_PLANT =
            "DROP TABLE IF EXISTS " + Plant.TABLE_NAME;

    public static class GrowingEntry implements BaseColumns {
        public static final String TABLE_NAME = "growing_entry";
        public static final String COLUMN_GROWING_TYPE = "growing_type";
        public static final String COLUMN_PLANT_ID = "plant_id";
        public static final String COLUMN_START = "start";
        public static final String COLUMN_END = "end";

        public static final String COLUMN_GROWING_TYPE_SUN = "SUN";
        public static final String COLUMN_GROWING_TYPE_COVERED = "COVERED";
    }

    public static final String SQL_CREATE_GROWING_ENTRY =
            "CREATE TABLE " + GrowingEntry.TABLE_NAME + " (" +
                    GrowingEntry._ID + " INTEGER PRIMARY KEY," +
                    GrowingEntry.COLUMN_GROWING_TYPE + " TEXT," +
                    GrowingEntry.COLUMN_PLANT_ID + " INTEGER KEY REFERENCING " + Plant.TABLE_NAME + "," +
                    GrowingEntry.COLUMN_START + " INTEGER," +
                    GrowingEntry.COLUMN_END + " INTEGER)";

    public static final String SQL_DELETE_GROWING_ENTRY =
            "DROP TABLE IF EXISTS " + GrowingEntry.TABLE_NAME;

}
