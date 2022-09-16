package com.szelecki.immersion.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.szelecki.immersion.R;
import com.szelecki.immersion.Utils;
import com.szelecki.immersion.models.EnumLanguages;
import com.szelecki.immersion.models.ModelUser;
import com.szelecki.immersion.viewModels.MainActivityViewModel;

public class LoginActivity extends AppCompatActivity {

    Button button;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        button = findViewById(R.id.button);

        ModelUser user = ModelUser.getInstance();
        preferences = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);

        MainActivityViewModel viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setFirstname("Mateusz");
                user.setSurname("Szelecki");
                EnumLanguages enumLanguages = Utils.setupChangedLanguage(preferences.getString("userLanguage", "default"));
                user.setLanguage(enumLanguages);
                user.setAuthentication("12345");
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}