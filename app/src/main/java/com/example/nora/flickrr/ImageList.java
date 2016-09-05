package com.example.nora.flickrr;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import Adapters.ImageAdapter;
import Loaders.ImageLoader;

/**
 * Created by Nora on 05/09/2016.
 */

public class ImageList extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<ImageDetails>>{
    private ListView list;
    private ImageAdapter image_adapter;
    private View progressBar;

    String url;
    String title;
    SearchView search_view;
    String query;
    public void setQuery(String q){
        query= q;
    }    @Override
    public void onLoaderReset(Loader<List<ImageDetails>> loader) {
        image_adapter.clear();
    }

    @Override
    public void onLoadFinished(Loader<List<ImageDetails>> loader, List<ImageDetails> data) {
        progressBar.setVisibility(View.GONE);
        image_adapter.clear();
        if (data != null && !data.isEmpty()) {
            image_adapter.addAll(data);
        }
    }

    @Override
    public Loader<List<ImageDetails>> onCreateLoader(int i, Bundle bundle) {
        return new ImageLoader(this, URLs.SEARCH_PHOTOS_QUERY + query + URLs.FORMAT);
    }

    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.images_list);
        image_adapter = new ImageAdapter(this, new ArrayList<ImageDetails>());
        list.setAdapter(image_adapter);
        progressBar = (View) findViewById(R.id.loading_layout);
        search_view = (SearchView)findViewById(R.id.search_view);
        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                query = s;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                query = s;
                return false;
            }
        });

    }
}