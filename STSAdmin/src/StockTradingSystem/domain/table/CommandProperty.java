package StockTradingSystem.domain.table;

import StockTradingSystem.domain.entity.Command;
import javafx.beans.property.*;

import java.util.Date;

public class CommandProperty {
    private ObjectProperty<Date> time;
    private IntegerProperty fundId;
    private StringProperty stockCode;
    private IntegerProperty stockCount;

    public CommandProperty(Command cmd) {
        time = new SimpleObjectProperty<>(cmd.getTime());
        fundId = new SimpleIntegerProperty(cmd.getFundId());
        stockCode = new SimpleStringProperty(cmd.getStockCode());
        stockCount = new SimpleIntegerProperty(cmd.getStockCount());
    }

    public void setTime(Date time) {
        this.time.set(time);
    }

    public void setStockCount(int stockCount) {
        this.stockCount.set(stockCount);
    }

    public void setStockCode(String stockCode) {
        this.stockCode.set(stockCode);
    }

    public void setFundId(int fundId) {
        this.fundId.set(fundId);
    }

    public Date getTime() {
        return time.get();
    }

    public int getFundId() {
        return fundId.get();
    }

    public int getStockCount() {
        return stockCount.get();
    }

    public String getStockCode() {
        return stockCode.get();
    }

    public IntegerProperty fundIdProperty() {
        return fundId;
    }

    public IntegerProperty stockCountProperty() {
        return stockCount;
    }

    public ObjectProperty<Date> timeProperty() {
        return time;
    }

    public StringProperty stockCodeProperty() {
        return stockCode;
    }
}
