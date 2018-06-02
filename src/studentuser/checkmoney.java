package studentuser;

import java.util.ArrayList;

import DBJavaBean.dbHelper;

public class checkmoney {
  
  public String Str;
  private String loginID;
  
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
    listresult = mysql.queryTransactionByTimeByUid(loginID,startdate,finishdate);
    if(listresult.size() == 0)
      Str = "error";
    else
      Str = "success";
    
    return Str;
  }
  
  public String statistics()
  {
    
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

}
