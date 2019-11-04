package com.example.cs441_project5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main3Activity extends AppCompatActivity {

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

    //Opens main menu screen
    public void openActivity() {
        Intent intent = new Intent (this, Main2Activity.class);
        startActivity(intent);
    }

    //Opens game screen
    public void openActivity2() {
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }
}
