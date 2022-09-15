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
import com.szelecki.immersion.viewModels.HomeFragmentViewModel;

import java.util.ArrayList;

public class FragmentHome extends Fragment implements PostsAdapterInterface {

    RecyclerView mainRecyclerView;

    ArrayList<ModelPostFromFirebase> posts;
    ArrayList<String> displayedPostsId;

    HomeFragmentViewModel viewModel;
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

        posts = new ArrayList<>(); displayedPostsId = new ArrayList<>();
        initRecyclerViewPosts();

        viewModel.getNextTenPosts(user.getLanguage().getDescription()).observe(getViewLifecycleOwner(), new Observer<ArrayList<ModelPostFromFirebase>>() {
            @Override
            public void onChanged(ArrayList<ModelPostFromFirebase> modelPostFromFirebases) {
                postsAdapter.setPosts(modelPostFromFirebases);
                viewModel.addReceivedPost(modelPostFromFirebases, user.getLanguage().getDescription());
            }
        });

        return view;
    }

    private void initRecyclerViewPosts() {
        postsAdapter = new PostsAdapter(posts, getContext());
        postsAdapter.setPostsAdapterInterface(this);
        mainRecyclerView.setAdapter(postsAdapter);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void loadMore() {
        ((MainActivity)getActivity()).replaceFragment(new FragmentHome());
    }
}
