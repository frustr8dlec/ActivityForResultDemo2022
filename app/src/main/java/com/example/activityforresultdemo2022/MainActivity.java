package com.example.activityforresultdemo2022;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.activityforresultdemo2022.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // Setup a callback for getting the result from NameActivity
    ActivityResultLauncher<Intent> mGetNameActivity =
        registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                // Validity checks
                if (result.getResultCode() != Activity.RESULT_OK) {
                    Log.i("MainIntent",
                    "Name activity has returned using the back button or cancelled.");
                    return;
                }

                Intent intent = result.getData();
                if (intent == null){
                    Log.i("MainIntent", "Name activity has returned no intent.");
                    return;
                }

                if (!intent.hasExtra("nameData")){
                        Log.i("MainIntent", "Name activity hasn't returned extra data.");
                        return;
                }

                Log.i("MainIntent", "Name activity has returned : "
                        + intent.getStringExtra("nameData"));
            });

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // UI Setup
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        // Attach events to UI
        binding.fab.setOnClickListener(view -> launchNameActivity());
    }

    private void launchNameActivity() {
        // Start an activity for a result explicitly
        // using the launcher with the Intent you want to start
        Intent intent = new Intent(this, NameActivity.class);
        mGetNameActivity.launch(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}