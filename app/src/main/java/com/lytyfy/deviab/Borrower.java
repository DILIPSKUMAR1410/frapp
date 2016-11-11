package com.lytyfy.deviab;

/**
 * Created by vasu on 21/10/16.
 */

public class Borrower {
    public String borrower_id;
    public String name;
    public String loanamount;
    public String emi;
    public String tenure_left;
    public String address;


    public Borrower(String borrower_id, String name, String loanamount, String emi, String tenure_left, String address) {
        this.borrower_id = borrower_id;

        this.name = name;
        this.loanamount = loanamount;
        this.emi = emi;
        this.tenure_left = tenure_left;
        this.address = address;
    }

}
