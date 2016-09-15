package com.example.nora.flickrr;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.SignInButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import Adapters.ImageAdapter;
import Loaders.ImageLoader;

public class MainActivity extends AppCompatActivity/* implements LoaderManager.LoaderCallbacks<List<ImageDetails>>*/ {

    RelativeLayout login;
    SearchView search_view;
    ImageAdapter image_adapter;
    private LoaderManager loader_manager;
    GridView list;
    static String query = "";
    ImageView image;
    static ImageDetails currentImage;
    View progressBar;
    Button owner;
    LoginButton facebookLogIn;
    SignInButton googleLogIn;
    static int x= 0;
    private LoaderManager.LoaderCallbacks<List<ImageDetails>> imageListener = new LoaderManager.LoaderCallbacks<List<ImageDetails>>() {
        @Override
        public Loader<List<ImageDetails>> onCreateLoader(int i, Bundle bundle) {

            return new ImageLoader(getApplicationContext(), URLs.SEARCH_PHOTOS_QUERY + getQuery() + URLs.FORMAT);
        }

        @Override
        public void onLoadFinished(Loader<List<ImageDetails>> loader, List<ImageDetails> data) {
            progressBar.setVisibility(View.GONE);
            image_adapter.clear();

            if (data != null && !data.isEmpty()) {
                image_adapter.addAll(data);
                image_adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onLoaderReset(Loader<List<ImageDetails>> loader) {
            image_adapter.clear();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        login= (RelativeLayout)findViewById(R.id.login_layout);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());
        if (AccessToken.getCurrentAccessToken() == null && x == 0) {
            setContentView(R.layout.login);
            facebookLogIn = (LoginButton) findViewById(R.id.fb_login_button);
            googleLogIn = (SignInButton) findViewById(R.id.google_login_button);
            facebookLogIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent openFBLoginPage = new Intent(getApplicationContext(), FacebookLogin.class);
                    openFBLoginPage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(openFBLoginPage);
                }
            });

        googleLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openGoogleLoginPage = new Intent(getApplicationContext(), GoogleLogin.class);
                startActivity(openGoogleLoginPage);
            }
        });
    }

        else{
            setContentView(R.layout.activity_main);
            search_view = (SearchView) findViewById(R.id.search_view);
            list = (GridView) findViewById(R.id.images_list);
            image_adapter = new ImageAdapter(this, new ArrayList<ImageDetails>());

            list.setAdapter(image_adapter);
            progressBar = (View) findViewById(R.id.loading_indicator);
            image = (ImageView) findViewById(R.id.viewImage);
            owner= (Button)findViewById(R.id.getOwner);
            owner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent openOwner= new Intent(getApplicationContext(), OpenOwner.class);
                    startActivity(openOwner);
                }
            });
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    currentImage= image_adapter.getItem(i);

                    if(image.getVisibility() == View.GONE){
                        image.setVisibility(View.VISIBLE);
                        Picasso.with(getApplicationContext()).load(currentImage.formURIImageView()).into(image);
                        owner.setVisibility(View.VISIBLE);
                        OpenOwner object= new OpenOwner();
                        object.setCurrentImage(currentImage.getOwner());

                    }
                    else{
                        image.setVisibility(View.GONE);
                        owner.setVisibility(View.GONE);

                    }
                }
            });
            loader_manager = getLoaderManager();
            search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String s) {
                    setQuery(s);
                    loader_manager.initLoader(0, null, imageListener);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    setQuery(s);
                    loader_manager.restartLoader(0, null, imageListener);

                    return false;
                }
            });


        }}
    public String getQuery(){
        return query;
    }
    public void setQuery(String query){
        this.query= query;
    }
}
