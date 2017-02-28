package eugenstaab.com.veggieguide.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import eugenstaab.com.veggieguide.domain.PlantDto;
import eugenstaab.com.veggieguide.domain.PlantType;

public class DatabaseQueries {

    @NonNull
    public static List<PlantDto> selectPlants(SQLiteDatabase db, boolean showVeggies, boolean showFruit) {
        List<PlantDto> plants = new ArrayList<>();
        List<String> plantTypes = new ArrayList<String>();

        if (showVeggies) {
            plantTypes.add(DatabaseSchema.Plant.COLUMN_PLANT_TYPE_VEGGIE);
        }

        if (showFruit) {
            plantTypes.add(DatabaseSchema.Plant.COLUMN_PLANT_TYPE_FRUIT);
        }

        String[] plantTypesArray = plantTypes.toArray(new String[plantTypes.size()]);

        if (plantTypesArray.length == 0) {
            return plants;
        }

        Cursor cursor = db.rawQuery("SELECT p." + DatabaseSchema.Plant.COLUMN_NAME_KEY + ", p." + DatabaseSchema.Plant.COLUMN_PLANT_TYPE + "  FROM PLANT p WHERE p.plant_type IN (" + DatabaseUtils.makePlaceholders(plantTypesArray.length) + ")", plantTypesArray);

        while (cursor.moveToNext()) {
            String nameKey = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseSchema.Plant.COLUMN_NAME_KEY));
            PlantType type = PlantType.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseSchema.Plant.COLUMN_PLANT_TYPE)));
            plants.add(new PlantDto(nameKey, type));
        }

        return plants;
    }

}
