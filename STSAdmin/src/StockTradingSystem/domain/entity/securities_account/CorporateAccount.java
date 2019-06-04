package StockTradingSystem.domain.entity.securities_account;

public class CorporateAccount {
    private int securitiesId;
    private String registerNo;
    private String businessLicenseNo;
    private String legalRepresentativeId;
    private String legalRepresentativeName;
    private String legalRepresentativePhoneNo;
    private String legalRepresentativeAdd;
    private String authorizerName;
    private String authorizerId;
    private String authorizerPhoneNo;
    private String authorizerAdd;
    private int state;

    public CorporateAccount(){}

    public CorporateAccount(String register_no, String business_license_no, String legal_representative_id, String legal_representative_name, String legal_representative_phone_no, String legal_representative_add, String authorizer_name, String authorizer_id, String authorizer_phone_no, String authorizer_add) {
        this.registerNo = register_no;
        this.businessLicenseNo = business_license_no;
        this.legalRepresentativeId = legal_representative_id;
        this.legalRepresentativeName = legal_representative_name;
        this.legalRepresentativePhoneNo = legal_representative_phone_no;
        this.legalRepresentativeAdd = legal_representative_add;
        this.authorizerName = authorizer_name;
        this.authorizerId = authorizer_id;
        this.authorizerPhoneNo = authorizer_phone_no;
        this.authorizerAdd = authorizer_add;
    }

    public CorporateAccount(String register_no, String business_license_no, String legal_representative_id, String legal_representative_name, String legal_representative_phone_no, String legal_representative_add, String authorizer_name, String authorizer_id, String authorizer_phone_no, String authorizer_add, int state) {
        this.registerNo = register_no;
        this.businessLicenseNo = business_license_no;
        this.legalRepresentativeId = legal_representative_id;
        this.legalRepresentativeName = legal_representative_name;
        this.legalRepresentativePhoneNo = legal_representative_phone_no;
        this.legalRepresentativeAdd = legal_representative_add;
        this.authorizerName = authorizer_name;
        this.authorizerId = authorizer_id;
        this.authorizerPhoneNo = authorizer_phone_no;
        this.authorizerAdd = authorizer_add;
        this.state = state;
    }

    public int getSecuritiesId() {
        return securitiesId;
    }

    public String getAuthorizerId() {
        return authorizerId;
    }

    public int getState() {
        return state;
    }

    public String getBusinessLicenseNo() {
        return businessLicenseNo;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public String getAuthorizerAdd() {
        return authorizerAdd;
    }

    public String getAuthorizerName() {
        return authorizerName;
    }

    public String getAuthorizerPhoneNo() {
        return authorizerPhoneNo;
    }

    public String getLegalRepresentativeId() {
        return legalRepresentativeId;
    }

    public String getLegalRepresentativeAdd() {
        return legalRepresentativeAdd;
    }

    public String getLegalRepresentativeName() {
        return legalRepresentativeName;
    }

    public String getLegalRepresentativePhoneNo() {
        return legalRepresentativePhoneNo;
    }

    public void setSecuritiesId(int securitiesId) {
        this.securitiesId = securitiesId;
    }

    public void setBusinessLicenseNo(String businessLicenseNo) {
        this.businessLicenseNo = businessLicenseNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public void setAuthorizerAdd(String authorizerAdd) {
        this.authorizerAdd = authorizerAdd;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setAuthorizerId(String authorizerId) {
        this.authorizerId = authorizerId;
    }

    public void setAuthorizerName(String authorizerName) {
        this.authorizerName = authorizerName;
    }

    public void setAuthorizerPhoneNo(String authorizerPhoneNo) {
        this.authorizerPhoneNo = authorizerPhoneNo;
    }

    public void setLegalRepresentativeAdd(String legalRepresentativeAdd) {
        this.legalRepresentativeAdd = legalRepresentativeAdd;
    }

    public void setLegalRepresentativeId(String legalRepresentativeId) {
        this.legalRepresentativeId = legalRepresentativeId;
    }

    public void setLegalRepresentativeName(String legalRepresentativeName) {
        this.legalRepresentativeName = legalRepresentativeName;
    }

    public void setLegalRepresentativePhoneNo(String legalRepresentativePhoneNo) {
        this.legalRepresentativePhoneNo = legalRepresentativePhoneNo;
    }

    public void copy(CorporateAccount account) {
        this.securitiesId = account.securitiesId;
        this.registerNo = account.registerNo;
        this.businessLicenseNo = account.businessLicenseNo;
        this.legalRepresentativeId = account.legalRepresentativeId;
        this.legalRepresentativeName = account.legalRepresentativeName;
        this.legalRepresentativePhoneNo = account.legalRepresentativePhoneNo;
        this.legalRepresentativeAdd = account.legalRepresentativeAdd;
        this.authorizerName = account.authorizerName;
        this.authorizerId = account.authorizerId;
        this.authorizerPhoneNo = account.authorizerPhoneNo;
        this.authorizerAdd = account.authorizerAdd;
        this.state = account.state;
    }
}
