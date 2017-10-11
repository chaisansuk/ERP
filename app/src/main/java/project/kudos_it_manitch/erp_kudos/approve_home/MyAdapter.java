package project.kudos_it_manitch.erp_kudos.approve_home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import project.kudos_it_manitch.erp_kudos.R;


public class MyAdapter extends BaseAdapter {

    //Explicit
    private Context context;
    private String[] projectnameStrings,projectstatusStrings;



    public MyAdapter(Context context, String[] projectnameStrings, String[] projectstatusStrings) {
        this.context = context;
        this.projectnameStrings = projectnameStrings;
        this.projectstatusStrings = projectstatusStrings;

    }

    @Override
    public int getCount() {
        return projectnameStrings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view2 = layoutInflater.inflate(R.layout.my_listview2, viewGroup, false);

        //Bind Widget
        TextView projectnameTextView = (TextView) view2.findViewById(R.id.date);
        TextView projectstatusTextView = (TextView) view2.findViewById(R.id.run_number);
        //Show Text
        projectnameTextView.setText(projectnameStrings[i]);
        return view2;
    }
}   // Main Class