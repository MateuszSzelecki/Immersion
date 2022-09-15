package com.szelecki.immersion.room;

import android.app.Application;

import com.szelecki.immersion.models.ModelPostFromFirebase;
import com.szelecki.immersion.models.ModelReceivedPost;
import com.szelecki.immersion.models.ModelWord;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ReceivedPostRepository {

    private ReceivedPostDAO receivedPostDAO;

    public ReceivedPostRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        receivedPostDAO = appDatabase.getReceivedPostDAO();
    }

    public ArrayList<ModelReceivedPost> getReceivedPosts(String language) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<ArrayList<ModelReceivedPost>> futureList = executor.submit(new TaskReceivedPost(language));
        ArrayList<ModelReceivedPost> list = new ArrayList<>();
        try {
            list = futureList.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }

    class TaskReceivedPost implements Callable<ArrayList<ModelReceivedPost>> {
        private String language;
        public TaskReceivedPost(String language) { this.language = language; }

        @Override
        public ArrayList<ModelReceivedPost> call() throws Exception {
            ArrayList<ModelReceivedPost> list = new ArrayList<>();
            for (ModelReceivedPost model : receivedPostDAO.getReceivedPosts(language)) {
                list.add(model);
            }
            return list;
        }
    }

    public void insertReceivedPost(ArrayList<ModelPostFromFirebase> newPosts, String language) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                for (ModelPostFromFirebase model : newPosts) {
                    receivedPostDAO.addReceivedPost(new ModelReceivedPost(model.getPostId(), language));
                }
            }
        });
    }
}
