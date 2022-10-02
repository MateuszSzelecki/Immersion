package com.szelecki.immersion.utils;

import android.content.Context;
import android.util.Log;

import com.szelecki.immersion.R;
import com.szelecki.immersion.models.ModelCategoryStatistic;

import java.util.ArrayList;
import java.util.Arrays;

public class CategoriesUtils {

    public static ArrayList<ModelCategoryStatistic> addAllCategories(Context context) {
        ArrayList<ModelCategoryStatistic> models = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();

        models.add(new ModelCategoryStatistic("tech", "tech"));
        temp.clear(); temp.addAll(Arrays.asList(context.getResources().getStringArray(R.array.tech)));
        for (String string : temp) {
            ModelCategoryStatistic model = new ModelCategoryStatistic(string, "tech");
            models.add(model);
        }

        models.add(new ModelCategoryStatistic("gaming", "gaming"));
        temp.clear(); temp.addAll(Arrays.asList(context.getResources().getStringArray(R.array.gaming)));
        for (String string : temp) {
            ModelCategoryStatistic model = new ModelCategoryStatistic(string, "gaming");
            models.add(model);
        }

        models.add(new ModelCategoryStatistic("health", "health"));
        temp.clear(); temp.addAll(Arrays.asList(context.getResources().getStringArray(R.array.health)));
        for (String string : temp) {
            ModelCategoryStatistic model = new ModelCategoryStatistic(string, "health");
            models.add(model);
        }

        models.add(new ModelCategoryStatistic("travel", "travel"));
        temp.clear(); temp.addAll(Arrays.asList(context.getResources().getStringArray(R.array.travel)));
        for (String string : temp) {
            ModelCategoryStatistic model = new ModelCategoryStatistic(string, "travel");
            models.add(model);
        }

        models.add(new ModelCategoryStatistic("football", "football"));
        temp.clear(); temp.addAll(Arrays.asList(context.getResources().getStringArray(R.array.football)));
        for (String string : temp) {
            ModelCategoryStatistic model = new ModelCategoryStatistic(string, "football");
            models.add(model);
        }

        models.add(new ModelCategoryStatistic("sports", "sports"));
        temp.clear(); temp.addAll(Arrays.asList(context.getResources().getStringArray(R.array.sports)));
        for (String string : temp) {
            ModelCategoryStatistic model = new ModelCategoryStatistic(string, "sports");
            models.add(model);
        }

        models.add(new ModelCategoryStatistic("fashion", "fashion"));
        models.add(new ModelCategoryStatistic("design", "design"));
        models.add(new ModelCategoryStatistic("photo", "photo"));
        models.add(new ModelCategoryStatistic("home", "home"));

        models.add(new ModelCategoryStatistic("film", "film"));
        temp.clear(); temp.addAll(Arrays.asList(context.getResources().getStringArray(R.array.film)));
        for (String string : temp) {
            ModelCategoryStatistic model = new ModelCategoryStatistic(string, "film");
            models.add(model);
        }

        models.add(new ModelCategoryStatistic("podcasts", "podcasts"));

        models.add(new ModelCategoryStatistic("TVseries", "TVseries"));
        temp.clear(); temp.addAll(Arrays.asList(context.getResources().getStringArray(R.array.TVseries)));
        for (String string : temp) {
            ModelCategoryStatistic model = new ModelCategoryStatistic(string, "TVseries");
            models.add(model);
        }

        models.add(new ModelCategoryStatistic("book", "book"));
        temp.clear(); temp.addAll(Arrays.asList(context.getResources().getStringArray(R.array.book)));
        for (String string : temp) {
            ModelCategoryStatistic model = new ModelCategoryStatistic(string, "book");
            models.add(model);
        }

        models.add(new ModelCategoryStatistic("music", "music"));
        temp.clear(); temp.addAll(Arrays.asList(context.getResources().getStringArray(R.array.music)));
        for (String string : temp) {
            ModelCategoryStatistic model = new ModelCategoryStatistic(string, "music");
            models.add(model);
        }

        models.add(new ModelCategoryStatistic("business", "business"));
        temp.clear(); temp.addAll(Arrays.asList(context.getResources().getStringArray(R.array.business)));
        for (String string : temp) {
            ModelCategoryStatistic model = new ModelCategoryStatistic(string, "business");
            models.add(model);
        }

        models.add(new ModelCategoryStatistic("law", "law"));
        temp.clear(); temp.addAll(Arrays.asList(context.getResources().getStringArray(R.array.law)));
        for (String string : temp) {
            ModelCategoryStatistic model = new ModelCategoryStatistic(string, "law");
            models.add(model);
        }

        models.add(new ModelCategoryStatistic("politics", "politics"));
        models.add(new ModelCategoryStatistic("humor", "humor"));
        models.add(new ModelCategoryStatistic("science", "science"));
        models.add(new ModelCategoryStatistic("shopping", "shopping"));

        return models;
    }

    public static ArrayList<ArrayList<String>> getGroupedCategories(Context context) {
        ArrayList<ArrayList<String>> categories = new ArrayList<>(); int index = -1;
        for (ModelCategoryStatistic model : CategoriesUtils.addAllCategories(context)) {
            if (model.getCategory().equals(model.getMembership())) {
                categories.add(new ArrayList<>());
                index++;
            }
            categories.get(index).add(model.getCategory());
        }
        return categories;
    }
}
