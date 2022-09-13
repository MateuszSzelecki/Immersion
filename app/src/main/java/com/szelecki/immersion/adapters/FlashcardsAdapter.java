package com.szelecki.immersion.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.szelecki.immersion.R;
import com.szelecki.immersion.models.ModelWord;

import java.util.ArrayList;

public class FlashcardsAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<ModelWord> models;

    FlashcardsAdapterInterface listener;

    public void setFlashcardsAdapterInterface(FlashcardsAdapterInterface listener) {
        this.listener = listener;
    }

    public FlashcardsAdapter(Context context, ArrayList<ModelWord> models) {
        this.context = context;
        this.models = models;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.flashcards_card_item, container, false);

        RelativeLayout wordCardItem = view.findViewById(R.id.wordFlashcardsCardItem);
        RelativeLayout meaningCardItem = view.findViewById(R.id.meaningFlashcardsCardItem);
        TextView textViewWord = view.findViewById(R.id.textViewFlashcardsWord);
        TextView textViewMeaning = view.findViewById(R.id.textViewFlashcardsMeaning);
        Button buttonPerfect = view.findViewById(R.id.buttonFlashcardsPerfect);
        Button buttonAlmost = view.findViewById(R.id.buttonFlashcardsAlmost);
        Button buttonLittle = view.findViewById(R.id.buttonFlashcardsLittle);
        Button buttonNothing = view.findViewById(R.id.buttonFlashcardsNothing);

        ModelWord model = models.get(position);

        textViewWord.setText(model.getWord());
        textViewMeaning.setText(model.getMeaning());

        wordCardItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordCardItem.setVisibility(View.GONE);
                meaningCardItem.setVisibility(View.VISIBLE);
            }
        });

        meaningCardItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                meaningCardItem.setVisibility(View.GONE);
                wordCardItem.setVisibility(View.VISIBLE);
            }
        });

        buttonPerfect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.setupLevel(position, 4);
            }
        });

        buttonAlmost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.setupLevel(position, 3);
            }
        });

        buttonLittle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.setupLevel(position, 2);
            }
        });

        buttonNothing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.setupLevel(position, 1);
            }
        });

        container.addView(view, position);
        return view;
    }

    public void setWords(ArrayList<ModelWord> models) {
        this.models = models;
    }
}