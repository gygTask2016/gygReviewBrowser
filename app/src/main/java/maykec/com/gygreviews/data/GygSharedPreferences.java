package maykec.com.gygreviews.data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by marko on 5/15/2016.
 */
public class GygSharedPreferences {
    private static final String PREFS_NAME = "GygPreferences";
    private static final String PREF_USER_TOKEN = "GygPreferences:PREF_USER_TOKEN";

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mPreferencesEditor;

    public GygSharedPreferences(Context context) {
        mSharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        mPreferencesEditor = mSharedPreferences.edit();
    }

    public String getUserToken(){
        return mSharedPreferences.getString(PREF_USER_TOKEN, "");
    }

    public boolean storeUserToken(String token){
        return mPreferencesEditor.putString(PREF_USER_TOKEN, token).commit();
    }

}
