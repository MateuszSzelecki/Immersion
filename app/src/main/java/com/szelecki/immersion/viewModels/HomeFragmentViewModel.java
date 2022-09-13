package com.szelecki.immersion.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.szelecki.immersion.models.ModelReceivedPost;
import com.szelecki.immersion.room.ReceivedPostRepository;

import java.util.ArrayList;

public class HomeFragmentViewModel extends AndroidViewModel {

    ReceivedPostRepository receivedPostRepository;

    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);
        receivedPostRepository = new ReceivedPostRepository(application);
    }

    private MutableLiveData<ArrayList<String>> receivedPostsIdViewModel = new MutableLiveData<>();
    private ArrayList<String> temporary = new ArrayList<>();

    public MutableLiveData<ArrayList<String>> getInitialReceivedPostsId(String language) {
        for (ModelReceivedPost model : receivedPostRepository.getReceivedPosts(language)) {
            temporary.add(model.getPost_id());
        }
        receivedPostsIdViewModel.setValue(temporary);
        return receivedPostsIdViewModel;
    }
}
