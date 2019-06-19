package StockTradingSystem.controller.fund;

import StockTradingSystem.domain.entity.FundAccount;
import StockTradingSystem.domain.entity.TransactionLog;
import StockTradingSystem.http_utils.CustomResp;
import StockTradingSystem.http_utils.HttpCommon;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class FinsysToServer {

	private static FundAccount customer = new FundAccount();
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

	private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

	static boolean FinsysLogin(Long customerID, String pwd) throws IOException{
		FundAccount user=new FundAccount();
		user.setFundId(customerID.intValue());
		user.setPassword(pwd);
		
		String json=gson.toJson(user);
		
		System.out.println(json);

	    CustomResp cr = new HttpCommon().doHttp("/client/login", "POST", json);
	    String res=cr.getResultJSON();
	    String resStatus = res.substring(res.lastIndexOf("\"status\":")+9, res.indexOf(','));
	    System.out.println(res);
	    System.out.println(cr.getObjectJSON());
	    if(resStatus.equals("true")) {
	    	customer=user;
	    	customer.setPassword("");
	    	return true;
	    }	
	    else
	    	return false;
	}
	
	static long CreateAccount(String stockID, String password, double money) {
		FundAccount newaccount=new FundAccount(-1,Integer.valueOf(stockID),password,money,0,true);
		String json=gson.toJson(newaccount);
		System.out.println("JSON: " + json);
		CustomResp cr = new HttpCommon().doHttp("/fund/new", "POST", json);
		String res=cr.getResultJSON();
		String resStatus = res.substring(res.lastIndexOf("\"status\":")+9, res.indexOf(','));
	    System.out.println(res);
	    System.out.println(cr.getObjectJSON());
	    if(resStatus.equals("true")) {
			int id = gson.fromJson(cr.getObjectJSON(), int.class);
			System.out.println("新ID: " + id);
	    	customer.setFundId(id);
	    	customer.setPassword(password);
	    	return (long)id;
	    }	
	    else
	    	return (long)-1;
	}

	static double Reposit_Withdraw(double amount) {
		if(customer.getFundId()==-1) {
			return -1;
		}

		TransactionLog log=new TransactionLog();
		log.setFundId(customer.getFundId());
		log.setChangeAmount(amount);
		log.setComment("transaction");
		log.setActionTime(new Date(System.currentTimeMillis()));
		String json=gson.toJson(log);
		CustomResp cr = new HttpCommon().doHttp("/fund/transfer/", "POST", json);
		String res=cr.getResultJSON();
		String resStatus = res.substring(res.lastIndexOf("\"status\":")+9, res.indexOf(','));
	    System.out.println(res);
	    System.out.println(cr.getObjectJSON());
	    
	    if(resStatus.equals("false"))
	    	return -1;
	    return 1;
	}
	
	public static boolean calcInterests() {
		CustomResp cr = new HttpCommon().doHttp("/fund/settle_interest", "POST");
		String res=cr.getResultJSON();
		String resStatus = res.substring(res.lastIndexOf("\"status\":")+9, res.indexOf(','));
	    System.out.println(res);
	    System.out.println(cr.getObjectJSON());
	    if(resStatus.equals("true"))
	    	return true;
	    else 
	    	return false;
	}
	
	public static boolean changePassword(String newpwd) {
		if(customer.getFundId()==-1) {
			return false;
		}
		
		CustomResp cr = new HttpCommon().doHttp("/fund/update/password/"+customer.getFundId()+"/"+newpwd, "POST", null);
		String res=cr.getResultJSON();
		String resStatus = res.substring(res.lastIndexOf("\"status\":")+9, res.indexOf(','));
	    System.out.println(res);
	    System.out.println(cr.getObjectJSON());
	    if(resStatus.equals("true")) {
	    	return true;
	    }
	    else 
	    	return false;   
	}

	public static String ChangeState() {
		if(customer.getFundId()==-1) {
			return "ERROR";
		}
		
		String json=gson.toJson(customer);
		CustomResp cr = new HttpCommon().doHttp("/fund/change/state/"+customer.getFundId(), "POST", json);
		String res=cr.getResultJSON();
		String resStatus = res.substring(res.lastIndexOf("\"status\":")+9, res.indexOf(','));
	    System.out.println(res);
	    System.out.println(cr.getObjectJSON());
	    if(resStatus.equals("true")) {
	    	return cr.getObjectJSON();
	    }
	    else 
	    	return "ERROR";   
	}

	public static boolean DeletAccount() {
		if(customer.getFundId()==-1) {
			return false;
		}
		String json=gson.toJson(customer);
		CustomResp cr = new HttpCommon().doHttp("/fund/delete/"+customer.getFundId(), "POST", json);
		String res=cr.getResultJSON();
		String resStatus = res.substring(res.lastIndexOf("\"status\":")+9, res.indexOf(','));
	    System.out.println(res);
	    System.out.println(cr.getObjectJSON());
	    
	    if(resStatus.equals("true")) {
	    	System.out.println("Successfully delete Account "+Integer.toString(customer.getFundId()));
	    	customer.setFundId(-1);
	    	return true;
	    }
	    else {
	    	System.out.println("fail to delete account");
	    	return false;
	    }
	    
	}
	
	public static String CustomerInfo() {
		if(customer.getFundId()==-1) {
			return "ERROR";
		}
		CustomResp cr = new HttpCommon().doHttp("/fund/"+customer.getFundId(), "GET", null);
		String res=cr.getResultJSON();
		if(res.indexOf("true")>=0) {
			return cr.getObjectJSON();
		}
		else {
			return "CANNOT GET CUSTOMER INFO";
		}
	}

	public static FundAccount GetUserInfo() {

		String json=gson.toJson(customer);
		CustomResp cr = new HttpCommon().doHttp("/fund/"+customer.getFundId(), "GET", json);
		String res=cr.getResultJSON();

		String resStatus = res.substring(res.lastIndexOf("\"status\":")+9, res.indexOf(','));
		System.out.println(res);
		String info=cr.getObjectJSON();
		System.out.println(info);
		FundAccount userinfo= new Gson().fromJson(info ,FundAccount.class);
		if(resStatus.equals("true")) {
			return userinfo;
		}
		else
			return null;
	}

	public static List<TransactionLog> GetTranscationLog(){
		//获取交易记录的url：/fund/transaction_log/{fundId} 方法：get
		CustomResp cr = new HttpCommon().doHttp("/fund/transaction_log/"+customer.getFundId(), "GET",null);
		String res=cr.getResultJSON();
		String resStatus = res.substring(res.lastIndexOf("\"status\":")+9, res.indexOf(','));
		System.out.println(res+"  "+resStatus);

		if(resStatus.equals("true")){
			String logstring=cr.getObjectJSON();
			System.out.println(logstring);
			List<TransactionLog> logs= gson.fromJson(logstring, new TypeToken<List<TransactionLog>>() {}.getType());
			System.out.println("ActionID  FundID  changeAmount  Actiontime  Comment");
			for(TransactionLog i:logs){
				System.out.println(i.getActionId()+"  "+i.getFundId()+"  "+i.getChangeAmount()+"  "+i.getActionTime()+"  "+i.getComment());
			}
			return logs;
		}
		else {
			System.out.println("ERROR: Invalid Server Response");
			return null;
		}

	}

	public static void setCustomer(int accountnumber){
		customer=new FundAccount();
		customer.setFundId(accountnumber);
	}

	public static boolean getState(){
		FundAccount user=GetUserInfo();
		return user.isState();
	}

}
