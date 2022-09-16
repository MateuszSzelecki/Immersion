package com.szelecki.immersion.viewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.szelecki.immersion.models.ModelPostFromFirebase;

import java.util.ArrayList;

public class HomeFragmentViewModel extends AndroidViewModel {

    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);
    }
}
