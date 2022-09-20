package com.szelecki.immersion.viewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.szelecki.immersion.models.ModelPostFromFirebase;
import com.szelecki.immersion.models.ModelWord;
import com.szelecki.immersion.room.WordRepository;

import java.util.ArrayList;
import java.util.Collections;

public class HomeFragmentViewModel extends AndroidViewModel {

    WordRepository wordRepository;

    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);
    }

    private MutableLiveData<ArrayList<ArrayList<ModelWord>>> postsWordsLiveData = new MutableLiveData<>();
    private ArrayList<ModelWord> databaseWords = new ArrayList<>();

    private ArrayList<ModelWord> getWordsForLanguage(String language) {
        databaseWords.addAll(wordRepository.getAllWordsForLanguage(language));
        return databaseWords;
    }

    public MutableLiveData<ArrayList<ArrayList<ModelWord>>> getInitialWordsForPosts(String language, ArrayList<String> contents) {
        databaseWords = getWordsForLanguage(language);
        ArrayList<ArrayList<ModelWord>> wordsForPosts = new ArrayList<>();
        for (int i=0; i<contents.size(); i++) { wordsForPosts.add(new ArrayList<ModelWord>()); }
        for (ModelWord model : databaseWords) {
            for (int i=0; i<contents.size(); i++) {
                if (contents.get(i).contains(model.getId())) {
                    wordsForPosts.get(i).add(model);
                }
            }
        }
        postsWordsLiveData.setValue(wordsForPosts);
        return postsWordsLiveData;
    }
}
