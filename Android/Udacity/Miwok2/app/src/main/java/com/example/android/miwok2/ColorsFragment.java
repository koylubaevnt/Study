package com.example.android.miwok2;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {

    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    public ColorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        final ArrayList<Word> colors = new ArrayList<>();
        colors.add(new Word("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        colors.add(new Word("green","chokokki", R.drawable.color_green, R.raw.color_green));
        colors.add(new Word("brown","ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        colors.add(new Word("gray","ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        colors.add(new Word("black","kululli", R.drawable.color_black, R.raw.color_black));
        colors.add(new Word("white","kelelli", R.drawable.color_white, R.raw.color_white));
        colors.add(new Word("dusty yellow","ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        colors.add(new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        WordAdapter adapter = new WordAdapter(getActivity(), colors, R.color.category_colors);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                if(colors.get(position).hasSound()) {
                    int status = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                    if(status == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                        mMediaPlayer = MediaPlayer.create(getActivity(), colors.get(position).getSoundResourceId());
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

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        return rootView;
    }

    @Override
    public void onStop() {
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
