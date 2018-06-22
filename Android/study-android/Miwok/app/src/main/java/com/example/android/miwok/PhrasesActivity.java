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

public class PhrasesActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> phrases = new ArrayList<>();
        phrases.add(new Word("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
        phrases.add(new Word("What is your name?","tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        phrases.add(new Word("My name is...","oyaaset...", R.raw.phrase_my_name_is));
        phrases.add(new Word("How are you feeling?","michәksәs?", R.raw.phrase_how_are_you_feeling));
        phrases.add(new Word("I’m feeling good.","kuchi achit", R.raw.phrase_im_feeling_good));
        phrases.add(new Word("Are you coming?","әәnәm", R.raw.phrase_are_you_coming));
        phrases.add(new Word("Yes, I’m coming.","әәnәs'aa?", R.raw.phrase_yes_im_coming));
        phrases.add(new Word("I’m coming.", "hәә’ әәnәm", R.raw.phrase_im_coming));
        phrases.add(new Word("Let’s go.", "yoowutis", R.raw.phrase_lets_go));
        phrases.add(new Word("Come here.", "әnni'nem", R.raw.phrase_come_here));

        WordAdapter adapter = new WordAdapter(getApplicationContext(), phrases, R.color.category_phrases);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                if(phrases.get(position).hasSound()) {
                    int status = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                    if(status == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                        mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, phrases.get(position).getSoundResourceId());
                        mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
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
