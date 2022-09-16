package com.szelecki.immersion.presenters;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.szelecki.immersion.Utils;
import com.szelecki.immersion.activities.MainActivity;
import com.szelecki.immersion.models.ModelPostFromFirebase;

import java.util.ArrayList;
import java.util.Date;

public class HomeFragmentPresenter {

    HomeFragmentPresenterInterface presenterInterface;

    FirebaseDatabase database;
    DatabaseReference reference;

    String authentication;

    public HomeFragmentPresenter(HomeFragmentPresenterInterface presenterInterface, String language, String authentication) {
        this.presenterInterface = presenterInterface;

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("posts").child(language);
        this.authentication = authentication;
    }

    public void getNextTenPosts() {
        ArrayList<ModelPostFromFirebase> posts = new ArrayList<>();
        reference.child(authentication).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> postsId = new ArrayList<>();
                if (snapshot.hasChildren()) {
                    int amount = 0;
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        amount ++; if (amount > 10) { break; }
                        postsId.add(dataSnapshot.getValue(String.class));
                    }
                    for (String id : postsId) {
                        reference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    String userName = snapshot.child("userName").getValue(String.class);
                                    String authentication = snapshot.child("authentiacion").getValue(String.class);
                                    long published = snapshot.child("published").getValue(Long.class);
                                    String timeAgo = Utils.getExactTimeAgo(published, new Date().getTime());
                                    //TODO: dokończyć uzyskiwanie wartości z bazy danych oraz instancja modelu
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {}
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
        presenterInterface.setupPostsInAdapter(posts);
    }
}
