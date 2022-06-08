package sg.edu.np.mad.week2pracmadnathan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.security.cert.TrustAnchor;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private String TAG = "Main Activity";
    User myUser;
    DbHandler dbHandler = new DbHandler(this,null, null,2);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*        Intent receivingEnd = getIntent();
        String random = receivingEnd.getStringExtra("MyRandomInt");
        //Using getIntExtra returns default value and not the actual value attached to key
        TextView myTextView = findViewById(R.id.textView);
        String printable = String.format("MAD %s",random);
        myTextView.setText(printable);*/
        Intent intents = getIntent();

        String name = intents.getStringExtra("name");
        String description = intents.getStringExtra("description");
        Integer id = intents.getIntExtra("id", 0);
        Boolean followed = intents.getBooleanExtra("followed", false);

        myUser = new User(name, description, id, followed);

        TextView usernameTextView = (TextView)findViewById(R.id.textView);
        usernameTextView.setText(myUser.getName());

        TextView descriptionTextView = (TextView)findViewById(R.id.textView3);
        descriptionTextView.setText(myUser.getDescription());

        Button myfollowbutton = (Button) findViewById(R.id.FollowButton);
        if(myUser.getFollowed() == Boolean.FALSE){
            myfollowbutton.setText("Follow");
        }
        else { myfollowbutton.setText("Unfollow"); }

/*        myUser.setFollowed(false);
        Integer newLong = Integer.parseInt(random);
        myUser.setId(newLong);*/

        myfollowbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean bool = myUser.getFollowed();
                bool = !bool;
                myUser.setFollowed(bool);

                Log.v(TAG, "on Click....");
                if (myUser.getFollowed() == Boolean.FALSE) {
                    myfollowbutton.setText("Follow");
                    dbHandler.updateUser(myUser);
                } else {
                    myfollowbutton.setText("Unfollow");
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT).show();
                    dbHandler.updateUser(myUser);
                }
            }
        });

        Button myMessageButton  = findViewById(R.id.MessageButton);
        myMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent =  new Intent(MainActivity.this, MessageGroup.class);
                //myIntent.putExtra("MyRandomInt", value);
                startActivity(myIntent);
            }
        });

    }

}