package com.example.aliot10.alcoscore;


import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class MainActivity extends AppCompatActivity {
    private String[] titles;
    private ListView titleList;
    ActionBar actionBar;
    private android.app.Fragment mainFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titles = getResources().getStringArray(R.array.titles);
        //mainFragment =  getFragmentManager().findFragmentById(R.id.main_fragment);
        createFragmentField(new mainFragment());

        titleList = (ListView) findViewById(R.id.drawer);
        titleList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, titles));
        createListeners();



    }

    private void createFragmentField(Fragment fragment) {

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();

    }

    private void createListeners() {
        titleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                selectItem(position);
                setActionBarTitle(position);
            }

            private void selectItem(int position) {//TODO
                Fragment fragment = null;
                switch (position){
                    case 0:
                        fragment = new mainFragment();
                        break;
                    case 1:
                        fragment = new ListOfAlcohol();
                        break;
                    case 2:
                        fragment = new AddAlcohole();

                }
                DrawerLayout drawerLayout =(DrawerLayout) findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(titleList);
                createFragmentField(fragment);
                
            }
            
        });
    }

    private void setActionBarTitle(int position) {
        String title;
        if (position == 0){
            title = getResources().getString(R.string.app_name);
        } else {
            title = titles[position];
        }

        getSupportActionBar().setTitle(title);


    }


}
