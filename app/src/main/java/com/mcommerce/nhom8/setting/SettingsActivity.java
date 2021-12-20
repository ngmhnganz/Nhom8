package com.mcommerce.nhom8.setting;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.app.FragmentTransaction;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreference;

import com.mcommerce.nhom8.R;

public class SettingsActivity extends AppCompatActivity {
    private SwitchPreference darkModeSwitch;
    private Object Preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            addPreferencesFromResource(R.xml.settings_screen);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

            //Switch NIGHT MODE
            SwitchPreference sw_night = (SwitchPreference) findPreference("NIGHT");
            boolean isChecked = sharedPreferences.getBoolean("NIGHT", false);

            sw_night.setOnPreferenceChangeListener((preference, newValue) -> {
                if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    sw_night.setChecked(false);
                }else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    sw_night.setChecked(true);
                    }
                    return false;
            });

            //List LANGUAGE
            ListPreference LP = (ListPreference) findPreference("LANGUAGE");

            String language = sharedPreferences.getString("LANGUAGE", "false");
            if ("1".equals(language)){

            }else if ("2".equals(language)){

            }

            LP.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(androidx.preference.Preference preference, Object newValue) {
                    return false;
                }
            });

        }

    }


}