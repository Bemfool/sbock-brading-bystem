package StockTradingSystem.data.SecuritiesAccount;

public class CorporateAccount implements SecuritiesAccount {
    // TODO 成员变量名字规范 e.g. businessLicenseNo
    private int securities_id;
    private String register_no;
    private String business_license_no;
    private String legal_representative_id;
    private String legal_representative_name;
    private String legal_representative_phone_no;
    private String legal_representative_add;
    private String authorizer_name;
    private String authorizer_id;
    private String authorizer_phone_no;
    private String authorizer_add;
    private int state;

    public CorporateAccount(){}

    public CorporateAccount(String register_no, String business_license_no, String legal_representative_id, String legal_representative_name, String legal_representative_phone_no, String legal_representative_add, String authorizer_name, String authorizer_id, String authorizer_phone_no, String authorizer_add) {
        this.register_no = register_no;
        this.business_license_no = business_license_no;
        this.legal_representative_id = legal_representative_id;
        this.legal_representative_name = legal_representative_name;
        this.legal_representative_phone_no = legal_representative_phone_no;
        this.legal_representative_add = legal_representative_add;
        this.authorizer_name = authorizer_name;
        this.authorizer_id = authorizer_id;
        this.authorizer_phone_no = authorizer_phone_no;
        this.authorizer_add = authorizer_add;
    }

    public int getSecurities_id() {
        return securities_id;
    }

    public void setSecurities_id(int securities_id) {
        this.securities_id = securities_id;
    }

    public String getRegister_no() {
        return register_no;
    }

    public void setRegister_no(String register_no) {
        this.register_no = register_no;
    }

    public String getBusiness_license_no() {
        return business_license_no;
    }

    public void setBusiness_license_no(String business_license_no) {
        this.business_license_no = business_license_no;
    }

    public String getLegal_representative_id() {
        return legal_representative_id;
    }

    public void setLegal_representative_id(String legal_representative_id) {
        this.legal_representative_id = legal_representative_id;
    }

    public String getLegal_representative_name() {
        return legal_representative_name;
    }

    public void setLegal_representative_name(String legal_representative_name) {
        this.legal_representative_name = legal_representative_name;
    }

    public String getLegal_representative_phone_no() {
        return legal_representative_phone_no;
    }

    public void setLegal_representative_phone_no(String legal_representative_phone_no) {
        this.legal_representative_phone_no = legal_representative_phone_no;
    }

    public String getLegal_representative_add() {
        return legal_representative_add;
    }

    public void setLegal_representative_add(String legal_representative_add) {
        this.legal_representative_add = legal_representative_add;
    }

    public String getAuthorizer_name() {
        return authorizer_name;
    }

    public void setAuthorizer_name(String authorizer_name) {
        this.authorizer_name = authorizer_name;
    }

    public String getAuthorizer_id() {
        return authorizer_id;
    }

    public void setAuthorizer_id(String authorizer_id) {
        this.authorizer_id = authorizer_id;
    }

    public String getAuthorizer_phone_no() {
        return authorizer_phone_no;
    }

    public void setAuthorizer_phone_no(String authorizer_phone_no) {
        this.authorizer_phone_no = authorizer_phone_no;
    }

    public String getAuthorizer_add() {
        return authorizer_add;
    }

    public void setAuthorizer_add(String authorizer_add) {
        this.authorizer_add = authorizer_add;
    }

    public int getState() { return state; }

    public void setState(int state) { this.state = state; }

    public boolean reportLoss(){
        return true;
    };

    public boolean reportReissue(){
        return true;
    };

    public boolean accountCancellation(){
        return true;
    };

    public boolean register(){
        return true;
    };
}
