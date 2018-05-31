package administrator;

import java.util.ArrayList;

public class checktransaction {
  public String Str;
  private String nameID;
  private String startdate;
  private String finishdate;
  private String Identity;
  ArrayList listresult = null;
  private String checkid;
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
    //listresult = songyunfei's API;
        Str = "success";
    Str = "fail1";//查无此人
    Str = "fail2";//时间不对
    Str = "fail3";//无交易记录
    return Str;
  }

  public String getNameID() {
    return nameID;
  }

  public void setNameID(String nameID) {
    this.nameID = nameID;
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

  public String getIdentity() {
    return Identity;
  }

  public void setIdentity(String identity) {
    Identity = identity;
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
  
}
