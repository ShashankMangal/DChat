package com.sharkBytesLab.DChat.Activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.sharkBytesLab.DChat.Utilities.Constants;
import com.sharkBytesLab.DChat.Utilities.PreferenceManager;
import com.sharkBytesLab.DChat.databinding.ActivitySignUpBinding;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private PreferenceManager preferenceManager;
    private String profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(getApplicationContext());
        setListeners();

    }

    private void setListeners()
    {
        binding.textSignIn.setOnClickListener(v->
                startActivity(new Intent(getApplicationContext(), SignInActivity.class)));

        binding.signUpButton.setOnClickListener(v ->
                {

            if(isValidSignUpDetails())
            {
                signUp();
            }
                }
                );

        binding.signUpImageProfile.setOnClickListener(v ->
        {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            pickImage.launch(intent);
        });

    }

    private void showToast(String message)
    {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void signUp()
    {
        loading(true);
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        HashMap<String , Object> user = new HashMap<>();
        user.put(Constants.KEY_NAME, binding.signUpName.getText().toString());
        user.put(Constants.KEY_EMAIL, binding.signUpEmail.getText().toString());
        user.put(Constants.KEY_PASSWORD, binding.signUpConfirmPassword.getText().toString());
        user.put(Constants.KEY_IMAGE, profileImage);
        database.collection(Constants.KEY_COLLECTION_USERS)
                .add(user).addOnSuccessListener(documentReference -> {
                    loading(false);
                    preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN, true);
                    preferenceManager.putString(Constants.KEY_USER_ID, documentReference.getId());
                    preferenceManager.putString(Constants.KEY_NAME, binding.signUpName.getText().toString());
                    preferenceManager.putString(Constants.KEY_IMAGE, profileImage);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }).addOnFailureListener(exception -> {
                    loading(false);
                    showToast(exception.getMessage());
                });
    }

    private Boolean isValidSignUpDetails()
    {
        if(profileImage == null)
        {
            showToast("Select Profile Image !");
            return false;
        }
        else if(binding.signUpName.getText().toString().trim().isEmpty())
        {
            binding.signUpName.setError("Enter Name !");
            return false;
        }
        else if(binding.signUpEmail.getText().toString().trim().isEmpty())
        {
            binding.signUpEmail.setError("Enter Email !");
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(binding.signUpEmail.getText().toString()).matches())
        {
            binding.signUpEmail.setError("Enter valid Email !");
        }
        else if(binding.signUpPassword.getText().toString().trim().isEmpty())
        {
            binding.signUpPassword.setError("Enter Password !");
            return false;
        }
        else if(binding.signUpConfirmPassword.getText().toString().trim().isEmpty())
        {
            binding.signUpPassword.setError("Confirm your Password !");
            return false;
        }
        else if(!binding.signUpPassword.getText().toString().equals(binding.signUpConfirmPassword.getText().toString()))
        {
            binding.signUpPassword.setError("Error !");
            binding.signUpConfirmPassword.setError("Error !");
            showToast("Password & Confirm password should be same !");
            return false;
        }
        return true;
    }

    private void loading(Boolean isLoading)
    {
        if(isLoading)
        {
            binding.signUpButton.setVisibility(View.INVISIBLE);
            binding.signUpProgressBar.setVisibility(View.VISIBLE);
        }
        else
        {
            binding.signUpButton.setVisibility(View.VISIBLE);
            binding.signUpProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    private String profileImage(Bitmap bitmap)
    {
        int previewWidth = 160;
        int previewHeight = bitmap.getHeight() * previewWidth / bitmap.getWidth();
        Bitmap previewbitmap = Bitmap.createScaledBitmap(bitmap, previewWidth, previewHeight, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        previewbitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    private final ActivityResultLauncher<Intent> pickImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {

                if(result.getResultCode() == RESULT_OK)
                {
                        if(result.getData()!=null)
                        {
                            Uri imageUri = result.getData().getData();
                            try
                            {
                                InputStream inputStream = getContentResolver().openInputStream(imageUri);
                                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                binding.signUpImageProfile.setImageBitmap(bitmap);
                                binding.signupProfileImageText.setVisibility(View.GONE);
                                profileImage = profileImage(bitmap);
                            }catch(FileNotFoundException e)
                            {
                                e.printStackTrace();
                            }
                        }
                }

            }
    );




}