package com.dao;

import com.bean.Expend;
import com.util.DBUtil;
import com.util.JsonUtil;
import org.json.JSONException;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExpendDao {
    private ExpendDao(){

    }

    /**
     * 读取数据库刷新.json文件
     * @param f_id
     * @throws IOException
     * @throws SQLException
     * @throws JSONException
     */
    public static void freshOut(int f_id) throws IOException, SQLException, JSONException {
        String sql = "SELECT * FROM expend WHERE f_id=? ORDER BY out_time DESC";
        ResultSet rs = DBUtil.query(sql,f_id);
        JsonUtil.SQLToJSON(rs,"D://apache-tomcat-8.5.57-windows-x64/webapps/artifacts/FamilyFinances_war_exploded/json/Expend_data.json");

        ResultSet rs2 = DBUtil.query(sql,f_id);
        JsonUtil.SQLToJSON(rs2,"D://apache-tomcat-8.5.57-windows-x64/webapps/artifacts/FamilyFinances_war_exploded/json/Expend_BILL.json");

        DBUtil.closeAll();
    }

    /**
     * 通过out_id删除数据
     * @param out_id
     * @return
     */
    public static boolean delByidOut(String out_id) {
        int id=Integer.parseInt(out_id);
        String sql = "DELETE FROM expend WHERE out_id=?";
        int n = DBUtil.update(sql,id);
        DBUtil.closeAll();
        if(n==1){
            return true;
        }else return false;
    }

    /**
     * 添加
     * @param expend
     * @return
     */
    public static boolean addOut(Expend expend) {
        String sql = "INSERT INTO expend(f_id,out_type,out_amount,out_time,out_note) " +
                "VALUES(?,?,?,?,?)";
        int n = DBUtil.update(sql,expend.getF_id(),expend.getOut_type(),expend.getOut_amount(),
                expend.getOut_time(),expend.getOut_note());
        DBUtil.closeAll();
        if(n==1){
            return true;
        }else return false;
    }

    /**
     * 修改
     * @param expend
     * @return
     */
    public static boolean modOut(Expend expend) {
        String sql = "UPDATE expend SET out_type=?,out_amount=?,out_note=? WHERE out_id=?";
        int n = DBUtil.update(sql,expend.getOut_type(),expend.getOut_amount(),
                expend.getOut_note(),expend.getOut_id());
        DBUtil.closeAll();
        if(n==1){
            return true;
        }else return false;
    }

    public static void check(int fid, String start, String end, String select)
            throws JSONException, SQLException, IOException {
        String sql = "SELECT * FROM expend "
                +"WHERE DATE_FORMAT(out_time,'%y-%m-%d') >= DATE_FORMAT(?,'%y-%m-%d') "
                +"AND DATE_FORMAT(out_time,'%y-%m-%d') <= DATE_FORMAT(?,'%y-%m-%d') AND out_type=? AND f_id=?";
        ResultSet rs = DBUtil.query(sql,start,end,select,fid);
        JsonUtil.SQLToJSON(rs,"D://apache-tomcat-8.5.57-windows-x64/webapps/artifacts/FamilyFinances_war_exploded/json/Expend_BILL.json");

        DBUtil.closeAll();
    }
}
