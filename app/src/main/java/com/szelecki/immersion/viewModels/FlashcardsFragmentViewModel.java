package com.szelecki.immersion.viewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.szelecki.immersion.utils.TimeAndLanguage;
import com.szelecki.immersion.models.ModelWord;
import com.szelecki.immersion.room.WordRepository;

import java.util.ArrayList;

public class FlashcardsFragmentViewModel extends AndroidViewModel {

    WordRepository wordRepository;

    public FlashcardsFragmentViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);
    }

    private MutableLiveData<ArrayList<ModelWord>> wordsLiveData = new MutableLiveData<>();
    private ArrayList<ModelWord> temporary = new ArrayList<>();

    public MutableLiveData<ArrayList<ModelWord>> getInitialWordsAtTheLevels(String language, long time) {
        temporary = wordRepository.getWordsAtTheLevel(language, time - TimeAndLanguage.DAY_MILLIS,1, 15);
        temporary.addAll(wordRepository.getWordsAtTheLevel(language, time - 3* TimeAndLanguage.DAY_MILLIS, 2, 15));
        temporary.addAll(wordRepository.getWordsAtTheLevel(language, time - TimeAndLanguage.WEEK_MILLIS, 3, 15));
        temporary.addAll(wordRepository.getWordsAtTheLevel(language, time - 10* TimeAndLanguage.DAY_MILLIS, 4, 15));
        wordsLiveData.setValue(temporary);
        return wordsLiveData;
    }

    public MutableLiveData<ArrayList<ModelWord>> getInitialWordsForLanguage(String language) {
        wordsLiveData.setValue(wordRepository.getAllWordsForLanguage(language));
        return wordsLiveData;
    }

    public void updateWord(ModelWord updatedModel, long recently, int level) {
        wordRepository.updateWord(updatedModel, recently, level);
    }

    public void deleteWord(String name, String language) {
        wordRepository.deleteWord(name, language);
    }
}
