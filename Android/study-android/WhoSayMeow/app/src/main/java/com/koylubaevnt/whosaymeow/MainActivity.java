package com.koylubaevnt.whosaymeow;

import android.annotation.TargetApi;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private SoundPool mSoundPool;
    private AssetManager mAssetManager;
    private int mCatSound, mChickenSound, mCowSound, mDogSound, mDuckSound, mSheepSound;
    private int mStreamID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            createOldSoundPool();
        } else {
            createNewSoundPool();
        }
        mAssetManager = getAssets();

        mCatSound = loadSound("cat.ogg");
        mChickenSound = loadSound("chicken.ogg");
        mCowSound = loadSound("cow.ogg");
        mDogSound = loadSound("dog.ogg");
        mDuckSound = loadSound("duck.ogg");
        mSheepSound = loadSound("sheep.ogg");

        ImageButton cowImageButton = (ImageButton) findViewById(R.id.imageButtonCow);
        ImageButton chickenImageButton = (ImageButton) findViewById(R.id.imageButtonChicken);
        ImageButton catImageButton = (ImageButton) findViewById(R.id.imageButtonCat);
        ImageButton duckImageButton = (ImageButton) findViewById(R.id.imageButtonDuck);
        ImageButton dogImageButton = (ImageButton) findViewById(R.id.imageButtonDog);
        ImageButton sheepImageButton = (ImageButton) findViewById(R.id.imageButtonSheep);

        //cowImageButton.setOnClickListener(onClickListener);
        chickenImageButton.setOnClickListener(onClickListener);
        catImageButton.setOnClickListener(onClickListener);
        duckImageButton.setOnClickListener(onClickListener);
        dogImageButton.setOnClickListener(onClickListener);
        sheepImageButton.setOnClickListener(onClickListener);

        cowImageButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int eventAction = motionEvent.getAction();
                if(eventAction == MotionEvent.ACTION_UP) {
                    if(mStreamID > 0) {
                        mSoundPool.stop(mStreamID);
                    }
                }
                if(eventAction == MotionEvent.ACTION_DOWN) {
                    mStreamID = playSound(mCowSound);
                }
                if(eventAction == MotionEvent.ACTION_CANCEL) {
                    mSoundPool.stop(mStreamID);
                }
                return true;
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void createNewSoundPool() {
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        mSoundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();
    }

    @SuppressWarnings("deprecation")
    private void createOldSoundPool() {
        mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
    }

    private int loadSound(String fileName) {
        AssetFileDescriptor afd;
        try {
            afd = mAssetManager.openFd(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Не могу загрузить файл " + fileName, Toast.LENGTH_LONG).show();
            return -1;
        }
        return  mSoundPool.load(afd, 1);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imageButtonCow:
                    playSound(mCowSound);
                    break;
                case R.id.imageButtonChicken:
                    playSound(mChickenSound);
                    break;
                case R.id.imageButtonCat:
                    playSound(mCatSound);
                    break;
                case R.id.imageButtonDuck:
                    playSound(mDuckSound);
                    break;
                case R.id.imageButtonSheep:
                    playSound(mSheepSound);
                    break;
                case R.id.imageButtonDog:
                    playSound(mDogSound);
                    break;
            }
        }
    };

    private int playSound(int sound) {
        if(sound > 0) {
            mStreamID = mSoundPool.play(sound, 1, 1, 1, 0, 1);
        }
        return mStreamID;
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            createOldSoundPool();
        } else {
            createNewSoundPool();
        }
        mAssetManager = getAssets();

        mCatSound = loadSound("cat.ogg");
        mChickenSound = loadSound("chicken.ogg");
        mCowSound = loadSound("cow.ogg");
        mDogSound = loadSound("dog.ogg");
        mDuckSound = loadSound("duck.ogg");
        mSheepSound = loadSound("sheep.ogg");
    }

    @Override
    protected void onPause() {
        super.onPause();

        mSoundPool.release();
        mSoundPool = null;
    }
}
