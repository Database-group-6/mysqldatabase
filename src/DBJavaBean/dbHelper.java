package DBJavaBean;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.struts2.components.Push;

import JavaBean.messBean;
import JavaBean.transactionBean;

import javax.swing.plaf.nimbus.State;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

import java.text.SimpleDateFormat;

public class dbHelper {
  private Connection conn;
  ArrayList transactions =null;
  private ResultSet rs = null;
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
      String url = "jdbc:mysql://127.0.0.1:3306/mylabdb";    //JDBC的URL
      String user = "root";
      String pwd = "tjr19970907";
      String connectionString = "jdbc:mysql://127.0.0.1:3306/" + "mylabdb" + "?user=" + user + "&password=" + pwd + "&useUnicode=true&characterEncoding=UTF-8";
      //调用DriverManager对象的getConnection()方法，获得一个Connection对象
      try {
          //conn = DriverManager.getConnection(url, user, pwd);
          conn = DriverManager.getConnection(connectionString);
      } catch (SQLException e){
          e.printStackTrace();
      }
  }

    
  class Transaction{
    public String ucid;
    public String maid;
    public float amount;
    public Timestamp time;

    @Override
    public String toString(){
        return String.format("ucid = %s , maid = %s, amount = %f, time = %s",ucid,maid,amount,time.toString());
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
  
  
  public ArrayList getUserInfo(String uid) throws SQLException{
    Map<String, String> res = new HashMap<String, String>();
    transactions = new ArrayList();
    Statement stmt = conn.createStatement();
    String sql = String.format("select * from user where uid = %s;", uid);
    System.out.println(sql);
    ResultSet rs = stmt.executeQuery(sql);
    while(rs.next()){
      System.out.println("1");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String password = rs.getString("password");
        String nameID = rs.getString("uid");
        res.put("name", name);
        res.put("email", email);
        messBean mess  = new messBean();
        mess.setFour(email);
        mess.setName(name);
        mess.setNameID(nameID);
        mess.setIdentity(0);
        mess.setPassword(password);
        transactions.add(mess);
        
        
    }
    return transactions;
}

  public ArrayList getMerchantInfo(String mid) throws SQLException{
    Map<String, String> res = new HashMap<String, String>();
    transactions = new ArrayList();
    Statement stmt = conn.createStatement();
    String sql = String.format("select * from merchant where mid = %s;", mid);
    ResultSet rs = stmt.executeQuery(sql);
    while(rs.next()){
        String name = rs.getString("name");
        String bankid = rs.getString("bankid");
        String password = rs.getString("password");
        String nameID = rs.getString("mid");
        res.put("name", name);
        res.put("bankid", bankid);
        messBean mess  = new messBean();
        mess.setFour(bankid);
        mess.setName(name);
        mess.setNameID(nameID);
        mess.setPassword(password);
        mess.setIdentity(1);
        transactions.add(mess);
    }
    return transactions;
  }


  //创建商家
  public boolean createMerchant(String mid, String name,String pwd, String bankid){
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
  
  public boolean updateUserInfo(String uid, String name, String pwd, String email) throws SQLException{
    boolean st = false;
    Statement stmt = conn.createStatement();
    String sql = String.format("update user set name = \"%s\", password = \"%s\", email = \"%s\" where uid = \"%s\"", name, pwd, email, uid);
    int i = stmt.executeUpdate(sql);
    if(i == 1){
        st = true;
    }
    return st;
  }
  
  
  public boolean updateMerchantInfo(String mid, String name, String pwd, String bankid) throws SQLException{
      boolean st = false;
      Statement stmt = conn.createStatement();
      String sql = String.format("update merchant set name = \"%s\", password = \"%s\", bankid = \"%s\" where mid = \"%s\"", name, pwd, bankid, mid);
      int i = stmt.executeUpdate(sql);
      //System.out.println("4");
      if(i == 1){
        //System.out.println("5");
          st = true;
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
  
  

  
  public ArrayList<Transaction> queryTransactionByTimeByUid(String uid, String start, String finish){
    ArrayList<String> cards = new ArrayList<>();
    transactions = new ArrayList();
    try{
        Statement stmt = conn.createStatement();
        String sql = String.format("select ucid from usercard where uid = %s", uid);
        System.out.println(sql);
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next())
            cards.add(rs.getString("ucid"));

        String card;
        for(int i=0; i<cards.size(); i++){
            card = cards.get(i);
            //System.out.println(card);
            sql = String.format("select * from transaction where UserCard_ucid = \"%s\" and tatime "
                +"between \"%s\" and \"%s\"", card, start, finish);

            System.out.println(sql);
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                System.out.println("22222\n");
                //System.out.println("flag");
                Transaction trans = new Transaction();
                trans.amount = rs.getFloat("amount");
                trans.ucid = card;
                trans.maid = rs.getString("MerchantAccout_maid");
                trans.time = rs.getTimestamp("tatime");
                transactionBean mess = new transactionBean();
                mess.setAmount(trans.amount);
                
                System.out.println("1");
                mess.setMaid(trans.maid);
                mess.setTime(trans.time);
                mess.setUcid(trans.ucid);
                transactions.add(mess);
            }
        }
    } catch (SQLException e){
        e.printStackTrace();
    }
    return transactions;
}

public ArrayList<Transaction> queryTransactionByUid(String uid){
    Date today = new Date(System.currentTimeMillis());
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    return queryTransactionByTimeByUid(uid,"1970-01-01", df.format(today));
}

public ArrayList<Transaction> queryTransactionByTimeByMid(String mid, String start, String finish){
  transactions = new ArrayList();
    try{
        Statement stmt = conn.createStatement();
        String sql = String.format("select maid from merchantaccout where mid = %s", mid);
        ResultSet rs = stmt.executeQuery(sql);
        String maid = "";
        while(rs.next())
            maid = rs.getString("maid");

        sql = String.format("select * from transaction where MerchantAccout_maid = \"%s\" and tatime "
            +"between \"%s\" and \"%s\"", mid, start, finish); 
        rs = stmt.executeQuery(sql);
        while(rs.next()){
            Transaction trans = new Transaction();
            trans.amount = rs.getFloat("amount");
            trans.ucid = rs.getString("UserCard_ucid");
            trans.maid = maid;
            trans.time = rs.getTimestamp("tatime");
            transactionBean mess = new transactionBean();
            mess.setAmount(trans.amount);
            
            //System.out.println("1");
            mess.setMaid(trans.maid);
            mess.setTime(trans.time);
            mess.setUcid(trans.ucid);
            transactions.add(trans);
        }
    } catch (SQLException e){
        e.printStackTrace();
    }
    return transactions;
}

public ArrayList<Transaction> queryTransactionByMid(String mid){
    Date today = new Date(System.currentTimeMillis());
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    return queryTransactionByTimeByMid(mid, "1970-01-01", df.format(today));
}

public ArrayList<Transaction> queryTransactions(){
    ArrayList<Transaction> transactions = new ArrayList<>();
    try{
        Statement stmt = conn.createStatement();
        String sql = String.format("select * from transaction");
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            Transaction t = new Transaction();
            t.time = rs.getTimestamp("tatime");
            t.amount = rs.getFloat("amount");
            t.maid = rs.getString("MerchantAccount_maid");
            t.ucid = rs.getString("UserCard_ucid");
            transactions.add(t);
        }
    } catch (SQLException e){
        e.printStackTrace();
    }
    return transactions;
}


}