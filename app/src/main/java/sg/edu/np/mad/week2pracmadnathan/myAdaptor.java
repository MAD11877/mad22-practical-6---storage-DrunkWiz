package sg.edu.np.mad.week2pracmadnathan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class myAdaptor extends RecyclerView.Adapter<myViewHolder>{
    ArrayList<User> UserList;
    private String TAG = "My Adaptor";

    public myAdaptor(ArrayList<User> userList) {
        UserList = userList;
    }

    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View item = LayoutInflater.f  rom(parent.getContext()).inflate(R.layout.layout, parent, false);
        View item;
        if(viewType == 1){
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout,parent,false);
        }
        else {
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview2,parent,false);
        }
        return new myViewHolder(item);
    }

    public void onBindViewHolder(myViewHolder holder, int position){
        User s = UserList.get(position);
        String n = s.getName();
        String d = s.getDescription();
        holder.txt.setText(n);
        holder.stxt.setText(d);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.image.getContext());
                builder.setMessage(s.getName());
                builder.setTitle("Profile");
                builder.setCancelable(true);
                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
/*                        Bundle bundle = new Bundle();
                        bundle.putInt("Position",position);
                        Intent intent = new Intent(holder.image.getContext(),MainActivity.class);
                        intent.putExtras(bundle);
                        holder.image.getContext().startActivity(intent);*/
                        Random ran = new Random();
                        //String value = String.valueOf(ran.nextInt(999999999));
//                        long val = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
//                        String value = String.valueOf(val);
//                        Log.v(TAG, "Random interger: " + value);
                        Intent myIntent =  new Intent(holder.image.getContext(), MainActivity.class);
                        myIntent.putExtra("name", s.getName());
                        myIntent.putExtra("description", s.getDescription());
                        myIntent.putExtra("id", s.getId());
                        myIntent.putExtra("followed", s.getFollowed());
                        holder.image.getContext().startActivity(myIntent);
                    }
                });
                builder.setNegativeButton("close",null);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    public int getItemCount(){
        return UserList.size();
    }

    public boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    public int getItemViewType(int position){
        User userobj = UserList.get(position);
        if(userobj.getName().charAt(userobj.getName().length() - 1)=='7'){
            return  0;
        }
        else {
            return 1;
        }
    }
}
