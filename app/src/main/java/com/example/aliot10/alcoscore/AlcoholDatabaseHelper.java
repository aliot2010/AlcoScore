package com.example.aliot10.alcoscore;

/**
 * Created by aliot on 28.10.2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AlcoholDatabaseHelper extends  SQLiteOpenHelper{
    private static final  String DB_NAME = "alcohols";
    private static final int DB_VERSION = 1;
    public AlcoholDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE DRINK (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NAME TEXT, " +
                "VOLUME INTEGER, " +
                "VOLUME_OF_ALCOHOL INTEGER, " +
                "IMAGE_RESOURCE_ID INTEGER);");
        doScript(sqLiteDatabase);
    }

    private void doScript(SQLiteDatabase db) {
        db.insert()//TODO
    }

    private static void insertDrink(SQLiteDatabase db, String name,
                                    int volume, int volumeOfAlcohol,
                                    int imageResourse){
        ContentValues values = new ContentValues();
        values.put("NAME", name);
        values.put("VOLUME", volume);
        values.put("VOLUME_OF_ALCOHOL", volumeOfAlcohol);
        values.put("IMAGE_RESOURCE_ID", imageResourse);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
