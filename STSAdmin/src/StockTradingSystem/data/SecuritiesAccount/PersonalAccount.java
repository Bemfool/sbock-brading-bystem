package StockTradingSystem.data.SecuritiesAccount;

import java.sql.Date;

public class PersonalAccount implements SecuritiesAccount {
    // TODO 成员变量名字规范 e.g. businessLicenseNo
    private int securities_id;
    private Date register_date;
    private String name;
    private Boolean gender;
    private String id_no;
    private String family_add;
    private String career;
    private String education;
    private String organization;
    private String phone_no;
    private String agent_id_no=null;
    private int state;

    public PersonalAccount(){}

    public PersonalAccount(Date register_date, String name, Boolean gender, String id_no, String family_add, String career, String education, String organization, String phone_no) {
        this.register_date = register_date;
        this.name = name;
        this.gender = gender;
        this.id_no = id_no;
        this.family_add = family_add;
        this.career = career;
        this.education = education;
        this.organization = organization;
        this.phone_no = phone_no;

    }

    public PersonalAccount( Date register_date, String name, Boolean gender, String id_no, String family_add, String career, String education, String organization, String phone_no, String agent_id_no) {
        this.register_date = register_date;
        this.name = name;
        this.gender = gender;
        this.id_no = id_no;
        this.family_add = family_add;
        this.career = career;
        this.education = education;
        this.organization = organization;
        this.phone_no = phone_no;
        this.agent_id_no = agent_id_no;
    }

    public int getSecurities_id() {
        return securities_id;
    }

    public void setSecurities_id(int securities_id) {
        this.securities_id = securities_id;
    }

    public Date getRegister_date() {
        return register_date;
    }

    public void setRegister_date(Date register_date) {
        this.register_date = register_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getId_no() {
        return id_no;
    }

    public void setId_no(String id_no) {
        this.id_no = id_no;
    }

    public String getFamily_add() {
        return family_add;
    }

    public void setFamily_add(String family_add) {
        this.family_add = family_add;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getAgent_id_no() {
        return agent_id_no;
    }

    public void setAgent_id_no(String agent_id_no) {
        this.agent_id_no = agent_id_no;
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
