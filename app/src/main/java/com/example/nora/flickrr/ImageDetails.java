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
    private String imageTitle, owner, secret, server;
    long id;
    int farm;
    public ImageDetails(Uri imagePath, long id){
        this.imagePath= imagePath;
        this.id= id;
    }
    public ImageDetails(long id, String owner, String secret, String server, int farm){
        this.id= id;
        this.owner= owner;
        this.secret= secret;
        this.server= server;
        this.farm= farm;
    }
    public ImageDetails(Uri imagePath, String imageTitle, long id){
        this.imagePath= imagePath;
        this.imageTitle= imageTitle;
        this.id= id;

    }


    public Uri getImagePath(){
        return imagePath;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public int getFarm() {
        return farm;
    }

    public String getOwner() {
        return owner;
    }

    public String getSecret() {
        return secret;
    }
    public String getServer(){
        return server;
    }
    public Uri formURI(){
        imagePath= Uri.parse("https://farm{" + getFarm()+"-"+ getId() +"}.staticflickr.com/{" + getServer() + "-"
                + getSecret() + "}/{" + getId() +"}_{o-" + getSecret() +"}_o.(jpg|gif|png)");
        return imagePath;
    }

    public long getId() {
        return id;
    }
}