package com.example.a642j;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button save_b;
    Button delete_b;
    EditText editText;

    boolean isPersistent = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    public void initViews() {
        editText = findViewById(R.id.et_btn);
        save_b = findViewById(R.id.save_file_btn);
        delete_b = findViewById(R.id.delete_file_btn);

        save_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString().trim();
                createFile(text);
            }
        });


        delete_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString().trim();
                deleteThisFile(text);
            }
        });

    }

    public void createFile(String text) {
        String fileName = text + ".txt";
        File file;
        if (isPersistent) {
            file = new File(getExternalFilesDir(null), fileName);
        } else {
            file = new File(getCacheDir(), fileName);
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
                Toast.makeText(this, "File created", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(this, "File NOT created", Toast.LENGTH_LONG).show();

            }

        }
    }

    public void deleteThisFile(String text) {
        String fileName = text + ".txt";
        File file = new File(getExternalFilesDir(null), fileName);
        if (file.exists()) {
            file.delete();
            Toast.makeText(this, "File deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "File NOT deleted", Toast.LENGTH_SHORT).show();
        }

    }
}