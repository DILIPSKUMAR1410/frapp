package com.lytyfy.deviab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by sonu on 29/10/16.
 */

public class BorrowersAdapter extends ArrayAdapter<InstallationBorrower> {
    public BorrowersAdapter(Context context, ArrayList<InstallationBorrower> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        InstallationBorrower user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.installation_borrower, parent, false);
        }
        // Lookup view for data population
        TextView borrowname = (TextView) convertView.findViewById(R.id.borrowname);
        TextView address = (TextView) convertView.findViewById(R.id.address);
        TextView mobile_number = (TextView) convertView.findViewById(R.id.mobile_number);
        TextView downpayment = (TextView) convertView.findViewById(R.id.downpayment);

        // Populate the data into the template view using the data object
        borrowname.setText(user.borrowname);
        address.setText(user.address);
        mobile_number.setText(user.mobile_number);
        downpayment.setText(user.downpayment);
        // Return the completed view to render on screen
        return convertView;
    }
}
