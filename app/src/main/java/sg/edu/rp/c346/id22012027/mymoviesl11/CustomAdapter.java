package sg.edu.rp.c346.id22012027.mymoviesl11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Movies> {
    Context context;
    ArrayList<Movies> al;
    int resource;

    public CustomAdapter(Context context, int resource, ArrayList<Movies> al) {
        super(context, resource, al);
        this.context = context;
        this.resource = resource;
        this.al = al;
    }

    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(resource, parent, false);

        TextView textViewMovieTitle2= rowView.findViewById(R.id.textViewMovieTitle2);
        TextView textViewGenre2= rowView.findViewById(R.id.textViewGenre2);
        TextView textViewYear2= rowView.findViewById(R.id.textViewYear2);
        ImageView imageViewRating = rowView.findViewById(R.id.imageViewRating);

        Movies currentVersion = al.get(position);

        textViewMovieTitle2.setText(currentVersion.getTitle());
        textViewGenre2.setText(currentVersion.getGenre());
        textViewYear2.setText(String.valueOf(currentVersion.getYear()));

        if(currentVersion.getRating().equalsIgnoreCase("G")){
            imageViewRating.setImageResource(R.drawable.rating_g);
        }
        else if(currentVersion.getRating().equalsIgnoreCase("PG")){
            imageViewRating.setImageResource(R.drawable.rating_pg);
        }
        else if (currentVersion.getRating().equalsIgnoreCase("PG13")) {
            imageViewRating.setImageResource(R.drawable.rating_pg13);
        }
        else if(currentVersion.getRating().equalsIgnoreCase("NC16")){
            imageViewRating.setImageResource(R.drawable.rating_nc16);
        }
        else if(currentVersion.getRating().equalsIgnoreCase("M18")){
            imageViewRating.setImageResource(R.drawable.rating_m18);
        }
        else if(currentVersion.getRating().equalsIgnoreCase("R21")){
            imageViewRating.setImageResource(R.drawable.rating_r21);
        }
        return rowView;
    }
}
