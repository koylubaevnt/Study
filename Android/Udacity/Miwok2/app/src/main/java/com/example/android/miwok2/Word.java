package com.example.android.miwok2;

/**
 * Created by koylu on 23.09.2017.
 */

public class Word {

    private static final int NO_IMAGE_PROVIDED = -1;
    private static final int NO_SOUND_PROVIDED = -1;

    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private int mSoundResourceId = NO_SOUND_PROVIDED;

    public Word() {
    }

    public Word(String defaultTranslation, String miwokTranslation) {
        this.mDefaultTranslation = defaultTranslation;
        this.mMiwokTranslation = miwokTranslation;
    }

    public Word(String defaultTranslation, String miwokTranslation, int soundResourceId) {
        this(defaultTranslation, miwokTranslation);
        this.mSoundResourceId = soundResourceId;
    }

    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId, int soundResourceId) {
        this(defaultTranslation, miwokTranslation, soundResourceId);
        this.mImageResourceId = imageResourceId;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public int getSoundResourceId() {
        return mSoundResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public boolean hasSound() {
        return mSoundResourceId != NO_SOUND_PROVIDED;
    }

}
