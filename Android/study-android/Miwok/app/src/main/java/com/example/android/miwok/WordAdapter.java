package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by koylu on 23.09.2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int color;

    public WordAdapter(@NonNull Context context, ArrayList<Word> words, int color) {
        super(context, 0, words);
        this.color = color;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item, parent, false);
        }

        Word currentWord = getItem(position);

        View textContainer = listItemView.findViewById(R.id.text_container);
        textContainer.setBackgroundResource(color);

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWord.getDefaultTranslation());

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(currentWord.getMiwokTranslation());

        ImageView image = (ImageView) listItemView.findViewById(R.id.image);
        if(currentWord.hasImage()) {
            image.setImageResource(currentWord.getImageResourceId());
            image.setVisibility(View.VISIBLE);
        } else {
            image.setVisibility(View.GONE);
        }

        return listItemView;
    }
}
