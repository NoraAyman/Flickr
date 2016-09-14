package com.example.nora.flickrr;

import android.net.Uri;

/**
 * Created by Nora on 07/09/2016.
 */

public class OwnerDetails {
    private String owner, secret, server;
    private long id;
    private int farm;

    public OwnerDetails(long id, String owner, String server, String secret, int farm){
        this.id= id;
        this.owner= owner;
        this.secret= secret;
        this.server= server;
        this.farm= farm;
    }
    public long getID(){
        return id;
    }

    public String getSecret() {
        return secret;
    }

    public String getOwner() {
        return owner;
    }

    public int getFarm() {
        return farm;
    }

    public String getServer() {
        return server;
    }
    public Uri formOwnerImagesURI(){
       // return Uri.parse("https://www.flickr.com/photos/" + getOwner() + "/" + getID());
        return Uri.parse("https://farm" + getFarm() +".staticflickr.com/" + getServer() +
                "/" + getID() +"_" + getSecret() +"_t.jpg");
    }

    public Uri formURIImageView(){

        return Uri.parse("https://farm" + getFarm() +".staticflickr.com/" + getServer() +
                "/" + getID() +"_" + getSecret() +"_z.jpg");
    }
}
