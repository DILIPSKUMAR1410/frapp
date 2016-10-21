package com.lytyfy.deviab;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class RepaymentFragment extends ListFragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

    View v = inflater.inflate(
            R.layout.fragment_repayment, container, false);

        // Construct the data source
        ArrayList<User> arrayOfUsers = new ArrayList<User>();
// Create the adapter to convert the array to views
        UsersAdapter adapter = new UsersAdapter(getActivity(), arrayOfUsers);
// Attach the adapter to a ListView
        ListView listView = (ListView) v.findViewById(android.R.id.list);
        listView.setAdapter(adapter);

        // Add item to adapter


        for(int l=0; l<=5; l++){
            User newUser = new User("Nathan", "San Diego");
            adapter.add(newUser);
        }



        return v;
    }
}