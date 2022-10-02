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
import android.view.View;
import android.widget.Toast;

import com.szelecki.immersion.R;
import com.szelecki.immersion.adapters.EditCategoriesBenethAdapter;
import com.szelecki.immersion.adapters.EditCategoriesMainAdapter;
import com.szelecki.immersion.models.ModelUser;
import com.szelecki.immersion.presenters.EditProfilePresenter;
import com.szelecki.immersion.utils.CategoriesUtils;
import com.szelecki.immersion.viewModels.EditCategoriesActivityViewModel;

import java.util.ArrayList;

public class EditCategoriesActivity extends AppCompatActivity implements EditCategoriesMainAdapter.OnCategoryChooseInterface {

    CardView introCardView;
    RecyclerView mainRecyclerView, benethRecyclerView;
    AppCompatButton confirmButton;

    ArrayList<ArrayList<String>> benethCategories;
    ArrayList<String> mainCategories;
    ArrayList<String> selected;
    ArrayList<String> selectedId;
    boolean signUp;
    int mainIndex;
    String textToButton;

    EditCategoriesActivityViewModel viewModel;
    ModelUser user;
    EditProfilePresenter presenter;

    EditCategoriesMainAdapter mainAdapter;
    EditCategoriesBenethAdapter benethAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_categories);
        getSupportActionBar().hide();

        introCardView = findViewById(R.id.introEditCategories);
        mainRecyclerView = findViewById(R.id.mainRecyclerViewEditCategories);
        benethRecyclerView = findViewById(R.id.benethRecyclerViewEditCategories);
        confirmButton = findViewById(R.id.confirmButtonEditCategories);

        viewModel = new ViewModelProvider(this).get(EditCategoriesActivityViewModel.class);
        user = ModelUser.getInstance();
        presenter = new EditProfilePresenter(EditCategoriesActivity.this);

        signUp = getIntent().getBooleanExtra("signUp", false);

        if (signUp) {
            introCardView.setVisibility(View.VISIBLE);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    introCardView.setVisibility(View.GONE);
                }
            }, 3500);
        }

        selected = getIntent().getStringArrayListExtra("selected");
        selectedId = getIntent().getStringArrayListExtra("selectedId");
        setupArraysToAdapter();
        initMainRecyclerView();

        textToButton = ".";
        confirmButton.setText(textToButton);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selected.size() > 0) { //jeśli wybrano jakieś kategorie
                    user.setHobbies(selectedId);
                    if (signUp) {
                        Intent intent = new Intent(EditCategoriesActivity.this, EditProfileActivity.class);
                        intent.putExtra("signUp", true);
                        startActivity(intent);
                    } else {
                        presenter.editHoobiesInFirestore(selectedId, new EditProfilePresenter.OnSuccessfulEditUser() {
                            @Override
                            public void userEdited() {
                                startActivity(new Intent(EditCategoriesActivity.this, MainActivity.class));
                            }
                        });
                    }
                } else {
                    Toast.makeText(EditCategoriesActivity.this, "You have to select at least 1 category!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void showMoreCategories(int position) {
        benethRecyclerView.setVisibility(View.GONE);
        mainIndex = position;
        if (benethCategories.get(position).size() == 0 || !benethCategories.get(position).get(0).equals(mainCategories.get(position))) {
            benethCategories.get(position).add(0, mainCategories.get(position));
        }
        benethAdapter = new EditCategoriesBenethAdapter(benethCategories.get(position), position, getApplicationContext(), new EditCategoriesBenethAdapter.OnSelectBenethCategoryInterface() {
            @Override
            public void onSelectBenethCategory(int benethPosition) {
                String id = getSelectedId(benethCategories.get(mainIndex).get(0), benethCategories.get(mainIndex).get(benethPosition));
                if (!selectedId.contains(id)) {
                    if (selected.size() < 5) {
                        selected.add(benethCategories.get(mainIndex).get(benethPosition));
                        selectedId.add(getSelectedId(benethCategories.get(mainIndex).get(0), benethCategories.get(mainIndex).get(benethPosition)));
                        textToButton = "";
                        for (String string : selected) {
                            textToButton = textToButton + string + ", ";
                        }
                        confirmButton.setText(textToButton);
                    } else {
                        Toast.makeText(EditCategoriesActivity.this, "You can add up to 5 categories", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    int indexToRemove = selectedId.indexOf(id);
                    selectedId.remove(indexToRemove);
                    selected.remove(indexToRemove);
                    textToButton = "";
                    for (String string : selected) {
                        textToButton = textToButton + string + ", ";
                    }
                    confirmButton.setText(textToButton);
                }
            }
        });
        benethRecyclerView.setLayoutManager(new LinearLayoutManager(EditCategoriesActivity.this, LinearLayoutManager.HORIZONTAL, false));
        benethRecyclerView.setAdapter(benethAdapter);
        benethRecyclerView.setVisibility(View.VISIBLE);
    }


    private void setupArraysToAdapter() {
        benethCategories = CategoriesUtils.getGroupedCategories(getApplicationContext());

        mainCategories = new ArrayList<>();

        for (ArrayList<String> array : benethCategories) {
            mainCategories.add(array.get(0));
            array.remove(0);
        }
    }

    private void initMainRecyclerView() {
        mainAdapter = new EditCategoriesMainAdapter(mainCategories, getApplicationContext(), this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(EditCategoriesActivity.this, 2, GridLayoutManager.VERTICAL, false);
        mainRecyclerView.setLayoutManager(gridLayoutManager);
        mainRecyclerView.setAdapter(mainAdapter);
    }

    private String getSelectedId(String membership, String category) {
        String id = membership.toUpperCase() + "_" + category.substring(0,3).toUpperCase();
        return id;
    }
}