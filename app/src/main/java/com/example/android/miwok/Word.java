package com.example.android.miwok;

public class Word {
    /** Default translation for the word */
    private String mDefaultTranslation;

    /** Miwok translation for the word */
    private String mMiwokTranslation;

    /** Image resource ID for the word */
    private int mImageResourceID = NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;

    /**
     * Create a new Word object
     *
     * @param defaultTranslation is word in familiar language
     *
     * @param miwokTranslation is the word in Miwok
     *
     * @param imageResourceID is the drawable resource ID for the image associated with the word
     */

    public Word(String defaultTranslation, String miwokTranslation, int imageResourceID) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceID = imageResourceID;
    }

    /**
     * Create a new Word object
     *
     * @param defaultTranslation is word in familiar language
     *
     * @param miwokTranslation is the word in Miwok
     */

    public Word(String defaultTranslation, String miwokTranslation) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
    }


    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getImageResourceID() {
        return mImageResourceID;
    }

    public boolean hasImage(){
        return mImageResourceID != NO_IMAGE_PROVIDED;
    }
}
