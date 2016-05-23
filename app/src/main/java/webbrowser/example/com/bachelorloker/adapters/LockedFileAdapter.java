package webbrowser.example.com.bachelorloker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


import webbrowser.example.com.bachelorloker.R;
import webbrowser.example.com.bachelorloker.db.DBLockedFile;

/**
 * Created by 85064_000 on 15.05.2016.
 */
public class LockedFileAdapter extends ArrayAdapter<DBLockedFile> {

    int resource;
    Context context;
    List<DBLockedFile> hideFilesList;
    LockedFileClickListener lockedFileClickListener;

    public LockedFileAdapter(Context context, int resource, List<DBLockedFile> hideFilesList, LockedFileClickListener lockedFileClickListener) {
        super(context, resource);
        this.hideFilesList = hideFilesList;
        this.resource = resource;
        this.context = context;
        this.lockedFileClickListener = lockedFileClickListener;
    }

    @Override
    public int getCount() {
        return hideFilesList.size();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(resource, parent, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.icon);
        TextView textView = (TextView) itemView.findViewById(R.id.label);
        LinearLayout items = (LinearLayout) itemView.findViewById(R.id.items);
        textView.setText(hideFilesList.get(position).fileType);
        items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lockedFileClickListener.addFile(hideFilesList.get(position));
            }
        });
        return itemView;
    }

    public interface LockedFileClickListener {

        void addFile(DBLockedFile dbLockedFile);
    }
}
