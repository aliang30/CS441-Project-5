package com.example.cs441_project5;

//Pick your car screen

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private Button play1;
    private Button play2;
    private Button mainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        play1 = (Button) findViewById(R.id.button);
        play1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
            }
        });

        play2 = (Button) findViewById(R.id.button1);
        play2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity1();
            }
        });


        mainMenu = (Button) findViewById(R.id.button2);
        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });

        //blinking text animation
        blinkingEffect();
    }

    private void blinkingEffect() {
        final Handler handler = new Handler();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //1000 millisecond = 1 second
                int blinkTime = 1000;

                try{Thread.sleep(blinkTime);}
                catch (Exception e) {}

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //Finds text and makes it disappear
                        TextView text = findViewById(R.id.text);

                        if(text.getVisibility() == View.VISIBLE){
                            text.setVisibility(View.INVISIBLE);
                        }
                        else{
                            //Makes text reappear
                            text.setVisibility(View.VISIBLE);
                        }
                        blinkingEffect();
                    }
                });
            }
        }).start();
    }

    //Opens game screen
    public void openActivity() {
        Intent intent = new Intent (this, RedCar.class);
        startActivity(intent);
    }

    //Opens game screen
    public void openActivity1() {
        Intent intent = new Intent (this, BlackCar.class);
        startActivity(intent);
    }

    //Opens main menu
    public void openActivity2() {
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }
}

