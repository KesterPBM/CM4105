package cm3110.gigachadapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bnv = findViewById(R.id.bottomNavigationView);
        bnv.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection


                //The Top Navigation Bar with Settings//
                NavController navControllerTop = Navigation.findNavController(findViewById(R.id.fragmentContainerView));
                int currentFragmentIdTop = navControllerTop.getCurrentDestination().getId();

                if(item.getItemId()== R.id.appBarSettings) {
                    if (currentFragmentIdTop != R.id.settingstab) {
                        navControllerTop.navigate(R.id.settingstab);
                        return true;
                    }
                }
                return false;

        }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        //The Bottom Navigation Bar with Home, Calorie, Workout and Stocks//
        NavController navController = Navigation.findNavController(findViewById(R.id.fragmentContainerView));
        int currentFragmentId = navController.getCurrentDestination().getId();

        if (item.getItemId() == R.id.bottomCalorie) {
            if (currentFragmentId != R.id.calorie) {
                navController.navigate(R.id.calorie);
            }
            return true;
        } else if (item.getItemId() == R.id.bottomHome) {
            if (currentFragmentId != R.id.homepage) {
                navController.navigate(R.id.homepage);
            }
            return true;
        } else if (item.getItemId() == R.id.bottomPhysical) {
            if (currentFragmentId != R.id.findcrypto) {
                navController.navigate(R.id.findcrypto);
            }
            return true;
        } else if (item.getItemId() == R.id.bottomWorkout) {
            if (currentFragmentId != R.id.workoutDaysSelected) {
                navController.navigate(R.id.workoutDaysSelected);
            }
            return true;
        }


        return false;
    }
}

