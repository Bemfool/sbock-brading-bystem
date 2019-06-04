package StockTradingSystem.domain.entity.securities_account;

import java.sql.Date;

public class PersonalAccount {
    private int securitiesId;
    private Date registerDate;
    private String name;
    private Boolean gender;
    private String idNo;
    private String familyAdd;
    private String career;
    private String education;
    private String organization;
    private String phoneNo;
    private String agentIdNo=null;
    private int state;

    public PersonalAccount(){}


    public PersonalAccount(Date register_date, String name, Boolean gender, String id_no, String family_add, String career, String education, String organization, String phone_no) {
        this.registerDate = register_date;
        this.name = name;
        this.gender = gender;
        this.idNo = id_no;
        this.familyAdd = family_add;
        this.career = career;
        this.education = education;
        this.organization = organization;
        this.phoneNo = phone_no;

    }

    public PersonalAccount(Date register_date, String name, Boolean gender, String id_no, String family_add, String career, String education, String organization, String phone_no, String agent_id_no) {
        this.registerDate = register_date;
        this.name = name;
        this.gender = gender;
        this.idNo = id_no;
        this.familyAdd = family_add;
        this.career = career;
        this.education = education;
        this.organization = organization;
        this.phoneNo = phone_no;
        this.agentIdNo = agent_id_no;
    }


    public String getName() {
        return name;
    }

    public Boolean getGender() {
        return gender;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public int getSecuritiesId() {
        return securitiesId;
    }

    public String getCareer() {
        return career;
    }

    public int getState() {
        return state;
    }

    public String getAgentIdNo() {
        return agentIdNo;
    }

    public String getEducation() {
        return education;
    }

    public String getFamilyAdd() {
        return familyAdd;
    }

    public String getIdNo() {
        return idNo;
    }

    public String getOrganization() {
        return organization;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFamilyAdd(String familyAdd) {
        this.familyAdd = familyAdd;
    }

    public void setSecuritiesId(int securitiesId) {
        this.securitiesId = securitiesId;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public void setAgentIdNo(String agentIdNo) {
        this.agentIdNo = agentIdNo;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void copy(PersonalAccount account) {
        this.securitiesId = account.securitiesId;
        this.registerDate = account.registerDate;
        this.name = account.name;
        this.gender = account.gender;
        this.idNo = account.idNo;
        this.familyAdd = account.familyAdd;
        this.career = account.career;
        this.education = account.education;
        this.organization = account.organization;
        this.phoneNo = account.phoneNo;
        this.agentIdNo = account.agentIdNo;
        this.state = account.state;
    }
}
