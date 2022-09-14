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
import com.szelecki.immersion.models.EnumLanguages;
import com.szelecki.immersion.models.ModelLanguageStatistic;
import com.szelecki.immersion.models.ModelUser;
import com.szelecki.immersion.viewModels.AddLanguageActivityViewModel;

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