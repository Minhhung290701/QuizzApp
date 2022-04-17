package java.com.minhhung.quizz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//Màn hình thông tin người dùng
public class ProfileActivity extends AppCompatActivity  {

    //Khai báo các biến sẽ sử dụng.
    private FirebaseUser user ;
    private DatabaseReference reference;

    private String userId;

    private Button logout,start ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile); //Hiển thị bố cục trong activity_profile.xml lên màn hình.

        logout = (Button) findViewById(R.id.signOut); //Gán button có id signOut cho logout
        start = (Button) findViewById(R.id.startQuizz); //Gán button có id startQuizz cho start

        //Set sự kiện conclick cho logout
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Xác thực firebase signOut
                FirebaseAuth.getInstance().signOut();
                //Sử dugnj intent quay trở lại màn MainActivity
                startActivity(new Intent(ProfileActivity.this , MainActivity.class));
            }
        });

        //Khai báo user là một thể hiện hiện tại của người dùng.
        user = FirebaseAuth.getInstance().getCurrentUser();
        //Tạo tham chiếu reference của thể hiện
        reference = FirebaseDatabase.getInstance().getReference("Users");
        //Trả vể userId
        userId = user.getUid();

        //Khai báo và gán dữ liệu vào các TextView fullNameTextView, emailTextView, ageTextView
        TextView fullNameTextView = (TextView) findViewById(R.id.fullName);
        TextView emailTextView = (TextView) findViewById(R.id.emailAdress);
        TextView ageTextView = (TextView) findViewById(R.id.age);

        emailTextView.setText(user.getEmail());

        //Gán dữ liệu vào các TextView từ dữ liệu trong database thông qua reference.
        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if(userProfile != null){
                    String fullName = userProfile.fullName;
                    String email = userProfile.email;
                    String age = userProfile.age;

                    fullNameTextView.setText(fullName);
                    emailTextView.setText(email);
                    ageTextView.setText(age);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this,"Somthing wrong happen",Toast.LENGTH_LONG).show();
            }
        });


        //Sự kiện onclick của start
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Khởi chạy sang màn hình MainApp sử dụng Intent
                startActivity(new Intent(ProfileActivity.this, MainApp.class));
            }
        });

    }
}