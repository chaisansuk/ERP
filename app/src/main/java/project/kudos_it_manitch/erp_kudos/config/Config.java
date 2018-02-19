package project.kudos_it_manitch.erp_kudos.config;

/**
 * Created by MeKa-IT_Nit on 6/10/2560.
 */

public class Config {
    //private String host = "http://192.168.100.238/mekabase/";
    private String host = "http://192.168.1.36/kudos/";

    private String serlogin = "app_controller/login_app";
    private String noti = "app_controller/get_notify";
    private String listapprove = "app_controller/get_list_approve";
    private String listproject = "app_controller/get_list_project";
    private String detail_sequenc = "app_controller/detail_approve_sequence";
    private String get_item_document = "app_controller/get_item_document";
    private String get_ic_receive = "app_controller/get_project_ic_receive";
    private String get_po_receive = "app_controller/get_project_po_receive";

    public String getHost() {
        return host;
    }

    public String getSerlogin() {
        return  host+serlogin;
    }

    public String getNoti() {
        return  host+noti;
    }

    public String getListapprove() {
        return  host+listapprove;
    }

    public String getListproject() {
        return  host+listproject;
    }

    public String getDetailSequenc() {
        return  host+detail_sequenc;
    }

    public String getItem_document() {
        return  host+get_item_document;
    }

    public String getIc_receive() {
        return  host+get_ic_receive;
    }

    public String getPo_receive() {
        return  host+get_po_receive;
    }


}
