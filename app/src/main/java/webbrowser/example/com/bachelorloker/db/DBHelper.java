package webbrowser.example.com.bachelorloker.db;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.List;

import webbrowser.example.com.bachelorloker.EncriptionModel;
import webbrowser.example.com.bachelorloker.HideFiles;
import webbrowser.example.com.bachelorloker.HideMets;

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

    public static void saveHideMet(HideMets hideMets){
        DBSecurMet dbSecurMet = new DBSecurMet(hideMets.fileName,hideMets.hideProt,
                hideMets.encryptProt,hideMets.hashProt,hideMets.normName);
        dbSecurMet.save();
    }

    public static void saveLeaguesItem(HideFiles taxiLists){
        DBLockedFile dbLockedFile = new DBLockedFile(taxiLists.location, taxiLists.fileType, taxiLists.byteMas);
        dbLockedFile.save();
    }

    public static void saveEncription(EncriptionModel encriptionModel){
        DBEncription dbEncription = new DBEncription(encriptionModel.fileName,encriptionModel.key
                ,encriptionModel.byteMas, encriptionModel.pass);
        dbEncription.save();
    }

    public static void deleteMetByName(String name){
        new Delete().from(DBSecurMet.class)
                .where("fileName = ?",name)
                .execute();
    }

    public static void deleteByName(String name){
            new Delete().from(DBLockedFile.class)
                    .where("fileType = ?",name)
                    .execute();
    }

    public static void deleteEncrByName(String name){
        new Delete().from(DBEncription.class)
                .where("fileName = ?",name)
                .execute();
    }

    public static DBSecurMet getMetsByName(String name){
        return new Select().from(DBSecurMet.class)
                .where("fileName = ?",name)
                .executeSingle();
    }

    public static DBEncription getEncByName(String name){
        return new Select().from(DBEncription.class)
                .where("fileName = ?",name)
                .executeSingle();
    }

    public static void clearData(){
        new Delete().from(DBLockedFile.class).execute();
    }
}
