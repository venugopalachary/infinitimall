package com.play4deal.infinitemall;

import android.app.Activity;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
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


public class ProductBrandSearchFragment extends Fragment implements  View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button backbutton;
    private ImageButton searchbutton;
    ListView listView;
    SearchView searchView;
    private ArrayList<String> brandname;
    private ArrayList<String> floor;
    private ArrayList<String> category;
    private ArrayList<String> products;

    private ArrayList<String> brandnamefilter;
    private ArrayList<String> floorfilter;
    private ArrayList<String> categoryfilter;
    private ArrayList<String> productsfilter;
    MyListAdapter adapter;
    private EditText usersintrest;
    private String searchwordphrase;
    private View v;


    private OnFragmentInteractionListener mListener;

    public ProductBrandSearchFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ProductBrandSearchFragment newInstance(String param1, String param2) {
        ProductBrandSearchFragment fragment = new ProductBrandSearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        v=inflater.inflate(R.layout.fragment_product_brand_search, container, false);
        backbutton = v.findViewById(R.id.backbutton);
        searchbutton=v.findViewById(R.id.searchbutton);
        backbutton.setOnClickListener(this);
        listView=(ListView)v.findViewById(R.id.list);
        usersintrest=v.findViewById(R.id.usersintrest);

        brandname = new ArrayList<>();
        floor = new ArrayList<>();
        category = new ArrayList<>();
        products = new ArrayList<>();

        // these are filtered arrays
        brandnamefilter = new ArrayList<>();
        floorfilter = new ArrayList<>();
        categoryfilter = new ArrayList<>();
        productsfilter = new ArrayList<>();

        loadLocations();
        listView.setTextFilterEnabled(true);


        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchwordphrase=usersintrest.getText().toString();
                if(searchwordphrase.length()<=0)
                {
                    Toast.makeText(getActivity().getApplicationContext()," Please Fill Data",Toast.LENGTH_LONG).show();
                }else
                {
                    loadFilteredBrands(searchwordphrase);
                }


            }
        });
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
      /*  if (context instanceof OnFragmentInteractionListener) {
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

            case R.id.backbutton:
                //    Toast.makeText(getActivity().getApplicationContext(), v.getId()+"", Toast.LENGTH_SHORT).show();
                // Create new fragment and transaction
                android.app.Fragment customerData = new HomeFragment();
                FragmentTransaction transaction = getActivity().getFragmentManager().beginTransaction();
                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.fragment_container, customerData);
                transaction.addToBackStack(null);
                // Commit the transaction
                transaction.commit();
                break;




            default:
                //Toast.makeText(getActivity().getApplicationContext(), v.getId()+"", Toast.LENGTH_SHORT).show();
                break;

        }
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



    private void loadFilteredBrands(final String searchdtext) {
        StringRequest stringRequest = new StringRequest( Request.Method.POST, getString(R.string.filteredBrandsList),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject = new JSONObject(response);

                            JSONArray array = jsonObject.getJSONArray("fetchingbrands");


                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {


                                //Creating a json object of the current index
                                JSONObject obj = null;
                                try {
                                    //getting json object from current index
                                    obj = array.getJSONObject( i );

                                    //getting image url and title from json object
                                    brandnamefilter.add( obj.getString( "brandname" ) );
                                    floorfilter.add( obj.getString( "floor" ) );
                                    categoryfilter.add( obj.getString( "category" ) );
                                    productsfilter.add( obj.getString( "productsavailability" ) );

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }

                            adapter=new MyListAdapter(getActivity(), brandnamefilter, floorfilter,categoryfilter,productsfilter);
                            adapter.notifyDataSetChanged();
                            listView.setAdapter(adapter);


                            listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    //   Toast.makeText( getApplicationContext(),floor.get( position ),Toast.LENGTH_LONG ).show();

                                    Intent dummymap=new Intent(getActivity(), MainActivity.class);
                                    //   coupongo.putExtra("brandname",brandname);
                                    //  coupongo.putExtra("brandid",selectedbrandid);
                                    dummymap.putExtra("brandname",brandnamefilter.get(position));
                                    startActivity(dummymap);
                                }
                            } );


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText( (Activity) getActivity().getApplicationContext(),error+"",Toast.LENGTH_LONG ).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<>();

                param.put("searchedtext",searchdtext);

                return param;
            }
        };

        //adding our stringrequest to queue
        Volley.newRequestQueue(getActivity()).add(stringRequest);

    }


    private void loadLocations() {   StringRequest stringRequest = new StringRequest( Request.Method.GET, getString(R.string.brandsList),
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {

                        JSONObject jsonObject = new JSONObject(response);

                        JSONArray array = jsonObject.getJSONArray("fetchingbrands");


                        //traversing through all the object
                        for (int i = 0; i < array.length(); i++) {


                            //Creating a json object of the current index
                            JSONObject obj = null;
                            try {
                                //getting json object from current index
                                obj = array.getJSONObject( i );

                                //getting image url and title from json object
                                brandname.add( obj.getString( "brandname" ) );
                                floor.add( obj.getString( "floor" ) );
                                category.add( obj.getString( "category" ) );
                                products.add( obj.getString( "productsavailability" ) );

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }

                        adapter=new MyListAdapter(getActivity(), brandname, floor,category,products);
                        adapter.notifyDataSetChanged();
                        listView.setAdapter(adapter);


                        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                //  Toast.makeText( getApplicationContext(),floor.get( position ),Toast.LENGTH_LONG ).show();


                                Intent dummymap=new Intent(getActivity(), MainActivity.class);
                                //   coupongo.putExtra("brandname",brandname);
                                //  coupongo.putExtra("brandid",selectedbrandid);
                                dummymap.putExtra("brandname",brandname.get(position));
                                startActivity(dummymap);
                            }
                        } );


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText( getActivity(),error+"",Toast.LENGTH_LONG ).show();
                }
            })


            ;

        //adding our stringrequest to queue
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }
}
