package com.example.miwok.model;

import com.example.miwok.R;

public class Word {
    private String miwokTranslation;
    private String defaultTranslation;
    private int imgRes = 0;
    private int mediaRes = R.raw.number_1;

    public Word(String miwokTranslation, String defaultTranslation) {
        this.setMiwokTranslation(miwokTranslation);
        this.setDefaultTranslation(defaultTranslation);
    }

    public Word(String miwokTranslation, String defaultTranslation, int imgID) {
        this.setMiwokTranslation(miwokTranslation);
        this.setDefaultTranslation(defaultTranslation);
        this.imgRes = imgID;
    }

    public Word(String miwokTranslation, String defaultTranslation, int imgID, int mediaResID) {
        this.setMiwokTranslation(miwokTranslation);
        this.setDefaultTranslation(defaultTranslation);
        this.imgRes = imgID;
        this.mediaRes = mediaResID;
    }

    public void setDefaultTranslation(String defaultTranslation) {
        this.defaultTranslation = defaultTranslation;
    }

    public void setMiwokTranslation(String miwokTranslation) {
        this.miwokTranslation = miwokTranslation;
    }

    public String getDefaultTranslation() {
        return defaultTranslation;
    }

    public String getMiwokTranslation() {
        return miwokTranslation;
    }

    public int getImgRes() {
        return imgRes;
    }

    public int getMediaRes() {
        return mediaRes;
    }
}
