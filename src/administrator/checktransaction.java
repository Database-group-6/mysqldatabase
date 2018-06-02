package administrator;

import java.util.ArrayList;
import java.util.Date;

import DBJavaBean.dbHelper;
import javafx.scene.control.DatePicker;

public class checktransaction {
  public String Str;
  private String nameID;
  private String loginID;
  private String startdate;
  private String finishdate;
  private int Identity;
  ArrayList listresult = null;
  private String checkid;
  dbHelper mysql = new dbHelper();
  private String tradetime;
  private String tradeid;
  private String trademoney;
  public String passhref()
  {
    Str = "success";
    return Str;
  }
  
  public String check()
  {
    if(Identity ==0)
    {
      //System.out.println(startdate);
      //student
      System.out.println(finishdate);
      listresult = mysql.queryByUid(nameID,startdate,finishdate);
      if(listresult.size() == 0)
        Str = "error";
      //System.out.println(startdate.toString());
      else
        Str = "success1";
      
    }
    else if(Identity == 1)
    {
      listresult = mysql.queryByMid(nameID,startdate,finishdate);
      if(listresult.size() == 0)
        Str = "error";
      //System.out.println(startdate.toString());
      else
        Str = "success2";
    }
    else
    {
      Str = "error";
    }
    //Identity nameID startdate finishdate
    
    //listresult = songyunfei's API;
        
    //Str = "fail1";//查无此人
    //Str = "fail2";//时间不对
    //Str = "fail3";//无交易记录
    return Str;
  }

  public String getNameID() {
    return nameID;
  }

  public void setNameID(String nameID) {
    this.nameID = nameID;
  }

  

  
  public ArrayList getListresult() {
    return listresult;
  }

  public void setListresult(ArrayList listresult) {
    this.listresult = listresult;
  }

  public String getCheckid() {
    return checkid;
  }

  public void setCheckid(String checkid) {
    this.checkid = checkid;
  }

  public String getTradetime() {
    return tradetime;
  }

  public void setTradetime(String tradetime) {
    this.tradetime = tradetime;
  }

  public String getTradeid() {
    return tradeid;
  }

  public void setTradeid(String tradeid) {
    this.tradeid = tradeid;
  }

  public String getTrademoney() {
    return trademoney;
  }

  public void setTrademoney(String trademoney) {
    this.trademoney = trademoney;
  }

  public String getLoginID() {
    return loginID;
  }

  public void setLoginID(String loginID) {
    this.loginID = loginID;
  }

  public int getIdentity() {
    return Identity;
  }

  public void setIdentity(int identity) {
    Identity = identity;
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

  

  
}
