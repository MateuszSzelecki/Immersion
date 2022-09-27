package com.szelecki.immersion.viewModels;

import android.app.Application;

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

public class RegistrationViewModel extends AndroidViewModel {

    LanguageStatisticRepository languageStatisticRepository;
    FriendStatisticRepository friendStatisticRepository;
    WordRepository wordRepository;

    public RegistrationViewModel(@NonNull Application application) {
        super(application);
        languageStatisticRepository = new LanguageStatisticRepository(application);
        friendStatisticRepository = new FriendStatisticRepository(application);
        wordRepository = new WordRepository(application);
    }

    public void deleteAllData() {
        for (ModelLanguageStatistic model : languageStatisticRepository.getLanguagesStatistic()) {
            deleteAllFriendsStatistics(model.getName());
            deleteAllWords(model.getName());
            deleteAllLanguagesStatistics(model.getName());
        }
    }

    private void deleteAllLanguagesStatistics(String language) {
        languageStatisticRepository.deleteLanguageStatistic(language);
    }

    private void deleteAllFriendsStatistics(String language) {
        for (ModelFriendStatistic model : friendStatisticRepository.getFriendsStatisticForLanguage(language)) {
            friendStatisticRepository.deleteFriendStatistic(model.getId());
        }
    }

    private void deleteAllWords(String language) {
        for (ModelWord model : wordRepository.getAllWordsForLanguage(language)) {
            wordRepository.deleteWord(model.getWord(), language);
        }
    }

}
