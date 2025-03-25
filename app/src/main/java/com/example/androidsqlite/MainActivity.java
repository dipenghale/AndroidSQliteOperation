package com.example.androidsqlite;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText etName, etPhone;
    Button btnAdd;
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> contactStrings;
    List<Contact> contactList;
    ContactsDB dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        btnAdd = findViewById(R.id.btnAdd);
        listView = findViewById(R.id.listView);

        dbHelper = new ContactsDB(this);
        contactStrings = new ArrayList<>();

        loadContacts();

        btnAdd.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String phone = etPhone.getText().toString();
            dbHelper.insertContact(name, phone);
            etName.setText("");
            etPhone.setText("");
            loadContacts();
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Contact selected = contactList.get(position);
            // You could show an AlertDialog to edit or delete here
            Intent intent = new Intent(this, EditContact.class);
            intent.putExtra("contactid", selected.getId());
            intent.putExtra("name", selected.getName());
            intent.putExtra("phone", selected.getPhone());
            startActivity(intent);
            //Toast.makeText(this, "Selected: " + selected.getName(), Toast.LENGTH_SHORT).show();
        });
    }

    private void loadContacts() {
        contactList = dbHelper.getAllContacts();
        contactStrings.clear();
        for (Contact c : contactList) {
            contactStrings.add(c.getName() + " - " + c.getPhone());
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactStrings);
        listView.setAdapter(adapter);
    }
}
