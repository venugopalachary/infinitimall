package com.play4deal.infinitemall;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;


import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class DealsActivityFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View v;

    private DealAdapter dealAdapter;
    private RecyclerView recyclerView;
    List<Deals> deals_list;
    private Button backbutton,buynow;

    private OnFragmentInteractionListener mListener;

    public DealsActivityFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static DealsActivityFragment newInstance(String param1, String param2) {
        DealsActivityFragment fragment = new DealsActivityFragment();
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
        v=inflater.inflate(R.layout.fragment_deals_activity, container, false);
        recyclerView=v.findViewById( R.id.recyclerView );
        backbutton =v.findViewById(R.id.backbutton);
        buynow=recyclerView.findViewById(R.id.buynow);
        backbutton.setOnClickListener(this);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        deals_list = new ArrayList<>();
        getGrid();
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


    private void getGrid() {
        StringRequest stringRequest = new StringRequest( Request.Method.GET, getString( R.string.DEAL_URL ),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equalsIgnoreCase( "no brands found" )) {

                            Toast.makeText(getActivity(), "No Brands Found", Toast.LENGTH_LONG ).show();
                        } else {

                            //    Toast.makeText( getActivity(),""+response+"",Toast.LENGTH_LONG ).show();
                            try {
                                //converting the string to json array object
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = jsonObject.getJSONArray("categorywisebrands");
                                //converting the string to json array object
                                //traversing through all the object
                                for (int i = 0; i < jsonArray.length(); i++) {

                                    //getting product object from json array
                                    JSONObject deals = jsonArray.getJSONObject( i );

                                    //adding the product to product list
                                    deals_list.add( new Deals(
                                            deals.getString( "brandname" ),
                                            deals.getString( "dealimage" ),
                                            deals.getString( "couponcode" ),
                                            deals.getString( "dealdescription" ),
                                            deals.getString( "validdate" ),
                                            deals.getString( "rupees" )

                                    ) );
                                }

                                //creating adapter object and setting it to recyclerview
                                dealAdapter = new DealAdapter(getActivity(), deals_list );
                                dealAdapter.notifyDataSetChanged();
                                recyclerView.setAdapter( dealAdapter );
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })
//        {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> param = new HashMap<>();
//
//               // param.put("kioskid",kioskid);
//
//                return param;
//            }
//        }
                ;

        //adding our stringrequest to queue
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backbutton:
                //  Toast.makeText(getActivity().getApplicationContext(), v.getId()+"", Toast.LENGTH_SHORT).show();
                // Create new fragment and transaction
                Fragment customerData = new DummyMapFragment();
                FragmentTransaction transaction = getActivity().getFragmentManager().beginTransaction();
                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.fragment_container, customerData);
                transaction.addToBackStack(null);
                // Commit the transaction
                transaction.commit();
                break;


        case R.id.buynow:
        //  Toast.makeText(getActivity().getApplicationContext(), v.getId()+"", Toast.LENGTH_SHORT).show();
        // Create new fragment and transaction
        Fragment buynow = new DummyMapFragment();
        FragmentTransaction buynowtransaction = getActivity().getFragmentManager().beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
            buynowtransaction.replace(R.id.fragment_container, buynow);
            buynowtransaction.addToBackStack(null);
        // Commit the transaction
            buynowtransaction.commit();
        break;
    }

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
