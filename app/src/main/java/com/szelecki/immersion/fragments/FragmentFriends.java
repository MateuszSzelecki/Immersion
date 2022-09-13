package com.szelecki.immersion.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.szelecki.immersion.R;
import com.szelecki.immersion.activities.MainActivity;
import com.szelecki.immersion.models.LanguagesEnum;
import com.szelecki.immersion.viewModels.FriendsFragmentViewModel;
import com.szelecki.immersion.viewModels.MainActivityViewModel;

import java.util.Date;

public class FragmentFriends extends Fragment {

    FriendsFragmentViewModel viewModel;

    public FragmentFriends() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friends, container, false);

        viewModel = new ViewModelProvider(this).get(FriendsFragmentViewModel.class);

        return view;
    }
}
