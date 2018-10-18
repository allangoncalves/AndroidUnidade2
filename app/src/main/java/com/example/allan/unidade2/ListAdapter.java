package com.example.allan.unidade2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class ListAdapter extends ListFragment {

    private ArrayAdapter<String> adapter;
    private OnItemClick listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(!(context instanceof OnItemClick)){
            throw new RuntimeException("It is not an OnItemClick");
        }
        listener = (OnItemClick) context;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        if(listener != null){
            listener.onClick(adapter.getItem(position), view, position);
        }
    }

    public void add(String name){
        adapter.add(name);
        adapter.notifyDataSetChanged();
    }

    public void update(String name, int position){
        adapter.remove(adapter.getItem(position));
        adapter.insert(name, position);
        adapter.notifyDataSetChanged();
    }

    public void remove(int position){
        adapter.remove(adapter.getItem(position));
        adapter.notifyDataSetChanged();
    }

    public ArrayAdapter getAdapter(){
        return adapter;
    }

    public interface OnItemClick{
        void onClick(String name, View view, int position);
    }
}
