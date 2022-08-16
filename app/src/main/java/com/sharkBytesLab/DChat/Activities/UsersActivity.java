package com.sharkBytesLab.DChat.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.sharkBytesLab.DChat.Adapters.UsersAdapter;
import com.sharkBytesLab.DChat.Models.User;
import com.sharkBytesLab.DChat.Utilities.Constants;
import com.sharkBytesLab.DChat.Utilities.PreferenceManager;
import com.sharkBytesLab.DChat.databinding.ActivityUsersBinding;

import java.util.ArrayList;
import java.util.List;


public class UsersActivity extends AppCompatActivity {

    private ActivityUsersBinding binding;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUsersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(getApplicationContext());
        getUsers();

    }

    private void setListeners()
    {
        binding.usersImageBack.setOnClickListener(v-> onBackPressed());
    }

    private void getUsers()
    {
        loading(true);
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection(Constants.KEY_COLLECTION_USERS)
                .get()
                .addOnCompleteListener(task ->
                {
                    loading(false);
                    String currentUserId = preferenceManager.getString(Constants.KEY_USER_ID);

                    if(task.isSuccessful() && task.getResult() != null)
                    {
                        List<User> users = new ArrayList<>();
                        for(QueryDocumentSnapshot queryDocumentSnapshot
                         : task.getResult())
                        {
                            if(currentUserId.equals(queryDocumentSnapshot.getId()))
                            {
                                continue;
                            }
                            User user = new User();
                            user.name = queryDocumentSnapshot.getString(Constants.KEY_NAME);
                            user.email = queryDocumentSnapshot.getString(Constants.KEY_EMAIL);
                            user.image = queryDocumentSnapshot.getString(Constants.KEY_IMAGE);
                            user.token = queryDocumentSnapshot.getString(Constants.KEY_FCM_TOKEN);
                            users.add(user);
                        }
                        if(users.size() > 0)
                        {
                            UsersAdapter usersAdapter = new UsersAdapter(users);
                            binding.usersRecyclerView.setAdapter(usersAdapter);
                            binding.usersRecyclerView.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            showErrorMessage();
                        }
                    }
                    else
                    {
                        showErrorMessage();
                    }
                });
    }

    private void showErrorMessage()
    {
        binding.usersTextErrorMessage.setText(String.format("%s", "No User Available"));
        binding.usersTextErrorMessage.setVisibility(View.VISIBLE);
    }

    private void loading(Boolean isLoading)
    {
        if(isLoading)
        {
            binding.usersProgressBar.setVisibility(View.VISIBLE);
        }
        else
        {
            binding.usersProgressBar.setVisibility(View.INVISIBLE);
        }
    }

}