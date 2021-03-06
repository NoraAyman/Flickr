package Adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.nora.flickrr.ImageDetails;
import com.example.nora.flickrr.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Nora on 05/09/2016.
 */

public class ImageAdapter extends ArrayAdapter<ImageDetails> {

    @Override
    public int getPosition(ImageDetails item) {
        return super.getPosition(item);
    }


    public ImageAdapter(Context context, List<ImageDetails> images){
        super(context, 0, images);

    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageDetails image= getItem(i);
        if(view == null){
            Context context = viewGroup.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            view= inflater.inflate(R.layout.grid_items, null);
        }
        ImageView imageView= (ImageView)view.findViewById(R.id.image);
       // Uri x= image.formURI();
        Picasso.with(viewGroup.getContext()).load(image.formURI()).into(imageView);
        return view;
    }
}
