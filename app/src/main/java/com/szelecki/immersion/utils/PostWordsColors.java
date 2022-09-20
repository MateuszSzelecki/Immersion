package com.szelecki.immersion.utils;

import com.szelecki.immersion.R;
import com.szelecki.immersion.models.ModelColor;

import java.util.ArrayList;

public class PostWordsColors {
    public static ModelColor getColors(int postPostion) {
        if (postPostion == 0 || postPostion == 5) {
            return new FirstColor();
        } else if (postPostion == 1 || postPostion == 6) {
            return new SecondColor();
        } else if (postPostion == 2 || postPostion == 7) {
            return new ThirdColor();
        } else if (postPostion == 3 || postPostion == 8) {
            return new FourthColor();
        } else if (postPostion == 4 || postPostion == 9) {
            return new FifthColor();
        }
        return new FirstColor();
    }
}

class FirstColor extends ModelColor {
    private ArrayList<Integer> backgroundColors = new ArrayList<>(R.color.teal_200);
    private ArrayList<Integer> textColors = new ArrayList<>(R.color.teal_200);

    @Override
    public int getBackgroundColor(int wordPosition) {
        return backgroundColors.get(wordPosition);
    }

    @Override
    public int getTextColor(int wordPosition) {
        return textColors.get(wordPosition);
    }
}

class SecondColor extends ModelColor {
    private ArrayList<Integer> backgroundColors = new ArrayList<>(R.color.teal_200);
    private ArrayList<Integer> textColors = new ArrayList<>(R.color.teal_200);

    @Override
    public int getBackgroundColor(int wordPosition) {
        return backgroundColors.get(wordPosition);
    }

    @Override
    public int getTextColor(int wordPosition) {
        return textColors.get(wordPosition);
    }
}

class ThirdColor extends ModelColor {
    private ArrayList<Integer> backgroundColors = new ArrayList<>(R.color.teal_200);
    private ArrayList<Integer> textColors = new ArrayList<>(R.color.teal_200);

    @Override
    public int getBackgroundColor(int wordPosition) {
        return backgroundColors.get(wordPosition);
    }

    @Override
    public int getTextColor(int wordPosition) {
        return textColors.get(wordPosition);
    }
}

class FourthColor extends ModelColor {
    private ArrayList<Integer> backgroundColors = new ArrayList<>(R.color.teal_200);
    private ArrayList<Integer> textColors = new ArrayList<>(R.color.teal_200);

    @Override
    public int getBackgroundColor(int wordPosition) {
        return backgroundColors.get(wordPosition);
    }

    @Override
    public int getTextColor(int wordPosition) {
        return textColors.get(wordPosition);
    }
}

class FifthColor extends ModelColor {
    private ArrayList<Integer> backgroundColors = new ArrayList<>(R.color.teal_200);
    private ArrayList<Integer> textColors = new ArrayList<>(R.color.teal_200);

    @Override
    public int getBackgroundColor(int wordPosition) {
        return backgroundColors.get(wordPosition);
    }

    @Override
    public int getTextColor(int wordPosition) {
        return textColors.get(wordPosition);
    }
}

