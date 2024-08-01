package com.example.androidsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    EditText etname, etcell;
    Button bshow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etname = findViewById(R.id.etna);
        etcell = findViewById(R.id.etcell);
        bshow = findViewById(R.id.bshow);
        bshow.setOnClickListener(v-> {

                Intent intent = new Intent(MainActivity.this,Data.class);
                startActivity(intent);

        });

    }


    public void submit(View v) {
        String name = etname.getText()
                .toString()
                .trim();
        String cell = etcell.getText()
                .toString()
                .trim();
        try {
            ContactsDB db = new ContactsDB(this);
            db.open();
            db.creat(name, cell);
            db.close();
            Toast.makeText(MainActivity.this, "Successfully saved ", Toast.LENGTH_LONG)
                    .show();
            etname.setText("");
            etcell.setText("");
        } catch (SQLException e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }
    }

    public void edit(View v) {
        try {
            ContactsDB db = new ContactsDB(this);
            db.open();
            db.update("1", "John", "24334421");
            db.close();
            Toast.makeText(MainActivity.this, "Successfully updated", Toast.LENGTH_LONG)
                    .show();
        } catch (SQLException e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }
    }

    public void delete(View v) {
        try {
            ContactsDB db = new ContactsDB(this);
            db.open();
            db.deleteEnter("1");
            Toast.makeText(MainActivity.this, "Successfully delete", Toast.LENGTH_LONG)
                    .show();
            db.close();
        } catch (SQLException e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }
    }
}