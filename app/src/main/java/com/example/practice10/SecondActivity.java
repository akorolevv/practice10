package com.example.practice10;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private EditText etField1, etField2, etField3, etField4, etField5;
    private Button btnSave, btnUpdate, btnDelete, btnSearch;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);


        etField1 = findViewById(R.id.etField1);
        etField2 = findViewById(R.id.etField2);
        etField3 = findViewById(R.id.etField3);
        etField4 = findViewById(R.id.etField4);
        etField5 = findViewById(R.id.etField5);

        btnSave = findViewById(R.id.btnSave);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnSearch = findViewById(R.id.btnSearch);

        databaseHelper = new DatabaseHelper(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEntity();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEntity();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEntity();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchEntities();
            }
        });
    }

    private void saveEntity() {
        String field1_string = etField1.getText().toString().trim();
        int field1 = Integer.parseInt(field1_string);
        String field2 = etField2.getText().toString().trim();
        String field3 = etField3.getText().toString().trim();
        String field4 = etField4.getText().toString().trim();
        String field5 = etField5.getText().toString().trim();

        Contact entity = new Contact(field1, field2, field3, field4, field5); // Используйте предоставленный пользователем ID
        long id = databaseHelper.insert(entity);

        if (id > 0) {
            Toast.makeText(this, "Entity saved successfully", Toast.LENGTH_SHORT).show();
            clearFields();
        } else {
            Toast.makeText(this, "Failed to save entity", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateEntity() {
        String idString = etField1.getText().toString().trim();
        if (!idString.isEmpty()) {
            int id = Integer.parseInt(idString);
            String field2 = etField2.getText().toString().trim();
            String field3 = etField3.getText().toString().trim();
            String field4 = etField4.getText().toString().trim();
            String field5 = etField5.getText().toString().trim();

            Contact entity = new Contact(id, field2, field3, field4, field5);
            int rowsUpdated = databaseHelper.update(entity);

            if (rowsUpdated > 0) {
                Toast.makeText(this, "Entity updated successfully", Toast.LENGTH_SHORT).show();
                clearFields();
            } else {
                Toast.makeText(this, "Failed to update entity", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please enter an ID to update", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteEntity() {
        String idString = etField1.getText().toString().trim();
        if (!idString.isEmpty()) {
            int id = Integer.parseInt(idString);
            databaseHelper.delete(id);
            Toast.makeText(this, "Entity deleted successfully", Toast.LENGTH_SHORT).show();
            clearFields();
        } else {
            Toast.makeText(this, "Please enter an ID to delete", Toast.LENGTH_SHORT).show();
        }
    }

    private void searchEntities() {
        String idString = etField1.getText().toString().trim();
        if (!idString.isEmpty()) {
            int id = Integer.parseInt(idString);
            List<Contact> entities = databaseHelper.getAllEntities();
            for (Contact entity : entities) {
                if (entity.getId() == id) {
                    etField1.setText(String.valueOf(entity.getId()));
                    etField2.setText(entity.getName());
                    etField3.setText(entity.getNumber());
                    etField4.setText(entity.getEmail());
                    etField5.setText(entity.getInfo());
                    Toast.makeText(this, "Entity found", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            Toast.makeText(this, "Entity not found", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please enter an ID to search", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        etField1.setText("");
        etField2.setText("");
        etField3.setText("");
        etField4.setText("");
        etField5.setText("");
    }
}