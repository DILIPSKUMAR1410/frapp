package com.lytyfy.deviab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sonu on 29/10/16.
 */

public class InstallationBorrower {
    public String borrowname;
    public String address;
    public String mobile_number;
    public String downpayment;


    public InstallationBorrower(String borrowname,String address,String mobile_number,String downpayment) {
        this.borrowname = borrowname;
        this.address = address;
        this.mobile_number = mobile_number;
        this.downpayment = downpayment;
    }


}
