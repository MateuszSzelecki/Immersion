package com.szelecki.immersion.adapters;

import java.util.ArrayList;

public interface OnClickLanguageItemInterface {
    void changeUserLanguage(int position);
    boolean addLanguage(ArrayList<Integer> chosen); //true jeśli język zostanie dodany
    void deleteLanguage(int position);
}
