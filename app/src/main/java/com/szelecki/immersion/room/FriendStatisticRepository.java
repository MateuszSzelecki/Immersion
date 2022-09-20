package com.szelecki.immersion.room;

import android.app.Application;

import com.szelecki.immersion.utils.TimeAndLanguage;
import com.szelecki.immersion.models.ModelFriendStatistic;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FriendStatisticRepository {

    private FriendStatisticDAO friendStatisticDAO;

    public FriendStatisticRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        friendStatisticDAO = appDatabase.getFriendStatisticDAO();
    }

    public ArrayList<ModelFriendStatistic> getTheBestFriendsStatistic(int theWorstMessages) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<ArrayList<ModelFriendStatistic>> futureList = executor.submit(new TaskTheBestFriends(theWorstMessages));
        ArrayList<ModelFriendStatistic> list = new ArrayList<>();
        try {
            list = futureList.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }

    class TaskTheBestFriends implements Callable<ArrayList<ModelFriendStatistic>> {
        private int theWorstMessages;
        public TaskTheBestFriends(int theWorstMessages) { this.theWorstMessages = theWorstMessages; }

        @Override
        public ArrayList<ModelFriendStatistic> call() throws Exception {
            ArrayList<ModelFriendStatistic> list = new ArrayList<>();
            for (ModelFriendStatistic model : friendStatisticDAO.getTheBestFriendsStatistics(theWorstMessages)) {
                list.add(setupFriendInformation(model));
            }
            return list;
        }
    }

    public ArrayList<ModelFriendStatistic> getFriendsStatisticForLanguage(String language) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<ArrayList<ModelFriendStatistic>> futureList = executor.submit(new TaskLanguageFriends(language));
        ArrayList<ModelFriendStatistic> list = new ArrayList<>();
        try {
            list = futureList.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }

    class TaskLanguageFriends implements Callable<ArrayList<ModelFriendStatistic>> {
        private String language;
        public TaskLanguageFriends(String language) { this.language = language; }

        @Override
        public ArrayList<ModelFriendStatistic> call() throws Exception {
            ArrayList<ModelFriendStatistic> list = new ArrayList<>();
            for (ModelFriendStatistic model : friendStatisticDAO.getFriendsStatisticsForLanguage(language)) {
                list.add(setupFriendInformation(model));
            }
            return list;
        }
    }

    public void insertFriendStatistic(String authentication, String firstname, String surname, String language, long started) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                ModelFriendStatistic model = new ModelFriendStatistic(authentication, firstname, surname, language, started, 0);
                friendStatisticDAO.addFriendStatistic(model);
            }
        });
    }

    public void updateFriendsStatistic(ModelFriendStatistic updatedModel, int messages) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                friendStatisticDAO.updateFriendStatistic(new ModelFriendStatistic(updatedModel.getAuthentication(), updatedModel.getFirstname(), updatedModel.getSurname(), updatedModel.getLanguage(), updatedModel.getStarted(), messages));
            }
        });
    }

    public void deleteFriendStatistic(String id) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                ModelFriendStatistic model = new ModelFriendStatistic(id);
                friendStatisticDAO.deleteFriendStatistic(model);
            }
        });
    }

    private ModelFriendStatistic setupFriendInformation(ModelFriendStatistic model) {
        String id = model.getId();
        String authentication = model.getAuthentication();
        String firstname = model.getFirstname();
        String surname = model.getSurname();
        String language = model.getLanguage();
        long started = model.getStarted();
        long now = new Date().getTime();
        int messages = model.getMessages();
        String immersionIn = "Immersion in: " + language;
        String daysFrom = "Started: " + TimeAndLanguage.getTimeAgo(started, now);
        String chatMessages = "In chat: " + String.valueOf(messages) + " messages";
        String imageURL = ""; //TODO: dodanie bazy danych do pobrania linku do zdjęcia
        return new ModelFriendStatistic(id, authentication, firstname, surname, language, started, messages, immersionIn, daysFrom, chatMessages, imageURL);
    }
}
