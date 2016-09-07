package Adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.nora.flickrr.ImageDetails;
import com.example.nora.flickrr.OwnerDetails;
import com.example.nora.flickrr.R;
import com.example.nora.flickrr.URLs;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Nora on 07/09/2016.
 */

public class OwnerAdapter extends ArrayAdapter<OwnerDetails> {

    @Override
    public int getPosition(OwnerDetails item) {
        return super.getPosition(item);
    }


    public OwnerAdapter(Context context, List<OwnerDetails> images){
        super(context, 0, images);

    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        OwnerDetails owner= getItem(i);
        if(view == null){
            Context context = viewGroup.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            view= inflater.inflate(R.layout.grid_items, null);
        }
        ImageView imageView= (ImageView)view.findViewById(R.id.image);
        Uri x= owner.formOwnerImagesURI();
        Picasso.with(viewGroup.getContext()).load(owner.formOwnerImagesURI()).into(imageView);
        return view;
    }
}


