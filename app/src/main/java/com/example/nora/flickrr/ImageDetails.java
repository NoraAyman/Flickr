package com.example.nora.flickrr;

import android.net.Uri;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nora on 05/09/2016.
 */

public class ImageDetails {
    private Uri imagePath;
    private String imageTitle, imageBelongsTo;
    long id;
    public ImageDetails(Uri imagePath, long id){
        this.imagePath= imagePath;
        this.id= id;
    }
    public ImageDetails(Uri imagePath, String imageTitle, long id){
        this.imagePath= imagePath;
        this.imageTitle= imageTitle;
        this.id= id;

    }
    public ImageDetails(Uri imagePath, String imageTitle, String getImageBelongsTo, long id){
        this.imagePath= imagePath;
        this.imageTitle= imageTitle;
        this.imageBelongsTo= imageBelongsTo;
        this.id= id;

    }
    public ImageDetails(JSONObject movieObject) throws JSONException{
        id = movieObject.getInt("id");
        //String image_path = movieObject.getString("title");
        //imagePath= Uri.parse(image_path);
        imageTitle = movieObject.getString("title");
        Log.e("cgvhjkknjbvcvbn", imageTitle);

    }

    public Uri getImagePath(){
        return imagePath;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public String getImageBelongsTo() {
        return imageBelongsTo;
    }

    public long getId() {
        return id;
    }
}
