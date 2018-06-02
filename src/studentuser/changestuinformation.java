package studentuser;

import java.sql.SQLException;
import java.util.ArrayList;

import DBJavaBean.dbHelper;

public class changestuinformation {
  
  private String name;
  private String password;
  private String four;
  public boolean rs;
  dbHelper mysql = new dbHelper();
  public String Str;
  private String loginID;
  ArrayList result = null;
  
  public String passhref()
  {
    System.out.println(loginID);
    try {
      result  = mysql.getUserInfo(loginID);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    Str = "success";
  //生成user的数据 arraylist result 
    //result 包括属性：Name password email
    return Str;
  }
  
  public String update()
  {
     //进行update完成
    try {
      rs = mysql.updateUserInfo(loginID, name, password, four);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    if(rs == true)
      Str = "success";
    else
      Str = "error";
    return Str;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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

  public ArrayList getResult() {
    return result;
  }

  public void setResult(ArrayList result) {
    this.result = result;
  }
}
