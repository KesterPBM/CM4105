package cm3110.gigachadapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.*;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class IntroScreen extends AppCompatActivity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_screen);

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(IntroScreen.this,MainActivity.class);
                startActivity(intent);
                finish();
                getSupportActionBar().hide();
            }
        },5000);
    }
}
