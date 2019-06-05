package StockTradingSystem.domain.entity;

import java.sql.Date;

public class TransactionLog {
    private int actionId;
    private int fundId;
    private Date actionTime;
    private double changeAmount;
    private String comment;

    public int getFundId() {
        return fundId;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public double getChangeAmount() {
        return changeAmount;
    }

    public int getActionId() {
        return actionId;
    }

    public String getComment() {
        return comment;
    }

    public void setFundId(int fundId) {
        this.fundId = fundId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    public void setChangeAmount(double changeAmount) {
        this.changeAmount = changeAmount;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
