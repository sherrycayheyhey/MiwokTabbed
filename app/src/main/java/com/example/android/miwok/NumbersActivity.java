package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    /** Handles playback of all the sound files */
    private MediaPlayer mMediaPlayer;

    /** Handles audio focus when playing a sound file */
    private AudioManager mAudioManager;

    //deals with the focus change options
    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };

    /**
     * This listener gets triggered when the {@Link MediaPlayer} has completed playing the audio file
     */
    //this is global so that a new objects isn't made and destroyed because that uses up resources
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Create a list of words
        //final so that we can reference the words ArrayList within the onItemClick method
        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("one", "lutti", R.drawable.number_one,
                R.raw.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two,
                R.raw.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three,
                R.raw.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four,
                R.raw.number_four));
        words.add(new Word("five", "massokka",R.drawable.number_five,
                R.raw.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six,
                R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven,
                R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight,
                R.raw.number_eight));
        words.add(new Word("nine", "wo’e", R.drawable.number_nine,
                R.raw.number_nine));
        words.add(new Word("ten", "na’aacha", R.drawable.number_ten,
                R.raw.number_ten));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);

        //OnItemClickListener listens for a list item to be clicked
        //set a click listener to play the audio when the list item is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //release the MediaPlayer resources before the player is initialized to play a different song
                //this is necessary because if a user clicks buttons quickly, before an audio file is
                //done playing, the completion listener wouldn't be triggered because it didn't finish playing
                releaseMediaPlayer();

                //figure out which word was clicked, then store it to a variable
                Word word = words.get(position);
                //get the audio resource id of the word that was clicked on and then pass it to the media player



                // Request audio focus for playback
                //when we request audio focus we need to pass in three inputs: a listener, a music
                //stream type, and how long to request audio for
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT); //because the clips are only 1-2 seconds

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // we have audio focus now

                    //create and setup the {@link MediaPlayer} for the audio resource associated with the current word
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceID());
                    mMediaPlayer.start(); // no need to call prepare(); create() does that for you

                    //setup a listener on the media player, so we can stop and release the media player
                    //once the sound has finished playing
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }

            }
        });


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

    @Override
    protected  void onStop(){
        super.onStop();
        //when the activity is stopped, release the media player resources because no more
        //sound needs to play
        releaseMediaPlayer();
    }

    /**
     * this helper method cleans up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            //abandon audio focus when playback complete
            //it's safe to release the audio focus here because we're no longer playing audio
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
