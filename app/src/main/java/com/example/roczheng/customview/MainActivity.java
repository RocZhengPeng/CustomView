package com.example.roczheng.customview;

import android.animation.ObjectAnimator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.roczheng.customview.view.StateView;

public class MainActivity extends AppCompatActivity {

    StateView stateView;
    Button buttonOne;
    Button buttonTow;
    Button buttonThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stateView = (StateView) findViewById(R.id.state);
        buttonOne = (Button) findViewById(R.id.btn_one);
        buttonTow = (Button) findViewById(R.id.btn_tow);
        buttonThree = (Button) findViewById(R.id.btn_three);

        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stateView.setState(0);
                stateView.stareAnimal();
            }
        });
        buttonTow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stateView.setState(1);
                stateView.stareAnimal();
            }
        });
        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stateView.setState(2);
                stateView.stareAnimal();
            }
        });
    }
}
