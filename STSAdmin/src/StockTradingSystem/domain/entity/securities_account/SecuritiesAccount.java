package StockTradingSystem.domain.entity.securities_account;

public interface SecuritiesAccount {
    public boolean reportLoss();
    public boolean reportReissue();
    public boolean accountCancellation();
    public boolean register();
}
