package com.sharkBytesLab.DChat.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Toast;

import com.sharkBytesLab.DChat.R;

import io.github.dreierf.materialintroscreen.MaterialIntroActivity;
import io.github.dreierf.materialintroscreen.MessageButtonBehaviour;
import io.github.dreierf.materialintroscreen.SlideFragmentBuilder;

public class IntroActivity extends MaterialIntroActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(new SlideFragmentBuilder().title("Title 1").description("First Intro screen").image(R.drawable.profile_image).buttonsColor(R.color.primaryDark).backgroundColor(R.color.primary).build());

        addSlide(new SlideFragmentBuilder().title("Title 2").image(R.drawable.profile_image).buttonsColor(R.color.primaryDark).backgroundColor(R.color.primary).build());

        addSlide(new SlideFragmentBuilder().title("Title 3").image(R.drawable.profile_image).buttonsColor(R.color.primaryDark).backgroundColor(R.color.primary).build(),
                new MessageButtonBehaviour(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        showMessage("Intro Complete");

                    }
                }, "Work with love"));

    }
}