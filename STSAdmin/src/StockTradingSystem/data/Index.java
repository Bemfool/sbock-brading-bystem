package StockTradingSystem.data;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Index {
    private StringProperty indexCode;
    private StringProperty indexName;
    private DoubleProperty indexPrice;

    public Index(){
        this.indexCode=new SimpleStringProperty("");
        this.indexName=new SimpleStringProperty("");
        this.indexPrice=new SimpleDoubleProperty(0);
    }

    public StringProperty indexCodeProperty() { return indexCode; }
    public DoubleProperty indexPriceProperty() { return indexPrice; }
    public StringProperty indexNameProperty() { return indexName; }

    public String getIndexCode() { return indexCode.get(); }
    public double getIndexPrice() { return indexPrice.get(); }
    public String getIndexName() { return indexName.get(); }

    public void setIndexName(String indexName) { this.indexName.set(indexName); }
    public void setIndexPrice(double indexPrice) { this.indexPrice.set(indexPrice); }
    public void setIndexCode(String indexCode) { this.indexCode.set(indexCode); }
}
