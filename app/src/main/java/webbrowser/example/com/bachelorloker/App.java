package webbrowser.example.com.bachelorloker;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by 85064_000 on 15.05.2016.
 */
public class App extends Application {

        @Override
        public void onCreate() {
            super.onCreate();
            ActiveAndroid.initialize(this);
        }
}
