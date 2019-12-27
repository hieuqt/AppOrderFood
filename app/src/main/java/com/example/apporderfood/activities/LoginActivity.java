package com.example.apporderfood.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apporderfood.DAO.EmplDAO;
import com.example.apporderfood.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private EditText mEdAccount, mEdPassword;
    private Button mBtnLogin, mBtnRegister;
    private EmplDAO mEmplDAO;
    private ProgressBar mProgressBar;
    private LinearLayout mLinearLayout;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        showButtonLoginAndRegister();
        mLinearLayout.setVisibility(View.VISIBLE);
        mImageView.setVisibility(View.VISIBLE);
    }

    private void init() {
        initView();
        addEvent();
    }

    private void initView() {
        mEdAccount = findViewById(R.id.ed_account);
        mEdPassword = findViewById(R.id.ed_password);
        mBtnLogin = findViewById(R.id.btn_login);
        mBtnRegister = findViewById(R.id.btn_register);
        mProgressBar = findViewById(R.id.pb_login);
        mLinearLayout = findViewById(R.id.ln_login);
        mImageView = findViewById(R.id.iv_login_mixifood);
        mEmplDAO = new EmplDAO(this);
    }

    private void addEvent() {
//        showButtonLoginAndRegister();
        mBtnLogin.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);
        mProgressBar.setVisibility(View.GONE);
    }

/*    private void showButtonLoginAndRegister() {
        CheckEmptyEmployeeTask checkEmptyEmployeeTask = new CheckEmptyEmployeeTask(this);
        checkEmptyEmployeeTask.execute();
    }*/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                loginEmployee();
                break;
            case R.id.btn_register:
                registerEmployee();
                break;
            default:
                break;
        }
    }

    private void loginEmployee() {
        String account = mEdAccount.getText().toString();
        String password = mEdPassword.getText().toString();
        if (account.equals("") && password.equals("")) {
            Toast.makeText(this, getResources().getString(R.string.error_enter_acc_pass), Toast.LENGTH_SHORT).show();
        } else if (account.equals("")) {
            Toast.makeText(this, getResources().getString(R.string.error_enter_account), Toast.LENGTH_SHORT).show();
        } else if (password.equals("")) {
            Toast.makeText(this, getResources().getString(R.string.error_enter_password), Toast.LENGTH_SHORT).show();
        } else {
            LoginTask loginTask = new LoginTask(this);
            loginTask.execute(account, password);
        }
    }

    private void registerEmployee() {
        Intent intentRegister = new Intent(this, RegisterActivity.class);
        startActivity(intentRegister);
    }

    class LoginTask extends AsyncTask<String, Void, Boolean> {

        private Context mContext;

        LoginTask(Context context) {
            mContext = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
            mLinearLayout.setVisibility(View.GONE);
            mImageView.setVisibility(View.GONE);
//            mProgressBar.setProgress(0);
        }

        @Override
        protected Boolean doInBackground(String... params) {
            String account = params[0];
            String password = params[1];
            boolean check = mEmplDAO.checkLoginEmployee(account, password);
            Log.d(TAG, "loginEmployee: " + check);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return check;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            mProgressBar.setVisibility(View.GONE);
            if (result) {
                Toast.makeText(mContext, getResources().getString(R.string.login_success), Toast.LENGTH_SHORT).show();
                Intent intentHomePage = new Intent(mContext, HomePageActivity.class);
                intentHomePage.putExtra("account", mEdAccount.getText().toString());
                Log.d(TAG, "onPostExecute: " + mEdAccount.getText().toString());
                startActivity(intentHomePage);
            } else {
                Toast.makeText(mContext, getResources().getString(R.string.login_failed), Toast.LENGTH_SHORT).show();
                mLinearLayout.setVisibility(View.VISIBLE);
                mImageView.setVisibility(View.VISIBLE);
            }
        }
    }
/*
    class CheckEmptyEmployeeTask extends AsyncTask<Void, Void, Boolean> {

        private Context mContext;

        CheckEmptyEmployeeTask(Context context) {
            mContext = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
            mLinearLayout.setVisibility(View.GONE);
            mImageView.setVisibility(View.GONE);
//            mProgressBar.setProgress(0);
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            boolean check = mEmplDAO.checkEmptyEmployee();
            Log.d(TAG, "showButtonLoginAndRegister: " + check);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return check;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            mProgressBar.setVisibility(View.GONE);
            mLinearLayout.setVisibility(View.VISIBLE);
            mImageView.setVisibility(View.VISIBLE);
            if (result) {
                mBtnLogin.setVisibility(View.VISIBLE);
                mBtnRegister.setVisibility(View.GONE);
            } else {
                mBtnLogin.setVisibility(View.GONE);
                mBtnRegister.setVisibility(View.VISIBLE);
            }
        }
    }*/
}
