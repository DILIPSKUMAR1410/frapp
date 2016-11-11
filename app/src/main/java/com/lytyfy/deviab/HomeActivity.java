package com.lytyfy.deviab;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class HomeActivity extends AppCompatActivity {

    private Drawer result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Onboarding");
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Installation");

        // Handle Toolbar
        result = new DrawerBuilder()
                .withActivity(this)
                .withSavedInstance(savedInstanceState)
                .withDisplayBelowStatusBar(false)
                .withTranslucentStatusBar(false)
                .withDrawerLayout(R.layout.material_drawer_fits_not)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Installation"),
                        new PrimaryDrawerItem().withName("Repayment"),
                        new PrimaryDrawerItem().withName("Logout")
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (position == 2) {
                            Context context = getApplicationContext();
                            AppPrefs appPrefs = new AppPrefs(context);
                            appPrefs.clearToken();

                            Intent logoutIntent = new Intent(HomeActivity.this, MainActivity.class);
                            HomeActivity.this.startActivity(logoutIntent);
                            return false;
                        }

                        selectFrag(position);


                        return false;
                    }
                }).build();


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    public void selectFrag(int position) {
        Fragment fr = null;

        switch (position) {
            case 0:
                fr = new InstallationFragment();
                break;
            case 1:
                fr = new RepaymentFragment();
                break;

            default:
                break;
        }

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentPlace, fr);
        fragmentTransaction.commit();

    }


}

