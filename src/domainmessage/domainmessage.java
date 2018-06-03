package domainmessage;

import java.sql.SQLException;

import DBJavaBean.dbHelper;

public class domainmessage {
  private String nameID;
  private float money;
  private String userID;
  private String merchantID;
  public boolean res;
  dbHelper mysql = new dbHelper();
  public String Str = null;
  public String withdraw() throws SQLException
  {
      res = mysql.withdraw(nameID, money);
      if(res){
        Str = "success";
      }else{
        Str = "error";
      }
    //参数 nameID ,money
   
    return Str;
  }
  
  public String charge() throws SQLException
  {
      res = mysql.charge(nameID, money);
      if(res){
        Str = "success";
      }else{
        Str = "error";
      }
    //参数 nameID ,money
   
    return Str;
  }
  
  public String tran()
  {
    res = mysql.startTransaction(userID, merchantID, money);
    if(res){
      Str = "success";
    }else{
      Str = "error";
    }
    return Str;
  }

  public String getNameID() {
    return nameID;
  }

  public void setNameID(String nameID) {
    this.nameID = nameID;
  }

  public float getMoney() {
    return money;
  }

  public void setMoney(float money) {
    this.money = money;
  }

  public String getUserID() {
    return userID;
  }

  public void setUserID(String userID) {
    this.userID = userID;
  }

  public String getMerchantID() {
    return merchantID;
  }

  public void setMerchantID(String merchantID) {
    this.merchantID = merchantID;
  }
  
}
