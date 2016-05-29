package webbrowser.example.com.bachelorloker;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 85064_000 on 28.05.2016.
 */
public class SettingsManager {

    public static void saveHide(Context context, String hideM) {
        SharedPreferences sPrefToken = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPrefToken.edit();
        ed.putString("hideM", hideM);
        ed.commit();
    }

    public static String getHide(Context context) {
        SharedPreferences sPrefToken = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        return sPrefToken.getString("hideM", "DB");
    }

    public static void saveEnc(Context context, String Enc) {
        SharedPreferences sPrefToken = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPrefToken.edit();
        ed.putString("Enc", Enc);
        ed.commit();
    }

    public static String getEnc(Context context) {
        SharedPreferences sPrefToken = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        return sPrefToken.getString("Enc", "");
    }

    public static void saveHash(Context context, String Hash) {
        SharedPreferences sPrefToken = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPrefToken.edit();
        ed.putString("Hash", Hash);
        ed.commit();
    }

    public static String getHash(Context context) {
        SharedPreferences sPrefToken = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        return sPrefToken.getString("Hash", "");
    }
}
