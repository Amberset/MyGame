package cn.edu.xjtlu.mygame;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SqliteDB {
    public static final String DB_NAME = "data.db";
    public static final int VERSION = 1;
    private static SqliteDB sqliteDB;
    private SQLiteDatabase db;

    private SqliteDB(Context context) {
        OpenHelper dbHelper = new OpenHelper(context,DB_NAME,null,VERSION);
        db = dbHelper.getWritableDatabase();
    }

    public synchronized static SqliteDB getInstance(Context context) {
        if (sqliteDB == null) {
            sqliteDB = new SqliteDB(context);
        }
        return sqliteDB;
    }

    public int saveUser(User user) {
        if (user != null) {
            Cursor cursor = db.rawQuery("select * from User where username=?",new String[]{user.getUsername().toString(),user.getUserpwd().toString()});
            if (cursor.getCount()>0) {
                return -1;
            }else {
                try {
                    db.execSQL("insert into User(username,userpwd) values(?,?)",new String[]{user.getUsername().toString(),user.getUserpwd().toString()});
                }catch (Exception e) {
                    Log.d("Wrong",e.getMessage().toString());
                }
                return 1;
            }
        }
        else {
            return 0;
        }
    }

    public List<User> loadUser() {
        List<User> list = new ArrayList<User>();
        Cursor cursor = db.query("User",null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do{
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex("id")));
                user.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                user.setUserpwd(cursor.getString(cursor.getColumnIndex("userpwd")));
                list.add(user);
            }while(cursor.moveToNext());
        }
        return list;
    }

    public int Quer(String pwd,String name) {
        //HashMap<String,String> hashmap = new HashMap<String, String>();
        //hashmap.put("name",db.rawQuery("select * from User where name=?",new String[]{name}).toString());
        Cursor cursor = db.rawQuery("select * from User where username=?",new String[]{name});
        if (cursor.getCount()>0) {
            Cursor pwdcursor = db.rawQuery("select * from User where userpwd=? and username=?",new String[]{pwd,name});
            if (pwdcursor.getCount()>0) {
                return 1;
            }
            else {
                return -1;
            }
        }
        else {
            return 0;
        }
    }
}
