package DBJavaBean;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.struts2.components.Push;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;

public class dbHelper {
  private Connection conn;
  public dbHelper(){
      //尝试加载MySQL驱动
      try{
          //调用Class.forName()方法加载驱动程序
          Class.forName("com.mysql.jdbc.Driver");
          System.out.println("成功加载MySQL驱动！");
      }catch(ClassNotFoundException e1){
          System.out.println("找不到MySQL驱动!");
          e1.printStackTrace();
      }
      //数据准备
      String url = "jdbc:mysql://123.206.9.180:3306/mylabdb";    //JDBC的URL
      String user = "root";
      String pwd = "password";
      String connectionString = "jdbc:mysql://123.206.9.180:3306/" + "BookDB" + "?user=" + user + "&password=" + pwd + "&useUnicode=true&characterEncoding=UTF-8";
      //调用DriverManager对象的getConnection()方法，获得一个Connection对象
      try {
          //conn = DriverManager.getConnection(url, user, pwd);
          conn = DriverManager.getConnection(connectionString);
      } catch (SQLException e){
          e.printStackTrace();
      }
  }

  //关闭连接
  public boolean closeConn(){
      boolean flag = false;
      try{
          conn.close();
          flag = true;
      } catch (SQLException e){
          e.printStackTrace();
      }
      return flag;
  }


  //创建商家
  public boolean createMerchant(String mid, String name, int pwd, String bankid){
      boolean st = false;
      try{
          Statement stmt  = conn.createStatement();
          String sql = String.format("insert into merchant(mid, name, password, bankid) values(%s, %s, %s, %s)", mid, name, pwd, bankid);
          int i = stmt.executeUpdate(sql);
          if(i == 1)
              st = true;
      } catch (SQLException e){
          e.printStackTrace();
      }
      return st;
  }


  //创建用户
  public boolean createUser(String uid, String name, String pwd, String email){
      boolean st = false;
      try{
          Statement stmt = conn.createStatement();
          String sql = String.format("insert into user(uid, name, password, email) values(%s, %s, %s, %s)", uid, name, pwd, email);
          int i = stmt.executeUpdate(sql);
          if(i == 1)
              st = true;
      }catch (SQLException e){
          e.printStackTrace();
      }
      return st;
  }
  public float getUserBalance(String uid){
      float balance = -1;
      try{
          Statement stmt = conn.createStatement();
          String sql = "insert into user(uid, name, password, email)";
      } catch(SQLException e){
          e.printStackTrace();
      }
      return balance;
  }

  //登陆检测
  public boolean login(String id, String pwd, int type){
      String sql = "";
      boolean st = false;
      if(id == "123456" && pwd == "123456" && type == 2){
          st = true;
      }
      if(type == 0) {
          sql = String.format("select * from user where uid = %s and password = %s", id, pwd);
      }
      else if (type == 1){
          sql = String.format("select * from merchant where uid = %s and password = %s", id, pwd);
      }
      try{
          Statement stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery(sql);
          if(rs.next()){
              st = true;
          }
      } catch(SQLException e){
          e.printStackTrace();
      }
      return st;
  }

}