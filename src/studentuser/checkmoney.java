package studentuser;

import java.util.ArrayList;

public class checkmoney {
  
  public String Str;
  private String loginID;
  
  private String startdate;
  private String finishdate;
  private String tradetime;
  
  private String tradeid;
  private String trademoney;
  ArrayList listresult = null;
  
  public String passhref()
  {
    Str = "success";
    return Str;
  }
  
  public String check()
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

  public ArrayList getListresult() {
    return listresult;
  }

  public void setListresult(ArrayList listresult) {
    this.listresult = listresult;
  }

}
