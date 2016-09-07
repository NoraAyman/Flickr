package com.example.nora.flickrr;

import android.app.LoaderManager;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Adapters.ImageAdapter;
import Adapters.OwnerAdapter;
import Loaders.OwnerLoader;

/**
 * Created by Nora on 07/09/2016.
 */

public class OpenOwner extends AppCompatActivity {

    OwnerAdapter owner_adapter;
    SearchView search_view;
    private LoaderManager loader_manager;
    GridView list;
    static String query= "";
    boolean state= false;
    ImageView image, userImage;
    ImageDetails currentImage;
    View progressBar;
    TextView userName;
    static String currentImageOwner="";
    public void setCurrentImage(String currentImageOwner){
        this.currentImageOwner= currentImageOwner;
    }
    public String getCurrentOwner(){
        return  currentImageOwner;
    }

    private LoaderManager.LoaderCallbacks<List<OwnerDetails>> ownerListner= new LoaderManager.LoaderCallbacks<List<OwnerDetails>>() {
        @Override
        public Loader<List<OwnerDetails>> onCreateLoader(int i, Bundle bundle) {
            return new OwnerLoader(getApplicationContext(), URLs.OWNER_PHOTOS + URLs.USER_ID + getCurrentOwner() + URLs.FORMAT);

        }

        @Override
        public void onLoadFinished(Loader<List<OwnerDetails>> loader, List<OwnerDetails> ownerDetails) {
            progressBar.setVisibility(View.GONE);
            owner_adapter.clear();
            if (ownerDetails != null && !ownerDetails.isEmpty()) {
                owner_adapter.addAll(ownerDetails);
                owner_adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onLoaderReset(Loader<List<OwnerDetails>> loader) {
            owner_adapter.clear();

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search_view = (SearchView) findViewById(R.id.search_view);
        search_view.setVisibility(View.GONE);
        list = (GridView) findViewById(R.id.images_list);
        owner_adapter = new OwnerAdapter(this, new ArrayList<OwnerDetails>());
        list.setAdapter(owner_adapter);
        progressBar = (View) findViewById(R.id.loading_indicator);
        image = (ImageView) findViewById(R.id.viewImage);
        loader_manager = getLoaderManager();
        loader_manager.initLoader(0, null, ownerListner);



    }
}
