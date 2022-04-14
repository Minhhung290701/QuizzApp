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
        setContentView(R.layout.activity_quiz_results);

        final AppCompatButton startNewBtn = findViewById(R.id.startNewQuizz);
        final TextView correctAnswer = findViewById(R.id.correctAnswers);
        final TextView incorrectAnswers = findViewById(R.id.incorrectAnswers);

        final int getCorrctAnswers = getIntent().getIntExtra("correct",0);
        final int getIncorrectAnswers = getIntent().getIntExtra("incorrect",0);

        correctAnswer.setText("Correct "+" : "+String.valueOf(getCorrctAnswers));
        incorrectAnswers.setText("Incorrect"+" : "+String.valueOf(getIncorrectAnswers));

        startNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuizResults.this,MainApp.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(QuizResults.this,MainApp.class));
        finish();
    }
}