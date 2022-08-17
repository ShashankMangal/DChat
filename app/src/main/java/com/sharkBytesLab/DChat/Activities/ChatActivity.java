package com.sharkBytesLab.DChat.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.sharkBytesLab.DChat.Models.User;
import com.sharkBytesLab.DChat.R;
import com.sharkBytesLab.DChat.Utilities.Constants;
import com.sharkBytesLab.DChat.databinding.ActivityChatBinding;

public class ChatActivity extends AppCompatActivity {

    private ActivityChatBinding binding;
    private User receiverUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setListeners();
        loadReceiverDetails();

    }

    private void loadReceiverDetails()
    {
        receiverUser = (User) getIntent().getSerializableExtra(Constants.KEY_USER);
        binding.chatTextName.setText(receiverUser.name);
    }

    private void setListeners()
    {
        binding.chatImageBack.setOnClickListener(v -> onBackPressed());
    }


}