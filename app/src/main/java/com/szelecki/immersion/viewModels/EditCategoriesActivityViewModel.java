package com.szelecki.immersion.viewModels;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.szelecki.immersion.R;
import com.szelecki.immersion.models.ModelCategoryStatistic;
import com.szelecki.immersion.models.ModelWord;
import com.szelecki.immersion.room.CategoryStatisticRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EditCategoriesActivityViewModel extends AndroidViewModel {

    CategoryStatisticRepository categoryStatisticRepository;
    Context context;

    public EditCategoriesActivityViewModel(@NonNull Application application) {
        super(application);
        categoryStatisticRepository = new CategoryStatisticRepository(application);
        context = application.getApplicationContext();
    }

    private MutableLiveData<ArrayList<ModelCategoryStatistic>> allCategoriesLiveData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<ModelCategoryStatistic>> memberCategoriesLiveData = new MutableLiveData<>();

    public MutableLiveData<ArrayList<ModelCategoryStatistic>> getInitialAllCategories() {
        ArrayList<ModelCategoryStatistic> models = categoryStatisticRepository.getAllCategories();
        allCategoriesLiveData.setValue(models);
        return allCategoriesLiveData;
    }

    public void getInitialCategoriesMembership(String membership) {

    }

    public void updateCategoryStatistic(ModelCategoryStatistic updatedModel, int points) {

    }

    public void addAllCategories() {
        categoryStatisticRepository.insertAllCategoryStatistic();
    }
}
