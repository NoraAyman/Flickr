package com.example.nora.flickrr;

/**
 * Created by Nora on 05/09/2016.
 */

public class URLs {
    public static final String API_KEY = "api_key=2591413f0cceaf433dff5018e4ef3228&";
    public static final String SEARCH_URL = "https://api.flickr.com/services/rest/?method=flickr.photos.search&" + API_KEY;
    public static final String SEARCH_PHOTOS_QUERY = SEARCH_URL + "text=";
    public static final String FORMAT = "&format=json&nojsoncallback=1";
}
