package project.kudos_it_manitch.erp_kudos.config;

/**
 * Created by MeKa-IT_Nit on 6/10/2560.
 */

public class Config {
    private String host = "http://192.168.100.9/cloudmeka/";
    private String serlogin = "app_controller/login_app";
    private String noti = "app_controller/get_notify";
    private String listapprove = "app_controller/get_list_approve";

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
}
