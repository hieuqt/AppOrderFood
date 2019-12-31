package com.example.apporderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.apporderfood.utilities.CreateDatabase;

public class FoodTableDAO {

    private static final String TAG = FoodTableDAO.class.getSimpleName();
    private SQLiteDatabase mSQLiteDatabase;

    public FoodTableDAO(Context context) {
        CreateDatabase createDatabase = new CreateDatabase(context);
        mSQLiteDatabase = createDatabase.open();
    }

    public boolean addFoodTable(String nameFoodTable){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_TABLE_NAME, nameFoodTable);
        contentValues.put(CreateDatabase.TB_TABLE_STATUS, "false");
        long check = mSQLiteDatabase.insert(CreateDatabase.TB_TABLE_FOOD, null, contentValues);
        if(check != 0){
            return true;
        }else{
            return false;
        }
    }
}
