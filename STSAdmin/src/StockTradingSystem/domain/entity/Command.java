package StockTradingSystem.domain.entity;

import java.sql.Date;

public class Command {
    private Date time;
    private int fundId;
    private boolean commandType;
    private String stockCode;
    private int stockCount;

    public void setFundId(int fundId) {
        this.fundId = fundId;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public void setCommandType(boolean commandType) {
        this.commandType = commandType;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getFundId() {
        return fundId;
    }

    public String getStockCode() {
        return stockCode;
    }

    public Date getTime() {
        return time;
    }

    public int getStockCount() {
        return stockCount;
    }

    public boolean isCommandType() {
        return commandType;
    }
}
