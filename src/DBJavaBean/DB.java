package DBJavaBean;


import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;


public class DB implements ServletRequestAware{
	private String driverName="com.mysql.jdbc.Driver";
	  private String user ="root";
	  
	  private String url = "jdbc:mysql://127.0.0.1:3306/urlsdb?useSSL=true";
	  private String password="tjr19970907";
	  
		
	 // private static String url = "jdbc:mysql://localhost:3306/urlsdb?useSSL=true";
	//private static String password = "1234";
		/* 
		 * in principle, these source codes that refer to the url and password to SQL server on sina
		 * should not just be commented but be deleted !!  
		 */
		//sina		//private static String url = "jdbc:mysql://fmpemtltjykv.mysql.sae.sina.com.cn:10319/urlsdb";
		//private static String password = "123456";
		
		private Connection con = null;
		  private Statement st =null;
		  private ResultSet rs = null;
		  private HttpServletRequest request;
  
  public DB(){
    
  }
  public String getDriverName(){
    return driverName;
  }
  public void setDriverName(String driverName)
  {
    this.driverName = driverName;
  }
  
  public String getUrl()
  {
    return url;
  }
  public void setUrl(String url)
  {
    this.url = url;
  }
  
  public String getUser()
  {
    return user;
  }
  public void setUser(String user)
  {
    this.user = user;
  }
  
  public String getPassword()
  {
    return password;
  }
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  public void setServletRequest(HttpServletRequest hsr)
  {
    request = hsr;
  }
  
  //完成数据库链接，生成容器并返回
  public Statement getStatement()
  {
    try{
      Class.forName(getDriverName());
      con = DriverManager.getConnection(getUrl(),getUser(),getPassword());
      return con.createStatement();
    }catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }
  
  
  public ResultSet selectLogin(HttpServletRequest request,String userName,String password,String Identity)
  {
    //System.out.println(userName);
    try{
      String sql = "select * from logindb where mail='"+userName+"'"+" and password='"+password+"'";
      st = getStatement();
      return st.executeQuery(sql);
    }catch(Exception e){
      e.printStackTrace();
      return null;
    }
  }
  
  
}