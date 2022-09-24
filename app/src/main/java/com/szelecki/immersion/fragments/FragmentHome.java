package com.szelecki.immersion.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.szelecki.immersion.R;
import com.szelecki.immersion.activities.MainActivity;
import com.szelecki.immersion.adapters.AddPostCategoriesAdapter;
import com.szelecki.immersion.adapters.PostsAdapter;
import com.szelecki.immersion.models.ModelPostFromFirebase;
import com.szelecki.immersion.models.ModelUser;
import com.szelecki.immersion.models.ModelWord;
import com.szelecki.immersion.presenters.HomeFragmentPresenter;
import com.szelecki.immersion.presenters.HomeFragmentPresenterInterface;
import com.szelecki.immersion.viewModels.HomeFragmentViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class FragmentHome extends Fragment implements HomeFragmentPresenterInterface {

    RecyclerView mainRecyclerView, recyclerViewCategories;
    EditText editTextWriteSth;
    RelativeLayout slideAddPost;
    TextView selectImageButton, publishButton;

    ArrayList<ModelPostFromFirebase> posts;
    ArrayList<String> chosenCategories;
    Uri imageUri;

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
        recyclerViewCategories = view.findViewById(R.id.recyclerViewCategoriesHomeAddPost);
        editTextWriteSth = view.findViewById(R.id.editTextHomeAddPost);
        slideAddPost = view.findViewById(R.id.slideHomeAddPost);
        selectImageButton = view.findViewById(R.id.selectImageHomeAddPost);
        publishButton = view.findViewById(R.id.publishHomeAddPost);

        viewModel = new ViewModelProvider(this).get(HomeFragmentViewModel.class);
        user = ModelUser.getInstance();
        presenter = new HomeFragmentPresenter(this, getContext());

        posts = new ArrayList<>();
        presenter.getNextTenPosts();

        chosenCategories = new ArrayList<>();

        editTextWriteSth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    slideAddPost.setVisibility(View.VISIBLE);
                    initAddCategoriesRecyclerView();
                } else {
                    slideAddPost.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        publishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contentText = editTextWriteSth.getText().toString();
                String category1 = "";
                String category2 = "";
                String category3 = "";
                presenter.publishPost(contentText, imageUri, category1, category2, category3);
                ((MainActivity)getActivity()).replaceFragment(new FragmentHome());
            }
        });

        ActivityResultLauncher<String> getImage = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                if (result != null) {
                    imageUri = result;
                }
            }
        });

        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImage.launch("image/*");
            }
        });

        return view;
    }

    public void loadMore() {
        ((MainActivity)getActivity()).replaceFragment(new FragmentHome());
    }

    @Override
    public void setupPostsInFirebase(ModelPostFromFirebase postFromFirebase, int amount) {
        posts.add(postFromFirebase);
        if (posts.size() == amount) {
            posts = matchWordsToContent(posts);
            postsAdapter = new PostsAdapter(posts, getContext());
            mainRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
            mainRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mainRecyclerView.setAdapter(postsAdapter);
        }
    }

    private void initAddCategoriesRecyclerView() {
        ArrayList<String> categories = new ArrayList<>();
        categories.add("football"); categories.add("opinion"); categories.add("DIY"); categories.add("norwegian");
        categories.add("hockey"); categories.add("fact"); //TODO: categories to select
        AddPostCategoriesAdapter adapter = new AddPostCategoriesAdapter(categories, getContext());
        adapter.setOnSelectedCategoryInterface(new AddPostCategoriesAdapter.OnSelectedCategoryAddPostInterface() {
            @Override
            public void onClickCategory(ArrayList<String> chosen) {
                chosenCategories = chosen;
                for (String string : chosenCategories) {
                    Log.d("PLACEHOLDER", string);
                }
            }
        });
        recyclerViewCategories.setOverScrollMode(View.OVER_SCROLL_NEVER);
        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCategories.setAdapter(adapter);
    }

    private ArrayList<ModelPostFromFirebase> matchWordsToContent(ArrayList<ModelPostFromFirebase> modelPosts) {
        ArrayList<String> contents = new ArrayList<>();
        for (ModelPostFromFirebase model : modelPosts) {
            contents.add(model.getContentText().toUpperCase());
        }
        viewModel.getInitialWordsForPosts(user.getLanguage().getDescription(), contents).observe(this, new Observer<ArrayList<ArrayList<ModelWord>>>() {
            @Override
            public void onChanged(ArrayList<ArrayList<ModelWord>> arrayLists) {
                for (int i=0; i<arrayLists.size(); i++) {
                    modelPosts.get(i).setWords(arrayLists.get(i));
                }
            }
        });
        return modelPosts;
    }
}
