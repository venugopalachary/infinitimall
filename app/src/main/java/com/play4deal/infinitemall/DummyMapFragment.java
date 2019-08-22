package com.play4deal.infinitemall;



import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;


import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.onurkaganaldemir.ktoastlib.KToast;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DummyMapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DummyMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DummyMapFragment extends Fragment implements  View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText userenterednumber;

    private OnFragmentInteractionListener mListener;
    private View v;
    private Button backbutton,sms,qrcode;
    private AlertDialog alertDialog;
    private RequestQueue mQueue;
    private WebView webview;

    public DummyMapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DummyMapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DummyMapFragment newInstance(String param1, String param2) {
        DummyMapFragment fragment = new DummyMapFragment();
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
        v=inflater.inflate(R.layout.fragment_dummy_map, container, false);
        webview=v.findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("https://maps.mapwize.io/#/f/p/infinitimall/vivo_mobile/t/p/infinitimall/faces?k=8b40023a313067cc&z=20.824&embed=true&menu=false&venueId=5d5b8e81cddcab00160347d8&organizationId=5d29996b71292f00165d4c83&follow=true");
        webview.setWebViewClient(new WebViewClient());
        backbutton=v.findViewById(R.id.backbutton);
        sms=v.findViewById(R.id.sms);
        qrcode=v.findViewById(R.id.qrcode);
        mQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });
        qrcode.setOnClickListener(this);
        backbutton.setOnClickListener(this);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void showCustomDialog() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = getActivity().findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.smsrouteshare, viewGroup, false);

        Button submitbutton=dialogView.findViewById(R.id.submitbutton);

        userenterednumber=dialogView.findViewById(R.id.mobilenumber);


        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            builder = new AlertDialog.Builder(getContext());
        }

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        alertDialog = builder.create();
        alertDialog.show();

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mn=userenterednumber.getText().toString();
                //       Log.i("userenteredotp",userenteredotp);
                if(mn.isEmpty() ) {
                    KToast.warningToast(getActivity(), "Please enter a mobilenumber.", Gravity.TOP, KToast.LENGTH_AUTO);

                    //   Toast.makeText(getApplicationContext(),"please enter a otp",Toast.LENGTH_LONG).show();
                }

                if (mn.length() < 10 || mn.length() > 10) {

                    KToast.warningToast(getActivity(), "Please Enter a 10-digit  Number.", Gravity.TOP, KToast.LENGTH_AUTO);

                }else
                {
                    sendingUserData(mn);
                    alertDialog.dismiss();
                }


            }
        });
    }

    private void sendingUserData(final String mobilenumber) {


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getString(R.string.shareroutesms),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        MainActivity myActivity = (MainActivity)getActivity();

                        FragmentManager fm = myActivity.getFragmentManager();
                        // replace
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.fragment_container, new FeedbackFragment());
                        ft.commit();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //     Log.i("error",error+"");
            }
        }) {
            //adding parameters to the request
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("mobilenumber", mobilenumber);
                return params;
            }
        };
        // Add the request to the RequestQueue.
        mQueue.add(stringRequest);

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }
}
