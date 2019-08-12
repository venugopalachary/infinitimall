package com.play4deal.infinitemall;

public class Deals {
    private String brandname,dealimage,couponcode,dealdescription,validdate,rupees;
    //  private Boolean status;
    public Deals(String brandname, String dealimage, String couponcode, String dealdescription, String validdate, String rupees){

        this.brandname=brandname;
        this.dealimage=dealimage;
        this.couponcode=couponcode;
        this.dealdescription=dealdescription;
        this.validdate=validdate;
        this.rupees=rupees;

        // this.status=status;
    }
    public String getBrandname(){
        return brandname;
    }
    public String getDealimage(){
        return dealimage;
    }
    public String getCouponcode(){
        return couponcode;
    }
    public String getDealdescription(){
        return dealdescription;
    }
    public String getValiddate(){
        return validdate;
    }
    public String getRupees(){
        return rupees;
    }
}
