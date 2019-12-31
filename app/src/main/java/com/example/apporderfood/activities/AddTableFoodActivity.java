package com.example.apporderfood.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.apporderfood.DAO.FoodTableDAO;
import com.example.apporderfood.R;

public class AddTableFoodActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = AddTableFoodActivity.class.getSimpleName();
    private EditText mEdNameAddFoodTable;
    private Button mBtnAcceptAddFoodTable;
    private FoodTableDAO mFoodTableDAO;
    private ImageButton mImgBtnCloseAddFoodTable;
    private LinearLayout mLlProgressBar;
    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_table_food);
        init();
    }

    private void init() {
        intView();
        addEvent();
    }

    private void intView() {
        mEdNameAddFoodTable = findViewById(R.id.ed_add_food_table_name);
        mBtnAcceptAddFoodTable = findViewById(R.id.btn_add_food_table_accept);
        mImgBtnCloseAddFoodTable = findViewById(R.id.btn_add_food_table_close);
        mLlProgressBar = findViewById(R.id.ll_pb_add_table_food);
        mLinearLayout = findViewById(R.id.ll_add_food_table);
        mFoodTableDAO = new FoodTableDAO(this);
    }

    private void addEvent() {
        mBtnAcceptAddFoodTable.setOnClickListener(this);
        mImgBtnCloseAddFoodTable.setOnClickListener(this);
        mLlProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        closeKeyboard();
        switch (v.getId()) {
            case R.id.btn_add_food_table_accept:
                Log.d(TAG, "onClick: accept");
                addFoodTable();
                break;
            case R.id.btn_add_food_table_close:
                Log.d(TAG, "onClick: close");
                Toast.makeText(this, getResources().getString(R.string.close), Toast.LENGTH_SHORT).show();
                finish();
                break;
            default:
                break;
        }
    }

    private void addFoodTable() {
        String nameFoodTable = mEdNameAddFoodTable.getText().toString();
        if (nameFoodTable.equals("")) {
            Toast.makeText(this, getResources().getString(R.string.name_food_table_empty), Toast.LENGTH_SHORT).show();
        } else {
            AddTableFoodTask registerTask = new AddTableFoodTask(this);
            registerTask.execute(nameFoodTable);
        }
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    class AddTableFoodTask extends AsyncTask<String, Void, Void> {

        private Context mContext;

        AddTableFoodTask(Context context) {
            mContext = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLlProgressBar.setVisibility(View.VISIBLE);
            mLinearLayout.setVisibility(View.GONE);
        }

        @Override
        protected Void doInBackground(String... params) {
            boolean check = mFoodTableDAO.addFoodTable(params[0]);
            Log.d(TAG, "doInBackground: " + check);
            Intent intent = new Intent();
            intent.putExtra("checkadd", check);
            intent.putExtra("namefoodtable", params[0]);
            setResult(Activity.RESULT_OK, intent);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            mLlProgressBar.setVisibility(View.GONE);
            finish();
        }
    }
}
