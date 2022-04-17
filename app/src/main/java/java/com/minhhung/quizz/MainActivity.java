package java.com.minhhung.quizz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Khai báo các biến được sử dụng
    private TextView register;
    private FirebaseAuth mAuth;

    private EditText editTextEmail , editTextPassword ;
    private Button signIn;
    private ProgressBar progressBar;


    //Method conCreate gọi ra khi Activity được chạy
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Hiển thị bố cục trong activity_main.xml lên màn hình.
        register = (TextView) findViewById(R.id.register); //Gán item có id là register cho register
        register.setOnClickListener(this); //Khai báo sự kiện onClick khi click vào register

        signIn = (Button) findViewById(R.id.logIn); //Gán item có id là logIn và signIn
        signIn.setOnClickListener(this); ////Khai báo sự kiện onClick khi click vào signIn

        //Gán item có id là email vào editTextEmail và tạo sự kiện onclick.
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextEmail.setOnClickListener(this);

        //Gán item có id là email vào editTextPassword và tạo sự kiện onclick.
        editTextPassword =(EditText) findViewById(R.id.password);
        editTextPassword.setOnClickListener(this);

        //Gán item có id là progressBar vào progressBar
        progressBar =(ProgressBar) findViewById(R.id.progressBar);
        //Gọi một thể hiện của lớp xác thực Firebase.
        mAuth = FirebaseAuth.getInstance();
    }

    //Viết lại phương thức onClick sử dụng switch cho các trường hợp là register, logIn và các trường hợp còn lại.
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                startActivity(new Intent(this,RegisterUser.class));
                break;
            case R.id.logIn:
                userLogin();
                break;
        }
    }

    //Hàm userLogin() gọi khi click vào button signIn
    private void userLogin() {
        //Khai báo string email và password lấy trong 2 editText
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        //Nếu email trống thì trả về thông báo là Email is required
        if(email.isEmpty()){
            editTextEmail.setError("Email is required ");
            editTextEmail.requestFocus();
            return;
        }

        //Xác thực dạng email nếu không đúng dạng email thì trả về thông báo "Invalid email"
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Invalid email");
            editTextEmail.requestFocus();
            return;
        }
        //Nếu password trống thì trả về thông báo "Password is required "
        if(password.isEmpty()){
            editTextPassword.setError("Password is required ");
            editTextPassword.requestFocus();
            return;
        }
        //Nếu password có độ dài nhỏ hơn 6 thì trả về thông báo "Min password length is 6 char "
        if(password.length() < 6 ){
            editTextPassword.setError("Min password length is 6 char ");
            editTextPassword.requestFocus();
            return;
        }

        //Hiện progressBar trong quá trình xác thực firebase
        progressBar.setVisibility(View.VISIBLE);

        //Xác thực firebase sử dụng email và password
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                }else{
                    Toast.makeText(MainActivity.this , "Failed to login please try again !! ", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }
}