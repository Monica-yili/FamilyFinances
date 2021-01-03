package com.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 该类是数据库操作工具类，用于连接、访问、关闭数据库，作为进行增删改查数据库的辅助类使用。
 * 该类中方法为静态方法，调用XxxDao.xxx(x,x,...)即可使用
 * @author Yili
 *
 * @version 2020.12
 *
 */
public class DBUtil {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/familyfinance?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8&useSSL=false";
    private static final String USER = "root";
    private static final String PWD = "yi991208yi";
    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs;

    private DBUtil() {}

    /**
     * 该方法用于加载JDBC驱动程序
     *
     * @return 返回数据库连接
     */
    public static Connection getCon() {
        try {
            // 加载驱动程序
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PWD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    /**
     * 关闭数据库相关资源，无返回值
     */
    public static void closeAll() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null)
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        if (con != null)
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    /**
     * 对数据库数据进行查询
     *
     * @param sql sql语句
     * @param pras 一些关键值
     * @return 结果集
     */
    public static ResultSet query(String sql, Object... pras) {
        con = getCon();
        try {
            ps = con.prepareStatement(sql);
            if (pras != null) {
                for (int i = 0; i < pras.length; i++) {
                    ps.setObject(i + 1, pras[i]);
                }
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 对数据库执行增改删等操作
     *
     * @param sql sql语句
     * @param pras 一些关键值
     * @return 返回受影响的条数
     */

    public static int update(String sql, Object... pras) {
        int n = 0;
        con = getCon();
        try {
            ps = con.prepareStatement(sql);
            if (pras != null) {
                for (int i = 0; i < pras.length; i++) {
                    ps.setObject(i + 1, pras[i]);
                }
            }
            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return n;
    }
}

