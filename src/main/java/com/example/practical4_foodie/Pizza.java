package com.example.practical4_foodie;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;


public class Pizza extends AppCompatActivity implements View.OnCreateContextMenuListener, View.OnClickListener {
    ImageView pasta1, pasta2;
    static ArrayList<String> arr = new ArrayList<>();
    static int cost=0;
    int pas1=0, pas2=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);
        pasta1 = findViewById(R.id.pizza1);
        pasta2 = findViewById(R.id.pizza2);
        pasta1.setOnCreateContextMenuListener(this);
        pasta2.setOnCreateContextMenuListener(this);
        pasta1.setOnClickListener(this);
        pasta2.setOnClickListener(this);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater mi = getMenuInflater();
        Log.d(String.valueOf(this), "hello2");
        if(v.getId()==R.id.pizza1 && pas1==0){
            cost+=299;
            arr.add("Pizza1");
            pas1+=1;
        }else{
            if(pas2==0 && v.getId()==R.id.pizza2) {
                cost += 349;
                arr.add("Pizza2");
                pas2+=1;
            }
        }
        mi.inflate(R.menu.item_adder, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.caps:
                Toast.makeText(this, "Capsicum", Toast.LENGTH_SHORT).show();
                arr.add("caps");
                cost+=20;
                return true;
            case R.id.mush:
                Toast.makeText(this, "Mushroom", Toast.LENGTH_SHORT).show();
                arr.add("mush");cost+=30;
                return true;
            case R.id.corn:
                Toast.makeText(this, "Corn", Toast.LENGTH_SHORT).show();
                arr.add("corn");cost+=25;
                return true;
            case R.id.pep:
                Toast.makeText(this, "Pepper", Toast.LENGTH_SHORT).show();
                arr.add("pep");cost+=10;
                break;
            case R.id.mac:
                Toast.makeText(this, "Macaroni", Toast.LENGTH_SHORT).show();
                arr.add("mac");cost+=40;
                break;
            case R.id.cheese:
                Toast.makeText(this, "Cheese", Toast.LENGTH_SHORT).show();
                arr.add("cheese");cost+=50;
                break;
            case R.id.add:
                Toast.makeText(this, "Added"+arr.toString(), Toast.LENGTH_SHORT).show();
                if(arr.contains("Pizza1")){
                    pas1=0;
                }else{
                    if(arr.contains("Pizza2"))
                        pas2=0;
                }
//                Intent pasta = new Intent(this, Cart.class);
//                pasta.putStringArrayListExtra("pizza", arr);
//                pasta.putExtra("pizza_cost", cost);
//                startActivity(pasta);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }


//    public void displaypopup(View v) {
//        PopupMenu popup = new PopupMenu(this, v);
//        popup.getMenuInflater().inflate(R.menu.menu2, popup.getMenu());
//
//        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//             public boolean onMenuItemClick(MenuItem item) {
//                 Log.d(String.valueOf(this), "got it");
//                 switch (item.getItemId()) {
//                     case R.id.add:
//                         Toast.makeText(getApplicationContext(), "Added" + arr.toString(), Toast.LENGTH_SHORT).show();
//                         if (arr.contains("Pasta1")) {
//                             pas1 = 0;
//                         } else {
//                             if (arr.contains("Pasta2"))
//                                 pas2 = 0;
//                         }
//                         Intent pasta = new Intent(getApplicationContext(), Cart.class);
//                         pasta.putStringArrayListExtra("pasta", arr);
//                         pasta.putExtra("pasta_cost", cost);
//                         startActivity(pasta);
//                         break;
//                 }
//                 return true;
//             }
//         }
//        );
//    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(String.valueOf(this), "paused pizza");


    }


    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.pizza1 && pas1==0){
            cost+=299;
            arr.add("Pizza1");
            pas1+=1;
        }else{
            if(pas2==0 && view.getId()==R.id.pizza2) {
                cost += 349;
                arr.add("Pizza2");
                pas2+=1;
            }
        }
            PopupMenu popupMenu = new PopupMenu(Pizza.this, view);
            popupMenu.getMenuInflater().inflate(R.menu.menu2, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    Log.d(String.valueOf(this), "got it");
                    switch (menuItem.getItemId()) {
                        case R.id.add:

                            Toast.makeText(getApplicationContext(), "Added" + arr.toString(), Toast.LENGTH_SHORT).show();
                            if (arr.contains("Pizza1")) {
                                pas1 = 0;
                            } else {
                                if (arr.contains("Pizza2"))
                                    pas2 = 0;
                            }
//                            Intent pasta = new Intent(getApplicationContext(), Cart.class);
//                            pasta.putStringArrayListExtra("pizza", arr);
//                            pasta.putExtra("pizza_cost", cost);
//                            startActivity(pasta);
                            break;
                    }
                    return true;
                }
            });
            popupMenu.show();
        }

    static ArrayList<String> getArr(){
        return arr;
    }
    static int getCost(){
        return cost;
    }
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
    }

