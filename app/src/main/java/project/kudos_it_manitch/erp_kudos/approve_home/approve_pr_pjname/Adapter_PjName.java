package project.kudos_it_manitch.erp_kudos.approve_home.approve_pr_pjname;

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

public class Adapter_PjName extends BaseAdapter {
    private Context context;
    private String[]  project_name,project_code,project_id;

    public Adapter_PjName(Context context, String[] project_name, String[] project_code, String[] project_id) {
        this.context = context;
        this.project_name = project_name;
        this.project_code = project_code;
        this.project_id = project_id;
    }

    @Override
    public int getCount() {
        return project_name.length;
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
        View view1 = layoutInflater.inflate(R.layout.main_list_pjname, viewGroup, false);
        TextView project_nameTextView = view1.findViewById(R.id.project_name);
        ImageView next = view1.findViewById(R.id.next);
        project_nameTextView.setText(project_name[i]);
        next.setImageResource(R.drawable.next);
        return view1;
    }
}
