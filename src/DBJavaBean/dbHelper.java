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
import java.util.Collections;
import java.util.Comparator;
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
  
  public ArrayList queryByUid(String uid, String start, String finish){
    ArrayList<transactionBean> in = queryChargeByTimeByUid(uid, start, finish);
    ArrayList<transactionBean> out = queryTransactionByTimeByUid(uid, start, finish);
    System.out.println(in.size());
    System.out.println(out.size());
    in.addAll(out);
    Collections.sort(in);
    return in;
  }
  
public ArrayList<transactionBean> queryChargeByTimeByUid(String uid, String start, String finish){
    ArrayList<transactionBean> transactions = new ArrayList<>();
    try{
        Statement stmt = conn.createStatement();
        String sql = String.format("select * from chargerecord where User_uid = \"%s\" " +
                "and chargetime between \"%s\" and \"%s\"", uid, start, finish);
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            transactionBean cur = new transactionBean();
            cur.setAmount(rs.getFloat("amount"));
            cur.setUcid(uid);
            cur.setMaid("Administer");
            cur.setTime(rs.getTimestamp("chargetime"));
            transactions.add(cur);
        }
    }catch (SQLException e){
        e.printStackTrace();
    }
    return transactions;
  }


  public ArrayList<transactionBean> queryTransactionByTimeByUid(String uid, String start, String finish){
      ArrayList<String> cards = new ArrayList<>();
      ArrayList<transactionBean> transactions = new ArrayList<>();
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
                  //System.out.println("flag");
                  transactionBean trans = new transactionBean();
                  trans.setAmount(rs.getFloat("amount"));
                  trans.setUcid(card);
                  trans.setMaid(rs.getString("MerchantAccout_maid"));
                  trans.setTime(rs.getTimestamp("tatime"));
                  transactions.add(trans);
              }
          }
      } catch (SQLException e){
          e.printStackTrace();
      }
      return transactions;
  }
  
  public ArrayList<transactionBean> queryTransactionByUid(String uid){
      Date today = new Date(System.currentTimeMillis());
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      return queryTransactionByTimeByUid(uid,"1970-01-01", df.format(today));
  }
  
  public ArrayList<transactionBean> queryTransactionByTimeByMid(String mid, String start, String finish){
    ArrayList<transactionBean> transactions = new ArrayList<>();
    try{
        Statement stmt = conn.createStatement();
        String sql = String.format("select maid from merchantaccout where mid = %s", mid);
        ResultSet rs = stmt.executeQuery(sql);
        String maid = "";
        while(rs.next())
            maid = rs.getString("maid");
  
        sql = String.format("select * from transaction where MerchantAccout_maid = %s" +
                " and tatime between \"%s\" and \"%s\"", maid, start, finish);
        rs = stmt.executeQuery(sql);
        while(rs.next()){
            transactionBean trans = new transactionBean();
            trans.setAmount(rs.getFloat("amount"));
            trans.setUcid(rs.getString("UserCard_ucid"));
            trans.setMaid(maid);
            trans.setTime(rs.getTimestamp("tatime"));
            transactions.add(trans);
        }
    } catch (SQLException e){
        e.printStackTrace();
    }
    return transactions;
  }
  
  public ArrayList<transactionBean> queryTransactionByMid(String mid){
    Date today = new Date(System.currentTimeMillis());
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    return queryTransactionByTimeByMid(mid, "1970-01-01", df.format(today));
  }
  
  public ArrayList<transactionBean> queryTransactions(){
    ArrayList<transactionBean> transactions = new ArrayList<>();
    try{
        Statement stmt = conn.createStatement();
        String sql = String.format("select * from transaction");
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            transactionBean t = new transactionBean();
            t.setTime(rs.getTimestamp("tatime"));
            t.setAmount(rs.getFloat("amount"));
            t.setMaid(rs.getString("MerchantAccount_maid"));
            t.setUcid(rs.getString("UserCard_ucid"));
            transactions.add(t);
        }
    } catch (SQLException e){
        e.printStackTrace();
    }
    return transactions;
  }
  
  public ArrayList<transactionBean> queryByMid(String mid, String start, String finish){
    ArrayList<transactionBean> in = queryTransactionByTimeByMid(mid, start, finish);
    ArrayList<transactionBean> out = queryWithdrawByTimeByMid(mid, start, finish);
    in.addAll(out);
    Collections.sort(in);
    return in;
  }

  public ArrayList<transactionBean> queryWithdrawByTimeByMid(String mid, String start, String finish){
      ArrayList<transactionBean> withdraw = new ArrayList<>();
      try{
          Statement stmt = conn.createStatement();
          String sql = String.format("select * from withdrawrecord where merchant_mid = \"%s\" "+
                  "and withdrawtime between \"%s\" and \"%s\" ", mid, start, finish);
          ResultSet rs = stmt.executeQuery(sql);
          while(rs.next()){
              transactionBean cur = new transactionBean();
              cur.setAmount(rs.getFloat("amount"));
              cur.setMaid(mid);
              cur.setTime(rs.getTimestamp("withdrawtime"));
              cur.setUcid("Bank");
              withdraw.add(cur);
          }
      }catch (SQLException e){
          e.printStackTrace();
      }
      return withdraw;
  }

  
  // 查询某学生充值了多少钱
  public float queryInByUid(String uid, String start, String finish){
      double total = 0.0;
      try{
          Statement stmt = conn.createStatement();
          String sql = String.format("select amount from chargerecord where User_uid = \"%s\"" +
                  "and chargetime between \"%s\" and \"%s\"", uid, start, finish);
          ResultSet rs = stmt.executeQuery(sql);
          while(rs.next()){
              total += rs.getFloat("amount");
          }
      }catch (SQLException e){
          e.printStackTrace();
      }
      return (float) total;
  }

  // 查询某学生花了多少钱
  public float queryOutByUid(String uid, String start, String finish){
      double total = 0.0;
      ArrayList<String> cards = new ArrayList<>();
      try{
          Statement stmt = conn.createStatement();
          String sql = String.format("select * from usercard where uid = \"%s\"", uid);
          ResultSet rs = stmt.executeQuery(sql);
          while(rs.next()){
              cards.add(rs.getString("ucid"));
          }

          int length = cards.size();
          for(int i=0; i<length; i++){
              String card = cards.get(i);
              sql = String.format("select amount from transaction where UserCard_ucid = \"%s\""
                      + "and tatime between \"%s\" and \"%s\"", card, start, finish);
              rs = stmt.executeQuery(sql);
              while(rs.next()){
                  total += rs.getFloat("amount");
              }
          }
      }catch (SQLException e){
          e.printStackTrace();
      }
      return (float) total;
  }

  // 查询商家收入
  public float queryInByMid(String mid, String start, String finish){
      double total = 0.0;
      try{
          String maid;
          Statement stmt = conn.createStatement();
          String sql = String.format("select maid from merchantaccout where mid = \"%s\"", mid);
          ResultSet rs = stmt.executeQuery(sql);
          while(rs.next()){
              maid = rs.getString("maid");
          }

          sql = String.format("select amount from transaction where MerchantAccout_maid = \"%s\""
                      + "and tatime between \"%s\" and \"%s\"", mid, start, finish);
          rs = stmt.executeQuery(sql);
          while(rs.next()){
              total += rs.getFloat("amount");
          }
      }catch (SQLException e){
          e.printStackTrace();
      }
      return (float) total;
  }
//查询某学生充值了多少钱
  public float queryOutByMid(String mid, String start, String finish){
    ArrayList<transactionBean> all = queryWithdrawByTimeByMid(mid, start, finish);
    double total = 0.0;
    for(transactionBean cur : all){
        total += cur.getAmount();
    }
    return (float) total;
}
               
  
//更新用户的卡
  public String updateCard(String uid){
      // in our system, we use the card format like "uid-num"

      String ucid = "";
      try{
          String old_ucid = getUserCard(uid);
          float balance = 0;
          Statement stmt = conn.createStatement();
          // if there is no card related with the user, uid = "cid-1"
          if(old_ucid == ""){
              ucid = uid + "-1";
          }else{
              // if there is an old card, update the ucid
              ucid = uid + "-" + (Integer.valueOf(old_ucid.split("-")[1]) + 1);
              // get the balance in the old card
              balance = getUserBalance(uid);
              String sql0 = String.format("update userown set isUsed = 0 where UserCard_ucid = \"%s\"", old_ucid);
              stmt.executeUpdate(sql0);
          }

          //add a new card and add the relationship
          String sql1 = String.format("insert into usercard(ucid, uid, balance, status) values( \"%s\",  \"%s\", %f, %d)", ucid, uid, balance, 0);
          String sql2 = String.format("insert into userown(isUsed, UserCard_ucid, User_uid) values(%d,  \"%s\",  \"%s\")", 1, ucid, uid);
          stmt.executeUpdate(sql1);
          stmt.executeUpdate(sql2);

      }catch (SQLException e){
          e.printStackTrace();
      }
      return ucid;
  }

  // 更新商家的账户
  public String updateAccount(String mid){
      String maid = getMerchantAccount(mid);
      // in the system, there is only one account related with the merchant.
      // if the merchant don't have an account, add an account
      // else do nothing.
      try{
          Statement stmt = conn.createStatement();
          if(maid == ""){
              String sql2 = String.format("insert into merchantaccout(maid, mid, balance, status) values(%s, %s, %f, %d)", mid, mid, 0, 0);
              stmt.executeUpdate(sql2);
              String sql1 = String.format("insert into merchantonw(MerchantAccout_maid, merchant_mid) values(%s, %s)", mid, mid);
              int i = stmt.executeUpdate(sql1);
              if(i == 1){
                  maid = mid;
              }
          }
      }catch (SQLException e){
          e.printStackTrace();
      }
      return maid;
  }

  
//获取用户当前使用的卡
  public String getUserCard(String uid){
      String ucid = "";
      try{
          Statement stmt = conn.createStatement();
          String sql = String.format("select UserCard_ucid from userown where User_uid = %s and isUsed = 1", uid);
          ResultSet rs = stmt.executeQuery(sql);
          while(rs.next()){
              ucid = rs.getString("UserCard_ucid");
          }
      } catch (SQLException e){
          e.printStackTrace();
      }
      return ucid;
  }

  // 获取商家当前账户
  public String getMerchantAccount(String mid){
      String maid = "";
      try{
          Statement stmt = conn.createStatement();
          String sql = String.format("select MerchantAccout_maid from merchantown where merchant_mid = %s and status = 0", mid);
          ResultSet rs = stmt.executeQuery(sql);
          while(rs.next()){
              maid = rs.getString("MerchantAccout_maid");
          }
      } catch (SQLException e){
          e.printStackTrace();
      }
      return maid;
  }
  
  //创建商家
  public boolean createMerchant(String mid, String name,String pwd, String bankid){
      boolean st = false;
      try{
          Statement stmt  = conn.createStatement();
          String sql = String.format("insert into merchant(mid, name, password, bankid) values( \"%s\",  \"%s\",  \"%s\",  \"%s\")", mid, name, pwd, bankid);
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
          String sql = String.format("insert into user(uid, name, password, email) values( \"%s\",  \"%s\",  \"%s\",  \"%s\")", uid, name, pwd, email);
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

//获取商家余额
  public float getMerchantBalance(String mid){
      float balance = -1;
      try{
          Statement stmt = conn.createStatement();
          String maid = getMerchantAccount(mid);
          String sql = String.format("select balance from merchantaccout where maid = %s", maid);
          ResultSet rs = stmt.executeQuery(sql);
          while(rs.next()){
              balance = rs.getFloat("balance");
          }

      }catch (SQLException e){
          e.printStackTrace();
      }
      return balance;
  }
//进行交易
  public boolean startTransaction(String uid, String mid, float price){
      boolean st = false;
      // start the transaction, and in the sql schema
      // there is a trigger to judge  if the transaction is legal
      try{
          String sql = String.format("insert into transaction(UserCard_ucid, MerchantAccout_maid, amount) values(\"%s\", \"%s\", %f)", uid, mid, price);
          Statement stmt = conn.createStatement();
          if(stmt.executeUpdate(sql) == 1){
              st = true;
          }
      } catch(SQLException e){
          e.printStackTrace();
      }
      return st;
  }


  //提款
  public boolean withdraw(String mid, float amount) throws SQLException{
      boolean st = false;
      String maid = getMerchantAccount(mid);
      float balance = getMerchantBalance(mid);
      // get the current balance of the merchant, and judge if the withdraw is ok?
      float new_balance = balance - amount;
      if(new_balance > 0) {
          String sql = String.format("update mrechantaccout set balance = %f where maid = \"%s\"", new_balance, maid);
          Statement stmt = conn.createStatement();
          int i = stmt.executeUpdate(sql);
          if(i == 1){
              st = true;
          }
      }
      return st;
  }
//充值
  public boolean charge(String uid, float amount) throws SQLException{
      String ucid = getUserCard(uid);
      float balance = getUserBalance(uid);
      float new_balance = balance + amount;
      Statement stmt = conn.createStatement();
      String sql = String.format("update usercard set balance = %f where ucid = %s", new_balance, ucid);
      int i = stmt.executeUpdate(sql);
      if(i == 1){
          return true;
      }else{
          return false;
      }
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
          sql = String.format("select * from merchant where mid = %s and password = %s", id, pwd);
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