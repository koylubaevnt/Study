package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

////        String[] words = new String[10];
////        words[0] = "one";
////        words[1] = "two";
////        words[2] = "three";
////        words[3] = "four";
////        words[4] = "five";
////        words[5] = "six";
////        words[6] = "seven";
////        words[7] = "eight";
////        words[8] = "nine";
////        words[9] = "ten";
////
////        Log.v("NumbersActivity", "Word at index 0: " + words[0]);
////        Log.v("NumbersActivity", "Word at index 1: " + words[1]);
//        ArrayList<String> words = new ArrayList<>(10);
//        words.add("one");
//        words.add("two");
//        words.add("three");
//        words.add("four");
//        words.add("five");
//        words.add("six");
//        words.add("seven");
//        words.add("eight");
//        words.add("nine");
//        words.add("ten");
//////        Log.v("NumbersActivity", "Word at index 0: " + words.get(0));
//////        Log.v("NumbersActivity", "Word at index 1: " + words.get(1));
////        LinearLayout layout = (LinearLayout) findViewById(R.id.rootView);
////        for(int i = 0; i < words.size(); i++) {
////            TextView wordView = new TextView(getApplicationContext());
////            wordView.setText(words.get(i));
////            layout.addView(wordView);
////        }
//       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, words);
        final ArrayList<Word> words = new ArrayList<Word>(10);
        words.add(new Word("one","lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two","ottiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three","tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four","oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five","massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six","temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven","kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight","kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine","wo'e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten","na'aacha", R.drawable.number_ten, R.raw.number_ten));

        WordAdapter adapter = new WordAdapter(getApplicationContext(), words, R.color.category_numbers);
        ListView listView = (ListView) findViewById(R.id.list);
        //GridView listView = (GridView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                if(words.get(position).hasSound()) {
                    int status = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                    if(status == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                        mMediaPlayer = MediaPlayer.create(NumbersActivity.this, words.get(position).getSoundResourceId());
                        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                releaseMediaPlayer();
                            }
                        });
                        mMediaPlayer.start();
                    }
                }
            }
        });

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if(mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                    break;
                case AudioManager.AUDIOFOCUS_GAIN:
                    mMediaPlayer.start();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    releaseMediaPlayer();
                    break;
            }
        }
    };
}
