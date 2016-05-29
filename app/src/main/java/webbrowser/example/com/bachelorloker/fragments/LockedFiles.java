package webbrowser.example.com.bachelorloker.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import webbrowser.example.com.bachelorloker.Constants;
import webbrowser.example.com.bachelorloker.HideFiles;
import webbrowser.example.com.bachelorloker.R;
import webbrowser.example.com.bachelorloker.SettingsManager;
import webbrowser.example.com.bachelorloker.Unsecur;
import webbrowser.example.com.bachelorloker.adapters.LockedFileAdapter;
import webbrowser.example.com.bachelorloker.db.DBHelper;
import webbrowser.example.com.bachelorloker.db.DBLockedFile;
import webbrowser.example.com.bachelorloker.db.DBSecurMet;

/**
 * Created by Serhii on 4/3/2016.
 */
public class LockedFiles extends Fragment implements LockedFileAdapter.LockedFileClickListener, SwipeRefreshLayout.OnRefreshListener {

    ListView list;
    LockedFileAdapter lockedFileAdapter;
    RelativeLayout data;
    LinearLayout no_data;
    Button bt_unlock;
    SwipeRefreshLayout refresh;
    String fileName = "";
    String fileLocation = "";
    byte[] byteMas;
    String normName = "";

    public static final String ARG_PAGE = "ARG_PAGE";

    public static LockedFiles newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        LockedFiles fragment = new LockedFiles();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.locked_files, container, false);
        list = (ListView) view.findViewById(R.id.list);
        bt_unlock = (Button) view.findViewById(R.id.bt_unlock);
        data = (RelativeLayout) view.findViewById(R.id.data);
        no_data = (LinearLayout) view.findViewById(R.id.no_data);
        refresh = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        refresh.setOnRefreshListener(this);
        hideKeyboard();
        //refresh.setColorScheme(Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED);
        loadData();
        return view;
    }


    public void loadData(){
        List<DBLockedFile> hideFiles = DBHelper.getFile();
        if (hideFiles.size() == 0){
            data.setVisibility(View.GONE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            lockedFileAdapter = new LockedFileAdapter(getActivity(),R.layout.listlayout,hideFiles,LockedFiles.this);
            list.setAdapter(lockedFileAdapter);
            bt_unlock.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DBSecurMet dbSecurMet = DBHelper.getMetsByName(fileName);
                    switch(dbSecurMet.hideProt) {
                        case Constants.DB:
                            Unsecur.unhideFroDB(fileLocation, fileName, byteMas);
                            break;
                        case Constants.DOT:
                            Unsecur.unhideDot(fileName,fileLocation,dbSecurMet.normName);
                            break;
                    }
                }
            });
        }
        refresh.setRefreshing(false);
    }

    @Override
    public void addFile(DBLockedFile dbLockedFile) {
        fileLocation = dbLockedFile.location;
        fileName = dbLockedFile.fileType;
        byteMas = dbLockedFile.byteMas;
    }

    @Override
    public void onRefresh() {
        refresh.setRefreshing(true);
        loadData();
    }

    public void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
