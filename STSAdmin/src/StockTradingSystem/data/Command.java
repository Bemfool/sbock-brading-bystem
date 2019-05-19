package StockTradingSystem.data;

import javafx.beans.property.*;

public class Command {
    private StringProperty time;
    private StringProperty fundID;
    private StringProperty commandType;
    private StringProperty stockCode;
    private IntegerProperty stockCount;
    //private boolean isselect;

    public Command(String time,String fundID,String commandType,String stockCode,Integer stockCount){
        this.time=new SimpleStringProperty(time);
        this.fundID=new SimpleStringProperty(fundID);
        this.commandType=new SimpleStringProperty(commandType);
        this.stockCode=new SimpleStringProperty(stockCode);
        this.stockCount=new SimpleIntegerProperty(stockCount);
    }

    public Command(){
        this.time=new SimpleStringProperty("");
        this.fundID=new SimpleStringProperty("");
        this.commandType=new SimpleStringProperty("");
        this.stockCode=new SimpleStringProperty("");
        this.stockCount=new SimpleIntegerProperty(0);
    }

    public void setTime(String time) { this.time.set(time); }
    public void setFundID(String fundID) { this.fundID.set(fundID); }
    public void setCommandType(String commandType) { this.commandType.set(commandType); }
    public void setStockCode(String stockCode) { this.stockCode.set(stockCode); }
    public void setStockCount(Integer stockCount) { this.stockCount.set(stockCount); }
    //public boolean isIsselect() { return isselect; }

    public String setTime() { return time.get(); }
    public String setFundID() { return fundID.get(); }
    public String setCommandType() { return commandType.get(); }
    public String setStockCode() { return stockCode.get(); }
    public Integer setStockCount() { return stockCount.get(); }


    public StringProperty timeProperty() { return time; }
    public StringProperty fundIDProperty() { return stockCode; }
    public StringProperty commandTypeProperty() { return commandType; }
    public StringProperty stockCodeProperty() { return stockCode; }
    public IntegerProperty stockCountProperty() { return stockCount; }
    //public void setIsselect(boolean isselect) { this.isselect = isselect; }
}