package StockTradingSystem.domain.entity;

import StockTradingSystem.domain.table.StockProperty;

public class Stock {
    private String stockCode;
    private String stockName;
    private Double stockPrice;
    private String stockState;
    private Double stockLimit;
    private Double closingPrice;
    private Integer stockAmount;
    private Double stockTotal;

    public Stock(StockProperty stockProperty){
        this.stockCode=stockProperty.getStockCode();
        this.stockName=stockProperty.getStockName();
        this.stockPrice=stockProperty.getStockPrice();
        this.stockState=stockProperty.getStockState();
        this.stockLimit=stockProperty.getStockLimit();
        this.closingPrice=stockProperty.getClosingPrice();
        this.stockAmount=stockProperty.getStockAmount();
        this.stockTotal=stockProperty.getStockTotal();
    }

    public Stock() {}

    public void setStockCode(String stockCode) { this.stockCode = stockCode; }
    public void setStockName(String stockName) { this.stockName = stockName; }
    public void setStockPrice(Double stockPrice) { this.stockPrice = stockPrice; }
    public void setStockState(String stockState) { this.stockState = stockState; }
    public void setStockLimit(Double stockLimit) { this.stockLimit = stockLimit; }
    public void setClosingPrice(Double closingPrice) { this.closingPrice = closingPrice; }
    public void setStockAmount(Integer stockAmount) { this.stockAmount = stockAmount; }
    public void setStockTotal(Double stockTotal) { this.stockTotal = stockTotal; }

    public String getStockCode() { return stockCode; }
    public Double getStockPrice() { return stockPrice; }
    public Double getStockLimit() { return stockLimit; }
    public String getStockName() { return stockName; }
    public String getStockState() { return stockState; }
    public Double getClosingPrice() { return closingPrice; }
    public Integer getStockAmount() { return stockAmount; }
    public Double getStockTotal() { return stockTotal; }
}