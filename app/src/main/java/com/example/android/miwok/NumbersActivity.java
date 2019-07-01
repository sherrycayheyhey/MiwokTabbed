//NumbersActivity has been updated to use the NumbersFragment
//this sets the activity_category XML layout resource as the content view
//next a new NumbersFragment is created and inserted it into the container view using a FragmentTransaction
//the container has “match_parent” for width and height so NumbersFragment will take up the whole
//width and height of the screen

package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new NumbersFragment())
                .commit();
    }
}
