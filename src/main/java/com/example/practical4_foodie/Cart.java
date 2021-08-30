package com.example.practical4_foodie;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {
    int cost;
    ArrayList<String> pasta=new ArrayList<>();
    ArrayList<String>pizza = new ArrayList<>();
    Button cost_btn;
    ListView ls, ls_pizza;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);
        pasta = getIntent().getStringArrayListExtra("pasta");
        pizza = getIntent().getStringArrayListExtra("pizza");
        ls = findViewById(R.id.list);
        ls_pizza = findViewById(R.id.list_pizza);
        int pasta_value = getIntent().getIntExtra("pasta_cost", 0);
        int pizza_value = getIntent().getIntExtra("pizza_cost", 0);
        cost+=pasta_value+pizza_value;
        cost_btn=findViewById(R.id.button);
        if(pasta!=null) {
            String[] list = new String[pasta.size()];
            for (int i = 0; i < pasta.size(); i++) {
                list[i] = pasta.get(i);
            }
                ls.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
        }
        if(pizza!=null) {
            String[] list_pizza = new String[pizza.size()];
            for (int i = 0; i < pizza.size(); i++) {
                list_pizza[i] = pizza.get(i);
            }
                ls_pizza.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, list_pizza));
        }
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
        switch (item.getItemId()) {
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

    public void get_cost(View v){
        TextView biller = findViewById(R.id.bill);
        String text = biller.getText().toString();
        text+=cost;
        biller.setText(text);

    }

    protected void onPause() {
        super.onPause();
        Log.d(String.valueOf(this), "paused cart");

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putStringArrayList("pasta", pasta);
        outState.putStringArrayList("pizza", pizza);
        outState.putInt("cost", cost);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pasta = savedInstanceState.getStringArrayList("pasta");
        pizza = savedInstanceState.getStringArrayList("pizza");
        cost += savedInstanceState.getInt("cost");
    }
}

