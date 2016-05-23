package webbrowser.example.com.bachelorloker.db;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.List;

import webbrowser.example.com.bachelorloker.HideFiles;

/**
 * Created by 85064_000 on 14.05.2016.
 */
public class DBHelper {

    public static void saveAmount(HideFiles taxiLists) {
        ActiveAndroid.beginTransaction();
        try {
            new Delete().from(DBLockedFile.class).execute();
            DBLockedFile dbAmount = new DBLockedFile(taxiLists.location, taxiLists.fileType, taxiLists.byteMas);
                dbAmount.save();
            ActiveAndroid.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ActiveAndroid.endTransaction();
        }
    }

    public static List<DBLockedFile> getFile() {
        return new Select().from(DBLockedFile.class).execute();
    }

    public static void saveLeaguesItem(HideFiles taxiLists){
        DBLockedFile dbLockedFile = new DBLockedFile(taxiLists.location, taxiLists.fileType, taxiLists.byteMas);
        dbLockedFile.save();
    }

    public static void deleteByName(String name){
            new Delete().from(DBLockedFile.class)
                    .where("fileType = ?",name)
                    .execute();
    }

    public static void clearData(){
        new Delete().from(DBLockedFile.class).execute();
    }
}
