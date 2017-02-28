package eugenstaab.com.veggieguide.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import eugenstaab.com.veggieguide.R;
import eugenstaab.com.veggieguide.domain.PlantDto;

public class PlantAdapter extends ArrayAdapter<PlantDto> {

    private final Context context;

    public PlantAdapter(Context context, ArrayList<PlantDto> plants) {
        super(context, 0, plants);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PlantDto plant = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_plant, parent, false);
        }

        TextView tvNameKey = (TextView) convertView.findViewById(R.id.tvNameKey);
        TextView tvType = (TextView) convertView.findViewById(R.id.tvType);

        tvNameKey.setText(plant.getName(context));
        tvType.setText(plant.type.toString());

        return convertView;
    }


}
