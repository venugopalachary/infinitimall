package com.play4deal.infinitemall;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class BrandsListFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static final String TAG_POSTER_URL = "poster";
    public static final String TAG_POSTER_POSTERBRAND="brandid";
    public static final String TAG_POSTER_POSTERBRANDNAME="brandname";
    public static final String TAG_GRID = "grids";
    //Tag values to read from json
    public static final String TAG_IMAGE_URL = "brandlogo";
    public static final String TAG_BRANDNAME = "brandname";
    public static final String TAG_BRANDID = "brandid";
    //GridView Object
    private GridView gridView;
    //ArrayList for Storing image urls and titles
    private ArrayList<String> images;
    private ArrayList<String> brandnames;
    private ArrayList<String> brandid;

    RequestQueue posterRequestQueue,gridRequestQueue;
    private Button backbutton;
    private String category,sector;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View v;

    private Button button;

    private OnFragmentInteractionListener mListener;

    public BrandsListFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static BrandsListFragment newInstance(String param1, String param2) {
        BrandsListFragment fragment = new BrandsListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     /*   if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_brands_list, container, false);
        gridView=v.findViewById(R.id.gridView);
        backbutton=v.findViewById(R.id.backbutton);
        backbutton.setOnClickListener(this);
        Bundle bundle = this.getArguments();

        if(bundle != null){
            // handle your code here.
            category = bundle.getString("category");
            sector = bundle.getString("sector");
        }
      //  Bundle extras = v.getIntent().getExtras();

        images = new ArrayList<>();
        brandnames = new ArrayList<>();
        brandid=new ArrayList<>();

        loadData();
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       /* if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {



            case R.id.exploremall:
                //  Toast.makeText(getActivity().getApplicationContext(), v.getId()+"", Toast.LENGTH_SHORT).show();
                // Create new fragment and transaction
                android.app.Fragment customerData = new ShoppingMapFragment();
                FragmentTransaction transaction = getActivity().getFragmentManager().beginTransaction();
                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.fragment_container, customerData);
                transaction.addToBackStack(null);
                // Commit the transaction
                transaction.commit();
                break;

            case R.id.backbutton:
                //  Toast.makeText(getActivity().getApplicationContext(), v.getId()+"", Toast.LENGTH_SHORT).show();
                // Create new fragment and transaction
                android.app.Fragment homeFragment = new HomeFragment();
                FragmentTransaction hometransaction = getActivity().getFragmentManager().beginTransaction();
                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                hometransaction.replace(R.id.fragment_container, homeFragment);
                hometransaction.addToBackStack(null);
                // Commit the transaction
                hometransaction.commit();
                break;



            default:
                //     Toast.makeText(getActivity().getApplicationContext(), v.getId()+"", Toast.LENGTH_SHORT).show();
                break;

        }
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    // this method we developed here
    private void loadData() {


        StringRequest stringRequest=new StringRequest(Request.Method.POST, getString(R.string.CATEGORY_WISE_BRANDS), new Response.Listener <String>() {
            @Override
            public void onResponse(String response) {



                if (response.trim().equalsIgnoreCase("no brands found") ) {

                    Toast.makeText(getActivity(), "No Brands Found", Toast.LENGTH_LONG).show();
                } else {


                    try {

                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("categorywisebrands");
                        showGrid(jsonArray);
                       /* if (gridRequestQueue != null) {
                            gridRequestQueue.cancelAll(TAG_GRID);
                        }*/

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            //adding parameters to the request
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("category", category);
                params.put("sector",sector);
                params.put("kioskid", "p4d006");
                return params;
            }
        };
        // Set the tag on the request.
        stringRequest.setTag(TAG_GRID);
        gridRequestQueue= Volley.newRequestQueue(getActivity());
        gridRequestQueue.add(stringRequest);
    }


    private void showGrid(JSONArray jsonArray) {
        //Looping through all the elements of json array
        for (int i = 0; i < jsonArray.length(); i++) {
            //Creating a json object of the current index
            JSONObject obj = null;
            try {
                //getting json object from current index
                obj = jsonArray.getJSONObject(i);
                //getting image url and title from json object
                images.add(obj.getString(TAG_IMAGE_URL));
                brandnames.add(obj.getString(TAG_BRANDNAME));
                brandid.add(obj.getString(TAG_BRANDID));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

/*
        for(int i=0;i<brandid.size();i++)
        {
            Toast.makeText(getApplicationContext(),brandnames.get(i)+"=brandid",Toast.LENGTH_LONG).show();
        }*/
        //Creating GridViewAdapter Object
        GridViewAdapter gridViewAdapter = new GridViewAdapter(getActivity(), images,brandnames,brandid);

        //Adding adapter to gridview
        gridView.setAdapter(gridViewAdapter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //    Toast.makeText(getApplicationContext(),brandnames.get(position)+"",Toast.LENGTH_LONG).show();
                //  String brandname=brandnames.get(position);
                //    String selectedbrandid=brandid.get(position);
                Intent dummymap=new Intent(getActivity(), MainActivity.class);
                //   coupongo.putExtra("brandname",brandname);
                //  coupongo.putExtra("brandid",selectedbrandid);
                dummymap.putExtra("brandname",brandnames.get(position));
                startActivity(dummymap);

            }
        });
    }
}
