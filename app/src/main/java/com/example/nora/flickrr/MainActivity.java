package com.example.nora.flickrr;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Adapters.ImageAdapter;
import Loaders.ImageLoader;

public class MainActivity extends AppCompatActivity/* implements LoaderManager.LoaderCallbacks<List<ImageDetails>>*/{

    SearchView search_view;
    ImageAdapter image_adapter;
    private LoaderManager loader_manager;
    GridView list;
    static String query= "";
    boolean state= false;
    ImageButton saveImage;
    ImageView image;
    ImageDetails currentImage;
    private View progressBar;

    private LoaderManager.LoaderCallbacks<List<ImageDetails>> imageListener= new LoaderManager.LoaderCallbacks<List<ImageDetails>>(){
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
        setContentView(R.layout.activity_main);
        search_view = (SearchView) findViewById(R.id.search_view);
        list = (GridView) findViewById(R.id.images_list);
        image_adapter = new ImageAdapter(this, new ArrayList<ImageDetails>());
        list.setAdapter(image_adapter);
        saveImage = (ImageButton) findViewById(R.id.saveImage);
        image = (ImageView) findViewById(R.id.viewImage);
        progressBar = (View) findViewById(R.id.loading_indicator);

        saveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                currentImage= image_adapter.getItem(i);
                if(image.getVisibility() == View.GONE){
                    image.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load(currentImage.formURIImageView()).into(image);
                    saveImage.setVisibility(View.VISIBLE);
                }
                else{
                    image.setVisibility(View.GONE);
                    saveImage.setVisibility(View.GONE);

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


    }
    public String getQuery(){
        return query;
    }
    public void setQuery(String query){
        this.query= query;
    }
}
