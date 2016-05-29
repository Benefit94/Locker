package webbrowser.example.com.bachelorloker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import webbrowser.example.com.bachelorloker.db.DBHelper;

/**
 * Created by 85064_000 on 28.05.2016.
 */
public class Unsecur {

    public static void unhideFroDB(String fileLocation, String fileName,byte[] byteMas){
        File fileNam = new File(fileLocation, fileName);
        try {
            fileNam.createNewFile();
            if(fileNam.exists())
            {
                OutputStream fo = new FileOutputStream(fileNam);
                fo.write(byteMas);
                fo.close();
                DBHelper.deleteByName(fileName);
                DBHelper.deleteMetByName(fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void unhideDot(String fileName, String path,String normName){
        File f=new File(path + "/" + fileName);
        f.renameTo(new File(path, normName));
        DBHelper.deleteByName(fileName);
        DBHelper.deleteMetByName(fileName);
    }

    private static byte[] decryptAES(byte[] raw, byte[] encrypted) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }


}
