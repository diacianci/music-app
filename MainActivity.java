package com.example.diaci.tunami;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.media.MediaPlayer;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener {

    Spinner spinner;
    CursorAdapter adapter;
    Tuning tuning;
    MyDBHandler dbHandler;
    public Button enterTuningButton;

    public MediaPlayer notePlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nextActivityButtonInit();

        setMediaPlayers();
        notePlayer = MediaPlayer.create(this, R.raw.note_c);

        dbHandler = new MyDBHandler(this);
        tuning = new Tuning("Standard", "E", "B", "G", "D", "A", "E");
        dbHandler.addTuning(tuning);
        tuning = new Tuning("Drop D", "E", "B", "G", "D", "A", "D");
        dbHandler.addTuning(tuning);

        tuning = dbHandler.getTuning("Standard");

        // Get all the tunings available from the database
        ArrayList<String> tuningList = dbHandler.getAllTunings();
        spinner = (Spinner)findViewById(R.id.spinner);

        // Convert the array list of tunings into a form compatible with spinners
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_layout, R.id.spinnerText, tuningList);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(MainActivity.this);
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (this.spinner != null && position >= 0) {
            String selectedTuning = parent.getItemAtPosition(position).toString();

            Toast.makeText(getBaseContext(), selectedTuning + " is selected", Toast.LENGTH_LONG).show();

            //Get a single tuning from the database for its string values to be displayed

            tuning = dbHandler.getTuning(selectedTuning);

        Button lowEButton = (Button) findViewById(R.id.LowEButton);
        Button aButton = (Button) findViewById(R.id.AButton);
        Button dButton = (Button) findViewById(R.id.DButton);
        Button gButton = (Button) findViewById(R.id.GButton);
        Button bButton = (Button) findViewById(R.id.BButton);
        Button highEButton = (Button) findViewById(R.id.HighEButton);

        lowEButton.setText(tuning.getLowEString());
        aButton.setText(tuning.getaString());
        dButton.setText(tuning.getdString());
        gButton.setText(tuning.getgString());
        bButton.setText(tuning.getbString());
        highEButton.setText(tuning.getHighEString());

            //When a text view is selected from the drop down, we query the database for
            //the tuning with that tuning name and store the result as a tuning object.
            //Then we replace the text of each button with the strings we get from the tuning.
            //Depending on the letter in the button label, that pitch will play when clicked.
        }
    }

    public void nextActivityButtonInit()
    {
        enterTuningButton = (Button)findViewById(R.id.newTuningButton);

        // Sets the intent of the button click to take us from MainActivity to enterTuning activity
        enterTuningButton.setOnClickListener((new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent toy = new Intent(MainActivity.this, enterTuning.class);
                startActivity(toy);
            }
        }));
    }

    public void setMediaPlayers()
    {
//        cNote = MediaPlayer.create(this, R.raw.note_c);
//        cSharpNote = MediaPlayer.create(this, R.raw.note_c_sharp);
//        dNote = MediaPlayer.create(this, R.raw.note_d);

        //TODO would like to get this to play every time we click the button
        // Might need to pass around a single media player instead
    }

    public void onLowEBtnClicked(View v)
    {
        Button lowEButton = (Button) v;
        playSound(lowEButton.getText().toString());

    }

    public void onABtnClicked(View v)
    {
        Button aButton = (Button) v;
        playSound(aButton.getText().toString());

    }

    public void onDBtnClicked(View v)
    {
        Button dButton = (Button) v;
        playSound(dButton.getText().toString());

    }

    public void onGBtnClicked(View v)
    {
        Button gButton = (Button) v;
        playSound(gButton.getText().toString());

    }

    public void onBButtonClicked(View v)
    {
        Button bButton = (Button) v;
        playSound(bButton.getText().toString());

    }

    public void onHighEButtonClicked(View v)
    {
        Button highEButton = (Button) v;
        playSound(highEButton.getText().toString());

    }

    public void playSound(String note)
    {
        //cNote.start();
        if (note.equals("C") || note.equals("B#"))
        {
            if (notePlayer.isPlaying())
            {
                notePlayer.stop();
            }
            notePlayer.reset();
            notePlayer = MediaPlayer.create(this, R.raw.note_c);
            notePlayer.start();
        }
        else if (note.equals("C#") || note.equals("Db"))
        {
            if (notePlayer.isPlaying())
            {
                notePlayer.stop();
            }
            notePlayer.reset();
            notePlayer = MediaPlayer.create(this, R.raw.note_c_sharp);
            notePlayer.start();
        }
        else if (note.equals("D"))
        {
            if (notePlayer.isPlaying())
            {
                notePlayer.stop();
            }
            notePlayer.reset();
            notePlayer = MediaPlayer.create(this, R.raw.note_d);
            notePlayer.start();        }
        else if (note.equals("D#") || note.equals("Eb"))
        {
            if (notePlayer.isPlaying())
            {
                notePlayer.stop();
            }
            notePlayer.reset();
            notePlayer = MediaPlayer.create(this, R.raw.note_d_sharp);
            notePlayer.start();
        }
        else if (note.equals("E") || note.equals("Fb"))
        {
            if (notePlayer.isPlaying())
            {
                notePlayer.stop();
            }
            notePlayer.reset();
            notePlayer = MediaPlayer.create(this, R.raw.note_e);
            notePlayer.start();
        }
        else if (note.equals("F") || note.equals("E#"))
        {
            if (notePlayer.isPlaying())
            {
                notePlayer.stop();
            }
            notePlayer.reset();
            notePlayer = MediaPlayer.create(this, R.raw.note_f);
            notePlayer.start();
        }
        else if (note.equals("F#") || note.equals("Gb"))
        {
            if (notePlayer.isPlaying())
            {
                notePlayer.stop();
            }
            notePlayer.reset();
            notePlayer = MediaPlayer.create(this, R.raw.note_f_sharp);
            notePlayer.start();
        }
        else if (note.equals("G"))
        {
            if (notePlayer.isPlaying())
            {
                notePlayer.stop();
            }
            notePlayer.reset();
            notePlayer = MediaPlayer.create(this, R.raw.note_g);
            notePlayer.start();
        }
        else if (note.equals("G#") || note.equals("Ab"))
        {
            if (notePlayer.isPlaying())
            {
                notePlayer.stop();
            }
            notePlayer.reset();
            notePlayer = MediaPlayer.create(this, R.raw.note_g_sharp);
            notePlayer.start();
        }
        else if (note.equals("A"))
        {
            if (notePlayer.isPlaying())
            {
                notePlayer.stop();
            }
            notePlayer.reset();
            notePlayer = MediaPlayer.create(this, R.raw.note_a);
            notePlayer.start();
        }
        else if (note.equals("A#") || note.equals("Bb"))
        {
            if (notePlayer.isPlaying())
            {
                notePlayer.stop();
            }
            notePlayer.reset();
            notePlayer = MediaPlayer.create(this, R.raw.note_a_sharp);
            notePlayer.start();
        }
        else if (note.equals("B") || note.equals("Cb"))
        {
            if (notePlayer.isPlaying())
            {
                notePlayer.stop();
            }
            notePlayer.reset();
            notePlayer = MediaPlayer.create(this, R.raw.note_b);
            notePlayer.start();
        }

    }

}
