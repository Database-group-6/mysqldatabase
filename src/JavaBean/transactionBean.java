package JavaBean;

import java.sql.Timestamp;

//import DBJavaBean.dbHelper.Transaction;

public class transactionBean implements Comparable{
  private String ucid;
  private String maid;
  private Timestamp time;
  private float amount;
  
  @Override
  public String toString(){
      return String.format("ucid = %s , maid = %s, amount = %f, time = %s",ucid,maid,amount,time.toString());
  }

  @Override
  public int compareTo(Object o){
      return ((transactionBean)o).time.compareTo(this.time);
  }
  
  public String getUcid() {
    return ucid;
  }
  public void setUcid(String ucid) {
    this.ucid = ucid;
  }
  public String getMaid() {
    return maid;
  }
  public void setMaid(String maid) {
    this.maid = maid;
  }
  
  
  
  public Timestamp getTime() {
    return time;
  }
  public void setTime(Timestamp time) {
    this.time = time;
  }
  public float getAmount() {
    return amount;
  }
  public void setAmount(float amount) {
    this.amount = amount;
  }
  
}



