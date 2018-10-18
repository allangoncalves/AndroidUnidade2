package com.example.allan.unidade2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements ListAdapter.OnItemClick {

    private ListAdapter listAdapter;
    private Toolbar toolbar;
    private int selectedItem;
    private View itemView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        selectedItem = -1;
        listAdapter = (ListAdapter) getSupportFragmentManager().getFragments().get(0);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuAdd:
                CreateItemDialog.show(getSupportFragmentManager(), new CreateItemDialog.OnTextListener() {
                    @Override
                    public void onSetText(String name) {
                        listAdapter.add(name);
                    }
                });
                break;
            case R.id.editMenuItem:
                CreateItemDialog.show(getSupportFragmentManager(), new CreateItemDialog.OnTextListener() {
                    @Override
                    public void onSetText(String name) {
                        listAdapter.update(name, selectedItem);
                    }
                });
                break;
            case R.id.delMenuItem:
                listAdapter.remove(selectedItem);
                toolbar.getMenu().clear();
                selectedItem = -1;
                getMenuInflater().inflate(R.menu.add_menu, toolbar.getMenu());
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.add_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(String name, View view, int position) {
        if(this.selectedItem==-1){
            this.selectedItem = position;
            this.toolbar.getMenu().clear();
            getMenuInflater().inflate(R.menu.edt_del_menu,this.toolbar.getMenu());
            view.setBackgroundColor(Color.LTGRAY);
            this.itemView = view;
        }else{
            if(this.selectedItem == position){
                this.selectedItem = -1;
                this.toolbar.getMenu().clear();
                getMenuInflater().inflate(R.menu.add_menu,this.toolbar.getMenu());
                view.setBackgroundColor(Color.TRANSPARENT);
                this.itemView = view;
            }
        }
    }
}
