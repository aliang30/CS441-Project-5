package com.example.cs441_project5;

//GameOver Screen for black car players

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main4Activity extends AppCompatActivity {

    private Button retry;
    private Button mainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        retry = (Button) findViewById(R.id.button);
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
            }
        });

        mainMenu = (Button) findViewById(R.id.button2);
        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });
    }

    //Opens game screen
    public void openActivity() {
        Intent intent = new Intent (this, BlackCar.class);
        startActivity(intent);
    }

    //Opens main menu
    public void openActivity2() {
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }
}
