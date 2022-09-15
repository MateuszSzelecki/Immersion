package com.szelecki.immersion.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.szelecki.immersion.models.ModelPostFromFirebase;
import com.szelecki.immersion.models.ModelPostToFirebase;
import com.szelecki.immersion.models.ModelReceivedPost;
import com.szelecki.immersion.models.ModelUser;
import com.szelecki.immersion.room.PostFromFirebaseRepository;
import com.szelecki.immersion.room.ReceivedPostRepository;

import java.util.ArrayList;

public class HomeFragmentViewModel extends AndroidViewModel {

    ReceivedPostRepository receivedPostRepository;
    PostFromFirebaseRepository postFromFirebaseRepository;

    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);
        receivedPostRepository = new ReceivedPostRepository(application);
        postFromFirebaseRepository = new PostFromFirebaseRepository(application);
    }

    private MutableLiveData<ArrayList<ModelPostFromFirebase>> postsViewModel = new MutableLiveData<>();
    private ArrayList<String> receivedPostId = new ArrayList<>();

    public MutableLiveData<ArrayList<ModelPostFromFirebase>> getNextTenPosts(String language) {
        for (ModelReceivedPost model : receivedPostRepository.getReceivedPosts(language)) {
            receivedPostId.add(model.getPostId());
        }
        postsViewModel.setValue(postFromFirebaseRepository.getPostsFromFirebase(language, receivedPostId));
        return postsViewModel;
    }

    public void addReceivedPost(ArrayList<ModelPostFromFirebase> posts, String language) {
        receivedPostRepository.insertReceivedPost(posts, language);
    }
}
