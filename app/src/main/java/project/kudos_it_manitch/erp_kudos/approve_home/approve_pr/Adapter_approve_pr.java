package project.kudos_it_manitch.erp_kudos.approve_home.approve_pr;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import project.kudos_it_manitch.erp_kudos.R;
import project.kudos_it_manitch.erp_kudos.config.Config;
import project.kudos_it_manitch.erp_kudos.getservice_okhttp.GetService;

/**
 * Created by MeKa-IT_Nit on 11/10/2560.
 */

public class Adapter_approve_pr extends BaseAdapter {
    private Context context;
    private String[] lock, user, approve_sequence,approve,disapprove,reject,message,id_item;
    private String doc_no,pj_code,m_user,type;
    private Config config;
    public Adapter_approve_pr(Context context,
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
                              String m_user,String type) {
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
        View view2 = layoutInflater.inflate(R.layout.main_approve_pr2, viewGroup, false);
        TextView approve_sequenceTextView = view2.findViewById(R.id.approve_sequence);
        TextView userTextView = view2.findViewById(R.id.user);
        TextView messageTextView = view2.findViewById(R.id.message);
        Button approveButton = view2.findViewById(R.id.approvebtn);
        Button disapproveButton = view2.findViewById(R.id.disapprovebtn);
        Button rejectbtnButton = view2.findViewById(R.id.rejectbtn);
        approve_sequenceTextView.setText(approve_sequence[i]);
        userTextView.setText(user[i]);
        messageTextView.setText(message[i]);

        if(approve[i].equals("false")){
            approveButton.setEnabled(false);
            approveButton.setBackgroundColor(Color.GRAY);
        }

        if(disapprove[i].equals("false")){
            disapproveButton.setEnabled(false);
            disapproveButton.setBackgroundColor(Color.GRAY);

        }

        if (reject[i].equals("false")){
            rejectbtnButton.setEnabled(false);
            rejectbtnButton.setBackgroundColor(Color.GRAY);
        }

        approveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Boolean res_approve = approve_action(id_item[i],doc_no,pj_code,approve_sequence[i],type);
                if(res_approve==true){
                    Toast.makeText(context, "approve สำเร็จ", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();


                }else{
                    Toast.makeText(context, "approve ไม่สำเร็จ", Toast.LENGTH_SHORT).show();
                }

            }
        });

        disapproveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, i+" disapprove "+id_item[i]+" "+doc_no, Toast.LENGTH_SHORT).show();
              Boolean status_disapprove =  disapprove_action(id_item[i], doc_no, type);
                if(status_disapprove==true){
                    Toast.makeText(context, "Disapprove สำเร็จ", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }else{
                    Toast.makeText(context, "Disapprove ไม่สำเร็จ", Toast.LENGTH_SHORT).show();
                }

            }
        });

        rejectbtnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, i+" reject "+id_item[i]+" "+doc_no, Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });



        return view2;
    }

    public Boolean approve_action(String item_id,String doc_no,String pj_code,String sequence,String type){

        boolean status_approve = false;
        String url = config.getHost() + "app_controller/approve_action/"+item_id+"/"+doc_no+"/"+pj_code+"/"+sequence+"/"+type;


        GetService getService = new GetService(context, url, null);

        getService.execute();

        try {
            String res = getService.get();

            Toast.makeText(context, res, Toast.LENGTH_LONG).show();

            if(res != null){
               status_approve = Boolean.parseBoolean(res.toString());

            }else{
                Toast.makeText(context,"ไม่สามารถ เชื่อมต่อได้", Toast.LENGTH_SHORT).show();

            }
        } catch (Exception e) {
            status_approve = false;
        }

        return status_approve;
    }

    public Boolean disapprove_action(String id_item,String doc_no,String type){
        boolean status_disapprove = false;
        String url = config.getHost() + "app_controller/disapprove_action/"+id_item+"/"+doc_no+"/"+pj_code+"/"+m_user+"/"+type;
        Toast.makeText(context, url, Toast.LENGTH_SHORT).show();

        GetService getService = new GetService(context, url, null);

        getService.execute();

        try {
            String res = getService.get();

            Toast.makeText(context, res, Toast.LENGTH_LONG).show();

            if(res != null){
                status_disapprove = Boolean.parseBoolean(res.toString());

            }else{
                Toast.makeText(context,"ไม่สามารถ เชื่อมต่อได้", Toast.LENGTH_SHORT).show();

            }
        } catch (Exception e) {
            status_disapprove = false;
        }

        return status_disapprove;
    }
}
