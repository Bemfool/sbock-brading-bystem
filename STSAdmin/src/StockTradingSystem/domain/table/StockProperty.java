package StockTradingSystem.domain.table;

import StockTradingSystem.domain.entity.Stock;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StockProperty {
    private StringProperty stockCode;
    private StringProperty stockName;
    private DoubleProperty stockPrice;
    private DoubleProperty ceilingPrice;
    private DoubleProperty floorPrice;
    private StringProperty stockState;
    private StringProperty stockLimit;
    private StringProperty stockChange;

    public StockProperty(Stock st){
        this.stockCode=new SimpleStringProperty(st.getStockCode());
        this.stockName=new SimpleStringProperty(st.getStockName());
        this.stockPrice=new SimpleDoubleProperty(st.getStockPrice());
        this.ceilingPrice=new SimpleDoubleProperty(st.getCeilingPrice());
        this.floorPrice=new SimpleDoubleProperty(st.getFloorPrice());
        this.stockState=new SimpleStringProperty(st.getStockState());
        this.stockLimit=new SimpleStringProperty(st.getStockLimit());
        this.stockChange=new SimpleStringProperty(st.getStockChange());
        this.setStockChange();
        this.setStockLimit();
        this.setCeilingPrice(st.getCeilingPrice());
        this.setFloorPrice(st.getFloorPrice());
    }
    public StockProperty(String stockCode,String stockName,double stockPrice,double ceilingPrice,double floorPrice,String stockState){
        this.stockCode=new SimpleStringProperty(stockCode);
        this.stockName=new SimpleStringProperty(stockName);
        this.stockPrice=new SimpleDoubleProperty(stockPrice);
        this.ceilingPrice=new SimpleDoubleProperty(ceilingPrice);
        this.floorPrice=new SimpleDoubleProperty(floorPrice);
        this.stockState=new SimpleStringProperty(stockState);
    }

    public StockProperty(){
        this.stockCode=new SimpleStringProperty("");
        this.stockName=new SimpleStringProperty("");
        this.stockPrice=new SimpleDoubleProperty(0);
        this.ceilingPrice=new SimpleDoubleProperty(0);
        this.floorPrice=new SimpleDoubleProperty(0);
        this.stockState=new SimpleStringProperty("");
        this.stockLimit=new SimpleStringProperty("");
        this.stockChange=new SimpleStringProperty("");
    }

    public void setStockCode(String stockCode) { this.stockCode.set(stockCode); }
    public void setStockName(String stockName) { this.stockName.set(stockName); }
    public void setCeilingPrice(double ceilingPrice) {
        this.ceilingPrice.set(Double.parseDouble(String.format("%.3f",ceilingPrice)));
    }
    public void setFloorPrice(double floorPrice) {
        this.floorPrice.set(Double.parseDouble(String.format("%.3f",floorPrice)));
    }
    public void setStockPrice(double stockPrice) { this.stockPrice.set(stockPrice); }
    public void setStockState(String stockState) { this.stockState.set(stockState); }
    public void setStockLimit() {
        double startPrice=(Double.valueOf(ceilingPrice.get())+floorPrice.get())/2;
        double limit=(Double.valueOf(ceilingPrice.get())-startPrice)/startPrice;
        String tempLimit=Double.parseDouble(String.format("%.2f",limit*100))+"%";
        this.stockLimit.set(tempLimit);
    }
    public void setStockChange() {
        double startprice=(Double.valueOf(ceilingPrice.get())+floorPrice.get())/2;
        double change=(stockPrice.get()-startprice)/startprice;
        if (change*100>-0.1 && change*100<0){
            change=0;
        }
        String tempChange=Double.parseDouble(String.format("%.2f",change*100))+"%";
        this.stockChange.set(tempChange);
    }

    public double getStockPrice() { return stockPrice.get(); }
    public String getStockCode() { return stockCode.get(); }
    public String getStockName() { return stockName.get(); }
    public Double getCeilingPrice() { return ceilingPrice.get(); }
    public double getFloorPrice() { return floorPrice.get(); }
    public String getStockState() { return stockState.get(); }
    public String getStockChange() { return stockChange.get(); }
    public String getStockLimit() { return stockLimit.get(); }

    public DoubleProperty ceilingPriceProperty() { return ceilingPrice; }
    public StringProperty stockCodeProperty() { return stockCode; }
    public StringProperty stockNameProperty() { return stockName; }
    public DoubleProperty floorPriceProperty() { return floorPrice; }
    public DoubleProperty stockPriceProperty() { return stockPrice; }
    public StringProperty stockStateProperty() { return stockState; }
    public StringProperty stockLimitProperty() { return stockLimit; }
    public StringProperty stockChangeProperty() { return stockChange; }
}
