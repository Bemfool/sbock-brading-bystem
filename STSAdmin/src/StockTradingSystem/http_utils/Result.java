package StockTradingSystem.http_utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Result {
    private boolean status;
    private List<String> reasons;
    public Result(){
        this.status = true;
        this.reasons = null;
    }
    public Result(String... reasons){
        this.status = false;
        this.reasons = Arrays.asList(reasons);
    }

    public List<String> getReasons() {
        return reasons;
    }

    public void setReasons(List<String> reasons) {
        this.reasons = reasons;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void addReason(String reason){
        this.status = false;
        this.reasons = new ArrayList<>();
        reasons.add(reason);
    }


}