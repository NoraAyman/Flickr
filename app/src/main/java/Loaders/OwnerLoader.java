package Loaders;

import android.content.Context;

import com.example.nora.flickrr.ImageDetails;
import com.example.nora.flickrr.OwnerDetails;
import com.example.nora.flickrr.QueryUtils;

import java.util.List;

/**
 * Created by Nora on 07/09/2016.
 */

public class OwnerLoader extends android.content.AsyncTaskLoader<List<OwnerDetails>>{
    private String url;
    public OwnerLoader(Context context, String url) {
        super(context);
        this.url= url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<OwnerDetails> loadInBackground() {
        if (url == null ) {
            return null;
        }
        List<OwnerDetails> result = QueryUtils.fetchOwnerData(url);

        return result;
    }
}