package com.szelecki.immersion.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.szelecki.immersion.R;
import com.szelecki.immersion.utils.TimeAndLanguage;
import com.szelecki.immersion.models.EnumLanguages;
import com.szelecki.immersion.models.ModelUser;
import com.szelecki.immersion.viewModels.AddLanguageActivityViewModel;
import com.szelecki.immersion.viewModels.FriendsFragmentViewModel;
import com.szelecki.immersion.viewModels.MainActivityViewModel;

import java.util.ArrayList;
import java.util.Date;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    TextView textViewLanguages, goToSignUpButton;
    EditText enterEmail, enterPassword;

    ArrayList<String> languages;
    int langIndex;

    FirebaseAuth auth;

    ModelUser user;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        textViewLanguages = findViewById(R.id.textViewLanguages);
        loginButton = findViewById(R.id.buttonLogin);
        goToSignUpButton = findViewById(R.id.goToSignUpButton);
        enterEmail = findViewById(R.id.enterEmailLogin);
        enterPassword = findViewById(R.id.enterPasswordLogin);

        user = ModelUser.getInstance();
        preferences = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        auth = FirebaseAuth.getInstance();

        AddLanguageActivityViewModel viewModelLanguage = new ViewModelProvider(this).get(AddLanguageActivityViewModel.class);
        FriendsFragmentViewModel viewModelFriend = new ViewModelProvider(this).get(FriendsFragmentViewModel.class);
        MainActivityViewModel viewModelMain = new ViewModelProvider(this).get(MainActivityViewModel.class);

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

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacks(languagesRunnable);
                String email = "mateusz.szelecki2@gmail.com";
                String password = "Jamnik100";
                auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        String authentication = authResult.getUser().getUid();
                        Log.d("PLACEHOLDER", authentication);
                    }
                });
                user.setFirstname("Mateusz");
                user.setSurname("Szelecki");
                EnumLanguages enumLanguages = TimeAndLanguage.setupChangedLanguage(preferences.getString("userLanguage", "default"));
                user.setLanguage(EnumLanguages.DEFAULT);
                user.setAuthentication("12345");
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        goToSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacks(languagesRunnable);
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
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