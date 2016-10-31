package com.example.aliot10.alcoscore;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.example.aliot10.alcoscore.adapters.MyGridAdapter;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddAlcohole extends Fragment {
    private TextView valTextView, procentTextView;
    DiscreteSeekBar valSeekBar, procentSeek;
    int volume = 50,  procent = 2;
    private GridView gridOfImages;
    Integer[] list = {R.drawable.alc1, R.drawable.alc2, R.drawable.alc3, R.drawable.alc4, R.drawable.alc5,
            R.drawable.alc6, R.drawable.alc7, R.drawable.alc8, R.drawable.alc9, R.drawable.alc10,
            R.drawable.alc11, R.drawable.alc12, R.drawable.alc13, R.drawable.alc14, R.drawable.alc15};
    MyGridAdapter adapter;
    public AddAlcohole() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //setSeekBarListeners();
        return inflater.inflate(R.layout.fragment_add_alcohole, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        valTextView = (TextView) getActivity().findViewById(R.id.valTextView);
        procentTextView = (TextView) getActivity().findViewById(R.id.procentTextView2);

        valSeekBar = (DiscreteSeekBar) getActivity().findViewById(R.id.valSeek);
        procentSeek = (DiscreteSeekBar) getActivity().findViewById(R.id.procentSeek);

        gridOfImages = (GridView) getActivity().findViewById(R.id.gridOfImages);

        createDridView();
        setSeekBarListeners();


    }

    private void createDridView() {
        adapter = new MyGridAdapter(getActivity().getBaseContext(),
                R.layout.griditem, list);
        gridOfImages.setAdapter(adapter);
    }

    private void setSeekBarListeners(){
        valSeekBar.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                volume = seekBar.getProgress()*50;
                valTextView.setText((String.valueOf(volume) + " мл"));
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });

        procentSeek.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                procent = procentSeek.getProgress()*2;
                procentTextView.setText(String.valueOf(procent)+" %");
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });
    }


}
