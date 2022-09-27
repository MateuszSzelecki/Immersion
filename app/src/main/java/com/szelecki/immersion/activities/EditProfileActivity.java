package com.szelecki.immersion.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.szelecki.immersion.R;
import com.szelecki.immersion.fragments.FragmentAccount;
import com.szelecki.immersion.fragments.FragmentEditImage;
import com.szelecki.immersion.fragments.FragmentEditInfo;
import com.szelecki.immersion.fragments.FragmentFlashcards;
import com.szelecki.immersion.fragments.FragmentFriends;
import com.szelecki.immersion.fragments.FragmentHome;
import com.szelecki.immersion.fragments.FragmentMessage;
import com.szelecki.immersion.models.ModelUser;

public class EditProfileActivity extends AppCompatActivity {

    TabLayout tabLayout;
    CardView introCardView;

    boolean signUp;

    ModelUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        getSupportActionBar().hide();

        tabLayout = findViewById(R.id.tabLayoutEditProfile);
        introCardView = findViewById(R.id.introEditProfile);

        user = ModelUser.getInstance();

        signUp = getIntent().getBooleanExtra("signUp", false);
        replaceFragment(new FragmentEditInfo(signUp)); tabLayout.getTabAt(1).select();

        if (signUp) {
            introCardView.setVisibility(View.VISIBLE);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    introCardView.setVisibility(View.GONE);
                }
            }, 4000);
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int id = tab.getPosition();
                switch (id) {
                    case 0: replaceFragment(new FragmentEditImage(signUp, new FragmentEditImage.ChangeTabLayoutInteface() {
                        @Override
                        public void setTabAt(int position) {
                            tabLayout.getTabAt(position).select();
                        }
                    })); break;
                    case 1: replaceFragment(new FragmentEditInfo(signUp)); break;
                    default: break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutEditProfile, fragment);
        fragmentTransaction.commit();
    }
}