package com.lytyfy.deviab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vasu on 21/10/16.
 */

public class UsersAdapter extends ArrayAdapter<User> {
    public UsersAdapter(Context context, ArrayList<User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        User user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
        }
        // Lookup view for data population
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView loanamount = (TextView) convertView.findViewById(R.id.loanamount);
        TextView emi = (TextView) convertView.findViewById(R.id.emi);
        // Populate the data into the template view using the data object
        name.setText(user.name);
        loanamount.setText(user.loanamount);
        emi.setText(user.emi);
        // Return the completed view to render on screen
        return convertView;
    }
}
