package StockTradingSystem.domain.table;

import StockTradingSystem.domain.entity.Index;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class IndexProperty {
    private StringProperty indexCode;
    private StringProperty indexName;
    private DoubleProperty indexPrice;

    public IndexProperty(Index in){
        this.indexCode=new SimpleStringProperty(in.getIndexCode());
        this.indexName=new SimpleStringProperty(in.getIndexName());
        this.indexPrice=new SimpleDoubleProperty(in.getIndexPrice());
    }
    public IndexProperty(){
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
