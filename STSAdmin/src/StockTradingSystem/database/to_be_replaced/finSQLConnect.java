package StockTradingSystem.database.to_be_replaced;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class finSQLConnect {	
	
	public static String driver = "com.mysql.jdbc.Driver"; // driver name
	public static String url = "jdbc:mysql://localhost:3306/sqltestdb"; // database url
	public String user; // MySQL user name
	public String password; // MySQL userpwd
	public Connection con; // declare connect subject
	
	
	//-------------------- constructor --------------------
	finSQLConnect(){
		user = "root";
		password = "123456";
	}
	
	finSQLConnect(String userValue, String pwd){
		user = userValue;
		password = pwd;
	}
	
	//--------------------- set function -----------------------
	public void setPassword(String pwd) {
		password = pwd;
	}
	
	public void setUserName(String userNameValue) {
		user = userNameValue;
	}
	
	public void setUser(String userValue, String pwd) {
		user = userValue;
		password = pwd;
	}
	
	
	
	//------------------- database connection ------------------------
	
	public void getConnection(String[] args) {
		try {
			// load driver
			Class.forName(driver);
			// connect to SQL server
			con = DriverManager.getConnection(url,user,password);
			if(!con.isClosed())
				System.out.println("Succeeded connecting to the Database!");
			
			return;
		} catch(ClassNotFoundException e) {   
			//driver problem
			System.out.println("Sorry,can`t find the Driver!");   
			e.printStackTrace();   
		} catch(SQLException e) {
			//database connect problem
			e.printStackTrace();  
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			System.out.println("���ݿ����ݳɹ���ȡ����");
		}
		return;
	}
	
	public void closeConnection() throws SQLException {
		con.close();
		return;
	}
	
	
	
	
	
	// use for checking
	public void executeQuery(String sql) throws SQLException {
		// create statement class to excute query
		Statement statement = con.createStatement();
		// result set save the result
		ResultSet rs = statement.executeQuery(sql);
		System.out.println("-----------------");
		System.out.println("ִ�н��������ʾ:");
		System.out.println("-----------------");
        /*     
		String job = null;
		String id = null;
		while(rs.next()){
			//��ȡstuname��������
			job = rs.getString("job");
			//��ȡstuid��������
			id = rs.getString("ename");
			//������
			System.out.println(id + "\t" + job);
		}
		*/
            rs.close();
	}
	

	//------------------- fin table operation ------------------------------
	
	// print finID, secID, balance, interest, state
	// return the number of rows found
	public int finSearch(String FinID) throws SQLException {
		Statement statement = con.createStatement();
		String sql = finSQLGen.finSearch(FinID);
		int flag = 0;
		try {
			ResultSet rs = statement.executeQuery(sql);
			
			System.out.println("finID\t secID\t balance\t interest\t state");
			while(rs.next()){
				//(finID, SecID, pwd, balance, 0, true);
				String ID = rs.getString(finSQLGen.IDColName);
				String secID = rs.getString(finSQLGen.secIDColName); 
				double balance = rs.getDouble(finSQLGen.balColName);
				double interest = rs.getDouble(finSQLGen.interestColName);
				boolean state = rs.getBoolean(finSQLGen.stateColName);
				
				System.out.println( ID + "\t" + secID + "\t" + Double.toString(balance) + "\t" 
						+ Double.toString(interest) + "\t" + state );
				flag++;
			}
			return flag;
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			statement.close();
		}
		return flag;
		
	}
	
	// use to change password. the return value is 1 if the change is succeed
	public int changePwd(String FinID, String pwd, String newpwd) throws SQLException {
		Statement statement = con.createStatement();
		String sql = finSQLGen.finUpdatepwd(FinID, pwd, newpwd);
		
		
		try {
			int result = statement.executeUpdate(sql);
			if (result == 1) {
				System.out.println("change succeed");
			}
			else if (result == 0) {
				System.out.println("confirm failed");
			}
			else {
				System.out.println("error: multiple change pwd subject");
			}
			return result;
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			statement.close();
		}
		return 0;
		
		
	}
	
	// return true if have a return; return false otherwise
	public boolean checkPwd(String FinID, String pwd) throws SQLException {
		Statement statement = con.createStatement();
		String sql = finSQLGen.finCheckpwd(FinID, pwd);
		try {
			return statement.execute(sql);	
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			statement.close();
		}
		return false;
			
	}
	
	// do change balance and record in the log, can write comment in the same time if needed
	public int[] changeBal(String FinID, double amount, String comment) throws SQLException {
		int numOfSqlLine = 2;
		Statement statement = con.createStatement();
		String sql[] = new String[numOfSqlLine];
		sql[0] = finSQLGen.finUpateBalance(FinID, amount);
		sql[1] = finSQLGen.logNewEntry(FinID, amount, comment);
		
		
		try {
			for(int i=0; i<numOfSqlLine; i++) {
				statement.addBatch(sql[i]);
			}
			return statement.executeBatch();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			statement.close();
		}
		return null;
		
	}
	
	// return true if operation success, false otherwise
	public String createNewFinAccount(String SecID, String pwd, String balance) throws SQLException {
		long FinID = 0;
		Statement statement = con.createStatement();
		String s = finSQLGen.finGetGreatestFinID();
		try {
				
			ResultSet rs = statement.executeQuery(s);
			while(rs.next()){
				FinID = rs.getLong(0);
			}
			FinID++;
			String sql = finSQLGen.finNewAccount(Long.toString(FinID), SecID, pwd, balance);
			return Long.toString(FinID);	
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			statement.close();
		}
		return "error";
	}
	
	// return affected rows, normal case 1
	public int setState(String FinID, boolean statevalue) throws SQLException {
		Statement statement = con.createStatement();
		String sql = finSQLGen.finSetState(FinID, statevalue);

		try {
			int result = statement.executeUpdate(sql);
			if (result != 1)
				System.out.println("error in set state");
			return result;
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			statement.close();
		}
		return 0;
	}
	
	// to calculate interest, the return is a array of statement return
	public int[] calcInterest() throws SQLException{
		int numOfSqlLine = 3;
		
		Statement statement = con.createStatement();
		String sql[] = finSQLGen.finCalInterest();
		
		try {
			for(int i=0; i<numOfSqlLine; i++) {
				statement.addBatch(sql[i]);
			}
			return statement.executeBatch();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			statement.close();
		}
	
		return null;
	}
	
	
	
	//------------------- fin Log operation ------------------------
	
	
	// print actID, finID, amount, time, comment
	public void logSearch(String FinID) throws SQLException {
		Statement statement = con.createStatement();
		String sql = finSQLGen.logSearch(FinID);
		try {
			ResultSet rs = statement.executeQuery(sql);
			
			System.out.println("actID\t finID\t amount\t time\t comment");
			while(rs.next()){
				//(finID, SecID, pwd, balance, 0, true);
				String actID = rs.getString(finSQLGen.actIDColName);
				String finID = rs.getString(finSQLGen.logFinIDColName); 
				double amount = rs.getDouble(finSQLGen.amountColName);
				Date time = rs.getDate(finSQLGen.timeColName);
				String comment = rs.getString(finSQLGen.logComColName);
				
				System.out.println( actID + "\t" + finID + "\t" + Double.toString(amount) + "\t" 
						+ time + "\t" + comment );
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			statement.close();
		}
		
	}
	
	// to insert a new log
	public boolean logInsert(String FinID, double amount, String comment) throws SQLException {
		Statement statement = con.createStatement();
		String sql = finSQLGen.logNewEntry(FinID, amount, comment);
		
		try {
			return statement.execute(sql);	
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			statement.close();
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}




