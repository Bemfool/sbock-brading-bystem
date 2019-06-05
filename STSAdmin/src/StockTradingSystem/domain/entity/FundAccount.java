package StockTradingSystem.domain.entity;

public class FundAccount {
    private int fundId;
    private int securitiesId;
    private String password;
    private double balance;
    private double interest;
    private boolean state;


    public FundAccount() {
        fundId=-1;
        securitiesId=-1;
        password="";
        balance=0;
        interest=0;
        state=false;
    }

    public FundAccount(int fid,int sid,String pwd,double bal,double itst,boolean stat) {
        fundId=fid;
        securitiesId=sid;
        password=pwd;
        balance=bal;
        interest=itst;
        state=stat;
    }

    public void setFundId(int fundId) {
        this.fundId = fundId;
    }

    public void setSecuritiesId(int securitiesId) {
        this.securitiesId = securitiesId;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public int getFundId() {
        return fundId;
    }

    public int getSecuritiesId() {
        return securitiesId;
    }

    public double getBalance() {
        return balance;
    }

    public double getInterest() {
        return interest;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public boolean isState() {
        return state;
    }
}
