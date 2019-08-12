package com.play4deal.infinitemall;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


public class HomeFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private Bundle bundle ;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageView exploremall,shopping,dine,deals,salon,electronics,fashion,entertainment;

    private View v;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();

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
        v=inflater.inflate(R.layout.fragment_home, container, false);
        exploremall=v.findViewById(R.id.exploremall);
        shopping=v.findViewById(R.id.shopping);
        dine=v.findViewById(R.id.dine);
        deals=v.findViewById(R.id.deals);
        salon=v.findViewById(R.id.salon);
        electronics=v.findViewById(R.id.electronics);
        fashion=v.findViewById(R.id.fashion);
        entertainment=v.findViewById(R.id.entertainment);

        bundle=new Bundle();

         // click listeners
        exploremall.setOnClickListener(this);
        shopping.setOnClickListener(this);
        dine.setOnClickListener(this);
        deals.setOnClickListener(this);
        salon.setOnClickListener(this);
        electronics.setOnClickListener(this);
        fashion.setOnClickListener(this);
        entertainment.setOnClickListener(this);



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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {

      //  Toast.makeText(getActivity().getApplicationContext(), v.getId()+"", Toast.LENGTH_SHORT).show();

       switch (v.getId()) {



            case R.id.exploremall:
              //  Toast.makeText(getActivity().getApplicationContext(), v.getId()+"", Toast.LENGTH_SHORT).show();
                // Create new fragment and transaction
                Fragment customerData = new ShoppingMapFragment();
                FragmentTransaction transaction = getActivity().getFragmentManager().beginTransaction();
                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.fragment_container, customerData);
                transaction.addToBackStack(null);
                // Commit the transaction
                transaction.commit();
                break;

           case R.id.shopping:
               //  Toast.makeText(getActivity().getApplicationContext(), v.getId()+"", Toast.LENGTH_SHORT).show();
               // Create new fragment and transaction
               Fragment shopping = new BrandsListFragment();
               FragmentTransaction shoppingtransaction = getActivity().getFragmentManager().beginTransaction();
               bundle.putString("category","lifestyle");
               bundle.putString("sector","lifestyle");
               shopping.setArguments(bundle);
               // Replace whatever is in the fragment_container view with this fragment,
               // and add the transaction to the back stack
               shoppingtransaction.replace(R.id.fragment_container, shopping);
               shoppingtransaction.addToBackStack(null);
               // Commit the transaction
               shoppingtransaction.commit();
               break;


           case R.id.dine:
               //  Toast.makeText(getActivity().getApplicationContext(), v.getId()+"", Toast.LENGTH_SHORT).show();
               // Create new fragment and transaction
               Fragment dine = new BrandsListFragment();
               FragmentTransaction dinetransaction = getActivity().getFragmentManager().beginTransaction();
               bundle.putString("category","foodandbeverages");
               bundle.putString("sector","foodandbeverages");
               dine.setArguments(bundle);
               // Replace whatever is in the fragment_container view with this fragment,
               // and add the transaction to the back stack
               dinetransaction.replace(R.id.fragment_container, dine);
               dinetransaction.addToBackStack(null);
               // Commit the transaction
               dinetransaction.commit();
               break;

          case R.id.deals:
               //  Toast.makeText(getActivity().getApplicationContext(), v.getId()+"", Toast.LENGTH_SHORT).show();
               // Create new fragment and transaction
               Fragment dealsactivity = new DealsActivityFragment();
               FragmentTransaction dealsactivitytransaction = getActivity().getFragmentManager().beginTransaction();
              /* bundle.putString("category","lifestyle");
               bundle.putString("sector","lifestyle");
              dealsactivity.setArguments(bundle);*/
               // Replace whatever is in the fragment_container view with this fragment,
               // and add the transaction to the back stack
              dealsactivitytransaction.replace(R.id.fragment_container, dealsactivity);
              dealsactivitytransaction.addToBackStack(null);
               // Commit the transaction
              dealsactivitytransaction.commit();
               break;

           case R.id.salon:
               //  Toast.makeText(getActivity().getApplicationContext(), v.getId()+"", Toast.LENGTH_SHORT).show();
               // Create new fragment and transaction
               Fragment salon = new BrandsListFragment();
               FragmentTransaction salontransaction = getActivity().getFragmentManager().beginTransaction();
               bundle.putString("category","salon");
               bundle.putString("sector","salon");
               salon.setArguments(bundle);
               // Replace whatever is in the fragment_container view with this fragment,
               // and add the transaction to the back stack
               salontransaction.replace(R.id.fragment_container, salon);
               salontransaction.addToBackStack(null);
               // Commit the transaction
               salontransaction.commit();
               break;

           case R.id.electronics:
               //  Toast.makeText(getActivity().getApplicationContext(), v.getId()+"", Toast.LENGTH_SHORT).show();
               // Create new fragment and transaction
               Fragment electronics = new BrandsListFragment();
               FragmentTransaction electronicstransaction = getActivity().getFragmentManager().beginTransaction();
               bundle.putString("category","lifestyle");
               bundle.putString("sector","lifestyle");
               electronics.setArguments(bundle);
               // Replace whatever is in the fragment_container view with this fragment,
               // and add the transaction to the back stack
               electronicstransaction.replace(R.id.fragment_container, electronics);
               electronicstransaction.addToBackStack(null);
               // Commit the transaction
               electronicstransaction.commit();
               break;

           case R.id.fashion:
               //  Toast.makeText(getActivity().getApplicationContext(), v.getId()+"", Toast.LENGTH_SHORT).show();
               // Create new fragment and transaction
               Fragment fashion = new BrandsListFragment();
               FragmentTransaction fashiontransaction = getActivity().getFragmentManager().beginTransaction();
               bundle.putString("category","fashion");
               bundle.putString("sector","fashion");
               fashion.setArguments(bundle);
               // Replace whatever is in the fragment_container view with this fragment,
               // and add the transaction to the back stack
               fashiontransaction.replace(R.id.fragment_container, fashion);
               fashiontransaction.addToBackStack(null);
               // Commit the transaction
               fashiontransaction.commit();
               break;

           case R.id.entertainment:
               //  Toast.makeText(getActivity().getApplicationContext(), v.getId()+"", Toast.LENGTH_SHORT).show();
               // Create new fragment and transaction
               Fragment entertainment = new BrandsListFragment();
               FragmentTransaction entertainmenttransaction = getActivity().getFragmentManager().beginTransaction();
               bundle.putString("category","games");
               bundle.putString("sector","games");
               entertainment.setArguments(bundle);
               // Replace whatever is in the fragment_container view with this fragment,
               // and add the transaction to the back stack
               entertainmenttransaction.replace(R.id.fragment_container, entertainment);
               entertainmenttransaction.addToBackStack(null);
               // Commit the transaction
               entertainmenttransaction.commit();
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
}
