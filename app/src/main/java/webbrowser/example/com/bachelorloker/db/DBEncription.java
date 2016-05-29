package webbrowser.example.com.bachelorloker.db;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by 85064_000 on 29.05.2016.
 */
@Table(name = "EncriptedFile")
public class DBEncription  extends Model implements Parcelable{

    @Column(name = "fileName")
    public String fileName;
    @Column(name = "key")
    public byte[] key;
    @Column(name = "byteMas")
    public byte[] byteMas;
    @Column(name = "pass")
    public String pass;

    public DBEncription() {
        super();
    }

    public DBEncription(String fileName, byte[] key, byte[] byteMas,String pass) {
        super();
        this.fileName = fileName;
        this.key = key;
        this.byteMas = byteMas;
        this.pass = pass;
    }


    protected DBEncription(Parcel in) {
        fileName = in.readString();
        key = in.createByteArray();
        byteMas = in.createByteArray();
        pass = in.readString();
    }

    public static final Creator<DBEncription> CREATOR = new Creator<DBEncription>() {
        @Override
        public DBEncription createFromParcel(Parcel in) {
            return new DBEncription(in);
        }

        @Override
        public DBEncription[] newArray(int size) {
            return new DBEncription[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fileName);
        dest.writeByteArray(key);
        dest.writeByteArray(byteMas);
        dest.writeString(pass);
    }
}
