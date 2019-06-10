package StockTradingSystem.domain.table;

import StockTradingSystem.domain.entity.Stock;
import javafx.beans.property.*;

public class StockProperty {
    private StringProperty stockCode;
    private StringProperty stockName;
    private DoubleProperty stockPrice;
    private DoubleProperty ceilingPrice;
    private DoubleProperty floorPrice;
    private StringProperty stockState;
    private DoubleProperty stockLimit;
    private StringProperty stockChange;
    private DoubleProperty closingPrice;
    private IntegerProperty stockAmount;
    private DoubleProperty stockTotal;

    public StockProperty(Stock st){
        this.stockCode=new SimpleStringProperty(st.getStockCode());
        this.stockName=new SimpleStringProperty(st.getStockName());
        this.stockPrice=new SimpleDoubleProperty(st.getStockPrice());
        this.ceilingPrice=new SimpleDoubleProperty();
        this.floorPrice=new SimpleDoubleProperty();
        this.stockState=new SimpleStringProperty(st.getStockState());
        this.stockLimit=new SimpleDoubleProperty(st.getStockLimit());
        this.stockChange=new SimpleStringProperty();
        this.closingPrice=new SimpleDoubleProperty(st.getClosingPrice());
        this.stockAmount=new SimpleIntegerProperty(st.getStockAmount());
        this.stockTotal=new SimpleDoubleProperty(st.getStockTotal());
        this.setStockChange();
        this.setCeilingPrice();
        this.setFloorPrice();
    }
    public StockProperty(String stockCode,String stockName,double stockPrice,double ceilingPrice,double floorPrice,String stockState,Integer stockAmount,double stockTotal){
        this.stockCode=new SimpleStringProperty(stockCode);
        this.stockName=new SimpleStringProperty(stockName);
        this.stockPrice=new SimpleDoubleProperty(stockPrice);
        this.ceilingPrice=new SimpleDoubleProperty(ceilingPrice);
        this.floorPrice=new SimpleDoubleProperty(floorPrice);
        this.stockState=new SimpleStringProperty(stockState);
        this.stockAmount=new SimpleIntegerProperty(stockAmount);
        this.stockTotal=new SimpleDoubleProperty(stockTotal);
    }

    public StockProperty(){
        this.stockCode=new SimpleStringProperty("");
        this.stockName=new SimpleStringProperty("");
        this.stockPrice=new SimpleDoubleProperty(0);
        this.ceilingPrice=new SimpleDoubleProperty(0);
        this.floorPrice=new SimpleDoubleProperty(0);
        this.stockState=new SimpleStringProperty("");
        this.stockLimit=new SimpleDoubleProperty(0);
        this.stockChange=new SimpleStringProperty("");
        this.stockAmount=new SimpleIntegerProperty(0);
        this.stockTotal=new SimpleDoubleProperty(0);
    }

    public void setStockCode(String stockCode) { this.stockCode.set(stockCode); }
    public void setStockName(String stockName) { this.stockName.set(stockName); }
    public void setCeilingPrice() {
        if (getStockLimit()<0){
            this.ceilingPrice.set(-1);
        }else{
            double tempPrice=getClosingPrice()*(1+getStockLimit());
            this.ceilingPrice.set(Double.parseDouble(String.format("%.3f",tempPrice)));
        }
    }
    public void setFloorPrice() {
        if (getStockLimit()<0){
            this.floorPrice.set(0);
        }else{
            double tempPrice=getClosingPrice()*(1-getStockLimit());
            this.floorPrice.set(Double.parseDouble(String.format("%.3f",tempPrice)));
        }
    }
    public void setStockPrice(double stockPrice) { this.stockPrice.set(stockPrice); }
    public void setStockState(String stockState) { this.stockState.set(stockState); }
    public void setStockLimit(double stockLimit) { this.stockLimit.set(stockLimit); }
    public void setStockChange() {
        double startprice=getClosingPrice();
        double change=(stockPrice.get()-startprice)/startprice;
        if (change*100>-0.1 && change*100<0){
            change=0;
        }
        String tempChange=Double.parseDouble(String.format("%.2f",change*100))+"%";
        this.stockChange.set(tempChange);
    }
    public void setClosingPrice(double closingPrice) { this.closingPrice.set(closingPrice); }
    public void setStockAmount(Integer stockAmount) { this.stockAmount.set(stockAmount); }
    public void setStockTotal(double stockTotal) { this.stockTotal.set(stockTotal); }

    public double getStockPrice() { return stockPrice.get(); }
    public String getStockCode() { return stockCode.get(); }
    public String getStockName() { return stockName.get(); }
    public Double getCeilingPrice() { return ceilingPrice.get(); }
    public double getFloorPrice() { return floorPrice.get(); }
    public String getStockState() { return stockState.get(); }
    public String getStockChange() { return stockChange.get(); }
    public double getStockLimit() { return stockLimit.get(); }
    public double getClosingPrice() { return closingPrice.get(); }
    public Integer getStockAmount() { return stockAmount.get(); }
    public double getStockTotal() { return stockTotal.get(); }

    public DoubleProperty ceilingPriceProperty() { return ceilingPrice; }
    public StringProperty stockCodeProperty() { return stockCode; }
    public StringProperty stockNameProperty() { return stockName; }
    public DoubleProperty floorPriceProperty() { return floorPrice; }
    public DoubleProperty stockPriceProperty() { return stockPrice; }
    public StringProperty stockStateProperty() { return stockState; }
    public DoubleProperty stockLimitProperty() { return stockLimit; }
    public StringProperty stockChangeProperty() { return stockChange; }
    public DoubleProperty closingPriceProperty() { return closingPrice; }
    public IntegerProperty stockAmountProperty() { return stockAmount; }
    public DoubleProperty stockTotalProperty() { return stockTotal; }
}
