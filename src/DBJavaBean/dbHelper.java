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
      //���Լ���MySQL����
      try{
          //����Class.forName()����������������
          Class.forName("com.mysql.jdbc.Driver");
          System.out.println("�ɹ�����MySQL������");
      }catch(ClassNotFoundException e1){
          System.out.println("�Ҳ���MySQL����!");
          e1.printStackTrace();
      }
      //����׼��
      String url = "jdbc:mysql://123.206.9.180:3306/mylabdb";    //JDBC��URL
      String user = "root";
      String pwd = "password";
      String connectionString = "jdbc:mysql://123.206.9.180:3306/" + "BookDB" + "?user=" + user + "&password=" + pwd + "&useUnicode=true&characterEncoding=UTF-8";
      //����DriverManager�����getConnection()���������һ��Connection����
      try {
          //conn = DriverManager.getConnection(url, user, pwd);
          conn = DriverManager.getConnection(connectionString);
      } catch (SQLException e){
          e.printStackTrace();
      }
  }

  //�ر�����
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


  //�����̼�
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


  //�����û�
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

  //��½���
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