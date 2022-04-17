package java.com.minhhung.quizz;

//Tạo class User để lưu vào db
public class User {
    public String fullName , age ,email ;
    public User(){
    }
    public User(String fullName, String age, String email){
        this.fullName = fullName;
        this.age = age;
        this.email = email;
    }
}
