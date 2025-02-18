package com.example.aplicacionmovil.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "aplicacion.db";
    private static final int DATABASE_VERSION = 1;

    // Tabla de usuarios
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USER_USERNAME = "username";
    public static final String COLUMN_USER_PASSWORD = "password";

    // Tabla de remisiones (código y valor en vez de título y descripción)
    public static final String TABLE_REMISSIONS = "remissions";
    public static final String COLUMN_REMISSION_ID = "id";
    public static final String COLUMN_REMISSION_CODE = "code";
    public static final String COLUMN_REMISSION_VALUE = "value";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear tabla de usuarios
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + " ("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USER_USERNAME + " TEXT UNIQUE, "
                + COLUMN_USER_PASSWORD + " TEXT)";
        db.execSQL(CREATE_USERS_TABLE);

        // Crear tabla de remisiones
        String CREATE_REMISSIONS_TABLE = "CREATE TABLE " + TABLE_REMISSIONS + " ("
                + COLUMN_REMISSION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_REMISSION_CODE + " TEXT, "
                + COLUMN_REMISSION_VALUE + " TEXT)";
        db.execSQL(CREATE_REMISSIONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Eliminar tablas existentes y recrearlas
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REMISSIONS);
        onCreate(db);
    }

    // ------------------ MÉTODOS PARA USUARIOS ------------------
    public boolean insertUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_USERNAME, username);
        values.put(COLUMN_USER_PASSWORD, password);
        long result = db.insert(TABLE_USERS, null, values);
        return result != -1;
    }

    public boolean validateUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS
                + " WHERE " + COLUMN_USER_USERNAME + " = ? AND " + COLUMN_USER_PASSWORD + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username, password});
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    // ------------------ MÉTODOS PARA REMISIONES ------------------
    public boolean insertRemission(String code, String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_REMISSION_CODE, code);
        values.put(COLUMN_REMISSION_VALUE, value);
        long result = db.insert(TABLE_REMISSIONS, null, values);
        return result != -1;
    }

    public Cursor getRemissionById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_REMISSIONS
                        + " WHERE " + COLUMN_REMISSION_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public Cursor getAllRemissions() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_REMISSIONS, null);
    }

    public boolean updateRemission(int id, String code, String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_REMISSION_CODE, code);
        values.put(COLUMN_REMISSION_VALUE, value);
        int rows = db.update(TABLE_REMISSIONS, values,
                COLUMN_REMISSION_ID + " = ?", new String[]{String.valueOf(id)});
        return rows > 0;
    }

    public boolean deleteRemission(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE_REMISSIONS,
                COLUMN_REMISSION_ID + " = ?", new String[]{String.valueOf(id)});
        return rows > 0;
    }
}
