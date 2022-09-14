package com.szelecki.immersion.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.szelecki.immersion.R;
import com.szelecki.immersion.adapters.ExternalPostsAdapter;
import com.szelecki.immersion.models.ModelPostFirebase;
import com.szelecki.immersion.viewModels.HomeFragmentViewModel;
import com.szelecki.immersion.viewModels.MainActivityViewModel;

import java.util.ArrayList;

public class FragmentHome extends Fragment {

    RecyclerView mainRecyclerView;

    ArrayList<ArrayList<ModelPostFirebase>> posts;
    ArrayList<String> displayedPostsId;

    HomeFragmentViewModel viewModel;

    ExternalPostsAdapter externalAdapter;

    public FragmentHome() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mainRecyclerView = view.findViewById(R.id.mainRecyclerViewHomeFragment);

        viewModel = new ViewModelProvider(this).get(HomeFragmentViewModel.class);

        viewModel.getNextTenPosts(displayedPostsId).observe(getViewLifecycleOwner(), new Observer<ArrayList<ModelPostFirebase>>() {
            @Override
            public void onChanged(ArrayList<ModelPostFirebase> modelPostFirebases) {
                posts.add(modelPostFirebases);
            }
        });

        return view;
    }

    private void initRecyclerViewPosts() {
        externalAdapter = new ExternalPostsAdapter(posts, getContext());
        mainRecyclerView.setAdapter(externalAdapter);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
