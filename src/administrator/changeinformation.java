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
  private int Identity; //�����б䶯
  //ArrayList searchresult = null;
  //Map searchresult = new HashMap();
  ArrayList searchresult = null;
  dbHelper mysql = new dbHelper();
  private String oldid; //ʼ�ղ������ID
  private String password;
  private String name;
  private String four;
  public String passhref()
  {
    Str = "success";
    return Str;
  }
  
  public String search() throws SQLException //��ѰҪ�޸ĵ�����
  //Ҫ�õ� arraylist ���� nameID Identity name password email/bank-four
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
    //��֪nameID ,Identity ����һ���˵��������
    
    return Str;
  }
  
  

  public String update() //�õ����ݺ�����޸�
  {
    //�Ѿ���nameID,Identity,name ,password,four[bankid/email]
    //nameID����,�����Ľ��и���
    if(Identity == 0)
    {
      
    }
    else if(Identity == 1)
    {
      
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
