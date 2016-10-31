package com.lytyfy.deviab;

import android.app.ListFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class InstallationFragment extends ListFragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View v = inflater.inflate(
                R.layout.fragment_installation, container, false);

        // Construct the data source
        ArrayList<InstallationBorrower> arrayOfUsers = new ArrayList<InstallationBorrower>();
// Create the adapter to convert the array to views
        BorrowersAdapter adapter = new BorrowersAdapter(getActivity(), arrayOfUsers);
// Attach the adapter to a ListView
        ListView listView = (ListView) v.findViewById(android.R.id.list);
        listView.setAdapter((ListAdapter) adapter);

        // Add item to adapter


        for(int l=0; l<=8; l++){
            InstallationBorrower newUser = new InstallationBorrower("Neha");
            adapter.add(newUser);
        }



        return v;

    }
}