package com.szelecki.immersion.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.szelecki.immersion.models.ModelPostFirebase;
import com.szelecki.immersion.models.ModelReceivedPost;
import com.szelecki.immersion.room.ReceivedPostRepository;

import java.util.ArrayList;

public class HomeFragmentViewModel extends AndroidViewModel {

    ReceivedPostRepository receivedPostRepository;
    FirebaseDatabase database;
    DatabaseReference reference;

    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);

        receivedPostRepository = new ReceivedPostRepository(application);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("posts");
    }

    private MutableLiveData<ArrayList<String>> receivedPostsIdViewModel = new MutableLiveData<>();
    private MutableLiveData<ArrayList<ModelPostFirebase>> postsViewModel = new MutableLiveData<>();
    private ArrayList<String> temporary = new ArrayList<>();

    public MutableLiveData<ArrayList<String>> getInitialReceivedPostsId(String language) {
        for (ModelReceivedPost model : receivedPostRepository.getReceivedPosts(language)) {
            temporary.add(model.getPost_id());
        }
        receivedPostsIdViewModel.setValue(temporary);
        return receivedPostsIdViewModel;
    }

    public MutableLiveData<ArrayList<ModelPostFirebase>> getNextTenPosts(ArrayList<String> displayedPostId) {
        return postsViewModel;
    }
}
