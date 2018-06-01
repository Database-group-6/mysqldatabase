package JavaBean;

import java.sql.Timestamp;

public class transactionBean {
  private String ucid;
  private String maid;
  private Timestamp time;
  private float amount;
  
  
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
