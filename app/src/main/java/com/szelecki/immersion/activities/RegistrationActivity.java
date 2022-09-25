package com.szelecki.immersion.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.szelecki.immersion.R;

import java.util.ArrayList;

public class RegistrationActivity extends AppCompatActivity {

    Button signUpButton;
    TextView textViewLanguages, goToLoginButton;
    EditText enterName, enterSurname, enterEmail, enterPassword;

    ArrayList<String> languages;
    int langIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();

        signUpButton = findViewById(R.id.buttonRegistration);
        goToLoginButton = findViewById(R.id.goToLoginButton);
        textViewLanguages = findViewById(R.id.textViewLanguages);
        enterName = findViewById(R.id.enterNameRegistration);
        enterSurname = findViewById(R.id.enterSurnameRegistration);
        enterEmail = findViewById(R.id.enterEmailRegistration);
        enterPassword = findViewById(R.id.enterPasswordRegistration);

        initLanguagesList();
        Handler handler = new Handler();
        Runnable languagesRunnable = new Runnable() {
            @Override
            public void run() {
                if (langIndex == 11) { langIndex = 0; }
                textViewLanguages.setText(languages.get(langIndex));
                Log.d("PLACEHOLDER", languages.get(langIndex));
                langIndex ++;
                handler.postDelayed(this, 1000);
            }
        };

        languagesRunnable.run();

        goToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });
    }

    private void initLanguagesList() {
        languages = new ArrayList<>();
        languages.add("danish"); languages.add("english"); languages.add("finnish"); languages.add("french");
        languages.add("german"); languages.add("greek"); languages.add("italian"); languages.add("norwegian");
        languages.add("polish"); languages.add("spanish"); languages.add("swedish");
    }
}