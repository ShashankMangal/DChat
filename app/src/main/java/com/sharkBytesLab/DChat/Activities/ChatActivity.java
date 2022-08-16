package com.sharkBytesLab.DChat.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.sharkBytesLab.DChat.R;
import com.sharkBytesLab.DChat.databinding.ActivityChatBinding;

public class ChatActivity extends AppCompatActivity {

    private ActivityChatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }
}