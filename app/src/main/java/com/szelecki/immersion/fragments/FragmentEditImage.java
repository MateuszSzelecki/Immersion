package com.szelecki.immersion.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.szelecki.immersion.R;
import com.szelecki.immersion.activities.EditProfileActivity;
import com.szelecki.immersion.activities.MainActivity;
import com.szelecki.immersion.viewModels.FriendsFragmentViewModel;

public class FragmentEditImage extends Fragment {

    Button buttonNext;

    boolean signUp;

    ChangeTabLayoutInteface listener;

    public FragmentEditImage(boolean signUp, ChangeTabLayoutInteface listener) {
        this.signUp = signUp;
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editprofile_image, container, false);

        buttonNext = view.findViewById(R.id.buttonNextEditImage);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((EditProfileActivity)getActivity()).replaceFragment(new FragmentEditInfo(signUp));
                listener.setTabAt(1);
            }
        });

        return view;
    }

    public interface ChangeTabLayoutInteface {
        public void setTabAt(int position);
    }
}
