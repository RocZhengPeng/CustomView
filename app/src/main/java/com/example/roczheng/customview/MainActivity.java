package com.example.roczheng.customview;

import android.animation.ObjectAnimator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.roczheng.customview.view.StateView;

public class MainActivity extends AppCompatActivity {

    StateView view;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = (StateView) findViewById(R.id.state);
        button= (Button) findViewById(R.id.btn_one);
        final ObjectAnimator animator = ObjectAnimator.ofInt(view, "progress", 0, 440);
        animator.setDuration(1000);
        animator.setInterpolator(new FastOutSlowInInterpolator());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animator.start();
            }
        });
    }
}
