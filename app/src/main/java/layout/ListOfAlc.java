package layout;


import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;

import com.example.aliot10.alcoscore.AlcoholDatabaseHelper;
import com.example.aliot10.alcoscore.R;
import com.example.aliot10.alcoscore.adapters.AlcCursorAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListOfAlc extends Fragment {
    ListView listOfAlc;

    AlcoholDatabaseHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;
    CursorAdapter cursorAdapter;
    public ListOfAlc() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_of_alc, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        createDB();
        listOfAlc =(ListView) getActivity().findViewById(R.id.list_of_alc);

        //ArrayAdapter adapter = new ArrayAdapter(getActivity().getBaseContext(),android.R.layout.simple_list_item_1, list);
        cursorAdapter = new AlcCursorAdapter(getActivity().getBaseContext(),
                R.layout.item,
                cursor, new String[]{"NAME", "VOLUME", "VOLUME_OF_ALCOHOL", "IMAGE_RESOURCE_ID", "FAVORITE"},
                new int[]{0 , 0 , 0, 0 ,0});


        listOfAlc.setAdapter(cursorAdapter);

    }

    private void createDB() {
        dbHelper = new AlcoholDatabaseHelper(getActivity().getBaseContext());
        db = dbHelper.getReadableDatabase();
        cursor = db.query("DRINK",
                new String[]{"_id", "NAME", "VOLUME",  "VOLUME_OF_ALCOHOL", "IMAGE_RESOURCE_ID", "FAVORITE"},
                null, null, null, null, null);

    }



}
