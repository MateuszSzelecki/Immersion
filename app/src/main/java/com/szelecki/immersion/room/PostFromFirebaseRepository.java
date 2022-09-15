package com.szelecki.immersion.room;

import android.app.Application;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.szelecki.immersion.models.ModelPostFromFirebase;
import com.szelecki.immersion.models.ModelReceivedPost;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PostFromFirebaseRepository {

    FirebaseDatabase database;
    DatabaseReference reference;

    public PostFromFirebaseRepository(Application application) {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("posts");
    }

    public ArrayList<ModelPostFromFirebase> getPostsFromFirebase(String language, ArrayList<String> receivedId) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<ArrayList<ModelPostFromFirebase>> futureList = executor.submit(new TaskFirebasePosts(language, receivedId));
        ArrayList<ModelPostFromFirebase> list = new ArrayList<>();
        try {
            list = futureList.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }

    class TaskFirebasePosts implements Callable<ArrayList<ModelPostFromFirebase>> {
        private String language;
        private ArrayList<String> receivedId;
        public TaskFirebasePosts(String language, ArrayList<String> receivedId) {
            this.language = language;
            this.receivedId = receivedId;
        }

        @Override
        public ArrayList<ModelPostFromFirebase> call() throws Exception {
            ArrayList<ModelPostFromFirebase> list = new ArrayList<>();
            //TODO:
            return list;
        }
    }
}
