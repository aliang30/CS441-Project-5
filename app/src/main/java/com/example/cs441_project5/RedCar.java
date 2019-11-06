package com.example.cs441_project5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class RedCar extends AppCompatActivity {

    private int screenWidth;
    private int screenHeight;

    private Button button;

    private ImageView road;

    private float roadX;
    private float roadY;

    private Handler handler = new Handler();
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_car);

        road = findViewById(R.id.line);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
            }
        });

        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;

        //Giving both apples initial coordinates
        road.setX(80.0f);
        road.setY(screenHeight + 80.0f);

        //Running apple movement
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        linePos();
                    }
                });
            }
        }, 0, 20);

    }

    public void openActivity() {
        Intent intent = new Intent (this, Main3Activity.class);
        startActivity(intent);
    }

    //line movement
    public void linePos() {
        //line speed
        roadY = roadY + 130;

        if(road.getY() > screenHeight) {
            roadX = (float) Math.floor(Math.random() * (screenWidth - road.getWidth()));
            roadY = -100.0f;
        }
        road.setX(roadX);
        road.setY(roadY);
    }
}
