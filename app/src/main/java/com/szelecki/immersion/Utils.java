package com.szelecki.immersion;

import com.szelecki.immersion.models.EnumLanguages;

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

    public static String getExactTimeAgo(long started, long now) {
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

    public static EnumLanguages setupChangedLanguage(String name) {
        int image = 0;
        switch (name) {
            case "danish": return EnumLanguages.DANISH;
            case "english": return EnumLanguages.ENGLISH;
            case "finnish": return EnumLanguages.FINNISH;
            case "french": return EnumLanguages.FRENCH;
            case "german": return EnumLanguages.GERMAN;
            case "greek": return EnumLanguages.GREEK;
            case "italian": return EnumLanguages.ITALIAN;
            case "norwegian": return EnumLanguages.NORWEGIAN;
            case "polish": return EnumLanguages.POLISH;
            case "spanish": return EnumLanguages.SPANISH;
            case "swedish": return EnumLanguages.SWEDISH;
            default: return EnumLanguages.DEFAULT;
        }
    }
}
