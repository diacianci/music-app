package com.example.diaci.tunami;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class enterTuning extends AppCompatActivity {

    MyDBHandler dbHandler;
    Tuning tuning;

    EditText tuningNameEditText;
    EditText highEStringEditText;
    EditText bStringEditText;
    EditText gStringEditText;
    EditText dStringEditText;
    EditText aStringEditText;
    EditText lowEStringEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_tuning);
        dbHandler = new MyDBHandler(this);

        tuningNameEditText = (EditText)findViewById(R.id.tuningNameEditText);
        highEStringEditText = (EditText)findViewById(R.id.highEEditText);
        bStringEditText = (EditText)findViewById(R.id.bEditText);
        gStringEditText = (EditText)findViewById(R.id.gEditText);
        dStringEditText = (EditText)findViewById(R.id.dEditText);
        aStringEditText = (EditText)findViewById(R.id.aEditText);
        lowEStringEditText = (EditText)findViewById(R.id.lowEEditText);

    }

    public void onAddTuningBtnClicked(View v)
    {
        //TODO Not sure if tunings are actually getting added or if they're getting added to the wrong place

        String tuningName = tuningNameEditText.getText().toString();
        String highE = highEStringEditText.getText().toString();
        String b = bStringEditText.getText().toString();
        String g = gStringEditText.getText().toString();
        String d = dStringEditText.getText().toString();
        String a = aStringEditText.getText().toString();
        String lowE = lowEStringEditText.getText().toString();

        tuning = new Tuning(tuningName, highE, b, g, d, a, lowE);
        dbHandler.addTuning(tuning);

        Toast.makeText(getBaseContext(), tuningName + " has been added", Toast.LENGTH_LONG).show();


    }

    public void onClearStringsBtnClicked(View v)
    {

        tuningNameEditText.setText("");
        highEStringEditText.setText("");
        bStringEditText.setText("");
        gStringEditText.setText("");
        dStringEditText.setText("");
        aStringEditText.setText("");
        lowEStringEditText.setText("");

    }
}
