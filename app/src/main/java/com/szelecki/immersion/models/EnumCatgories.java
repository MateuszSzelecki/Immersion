package com.szelecki.immersion.models;

public enum EnumCatgories {
    TECH("tech", ""),
    COMPUTER("computer", "tech"),
    GADGETS("gadgets", "tech"),
    MOBILE_APP("mobile app", "tech"),

    GAMING("gaming", ""),
    E_SPORTS("e-sports", "gaming"),

    HEALTH("health", ""),
    PSYCHOLOGY("psychology", "health"),
    FOOD("food", "health"),
    SEX("sex", "health"),
    RELATIONSHIPS("relationships", "health"),
    DIETS("diets", "health"),
    FITNESS("fitness", "health"),

    TRAVEL("travel", ""),
    EUROPE("Europe", "travel"),
    ASIA("Asia", "travel"),
    UNITED_STATES("United States", "travel"),
    AFRICA("Africa", "travel"),
    CENTRAL_SOUTH_AMERICA("Central-South America", "travel"),
    AUSTRALIA("Australia", "travel"),
    POLAR_REGIONS("Polar-regions", "travel"),

    FOOTBALL("football", "football"),
    BUNDESLIGA("Bundesliga", "football"),
    LIGUE_1("Ligue 1", "football"),
    PREMIER_LEAGUE("Premier League", "football"),
    LA_LIGA("La Liga", "football"),
    SERIE_A("Serie A", "football"),

    SPORTS("sports", "sports"),
    BASKETBALL("basketball", "sports"),
    CYCLING("cycling", "sports"),
    FORMULA_ONE("formula one", "sports"),
    GOLF("golf", "sports"),
    MARTIAL_ARTS("martial arts", "sports"),
    RUGBY("rugby", "sports"),
    TENNIS("tennis", "sports"),
    EXTREME_SPORTS("extreme sports", "sports"),
    HORSE_RACING("horse racing", "sports"),
    BODYBUILDING("body building", "sports"),
    BASEBALL("baseball", "sports"),
    ICE_HOCKEY("ice hockey", "sports"),
    BETTING("betting", "sports"),
    MOTOR("motor", "sports"),
    BOATS("boats", "sports"),

    FASHION("fashion", ""),

    DESIGN("design", ""),

    PHOTO("photo", ""),

    PARENTING("parenting", ""),

    HOME_GARDEN("home & garden", ""),

    FILM("film", "film"),
    FILM_ACTION_ADVENTURES("action & adventures", "film"),
    FILM_COMEDY("comedy", "film"),
    FILM_DOCUMENTARY("documentary", "film"),
    FILM_DRAMA("drama", "film"),
    FILM_FANTASY("fantasy", "film"),
    FILM_HORROR("horror", "film"),
    FILM_KIDS_FAMILY("kids & family", "film"),
    FILM_MILITARY_WAR("military & war", "film"),
    FILM_THRILLERS("thrillers", "film"),
    FILM_ROMANCE("romance", "film"),
    FILM_SCIENCE_FICTION("science-fiction", "film"),
    FILM_WESTERNS("westerns", "film"),

    PODCASTS("podcasts", ""),

    TV_SERIES("tv series", "tv series"),
    TV_SERIES_ACTION_ADVENTURES("action & adventures", "tv series"),
    TV_SERIES_COMEDY("comedy", "tv series"),
    TV_SERIES_DOCUMENTARY("documentary", "tv series"),
    TV_SERIES_DRAMA("drama", "tv series"),
    TV_SERIES_FANTASY("fantasy", "tv series"),
    TV_SERIES_HORROR("horror", "tv series"),
    TV_SERIES_KIDS_FAMILY("kids & family", "tv series"),
    TV_SERIES_MILITARY_WAR("military & war", "tv series"),
    TV_SERIES_THRILLERS("thrillers", "tv series"),
    TV_SERIES_ROMANCE("romance", "tv series"),
    TV_SERIES_SCIENCE_FICTION("science-fiction", "tv series"),
    TV_SERIES_WESTERNS("westerns", "tv series"),
    TV_SERIES_REALITY_TV("reality TV", "tv series"),

    MUSIC("music", "music"),
    MUSIC_HIP_HOP("hip-hop", "music"),
    MUSIC_POP("pop", "music"),
    MUSIC_INDIE("indie", "music"),
    MUSIC_ROCK("rock", "music"),
    MUSIC_ELECTRONIC("electronic", "music"),
    MUSIC_CHILL("chill", "music"),
    MUSIC_PARTY("party", "music"),
    MUSIC_LATIN("latin", "music"),
    MUSIC_ROMANCE("romance", "music"),
    MUSIC_METAL("metal", "music"),
    MUSIC_JAZZ("jazz", "music"),
    MUSIC_CLASSICAL("classical", "music"),
    MUSIC_ACOUSTIC("acoustic", "music"),
    MUSIC_SOUL("soul", "music"),
    MUSIC_INSTRUMENTAL("instrumental", "music"),
    MUSIC_PUNK("punk", "music"),
    MUSIC_BLUES("blues", "music"),
    MUSIC_ALTERNATIVE("alternative", "music"),
    MUSIC_AFRO("afro", "music"),
    MUSIC_FUNK("funk", "music"),
    MUSIC_COUNTRY("country", "music"),

    BUSINESS("business", "business"),
    CRYPTO("crypto", "business"),
    INVESTING("investing", "business"),
    INDUSTRIES("industries", "business"),
    STARTUP("start-up", "business"),
    MARKETING("marketing", "business"),

    LAW("law", "law"),
    CRIMINAL_LAW("criminal_law", "law"),
    INTERNATIONAL_LAW("international_law", "law"),
    FAMILY_HEALTH_LAW("family-health law", "law"),

    POLITICS("politics", ""),

    HUMOR("humor", ""),

    GOSSIP("gossip", ""),

    SCIENCE("science", ""),

    SHOPPING("shopping", "");

    private String description;
    private String membership;

    EnumCatgories(String description, String membership) {
        this.description = description;
        this.membership = membership;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }
}
