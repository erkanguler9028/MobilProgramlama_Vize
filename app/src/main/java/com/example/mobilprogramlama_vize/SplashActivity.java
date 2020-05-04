package com.example.mobilprogramlama_vize;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    private TextView hosgeldiniz;
    private ImageView logo;
    private static int gecis_suresi = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        hosgeldiniz=(TextView)findViewById(R.id.hosgeldiniz);
        logo=(ImageView)findViewById(R.id.logo);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.animation);

        hosgeldiniz.startAnimation(animation);
        logo.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent (SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, gecis_suresi);
    }
}
