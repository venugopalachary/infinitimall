package com.play4deal.infinitemall;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class DealAdapter extends RecyclerView.Adapter<DealAdapter.ViewHolder>{
    List<Deals> deals_list;
    private Context context;

    public DealAdapter(Context context, List<Deals> deals_list) {
        this.context = context;
        this.deals_list = deals_list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Deals dealData=deals_list.get(position);
        //loading the image
        //loading the image
        Glide.with(context).load(dealData.getDealimage()).into(holder.dealimage);
        holder.description.setText( dealData.getDealdescription());
        holder.enddate.setText( dealData.getValiddate());
        holder.rupees.setText( dealData.getRupees() );
//        holder.imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(),"youclicked  " +   brandData.getBrandid(),Toast.LENGTH_LONG).show();
//            }
//        });
        holder.buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(),"youclicked  " +   dealData.getCouponcode(),Toast.LENGTH_LONG).show();
                Intent coupongo=new Intent(context, MainActivity.class);
                coupongo.putExtra("brandname",dealData.getBrandname());
                coupongo.putExtra("couponcode",dealData.getCouponcode());
                coupongo.putExtra( "enddate",dealData.getValiddate());
                coupongo.putExtra( "description",dealData.getDealdescription() );
                coupongo.putExtra( "rupees",dealData.getRupees() );
               context.startActivity(coupongo);
            }
        });
        holder.tc.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        } );


        //  holder.textViewPrice.setText(listData.getWebsite());

//     holder.linearLayout.setOnClickListener(new View.OnClickListener() {
//
//
//          @Override
//            public void onClick(View v) {
//
//             // Toast.makeText(context,listData.getUsername(),Toast.LENGTH_LONG).show();
//               Context context = v.getContext();
//
//
//               Intent intent = new Intent(context,Claimed.class);
//            //   intent.putExtra("id",listData.getId());
////                intent.putExtra("username",listData.getUsername());
////                intent.putExtra("mobilenumber",listData.getMobilenumber());
////                intent.putExtra("coupon",listData.getCoupon());
//        //        intent.putExtra("website",listData.getWebsite());
//
//                context.startActivity(intent);
//            }
//        });
    }
    /*  public interface RecyclerViewClickListener {
          void onClick(View view, int position);
      }*/
    @Override
    public int getItemCount() {
        return deals_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //  private   TextView brandid;
        ImageView dealimage;
        Button buynow;
        TextView description,enddate,rupees,tc;
       // ToggleButton wishlist;
//        ArrayList<Product> products=new ArrayList<Product>();
//        Context context;

        private LinearLayout linearLayout;
        public ViewHolder(View view){

            super(view);

            view.setOnClickListener(this);
            // linearLayout=view.findViewById(R.id.linearlayout);
            tc=view.findViewById( R.id.tc );
            dealimage=view.findViewById(R.id.dealimage);
            description=view.findViewById( R.id.description );
            enddate=view.findViewById( R.id.enddate );
            rupees=view.findViewById( R.id.rupees );
            buynow=view.findViewById(R.id.buynow);

            //  brandid= itemView.findViewById(R.id.brandid);

        }


        @Override
        public void onClick(View v){
            // Toast.makeText(context, String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
           /* Context context = v.getContext();
            Intent intent = new Intent(context, Claimed.class);
            context.startActivity(intent);
*/
        }
    }

}
