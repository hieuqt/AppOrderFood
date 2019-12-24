package com.example.apporderfood.DTO;

public class EmplDTO {

    private int mEmplId;
    private String mAccount;
    private String mPassword;
    private String mGender;
    private String mDob;
    private int mIdNational;

    public EmplDTO() {
    }

    public EmplDTO(int emplId, String account, String password, String gender, String dob, int idNational) {
        mEmplId = emplId;
        mAccount = account;
        mPassword = password;
        mGender = gender;
        mDob = dob;
        mIdNational = idNational;
    }

    public int getEmplId() {
        return mEmplId;
    }

    public void setEmplId(int emplId) {
        mEmplId = emplId;
    }

    public String getAccount() {
        return mAccount;
    }

    public void setAccount(String account) {
        mAccount = account;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    public String getDob() {
        return mDob;
    }

    public void setDob(String dob) {
        mDob = dob;
    }

    public int getIdNational() {
        return mIdNational;
    }

    public void setIdNational(int idNational) {
        mIdNational = idNational;
    }
}
