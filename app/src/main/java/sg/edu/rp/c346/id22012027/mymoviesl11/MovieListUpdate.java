package sg.edu.rp.c346.id22012027.mymoviesl11;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MovieListUpdate extends AppCompatActivity {

    EditText editTextMovieTitle;
    EditText editTextGenre;
    EditText editTextYear;
    TextView textViewRating;
    Spinner spinner;
    Button buttonUpdate;
    Button buttonDelete;
    Button buttonCancel;
    Movies Movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movielistupdate);

        editTextMovieTitle= findViewById(R.id.editTextMovieTitle);
        editTextGenre= findViewById(R.id.editTextGenre);
        editTextYear= findViewById(R.id.editTextYear);
        textViewRating= findViewById(R.id.textViewRating);
        spinner= findViewById(R.id.spinner);
        buttonUpdate= findViewById(R.id.buttonUpdate);
        buttonDelete= findViewById(R.id.buttonDelete);
        buttonCancel= findViewById(R.id.buttonCancel);

        Intent i = getIntent();
        Movies= (Movies) i.getSerializableExtra("movieId");

        editTextMovieTitle.setText(Movies.getTitle());
        editTextGenre.setText(Movies.getGenre());
        editTextYear.setText(String.valueOf(Movies.getYear()));

        String rating = Movies.getRating();

        if(rating == "G"){
            textViewRating.setText("G is Selected");
        }
        else if(rating == "PG"){
            textViewRating.setText("PG is Selected");
        }
        else if(rating == "PG13"){
            textViewRating.setText("PG13 is Selected");
        }
        else if(rating == "NC16"){
            textViewRating.setText("NC16 is Selected");
        }
        else if(rating == "M18"){
            textViewRating.setText("M18 is Selected");
        }
        else if (rating == "R21"){
            textViewRating.setText("R21 is Selected");
        }

        buttonUpdate .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh= new DBHelper(MovieListUpdate.this);

                Movies.setTitle(editTextMovieTitle.getText().toString());
                Movies.setGenre(editTextGenre.getText().toString());
                Movies.setYear(Integer.parseInt(editTextYear.getText().toString()));

                if(dbh.updateMovies(Movies) >0){
                    Toast.makeText(MovieListUpdate.this, "Updated", Toast.LENGTH_LONG).show();
                    setResult(RESULT_OK);
                }else{
                    Toast.makeText(MovieListUpdate.this,"Failed", Toast.LENGTH_LONG).show();
                }
                finish();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh= new DBHelper(MovieListUpdate.this);

                AlertDialog.Builder myBuilder= new AlertDialog.Builder(MovieListUpdate.this);

                myBuilder.setTitle("Danger");
                myBuilder.setMessage("are you sure you want to delete" + getTitle());
                myBuilder.setCancelable(false);

                myBuilder.setPositiveButton("delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MovieListUpdate.this, "you have selected delete", Toast.LENGTH_SHORT).show();
                    }
                });

                myBuilder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MovieListUpdate.this, "you have selected cancel", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog myDialog= myBuilder.create();
                myDialog.show();

                dbh.deleteMovies(Movies.getId());

                finish();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MovieListUpdate.this, MovieList.class);
                startActivity(intent);
            }
        });
    }
}
