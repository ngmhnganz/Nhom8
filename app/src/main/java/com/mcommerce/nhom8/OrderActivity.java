package com.mcommerce.nhom8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.android.material.appbar.MaterialToolbar;
import com.mcommerce.adapter.TestOrderAdapter;
import com.mcommerce.model.OrderModel;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    private ListView lvTest;
    private ArrayList<OrderModel> orderModels;
    private RecyclerView rcvOrder;
    private android.widget.SearchView searchView;
    private TestOrderAdapter testOrderAdapter;
    private MaterialToolbar topBar_ComingOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        linkview();
        initData();
        initAdapter();
        addEvent();
    }

    private void addEvent() {

        topBar_ComingOrder.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Xu ly su kien tra ve
            }
        });

        //Xu li search
        //Mo TestOrderAdapter xem xu ly filter dong 61
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {


                testOrderAdapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                testOrderAdapter.getFilter().filter(s);
                return false;
            }
        });

    }

    private void initAdapter() {
        testOrderAdapter = new TestOrderAdapter(orderModels,this);
        rcvOrder.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rcvOrder.setAdapter(testOrderAdapter);

    }

    private void initData() {
        orderModels = new ArrayList<OrderModel>();
        orderModels.add(new OrderModel("2 người","200000","1","23/2",R.drawable.botmi));
        orderModels.add(new OrderModel("3 người","200000","3","23/2",R.drawable.botmi));
        orderModels.add(new OrderModel("3 người","200000","7","23/2",R.drawable.botmi));
        orderModels.add(new OrderModel("2 người","400000","5","23/2",R.drawable.botmi));
        orderModels.add(new OrderModel("3 người","200000","6","23/2",R.drawable.botmi));
        orderModels.add(new OrderModel("3 người","200000","9","23/2",R.drawable.botmi));
        orderModels.add(new OrderModel("3 người","200000","7","23/2",R.drawable.botmi));

    }

    private void linkview() {
        searchView = findViewById(R.id.search_view);
        rcvOrder = findViewById(R.id.rcvOder_ComingOrder);
        topBar_ComingOrder = findViewById(R.id.topBar_ComingOrder);
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) findViewById(R.id.search_view);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                testOrderAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                testOrderAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }*/
}