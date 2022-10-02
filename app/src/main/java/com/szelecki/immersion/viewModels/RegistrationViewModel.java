package com.szelecki.immersion.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.szelecki.immersion.models.ModelCategoryStatistic;
import com.szelecki.immersion.models.ModelFriendStatistic;
import com.szelecki.immersion.models.ModelLanguageStatistic;
import com.szelecki.immersion.models.ModelWord;
import com.szelecki.immersion.room.CategoryStatisticRepository;
import com.szelecki.immersion.room.FriendStatisticRepository;
import com.szelecki.immersion.room.LanguageStatisticRepository;
import com.szelecki.immersion.room.WordRepository;
import com.szelecki.immersion.utils.TimeAndLanguage;

import java.util.ArrayList;

public class RegistrationViewModel extends AndroidViewModel {

    LanguageStatisticRepository languageStatisticRepository;
    FriendStatisticRepository friendStatisticRepository;
    WordRepository wordRepository;
    CategoryStatisticRepository categoryStatisticRepository;

    public RegistrationViewModel(@NonNull Application application) {
        super(application);
        languageStatisticRepository = new LanguageStatisticRepository(application);
        friendStatisticRepository = new FriendStatisticRepository(application);
        wordRepository = new WordRepository(application);
        categoryStatisticRepository = new CategoryStatisticRepository(application);
    }

    public void deleteAllData() {
        for (String language : TimeAndLanguage.getArrayOfAllLanguages()) {
            deleteAllFriendsStatistics(language);
            deleteAllWords(language);
            deleteAllLanguagesStatistics(language);
        }
        for (ModelCategoryStatistic model : categoryStatisticRepository.getAllCategories()) {
            categoryStatisticRepository.deleteCategoryStatistic(model);
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
