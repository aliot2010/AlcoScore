package com.example.aliot10.alcoscore;

/**
 * Created by aliot on 28.10.2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AlcoholDatabaseHelper extends  SQLiteOpenHelper{
    private static final  String DB_NAME = "DRINK";
    private static final int DB_VERSION = 1;
    public AlcoholDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE DRINK (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NAME TEXT, " +
                "VOLUME TEXT, " +
                "VOLUME_OF_ALCOHOL INTEGER, " +
                "IMAGE_RESOURCE_ID INTEGER, " +
                "FAVORITE INTEGER);");
        doScript(sqLiteDatabase);
    }

    private void doScript(SQLiteDatabase db) {
        insertDrink(db, "Пиво светлое", 568, 5, R.drawable.alc1, 0);
        insertDrink(db, "Пиво темное", 568 , 5, R.drawable.alc1, 0);
        insertDrink(db, "Пиво темное", 300 , 5, R.drawable.alc11, 0);
        insertDrink(db, "Пиво светлое", 300, 5, R.drawable.alc5, 0);
    }

    public static void insertDrink(SQLiteDatabase db, String name,
                                    int volume, int volumeOfAlcohol,
                                    int imageResourse, int favorite){
        ContentValues values = new ContentValues();
        values.put("NAME", name);
        values.put("VOLUME", volume);
        values.put("VOLUME_OF_ALCOHOL", volumeOfAlcohol);
        values.put("IMAGE_RESOURCE_ID", imageResourse);
        values.put("FAVORITE", favorite);
        db.insert("DRINK", null, values);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public static void onItemSetFlag(SQLiteDatabase db,int id, int flag) {
         ContentValues cv = new ContentValues();
        cv.put("FAVORITE", flag);
        int i = db.update("DRINK", cv, "_id=?", new String[]{Integer.toString((id))});
    }
}
