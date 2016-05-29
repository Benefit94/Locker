package webbrowser.example.com.bachelorloker.db;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by 85064_000 on 29.05.2016.
 */
@Table(name = "SecurMet")
public class DBSecurMet extends Model implements Parcelable{

    @Column(name = "fileName")
    public String fileName;
    @Column(name = "hideProt")
    public String hideProt;
    @Column(name = "encryptProt")
    public String encryptProt;
    @Column(name = "hashProt")
    public String hashProt;
    @Column(name = "normName")
    public String normName;

    public DBSecurMet() {
        super();
    }

    public DBSecurMet(String fileName, String hideProt, String encryptProt, String hashProt,String normName ) {
        super();
        this.fileName = fileName;
        this.hideProt = hideProt;
        this.encryptProt = encryptProt;
        this.hashProt = hashProt;
        this.normName = normName;
    }

    protected DBSecurMet(Parcel in) {
        fileName = in.readString();
        hideProt = in.readString();
        encryptProt = in.readString();
        hashProt = in.readString();
        normName = in.readString();
    }

    public static final Creator<DBSecurMet> CREATOR = new Creator<DBSecurMet>() {
        @Override
        public DBSecurMet createFromParcel(Parcel in) {
            return new DBSecurMet(in);
        }

        @Override
        public DBSecurMet[] newArray(int size) {
            return new DBSecurMet[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fileName);
        dest.writeString(hideProt);
        dest.writeString(encryptProt);
        dest.writeString(hashProt);
        dest.writeString(normName);
    }
}
