package com.play4deal.infinitemall;




import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener {


    SharedPreferences sp;
    String employeeId,offline;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private Context context;
    Calendar calendar;
    private Toast toast;
    private ImageView searchbarbutton;
    private Timer timer;

    ViewPager moviesViewPager,eventsviewPager;
    int images[] = {R.drawable.saaho, R.drawable.war, R.drawable.mangal};
    int events[]={R.drawable.anniversary,R.drawable.dance,R.drawable.food};
    MoviesPagerAdapter myCustomPagerAdapter;
    EventsPagerAdapter eventsPagerAdapter;
    int currenteventIndex=0;
    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchbarbutton=findViewById(R.id.searchbarbutton);
        searchbarbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new ProductBrandSearchFragment());
            }
        });

        moviesViewPager = (ViewPager)findViewById(R.id.moviesviewPager);
        eventsviewPager=(ViewPager)findViewById(R.id.eventsviewPager);


        myCustomPagerAdapter = new MoviesPagerAdapter(MainActivity.this, images);
        eventsPagerAdapter=new EventsPagerAdapter(MainActivity.this,events);


        moviesViewPager.setAdapter(myCustomPagerAdapter);
        eventsviewPager.setAdapter(eventsPagerAdapter);

        setupAutoMoviePager();

        setupAutoEventsPager();

        //loading the default fragment
        loadFragment(new HomeFragment());

    }



    private void setupAutoMoviePager()
    {
        final Handler handler = new Handler();

        final Runnable update = new Runnable() {
            public void run()
            {

                moviesViewPager.setCurrentItem(currentPage, true);
                if(currentPage == images.length)
                {
                    currentPage = 0;
                }
                else
                {
                    ++currentPage ;
                }
            }
        };


        timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(update);
            }
        }, 500, 2500);
    }



    private void setupAutoEventsPager()
    {
        final Handler handler = new Handler();

        final Runnable update = new Runnable() {
            public void run()
            {

                eventsviewPager.setCurrentItem(currenteventIndex, true);
                if(currenteventIndex == images.length)
                {
                    currenteventIndex = 0;
                }
                else
                {
                    ++currenteventIndex ;
                }
            }
        };


        timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(update);
            }
        }, 500, 2500);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    //here we are loading fragments
    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    /*  .addToBackStack(null)*/
                    .commit();


            return true;
        }
        return false;
    }



    public class MoviesPagerAdapter extends PagerAdapter {
        Context context;
        int images[];
        LayoutInflater layoutInflater;


        public MoviesPagerAdapter(Context context, int images[]) {
            this.context = context;
            this.images = images;
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View itemView = layoutInflater.inflate(R.layout.moviesview, container, false);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
            imageView.setImageResource(images[position]);

            container.addView(itemView);

            //listening to image click
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                //    Toast.makeText(context, "you clicked image " + (position + 1), Toast.LENGTH_LONG).show();
                }
            });

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }


    public class EventsPagerAdapter extends PagerAdapter {
        Context context;
        int imagesk[];
        LayoutInflater layoutInflater;


        public EventsPagerAdapter(Context context, int imagesk[]) {
            this.context = context;
            this.imagesk = imagesk;
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return imagesk.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View itemView = layoutInflater.inflate(R.layout.eventsview, container, false);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
            imageView.setImageResource(imagesk[position]);

            container.addView(itemView);

            //listening to image click
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 //   Toast.makeText(context, "you clicked image " + (position + 1), Toast.LENGTH_LONG).show();
                }
            });

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }

}
