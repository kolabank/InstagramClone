package playing.kolade.com.instagramclone;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignupLoginActivity extends AppCompatActivity {
    private EditText edtUserNameSignUp, edtPasswordSignUp, edtUserNameLogin, edtPasswordLogin;
    private Button btnSignUp, btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.signup_login_activity);

        edtUserNameSignUp = findViewById(R.id.edtUserNameSignUp);
        edtPasswordSignUp = findViewById(R.id.edtPasswordSignUp);
        edtUserNameLogin = findViewById(R.id.edtUserNameLogin);
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final ParseUser appUser = new ParseUser();  //creates a new ParseUser object and assigns it to the variable appUser

                appUser.setUsername(edtUserNameSignUp.getText().toString()); //assigns username entered to Parse
                appUser.setPassword(edtPasswordSignUp.getText().toString()); //assigns password entered to Parse

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {

                            FancyToast.makeText(SignupLoginActivity.this, appUser.get("username") + "is signed up successfully"
                                    , Toast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                        } else {
                            FancyToast.makeText(SignupLoginActivity.this, e.getMessage(), Toast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });

            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser.logInInBackground(edtUserNameLogin.getText().toString(), edtPasswordLogin.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if (user != null&& e == null) {

                            FancyToast.makeText(SignupLoginActivity.this, user.get("username") + "is logged in successfully"
                                    , Toast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                        }
                    else {
                            FancyToast.makeText(SignupLoginActivity.this, e.getMessage(), Toast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }

                    }
                });


            }
        });

    }

}