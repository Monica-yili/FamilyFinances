package com.dao;

import com.bean.Expend;
import com.bean.Finance;
import com.util.DBUtil;
import com.util.JsonUtil;
import org.json.JSONException;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FinanceDao {
    public FinanceDao(){}

    public static void freshFin(int f_id) throws IOException, SQLException, JSONException {
        String sql = "SELECT * FROM finance where DATE_FORMAT(end_time,'%y-%m-%d') > DATE_FORMAT(now(),'%y-%m-%d') AND f_id=?";
        ResultSet rs = DBUtil.query(sql,f_id);
        JsonUtil.SQLToJSON(rs,"D://apache-tomcat-8.5.57-windows-x64/webapps/artifacts/FamilyFinances_war_exploded/json/fin_living.json");

        DBUtil.closeAll();

        String sql2 = "SELECT * FROM finance where DATE_FORMAT(end_time,'%y-%m-%d') <= DATE_FORMAT(now(),'%y-%m-%d') AND f_id=?";
        ResultSet rs2 = DBUtil.query(sql2,f_id);
        JsonUtil.SQLToJSON(rs2,"D://apache-tomcat-8.5.57-windows-x64/webapps/artifacts/FamilyFinances_war_exploded/json/fin_finish.json");

        String sql3 = "SELECT * FROM finance where f_id=?";
        ResultSet rs3 = DBUtil.query(sql3,f_id);
        JsonUtil.SQLToJSON(rs3,"D://apache-tomcat-8.5.57-windows-x64/webapps/artifacts/FamilyFinances_war_exploded/json/Finance_BILL.json");

        DBUtil.closeAll();

    }

    /**
     * 添加
     * @param finance
     * @return
     */
    public static boolean addfin(Finance finance) {
        String sql = "INSERT INTO finance(f_id,fin_type,fin_rate,fin_amount,start_time,end_time,fin_note,fin_earn) " +
                "VALUES(?,?,?,?,?,?,?,?)";
        int n = DBUtil.update(sql,finance.getF_id(),finance.getFin_type(),finance.getFin_rate(),
                finance.getFin_amount(),finance.getStart_time(),finance.getEnd_time(),
                finance.getFin_note(),finance.getFin_earn());
        DBUtil.closeAll();
        if(n==1){
            return true;
        }else return false;
    }

    public static void check(int fid, String start, String end, String select) throws JSONException, SQLException, IOException {
        String sql = "SELECT * FROM finance "
                +"WHERE DATE_FORMAT(fin_time,'%y-%m-%d') >= DATE_FORMAT(?,'%y-%m-%d') "
                +"AND DATE_FORMAT(fin_time,'%y-%m-%d') <= DATE_FORMAT(?,'%y-%m-%d') AND fin_type=? AND f_id=?";
        ResultSet rs = DBUtil.query(sql,start,end,select,fid);
        JsonUtil.SQLToJSON(rs,"D://apache-tomcat-8.5.57-windows-x64/webapps/artifacts/FamilyFinances_war_exploded/json/Finance_BILL.json");


        DBUtil.closeAll();
    }

    /**
     * 总额
     * @param fid
     * @return
     */
    public double sum(int fid) throws SQLException {
        String sql="SELECT SUM(fin_amount) FROM finance WHERE f_id=?";
        ResultSet rs=DBUtil.query(sql,fid);
        String Sum="";
        while(rs.next()){
            Sum = rs.getString("SUM(fin_amount)");
            if(Sum==null)return 0.0;
            else return Double.parseDouble(Sum);
        }
        return 0.0;
    }

    /**
     * 活跃理财
     * @param fid
     * @return
     */
    public double livingSum(int fid) throws SQLException {
        String sql="SELECT SUM(fin_amount) FROM finance WHERE " +
                "DATE_FORMAT(end_time,'%y-%m-%d') < DATE_FORMAT(now(),'%y-%m-%d') AND f_id=?";
        ResultSet rs=DBUtil.query(sql,fid);
        String lSum="";
        while(rs.next()){
            lSum = rs.getString("SUM(fin_amount)");
            if(lSum==null)return 0.0;
            else return Double.parseDouble(lSum);
        }
        return 0.0;
    }
}
