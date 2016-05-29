package webbrowser.example.com.bachelorloker;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import webbrowser.example.com.bachelorloker.adapters.SampleFragmentPagerAdapter;

/**
 * Created by 85064_000 on 28.05.2016.
 */
public class Settings extends AppCompatActivity {

    Spinner one;
    Spinner two;
    Spinner three;
    String[] Onedata = {"В базі даних","Додавання символу '.' до імені" };
    String[] Twodata = {"Шифрування з закритим ключем", "AES"};
    String[] Threedata = {"one", "two", "three", "four", "five"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        one = (Spinner) findViewById(R.id.spinner1);
        two = (Spinner) findViewById(R.id.spinner2);
        three = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<String> Oneadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Onedata);
        Oneadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        one.setAdapter(Oneadapter);
        one.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if (position == 0){
                    SettingsManager.saveHide(Settings.this,Constants.DB);
                }else if (position == 1){
                    SettingsManager.saveHide(Settings.this,Constants.DOT);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });


        ArrayAdapter<String> Twoadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Twodata);
        Twoadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        two.setAdapter(Twoadapter);
        two.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });


        ArrayAdapter<String> Threeadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Threedata);
        Threeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        three.setAdapter(Threeadapter);
        three.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }
}
