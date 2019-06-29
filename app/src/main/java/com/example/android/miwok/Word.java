package com.example.android.miwok;

import android.media.Image;

public class Word {
    // Default translation for the word
    private String mDefaultTranslation;

    // Miwok translation for the word
    private String mMiwokTranslation;

    // Image resource ID for the word
    private int mImageResourceID = NO_IMAGE_PROVIDED;

    //constant value to represent no image provided for this word/phrase
    private static final int NO_IMAGE_PROVIDED = -1;

    //audio resource ID for the word
    private int mAudioResourceID;

    /**
     * Create a new Word object
     *
     * @param defaultTranslation is word in familiar language
     *
     * @param miwokTranslation is the word in Miwok
     *
     * @param imageResourceID is the drawable resource ID for the image associated with the word
     *
     * @param audioResourceID is the raw resource ID for the audio file associated with the word
     */

    public Word(String defaultTranslation, String miwokTranslation, int imageResourceID, int audioResourceID) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceID = imageResourceID;
        mAudioResourceID = audioResourceID;
    }

    /**
     * Create a new Word object
     *
     * @param defaultTranslation is word in familiar language
     *
     * @param miwokTranslation is the word in Miwok
     *
     * @param audioResourceID is the raw resource ID for the audio file associated with the word
     */

    public Word(String defaultTranslation, String miwokTranslation, int audioResourceID) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceID = audioResourceID;
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

    /**
     * Return the audio resource ID of the word.
     * @return
     */
    public int getAudioResourceID(){
        return mAudioResourceID;
    }
}
