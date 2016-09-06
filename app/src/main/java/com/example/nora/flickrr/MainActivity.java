package com.example.nora.flickrr;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import Adapters.ImageAdapter;
import Loaders.ImageLoader;

public class MainActivity extends AppCompatActivity/* implements LoaderManager.LoaderCallbacks<List<ImageDetails>>*/{

    SearchView search_view;
    ImageAdapter image_adapter;
    private LoaderManager loader_manager;
    ListView list;
    static String query= "";

    private LoaderManager.LoaderCallbacks<List<ImageDetails>> imageListener= new LoaderManager.LoaderCallbacks<List<ImageDetails>>(){
    @Override
    public Loader<List<ImageDetails>> onCreateLoader(int i, Bundle bundle) {

        return new ImageLoader(getApplicationContext(), URLs.SEARCH_PHOTOS_QUERY + getQuery() + URLs.FORMAT);
    }

    @Override
    public void onLoadFinished(Loader<List<ImageDetails>> loader, List<ImageDetails> data) {
        //progressBar.setVisibility(View.GONE);
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
        search_view= (SearchView) findViewById(R.id.search_view);
        list = (ListView) findViewById(R.id.images_list);
        image_adapter = new ImageAdapter(this, new ArrayList<ImageDetails>());
        list.setAdapter(image_adapter);
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
