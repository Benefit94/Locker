package webbrowser.example.com.bachelorloker.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import webbrowser.example.com.bachelorloker.Constants;
import webbrowser.example.com.bachelorloker.EncriptionModel;
import webbrowser.example.com.bachelorloker.HideFiles;
import webbrowser.example.com.bachelorloker.HideMets;
import webbrowser.example.com.bachelorloker.Secur;
import webbrowser.example.com.bachelorloker.SettingsManager;
import webbrowser.example.com.bachelorloker.Unsecur;
import webbrowser.example.com.bachelorloker.adapters.ListAdapterModel;
import webbrowser.example.com.bachelorloker.db.DBEncription;
import webbrowser.example.com.bachelorloker.db.DBHelper;
import webbrowser.example.com.bachelorloker.utils.Locker;
import webbrowser.example.com.bachelorloker.R;
import webbrowser.example.com.bachelorloker.utils.MessageAlert;

/**
 * Created by Serhii on 4/3/2016.
 */
public class FragmentFileList extends Fragment{


    private String path="";
    private String selectedFile="";
    private Context context;
    ListView l;
    EditText txtpwd;
    public static final String ARG_PAGE = "ARG_PAGE";

    public static FragmentFileList newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        FragmentFileList fragment = new FragmentFileList();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_file_list, container, false);
        ListView lv=(ListView) view.findViewById(R.id.files_list);
        l=(ListView) view.findViewById(R.id.files_list);
        txtpwd=(EditText)view.findViewById(R.id.txt_input);
        Button bt_lock = (Button) view.findViewById(R.id.bt_lock);
        Button bt_encryption = (Button) view.findViewById(R.id.bt_encryption);
        Button bt_dencryption = (Button) view.findViewById(R.id.bt_dencryption);

        if(lv!=null){
            lv.setOnItemClickListener(new ClickListener());
        }
        path="/mnt";
        listDirContents();


        ////////
        bt_lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HideMets hideMets = new HideMets();
                String hide = SettingsManager.getHide(getActivity());
                switch(hide){
                    case Constants.DB:
                        File file = new File(selectedFile);
                        String filename = file.getPath().substring(path.lastIndexOf("/") + 1);
                        String[] name = filename.split("/");
                        String fileName = name[1];
                        hideMets.setFileName(fileName);
                        hideMets.setHideProt(Constants.DB);
                        Secur.hideInDB(selectedFile,path,file,fileName);
                        break;
                    case Constants.DOT:
                        File f = new File(selectedFile);
                        String filenames = f.getPath().substring(path.lastIndexOf("/") + 1);
                        String[] names = filenames.split("/");
                        String fileNames = names[1];
                        hideMets.setNormName(fileNames);
                        hideMets.setFileName("." + fileNames);
                        hideMets.setHideProt(Constants.DOT);
                        Secur.hideDot(selectedFile,path,f,fileNames);
                        break;
                }
                DBHelper.saveHideMet(hideMets);
            }
        });

        //////
        bt_encryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd=txtpwd.getText().toString();
                try {
                    File file = new File(selectedFile);
                    String filename = file.getPath().substring(path.lastIndexOf("/") + 1);
                    String[] name = filename.split("/");
                    String fileName = name[1];
                    int size = (int) file.length();
                    byte[] bytes = new byte[size];
                    BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
                    buf.read(bytes, 0, bytes.length);
                    byte[] keyStart = pwd.getBytes();
                    KeyGenerator kgen = KeyGenerator.getInstance("AES");
                    SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
                    sr.setSeed(keyStart);
                    kgen.init(128, sr);
                    SecretKey skey = kgen.generateKey();
                    byte[] key = skey.getEncoded();
                    EncriptionModel encriptionModel = new EncriptionModel();
                    encriptionModel.setFileName(fileName);
                    encriptionModel.setPass(pwd);
                    encriptionModel.setKey(key);
                    byte[] encryptedData = Secur.encryptAES(key,bytes);
                    encriptionModel.setByteMas(encryptedData);
                    DBHelper.saveEncription(encriptionModel);
                    boolean deleted = file.delete();
                    File fileNam = new File(path, fileName);
                    fileNam.createNewFile();
                    OutputStream fo = new FileOutputStream(fileNam);
                    fo.write(encryptedData);
                    fo.close();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                /*String pwd=txtpwd.getText().toString();
                Secur.encryptByteMass(getActivity(),pwd,selectedFile);
                if(pwd.length()>0){
                    if(selectedFile.length()>0){
                        BackTaskLock btlock=new BackTaskLock();
                        btlock.execute(pwd,null,null);
                    }
                    else{
                        MessageAlert.showAlert("Please a select a file to lock", getActivity());
                    }
                }
                else{
                    MessageAlert.showAlert("Please enter password",getActivity());
                }*/
            }
        });
        bt_dencryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd=txtpwd.getText().toString();
                try {
                    File file = new File(selectedFile);
                    String filename = file.getPath().substring(path.lastIndexOf("/") + 1);
                    String[] name = filename.split("/");
                    String fileName = name[1];

                    DBEncription enc = DBHelper.getEncByName(fileName);
                    byte[] decryptedData  = Unsecur.decryptAES(enc.key,enc.byteMas);
                    boolean deleted = file.delete();
                    File fileNam = new File(path, fileName);
                    fileNam.createNewFile();
                    OutputStream fo = new FileOutputStream(fileNam);
                    fo.write(decryptedData);
                    fo.close();
                    DBHelper.deleteEncrByName(fileName);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                /*String pwd=txtpwd.getText().toString();
                if(pwd.length()>0){
                    if(selectedFile.length()>0){
                        BackTaskUnlock btunlock=new BackTaskUnlock();
                        btunlock.execute(pwd,null,null);
                    }
                    else{
                        MessageAlert.showAlert("Please select a file to unlock",getActivity());
                    }
                }
                else{
                    MessageAlert.showAlert("Please enter password",getActivity());
                }*/
            }
        });
        return view;
    }

    public static final String md5(final String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    private class ClickListener implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //selected item
            ViewGroup vg=(ViewGroup)view;
            String selectedItem = ((TextView) vg.findViewById(R.id.label)).getText().toString();
            path=path+"/"+selectedItem;
            //et.setText(path);
            listDirContents();
        }
    }

    private void listDirContents(){
        if(path!=null){
            try{
                File f=new File(path);
                if(f!=null){
                    if(f.isDirectory()){
                        String[] contents=f.list();
                        if(contents.length>0){
                            ListAdapterModel lm=new ListAdapterModel(getActivity(),R.layout.listlayout,R.id.label,contents,path);
                            l.setAdapter(lm);
                        }
                        else
                        {
                            path=f.getParent();
                        }
                    }
                    else{
                        selectedFile=path;
                        path=f.getParent();
                    }
                }
            }catch(Exception e){}
        }
    }

    public void startLock(String pwd){
        Locker locker=new Locker(getActivity(),selectedFile,pwd);
        locker.lock();
    }

    public void startUnlock(String pwd){
        Locker locker=new Locker(getActivity(),selectedFile,pwd);
        locker.unlock();
    }

    private class BackTaskUnlock extends AsyncTask<String,Void,Void> {
        ProgressDialog pd;
        protected void onPreExecute(){
            super.onPreExecute();
            pd = new ProgressDialog(getActivity());
            pd.setTitle("UnLocking the file");
            pd.setMessage("Please wait.");
            pd.setCancelable(true);
            pd.setIndeterminate(true);
            pd.show();
        }
        protected Void doInBackground(String...params){
            try{

                startUnlock(params[0]);

            }catch(Exception e){
                pd.dismiss();   //close the dialog if error occurs
            }
            return null;
        }
        protected void onPostExecute(Void result){
            pd.dismiss();
        }

    }

    private class BackTaskLock extends AsyncTask<String,Void,Void> {
        ProgressDialog pd;
        protected void onPreExecute(){
            super.onPreExecute();
            pd = new ProgressDialog(getActivity());
            pd.setTitle("Locking the file");
            pd.setMessage("Please wait.");
            pd.setCancelable(true);
            pd.setIndeterminate(true);
            pd.show();
        }
        protected Void doInBackground(String...params){
            try{
                startLock(params[0]);
            }catch(Exception e){
                pd.dismiss();   //close the dialog if error occurs
            }
            return null;
        }
        protected void onPostExecute(Void result){
            pd.dismiss();
        }
    }
}
