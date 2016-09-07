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
    public ImageDetails(long id, String owner){
        this.id= id;
        this.owner= owner;
    }
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
    public ImageDetails(){
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
        imagePath= Uri.parse("https://farm" + getFarm() +".staticflickr.com/" + getServer() +
                "/" + getId() +"_" + getSecret() +"_t.jpg");
        return imagePath;
    }
    public Uri formURIImageView(){

        return Uri.parse("https://farm" + getFarm() +".staticflickr.com/" + getServer() +
                "/" + getId() +"_" + getSecret() +"_z.jpg");
    }

    public long getId() {
        return id;
    }
}
