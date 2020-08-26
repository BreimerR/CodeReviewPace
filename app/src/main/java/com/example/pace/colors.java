package com.example.pace;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

//TODO Have a consistent naming convention
// This will affect your code readability later on
// Pick a style and stick to it
// but Always use Camel-case like AppCompatActivity
// That way you always know that anything with CamelCase is a class identifier.
// Avoid confusing yourself as you code.
// Simple habit but you'd be remembering functionNames you wrote months ago very easily.

public class colors extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mCompletionListener = mCompletionListener = new MediaPlayer.OnCompletionListener() {
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


        //TODO Your on create should be as empty as possible and delegation to functions is preferred.
        updateWords(words);

        WordAdapter adapter =
                new WordAdapter(this, words, R.color.category_colors);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);

                releaseMediaPlayer();

                mMediaPlayer = MediaPlayer.create(colors.this, word.getAudioResourceId());
                mMediaPlayer.start();

                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
    }

    private void updateWords(ArrayList<Word> words) {
        //TODO for test cases and simple use case
        // having data like this is fine.
        // But for production case
        // This will add soo much work you'd feel like you are doing data entry
        // And actually get bored.
        // For demo it's descent. But with RoomDatabase (If you have not reached there no issue perfect code).
        // If you have you'd realize setting up this is much simpler and will carry along on during implementation phase.
        // All this circles can be a single drawable that is copied and colored again.
        words.add(new Word("red", "wetetti", R.drawable.color_red, R.raw.color_red));
        words.add(new Word("mustard yellow", "chiwiio", R.drawable.color_mustard_yellow, R.raw.color_red));
        words.add(new Word("dusty yellow", "topiisa", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Word("green", "chokokki", R.drawable.color_green, R.raw.color_green));
        words.add(new Word("brown", "takaaki", R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("gray", "topoppi", R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word("black", "kululli", R.drawable.color_black, R.raw.color_black));
        words.add(new Word("white", "kelelli", R.drawable.color_white, R.raw.color_white));
    }

    private void releaseMediaPlayer() {

        if (mMediaPlayer != null) {
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


/// TODO ctrl+alt+l will check format your code. be acustomed to dong so to avoid having code where
// it is not visible