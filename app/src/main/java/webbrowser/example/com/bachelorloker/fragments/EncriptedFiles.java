package webbrowser.example.com.bachelorloker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import webbrowser.example.com.bachelorloker.R;

/**
 * Created by Serhii on 4/3/2016.
 */
public class EncriptedFiles extends Fragment{



    public static final String ARG_PAGE = "ARG_PAGE";

    public static EncriptedFiles newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        EncriptedFiles fragment = new EncriptedFiles();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.encripted_file, container, false);

        return view;
    }
}
