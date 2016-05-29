package webbrowser.example.com.bachelorloker;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import webbrowser.example.com.bachelorloker.db.DBHelper;
import webbrowser.example.com.bachelorloker.utils.MessageAlert;

/**
 * Created by 85064_000 on 28.05.2016.
 */
public class Secur {

    public static void hideInDB(String selectedFile, String path,File file,String fileName){
        int size = (int) file.length();
        byte[] bytes = new byte[size];
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
            buf.read(bytes, 0, bytes.length);
            HideFiles hideFiles = new HideFiles();
            hideFiles.setByteMas(bytes);
            hideFiles.setFileType(fileName);
            hideFiles.setLocation(path);
            DBHelper.saveLeaguesItem(hideFiles);
            boolean deleted = file.delete();
            buf.close();
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static void hideDot(String selectedFile, String path,File f,String fileName){
        f.renameTo(new File(path, "." + fileName));
        HideFiles hideFiles = new HideFiles();
        hideFiles.setByteMas(null);
        hideFiles.setFileType("." + fileName);
        hideFiles.setLocation(path);
        DBHelper.saveLeaguesItem(hideFiles);
    }

    public static byte[] encryptAES(byte[] raw, byte[] clear) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(clear);
        return encrypted;
    }

    public static void encryptByteMass(Context context, String pass, String selectedFile){
        /*if(pass.length()>0){
            if(selectedFile.length()>0){
                BackTaskLock btlock=new BackTaskLock();
                btlock.execute(pass,null,null);
            }
            else{
                MessageAlert.showAlert("Please a select a file to lock", context);
            }
        }
        else{
            MessageAlert.showAlert("Please enter password", context);
        }*/
    }
}
