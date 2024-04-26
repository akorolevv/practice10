package com.example.practice10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ContactsBase.db"; // имя
    private static final int DATABASE_VERSION = 1; // версия

    private static final String TABLE_NAME = "ContactsTable";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FIELD1 = "field1";
    private static final String COLUMN_FIELD2 = "field2";
    private static final String COLUMN_FIELD3 = "field3";
    private static final String COLUMN_FIELD4 = "field4";
    private static final String COLUMN_FIELD5 = "field5";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + // строка для создания таблицы
            COLUMN_ID + " INTEGER PRIMARY KEY," +
            COLUMN_FIELD2 + " TEXT," +
            COLUMN_FIELD3 + " TEXT," +
            COLUMN_FIELD4 + " TEXT," +
            COLUMN_FIELD5 + " TEXT" + ")";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    // Метод, вызываемый при обновлении версии базы данных
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Методы для вставки, обновления, удаления и поиска данных
    public long insert(Contact entity) {
        SQLiteDatabase db = this.getWritableDatabase(); // получение доступа у бд для записи
        ContentValues values = new ContentValues(); // оздание объекта ContentValues для хранения значений
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_FIELD2, entity.getName());
        values.put(COLUMN_FIELD3, entity.getNumber());
        values.put(COLUMN_FIELD4, entity.getEmail());
        values.put(COLUMN_FIELD5, entity.getInfo());
        return db.insert(TABLE_NAME, null, values); // // Вставка новой записи в таблицу и возвращение id новой записи
    }

    public int update(Contact entity)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIELD2, entity.getName());
        values.put(COLUMN_FIELD3, entity.getNumber());
        values.put(COLUMN_FIELD4, entity.getEmail());
        values.put(COLUMN_FIELD5, entity.getInfo());
        return db.update(TABLE_NAME, values, COLUMN_ID + " = ?", // Обновление записи, где id совпадает с id объекта entity
                new String[]{String.valueOf(entity.getId())});
    }

    public void delete(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)}); // Удаление записи, где id совпадает с переданным значением
    }

    // Метод для получения всех записей из таблицы
    public List<Contact> getAllEntities()
    {
        List<Contact> entities = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME; // SQL-запрос для выборки всех записей из таблицы
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String field2 = cursor.getString(cursor.getColumnIndex(COLUMN_FIELD2));
                String field3 = cursor.getString(cursor.getColumnIndex(COLUMN_FIELD3));
                String field4 = cursor.getString(cursor.getColumnIndex(COLUMN_FIELD4));
                String field5 = cursor.getString(cursor.getColumnIndex(COLUMN_FIELD5));
                Contact entity = new Contact(id, field2, field3, field4, field5);
                entities.add(entity); // Добавление объекта Contact в список
            } while (cursor.moveToNext()); // Переход к следующей записи в курсоре
        }
        cursor.close();
        return entities;
    }
}