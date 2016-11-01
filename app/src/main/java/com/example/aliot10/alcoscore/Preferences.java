package com.example.aliot10.alcoscore;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by aliot on 01.11.2016.
 */

public class Preferences {
    private static final String APP_REFERENCES = "MySetting";
    public static final String APP_REFERENCES_Effect_OF_ALCOHOL = "EffectOfAlcohol";
    public static final String APP_REFERENCES_WEIGHT = "Weight";
    private static final String APP_HAS_VISITED = "visited";
    public static final int WEIGHT_FLAG = 0;
    public static final int EFFECT_OF_ALCOHOL_FLAG = 1;
    Context context;
    private SharedPreferences preferences;

    Preferences(Context context) {
        this.context = context;
    }

    public void setPreferences(int weightPref, int effectOfAlcPref) {
        this.preferences = context.
                getSharedPreferences(APP_REFERENCES, Context.MODE_PRIVATE);
//        this.weight = context.
//                getSharedPreferences(APP_REFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.putInt(APP_REFERENCES_WEIGHT, weightPref);


       // SharedPreferences.Editor editorEffectOfAlc = this.effectOfAlc.edit();
        editor.putInt(APP_REFERENCES_Effect_OF_ALCOHOL, effectOfAlcPref);
        editor.apply();
    }

    public int getPreferences(String appReferenceFlag) {
        SharedPreferences preferences =
                context.getSharedPreferences(APP_REFERENCES, Context.MODE_PRIVATE);
        return  preferences.getInt(appReferenceFlag, -1);//

    }
    public boolean hasVisited(){
        SharedPreferences sp =
                context.getSharedPreferences(APP_REFERENCES, Context.MODE_PRIVATE);
        return sp.getBoolean(APP_HAS_VISITED, false);
    }

    public  void setVisited(boolean visited){
        SharedPreferences sp =
                context.getSharedPreferences(APP_REFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(APP_HAS_VISITED, visited);
        editor.commit();
    }




}
