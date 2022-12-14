package com.szelecki.immersion.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.szelecki.immersion.R;
import com.szelecki.immersion.presenters.EditProfilePresenter;
import com.szelecki.immersion.presenters.LoginPresenter;
import com.szelecki.immersion.utils.TimeAndLanguage;
import com.szelecki.immersion.adapters.AddLanguageAdapter;
import com.szelecki.immersion.adapters.OnClickLanguageItemInterface;
import com.szelecki.immersion.models.EnumLanguages;
import com.szelecki.immersion.models.ModelLanguageStatistic;
import com.szelecki.immersion.models.ModelUser;
import com.szelecki.immersion.viewModels.AddLanguageActivityViewModel;
import com.szelecki.immersion.viewModels.EditCategoriesActivityViewModel;

import java.util.ArrayList;
import java.util.Date;

public class AddLanguageActivity extends AppCompatActivity implements OnClickLanguageItemInterface {

    RecyclerView recyclerView;
    AppCompatButton confirmButton;
    CardView introMotherLanguage, introAddLanguage;

    ArrayList<ModelLanguageStatistic> languages;
    ArrayList<String> representations;
    ArrayList<String> selected;
    ArrayList<Integer> chosen;
    boolean signUp, motherLanguage, addLanguage;

    AddLanguageActivityViewModel viewModel;
    EditCategoriesActivityViewModel viewModelCategories;
    ModelUser user;
    EditProfilePresenter presenter;

    AddLanguageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_language);
        getSupportActionBar().hide();

        recyclerView = findViewById(R.id.recyclerViewAddLanguage);
        confirmButton = findViewById(R.id.confirmButtonAddLanguage);
        introMotherLanguage = findViewById(R.id.introMotherAddLanguage);
        introAddLanguage = findViewById(R.id.introAddLanguageAddLanguage);

        viewModel = new ViewModelProvider(this).get(AddLanguageActivityViewModel.class);
        viewModelCategories = new ViewModelProvider(this).get(EditCategoriesActivityViewModel.class);
        user = ModelUser.getInstance();
        presenter = new EditProfilePresenter(AddLanguageActivity.this);

        signUp = getIntent().getBooleanExtra("signUp", false);
        motherLanguage = getIntent().getBooleanExtra("motherLanguage", false);
        addLanguage = getIntent().getBooleanExtra("addLanguage", false);

        if (signUp) {
            if (motherLanguage && !addLanguage) {
                viewModelCategories.addAllCategories();
                confirmButton.setVisibility(View.GONE);
                introMotherLanguage.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        introMotherLanguage.setVisibility(View.GONE);
                    }
                }, 3500);
            } else if (!motherLanguage && addLanguage) {
                introAddLanguage.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        introAddLanguage.setVisibility(View.GONE);
                    }
                }, 3500);
            }
        }

        languages = new ArrayList<>(); representations = new ArrayList<>(); selected = new ArrayList<>(); chosen = new ArrayList<>();
        selected = getIntent().getStringArrayListExtra("selected");
        initLanguages();

        if (signUp) {
            selected.clear();
        } else {
            selected.remove(user.getMotherLanguage().getDescription());
        }

        String textToButton = String.valueOf(selected.size()) + "/4";
        confirmButton.setText(textToButton);

        //instancja adapteru w GridLayout
        adapter = new AddLanguageAdapter(languages, representations, getApplicationContext());
        adapter.setOnClickLanguageItemInterface(AddLanguageActivity.this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(AddLanguageActivity.this, 3, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        confirmButton.setOnClickListener(new View.OnClickListener() { //tylko gdy w trybie addLanguage
            @Override
            public void onClick(View view) {
                if (chosen.size() > 0) { //je??li wybrano jakie?? j??zyki
                    ArrayList<String> tempLanguages = new ArrayList<>();
                    for (int index : chosen) {
                        viewModel.addLanguageStatistic(languages.get(index).getName(), new Date().getTime(), user.getAuthentication());
                        tempLanguages.add(languages.get(index).getName());
                    }
                    user.setLanguages(tempLanguages);
                    user.setLanguage(TimeAndLanguage.setupChangedLanguage(languages.get(chosen.get(0)).getName()));
                    if (signUp) {
                        Intent intent = new Intent(AddLanguageActivity.this, EditCategoriesActivity.class);
                        intent.putExtra("signUp", true);
                        intent.putExtra("selected", new ArrayList<>());
                        intent.putExtra("selectedId", new ArrayList<>());
                        startActivity(intent);
                    } else {
                        ArrayList<String> userLanguages = new ArrayList<>();
                        userLanguages.addAll(selected); userLanguages.addAll(tempLanguages);
                        presenter.editLanguagesInFirestore(userLanguages, new EditProfilePresenter.OnSuccessfulEditUser() {
                            @Override
                            public void userEdited() {
                                startActivity(new Intent(AddLanguageActivity.this, MainActivity.class));
                            }
                        });
                    }
                } else {
                    Toast.makeText(AddLanguageActivity.this, "You have to select at least 1 language!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initLanguages() {
        languages.add(setupLanguage(EnumLanguages.DANISH)); representations.add(EnumLanguages.DANISH.getRepresentation());
        languages.add(setupLanguage(EnumLanguages.ENGLISH)); representations.add(EnumLanguages.ENGLISH.getRepresentation());
        languages.add(setupLanguage(EnumLanguages.FINNISH)); representations.add(EnumLanguages.FINNISH.getRepresentation());
        languages.add(setupLanguage(EnumLanguages.FRENCH)); representations.add(EnumLanguages.FRENCH.getRepresentation());
        languages.add(setupLanguage(EnumLanguages.GERMAN)); representations.add(EnumLanguages.GERMAN.getRepresentation());
        languages.add(setupLanguage(EnumLanguages.GREEK)); representations.add(EnumLanguages.GREEK.getRepresentation());
        languages.add(setupLanguage(EnumLanguages.ITALIAN)); representations.add(EnumLanguages.ITALIAN.getRepresentation());
        languages.add(setupLanguage(EnumLanguages.NORWEGIAN)); representations.add(EnumLanguages.NORWEGIAN.getRepresentation());
        languages.add(setupLanguage(EnumLanguages.POLISH)); representations.add(EnumLanguages.POLISH.getRepresentation());
        languages.add(setupLanguage(EnumLanguages.SPANISH)); representations.add(EnumLanguages.SPANISH.getRepresentation());
        languages.add(setupLanguage(EnumLanguages.SWEDISH)); representations.add(EnumLanguages.SWEDISH.getRepresentation());
    }

    private ModelLanguageStatistic setupLanguage(EnumLanguages language) {
        String name = language.getDescription();
        int image = TimeAndLanguage.setupLanguageImage(name);
        int words = 0;
        if (selected.contains(name)) {
            words = 1;
        }
        return new ModelLanguageStatistic(name, image, words);
    }

    @Override
    public void changeUserLanguage(int position) {} //nieu??ywane w tej aktywno??ci

    @Override
    public boolean addLanguage(ArrayList<Integer> recyclerChosen) {
        if (motherLanguage && !addLanguage) { //je??li w trybie mother language
            if (recyclerChosen.size() == 1) {
                user.setMotherLanguage(TimeAndLanguage.setupChangedLanguage(languages.get(recyclerChosen.get(0)).getName()));
                Intent intent = new Intent(AddLanguageActivity.this, AddLanguageActivity.class);
                selected.clear(); selected.add(languages.get(recyclerChosen.get(0)).getName());
                intent.putExtra("selected", selected);
                intent.putExtra("signUp", true);
                intent.putExtra("motherLanguage", false);
                intent.putExtra("addLanguage", true);
                startActivity(intent);
            }
            return true;
        } else if (selected.size()+recyclerChosen.size() <= 4) {
            chosen.clear(); chosen.addAll(recyclerChosen);
            String textToButton = String.valueOf(chosen.size()+selected.size()) + "/4";
            confirmButton.setText(textToButton);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void deleteLanguage(int position) {} //nieu??ywane w tej aktywno??ci
}