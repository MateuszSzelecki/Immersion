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

public class HomeFragmentViewModel extends AndroidViewModel {

    WordRepository wordRepository;

    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);
    }

    private MutableLiveData<ArrayList<ArrayList<String>>> postsWordsLiveData = new MutableLiveData<>();
    private ArrayList<String> databaseWords = new ArrayList<>();

    private ArrayList<String> getWordsForLanguage(String language) {
        for (ModelWord modelWord : wordRepository.getAllWordsForLanguage(language)) {
            databaseWords.add(modelWord.getId());
        }
        return databaseWords;
    }

    public MutableLiveData<ArrayList<ArrayList<String>>> getInitialWordsForPosts(String language, ArrayList<String> contents) {
        databaseWords = getWordsForLanguage(language);
        ArrayList<ArrayList<String>> wordsForPosts = new ArrayList<>();
        for (int i=0; i<contents.size(); i++) { wordsForPosts.add(new ArrayList<String>()); }
        for (String word : databaseWords) {
            for (int i=0; i<contents.size(); i++) {
                if (contents.get(i).contains(word)) {
                    wordsForPosts.get(i).add(word.toLowerCase());
                }
            }
        }
        postsWordsLiveData.setValue(wordsForPosts);
        return postsWordsLiveData;
    }
}
