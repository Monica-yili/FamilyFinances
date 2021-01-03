package com.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class JsonUtil{
    private static int col=0;
    private JsonUtil(){}
    /**
     * 结果集转.json文件
     * @param rs
     * @throws SQLException
     * @throws IOException
     * @throws JSONException
     */
    public static void SQLToJSON(ResultSet rs, String fileName) throws SQLException, IOException, JSONException {
        JSONArray array = SQLtoJSONARRAY(rs);
        JSONARRAYtoSTRING(array,fileName);
    }

    /**
     * 结果集转JSONArray数组
     * @param rs
     * @return
     * @throws SQLException
     * @throws JSONException
     */
    private static JSONArray SQLtoJSONARRAY(ResultSet rs)throws SQLException, JSONException{
        ResultSetMetaData metaData =  rs.getMetaData();
        int columnCount= metaData.getColumnCount();

        JSONArray array = new JSONArray();
        while(rs.next()) {
            JSONObject jsonObj = new JSONObject();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnLabel(i);
                String value = rs.getString(columnName);
                jsonObj.put(columnName, value);
            }
            array.put(jsonObj);
            col++;
        }
        return array;
    }

    /**
     * JSONArray数组存入.json文件
     * @param array
     * @throws IOException
     */
    private static void JSONARRAYtoSTRING(JSONArray array, String FileName) throws IOException {
        File file=new File(FileName);
        if(!file.exists())//判断文件是否存在，若不存在则新建
        {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream=new FileOutputStream(file);//实例化FileOutputStream
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream,"utf-8");//将字符流转换为字节流
        BufferedWriter bufferedWriter= new BufferedWriter(outputStreamWriter);//创建字符缓冲输出流对象

        String jsonString=array.toString();//将jsonarray数组转化为字符串
        bufferedWriter.write("{\"total\":"+col+",\"rows\":");
        col=0;
        String JsonString=stringToJSON(jsonString);//将jsonarrray字符串格式化
        bufferedWriter.write(JsonString);//将格式化的jsonarray字符串写入文件
        bufferedWriter.write("}");
        bufferedWriter.flush();//清空缓冲区，强制输出数据
        bufferedWriter.close();//关闭输出流
    }

    /**
     * jsonarrray字符串格式化
     * @param strJson
     * @return
     */
    private static String stringToJSON(String strJson) {
        StringBuffer jsonFormat = new StringBuffer();
        int length = strJson.length();
        for (int i = 0; i < length; i++) {
            char c = strJson.charAt(i);
            jsonFormat.append(c);
        }
        return jsonFormat.toString();
    }
}
