package eugenstaab.com.veggieguide.domain;

import android.content.Context;

public class PlantDto {

    public final String nameKey;
    public final PlantType type;

    public PlantDto(String nameKey, PlantType type) {
        this.nameKey = nameKey;
        this.type = type;
    }

    public String getName(Context context) {
        int nameResourceID = context.getResources().getIdentifier(nameKey, "string", context.getApplicationInfo().packageName);
        if (nameResourceID == 0) {
            throw new IllegalArgumentException("No resource string found with name " + nameKey);
        } else {
            return context.getString(nameResourceID);
        }
    }

}
