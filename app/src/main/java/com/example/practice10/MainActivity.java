package com.example.practice10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etUsername;
    private TextView tvCurrentUsername;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.etUsername);
        tvCurrentUsername = findViewById(R.id.tvCurrentUsername);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnLoad = findViewById(R.id.btnLoad);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnDelete = findViewById(R.id.btnDelete);

        sharedPreferences = getPreferences(MODE_PRIVATE);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                saveUsername(username);
            }
        });

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadUsername();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUsername = etUsername.getText().toString();
                updateUsername(newUsername);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUsername();
            }
        });
    }

    // Метод для сохранения имени пользователя
    private void saveUsername(String username)
    {
        // Получение редактора SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Добавление имени пользователя в SharedPreferences
        editor.putString("username", username);
        // Применение изменений
        editor.apply();
        updateUsernameDisplay(username);
        Toast.makeText(this, "Имя пользователя сохранено", Toast.LENGTH_SHORT).show();
    }


    // Метод для загрузки имени пользователя
    private void loadUsername() {
        // Получение имени пользователя из SharedPreferences, если оно существует
        String username = sharedPreferences.getString("username", "");
        etUsername.setText(username);
        updateUsernameDisplay(username);
        Toast.makeText(this, "Имя пользователя загружено", Toast.LENGTH_SHORT).show();
    }

    // Метод для обновления имени пользователя
    private void updateUsername(String newUsername) {
        // Получение редактора SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Обновление имени пользователя в SharedPreferences
        editor.putString("username", newUsername);
        editor.apply();
        updateUsernameDisplay(newUsername);
        Toast.makeText(this, "Имя пользователя обновлено", Toast.LENGTH_SHORT).show();
    }


    // Метод для удаления имени пользователя
    private void deleteUsername() {
        // Получение редактора SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Удаление имени пользователя из SharedPreferences
        editor.remove("username");
        editor.apply();
        // Очистка текста в EditText
        etUsername.setText("");
        // Обновление отображения имени пользователя
        updateUsernameDisplay("");
        Toast.makeText(this, "Имя пользователя удалено", Toast.LENGTH_SHORT).show();
    }

    // Метод для обновления отображения имени пользователя в TextView
    private void updateUsernameDisplay(String username) {
        // Установка текста в TextView для отображения имени пользователя
        tvCurrentUsername.setText("Имя пользователя: " + username);
    }
}