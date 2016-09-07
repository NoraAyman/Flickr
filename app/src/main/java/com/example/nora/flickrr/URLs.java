package com.example.nora.flickrr;

/**
 * Created by Nora on 05/09/2016.
 */

public class URLs {
    public static final String API_KEY = "api_key=bfbe420ec9d379203ee09f8b8ec5a44f&";
    public static final String SEARCH_URL = "https://api.flickr.com/services/rest/?method=flickr.photos.search&" + API_KEY;
    public static final String SEARCH_PHOTOS_QUERY = SEARCH_URL + "text=";
    public static final String FORMAT = "&format=json&nojsoncallback=1";
    public static final String PHOTO_OWNER = "https://www.flickr.com/photos/";
    public static final String OWNER_PHOTOS = "https://api.flickr.com/services/rest/?method=flickr.people.getPhotos&" + API_KEY;
    public static final String USER_ID ="user_id=";




}
