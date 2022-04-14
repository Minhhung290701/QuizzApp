package java.com.minhhung.quizz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainApp extends AppCompatActivity {

    private String selectedTopicName ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_main);

        final LinearLayout animal = findViewById(R.id.animalLayout);
        final LinearLayout math = findViewById(R.id.mathLayout);
        final LinearLayout music = findViewById(R.id.musicLayout);
        final LinearLayout game = findViewById(R.id.GameLayout);

        final Button startbtn = findViewById(R.id.startQuizBtn);

        animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopicName = "animal";
                animal.setBackgroundResource(R.drawable.round_back_white_stroke10);

                math.setBackgroundResource(R.drawable.round_back_white10);
                music.setBackgroundResource(R.drawable.round_back_white10);
                game.setBackgroundResource(R.drawable.round_back_white10);
            }
        });

        math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopicName = "math";
                math.setBackgroundResource(R.drawable.round_back_white_stroke10);
                animal.setBackgroundResource(R.drawable.round_back_white10);
                music.setBackgroundResource(R.drawable.round_back_white10);
                game.setBackgroundResource(R.drawable.round_back_white10);

            }
        });

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTopicName = "music";
                music.setBackgroundResource(R.drawable.round_back_white_stroke10);
                animal.setBackgroundResource(R.drawable.round_back_white10);
                math.setBackgroundResource(R.drawable.round_back_white10);
                game.setBackgroundResource(R.drawable.round_back_white10);
            }
        });

        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTopicName = "game";
                game.setBackgroundResource(R.drawable.round_back_white_stroke10);
                animal.setBackgroundResource(R.drawable.round_back_white10);
                math.setBackgroundResource(R.drawable.round_back_white10);
                music.setBackgroundResource(R.drawable.round_back_white10);
            }
        });

        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedTopicName.isEmpty()){
                    Toast.makeText(MainApp.this,"Please choose the topic",Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(MainApp.this,QuizActivity.class);
                    intent.putExtra("selectedTopic",selectedTopicName);
                    startActivity(intent);
                }
            }
        });

    }
}