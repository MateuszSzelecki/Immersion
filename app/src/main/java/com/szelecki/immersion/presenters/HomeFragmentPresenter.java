package com.szelecki.immersion.presenters;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.collection.ArraySet;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.szelecki.immersion.utils.TimeAndLanguage;
import com.szelecki.immersion.models.ModelPostToFirebase;
import com.szelecki.immersion.models.ModelUser;
import com.szelecki.immersion.models.ModelPostFromFirebase;

import java.util.Date;
import java.util.Set;

public class HomeFragmentPresenter { //usage of architecture MVP

    HomeFragmentPresenterInterface presenterInterface;
    Context content;

    FirebaseDatabase database;
    DatabaseReference referenceId, referencePosts;

    ModelUser user;

    public HomeFragmentPresenter(HomeFragmentPresenterInterface presenterInterface, Context context) {
        this.presenterInterface = presenterInterface;
        this.content = context;
        user = ModelUser.getInstance();
        database = FirebaseDatabase.getInstance();
        referenceId = database.getReference("postsId").child(user.getLanguage().getDescription());
        referencePosts = database.getReference("posts").child(user.getLanguage().getDescription());
    }

    public void getNextTenPosts() {
        Set<String> postsId = new ArraySet<>();
        referenceId.child(user.getAuthentication()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    int amount = 0;
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        if (amount < 10) { //TODO: change to 10
                            amount++;
                            postsId.add(dataSnapshot.getValue(String.class));
                        }
//                        if (amount == 1) { referenceId.child(user.getAuthentication()).child(dataSnapshot.getKey()).removeValue(); }
                    }
                    getPostsFromId(postsId, amount);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    private void getPostsFromId(Set<String> postsId, int amount) {
        for (String postId : postsId) {
            referencePosts.child(postId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String userName = snapshot.child("userName").getValue(String.class);
                    String profileImageURL = ""; //TODO: uzyskane z firestore na podstawie authentication
                    long publishedDate = snapshot.child("publishedDate").getValue(Long.class);
                    String timeAgo = TimeAndLanguage.getExactTimeAgo(publishedDate, new Date().getTime());
                    String contentText = snapshot.child("contentText").getValue(String.class);
                    String contentImageURL = snapshot.child("contentImageURL").getValue(String.class);
                    String category1 = snapshot.child("category1").getValue(String.class);
                    String category2 = snapshot.child("category2").getValue(String.class);
                    String category3 = snapshot.child("category3").getValue(String.class);
                    int likesAmount = snapshot.child("likesAmount").getValue(Integer.class);
                    ModelPostFromFirebase model = new ModelPostFromFirebase(userName, profileImageURL, timeAgo, contentText, contentImageURL, category1, category2, category3, likesAmount);
                    presenterInterface.setupPostsInFirebase(model, amount);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {}
            });
        }
    }

    public void publishPost(String contentText, Uri imageUri, String category1, String category2, String category3) {
        String contentImageURL = "."; //TODO
        ModelPostToFirebase model = new ModelPostToFirebase(user.getAuthentication(), user.getFirstname() + " " + user.getSurname(), user.getImageURL(), new Date().getTime(), contentText, contentImageURL, category1, category2, category3);
        String postId = model.getPostId(user.getLanguage().getDescription());
        String getIdChild = "-" + String.valueOf(Timestamp.now().getSeconds() + user.getAuthentication().substring(0,3)); //TODO: dla znajomych
        referencePosts.child(postId).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    referenceId.child(user.getAuthentication()).child(getIdChild).setValue(postId);
                    Toast.makeText(content, "Post has been added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
