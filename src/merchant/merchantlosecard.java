package merchant;

import java.util.ArrayList;

public class merchantlosecard {
  
  public String Str;
  private String loginID;
  ArrayList result = null;
  private String noemail;
  private String password;
  private String losttime;
  public String passhref()
  {
    Str = "success";
    return Str;
  }
  
  public String freeze()
  {
    Str = "success";
    return Str;
  }
  public String newcard()
  {
    
    return Str;
  }
  public String freezefin()
  {
    
    return Str;
  }

  public String getLoginID() {
    return loginID;
  }

  public void setLoginID(String loginID) {
    this.loginID = loginID;
  }

  public String getNoemail() {
    return noemail;
  }

  public void setNoemail(String noemail) {
    this.noemail = noemail;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getLosttime() {
    return losttime;
  }

  public void setLosttime(String losttime) {
    this.losttime = losttime;
  }
  
}
