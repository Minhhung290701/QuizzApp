package java.com.minhhung.quizz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class QuizResults extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results); //Hiển thị bố cục trong activity_quiz_results.xml lên màn hình.

        //Khai báo các biến và gán các item vào theo id.
        final AppCompatButton startNewBtn = findViewById(R.id.startNewQuizz); //Dùng để bắt đầu quizz khác.
        final TextView correctAnswer = findViewById(R.id.correctAnswers); //Hiển thị số câu trả lời đúng.
        final TextView incorrectAnswers = findViewById(R.id.incorrectAnswers); //Hiển thị số câu trả lời sai.

        final int getCorrctAnswers = getIntent().getIntExtra("correct",0); //Số câu trả lời đúng sử dụng getIntExtra của Intent.
        final int getIncorrectAnswers = getIntent().getIntExtra("incorrect",0); // Số câu trả lời sai sử dụng getIntExtra của Intent.

        correctAnswer.setText("Correct "+" : "+String.valueOf(getCorrctAnswers)); //SetText cho TextView correctAnswer
        incorrectAnswers.setText("Incorrect"+" : "+String.valueOf(getIncorrectAnswers)); // SetText cho TextView  incorrectAnswers

        //Sự kiện onclick của startNewBtn trả về màn hình MainApp để chọn topic cho quizz
        startNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuizResults.this,MainApp.class));
                finish();
            }
        });
    }

    //Quay lại màn hình MainApp để chọn topic.
    @Override
    public void onBackPressed() {
        startActivity(new Intent(QuizResults.this,MainApp.class));
        finish();
    }
}