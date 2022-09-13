package com.szelecki.immersion.room;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.szelecki.immersion.R;
import com.szelecki.immersion.Utils;
import com.szelecki.immersion.models.ModelLanguageStatistic;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LanguageStatisticRepository {

    private LanguageStatisticDAO languageStatisticDAO;

    public LanguageStatisticRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        languageStatisticDAO = appDatabase.getLanguageStatisticDAO();
    }

    public ArrayList<ModelLanguageStatistic> getLanguagesStatistic() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<ArrayList<ModelLanguageStatistic>> futureList = executor.submit(new Task());
        ArrayList<ModelLanguageStatistic> list = new ArrayList<>();
        try {
            list = futureList.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }

    class Task implements Callable<ArrayList<ModelLanguageStatistic>> {
        ArrayList<ModelLanguageStatistic> list = new ArrayList<>();
        @Override
        public ArrayList<ModelLanguageStatistic> call() throws Exception {
            for (ModelLanguageStatistic model : languageStatisticDAO.getLanguagesStatistics()) {
                list.add(setupLanguageInformation(model));
            }
            return list;
        }
    }

    public void insertLanguageStatistic(String name, long started, long recently) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                ModelLanguageStatistic model = new ModelLanguageStatistic(name, started, recently, 0);
                languageStatisticDAO.addLanguageStatistic(model);
            }
        });
    }

    public void updateLanguageStatistic(ModelLanguageStatistic updatedModel, long time, int words) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                ModelLanguageStatistic model = new ModelLanguageStatistic(updatedModel.getName(), updatedModel.getStarted(), time, words);
                languageStatisticDAO.updateLanguageStatistic(model);
            }
        });
    }

    public void deleteLanguageStatistic(String name) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                ModelLanguageStatistic model = new ModelLanguageStatistic(name);
                languageStatisticDAO.deleteLanguageStatistic(model);
            }
        });
    }

    private ModelLanguageStatistic setupLanguageInformation(ModelLanguageStatistic model) {
        String name = model.getName();
        long started = model.getStarted();
        long recently = model.getRecently();
        long now = new Date().getTime();
        int words = model.getWords();
        String daysFrom = "Started: " + Utils.getTimeAgo(started, now);
        String daysRecently = "Recently: " + Utils.getTimeAgo(recently, now);
        String dictionaryWords = "In dictionary: " + String.valueOf(words) + " words";
        int image = Utils.setupLanguageImage(name);
        return new ModelLanguageStatistic(name, started, recently, words, image, daysFrom, daysRecently, dictionaryWords);
    }
}
