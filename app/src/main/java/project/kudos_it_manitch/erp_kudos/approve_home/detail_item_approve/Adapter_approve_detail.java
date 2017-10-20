package project.kudos_it_manitch.erp_kudos.approve_home.detail_item_approve;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import project.kudos_it_manitch.erp_kudos.R;
import project.kudos_it_manitch.erp_kudos.config.Config;

/**
 * Created by MeKa-IT_Nit on 11/10/2560.
 */

public class Adapter_approve_detail extends BaseAdapter {
    private Context context;
    private String[] lock, user, approve_sequence,approve,disapprove,reject,message,id_item;
    private String doc_no,pj_code,m_user,type;
    private Config config;
    public Adapter_approve_detail(Context context,
                                  String[] lock,
                                  String[] user,
                                  String[] approve_sequence,
                                  String[] approve,
                                  String[] disapprove,
                                  String[] reject,
                                  String[] message,
                                  String[] id_item,
                                  String doc_no,
                                  String pj_code,
                                  String m_user,
                                  String type
                             ) {
        this.context = context;
        this.lock = lock;
        this.user = user;
        this.approve_sequence = approve_sequence;
        this.approve = approve;
        this.disapprove = disapprove;
        this.reject = reject;
        this.message = message;
        this.id_item = id_item;
        this.doc_no = doc_no;
        this.pj_code = pj_code;
        this.m_user = m_user;
        this.type = type;

         config = new Config();

    }



    @Override
    public int getCount() {
        return user.length;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view2 = layoutInflater.inflate(R.layout.main_approve_pr_po, viewGroup, false);
        TextView approve_sequenceTextView = view2.findViewById(R.id.approve_sequence);
        TextView userTextView = view2.findViewById(R.id.user);
        TextView messageTextView = view2.findViewById(R.id.message);
        Button approveButton = view2.findViewById(R.id.approvebtn);
        Button disapproveButton = view2.findViewById(R.id.disapprovebtn);
        Button rejectbtnButton = view2.findViewById(R.id.rejectbtn);
        ImageView lock = view2.findViewById(R.id.lock);

        return view2;
    }




}
