package com.mcommerce.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.mcommerce.adapter.PointsHistoryAdapter;
import com.mcommerce.nhom8.R;

public class PointsHistoryFragment extends Fragment {

    View view;

    RecyclerView rcv_PointsHistory;
    PointsHistoryAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_point_history,container,false);

        linkdata();

        return view;
    }

    private void linkdata() {

    }
}
