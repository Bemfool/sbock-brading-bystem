package StockTradingSystem.domain.entity;

import StockTradingSystem.domain.table.StockProperty;

public class Stock {
    private String stockCode;
    private String stockName;
    private Double stockPrice;
    private Double ceilingPrice;
    private Double floorPrice;
    private String stockState;
    private String stockLimit;
    private String stockChange;

    public Stock(StockProperty stockProperty){
        this.stockCode=stockProperty.getStockCode();
        this.stockName=stockProperty.getStockName();
        this.stockPrice=stockProperty.getStockPrice();
        this.ceilingPrice=stockProperty.getCeilingPrice();
        this.floorPrice=stockProperty.getFloorPrice();
        this.stockState=stockProperty.getStockState();
        this.stockLimit=stockProperty.getStockLimit();
        this.stockChange=stockProperty.getStockChange();
    }

    public Stock() {}

    public void setStockCode(String stockCode) { this.stockCode = stockCode; }
    public void setStockName(String stockName) { this.stockName = stockName; }
    public void setCeilingPrice(Double ceilingPrice) { this.ceilingPrice = ceilingPrice; }
    public void setFloorPrice(Double floorPrice) { this.floorPrice = floorPrice; }
    public void setStockPrice(Double stockPrice) { this.stockPrice = stockPrice; }
    public void setStockState(String stockState) { this.stockState = stockState; }
    public void setStockChange(String stockChange) { this.stockChange = stockChange; }
    public void setStockLimit(String stockLimit) { this.stockLimit = stockLimit; }

    public String getStockCode() { return stockCode; }
    public Double getCeilingPrice() { return ceilingPrice; }
    public Double getStockPrice() { return stockPrice; }
    public Double getFloorPrice() { return floorPrice; }
    public String getStockChange() { return stockChange; }
    public String getStockLimit() { return stockLimit; }
    public String getStockName() { return stockName; }
    public String getStockState() { return stockState; }
}
