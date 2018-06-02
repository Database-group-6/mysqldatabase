package administrator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import DBJavaBean.dbHelper;

public class changeinformation {
  
  private String loginID;
  public String Str;
  private String nameID;
  private int Identity; //不进行变动
  //ArrayList searchresult = null;
  //Map searchresult = new HashMap();
  ArrayList searchresult = null;
  dbHelper mysql = new dbHelper();
  private String oldid; //始终不变的是ID
  private String password;
  private String name;
  private String four;
  public boolean result;
  public String passhref()
  {
    Str = "success";
    return Str;
  }
  
  public String search() throws SQLException //搜寻要修改的人物
  //要得到 arraylist 包括 nameID Identity name password email/bank-four
  {
    if(Identity == 0)
    {
      searchresult = mysql.getUserInfo(nameID);
      //String name = searchresult.("name").toString();
      //System.out.println(name);
      if(searchresult.size() == 0)
        Str = "error";
      else
        Str = "success";
      
    }
    else if(Identity == 1)
    {
      searchresult = mysql.getMerchantInfo(nameID);
      //String name = searchresult.("name").toString();
      //System.out.println(name);
      if(searchresult.size() == 0)
        Str = "error";
      else
        Str = "success";
    }
    else
    {
      Str = "error";
    }
    //已知nameID ,Identity 返回一个人的所有情况
    
    return Str;
  }
  
  

  public String update() //得到内容后进行修改
  {
    //已经有nameID,Identity,name ,password,four[bankid/email]
    //nameID不变,其他的进行更新
    if(Identity == 0)
    {
      try {
        result = mysql.updateUserInfo(nameID, name, password, four);
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      if(result == true)
        Str = "success";
      else
        Str = "error";
    }
    else if(Identity == 1)
    {
      try {
        System.out.println(nameID+name+password+four);
        result = mysql.updateMerchantInfo(nameID, name, password, four);
        System.out.println("1");
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      if(result == true)
      {
        System.out.println("2");
        Str = "success";
      }
      else
      {
        System.out.println("3");
        Str = "error";
      }
    }
    else
    {
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

  public int getIdentity() {
    return Identity;
  }

  public void setIdentity(int identity) {
    Identity = identity;
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

  public ArrayList getSearchresult() {
    return searchresult;
  }

  public void setSearchresult(ArrayList searchresult) {
    this.searchresult = searchresult;
  }

  
}
