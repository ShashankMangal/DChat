package com.sharkBytesLab.DChat.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.sharkBytesLab.DChat.R;
import com.sharkBytesLab.DChat.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setListeners();

    }

    private void setListeners()
    {
        binding.textSignIn.setOnClickListener(v->
                startActivity(new Intent(getApplicationContext(), SignInActivity.class)));
    }


}