package com.szelecki.immersion.presenters;

import com.szelecki.immersion.models.ModelPostFromFirebase;

import java.util.ArrayList;

public interface HomeFragmentPresenterInterface {
    void setupPostsInFirebase(ModelPostFromFirebase postFromFirebase, int amount);
}
