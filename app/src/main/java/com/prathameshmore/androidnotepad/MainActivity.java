package com.prathameshmore.androidnotepad;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText etText;
    private Button btnSave;
    private Button btnRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etText = (EditText) findViewById(R.id.etText);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnRead = (Button) findViewById(R.id.btnRead);

        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    FileOutputStream fileOutputStream = openFileOutput("myfile.txt", MODE_PRIVATE);
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                    outputStreamWriter.write(etText.getText().toString());
                    outputStreamWriter.close();
                    Toast.makeText(MainActivity.this, "File saved successfully", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fileInputStream = openFileInput("myfile.txt");
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                    char[] tempInput = new char[500];
                    String str = "";
                    int charRead;
                    while ((charRead=inputStreamReader.read(tempInput))>0) {
                        // char to string conversion
                        String readString=String.copyValueOf(tempInput,0,charRead);
                        str +=readString;
                    }
                    inputStreamReader.close();
                    Toast.makeText(getBaseContext(), str,Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
