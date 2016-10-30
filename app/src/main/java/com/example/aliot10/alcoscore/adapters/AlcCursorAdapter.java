package com.example.aliot10.alcoscore.adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.aliot10.alcoscore.Alcohol;
import com.example.aliot10.alcoscore.AlcoholDatabaseHelper;
import com.example.aliot10.alcoscore.R;

/**
 * Created by aliot on 30.10.2016.
 */

public class AlcCursorAdapter extends SimpleCursorAdapter {
    private int layout;
    AlcoholDatabaseHelper alcoholDatabaseHelper;
    SQLiteDatabase db;
    Context context;

    public AlcCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to) {
        super(context, layout, c, from, to);
        this.context = context;
        this.layout = layout;
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout, viewGroup, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        int id = Integer.parseInt(cursor.getString(0));
        String name = cursor.getString(1);
        int volume = Integer.parseInt(cursor.getString(2));
        int volumeOfAlc = Integer.parseInt(cursor.getString(3));
        int imagePath = Integer.parseInt(cursor.getString(4));
        int favorite = Integer.parseInt(cursor.getString(5));
        createViews(new Alcohol(name, volume, volumeOfAlc, imagePath, favorite), view, id);

    }
    private void createViews(Alcohol alc, View view, final int id){
        TextView nameTextView = (TextView) view.findViewById(R.id.nameTextView);
        TextView volumeTextView = (TextView) view.findViewById(R.id.volumeTextView);
        ImageView glassImageView = (ImageView) view.findViewById(R.id.glassImageView);
        final ToggleButton likeToggleButton = (ToggleButton) view.findViewById(R.id.likeToggleButton);
        nameTextView.setText(alc.getName());
        volumeTextView.setText((String.valueOf(alc.getVolume()) + " мл"));//danger
        glassImageView.setImageResource(alc.getImagePath());


        if(alc.getFavorite() == 1){
            likeToggleButton.setBackgroundResource(R.drawable.hearts);
        }

        likeToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                createDatabase();
                if (likeToggleButton.isChecked()){
                    likeToggleButton.setBackgroundResource(R.drawable.hearts);
                    insertLikeToggleButtonFlag(id, 1);
                }else{
                    likeToggleButton.setBackgroundResource(R.drawable.favorite);
                    insertLikeToggleButtonFlag(id, 2);
                }
            }
        });
    }

    private void createDatabase() {
        alcoholDatabaseHelper = new AlcoholDatabaseHelper(context);
        db = alcoholDatabaseHelper.getWritableDatabase();

    }
    private void insertLikeToggleButtonFlag(int id, int flag){
        AlcoholDatabaseHelper.onItemSetFlag(db, id, flag);
    }



}
