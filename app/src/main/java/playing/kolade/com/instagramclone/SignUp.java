package playing.kolade.com.instagramclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity {

  private  EditText edtName, edtSpeed, edtPower;

   private Button btnSave, getAllData;

    private TextView txtGetData;

    private String allKickBoxers;

    private Button btnTransition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtName = findViewById(R.id.edtName);
        edtSpeed = findViewById(R.id.edtSpeed);
        edtPower = findViewById(R.id.edtPower);
        txtGetData = findViewById(R.id.txtGetData);
        getAllData = findViewById(R.id.getAllData);
        btnTransition = findViewById(R.id.btnNextActivity);

        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                ParseQuery<ParseObject>parseQuery = ParseQuery.getQuery("KickBoxer");
//                parseQuery.getInBackground("HoT6xxMJpl", new GetCallback<ParseObject>() {
//                    @Override
//                    public void done(ParseObject object, ParseException e) {
//
//                        if (object!=null && e == null){
//
//                            txtGetData.setText(object.get("name")+" - " + "Kick Power = " + object.get("kickPower"));
//                        }
//                    }
//                });



                // To extract a particular data cell from the parse object

                ParseQuery<ParseObject>parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("oSVXgZVYgm", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (object!=null && e == null){

                            txtGetData.setText(object.get("name") + " has Kick Speed of " + object.get("kickSpeed"));
                        }
                    }
                });

            }
        });


        //To extract all the data of the parse object

        getAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                allKickBoxers = "";

                ParseQuery<ParseObject>queryAll = ParseQuery.getQuery("KickBoxer");

                queryAll.whereGreaterThan("kickPower", 100); //This is to show the objects with kickPoser greater than 100
                queryAll.setLimit(1); //This sets the limit of the number of displayed results to 1

                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null) {
                            if (objects.size()>0){

                                for (ParseObject kickBoxer:objects){

                                    allKickBoxers = allKickBoxers + kickBoxer.get("name") + "\n";

                                }

                                FancyToast.makeText(SignUp.this, allKickBoxers , Toast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            }
                        else {
                                FancyToast.makeText(SignUp.this,"Error", Toast.LENGTH_LONG, FancyToast.ERROR, true).show();

                            }
                        }
                    }
                });
            }
        });

        //The next lines are to transition to another activity (another page)

        btnTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignUp.this, SignupLoginActivity.class);  //To transition to another activity

            startActivity(intent);

            // Note that we need to go into Manifest.xml to complete process

            }
        });

    }





    public void btnSave(View v) {

        try {           // This is to for error handling ...try and catch combination

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
