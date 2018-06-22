package com.koylubaevnt.notepad;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;

/**
 * Created by KoylubaevNT on 13.07.2017.
 */

public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
