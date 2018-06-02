package studentuser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import DBJavaBean.dbHelper;

public class checkmoney {
  
  public String Str;
  private String loginID;
  private float balance;
  private float income;
  private float out;
  private String startdate;
  private String finishdate;
  private String time;
  dbHelper mysql = new dbHelper();
  private String maid;
  private String amount;
  
  ArrayList listresult = null;
  
  public String passhref()
  {
    Str = "success";
    return Str;
  }
  
  public String check()
  {
    //startdate +finishdate +loginID
    listresult = mysql.queryByUid(loginID,startdate,finishdate);
    if(listresult.size() == 0)
      Str = "error";
    else
      Str = "success";
    
    return Str;
  }
  
  public String statistics()
  {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
    String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
    System.out.println(date);
    income = mysql.queryInByUid(loginID,"0000-00-00",date);
    out = mysql.queryOutByUid(loginID, "0000-00-00", date);
    balance = income - out;
    Str = "success";
    return Str;
  }

  public String getLoginID() {
    return loginID;
  }

  public void setLoginID(String loginID) {
    this.loginID = loginID;
  }

  public String getStartdate() {
    return startdate;
  }

  public void setStartdate(String startdate) {
    this.startdate = startdate;
  }

  public String getFinishdate() {
    return finishdate;
  }

  public void setFinishdate(String finishdate) {
    this.finishdate = finishdate;
  }

 

  public ArrayList getListresult() {
    return listresult;
  }

  public void setListresult(ArrayList listresult) {
    this.listresult = listresult;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getMaid() {
    return maid;
  }

  public void setMaid(String maid) {
    this.maid = maid;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public float getBalance() {
    return balance;
  }

  public void setBalance(float balance) {
    this.balance = balance;
  }

  public float getIncome() {
    return income;
  }

  public void setIncome(float income) {
    this.income = income;
  }

  public float getOut() {
    return out;
  }

  public void setOut(float out) {
    this.out = out;
  }

}
