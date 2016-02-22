package com.guigeek.devilopers.dd5charactersheet;

import android.app.Application;
import android.content.Context;

/**
 * Created by ggallani on 19/02/2016.
 */
public class App extends Application {
    private static Context _context;

    @Override
    public void onCreate() {
        super.onCreate();
        _context = this;
    }

    public static Context getContext(){
        return _context;
    }

    public static String getResString(int id) {
        return getContext().getResources().getString(id);
    }
}
