package StockTradingSystem.http_utils;

public class Result {
    private boolean status;
    private String cause;
    public Result(){
        this.status = true;
        this.cause = null;
    }
    public Result(String cause){
        this.status = false;
        this.cause = cause;
    }

    public String getReasons() {
        return cause;
    }

    public void setReasons(String reasons) {
        this.cause = reasons;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


}