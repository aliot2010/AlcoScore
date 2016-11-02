package com.example.aliot10.alcoscore;


import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aliot10.alcoscore.adapters.AlcDrinkCursorAdapter;

import me.itangqi.waveloadingview.WaveLoadingView;


/**
 * A simple {@link Fragment} subclass.
 */
public class mainFragment extends Fragment {
    AlcDrinkCursorAdapter adapter;
    ListView listOfAlc;
    WaveLoadingView waveLoadingView;
    TextView adviceText;
    String advice[] = {"Вы трезвы как стеклышко, пора начинать пить!",
            "Вы двигаетесь в правильном направлении", "Сейчас вы способны на все!",
            "Достаточно, больше пить явно не стоит", "Опасно! Срочно перестаньте пить!"};
    private double weightAlcoholIn1KG, promile = 0;

    public mainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adviceText = (TextView) getActivity().findViewById(R.id.textViewAdvice);
        listOfAlc = (ListView) getActivity().findViewById(R.id.list_of_alc);
        waveLoadingView = ((WaveLoadingView) getActivity().findViewById(R.id.waveLoadingView));
        Preferences pref = new Preferences(getActivity());
        promile = pref.getWeightOfAlcoholInBody();
        setWaveWiew();

        new SetCursorAsyncTasc().execute();
        setOnItemClicked();
    }

    private void setWaveWiew() {
        Preferences pref = new Preferences(getActivity());//
        double koef = ((double)
                (pref.getPreferences(Preferences.APP_REFERENCES_Effect_OF_ALCOHOL) * 25 + 50)) / 100;
        waveLoadingView.setProgressValue((int) (koef*promile / 4.0 * 100));
        if (promile < 0.5) {
            adviceText.setText(advice[0]);
            waveLoadingView.setWaveColor(Color.GREEN);
        } else if (promile < 1.5) {
            adviceText.setText(advice[1]);
            waveLoadingView.setWaveColor(Color.argb(255, 149, 223, 0));
        } else if (promile < 2.5) {
            adviceText.setText(advice[2]);
            waveLoadingView.setWaveColor(Color.argb(255, 242, 255, 0));
        } else if (promile < 3) {
            adviceText.setText(advice[3]);
            waveLoadingView.setWaveColor(Color.argb(255, 255, 166, 0));
        } else {
            adviceText.setText(advice[4]);
            waveLoadingView.setWaveColor(Color.argb(255, 255, 0, 0));
        }
    }

    private void setOnItemClicked() {
        listOfAlc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SQLiteDatabase db = AlcoholDatabaseHelper.
                        openReadablDb(getActivity().getBaseContext());
                Alcohol currentAlcohol = AlcoholDatabaseHelper.getAlcohol(db, (int) l);
                db.close();
                Toast toast = Toast.makeText(getActivity().getBaseContext(),
                        currentAlcohol.getName(), Toast.LENGTH_SHORT);
                toast.show();
                setAlcoholInBody(currentAlcohol);
                setWaveWiew();

            }
        });
    }

    private void setAlcoholInBody(Alcohol alcohol) {
        Preferences pref = new Preferences(getActivity());

        double m = (double) pref.getPreferences(Preferences.APP_REFERENCES_WEIGHT) * 0.7;
        double mOfalc = alcohol.getVolume() * (alcohol.getVolumeOfAlc()) / 100.0 * 0.79 * 0.9;
        promile = pref.getWeightOfAlcoholInBody();
        promile += (mOfalc / m);
        pref.setWeightOfAlcoholInBody((float) promile);


        waveLoadingView.setProgressValue((int) (promile / 4.0 * 100));
    }

    class SetCursorAsyncTasc extends AsyncTask<Void, Void, Cursor> {
        SQLiteDatabase db;

        @Override
        protected Cursor doInBackground(Void... voids) {
            return createDB();
        }

        private Cursor createDB() {
            AlcoholDatabaseHelper dbHelper = new AlcoholDatabaseHelper(getActivity().getBaseContext());
            db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query("DRINK",
                    new String[]{"_id", "NAME", "VOLUME", "VOLUME_OF_ALCOHOL", "IMAGE_RESOURCE_ID", "FAVORITE"},
                    null, null, null, null, "FAVORITE DESC");
            return cursor;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            adapter = new AlcDrinkCursorAdapter(getActivity().getBaseContext(),
                    R.layout.item2,
                    cursor, new String[]{"NAME", "VOLUME", "VOLUME_OF_ALCOHOL", "IMAGE_RESOURCE_ID", "FAVORITE"},
                    new int[]{0, 0, 0, 0, 0});
            listOfAlc.setAdapter(adapter);
            db.close();

        }
    }

}
