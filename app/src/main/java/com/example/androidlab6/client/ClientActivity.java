package com.example.androidlab6.client;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidlab6.myinterf.OnClick;
import com.example.androidlab6.R;
import com.example.androidlab6.admin.AdminActivity;
import com.example.androidlab6.cart.CartActivity;

public class ClientActivity extends FragmentActivity {
    ClientRecyclerFragment clientRecyclerFragment;
    ClientViewPagerFragment clientViewPagerFragment;
    final static String TAG_1 = "RECYCLER_FRAGMENT";
    final static String TAG_2 = "VIEW_PAGER_FRAGMENT";
    OnClick click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Создаем интерфейс, который будет вызываться при клике
        click = new OnClick() {
            @Override
            public void click(int position) {
                clientViewPagerFragment = new ClientViewPagerFragment(position);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_frame, clientViewPagerFragment, TAG_2);
                transaction.commit();
            }
        };
        // Создаем фрагмент и выводим его на экран
        clientRecyclerFragment = new ClientRecyclerFragment(click);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_frame, clientRecyclerFragment, TAG_1);
        transaction.commit();
        // Прописываем нажатие на кнопку Корзина
        Button button = findViewById(R.id.cart_btn_main);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
        // Прописываем нажатие на кнопку Админ
        Button adminButton = findViewById(R.id.admin_btn_main);
        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });
    }

    // Если при нажатии на кнопку НАЗАД, у нас ViewPager, то меняем его на список. Иначе выход
    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().findFragmentByTag(TAG_2) != null){
            clientRecyclerFragment = new ClientRecyclerFragment(click);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_frame, clientRecyclerFragment, TAG_1)
                    .commit();
        } else {
            finish();
        }
    }
}
