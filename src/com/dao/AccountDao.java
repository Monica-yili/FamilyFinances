package com.dao;

import com.bean.Account;
import com.util.DBUtil;

import java.sql.*;
import java.util.Date;

public class AccountDao {
    private ResultSet rs = null;

    public AccountDao(){
    }

    /**
     * 登录
     * @param email 邮箱
     * @param password 密码
     * @return 账户id
     */
    public int login(String email, String password) {
        int FID = 0;
        String sql = "SELECT f_id FROM account WHERE f_email =? and f_password=?";
        rs = DBUtil.query(sql,email,password);
        try {
            if (rs.next())
                FID = rs.getInt("f_id");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll();
        }
        return FID;
    }

    /**
     * 验证邮箱是否被注册
     * @param email 邮箱
     * @return 布尔型参数
     */
    public boolean isExistEmail(String email) {
        String sql = "SELECT * FROM account WHERE f_email=?";
        rs=DBUtil.query(sql,email);
        try {
            if (rs.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll();
        }
        return false;
    }

    /**
     * 注册账户
     * @param email 邮箱
     * @param password 密码
     */
    public int save(String email, String password) {
            String sql = "INSERT INTO account(f_email,f_password) VALUES(?,?)";
            int n=DBUtil.update(sql,email,password);
            DBUtil.closeAll();
            return n;
    }

    public Account getByID(int fid) throws SQLException {
        ResultSet rs = DBUtil.query("SELECT * FROM account WHERE f_id=?",fid);
        Account account = new Account();
        while(rs.next()) {
            String password = rs.getString("f_password");
            String email = rs.getString("f_email");
            String familysize = rs.getString("f_familysize");
            int familySize = 1;
            if (familysize != "" && familysize != null) {
                familySize = Integer.parseInt(familysize);
            }
            String city = rs.getString("f_city");
            String sign = rs.getString("f_sign");

            account.setF_id(fid);
            account.setF_email(email);
            account.setF_password(password);
            account.setF_familysize(familySize);
            account.setF_sign(sign);
            account.setF_city(city);
        }
        DBUtil.closeAll();
            return account;
    }

    public boolean ReSetPW(int f_id, String newPW) {
        String sql = "UPDATE account SET f_password=? WHERE f_id=?";
        int n=DBUtil.update(sql,newPW,f_id);
        DBUtil.closeAll();
        if(n==1){
            return true;
        }else return false;
    }

    public boolean ReSetInfo(int f_id, int familySize, String city, String sign) {
        System.out.println(familySize+city+sign+f_id);
        String sql = "UPDATE account SET f_familysize=?,f_city=?,f_sign=? WHERE f_id=?";
        int n=DBUtil.update(sql,familySize,city,sign,f_id);
        DBUtil.closeAll();
        if(n==1){
            return true;
        }else return false;
    }
}
