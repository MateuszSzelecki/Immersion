package com.szelecki.immersion;

import com.szelecki.immersion.models.LanguagesEnum;

public class Utils {

    public static final long SECOND_MILLIS = 1000;
    public static final long MINUTE_MILLIS = 60 * SECOND_MILLIS;
    public static final long HOUR_MILLIS = 60 * MINUTE_MILLIS;
    public static final long DAY_MILLIS = 24 * HOUR_MILLIS;
    public static final long WEEK_MILLIS = 7 * DAY_MILLIS;

    public static String getTimeAgo(long started, long now) {
        long diff = now - started;
        String ago = "";

        if (diff < 15 * MINUTE_MILLIS) {
            ago = "moments ago";
        } else if (diff < 0.5 * HOUR_MILLIS) {
            ago = "15 minutes ago";
        } else if (diff < HOUR_MILLIS) {
            ago = "half hour ago";
        } else if (diff < 2 * HOUR_MILLIS) {
            ago = "an hour ago";
        } else if (diff < 24 * HOUR_MILLIS) {
            ago = String.valueOf(diff/HOUR_MILLIS) + " hours ago";
        } else if (diff < DAY_MILLIS) {
            ago = "today";
        } else if (diff < 2 * DAY_MILLIS) {
            ago = "yesterday";
        } else if (diff < WEEK_MILLIS) {
            ago = String.valueOf(diff/DAY_MILLIS) + " days ago";
        } else if (diff < 2 * WEEK_MILLIS) {
            ago = "1 week ago";
        } else {
            ago = String.valueOf(diff/WEEK_MILLIS) + " weeks ago";
        }

        return ago;
    }

    public static int setupLanguageImage(String name) {
        int image = 0;
        switch (name) {
            case "danish": return R.mipmap.ic_flag_danish_foreground;
            case "english": return R.mipmap.ic_flag_english_foreground;
            case "finnish": return R.mipmap.ic_flag_finnish_foreground;
            case "french": return R.mipmap.ic_flag_french_foreground;
            case "german": return R.mipmap.ic_flag_german_foreground;
            case "greek": return R.mipmap.ic_flag_greek_foreground;
            case "italian": return R.mipmap.ic_flag_italian_foreground;
            case "norwegian": return R.mipmap.ic_flag_norwegian_foreground;
            case "polish": return R.mipmap.ic_flag_polish_foreground;
            case "spanish": return R.mipmap.ic_flag_spanish_foreground;
            case "swedish": return R.mipmap.ic_flag_swedish_foreground;
            default: return R.mipmap.ic_flag_english_foreground;
        }
    }

    public static LanguagesEnum setupChangedLanguage(String name) {
        int image = 0;
        switch (name) {
            case "danish": return LanguagesEnum.DANISH;
            case "english": return LanguagesEnum.ENGLISH;
            case "finnish": return LanguagesEnum.FINNISH;
            case "french": return LanguagesEnum.FRENCH;
            case "german": return LanguagesEnum.GERMAN;
            case "greek": return LanguagesEnum.GREEK;
            case "italian": return LanguagesEnum.ITALIAN;
            case "norwegian": return LanguagesEnum.NORWEGIAN;
            case "polish": return LanguagesEnum.POLISH;
            case "spanish": return LanguagesEnum.SPANISH;
            case "swedish": return LanguagesEnum.SWEDISH;
            default: return LanguagesEnum.DEFAULT;
        }
    }
}