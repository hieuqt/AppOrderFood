package com.example.apporderfood.activities;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apporderfood.DAO.EmplDAO;
import com.example.apporderfood.DTO.EmplDTO;
import com.example.apporderfood.R;
import com.example.apporderfood.fragments.DatePickerFragment;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    private static final String TAG = RegisterActivity.class.getSimpleName();
    private EditText mEdAccount, mEdPassword, mEdDob, mEdNationalId;
    private Button mBtnRegister, mBtnCancel;
    private RadioGroup mRgGender;
    private DatePickerFragment mDatePickerFragment;
    private String mGender;
    private EmplDAO mEmplDAO;
    private String mNationalId;
    private ProgressBar mProgressBar;
    private LinearLayout mLinearLayout;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init() {
        initView();
        addEvent();
    }

    private void initView() {
        mEdAccount = findViewById(R.id.ed_account);
        mEdPassword = findViewById(R.id.ed_password);
        mEdDob = findViewById(R.id.ed_dob);
        mEdNationalId = findViewById(R.id.ed_national_id);
        mBtnRegister = findViewById(R.id.btn_register);
        mBtnCancel = findViewById(R.id.btn_cancel);
        mRgGender = findViewById(R.id.rg_gender);
        mEmplDAO = new EmplDAO(this);
        mProgressBar = findViewById(R.id.pb_register);
        mLinearLayout = findViewById(R.id.ln_register);
        mImageView = findViewById(R.id.iv_register_mixifood);
    }

    private void addEvent() {
        mBtnRegister.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
//        mEdDob.setInputType(InputType.TYPE_NULL);
        mEdDob.setOnClickListener(this);
        mEdDob.setOnFocusChangeListener(this);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                Log.d(TAG, "register button click");
                registerEmployee();
                break;
            case R.id.btn_cancel:
                Log.d(TAG, "cancel button click");
                finish();
                break;
            case R.id.ed_dob:
                Log.d(TAG, "DOB edittext click");
                showDatePickerDialog();
                break;
            default:
                break;
        }
    }

    private void registerEmployee() {
        String account = mEdAccount.getText().toString();
        String password = mEdPassword.getText().toString();
        String dob = mEdDob.getText().toString();
        if(mEdNationalId.getText().toString().equals("")){
            mNationalId = "0";
        }else{
            mNationalId = mEdNationalId.getText().toString();
        }
        switch (mRgGender.getCheckedRadioButtonId()) {
            case R.id.rb_male:
                mGender = getResources().getString(R.string.gender_male);
                break;
            case R.id.rb_female:
                mGender = getResources().getString(R.string.gender_female);
                break;
            default:
                break;
        }
        if(account.equals("")&&password.equals("")){
            Toast.makeText(this, getResources().getString(R.string.error_enter_acc_pass), Toast.LENGTH_SHORT).show();
        }else if(account.equals("")){
            Toast.makeText(this, getResources().getString(R.string.error_enter_account), Toast.LENGTH_SHORT).show();
        }else if(password.equals("")){
            Toast.makeText(this, getResources().getString(R.string.error_enter_password), Toast.LENGTH_SHORT).show();
        }else{
            RegisterTask registerTask = new RegisterTask(this);
            registerTask.execute(account, password, mGender, dob, mNationalId);
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (v.getId() == R.id.ed_dob) {
            if (hasFocus) {
                Log.d(TAG, "DOB edittext focus");
                showDatePickerDialog();
            }
        }
    }

    private void showDatePickerDialog() {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getSupportFragmentManager(), "DOB");
    }

    class RegisterTask extends AsyncTask<String, Void, String> {

        private Context mContext;

        RegisterTask(Context context) {
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
        protected String doInBackground(String... params) {
            EmplDTO emplDTO = new EmplDTO();
            emplDTO.setAccount(params[0]);
            emplDTO.setPassword(params[1]);
            emplDTO.setGender(params[2]);
            emplDTO.setDob(params[3]);
            emplDTO.setIdNational(Integer.parseInt(params[4]));
            long check = mEmplDAO.addEmployee(emplDTO);
            Log.d(TAG, "registerEmployee: " + check);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(check != 0){
                return getResources().getString(R.string.add_success_employee);
            }else{
                return getResources().getString(R.string.add_failed_employee);
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            mProgressBar.setVisibility(View.GONE);
            mLinearLayout.setVisibility(View.VISIBLE);
            mImageView.setVisibility(View.VISIBLE);
            Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();

        }
    }

}
