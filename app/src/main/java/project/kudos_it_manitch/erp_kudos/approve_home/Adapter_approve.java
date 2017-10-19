package project.kudos_it_manitch.erp_kudos.approve_home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import project.kudos_it_manitch.erp_kudos.R;

/**
 * Created by MeKa-IT_Nit on 11/10/2560.
 */

public class Adapter_approve extends BaseAdapter {
    private Context context;
    private String[] run_number, projects, project_name, create_user, create_date, types;

    public Adapter_approve(Context context,String[] run_number, String[] projects, String[] project_name, String[] create_user, String[] create_date, String[] types) {
        this.context = context;
        this.run_number = run_number;
        this.projects = projects;
        this.project_name = project_name;
        this.create_user = create_user;
        this.create_date = create_date;
        this.types = types;
    }

    @Override
    public int getCount() {
        return run_number.length;
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
        View view1 = layoutInflater.inflate(R.layout.main_listview_pr, viewGroup, false);
        TextView project_nameTextView = view1.findViewById(R.id.project_name);
        TextView run_numberTextView = view1.findViewById(R.id.run_number);
        TextView create_userTextView = view1.findViewById(R.id.create_user);
        TextView create_dateTextView = view1.findViewById(R.id.date);
        ImageView img_type = view1.findViewById(R.id.img_type);
        ImageView next = view1.findViewById(R.id.next);
        if(types[i].equals("pr")){
            img_type.setImageResource(R.drawable.pr_icon1);
        }else if(types[i].equals("po")){
            img_type.setImageResource(R.drawable.po_icon1);
        }else{

        }
        next.setImageResource(R.drawable.next);
        project_nameTextView.setText(project_name[i]);
        run_numberTextView.setText(run_number[i]);
        create_userTextView.setText(create_user[i]);
        create_dateTextView.setText(create_date[i]);
        return view1;
    }
}
