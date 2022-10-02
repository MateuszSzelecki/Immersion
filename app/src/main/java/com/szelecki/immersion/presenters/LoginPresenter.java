package com.szelecki.immersion.presenters;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.szelecki.immersion.models.EnumLanguages;
import com.szelecki.immersion.models.ModelUser;
import com.szelecki.immersion.utils.TimeAndLanguage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LoginPresenter {

    Context content;

    FirebaseFirestore database;
    DocumentReference reference;

    public LoginPresenter(Context content) {
        this.content = content;

        database = FirebaseFirestore.getInstance();
    }

    public void setupInfoFromFirestore(String authentication, OnSuccessfulLoggedUser listener) {
        reference = database.collection("Users").document(authentication);
        reference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                String firstname = value.getString("firstname");
                String surname = value.getString("surname");
                String email = value.getString("email");
                String imageURL = value.getString("imageURL");
                String imageRotation = value.getString("imageRotation");
                String motherLanguage = value.getString("motherLanguage");
                String hobbiesString = value.getString("hobbies");
                ArrayList<String> hobbies = new ArrayList<>();
                hobbies.addAll(Arrays.asList(hobbiesString.split(",")));
                String languagesString = value.getString("languages");
                ArrayList<String> languages = new ArrayList<>();
                languages.addAll(Arrays.asList(languagesString.split(",")));

                listener.userLogged(authentication, firstname, surname, email, imageURL, imageRotation, TimeAndLanguage.setupChangedLanguage(motherLanguage), hobbies, languages);
            }
        });
    }

    public interface OnSuccessfulLoggedUser {
        public void userLogged(String authentication, String firstname, String surname, String email,
                               String imageURL, String imageRotation, EnumLanguages motherLanguage,
                               ArrayList<String> hobbies, ArrayList<String> languages);
    }
}
