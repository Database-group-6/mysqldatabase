package administrator;

import java.util.ArrayList;

public class changeinformation {
  
  private String loginID;
  public String Str;
  private String nameID;
  private int Identity; //�����б䶯
  ArrayList searchresult = null;
  private String oldid; //ʼ�ղ������ID
  private String password;
  private String name;
  private String four;
  public String passhref()
  {
    Str = "success";
    return Str;
  }
  
  public String search()//��ѰҪ�޸ĵ�����
  //Ҫ�õ� arraylist ���� nameID Identity name password email/bank-four
  {
     
    return Str;
  }
  public String update() //�õ����ݺ�����޸�
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
