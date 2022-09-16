package com.szelecki.immersion.fragments;

import android.content.Intent;
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
import com.szelecki.immersion.activities.MainActivity;
import com.szelecki.immersion.adapters.PostsAdapter;
import com.szelecki.immersion.adapters.PostsAdapterInterface;
import com.szelecki.immersion.models.ModelPostFromFirebase;
import com.szelecki.immersion.models.ModelUser;
import com.szelecki.immersion.presenters.HomeFragmentPresenter;
import com.szelecki.immersion.presenters.HomeFragmentPresenterInterface;
import com.szelecki.immersion.viewModels.HomeFragmentViewModel;

import java.util.ArrayList;

public class FragmentHome extends Fragment implements PostsAdapterInterface, HomeFragmentPresenterInterface {

    RecyclerView mainRecyclerView;

    ArrayList<ModelPostFromFirebase> posts;

    HomeFragmentViewModel viewModel;
    HomeFragmentPresenter presenter;
    ModelUser user;

    PostsAdapter postsAdapter;

    public FragmentHome() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mainRecyclerView = view.findViewById(R.id.mainRecyclerViewHomeFragment);

        viewModel = new ViewModelProvider(this).get(HomeFragmentViewModel.class);
        user = ModelUser.getInstance();
        presenter = new HomeFragmentPresenter(this, user.getLanguage().getDescription(), user.getAuthentication());

        posts = new ArrayList<>();

        presenter.getNextTenPosts();

        return view;
    }

    @Override
    public void loadMore() {
        ((MainActivity)getActivity()).replaceFragment(new FragmentHome());
    }

    @Override
    public void setupPostsInAdapter(ArrayList<ModelPostFromFirebase> postsFromFirebase) {
        posts = postsFromFirebase;
        postsAdapter = new PostsAdapter(posts, getContext());
        postsAdapter.setPostsAdapterInterface(FragmentHome.this);
        mainRecyclerView.setAdapter(postsAdapter);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
