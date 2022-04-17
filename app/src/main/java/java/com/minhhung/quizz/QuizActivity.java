package java.com.minhhung.quizz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class QuizActivity extends AppCompatActivity {

    //Khai báo các biến
    private TextView questions;
    private TextView question;


    private AppCompatButton option1 , option2 , option3 , option4 , nextBtn;

    private Timer quizTimer;

    private int totalTimeInMins = 1;
    private int seconds = 0 ;

    private  List<QuestionsList> questionsLists ;

    private int currentQuestionPosition = 0;
    private  String selectedOptionByUser = "";

    //Method conCreate gọi ra khi Activity được chạy
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz); //Hiển thị bố cục trong activity_main.xml lên màn hình.

        //Khai báo các biến và gán các item bằng id vào các biến
        final ImageView backBtn = findViewById(R.id.backBtn);
        final TextView timer = findViewById(R.id.timer);
        final TextView selectedTopicName = findViewById(R.id.topicName);


        questions = findViewById(R.id.questions);
        question = findViewById(R.id.question);

        option1= findViewById(R.id.option1);
        option2= findViewById(R.id.option2);
        option3= findViewById(R.id.option3);
        option4= findViewById(R.id.option4);

        nextBtn = findViewById(R.id.nextBtn);
        final String getSelectedTopicName = getIntent().getStringExtra("selectedTopic");


        selectedTopicName.setText(getSelectedTopicName);
        questionsLists = QuestionBank.getQuestions(getSelectedTopicName);
        startTimer(timer);

        questions.setText((currentQuestionPosition+1)+"/"+questionsLists.size());
        question.setText(questionsLists.get(0).getQuestion());
        option1.setText(questionsLists.get(0).getOption1());
        option2.setText(questionsLists.get(0).getOption2());
        option3.setText(questionsLists.get(0).getOption3());
        option4.setText(questionsLists.get(0).getOption4());

        //Tạo sự kiện onclick cho option1
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = option1.getText().toString();
                    option1.setBackgroundResource(R.drawable.round_back_reda10);
                    option1.setTextColor(Color.WHITE);
                    revealAnswer();
                    questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }

            }
        });

        //Tạo sự kiện onclick cho option2
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = option2.getText().toString();
                    option2.setBackgroundResource(R.drawable.round_back_reda10);
                    option2.setTextColor(Color.WHITE);
                    revealAnswer();
                    questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }

            }
        });

        //Tạo sự kiện onclick cho option3
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = option3.getText().toString();
                    option3.setBackgroundResource(R.drawable.round_back_reda10);
                    option3.setTextColor(Color.WHITE);
                    revealAnswer();
                    questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        //Tạo sự kiện onclick cho option4
        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = option4.getText().toString();
                    option4.setBackgroundResource(R.drawable.round_back_reda10);
                    option4.setTextColor(Color.WHITE);
                    revealAnswer();
                    questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        //Tạo sự kiện onclick cho nextBtn
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(selectedOptionByUser.isEmpty()){
                        Toast.makeText(QuizActivity.this,"Enter ",Toast.LENGTH_LONG).show();
                    }
                    else {
                        changeNextquestion();
                    }
            }
        });

        //Tạo sự kiện onclick cho backBtn
        backBtn.setOnClickListener(new View.OnClickListener() {
            //
            @Override
            public void onClick(View view) {
                //Tắt và reset bộ hẹn giờ
                quizTimer.purge();
                quizTimer.cancel();
                //Quay về màn hình MainApp.
                startActivity(new Intent(QuizActivity.this,MainApp.class));
                finish();
            }
        });
    }

    //Hàm changeNextQuestion
    private void changeNextquestion(){
        currentQuestionPosition++;
        if(currentQuestionPosition+1==questionsLists.size()){
            nextBtn.setText("Enter");
        }
        if(currentQuestionPosition <  questionsLists.size()){
            selectedOptionByUser ="";
            option1.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option1.setTextColor(Color.parseColor("#1F6B88"));

            option2.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option2.setTextColor(Color.parseColor("#1F6B88"));

            option3.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option3.setTextColor(Color.parseColor("#1F6B88"));

            option4.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option4.setTextColor(Color.parseColor("#1F6B88"));

            questions.setText((currentQuestionPosition+1)+"/"+questionsLists.size());
            question.setText(questionsLists.get(currentQuestionPosition).getQuestion());
            option1.setText(questionsLists.get(currentQuestionPosition).getOption1());
            option2.setText(questionsLists.get(currentQuestionPosition).getOption2());
            option3.setText(questionsLists.get(currentQuestionPosition).getOption3());
            option4.setText(questionsLists.get(currentQuestionPosition).getOption4());
        }
        else {
            Intent intent = new Intent(QuizActivity.this,QuizResults.class);
            intent.putExtra("correct",getCorrectAnswer());
            intent.putExtra("incorrect",getInCorrectAnswer());
            startActivity(intent);
            finish();
        }
    }

    //Đặt bộ thời gian với hàm startTimer
    private  void startTimer(TextView timertextView){

        //Khởi tạo Timer() quizTime
        quizTimer= new Timer();

        //Đặt lịch 60s
        quizTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //Đặt giờ 60s
                if(seconds == 0){
                    totalTimeInMins--;
                    seconds = 60;
                }
                //Nếu hết giờ thì hiện ra hàm hình "Time out" và sử dụng Intent để chuyển sang màn hình QuiizResults và gửi thông tin bằng putExtra của Intent
                else if(seconds == 0 && totalTimeInMins == 0)
                {
                    quizTimer.purge();
                    quizTimer.cancel();
                    Toast.makeText(QuizActivity.this,"Time out",Toast.LENGTH_LONG).show();
                    Intent intent  = new Intent(QuizActivity.this,QuizResults.class);
                    intent.putExtra("correct",getCorrectAnswer());
                    intent.putExtra("incorrect",getInCorrectAnswer());
                    startActivity(intent);
                    finish();
                }else //Thời gian trừ 1
                {
                    seconds--;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                         String  finalMinutes = String.valueOf(totalTimeInMins);
                         String finalSeconds = String.valueOf(seconds);

                        if(finalMinutes.length()==1){
                            finalMinutes = "0"+finalMinutes;
                        }
                        if(finalSeconds.length()==1){
                            finalSeconds ="0"+finalSeconds;
                        }
                        timertextView.setText(finalMinutes+":"+finalSeconds);
                    }
                });
            }
        },1000,1000); // delay 1s

    }

    //Trả về số câu trả lời đúng.
    private int getCorrectAnswer(){
        int  correctAnswer = 0 ;

        for(int i =0;i<questionsLists.size();i++){
            final String getuserSelectedAnswer = questionsLists.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsLists.get(i).getAnswer();

            if(getuserSelectedAnswer.equals(getAnswer)){
                correctAnswer ++ ;
            }
        }
        return correctAnswer ;

    }

    //Trả về số câu trả lười sai.
    private int getInCorrectAnswer(){
        int  correctAnswer = 0 ;

        for(int i =0;i<questionsLists.size();i++){
            final String getuserSelectedAnswer = questionsLists.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsLists.get(i).getAnswer();

            if(!getuserSelectedAnswer.equals(getAnswer)){
                correctAnswer ++ ;
            }
        }
        return correctAnswer ;

    }

    //HÀm onBackPressed trả về màn MainApp và cài lại bộ đếm thời gian.
    @Override
    public void onBackPressed() {
        quizTimer.purge();
        quizTimer.cancel();
        startActivity(new Intent(QuizActivity.this,MainApp.class));
        finish();
    }

    //Hiển thị câu trả lời đúng. Nếu đúng thì background hiển thị màu xanh.
    private void revealAnswer(){
        final String getAnswer = questionsLists.get(currentQuestionPosition).getAnswer();
        if(option1.getText().toString().equals(getAnswer)){
            option1.setBackgroundResource(R.drawable.round_back_green10);
            option1.setTextColor(Color.WHITE);
        }
        else if(option2.getText().toString().equals(getAnswer)){
            option2.setBackgroundResource(R.drawable.round_back_green10);
            option2.setTextColor(Color.WHITE);
        }
        else if(option3.getText().toString().equals(getAnswer)){
            option3.setBackgroundResource(R.drawable.round_back_green10);
            option3.setTextColor(Color.WHITE);
        }
        else if(option4.getText().toString().equals(getAnswer)){
            option4.setBackgroundResource(R.drawable.round_back_green10);
            option4.setTextColor(Color.WHITE);
        }
    }
}