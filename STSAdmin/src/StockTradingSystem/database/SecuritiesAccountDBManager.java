package StockTradingSystem.database;

import StockTradingSystem.data.SecuritiesAccount.CorporateAccount;
import StockTradingSystem.data.SecuritiesAccount.PersonalAccount;

import java.sql.*;
import java.util.ArrayList;

public class SecuritiesAccountDBManager {
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static String dbURL = "jdbc:mysql://localhost:3306/project?serverTimezone=UTC";
    private static String userName = "root";
    private static String userPwd = "";

    /**
     * 注册个人账户
     * @param account
     * @return 操作是否成功
     */
    public static boolean newAccount(PersonalAccount account, int flag) {
        if(flag == 0){
            String sql = "INSERT INTO personal_account(register_date, name, gender, id_no, " +
                    "family_add, career, education, organization, phone_no, agent_id_no) VALUES(?, ?, ?, ?, ?, ?, ?, " +
                    "?, ?, ?)";
            Object []args = {account.getRegister_date(), account.getName(),
                    account.getGender(), account.getId_no(), account.getFamily_add(), account.getCareer(), account.getEducation(),
                    account.getOrganization(), account.getPhone_no(), account.getAgent_id_no()};
            return executeUpdate(sql, args);
        }else{
            String sql = "INSERT INTO personal_account(register_date, name, gender, id_no, " +
                    "family_add, career, education, organization, phone_no) VALUES(?, ?, ?, ?, ?, ?, ?, " +
                    "?, ?)";
            Object []args = {account.getRegister_date(), account.getName(),
                    account.getGender(), account.getId_no(), account.getFamily_add(), account.getCareer(), account.getEducation(),
                    account.getOrganization(), account.getPhone_no()};
            return executeUpdate(sql, args);
        }
    }

    /**
     * 注册法人账户
     * @param account
     * @return 操作是否成功
     */
    public static boolean newAccount(CorporateAccount account) {
        String sql = "INSERT INTO corporate_account(register_no, business_license_no, " +
                "legal_representative_id, legal_representative_name, legal_representative_phone_no, " +
                "legal_representative_add, authorizer_name, authorizer_id, authorizer_phone_no, authorizer_add) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object []args = {account.getRegister_no(), account.getBusiness_license_no(),
        account.getLegal_representative_id(), account.getLegal_representative_name(), account.getBusiness_license_no(),
        account.getLegal_representative_add(), account.getAuthorizer_name(), account.getAuthorizer_id(),
        account.getAuthorizer_phone_no(), account.getAuthorizer_add()};
        return executeUpdate(sql, args);
    }
// 删除操作更改为使用setInformation()更改状态为“已删除”
//    /**
//     * 删除个人账户
//     * @param account
//     * @return 操作是否成功
//     */
//    public static boolean deleteAccount(PersonalAccount account) {
//        String sql = "DELETE FROM personal_account WHERE securities_id='" + account.getSecurities_id() + "'";
//        return executeUpdate(sql, null);
//    }
//
//    /**
//     * 删除法人账户
//     * @param account
//     * @return 操作是否成功
//     */
//    public static boolean deleteAccount(CorporateAccount account) {
//        String sql = "DELETE FROM corporate_account WHERE securities_id='" + account.getSecurities_id() + "'";
//        return executeUpdate(sql, null);
//    }

    /**
     * 获取个人账户信息
     * @param id_no 身份证号
     * @param account 返回的个人账户
     * @param fundAccount 返回的资金账户
     * @return 操作是否成功
     */
    public static boolean getInformation(String id_no, PersonalAccount account, ArrayList<String> fundAccount) {
        String sql = "SELECT * FROM ? WHERE ?=?";
        Object []args = {"personal_account", "id_no", id_no};
        ResultSet rs = executeQuery(sql, args);
        boolean result = false;
        try {
            if (rs.next()) {
                account.setSecurities_id(rs.getInt(1));
                account.setRegister_date(rs.getDate(2));
                account.setName(rs.getString(3));
                account.setGender(rs.getBoolean(4));
                account.setId_no(rs.getString(5));
                account.setFamily_add(rs.getString(6));
                account.setCareer(rs.getString(7));
                account.setEducation(rs.getString(8));
                account.setOrganization(rs.getString(9));
                account.setPhone_no(rs.getString(10));
                account.setAgent_id_no(rs.getString(11));
                account.setState(rs.getInt(12));
                args[0] = "securities_fund";
                args[1] = "securities_id";
                args[2] = account.getSecurities_id();
                rs = executeQuery(sql, args);
                while (rs.next()) {
                    fundAccount.add(rs.getString(2));
                }
                result = true;
            }
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取法人账户信息
     * @param register_no 注册号码
     * @param account 返回的法人账户
     * @param fundAccount 返回的资金账户
     * @return 操作是否成功
     */
    public static boolean getInformation(String register_no, CorporateAccount account, ArrayList<String> fundAccount) {
        String sql = "SELECT * FROM ? WHERE ?=?";
        Object []args = {"corporate_account", "register_no", register_no};
        ResultSet rs = executeQuery(sql, args);
        boolean find = false;
        try {
            if (rs.next()) {
                account.setSecurities_id(rs.getInt(1));
                account.setRegister_no(rs.getString(2));
                account.setBusiness_license_no(rs.getString(3));
                account.setLegal_representative_id(rs.getString(4));
                account.setLegal_representative_name(rs.getString(5));
                account.setLegal_representative_phone_no(rs.getString(6));
                account.setLegal_representative_add(rs.getString(7));
                account.setAuthorizer_name(rs.getString(8));
                account.setAuthorizer_id(rs.getString(9));
                account.setAuthorizer_phone_no(rs.getString(10));
                account.setAuthorizer_add(rs.getString(11));
                account.setState(rs.getInt(12));
                args[0] = "securities_fund";
                args[1] = "securities_id";
                args[2] = account.getSecurities_id();
                rs = executeQuery(sql, args);
                while (rs.next()) {
                    fundAccount.add(rs.getString(2));
                }
                find = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return find;
    }

    /**
     * 修改个人账户
     * @param account
     * @return
     */
    public static boolean setInformation(PersonalAccount account) {
        String sql = "UPDATE personal_account SET name=?, gender=?, id_no=?, family_add=?, career=?, education=?, " +
                "organization=?, phone_no=?, agent_id_no=?, sate=?";
        Object []args = {account.getName(), account.getGender(), account.getId_no(), account.getFamily_add(),
                account.getCareer(), account.getEducation(), account.getOrganization(), account.getPhone_no(),
                account.getAgent_id_no(), account.getState()};
        return executeUpdate(sql, args);
    }

    /**
     * 修改法人账户
     * @param account
     * @return
     */
    public static boolean setInformation(CorporateAccount account) {
        String sql = "UPDATE corporate_account SET register_no=?, business_license_no=?, legal_representative_id=?, " +
                "legal_representative_name=?, legal_representative_phone_no=?, legal_representative_add=?, " +
                "authorizer_name=?, authorizer_id=?, authorizer_phone_no=?, authorizer_add=?, state=?";
        Object []args = {account.getRegister_no(), account.getBusiness_license_no(), account.getLegal_representative_id(),
                account.getLegal_representative_name(), account.getLegal_representative_phone_no(),
                account.getLegal_representative_add(), account.getAuthorizer_name(), account.getAuthorizer_id(),
                account.getAuthorizer_phone_no(), account.getAuthorizer_add(), account.getState()};
        return executeUpdate(sql, args);
    }

    /**
     * 获取数据库连接
     * @return
     */
    private static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(dbURL, userName, userPwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 执行查询操作
     * @param SQL
     * @param args
     * @return 查询结果
     */
    public static ResultSet executeQuery(String SQL, Object []args) {
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            conn = getConn();
            pStmt = conn.prepareStatement(SQL);
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    pStmt.setObject(i + 1, args[i]);
                }
            }
            rs = pStmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pStmt != null)
                    pStmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 执行增删改操作
     * @param SQL
     * @param args
     * @return 操作是否成功
     */
    public static boolean executeUpdate(String SQL, Object []args) {
        Connection conn = null;
        PreparedStatement pStmt = null;
        try {
            conn = getConn();
            pStmt = conn.prepareStatement(SQL);
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    pStmt.setObject(i + 1, args[i]);
                }
            }
            int result = pStmt.executeUpdate();
            if (result > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pStmt != null)
                    pStmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}