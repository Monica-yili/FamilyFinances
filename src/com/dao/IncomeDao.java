package com.dao;

import com.bean.Income;
import com.util.DBUtil;
import com.util.JsonUtil;
import org.json.JSONException;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IncomeDao {
    private IncomeDao(){

    }

    public static void freshIn(int f_id) throws IOException, SQLException, JSONException {
        String sql = "SELECT * FROM income WHERE f_id=? ORDER BY in_time DESC";
        ResultSet rs = DBUtil.query(sql,f_id);
        JsonUtil.SQLToJSON(rs,"D://apache-tomcat-8.5.57-windows-x64/webapps/artifacts/FamilyFinances_war_exploded/json/Income_data.json");

        ResultSet rs2 = DBUtil.query(sql,f_id);
        JsonUtil.SQLToJSON(rs2,"D://apache-tomcat-8.5.57-windows-x64/webapps/artifacts/FamilyFinances_war_exploded/json/Income_BILL.json");

        DBUtil.closeAll();
    }

    public static boolean delByidIn(String in_id) {
        int id=Integer.parseInt(in_id);
        String sql = "DELETE FROM income WHERE in_id=?";
        int n = DBUtil.update(sql,id);
        DBUtil.closeAll();
        if(n==1){
            return true;
        }else return false;
    }

    public static boolean addIn(Income income) {
        String sql = "INSERT INTO income(f_id,in_type,in_amount,in_time,in_note) " +
                "VALUES(?,?,?,?,?)";
        int n = DBUtil.update(sql,income.getF_id(),income.getIn_type(),income.getIn_amount(),
                income.getIn_time(),income.getIn_note());
        DBUtil.closeAll();
        if(n==1){
            return true;
        }else return false;
    }

    public static boolean modIn(Income income) {
        String sql = "UPDATE income SET in_type=?,in_amount=?,in_note=? WHERE in_id=?";
        int n = DBUtil.update(sql,income.getIn_type(),income.getIn_amount(),
                income.getIn_note(),income.getIn_id());
        DBUtil.closeAll();
        if(n==1){
            return true;
        }else return false;
    }

    public static void check(int fid, String start, String end, String select)
            throws JSONException, SQLException, IOException {
        String sql = "SELECT * FROM income "
                +"WHERE DATE_FORMAT(in_time,'%y-%m-%d') >= DATE_FORMAT(?,'%y-%m-%d') "
        +"AND DATE_FORMAT(in_time,'%y-%m-%d') <= DATE_FORMAT(?,'%y-%m-%d') AND in_type=? AND f_id=?";
        ResultSet rs = DBUtil.query(sql,start,end,select,fid);
        JsonUtil.SQLToJSON(rs,"D://apache-tomcat-8.5.57-windows-x64/webapps/artifacts/FamilyFinances_war_exploded/json/Income_BILL.json");


        DBUtil.closeAll();
    }
}
