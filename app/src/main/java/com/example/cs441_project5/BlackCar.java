package com.example.cs441_project5;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.view.View.OnTouchListener;
import java.util.Timer;
import java.util.TimerTask;

public class BlackCar extends AppCompatActivity {

    int i = 0;

    private ViewGroup mainLayout;

    private int screenWidth;
    private int screenHeight;

    private Button button;

    private ImageView road;

    private float roadX;
    private float roadY;

    private int xDelta;
    private int yDelta;

    private ImageView car;
    private ImageView ambulance;
    private ImageView truck;
    private ImageView taxi;
    private ImageView police;

    //Coordinates for ambulance
    private float ambulanceX;
    private float ambulanceY;

    //Coordinates for truck
    private float truckX;
    private float truckY;

    //Coordinates for taxi
    private float taxiX;
    private float taxiY;

    //Coordinates for police
    private float policeX;
    private float policeY;

    private Handler handler = new Handler();
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_car);
        mainLayout = (RelativeLayout) findViewById(R.id.main);

        road = findViewById(R.id.line);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
            }
        });


        car = findViewById(R.id.black_viper);
        truck = findViewById(R.id.truck);
        taxi = findViewById(R.id.taxi);
        police = findViewById(R.id.police);
        ambulance = findViewById(R.id.ambulance);


        car.setOnTouchListener(onTouchListener());

        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;

        //Giving both apples initial coordinates
        road.setX(80.0f);
        road.setY(screenHeight + 80.0f);

        //Assigning initial coordinates
        truck.setX(80.0f);
        truck.setY(screenHeight + 80.0f);

        taxi.setX(80.0f);
        taxi.setY(screenHeight + 80.0f);

        police.setX(80.0f);
        police.setY(screenHeight + 80.0f);

        ambulance.setX(80.0f);
        ambulance.setY(screenHeight + 80.0f);


        //Running road movement
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

        //Running road movement
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        carPos();
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

    //Car movement
    public void carPos() {

        //ambulance speed
        ambulanceY = ambulanceY + 40;

        if(ambulance.getY() > screenHeight) {
            ambulanceX = (float) Math.floor(Math.random() * (screenWidth - ambulance.getWidth()));
            ambulanceY = -100.0f;
        }
        ambulance.setX(ambulanceX);
        ambulance.setY(ambulanceY);


        //truck speed
        truckY = truckY + 30;

        if(truck.getY() > screenHeight) {
            truckX = (float) Math.floor(Math.random() * (screenWidth - truck.getWidth()));
            truckY = -100.0f;
        }
        truck.setX(truckX);
        truck.setY(truckY);

        //taxi apple speed
        taxiY = taxiY + 30;

        if(taxi.getY() > screenHeight) {
            taxiX = (float) Math.floor(Math.random() * (screenWidth - taxi.getWidth()));
            taxiY = -100.0f;
        }
        taxi.setX(taxiX);
        taxi.setY(taxiY);

        //police apple speed
        policeY = policeY + 30;

        if(police.getY() > screenHeight) {
            policeX = (float) Math.floor(Math.random() * (screenWidth - police.getWidth()));
            policeY = -100.0f;
        }
        police.setX(policeX);
        police.setY(policeY);
    }


    private OnTouchListener onTouchListener() {
        return new OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int x = (int) event.getRawX();
                final int y = (int) event.getRawY();

                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                        xDelta = x - lParams.leftMargin;
                        yDelta = y - lParams.topMargin;
                        break;

                    case MotionEvent.ACTION_UP:
                        break;

                    case MotionEvent.ACTION_MOVE:
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                        layoutParams.leftMargin = x - xDelta;
                        layoutParams.topMargin = y - yDelta;
                        layoutParams.rightMargin = 0;
                        layoutParams.bottomMargin = 0;
                        view.setLayoutParams(layoutParams);
                        break;
                }

                mainLayout.invalidate();
                return true;
            }
        };
    }

}