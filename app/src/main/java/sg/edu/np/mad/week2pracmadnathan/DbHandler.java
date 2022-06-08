package sg.edu.np.mad.week2pracmadnathan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class DbHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserDB.db";
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_ID = "int";
    public static final String COLUMN_FOLLOWED = "followed";

    public DbHandler(Context context, String name,
                     SQLiteDatabase.CursorFactory factory,
                     int version)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createCommand = "CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, name STRING, description STRING, followed BOOLEAN)";
        sqLiteDatabase.execSQL(createCommand);

        String addData = "INSERT INTO users (id, name, description, followed) VALUES ";

        for (int i = 1; i <= 20; i++) {
            int id = (int) Math.round(Math.random() * 1000000);
            String randomNameSuffix = String.valueOf((int) Math.round(Math.random() * 1000000));
            String randomDescriptionSuffix = String.valueOf((int) Math.round(Math.random() * 100000000));

            String name = "User " + randomNameSuffix;
            String description = "This is a description. Here's a random number! " + randomDescriptionSuffix;
            Boolean followed = Math.random() > 0.5;

            addData += "(" + String.valueOf(id) + ", \"" + name + "\", \"" + description + "\", " + followed + ")," ;
        }

        sqLiteDatabase.execSQL(addData.substring(0, addData.length() - 1));
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");
        onCreate(sqLiteDatabase);
    }

    public ArrayList<User> getUsers() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ArrayList<User> userList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM users", null);

        while (cursor.moveToNext()){
            String name = cursor.getString((int)cursor.getColumnIndex("name"));
            String description = cursor.getString((int)cursor.getColumnIndex("description"));
            Integer id = cursor.getInt((int)cursor.getColumnIndex("id"));
            Boolean follow = Boolean.parseBoolean(cursor.getString((int)cursor.getColumnIndex("followed")));

            User user = new User(name, description, id, follow) ;
            userList.add(user);
        }

        return userList;
    }

    public void updateUser(User user){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        Integer refId = user.getId();
//        Integer follow;
//        if(user.getFollowed() == Boolean.TRUE){
//            follow = 1;
//        }
//        else {
//            follow = 0;
//        }
        // Update followed in DB
        String UPDATE = "UPDATE users SET followed = \"" + user.getFollowed() + "\" WHERE id is \"" + refId + "\"";

        sqLiteDatabase.execSQL(UPDATE);
        sqLiteDatabase.close();
    }
/*
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserDB.db";
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_ID = "int";
    public static final String COLUMN_FOLLOWED = "followed";

    public DbHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory,
                       int version)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " +
                TABLE_USERS +
                "(" + COLUMN_ID + " INTERGER,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_FOLLOWED + " BOOLEAN" +  ")";
        db.execSQL(CREATE_USERS_TABLE);

        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            boolean bool = random.nextBoolean();

            User m = new User();
            m.setName("Name" + new Random());;
            m.setDescription("Description " + new Random());
            m.setFollowed(bool);
            m.setId(i);

            ContentValues values = new ContentValues();
            values.put("Id", m.getId());
            values.put("Name", m.getName());
            values.put("Description", m.getDescription());
            values.put("Followed", m.getFollowed());
            //SQLiteDatabase database = this.getWritableDatabase();

            db.insert("Users", null, values);
            //db.close();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);

    }

    public ArrayList<User> getUsers(){
        ArrayList<User> uList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()){
            User user = new User();
            user.setName(cursor.getString(0));
            user.setDescription(cursor.getString(1));
            user.setId(cursor.getInt(2));
            user.setFollowed(Boolean.parseBoolean(cursor.getString((int)cursor.getColumnIndex("followed"))));
            cursor.moveToNext();
        }
        cursor.close();
        return uList;

    }

    public void addUsers(User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        values.put(COLUMN_FOLLOWED, user.getFollowed());
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public void updateUser(User user){
        String query = "SELECT * FROM " + TABLE_USERS
                + " WHERE " + COLUMN_ID + " = \"" + user.getId()
                + "\"";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        values.put(COLUMN_FOLLOWED,user.getFollowed());
        values.put(COLUMN_ID,user.getId());
        values.put(COLUMN_NAME,user.getName());

        db.update(TABLE_USERS, values,"id = ?", new String[] {String.valueOf(user.getId())});
        Log.v("Update","hii");
    }

*/
}
