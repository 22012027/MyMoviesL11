package sg.edu.rp.c346.id22012027.mymoviesl11;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieList extends AppCompatActivity {
    ListView listViewMovies;
    Button buttonBack;
    ToggleButton toggleButtonShowMovies;
    ArrayAdapter aaMovies;
    CustomAdapter ca;
    CustomAdapter caFilter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movielist);

        listViewMovies= findViewById(R.id.listViewMovies);
        buttonBack= findViewById(R.id.buttonBack);
        toggleButtonShowMovies = findViewById(R.id.toggleButtonShowMovies);

        DBHelper db= new DBHelper(MovieList.this);

        ArrayList<Movies> Movies = db.getMovies();

        db.close();

        ca= new CustomAdapter(this, R.layout.row, Movies);

        listViewMovies.setAdapter(ca);

        toggleButtonShowMovies.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ArrayList<Movies> filteredList = new ArrayList<>();
                    for (int i = 0; i < db.getMovies().size(); i++) {
                        if (Movies.get(i).getRating() == "G") {
                            filteredList.add(Movies.get(i));
                        }
                        caFilter= new CustomAdapter(MovieList.this, R.layout.row, filteredList);
                        listViewMovies.setAdapter(caFilter);
                    }
                } else {
                    listViewMovies.setAdapter(ca);
                }
            }
        });

        listViewMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long identity) {
                Movies movies= Movies.get(position);
                Intent i= new Intent(MovieList.this, MovieListUpdate.class);
                i.putExtra("movie", Movies);
                startActivity(i);

            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovieList.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        DBHelper db= new DBHelper(MovieList.this);

        ArrayList<Movies> Movies = db.getMovies();

        db.close();

        Movies.clear();
        Movies.addAll(db.getMovies());

        ca= new CustomAdapter(this, R.layout.row, Movies);
        listViewMovies.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
}
