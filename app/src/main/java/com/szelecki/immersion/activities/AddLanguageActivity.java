package com.szelecki.immersion.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.szelecki.immersion.R;
import com.szelecki.immersion.Utils;
import com.szelecki.immersion.adapters.AddLanguageAdapter;
import com.szelecki.immersion.adapters.OnClickLanguageItemInterface;
import com.szelecki.immersion.models.LanguagesEnum;
import com.szelecki.immersion.models.ModelLanguageStatistic;
import com.szelecki.immersion.models.ModelUser;
import com.szelecki.immersion.viewModels.AddLanguageActivityViewModel;
import com.szelecki.immersion.viewModels.MainActivityViewModel;

import java.util.ArrayList;
import java.util.Date;

public class AddLanguageActivity extends AppCompatActivity implements OnClickLanguageItemInterface {

    RecyclerView recyclerView;

    ArrayList<ModelLanguageStatistic> languages;
    ArrayList<String> representations;
    ArrayList<String> selected;

    AddLanguageActivityViewModel viewModel;
    ModelUser user;

    AddLanguageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_language);
        getSupportActionBar().hide();

        recyclerView = findViewById(R.id.recyclerViewAddLanguage);

        viewModel = new ViewModelProvider(this).get(AddLanguageActivityViewModel.class);
        user = ModelUser.getInstance();

        languages = new ArrayList<>(); representations = new ArrayList<>(); selected = new ArrayList<>();
        selected = getIntent().getStringArrayListExtra("selected");
        initLanguages(); //inicjowanie listy języków do RecyclerView

        //instancja adapteru w GridLayout
        adapter = new AddLanguageAdapter(languages, representations, getApplicationContext(), getDrawable(R.drawable.shape_addlanguage_item_sel));
        adapter.setOnClickLanguageItemInterface(AddLanguageActivity.this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(AddLanguageActivity.this, 3, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initLanguages() {
        languages.add(setupLanguage(LanguagesEnum.DANISH)); representations.add(LanguagesEnum.DANISH.getRepresentation());
        languages.add(setupLanguage(LanguagesEnum.ENGLISH)); representations.add(LanguagesEnum.ENGLISH.getRepresentation());
        languages.add(setupLanguage(LanguagesEnum.FINNISH)); representations.add(LanguagesEnum.FINNISH.getRepresentation());
        languages.add(setupLanguage(LanguagesEnum.FRENCH)); representations.add(LanguagesEnum.FRENCH.getRepresentation());
        languages.add(setupLanguage(LanguagesEnum.GERMAN)); representations.add(LanguagesEnum.GERMAN.getRepresentation());
        languages.add(setupLanguage(LanguagesEnum.GREEK)); representations.add(LanguagesEnum.GREEK.getRepresentation());
        languages.add(setupLanguage(LanguagesEnum.ITALIAN)); representations.add(LanguagesEnum.ITALIAN.getRepresentation());
        languages.add(setupLanguage(LanguagesEnum.NORWEGIAN)); representations.add(LanguagesEnum.NORWEGIAN.getRepresentation());
        languages.add(setupLanguage(LanguagesEnum.POLISH)); representations.add(LanguagesEnum.POLISH.getRepresentation());
        languages.add(setupLanguage(LanguagesEnum.SPANISH)); representations.add(LanguagesEnum.SPANISH.getRepresentation());
        languages.add(setupLanguage(LanguagesEnum.SWEDISH)); representations.add(LanguagesEnum.SWEDISH.getRepresentation());
    }

    private ModelLanguageStatistic setupLanguage(LanguagesEnum language) {
        String name = language.getDescription();
        int image = Utils.setupLanguageImage(name);
        int words = 0;
        if (selected.contains(name)) {
            words = 1;
        }
        return new ModelLanguageStatistic(name, image, words);
    }

    @Override
    public void changeUserLanguage(int position) {} //nieużywane w tej aktywności

    //metoda wywołana z RecyclerView jeśli język jeszcze nie został wybrany
    @Override
    public void addLanguage(int positon) {
        viewModel.addLanguageStatistic(languages.get(positon).getName(), new Date().getTime());
        user.setLanguage(Utils.setupChangedLanguage(languages.get(positon).getName()));
        startActivity(new Intent(AddLanguageActivity.this, MainActivity.class));
    }

    @Override
    public void deleteLanguage(int position) {} //nieużywane w tej aktywności
}