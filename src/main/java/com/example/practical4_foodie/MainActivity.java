package com.example.practical4_foodie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(String.valueOf(this), "main");
        t = findViewById(R.id.welcome);
        //t.setOnClickListener(this::onClick);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        Log.d(String.valueOf(this), "hello");
        mi.inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.pasta:
                Toast.makeText(this, "Pasta", Toast.LENGTH_SHORT).show();
                Intent pasta = new Intent(this, Pasta.class);
                startActivity(pasta);
                return true;
            case R.id.pizza:
                Toast.makeText(this, "Pizza", Toast.LENGTH_SHORT).show();
                Intent pizza = new Intent(this, Pizza.class);
                startActivity(pizza);
                return true;
            case R.id.cart:
                Toast.makeText(this, "Shopping Cart", Toast.LENGTH_SHORT).show();
                Intent cart = new Intent(this, Cart.class);
                startActivity(cart);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    public void display(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.getMenuInflater().inflate(R.menu.menu2, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
         public boolean onMenuItemClick(MenuItem item) {
             Log.d(String.valueOf(this), "got it");
             return true;
         }
     }
        );
    }
}