package com.mcommerce.nhom8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.mcommerce.fragment.HistoryOrderFragment;

public class OderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oder);

        linkview();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HistoryOrderFragment historyOrderFragment = new HistoryOrderFragment();
        fragmentTransaction.add(R.id.layoutContainerFragment_orderactivity,historyOrderFragment);
        fragmentTransaction.commit();
    }

    private void linkview() {

    }
}