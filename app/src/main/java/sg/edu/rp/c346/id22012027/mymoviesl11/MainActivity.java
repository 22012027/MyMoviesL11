package sg.edu.rp.c346.id22012027.mymoviesl11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textViewMovieTitle;
    EditText editTextMovieTitle;
    TextView textViewGenre;
    EditText editTextGenre;
    TextView textViewYear;
    EditText editTextYear;
    TextView textViewRating;
    Spinner spinner;
    Button buttonInsert;
    Button buttonShowList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewMovieTitle= findViewById(R.id.textViewMovieTitle);
        editTextMovieTitle= findViewById(R.id.editTextMovieTitle);
        textViewGenre= findViewById(R.id.textViewGenre);
        editTextGenre= findViewById(R.id.editTextGenre);
        textViewYear= findViewById(R.id.textViewYear);
        editTextYear= findViewById(R.id.editTextYear);
        textViewRating= findViewById(R.id.textViewRating);
        spinner= findViewById(R.id.spinner);
        textViewMovieTitle= findViewById(R.id.textViewMovieTitle);
        buttonInsert= findViewById(R.id.buttonInsert);
        buttonShowList= findViewById(R.id.buttonShowList);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        textViewRating.setText("G is Selected");
                        break;
                    case 1:
                        textViewRating.setText("PG is Selected");
                        break;
                    case 2:
                        textViewRating.setText("PG13 is Selected");
                        break;
                    case 3:
                        textViewRating.setText("NC16 is Selected");
                        break;
                    case 4:
                        textViewRating.setText("M18 is Selected");
                        break;
                    case 5:
                        textViewRating.setText("R21 is Selected");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);

                db.insertMovies(editTextMovieTitle.getText().toString(), editTextGenre.getText().toString(), Integer.parseInt(editTextYear.getText().toString()), String.valueOf(spinner));
                db.close();
            }
        });


        buttonShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, MovieList.class);
                startActivity(intent);
            }
        });
    }
}