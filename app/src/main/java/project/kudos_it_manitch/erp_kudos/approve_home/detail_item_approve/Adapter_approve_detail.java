package project.kudos_it_manitch.erp_kudos.approve_home.detail_item_approve;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import project.kudos_it_manitch.erp_kudos.R;

/**
 * Created by MeKa-IT_Nit on 11/10/2560.
 */

public class Adapter_approve_detail extends BaseAdapter {
    private Context context;
    private String[]  id, doc_date, qty, unit, mat_name, mat_code, amount, priceunit;
    private String type,doc_no,m_id, m_code ,m_user;
    public Adapter_approve_detail(Context context,
                                  String[] id,
                                  String[] doc_date,
                                  String[] qty,
                                  String[] unit,
                                  String[] mat_name,
                                  String[] mat_code,
                                  String[] amount,
                                  String[] priceunit,
                                  String doc_no,
                                  String m_user,
                                  String m_id,
                                  String m_code,
                                  String type
                             ) {
        this.context = context;
        this.id = id;
        this.doc_date = doc_date;
        this.qty = qty;
        this.unit = unit;
        this.mat_name = mat_name;
        this.mat_code = mat_code;
        this.amount = amount;
        this.priceunit = priceunit;
        this.doc_no = doc_no;
        this.m_user = m_user;
        this.m_id = m_id;
        this.m_code = m_code;
        this.type = type;

    }



    @Override
    public int getCount() {
        return id.length;
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
        View view3 = layoutInflater.inflate(R.layout.main_showapprove_detail, viewGroup, false);


        return view3;
    }




}
