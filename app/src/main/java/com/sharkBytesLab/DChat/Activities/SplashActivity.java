package com.sharkBytesLab.DChat.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.window.SplashScreen;

import com.sharkBytesLab.DChat.MainActivity;
import com.sharkBytesLab.DChat.R;
import com.sharkBytesLab.DChat.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferences = this.getSharedPreferences("splash", MODE_PRIVATE);
        editor = preferences.edit();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run()
            {
                if(preferences.getBoolean("isMain", false))
                {
                    startActivity(new Intent(SplashActivity.this, SignInActivity.class));
                    finish();
                }
                else
                {
                    editor.putBoolean("isMain", true);
                    editor.apply();

                    TaskStackBuilder.create(SplashActivity.this)
                            .addNextIntentWithParentStack(new Intent(SplashActivity.this, SignInActivity.class))
                            .addNextIntent(new Intent(SplashActivity.this, IntroActivity.class))
                            .startActivities();
                }
            }
        }, 2000);

    }
}