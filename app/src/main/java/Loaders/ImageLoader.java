package Loaders;

import android.content.Context;

import java.util.List;
import com.example.nora.flickrr.ImageDetails;
import com.example.nora.flickrr.QueryUtils;

/**
 * Created by Nora on 05/09/2016.
 */

public class ImageLoader extends android.content.AsyncTaskLoader<List<ImageDetails>>{
    private String url;
    public ImageLoader(Context context, String url) {
        super(context);
        this.url= url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<ImageDetails> loadInBackground() {
        if (url == null ) {
            return null;
        }
        List<ImageDetails> result = QueryUtils.fetchData(url);

        return result;
    }
}
