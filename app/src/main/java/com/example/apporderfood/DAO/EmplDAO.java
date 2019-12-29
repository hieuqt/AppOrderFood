package com.example.apporderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.apporderfood.DTO.EmplDTO;
import com.example.apporderfood.utilities.CreateDatabase;

public class EmplDAO {

    private static final String TAG = EmplDAO.class.getSimpleName();
    private SQLiteDatabase mSQLiteDatabase;

    public EmplDAO(Context context) {
        CreateDatabase createDatabase = new CreateDatabase(context);
        mSQLiteDatabase = createDatabase.open();
    }

    public long addEmployee(EmplDTO emplDTO){
        ContentValues  contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_EMPLOYEE_ACC, emplDTO.getAccount());
        contentValues.put(CreateDatabase.TB_EMPLOYEE_PASS, emplDTO.getPassword());
        contentValues.put(CreateDatabase.TB_EMPLOYEE_GENDER, emplDTO.getGender());
        contentValues.put(CreateDatabase.TB_EMPLOYEE_DOB, emplDTO.getDob());
        contentValues.put(CreateDatabase.TB_EMPLOYEE_ID_NATIONAL, emplDTO.getIdNational());
        return mSQLiteDatabase.insert(CreateDatabase.TB_EMPLOYEE, null, contentValues);
    }

/*    public boolean checkEmptyEmployee(){
        String queryEmpl = "SELECT * FROM " + CreateDatabase.TB_EMPLOYEE;
        Cursor cursor = mSQLiteDatabase.rawQuery(queryEmpl, null);
        if(cursor.getCount() != 0){
            return true;
        }else{
            return false;
        }
    }*/

    public boolean checkLoginEmployee(String account, String password){
        String queryEmpl_2 = "SELECT * FROM " + CreateDatabase.TB_EMPLOYEE + " WHERE "
                + CreateDatabase.TB_EMPLOYEE_ACC + " = '" + account + "' AND "
                + CreateDatabase.TB_EMPLOYEE_PASS + " = '" + password + "' ";
        Cursor cursor = mSQLiteDatabase.rawQuery(queryEmpl_2, null);
        Log.d(TAG, "checkLoginEmployee: " + cursor.getCount());
        if(cursor.getCount() != 0){
            return true;
        }else{
            return false;
        }
    }
}
