package StockTradingSystem.domain.entity;

public class AdminAccount {
    private String id;
    private String password;
    private String name;
    private int priv;

    public AdminAccount(String text) {
    }

    public AdminAccount(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public AdminAccount(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getPriv() {
        return priv;
    }

    public void setPriv(int priv) {
        this.priv = priv;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }
}
