package java.com.minhhung.quizz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener{

    //Khai báo các biến được sử dụng.
    private EditText editextFullName , editTextAge , editTextEmail, editTextPassword ;
    private ProgressBar progressBar ;
    private TextView   registerUser ;
    private ImageView banner;

    private FirebaseAuth mAuth ;

    //Method conCreate gọi ra khi Activity được chạy
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user); //Hiển thị bố cục trong activity_register_user.xml lên màn hình.

        mAuth = FirebaseAuth.getInstance(); //Khai báo mAuth là một thể hiện của lớp xác thực firebase

        registerUser = (Button) findViewById(R.id.registerUser); //Gán item có id là registerUser cho redisterUser.
        registerUser.setOnClickListener(this);  //Tạo sự kiện onclick cho redisterUser

        //Gán các item theo id vào các biến tương ứng.
        editextFullName = (EditText) findViewById(R.id.FullName);
        editTextAge = (EditText) findViewById(R.id.Age);
        editTextEmail =(EditText) findViewById(R.id.Email);
        editTextPassword =(EditText) findViewById(R.id.Password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        banner = findViewById(R.id.logo);
    }

    //Viết lại phương thức onClick
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //Nếu trường hợp id là logo thì quay về màn hình đăng nhập.
            case R.id.logo:
                startActivity(new Intent(this,MainActivity.class));
                break;
            //Trwuongf hợp id là registerUser thì chạy hàm registerUser() và quy về màn hình đăng nhập.
            case R.id.registerUser:
                registerUser();
                startActivity(new Intent(this,MainActivity.class));
                break;
        }

    }

    //Hàm registerUser()
    private void registerUser() {
        //Khai báo các biến sử dụng getText().toString().trim() để chuẩn hóa email, password,fullname, age
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String fullName = editextFullName.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();

        //Nếu fullName rỗng thì trả về thông báo Full name is required
        if(fullName.isEmpty()){
            editextFullName.setError("Full name is required");
            editextFullName.requestFocus();
            return;
        }
        //Nếu age rỗng thì trả về thông báo Age is required
        if(age.isEmpty()){
            editTextAge.setError("Age is required");
            editTextAge.requestFocus();
            return;
        }
        //Nếu email rỗng thì trả về thông báo email is required
        if(email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }
        //Nếu email không đúng định dạng thì trả về thông báo Email is duplicated
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Email is duplicated!");
            editTextEmail.requestFocus();
            return;
        }

        //Nếu password rỗng thì trả về thông báo password is required
        if(password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }
        //Nếu password có độ dài nhỏ hơn 6 thì trả về thông báo Min password length is 6 characters
        if(password.length() < 6){
            editTextPassword.setError("Min password length is 6 characters ");
            editTextPassword.requestFocus();
            return;
        }
        //Hiển thị progressBar trong thời gian mAuth thực hiện xác thực đăng ký.
        progressBar.setVisibility(View.VISIBLE);
        //Xác thực đăng kí trong firebase
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //Nếu task thành công thì một user mới được thêm vào, nếu không thì các thông báo hiện ra tương ứng.
                          if(task.isSuccessful()){
                              User user = new User(fullName,age,email);
                              FirebaseDatabase.getInstance().getReference("Users")
                                      .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                      .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                  @Override
                                  public void onComplete(@NonNull Task<Void> task) {
                                      if(task.isSuccessful()){
                                          Toast.makeText(RegisterUser.this , "User has been registred successfully !! ",Toast.LENGTH_LONG).show();
                                          progressBar.setVisibility(View.GONE);
                                      }else {
                                          Toast.makeText(RegisterUser.this , "Fail to register !! try again ",Toast.LENGTH_LONG).show();
                                          progressBar.setVisibility(View.GONE);
                                      }
                                  }
                              });

                          }else {
                              Toast.makeText(RegisterUser.this , "Fail to register",Toast.LENGTH_LONG).show();
                              progressBar.setVisibility(View.GONE);
                          }
                    }
                });
    }
}