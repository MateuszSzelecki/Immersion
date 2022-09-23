package com.szelecki.immersion.room;

import android.app.Application;
import android.util.Log;

import com.szelecki.immersion.models.ModelFriendStatistic;
import com.szelecki.immersion.models.ModelLanguageStatistic;
import com.szelecki.immersion.models.ModelWord;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WordRepository {

    private WordDAO wordDAO;

    public WordRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        wordDAO = appDatabase.getWordDAO();
    }

    public ArrayList<ModelWord> getWordsAtTheLevel(String language, long extremeTime, int level, int limit) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<ArrayList<ModelWord>> futureList = executor.submit(new TaskWordsAtTheLevel(language, extremeTime, level, limit));
        ArrayList<ModelWord> list = new ArrayList<>();
        try {
            list = futureList.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }

    class TaskWordsAtTheLevel implements Callable<ArrayList<ModelWord>> {
        private String language;
        private long time;
        private int level;
        private int limit;
        public TaskWordsAtTheLevel(String language, long time, int level, int limit) {
            this.language = language;
            this.time = time;
            this.level = level;
            this.limit = limit;
        }

        @Override
        public ArrayList<ModelWord> call() throws Exception {
            ArrayList<ModelWord> list = new ArrayList<>();
            for (ModelWord model : wordDAO.getWordsAtTheLevel(language, time, level, limit)) {
                list.add(model);
            }
            return list;
        }
    }

    public ArrayList<ModelWord> getAllWordsForLanguage(String language) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<ArrayList<ModelWord>> futureList = executor.submit(new TaskWordsForLanguage(language));
        ArrayList<ModelWord> list = new ArrayList<>();
        try {
            list = futureList.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }

    class TaskWordsForLanguage implements Callable<ArrayList<ModelWord>> {
        private String language;
        public TaskWordsForLanguage(String language) { this.language = language; }

        @Override
        public ArrayList<ModelWord> call() throws Exception {
            ArrayList<ModelWord> list = new ArrayList<>();
            for (ModelWord model : wordDAO.getAllWordsForLanguage(language)) {
                list.add(model);
            }
            return list;
        }
    }

    public void insertWord(String word, String meaning, String language, long recently, int level) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                ModelWord model = new ModelWord(word, meaning, language, recently, level);
                wordDAO.addWord(model);
            }
        });
    }

    public void updateWord(ModelWord updatedModel, long recently, int level) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                wordDAO.updateWord(new ModelWord(updatedModel.getWord(), updatedModel.getMeaning(), updatedModel.getLanguage(), recently, level));
            }
        });
    }

    public void deleteWord(String word, String language) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                ModelWord model = new ModelWord(word, language);
                wordDAO.deleteWord(model);
            }
        });
    }
}
