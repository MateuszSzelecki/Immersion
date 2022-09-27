package com.szelecki.immersion.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.szelecki.immersion.R;
import com.szelecki.immersion.models.ModelLanguageStatistic;
import com.szelecki.immersion.models.ModelUser;
import com.szelecki.immersion.viewModels.MainActivityViewModel;
import com.szelecki.immersion.viewModels.RegistrationViewModel;

import java.util.ArrayList;

public class RegistrationActivity extends AppCompatActivity {

    Button signUpButton;
    TextView textViewLanguages, goToLoginButton;
    EditText enterName, enterSurname, enterEmail, enterPassword;

    ArrayList<String> languages;
    int langIndex;

    FirebaseAuth auth;

    RegistrationViewModel viewModel;
    ModelUser user;

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

        viewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);
        user = ModelUser.getInstance();
        auth = FirebaseAuth.getInstance();

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

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacks(languagesRunnable);

                String email = enterEmail.getText().toString();
                String password = enterPassword.getText().toString();
                auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        user.setAuthentication(authResult.getUser().getUid());
                        user.setFirstname(enterName.getText().toString());
                        user.setSurname(enterSurname.getText().toString());
                        user.setEmail(email);

                        viewModel.deleteAllData();

                        Intent intent = new Intent(RegistrationActivity.this, AddLanguageActivity.class);
                        ArrayList<String> selected = new ArrayList<>();
                        intent.putExtra("selected", selected);
                        intent.putExtra("signUp", true);
                        intent.putExtra("motherLanguage", true);
                        intent.putExtra("addLanguage", false);
                        startActivity(intent);
                    }
                });
            }
        });

        goToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacks(languagesRunnable);
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