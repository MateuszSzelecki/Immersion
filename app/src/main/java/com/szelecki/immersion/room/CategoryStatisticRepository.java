package com.szelecki.immersion.room;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.szelecki.immersion.R;
import com.szelecki.immersion.models.ModelCategoryStatistic;
import com.szelecki.immersion.models.ModelFriendStatistic;
import com.szelecki.immersion.models.ModelLanguageStatistic;
import com.szelecki.immersion.utils.CategoriesUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CategoryStatisticRepository {

    Context context;

    private CategoryStatisticDAO categoryStatisticDAO;

    public CategoryStatisticRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        categoryStatisticDAO = appDatabase.getCategoryStatisticDAO();
        context = application.getApplicationContext();
    }

    public ArrayList<ModelCategoryStatistic> getCategoriesForMembership(String membership) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<ArrayList<ModelCategoryStatistic>> futureList = executor.submit(new TaskCategories(membership));
        ArrayList<ModelCategoryStatistic> list = new ArrayList<>();
        try {
            list = futureList.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }

    class TaskCategories implements Callable<ArrayList<ModelCategoryStatistic>> {
        private String membership;
        public TaskCategories(String membership) { this.membership = membership; }

        @Override
        public ArrayList<ModelCategoryStatistic> call() throws Exception {
            ArrayList<ModelCategoryStatistic> list = new ArrayList<>();
            list.addAll(categoryStatisticDAO.getCategoriesForMembership(membership));
            return list;
        }
    }

    public ArrayList<ModelCategoryStatistic> getAllCategories() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<ArrayList<ModelCategoryStatistic>> futureList = executor.submit(new TaskAllCategories());
        ArrayList<ModelCategoryStatistic> list = new ArrayList<>();
        try {
            list = futureList.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }

    class TaskAllCategories implements Callable<ArrayList<ModelCategoryStatistic>> {
        public TaskAllCategories() {}

        @Override
        public ArrayList<ModelCategoryStatistic> call() throws Exception {
            ArrayList<ModelCategoryStatistic> list = new ArrayList<>();
            list.addAll(categoryStatisticDAO.getAllCategories());
            return list;
        }
    }

    public ArrayList<ModelCategoryStatistic> getUsersCategories() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<ArrayList<ModelCategoryStatistic>> futureList = executor.submit(new TaskUsersCategories());
        ArrayList<ModelCategoryStatistic> list = new ArrayList<>();
        try {
            list = futureList.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }

    class TaskUsersCategories implements Callable<ArrayList<ModelCategoryStatistic>> {
        public TaskUsersCategories() {}

        @Override
        public ArrayList<ModelCategoryStatistic> call() throws Exception {
            ArrayList<ModelCategoryStatistic> list = new ArrayList<>();
            list.addAll(categoryStatisticDAO.getUsersCategories(true));
            return list;
        }
    }

    public void insertAllCategoryStatistic() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                ArrayList<ModelCategoryStatistic> data = CategoriesUtils.addAllCategories(context);
                for (ModelCategoryStatistic modelCategoryStatistic : data) {
                    ModelCategoryStatistic model = new ModelCategoryStatistic(modelCategoryStatistic.getCategory(), modelCategoryStatistic.getMembership(), 0, false);
                    categoryStatisticDAO.addCategoryStatistic(model);
                }
            }
        });
    }

    public void updateCategoryStatistic(String category, String membership, int points, boolean users) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                ModelCategoryStatistic model = new ModelCategoryStatistic(category, membership, points, users);
                categoryStatisticDAO.updateCategoryStatistic(model);
            }
        });
    }

    public void deleteCategoryStatistic(ModelCategoryStatistic modelCategoryStatistic) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                categoryStatisticDAO.deleteCategory(modelCategoryStatistic);
            }
        });
    }
}
