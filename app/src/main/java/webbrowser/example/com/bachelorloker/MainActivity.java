package webbrowser.example.com.bachelorloker;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import webbrowser.example.com.bachelorloker.adapters.SampleFragmentPagerAdapter;


/**
 * Created by Serhii on 4/3/2016.
 */
public class MainActivity extends AppCompatActivity{

    /*private String path="";
    private String selectedFile="";
    private Context context;*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(), MainActivity.this));
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this,Settings.class);
            //ActivityCompat.startActivity(this, intent,new Bundle());
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /*protected void onStart(){
        super.onStart();
        ListView lv=(ListView) findViewById(R.id.files_list);
        if(lv!=null){
            //lv.setSelector(R.drawable.selection_style);
            lv.setOnItemClickListener(new ClickListener());
        }
        path="/mnt";
        listDirContents();
    }*/

    /*public void onBackPressed(){
        if(path.length()>1){ //up one level of directory structure
            File f=new File(path);
            path=f.getParent();
            listDirContents();
        }
        else{
            refreshThumbnails();
            System.exit(0); //exit app
        }
    }*/


    /*private void refreshThumbnails(){
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory())));
    }*/

    /*private class ClickListener implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //selected item
            ViewGroup vg=(ViewGroup)view;
            String selectedItem = ((TextView) vg.findViewById(R.id.label)).getText().toString();
            path=path+"/"+selectedItem;
            //et.setText(path);
            listDirContents();
        }
    }*/

    /*private void listDirContents(){
       // ListView l=(ListView) findViewById(R.id.files_list);
        if(path!=null){
            try{
                File f=new File(path);
                if(f!=null){
                    if(f.isDirectory()){
                        String[] contents=f.list();
                        if(contents.length>0){
                            //create the data source for the list
                            ListAdapterModel lm=new ListAdapterModel(this,R.layout.listlayout,R.id.label,contents,path);
                            //supply the data source to the list so that they are ready to display
                            //l.setAdapter(lm);
                        }
                        else
                        {
                            //keep track the parent directory of empty directory
                            path=f.getParent();
                        }
                    }
                    else{
                        //capture the selected file path
                        selectedFile=path;
                        //keep track the parent directory of the selected file
                        path=f.getParent();
                    }
                }
            }catch(Exception e){}
        }
    }*/

    /*public void lockFile(View view){
        File f=new File(selectedFile);
        String filename=f.getPath().substring(path.lastIndexOf("/") + 1);
        String[] name = filename.split("/");
        String fileName = name[1];
        f.renameTo(new File(path, "." + fileName));
    }*/

    /*public void encryptionFile(View view){
        EditText txtpwd=(EditText)findViewById(R.id.txt_input);
        String pwd=txtpwd.getText().toString();
        if(pwd.length()>0){
            if(selectedFile.length()>0){
                BackTaskLock btlock=new BackTaskLock();
                btlock.execute(pwd,null,null);
            }
            else{
                MessageAlert.showAlert("Please a select a file to lock",context);
            }
        }
        else{
            MessageAlert.showAlert("Please enter password",context);
        }
    }*/

    /*public void startLock(String pwd){
        Locker locker=new Locker(context,selectedFile,pwd);
        locker.lock();
    }

    public void unlockFile(View view){
        File f=new File(selectedFile);
        String filename=f.getPath().substring(path.lastIndexOf("/") + 1);
        String[] name = filename.split("/.");
        String fileName = name[1];
        f.renameTo(new File(path, fileName));
    }*/

    /*public void dencryptionFile(View view){
        EditText txtpwd=(EditText)findViewById(R.id.txt_input);
        String pwd=txtpwd.getText().toString();
        if(pwd.length()>0){
            if(selectedFile.length()>0){
                BackTaskUnlock btunlock=new BackTaskUnlock();
                btunlock.execute(pwd,null,null);
            }
            else{
                MessageAlert.showAlert("Please select a file to unlock",context);
            }
        }
        else{
            MessageAlert.showAlert("Please enter password",context);
        }
    }*/

    /*public void startUnlock(String pwd){
        Locker locker=new Locker(context,selectedFile,pwd);
        locker.unlock();
    }*/

    /*private class BackTaskLock extends AsyncTask<String,Void,Void> {
        ProgressDialog pd;
        protected void onPreExecute(){
            super.onPreExecute();
            //show process dialog
            pd = new ProgressDialog(context);
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

    private class BackTaskUnlock extends AsyncTask<String,Void,Void>{
        ProgressDialog pd;
        protected void onPreExecute(){
            super.onPreExecute();
            //show process dialog
            pd = new ProgressDialog(context);
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

    }*/
}
