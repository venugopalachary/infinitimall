package com.play4deal.infinitemall;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyListAdapter extends ArrayAdapter<String> {

     private final Activity context;
    ArrayList<String> brandname;
     ArrayList<String> floor;
    ArrayList<String> category;
    ArrayList<String> products;

    public MyListAdapter(Activity context, ArrayList<String> brandname, ArrayList<String> floor, ArrayList<String> category, ArrayList<String> products) {
        super(context, R.layout.mylist, brandname);
        this.context = context;
        this.brandname = brandname;
        this.floor = floor;
        this.category = category;
        this.products = products;
    }


    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);
        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        TextView floorText = (TextView) rowView.findViewById(R.id.floor);
        titleText.setText(brandname.get(position));
        floorText.setText( floor.get(position) );
        return rowView;

    };


}