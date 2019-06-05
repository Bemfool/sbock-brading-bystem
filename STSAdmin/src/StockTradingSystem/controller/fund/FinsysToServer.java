package StockTradingSystem.controller.fund;

import StockTradingSystem.domain.entity.FundAccount;
import StockTradingSystem.domain.entity.TransactionLog;
import StockTradingSystem.http_utils.CustomResp;
import StockTradingSystem.http_utils.HttpCommon;
import com.google.gson.Gson;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.sql.Date;


public class FinsysToServer {

	private static FundAccount customer = new FundAccount();
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

	static boolean FinsysLogin(Long customerID, String pwd) throws IOException{
		FundAccount user=new FundAccount();
		user.setFundId(customerID.intValue());
		user.setPassword(pwd);
		
		String json=new Gson().toJson(user);
		
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
		String json=new Gson().toJson(newaccount);
		System.out.println("JSON: " + json);
		CustomResp cr = new HttpCommon().doHttp("/fund/new", "POST", json);
		String res=cr.getResultJSON();
		String resStatus = res.substring(res.lastIndexOf("\"status\":")+9, res.indexOf(','));
	    System.out.println(res);
	    System.out.println(cr.getObjectJSON());
	    if(resStatus.equals("true")) {
			int id = new Gson().fromJson(cr.getObjectJSON(), int.class);
			System.out.println("新ID: " + id);
	    	customer.setFundId(id);
	    	customer.setPassword(password);
	    	return (long)id;
	    }	
	    else
	    	return (long)-1;
	}
	
	static String SearchLog() {
		
		
		String json=new Gson().toJson(customer);
		CustomResp cr = new HttpCommon().doHttp("/fund/"+customer.getFundId(), "GET", json);
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
	
	
	static double Reposit_Withdraw(double amount) {
		if(customer.getFundId()==-1) {
			return -1;
		}
		TransactionLog log=new TransactionLog();
		log.setFundId(customer.getFundId());
		log.setChangeAmount(amount);
		log.setComment("transaction");
		log.setActionTime(new Date(System.currentTimeMillis()));
		String json=new Gson().toJson(log);
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
		CustomResp cr = new HttpCommon().doHttp("/fund/update/balance", "POST");
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
		
		String json=new Gson().toJson(customer);
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
		String json=new Gson().toJson(customer);
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
	
	
	
	
}
