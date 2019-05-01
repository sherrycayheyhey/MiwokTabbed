package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create a list of words
        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("one", "lutti", R.drawable.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four));
        words.add(new Word("five", "massokka",R.drawable.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight));
        words.add(new Word("nine", "wo’e", R.drawable.number_nine));
        words.add(new Word("ten", "na’aacha", R.drawable.number_ten));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this, words);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);

//        // log message to check that the array worked like I wanted it to
//        for(int i = 0; i < words.size(); i++) {
//            Log.i("NumbersActivity", "Word at index " + i + ": " + words.get(i));
//        }

//        //find the LinearLayout view and store it in a variable
//        LinearLayout rootView = (LinearLayout)findViewById(R.id.rootView);
//
//        //create and add TextViews to this LinearLayout
//        for(int i = 0; i < words.size(); i++) {
//            TextView wordView = new TextView(this);
//            wordView.setText(words.get(i));
//            rootView.addView(wordView);
//        }
        //uses an Android build layout (simple_list_item_1)
        //ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, words);
        //uses a custom layout I made instead (list_item)


    }
}
