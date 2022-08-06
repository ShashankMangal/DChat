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

        addSlide(new SlideFragmentBuilder().title("Welcome to DChat").description("\nQuickly and easy to chat and messaging with your friends. Passing information on any screen, any device instantly.").image(R.drawable.ic_welcome_svg).buttonsColor(R.color.primaryDark).backgroundColor(R.color.primary).build());

        addSlide(new SlideFragmentBuilder().title("Do real-time messaging").description("\nSend your information instantly with simplicity. The messages are End-To-End encrypted to secure your information.").image(R.drawable.ic_realtime_svg).buttonsColor(R.color.primaryDark).backgroundColor(R.color.primary).build());

        addSlide(new SlideFragmentBuilder().title("Cloud Storage").description("\nYour messages are saved on cloud server securely, It won't get deleted anyhow.").image(R.drawable.ic_storage_svg).buttonsColor(R.color.primaryDark).backgroundColor(R.color.primary).build());

        addSlide(new SlideFragmentBuilder().title("OK, Enough Intro").description("\nLet's chat").image(R.drawable.ic_start_svg).buttonsColor(R.color.primaryDark).backgroundColor(R.color.primary).build());


    }
}