package com.szelecki.immersion.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.szelecki.immersion.R;
import com.szelecki.immersion.adapters.FlashcardsAdapter;
import com.szelecki.immersion.adapters.FlashcardsAdapterInterface;
import com.szelecki.immersion.models.ModelUser;
import com.szelecki.immersion.models.ModelWord;
import com.szelecki.immersion.utils.TimeAndLanguage;
import com.szelecki.immersion.viewModels.FlashcardsFragmentViewModel;

import java.util.ArrayList;
import java.util.Date;

public class FragmentFlashcards extends Fragment {

    ViewPager viewPager;

    ArrayList<ModelWord> words;

    FlashcardsFragmentViewModel viewModel;
    ModelUser user;

    FlashcardsAdapter adapter;

    public FragmentFlashcards() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flashcards, container, false);

        viewPager = view.findViewById(R.id.viewPagerFlashcards);

        viewModel = new ViewModelProvider(this).get(FlashcardsFragmentViewModel.class);
        user = ModelUser.getInstance();

        words = new ArrayList<>();
        initViewPagerWords();

        viewModel.getInitialWordsAtTheLevels(user.getLanguage().getDescription(), new Date().getTime()).observe(getViewLifecycleOwner(), new Observer<ArrayList<ModelWord>>() {
            @Override
            public void onChanged(ArrayList<ModelWord> modelWords) {
                words = modelWords;
                adapter.setWords(words);
                adapter.notifyDataSetChanged();
                for (ModelWord model : words) {
                    Log.d("PLACEHOLDER", String.valueOf(model.getRecently()));
                    Log.d("PLACEHOLDER", String.valueOf(new Date().getTime() - TimeAndLanguage.DAY_MILLIS));
                }
            }
        });

        return view;
    }

    private void initViewPagerWords() {
        adapter = new FlashcardsAdapter(getContext(), words);
        adapter.setFlashcardsAdapterInterface(new FlashcardsAdapterInterface() {
            @Override
            public void setupLevel(int position, int level) {
                viewModel.updateWord(words.get(position), new Date().getTime(), level);
            }
        });
        viewPager.setAdapter(adapter);
        viewPager.setPadding(100, 0, 100, 0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Log.d("PLACEHOLDER", "onDestroyView");
    }
}
