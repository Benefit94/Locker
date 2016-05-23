package webbrowser.example.com.bachelorloker.db;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by 85064_000 on 14.05.2016.
 */
@Table(name = "LockedFile")
public class DBLockedFile extends Model implements Parcelable {

    @Column(name = "location")
    public String location;
    @Column(name = "fileType")
    public String fileType;
    @Column(name = "byteMas")
    public byte[] byteMas;

    public DBLockedFile() {
        super();
    }

    public DBLockedFile(String location, String fileType, byte[] byteMas) {
        super();
        this.location = location;
        this.fileType = fileType;
        this.byteMas = byteMas;
    }

    protected DBLockedFile(Parcel in) {
        this.location = in.readString();
        this.fileType = in.readString();
        this.byteMas = new byte[in.readInt()];
        in.readByteArray(this.byteMas);
    }

    public static final Creator<DBLockedFile> CREATOR = new Creator<DBLockedFile>() {
        @Override
        public DBLockedFile createFromParcel(Parcel in) {
            return new DBLockedFile(in);
        }

        @Override
        public DBLockedFile[] newArray(int size) {
            return new DBLockedFile[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.location);
        dest.writeString(this.fileType);
        dest.writeByteArray(this.byteMas);
    }
}
