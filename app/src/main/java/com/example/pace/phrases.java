package com.example.pace;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class phrases extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mCompletionListener =  mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Where are you going?", "minto wuksus", R.raw.phrase_my_name_is));
        words.add(new Word("Whats is your name?", "tinne oyaase", R.raw.phrase_how_are_you_feeling));
        words.add(new Word("My name is...", "oyaaset....", R.raw.phrase_im_feeling_good));
        words.add(new Word("How arr you feeling?", "michaksas?", R.raw.phrase_are_you_coming));
        words.add(new Word("I'm feeling good", "kuchi achit", R.raw.phrase_yes_im_coming));
        words.add(new Word("Are you coming?", "eanas'aaa?", R.raw.phrase_lets_go));
        words.add(new Word("Let's go", "has' aanam", R.raw.phrase_im_coming));
        words.add(new Word("Lets", "anni'nem", R.raw.phrase_come_here));

        WordAdapter adapter =
                new WordAdapter(this, words, R.color.category_phrases);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);

                releaseMediaPlayer();

                mMediaPlayer = MediaPlayer.create(phrases.this, word.getAudioResourceId());
                mMediaPlayer.start();

                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
    }
    private void releaseMediaPlayer(){

        if(mMediaPlayer != null){
            mMediaPlayer.release();
        }
        mMediaPlayer = null;
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}