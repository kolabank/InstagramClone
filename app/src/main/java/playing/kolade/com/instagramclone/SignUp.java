package playing.kolade.com.instagramclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity {

    EditText edtName, edtSpeed, edtPower;

    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtName = findViewById(R.id.edtName);
        edtSpeed = findViewById(R.id.edtSpeed);
        edtPower = findViewById(R.id.edtPower);

    }


//    public void helloWorldTap(View view){
//        ParseObject boxer = new ParseObject("Boxer");
//        boxer.put("punch_speed", 200);
//        boxer.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//
//                if (e==null){
//                    Toast.makeText(SignUp.this,"boxer object is saved successfully", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//
//
//    }

    public void btnSave(View v) {

        try {

            String name = edtName.getText().toString();
            int speed = Integer.parseInt(edtSpeed.getText().toString());
            int power = Integer.parseInt(edtPower.getText().toString());


            final ParseObject kickBoxer = new ParseObject("KickBoxer");
            kickBoxer.put("kickPower", power);
            kickBoxer.put("kickSpeed", speed);
            kickBoxer.put("name", name);

            kickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {

                        FancyToast.makeText(SignUp.this, kickBoxer.get("name") + " is created ", Toast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                    } else {

                        Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });

        } catch (Exception e){
            FancyToast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_LONG, FancyToast.ERROR, true).show();

        }
    }
}
