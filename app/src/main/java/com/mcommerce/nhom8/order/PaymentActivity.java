package com.mcommerce.nhom8.order;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.LinearLayout;
import com.mcommerce.fragment.ConfirmOrderFragment;
import com.mcommerce.nhom8.R;

public class PaymentActivity extends AppCompatActivity {

    LinearLayout layoutContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        linkview();
        loadUI();
    }

    private void loadUI() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layoutContainer, new ConfirmOrderFragment());
        transaction.commit();
    }

    private void linkview() {
        layoutContainer = findViewById(R.id.layoutContainer);
   }

}