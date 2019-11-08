package cn.edu.xjtlu.mygame;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OpenHelper extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 1;
    private final static String DATABASE_NAME = "data.db";
    public static final String CREATE_USER = "create table User ("
            + "id integer primary key autoincrement,"
            +"username text,"
            +"userpwd text)";

    public OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldversion,int newversion) {

    }
}
