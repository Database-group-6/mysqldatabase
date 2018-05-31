package administrator;

import DBJavaBean.dbHelper;

public class CreateAccount {
  private String loginID;
  public String Str;
  public boolean resultcreate;
  private String uid;
  private String name;
  private String pwd;
  private String email;
  private String mid;
  private String bankid;
  dbHelper mysql = new dbHelper();
  public String passhref()
  {
    Str = "success";
    return Str;
  }
  
  
  public String createmerchant()
  {
    resultcreate = mysql.createMerchant(mid, name, pwd, bankid);
    if(resultcreate == true)
      Str = "success";
    else
      Str = "error";
    //Str = "success";
    return Str;
  }
  
  public String createstudent()
  {
    resultcreate = mysql.createUser(uid, name, pwd, email);
    if(resultcreate == true)
    {
      Str = "success";
      System.out.println("1");
    }
      
    else
      Str = "error";
    //Str = "success";
    return Str;
  }


  public String getLoginID() {
    return loginID;
  }


  public void setLoginID(String loginID) {
    this.loginID = loginID;
  }


  public String getUid() {
    return uid;
  }


  public void setUid(String uid) {
    this.uid = uid;
  }


  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public String getPwd() {
    return pwd;
  }


  public void setPwd(String pwd) {
    this.pwd = pwd;
  }


  public String getEmail() {
    return email;
  }


  public void setEmail(String email) {
    this.email = email;
  }


  public String getMid() {
    return mid;
  }


  public void setMid(String mid) {
    this.mid = mid;
  }


  public String getBankid() {
    return bankid;
  }


  public void setBankid(String bankid) {
    this.bankid = bankid;
  }
}
