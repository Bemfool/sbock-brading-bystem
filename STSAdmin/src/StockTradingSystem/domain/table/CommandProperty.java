package StockTradingSystem.domain.table;

import StockTradingSystem.domain.entity.Command;
import javafx.beans.property.*;

import java.util.Date;

public class CommandProperty {
    private ObjectProperty<Date> time;
    private IntegerProperty fundId;
    private IntegerProperty stockCount;
    private DoubleProperty stockPrice;

    public CommandProperty(Command cmd) {
        time = new SimpleObjectProperty<>(cmd.getTime());
        fundId = new SimpleIntegerProperty(cmd.getFundId());
        stockCount = new SimpleIntegerProperty(cmd.getStockCount());
        stockPrice = new SimpleDoubleProperty(cmd.getStockPrice());
    }

    public void setTime(Date time) {
        this.time.set(time);
    }

    public void setStockCount(int stockCount) {
        this.stockCount.set(stockCount);
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

    public IntegerProperty fundIdProperty() {
        return fundId;
    }

    public IntegerProperty stockCountProperty() {
        return stockCount;
    }

    public ObjectProperty<Date> timeProperty() {
        return time;
    }

    public double getStockPrice() {
        return stockPrice.get();
    }

    public DoubleProperty stockPriceProperty() {
        return stockPrice;
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice.set(stockPrice);
    }
}
