package sg.edu.np.mad.week2pracmadnathan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    ArrayList<User> UserList20 = new ArrayList<>();
    private String TAG = "List Activity";
    DbHandler dataManager = new DbHandler(this,null, null,2);
    static ArrayList<User> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

/*        for(int i = 0; i < 20; i++){
            long val1 = (long) Math.floor(Math.random()*(9_000_000_000L)+1_000_000_000L);
            String Name = "Name" + val1;
            long val2 = (long) Math.floor(Math.random()*(9_000_000_000L)+1_000_000_000L);
            String Description = "Description " + val2;
            Boolean followed = getRandomBoolean();
            long id = i;
            UserList20.add(new User(Name,Description,id,followed));
        }*/
        userList = dataManager.getUsers();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        myAdaptor mAdapter = new myAdaptor(userList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

/*        ConstraintLayout mylayout = findViewById(R.id.Layout1);
        mylayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view1) {
                int itemPosition = recyclerView.getChildLayoutPosition(view1);
                User user2 = UserList20.get(itemPosition);
                String UserName = user2.getName();
                alert2(UserName);
                Toast.makeText(ListActivity.this, "Works", Toast.LENGTH_SHORT).show();
           }
        });
        ImageView myimage = findViewById(R.id.imageView4);
        myimage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int itemPosition = recyclerView.getChildLayoutPosition(view);
                User user2 = UserList20.get(itemPosition);
                String UserName = user2.getName();
                String UserName = "Name";
                alert2(UserName);
            }
        });

        ImageView myImage = findViewById(R.id.imageView4);
        myImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert();
            }
        });*/
    }
    private String randomINT(){
        Random ran = new Random();
        //String value = String.valueOf(ran.nextInt(999999999));
        long val = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        String value = String.valueOf(val);
        Log.v(TAG, "Random interger: " + value);
        Intent myIntent =  new Intent(ListActivity.this, MainActivity.class);
        myIntent.putExtra("MyRandomInt", value);
        startActivity(myIntent);
        return value;
    }
    private void alert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Profile");
        builder.setMessage("MADness");
        builder.setCancelable(false);
        builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {randomINT();}
        });
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {finish(); }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    private void alert2(String u){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Profile");
        builder.setMessage(u);
        builder.setCancelable(false);
        builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {randomINT();}
        });
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {finish(); }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    public boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }
}