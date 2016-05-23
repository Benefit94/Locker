package webbrowser.example.com.bachelorloker.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import webbrowser.example.com.bachelorloker.fragments.EncriptedFiles;
import webbrowser.example.com.bachelorloker.fragments.FragmentFileList;
import webbrowser.example.com.bachelorloker.fragments.LockedFiles;

/**
 * Created by Serhii on 4/3/2016.
 */
public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Bla bla files", "Hidden files", "Encrypted files" };
    private Context context;

    public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return FragmentFileList.newInstance(position + 1);
            case 1:
                return LockedFiles.newInstance(position + 1);
            case 2:
                return EncriptedFiles.newInstance(position + 1);
        }
        return FragmentFileList.newInstance(position + 1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
