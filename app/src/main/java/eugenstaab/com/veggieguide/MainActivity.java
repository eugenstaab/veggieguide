package eugenstaab.com.veggieguide;

import android.database.sqlite.SQLiteDatabase;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import eugenstaab.com.veggieguide.database.DatabaseHelper;
import eugenstaab.com.veggieguide.database.DatabaseQueries;
import eugenstaab.com.veggieguide.domain.PlantDto;
import eugenstaab.com.veggieguide.view.PlantAdapter;

public class MainActivity extends AppCompatActivity {

    private static boolean showVeggies = true;
    private static boolean showFruit = true;

    private DatabaseHelper databaseHelper;
    private PlantAdapter plantAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            showVeggies = savedInstanceState.getBoolean("showVeggies", true);
            showFruit = savedInstanceState.getBoolean("showFruit", true);
        }

        databaseHelper = new DatabaseHelper(getApplicationContext());

        initializeGui();
    }

    private void initializeGui() {
        setContentView(R.layout.activity_main);

        plantAdapter = new PlantAdapter(this, new ArrayList<PlantDto>());

        final ListView listView = (ListView) findViewById(R.id.listview);
        listView.setTextFilterEnabled(true);
        listView.setAdapter(plantAdapter);

        ToggleButton toggleButtonFruit = (ToggleButton) findViewById(R.id.toggleButtonFruit);
        toggleButtonFruit.setChecked(showFruit);
        toggleButtonFruit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                showList(listView);
            }
        });

        ToggleButton toggleButtonVeggies = (ToggleButton) findViewById(R.id.toggleButtonVeggies);
        toggleButtonVeggies.setChecked(showVeggies);
        toggleButtonVeggies.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                showList(listView);
            }
        });

        showList(listView);
    }

    private void showList(ListView listView) {
        List<PlantDto> plants = selectPlants();

        // TODO: https://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView

        plantAdapter.clear();
        plantAdapter.addAll(plants);
    }

    private List<PlantDto> selectPlants() {
        boolean showVeggies = ((ToggleButton) findViewById(R.id.toggleButtonVeggies)).isChecked();
        boolean showFruit = ((ToggleButton) findViewById(R.id.toggleButtonFruit)).isChecked();

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        return DatabaseQueries.selectPlants(db, showVeggies, showFruit);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        Log.d(MainActivity.class.getName(), "onSaveInstanceState");

        outState.putBoolean("showVeggies", showVeggies);
        outState.putBoolean("showFruit", showFruit);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(MainActivity.class.getName(), "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(MainActivity.class.getName(), "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(MainActivity.class.getName(), "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(MainActivity.class.getName(), "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(MainActivity.class.getName(), "onDestroy");
        databaseHelper.close();
    }
}
