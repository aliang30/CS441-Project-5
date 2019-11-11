package com.example.cs441_project5;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class RedCar extends AppCompatActivity {

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
    private ImageView cone;

    //Coordinates for cone
    private float coneX;
    private float coneY;


    private TextView text_score;
    private TextView text_lives;


    private Handler handler = new Handler();
    private Timer timer = new Timer();

    private int score = 0;
    private int lives = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_car);
        mainLayout = (RelativeLayout) findViewById(R.id.main);

        text_score = findViewById(R.id.score);
        text_lives = findViewById(R.id.lives);

        road = findViewById(R.id.line);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
            }
        });

        car = findViewById(R.id.audi);
        cone = findViewById(R.id.cone);


        car.setOnTouchListener(onTouchListener());

        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;

        road.setX(80.0f);
        road.setY(screenHeight + 80.0f);

        //Assigning initial coordinates

        cone.setX(80.0f);
        cone.setY(screenHeight + 80.0f);

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
        }, 1, 20);


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

        //Hit collision to cone
        if (hitDetect(coneX, coneY)) {
            //erase cone object
            coneX = -500;
            lives--;
            text_lives.setText("Lives: " + lives);
            if(lives == 0){
                Intent intent = new Intent (this, Main3Activity.class);
                intent.putExtra("SCORE", score);
                startActivity(intent);
            }

        }
        else{
            score += 1;
            text_score.setText("Score: " + score);
           // if(score == 1000){
           //     Intent intent2 = new Intent(this, WinScreen.class);
           //     startActivity(intent2);
           // }
        }




        //cone speed
        coneY = coneY + 40;

        if(cone.getY() > screenHeight) {
            coneX = (float) Math.floor(Math.random() * (screenWidth - cone.getWidth()));
            coneY = -100.0f;
        }
        cone.setX(coneX);
        cone.setY(coneY);
    }

    //returns true if car collides with a cone
    public boolean hitDetect(float x, float y) {
        if (car.getX() < x && x < (car.getX() + car.getWidth()) &&
                car.getY() < y && y < (car.getY() + car.getHeight())) {
            return true;
        }
        return false;
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
                    case MotionEvent.ACTION_POINTER_DOWN:
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                        layoutParams.leftMargin = x - xDelta;
                        layoutParams.topMargin = y - yDelta;
                        layoutParams.rightMargin = -500;
                        layoutParams.bottomMargin = -500;
                        view.setLayoutParams(layoutParams);
                        break;
                }

                mainLayout.invalidate();
                return true;
            }
        };
    }

}