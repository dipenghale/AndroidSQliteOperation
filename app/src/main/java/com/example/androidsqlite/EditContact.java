package com.example.androidsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_contact);
        Button btnsave = findViewById(R.id.btnSave);
        Button btndel = findViewById(R.id.btnDel);
        TextView rowid = findViewById(R.id.rowid);
        EditText name = findViewById(R.id.etName);
        EditText phone = findViewById(R.id.etPhone);

        ContactsDB dbHelper = new ContactsDB(this);
        Intent intent = getIntent();
        if(intent.hasExtra("contactid")){
            Integer cid = intent.getIntExtra("contactid", -1);
            String cname = intent.getStringExtra("name").toString();
            String cphone = intent.getStringExtra("phone").toString();
            rowid.setText(cid.toString());
            name.setText(cname);
            phone.setText(cphone);
           rowid.setVisibility(View.INVISIBLE);

            btnsave.setOnClickListener(v -> {
                String uid = rowid.getText().toString();
                String uname = name.getText().toString();
                String uphone = phone.getText().toString();
                dbHelper.updateContact(new Contact(Integer.parseInt(uid),uname, uphone));
                Intent gointent = new Intent(this, MainActivity.class);
                startActivity(gointent);


            });

            btndel.setOnClickListener(v -> {
                int uid = Integer.parseInt(rowid.getText().toString());

                dbHelper.deleteContact(uid);
                Intent gointent = new Intent(this, MainActivity.class);
                startActivity(gointent);


            });

    }
}
}