package com.example.androidsqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.database.SQLException;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Data extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        textView = findViewById(R.id.textView);
        try {
            ContactsDB db = new ContactsDB(this);
            db.open();
            textView.setText(db.returndata());
            db.close();
        } catch (SQLException e) {
            Toast.makeText(Data.this, e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }
    }
}