package com.szelecki.immersion.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.szelecki.immersion.R;
import com.szelecki.immersion.activities.MainActivity;
import com.szelecki.immersion.models.ModelUser;
import com.szelecki.immersion.presenters.EditProfilePresenter;
import com.szelecki.immersion.utils.TimeAndLanguage;

public class FragmentEditInfo extends Fragment {

    Button buttonNext;

    boolean signUp;

    EditProfilePresenter presenter;
    ModelUser user;
    SharedPreferences preferences;

    public FragmentEditInfo(boolean signUp) {
        this.signUp = signUp;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editprofile_info, container, false);

        buttonNext = view.findViewById(R.id.buttonNextEditInfo);

        user = ModelUser.getInstance();
        preferences = getContext().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        presenter = new EditProfilePresenter(getContext());

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (signUp) {
                    presenter.insertNewUserToDatabase(new EditProfilePresenter.OnSuccessfulAddedUser() {
                        @Override
                        public void userAdded() {
                            user.setLanguage(TimeAndLanguage.setupChangedLanguage(user.getLanguages().get(0)));
                            preferences.edit().putString("userAuth", user.getAuthentication()).apply();
                            startActivity(new Intent(getActivity(), MainActivity.class));
                        }
                    });
                }
            }
        });

        return view;
    }
}
