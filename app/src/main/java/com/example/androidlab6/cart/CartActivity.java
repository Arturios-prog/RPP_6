package com.example.androidlab6.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidlab6.data.ItemData;
import com.example.androidlab6.R;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        RecyclerView recyclerView = findViewById(R.id.recycler_cart);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new CartRecyclerAdapter());
        // Обрабатываем нажатие на кнопку
        Button button = findViewById(R.id.buy_btn_cart);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemData.getInstance().performPurchase(Cart.getInstance());
                Cart.getInstance().clearCart();
                finish();
            }
        });
    }
}
