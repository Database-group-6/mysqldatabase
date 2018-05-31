package administrator;

import java.util.ArrayList;

public class changeinformation {
  
  private String loginID;
  public String Str;
  private String nameID;
  private int Identity; //不进行变动
  ArrayList searchresult = null;
  private String oldid; //始终不变的是ID
  private String password;
  private String name;
  private String four;
  public String passhref()
  {
    Str = "success";
    return Str;
  }
  
  public String search()//搜寻要修改的人物
  //要得到 arraylist 包括 nameID Identity name password email/bank-four
  {
     
    return Str;
  }
  public String update() //得到内容后进行修改
  {
    
    return Str;
  }

  public String getNameID() {
    return nameID;
  }

  public void setNameID(String nameID) {
    this.nameID = nameID;
  }

  public int getIdentity() {
    return Identity;
  }

  public void setIdentity(int identity) {
    Identity = identity;
  }

  public ArrayList getSearchresult() {
    return searchresult;
  }

  public void setSearchresult(ArrayList searchresult) {
    this.searchresult = searchresult;
  }

  public String getOldid() {
    return oldid;
  }

  public void setOldid(String oldid) {
    this.oldid = oldid;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFour() {
    return four;
  }

  public void setFour(String four) {
    this.four = four;
  }

  public String getLoginID() {
    return loginID;
  }

  public void setLoginID(String loginID) {
    this.loginID = loginID;
  }
}
