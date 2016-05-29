package webbrowser.example.com.bachelorloker;

/**
 * Created by 85064_000 on 29.05.2016.
 */
public class EncriptionModel {
    public String fileName;
    public byte[] key;
    public byte[] byteMas;

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String pass;

    public byte[] getKey() {
        return key;
    }

    public void setKey(byte[] key) {
        this.key = key;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getByteMas() {
        return byteMas;
    }

    public void setByteMas(byte[] byteMas) {
        this.byteMas = byteMas;
    }
}
