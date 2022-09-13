package com.szelecki.immersion.viewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.szelecki.immersion.models.ModelFriendStatistic;
import com.szelecki.immersion.models.ModelLanguageStatistic;
import com.szelecki.immersion.models.ModelWord;
import com.szelecki.immersion.room.FriendStatisticRepository;
import com.szelecki.immersion.room.LanguageStatisticRepository;
import com.szelecki.immersion.room.WordRepository;

import java.util.ArrayList;

public class MainActivityViewModel extends AndroidViewModel {

    LanguageStatisticRepository languageStatisticRepository;
    FriendStatisticRepository friendStatisticRepository;
    WordRepository wordRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        languageStatisticRepository = new LanguageStatisticRepository(application);
        friendStatisticRepository = new FriendStatisticRepository(application);
        wordRepository = new WordRepository(application);
    }

    private MutableLiveData<ArrayList<ModelLanguageStatistic>> languagesStatisticsLiveData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<ModelFriendStatistic>> allFriendsStatisticsLiveData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<ModelFriendStatistic>> friendsStatisticsForLanguageLiveData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<ModelWord>> wordsLiveData = new MutableLiveData<>();

    public MutableLiveData<ArrayList<ModelLanguageStatistic>> getInitialLanguagesStatistics() {
        languagesStatisticsLiveData.setValue(languageStatisticRepository.getLanguagesStatistic());
        return languagesStatisticsLiveData;
    }

    public void updateLanguageStatistic(ModelLanguageStatistic updatedModel, long time, int words) {
        languageStatisticRepository.updateLanguageStatistic(updatedModel, time, words);
    }

    public void deleteLanguageStatistic(String name) {
        languageStatisticRepository.deleteLanguageStatistic(name);
    }

    public MutableLiveData<ArrayList<ModelFriendStatistic>> getInitialTheBestFriendsStatistics(int theWorstMessages) {
        allFriendsStatisticsLiveData.setValue(friendStatisticRepository.getTheBestFriendsStatistic(theWorstMessages));
        return allFriendsStatisticsLiveData;
    }

    public MutableLiveData<ArrayList<ModelFriendStatistic>> getInitialFriendsStatisticsForLanguage(String language) {
        friendsStatisticsForLanguageLiveData.setValue(friendStatisticRepository.getFriendsStatisticForLanguage(language));
        return friendsStatisticsForLanguageLiveData;
    }

    public void updateFriendStatistic(ModelFriendStatistic updatedModel, int messages) {
        friendStatisticRepository.updateFriendsStatistic(updatedModel, messages);
    }

    public void deleteFriendStatistic(String id) {
        friendStatisticRepository.deleteFriendStatistic(id);
    }

    public MutableLiveData<ArrayList<ModelWord>> getInitialWordsForLanguage(String language) {
        wordsLiveData.setValue(wordRepository.getAllWordsForLanguage(language));
        return wordsLiveData;
    }

    public void addWord(String word, String meaning, String language, long recently) {
        wordRepository.insertWord(word, meaning, language, recently, 1);
    }
}
