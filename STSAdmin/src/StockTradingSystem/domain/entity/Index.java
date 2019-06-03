package StockTradingSystem.domain.entity;

public class Index {
    private String indexCode;
    private String indexName;
    private Double indexPrice;

    public Index(){}

    public void setIndexPrice(Double indexPrice) { this.indexPrice = indexPrice; }
    public void setIndexName(String indexName) { this.indexName = indexName; }
    public void setIndexCode(String indexCode) { this.indexCode = indexCode; }

    public Double getIndexPrice() { return indexPrice; }
    public String getIndexCode() { return indexCode; }
    public String getIndexName() { return indexName; }
}
