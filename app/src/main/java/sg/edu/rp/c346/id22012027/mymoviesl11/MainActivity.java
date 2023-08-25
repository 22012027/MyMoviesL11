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
import android.widget.Toast;

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

        buttonInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);

                db.insertMovies(editTextMovieTitle.getText().toString(), editTextGenre.getText().toString(), Integer.parseInt(editTextYear.getText().toString()),spinner.getSelectedItem().toString());
                Toast.makeText(MainActivity.this, "Inserted successfully", Toast.LENGTH_SHORT).show();
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