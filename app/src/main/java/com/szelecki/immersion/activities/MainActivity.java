package com.szelecki.immersion.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.szelecki.immersion.utils.TimeAndLanguage;
import com.szelecki.immersion.adapters.NavigationViewFriendsAdapter;
import com.szelecki.immersion.adapters.OnClickLanguageItemInterface;
import com.szelecki.immersion.adapters.SheetDialogAdapter;
import com.szelecki.immersion.models.EnumLanguages;
import com.szelecki.immersion.models.ModelFriendStatistic;
import com.szelecki.immersion.models.ModelLanguageStatistic;
import com.szelecki.immersion.adapters.NavigationViewLanguagesAdapter;
import com.szelecki.immersion.R;
import com.szelecki.immersion.fragments.FragmentAccount;
import com.szelecki.immersion.fragments.FragmentFlashcards;
import com.szelecki.immersion.fragments.FragmentFriends;
import com.szelecki.immersion.fragments.FragmentHome;
import com.szelecki.immersion.fragments.FragmentMessage;
import com.szelecki.immersion.models.ModelUser;
import com.szelecki.immersion.viewModels.MainActivityViewModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity implements OnClickLanguageItemInterface {

    TabLayout tabLayout;
    MaterialToolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    RecyclerView recyclerViewLanguages, recyclerViewFriends;
    BottomSheetDialog bottomSheetDialog;

    ArrayList<ModelLanguageStatistic> languages;
    ArrayList<ModelFriendStatistic> bestFriends;

    MainActivityViewModel viewModel;
    ModelUser user;
    SharedPreferences preferences;

    NavigationViewLanguagesAdapter languagesAdapter;
    NavigationViewFriendsAdapter friendsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        replaceFragment(new FragmentHome());

        tabLayout = findViewById(R.id.tabLayout);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        recyclerViewLanguages = findViewById(R.id.recyclerViewNavigationHeaderLanguages);
        recyclerViewFriends = findViewById(R.id.recyclerViewNavigationHeaderFriends);

        //instancja ViewModel, Singletona oraz Preferences
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        user = ModelUser.getInstance();
        preferences = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);

        languages = new ArrayList<>(); bestFriends = new ArrayList<>();
        initRecyclerViewLanguageStatistics();
        initRecyclerViewFriendStatistics();

        //tytuł toolbaru
        if (user.getLanguage() != EnumLanguages.DEFAULT) {
            toolbar.setTitle(user.getLanguage().getRepresentation());
        }

        //otwieranie fragmentów po kliknięciach tabu
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int id = tab.getPosition();
                switch (id) {
                    case 0: replaceFragment(new FragmentHome()); break;
                    case 1: replaceFragment(new FragmentFlashcards()); break;
                    case 2: replaceFragment(new FragmentMessage()); break;
                    case 3: replaceFragment(new FragmentFriends()); break;
                    case 4: replaceFragment(new FragmentAccount()); break;
                    default: break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        //wysunięcie menu po kliknięciu icon w toolbarze
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        //okno do zmiany języka po długim przytrzymaniu nazwy
        toolbar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
                View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.main_sheet_select_language, findViewById(R.id.bottomSheetContainer));
                RecyclerView recyclerView = bottomSheetView.findViewById(R.id.recyclerViewSheet);
                SheetDialogAdapter adapter = new SheetDialogAdapter(languages, MainActivity.this);
                adapter.setOnClickLanguageItemInterface(MainActivity.this);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
                return true;
            }
        });

        //obserwowanie liveData z listą modeli statystyk języków
        viewModel.getInitialLanguagesStatistics().observe(this, new Observer<ArrayList<ModelLanguageStatistic>>() {
            @Override
            public void onChanged(ArrayList<ModelLanguageStatistic> modelLanguageStatistics) {
                languages = modelLanguageStatistics;
                languagesAdapter.setLanguages(languages);
                if (languages.stream().noneMatch(s -> s.getName().equals(user.getLanguage().getDescription())) || user.getLanguage() == EnumLanguages.DEFAULT || user.getLanguage() == null) {
                    user.setLanguage(TimeAndLanguage.setupChangedLanguage(languages.get(0).getName()));
                    toolbar.setTitle(user.getLanguage().getRepresentation());
                }
            }
        });

        //obserwowanie liveData z listą modeli statystyk najlepszych znajomych
        viewModel.getInitialTheBestFriendsStatistics(-1).observe(this, new Observer<ArrayList<ModelFriendStatistic>>() {
            @Override
            public void onChanged(ArrayList<ModelFriendStatistic> modelFriendStatistics) {
                bestFriends = modelFriendStatistics;
                int amount = 6 - languages.size();
                if (bestFriends.size() < amount) {
                    amount = bestFriends.size();
                }
                List<ModelFriendStatistic> temp = bestFriends.stream().sorted(Comparator.comparing(ModelFriendStatistic::getMessages)).collect(Collectors.toList());
                bestFriends.clear();
                for (int i=1; i<=amount; i++) { bestFriends.add(temp.get(temp.size()-i)); }
                friendsAdapter.setFriends(bestFriends);
            }
        });
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutMain, fragment);
        fragmentTransaction.commit();
    }

    private void initRecyclerViewLanguageStatistics() {
        languagesAdapter = new NavigationViewLanguagesAdapter(languages, MainActivity.this);
        recyclerViewLanguages.setAdapter(languagesAdapter); recyclerViewLanguages.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initRecyclerViewFriendStatistics() {
        friendsAdapter = new NavigationViewFriendsAdapter(bestFriends, MainActivity.this);
        recyclerViewFriends.setAdapter(friendsAdapter); recyclerViewFriends.setLayoutManager(new LinearLayoutManager(this));
    }

    //metoda zapisująca nowe wartości dla obecnego języka użytkownika
    private void newStatisticsAndPref() {
        if (user.getLanguage() != EnumLanguages.DEFAULT) {
            String language = user.getLanguage().getDescription();
            preferences.edit().putString("userLanguage", language).apply();
            ModelLanguageStatistic model = languages.stream().filter(s -> s.getName().equals(language)).findFirst().get();
            viewModel.updateLanguageStatistic(model, new Date().getTime(), 0);
        }
    }

    //metoda na kliknięcie itemu w RecyclerView w BottomSheet, zmiana języka użytkownika
    @Override
    public void changeUserLanguage(int position) {
        newStatisticsAndPref();
        user.setLanguage(TimeAndLanguage.setupChangedLanguage(languages.get(position).getName()));
        startActivity(new Intent(MainActivity.this, MainActivity.class));
    }

    //metoda na przytrzymaniu itemu w RecyclerView w BottomSheet, strona do dodania language
    @Override
    public boolean addLanguage(ArrayList<Integer> chosen) {
        Intent intent = new Intent(MainActivity.this, AddLanguageActivity.class);
        ArrayList<String> selected = new ArrayList<>();
        for (ModelLanguageStatistic model : languages) {
            selected.add(model.getName());
        }
        intent.putExtra("selected", selected);
        intent.putExtra("signUp", false);
        intent.putExtra("motherLanguage", false);
        intent.putExtra("addLanguage", false);
        startActivity(intent);
        return true;
    }

    //metoda na kliknięcie itemu w RecyclerView w BottomSheet, usunięcie language
    @Override
    public void deleteLanguage(int position) {
        if (languages.size() > 1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Deleting " + languages.get(position).getName());
            builder.setMessage("The process is irreversible. Are you sure?");
            builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    viewModel.deleteLanguageStatistic(languages.get(position).getName());
                    languages.remove(position);
                    user.setLanguage(TimeAndLanguage.setupChangedLanguage(languages.get(0).getName()));
                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                }
            });
            builder.setNegativeButton("Leave", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    bottomSheetDialog.dismiss();
                }
            });
            builder.create().show();
        } else {
            Toast.makeText(MainActivity.this, "You have to have at least 1 language", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        newStatisticsAndPref();
    }

    //for (ModelWord model : words) gdzie words - wszystkie słówka ze słownika użytkownika dla języka
    //  if(content.toUpperCase().contains(model.getWord()) gdzie content - zawartość posta
    //    usersWords<String>.add(model.getWord())
    //recyclerView.setWords(userWords) gdzie recyclerView - RecyclerView pod postem - kolorowe, okrągłe itemy

    //5, 10, 15, 20, 25, 30, 35
    //4, 8, 12, 16, 24, 28, 32, 36,
    //3, 6, 9, 18, 21, 27, 33
    //2, 14, 22, 26, 34
    //1, 7, 11, 13, 17, 19, 23, 29, 31
}