package com.example.apporderfood.utilities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CreateDatabase extends SQLiteOpenHelper {

    public static final String TB_EMPLOYEE = "EMPLOYEE";
    public static final String TB_FOOD = "FOOD";
    public static final String TB_FOOD_TYPE = "FOOD_TYPE";
    public static final String TB_TABLE_FOOD = "TABLE_FOOD";
    public static final String TB_ORDER_FOOD = "ORDER_FOOD";
    public static final String TB_ORDER_DETAIL = "ORDER_DETAIL";

    // TB_EMPLOYEE
    public static final String TB_EMPLOYEE_ID = "EMPLOYEE_ID";
    public static final String TB_EMPLOYEE_ACC = "ACC";
    public static final String TB_EMPLOYEE_PASS = "PASS";
    public static final String TB_EMPLOYEE_GENDER = "GENDER";
    public static final String TB_EMPLOYEE_DOB = "DOB";
    public static final String TB_EMPLOYEE_ID_NATIONAL = "ID_NATIONAL";

    // TB_FOOD
    public static final String TB_FOOD_ID = "FOOD_ID";
    public static final String TB_FOOD_NAME = "FOOD_NAME";
    public static final String TB_FOOD_PRICE = "PRICE";
    public static final String TB_FOOD_FOOD_TYPE_ID = "TYPE_ID";

    // TB_FOOD_TYPE
    public static final String TB_FOOD_TYPE_ID = "TYPE_ID";
    public static final String TB_FOOD_TYPE_NAME = "FOOD_TYPE_NAME";

    // TB_TABLE
    public static final String TB_TABLE_ID = "TABLE_ID";
    public static final String TB_TABLE_NAME = "TABLE_NAME";
    public static final String TB_TABLE_STATUS = "TABLE_STATUS";

    // ORDER
    public static final String TB_ORDER_ID = "ORDER_ID";
    public static final String TB_ORDER_EMPLOYEE_ID = "EMPLOYEE_ID";
    public static final String TB_ORDER_DAY = "DAY";
    public static final String TB_ORDER_STATUS = "ORDER_STATUS";
    public static final String TB_ORDER_TABLE_ID = "TABLE_ID";

    // ORDER_DETAIL
    public static final String TB_ORDER_DETAIL_ORDER_ID = "ORDER_ID";
    public static final String TB_ORDER_DETAIL_FOOD_ID = "FOOD_ID";
    public static final String TB_ORDER_DETAIL_AMOUNT = "AMOUNT";

    public CreateDatabase(@Nullable Context context) {
        super(context, "OrderFood", null,  1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbEMPLOYEE = "CREATE TABLE " + TB_EMPLOYEE + " ( " + TB_EMPLOYEE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_EMPLOYEE_ACC + " TEXT, " + TB_EMPLOYEE_PASS + " TEXT, " + TB_EMPLOYEE_GENDER + " TEXT, "
                + TB_EMPLOYEE_DOB + " TEXT, " + TB_EMPLOYEE_ID_NATIONAL + " INTEGER )";

        String tbTABLE = "CREATE TABLE " + TB_TABLE_FOOD + " ( " + TB_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_TABLE_NAME + " TEXT, " + TB_TABLE_STATUS + " TEXT ) ";

        String tbFOOD = "CREATE TABLE " + TB_FOOD + " ( " + TB_FOOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_FOOD_NAME + " TEXT, " + TB_FOOD_FOOD_TYPE_ID + " INTEGER, " + TB_FOOD_PRICE + " TEXT ) ";

        String tbFOOD_TYPE = "CREATE TABLE " + TB_FOOD_TYPE + " ( " + TB_FOOD_TYPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_FOOD_TYPE_NAME + " TEXT ) ";

        String tbORDER = "CREATE TABLE " + TB_ORDER_FOOD + " ( " + TB_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_ORDER_TABLE_ID + " INTEGER, " + TB_ORDER_EMPLOYEE_ID + " INTEGER, " + TB_ORDER_DAY + " TEXT, "
                + TB_ORDER_STATUS + " TEXT ) ";

        String tbORDER_DETAIL = "CREATE TABLE " + TB_ORDER_DETAIL + " ( " + TB_ORDER_DETAIL_ORDER_ID + " INTEGER, "
                + TB_ORDER_DETAIL_FOOD_ID + " INTEGER, " + TB_ORDER_DETAIL_AMOUNT + " INTEGER, "
                + " PRIMARY KEY ( " + TB_ORDER_DETAIL_ORDER_ID + " , " + TB_ORDER_DETAIL_AMOUNT + " ) ) ";

        db.execSQL(tbEMPLOYEE);
        db.execSQL(tbTABLE);
        db.execSQL(tbFOOD);
        db.execSQL(tbFOOD_TYPE);
        db.execSQL(tbORDER);
        db.execSQL(tbORDER_DETAIL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TB_EMPLOYEE);
        db.execSQL("DROP TABLE IF EXISTS "+TB_TABLE_FOOD);
        db.execSQL("DROP TABLE IF EXISTS "+TB_FOOD);
        db.execSQL("DROP TABLE IF EXISTS "+TB_FOOD_TYPE);
        db.execSQL("DROP TABLE IF EXISTS "+TB_ORDER_FOOD);
        db.execSQL("DROP TABLE IF EXISTS "+TB_ORDER_DETAIL);
        onCreate(db);
    }

    public SQLiteDatabase open(){
        return this.getWritableDatabase();
    }

}
