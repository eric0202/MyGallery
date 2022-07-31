package com.eric.gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    TextView welcome;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcome = findViewById(R.id.welcome);
        lottieAnimationView = findViewById(R.id.lottie);


        new Handler().postDelayed(() -> {
            Intent intent = new Intent(getApplicationContext(), FoldersActivity.class);
            startActivity(intent);
        },2000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.finish();
    }
}