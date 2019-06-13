package StockTradingSystem.http_utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Result {
    private boolean status;
    private String reason;
    public Result(){
        this.status = true;
        this.reason = null;
    }
    public Result(String reason){
        this.status = false;
        this.reason = reason;
    }

    public String getReasons() {
        return reason;
    }

    public void setReasons(String reasons) {
        this.reason = reasons;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


}