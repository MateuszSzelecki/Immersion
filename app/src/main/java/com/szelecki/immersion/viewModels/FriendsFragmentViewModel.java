package com.szelecki.immersion.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.szelecki.immersion.room.FriendStatisticRepository;

public class FriendsFragmentViewModel extends AndroidViewModel {

    FriendStatisticRepository friendStatisticRepository;

    public FriendsFragmentViewModel(@NonNull Application application) {
        super(application);
        friendStatisticRepository = new FriendStatisticRepository(application);
    }

    public void addFriendStatistic(String authentication, String firstname, String surname, String language, long started) {
        friendStatisticRepository.insertFriendStatistic(authentication, firstname, surname, language, started);
    }
}
