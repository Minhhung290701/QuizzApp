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

    //Method conCreate gọi ra khi Activity được chạy
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_main);  //Hiển thị bố cục trong activity_main_main.xml lên màn hình.

        //Khai báo các biến final và gán các item cho chúng thông qua id
        final LinearLayout animal = findViewById(R.id.animalLayout);
        final LinearLayout math = findViewById(R.id.mathLayout);
        final LinearLayout music = findViewById(R.id.musicLayout);
        final LinearLayout game = findViewById(R.id.GameLayout);

        final Button startbtn = findViewById(R.id.startQuizBtn);

        //Set sự kiện onclick cho animal, xung quanh có viền máu xanh.
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

        //Set sự kiện onclick cho math, xung quanh có viền máu xanh.
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

        //Set sự kiện onclick cho music, xung quanh có viền máu xanh.
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

        //Set sự kiện onclick cho game, xung quanh có viền máu xanh.
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

        //Set sự kiện onclick cho startbtn
        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Trường hợp chưa chọn Topic thì hiện ra thông báo.
                if(selectedTopicName.isEmpty()){
                    Toast.makeText(MainApp.this,"Please choose the topic",Toast.LENGTH_LONG).show();
                }
                else {
                    //Trong trường hợp chọn topic rồi thì chuyển qua màn hình QuizAcitivity và gửi topic đã chọn thông qua putExtra của Intent
                    Intent intent = new Intent(MainApp.this,QuizActivity.class);
                    intent.putExtra("selectedTopic",selectedTopicName);
                    startActivity(intent);
                }
            }
        });

    }
}