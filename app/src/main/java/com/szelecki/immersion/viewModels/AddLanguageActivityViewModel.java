package com.szelecki.immersion.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.szelecki.immersion.models.LanguagesEnum;
import com.szelecki.immersion.models.ModelLanguageStatistic;
import com.szelecki.immersion.room.LanguageStatisticRepository;

import java.util.ArrayList;

public class AddLanguageActivityViewModel extends AndroidViewModel {

    LanguageStatisticRepository languageStatisticRepository;

    public AddLanguageActivityViewModel(@NonNull Application application) {
        super(application);
        languageStatisticRepository = new LanguageStatisticRepository(application);
    }

    public void addLanguageStatistic(String name, long startedAndRecently) {
        languageStatisticRepository.insertLanguageStatistic(name, startedAndRecently, startedAndRecently);
    }

    public void deleteLanguageStatistic(String name) {
        languageStatisticRepository.deleteLanguageStatistic(name);
    }
}
