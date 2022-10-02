package com.szelecki.immersion.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.szelecki.immersion.R;
import com.szelecki.immersion.models.ModelFriendStatistic;
import com.szelecki.immersion.presenters.LoginPresenter;
import com.szelecki.immersion.utils.TimeAndLanguage;
import com.szelecki.immersion.models.EnumLanguages;
import com.szelecki.immersion.models.ModelUser;
import com.szelecki.immersion.viewModels.AddLanguageActivityViewModel;
import com.szelecki.immersion.viewModels.FriendsFragmentViewModel;
import com.szelecki.immersion.viewModels.MainActivityViewModel;

import java.util.ArrayList;
import java.util.Date;

public class LoginActivity extends AppCompatActivity implements LoginPresenter.OnSuccessfulLoggedUser {

    Button loginButton;
    TextView textViewLanguages, goToSignUpButton;
    EditText enterEmail, enterPassword;
    CardView introCardView;

    ArrayList<String> languages;
    int langIndex;

    FirebaseAuth auth;

    LoginPresenter presenter;
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
        introCardView = findViewById(R.id.introLoginActivity);

        user = ModelUser.getInstance();
        preferences = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        auth = FirebaseAuth.getInstance();
        presenter = new LoginPresenter(LoginActivity.this);

        String authentication = preferences.getString("userAuth", "123");

        if (!authentication.equals("123")) {
            presenter.setupInfoFromFirestore(authentication, LoginActivity.this);
        } else {
            introCardView.setVisibility(View.GONE);
        }

        AddLanguageActivityViewModel viewModelLanguage = new ViewModelProvider(this).get(AddLanguageActivityViewModel.class);

        initLanguagesList();
        Handler handler = new Handler();
        Runnable languagesRunnable = new Runnable() {
            @Override
            public void run() {
                if (langIndex == 11) { langIndex = 0; }
                textViewLanguages.setText(languages.get(langIndex));
                langIndex ++;
                handler.postDelayed(this, 2000);
            }
        };

        languagesRunnable.run();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacks(languagesRunnable);

                String email = enterEmail.getText().toString();
                String password = enterPassword.getText().toString();

                auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        String authentication = authResult.getUser().getUid();
                        preferences.edit().putString("userAuth", authentication).apply();
                        presenter.setupInfoFromFirestore(authentication, LoginActivity.this);
                    }
                });
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

    @Override
    public void userLogged(String authentication, String firstname, String surname, String email, String imageURL, String imageRotation, EnumLanguages motherLanguage, ArrayList<String> hobbies, ArrayList<String> languages) {
        user.setAuthentication(authentication);
        user.setLanguage(TimeAndLanguage.setupChangedLanguage(languages.get(0)));
        user.setFirstname(firstname);
        user.setSurname(surname);
        user.setEmail(email);
        user.setImageURL(imageURL);
        user.setRotation(imageRotation);
        user.setMotherLanguage(motherLanguage);
        user.setHobbies(hobbies);
        user.setLanguages(languages);
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}