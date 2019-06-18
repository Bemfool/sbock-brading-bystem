package StockTradingSystem.database;

import java.lang.reflect.Type;
import java.util.*;

import StockTradingSystem.domain.entity.Stock;
import StockTradingSystem.domain.entity.securities_account.CorporateAccount;
import StockTradingSystem.domain.entity.securities_account.PersonalAccount;
import StockTradingSystem.http_utils.CustomResp;
import StockTradingSystem.http_utils.HttpCommon;
import StockTradingSystem.http_utils.Result;
import com.google.gson.Gson;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class SecuritiesAccountDBManager {
    /**
     * 注册个人账户
     * @param account 个人账户注册信息
     * @return 操作是否成功
     */
    private String msg="";
    public String getMsg(){
        return msg;
    }
    public boolean newPersonalAccount(PersonalAccount account) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        String json = gson.toJson(account);
        CustomResp cr = new HttpCommon().doHttp("/securities/new/personal", "POST", json);
        Result res = new Gson().fromJson(cr.getResultJSON(), Result.class);
        if(!res.isStatus()) msg =res.getReasons().substring(0,res.getReasons().length());
        return res.isStatus();
    }

    /**
     * 注册法人账户
     * @param account 法人账户注册信息
     * @return 操作是否成功
     */
    public boolean newCorporateAccount(CorporateAccount account) {
        String json = new Gson().toJson(account);
        CustomResp cr = new HttpCommon().doHttp("/securities/new/corporate", "POST", json);
        Result res = new Gson().fromJson(cr.getResultJSON(), Result.class);
        if(!res.isStatus()) msg =res.getReasons().substring(0,res.getReasons().length());
        return res.isStatus();
    }

    /**
     * 用身份证号来获取个人账户信息
     * @param id_no 身份证号
     * @param account 返回的个人账户
     * @return 操作是否成功，即是否存在该账户
     */
    public boolean getPersonalAccount(String id_no, PersonalAccount account) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        CustomResp cr = new HttpCommon().doHttp("/securities/personal/" + id_no, "GET", null);
        Result res = new Gson().fromJson(cr.getResultJSON(), Result.class);
        if(res.getReasons()==null) System.out.println("hjh");
        if (res.isStatus()){
            account.copy(gson.fromJson(cr.getObjectJSON(), PersonalAccount.class));}
        if(!res.isStatus()){  msg =res.getReasons().substring(0,res.getReasons().length());}
        return res.isStatus();
    }

    /**
     * 用注册号码来获取法人账户信息
     * @param register_no 注册号码
     * @param account 返回的法人账户
     * @return 操作是否成功，即是否存在该账户
     */
    public boolean getCorporateAccount(String register_no, CorporateAccount account) {
        CustomResp cr = new HttpCommon().doHttp("/securities/corporate/" + register_no, "GET", null);
        Result res = new Gson().fromJson(cr.getResultJSON(), Result.class);
        if (res.isStatus()) {
            account.copy(new Gson().fromJson(cr.getObjectJSON(), CorporateAccount.class));
        } else {
            msg = res.getReasons().substring(0, res.getReasons().length());
        }
        return res.isStatus();
    }

    /**
     * 获取关联的资金账户ID
     * @param securities_id 证券账户ID
     * @return 返回关联的资金账号ID
     */
    public String getSecuritiesFund(int securities_id) {
        CustomResp cr = new HttpCommon().doHttp("/securities/fund_connected/" + securities_id, "GET", null);
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        Result res = new Gson().fromJson(cr.getResultJSON(), Result.class);
        if (!res.isStatus())
            msg =res.getReasons().substring(0,res.getReasons().length());

        return new Gson().fromJson(cr.getObjectJSON(), listType);
    }

    /**
     * 检查是否有剩余的证券
     * @param securities_id 证券账户ID
     * @return 返回该证券账户是否有剩余的证券
     */
    public boolean getSecuritiesStock(int securities_id) {
        CustomResp cr = new HttpCommon().doHttp("/securities/stock_connected/" + securities_id, "GET", null);
        Type listType = new TypeToken<ArrayList<Stock>>() {}.getType();
        List<Stock> connectedStock = new Gson().fromJson(cr.getObjectJSON(), listType);
        Result res = new Gson().fromJson(cr.getResultJSON(), Result.class);
        if (!res.isStatus()) {
            msg =res.getReasons().substring(0,res.getReasons().length());
            return false;
        }
        return !connectedStock.isEmpty();
    }

    /**
     * 修改个人账户状态
     * @param id_no 身份证号
     * @param state
     * @return 操作是否成功
     */
    public boolean modifyPersonalState(String id_no, int state) {
        CustomResp cr = new HttpCommon().doHttp("/securities/update/personal/state/" + id_no + "/" + state, "POST", null);
        Result res = new Gson().fromJson(cr.getResultJSON(), Result.class);
        if (!res.isStatus())
            msg =res.getReasons().substring(0,res.getReasons().length());
        return res.isStatus();
    }

    /**
     * 修改法人账户状态
     * @param register_no 注册号码
     * @param state
     * @return 操作是否成功
     */
    public boolean modifyCorporateState(String register_no, int state) {
        CustomResp cr = new HttpCommon().doHttp("/securities/update/corporate/state/" + register_no + "/" + state, "POST", null);
        Result res = new Gson().fromJson(cr.getResultJSON(), Result.class);
        if (!res.isStatus())
            msg =res.getReasons().substring(0,res.getReasons().length());
        return res.isStatus();
    }

    /**
     * 更新证券账户与资金账户的关联信息，使用newID代替所有的oldID
     * @param oldID
     * @param newID
     * @return 操作是否成功
     */
    public boolean modifySecuritiesFunds(int oldID, int newID) {
        CustomResp cr = new HttpCommon().doHttp("/securities/alter/personal/" + oldID + "/" + newID, "POST", null);
        Result res = new Gson().fromJson(cr.getResultJSON(), Result.class);
        if (!res.isStatus())
            msg =res.getReasons().substring(0,res.getReasons().length());
        return res.isStatus();
    }

    /**
     * 删除个人账户
     * @param id_no 身份证号
     * @return 操作是否成功
     */
    public boolean deletePersonalAccount(String id_no) {
        CustomResp cr = new HttpCommon().doHttp("/securities/delete/personal/" + id_no, "POST", null);
        Result res = new Gson().fromJson(cr.getResultJSON(), Result.class);
        if (!res.isStatus())
            msg =res.getReasons().substring(0,res.getReasons().length());
        return res.isStatus();
    }

    /**
     * 删除法人账户
     * @param register_no 注册号码
     * @return 操作是否成功
     */
    public boolean deleteCorporateAccount(String register_no) {
        CustomResp cr = new HttpCommon().doHttp("/securities/delete/corporate/" + register_no, "POST", null);
        Result res = new Gson().fromJson(cr.getResultJSON(), Result.class);
        if (!res.isStatus())
            msg =res.getReasons().substring(0,res.getReasons().length());
        return res.isStatus();
    }
}