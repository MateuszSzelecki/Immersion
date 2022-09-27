package com.szelecki.immersion.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.szelecki.immersion.R;
import com.szelecki.immersion.activities.AddLanguageActivity;
import com.szelecki.immersion.activities.MainActivity;
import com.szelecki.immersion.models.EnumLanguages;
import com.szelecki.immersion.models.ModelUser;
import com.szelecki.immersion.presenters.EditProfileActivityPresenter;
import com.szelecki.immersion.presenters.HomeFragmentPresenter;
import com.szelecki.immersion.utils.TimeAndLanguage;

public class FragmentEditInfo extends Fragment {

    Button buttonNext;

    boolean signUp;

    EditProfileActivityPresenter presenter;
    ModelUser user;

    public FragmentEditInfo(boolean signUp) {
        this.signUp = signUp;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editprofile_info, container, false);

        buttonNext = view.findViewById(R.id.buttonNextEditInfo);

        user = ModelUser.getInstance();
        presenter = new EditProfileActivityPresenter(getContext());

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.insertNewUserToDatabase(new EditProfileActivityPresenter.OnSuccessfulAddedUser() {
                    @Override
                    public void userAdded() {
                        user.setLanguage(TimeAndLanguage.setupChangedLanguage(user.getLanguages().get(0)));
                        startActivity(new Intent(getActivity(), MainActivity.class));
                    }
                });
            }
        });

        return view;
    }
}
