package com.szelecki.immersion.presenters;

import android.content.Context;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.szelecki.immersion.models.ModelUser;

import java.util.HashMap;
import java.util.Map;

public class EditProfileActivityPresenter {

    Context content;

    FirebaseFirestore database;
    DocumentReference reference;

    ModelUser user;

    public EditProfileActivityPresenter(Context context) {
        this.content = context;
        user = ModelUser.getInstance();

        database = FirebaseFirestore.getInstance();
        reference = database.collection("Users").document(user.getAuthentication());
    }

    public void insertNewUserToDatabase(OnSuccessfulAddedUser listener) {
        Map<String, Object> data = new HashMap<>();
        data.put("firstname", user.getFirstname());
        data.put("surname", user.getSurname());
        data.put("email", user.getEmail());
        data.put("imageURL", user.getImageURL());
        data.put("imageRotation", user.getRotation());
        data.put("motherLanguage", user.getMotherLanguage().getDescription());
        data.put("hobby1", "."); //TODO: . -> hobbies.get(0)
        String languageString = "";
        for (String language : user.getLanguages()) {
            languageString = languageString + language + ",";
        }
        data.put("languages", languageString);
        reference.set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                listener.userAdded();
            }
        });
    }

    public interface OnSuccessfulAddedUser {
        public void userAdded();
    }
}
