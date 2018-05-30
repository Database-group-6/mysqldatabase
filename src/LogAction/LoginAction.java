package LogAction;

import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import com.opensymphony.xwork2.ActionSupport;

import DBJavaBean.DB;
import DBJavaBean.dbHelper;


public class LoginAction extends ActionSupport {
    private String loginID;   
    private String password;
    private int Identity;
    private HttpServletRequest request;
    private boolean resultlog;
    DB Mysql = new DB();
    dbHelper mysql = new dbHelper();
    public ResultSet result;
    public String Str;
    
    public String getUserName(){
      return loginID;
    }
    public void setUserName(String userName)
    {
      this.loginID = userName;
    }
    public void setServletRequest(HttpServletRequest hsr)
    {
      request = hsr;
    }
    
    public String search(){   
      //System.out.println(email);
      
      if(Identity == 0)
        //student
      {
        resultlog = mysql.login(loginID,password,Identity);
        if(resultlog = true)
        {
          Str = "stusuccess";
        }
        else
        {
          Str = "error";
        }
      }
      else if(Identity == 1)
        //merchant
      {
        resultlog = mysql.login(loginID,password,Identity);
        if(resultlog = true)
        {
          Str = "mersuccess";
        }
        else
        {
          Str = "error";
        }
      }
      else
        //admin
      {
        resultlog = mysql.login(loginID,password,Identity);
        if(resultlog = true)
        {
          Str = "admsuccess";
        }
        else
        {
          Str = "error";
        }
      }
      
      return Str;
    }
    
    public String getPassword() {
      return password;
    }
    public void setPassword(String password) {
      this.password = password;
    }
    public int getIdentity() {
      return Identity;
    }
    public void setIdentity(int identity) {
      Identity = identity;
    }
    public String getLoginID() {
      return loginID;
    }
    public void setLoginID(String loginID) {
      this.loginID = loginID;
    }
    
  
}
