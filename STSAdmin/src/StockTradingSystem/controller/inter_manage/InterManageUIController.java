package StockTradingSystem.controller.inter_manage;

import StockTradingSystem.Main;
import StockTradingSystem.controller.utils.AdminUIController;
import StockTradingSystem.data.Index;
import StockTradingSystem.data.Stock;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class InterManageUIController extends AdminUIController {
    private Main application;
    @FXML private JFXButton setstatebtn;
    @FXML private JFXButton setlimitbtn;
    @FXML private JFXTextField JFXlimittext;
    @FXML private TableView<Stock> stocktableview;
    @FXML private TableColumn<Stock,String> jfxstnametv;    //股票名称列
    @FXML private TableColumn<Stock,String> jfxstlimittv;    //股票涨跌幅限制列
    @FXML private TableColumn<Stock,String> jfxstcodetv;    //股票代码列
    @FXML private TableColumn<Stock,Double> jfxstceiltv;    //股票涨停价格列
    @FXML private TableColumn<Stock,Double> jfxstfloortv;    //股票跌停价格列
    @FXML private TableColumn<Stock,Double> jfxstpricetv;    //股票价格列
    @FXML private TableColumn<Stock,String> jfxststatetv;    //股票交易状态列
    @FXML private TableColumn<Stock,String> jfxstchangetv;    //股票涨跌幅（现）列
    @FXML private TableView<Index> indextableview;
    @FXML private TableColumn<Index,String> jfxinnametv;    //指数名称列
    @FXML private TableColumn<Index,String> jfxincodetv;    //指数代码列
    @FXML private TableColumn<Index,String> jfxinnumtv;    //指数数值列
    private ObservableList<Stock> stockObservableList = FXCollections.observableArrayList();
    private ObservableList<Index> indexObservableList = FXCollections.observableArrayList();

    public void setApp(Main app) { this.application = app; }
    public Main getApp() {return this.application; }

    public void modifyPassword(){
        // TODO 跳到修改密码界面
        //application.createChangePasswordUI();
    }

    @Override
    public void logout() throws Exception {
        application.stage.close();
        application.gotoAdminLoginUI();
    }

    @Override
    public void quit() {
        // TODO 退出
        application.stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 显示股票信息
        displaystock();
        bindstock();
        // TODO 显示指数信息，但现在还没有
//        displayindex();
        bindindex();
        super.initialize(url, rb);
    }

    public void bindstock(){
        // TODO 股票数据绑定TableView
        jfxstnametv.setCellValueFactory(new PropertyValueFactory<>("stockName"));
        jfxstlimittv.setCellValueFactory(new PropertyValueFactory<>("stockLimit"));
        jfxstcodetv.setCellValueFactory(new PropertyValueFactory<>("stockCode"));
        jfxstceiltv.setCellValueFactory(new PropertyValueFactory<>("ceilingPrice"));
        jfxstfloortv.setCellValueFactory(new PropertyValueFactory<>("floorPrice"));
        jfxstpricetv.setCellValueFactory(new PropertyValueFactory<>("stockPrice"));
        jfxststatetv.setCellValueFactory(new PropertyValueFactory<>("stockState"));
        jfxstchangetv.setCellValueFactory(new PropertyValueFactory<>("stockChange"));

        stocktableview.setVisible(true);
        stocktableview.setEditable(false);
        stocktableview.setTableMenuButtonVisible(true);
        stocktableview.setItems(stockObservableList);
    }

    public void bindindex(){
        // TODO 指数数据绑定TableView
        jfxinnametv.setCellValueFactory(new PropertyValueFactory<>("indexName"));
        jfxincodetv.setCellValueFactory(new PropertyValueFactory<>("indexCode"));
        jfxinnumtv.setCellValueFactory(new PropertyValueFactory<>("indexPrice"));

        indextableview.setVisible(true);
        indextableview.setEditable(false);
        indextableview.setTableMenuButtonVisible(true);
        indextableview.setItems(indexObservableList);
    }

    public void clickintodetail() throws Exception{
        // TODO 将选中股票的isSelect状态设置为选中
        //  单选、多选时先清空，再把选中的设置
        stocktableview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        for (int i = 0; i < stockObservableList.size(); i++) {
            stockObservableList.get(i).setIsselect(false);
        }
        List<Stock> stockSelected=stocktableview.getSelectionModel().getSelectedItems();
        for (int i = 0; i < stockSelected.size(); i++) {
            for (int j = 0; j < stockObservableList.size(); j++) {
                if (stockSelected.get(i).equals(stockObservableList.get(j))){
                    stockObservableList.get(j).setIsselect(true);
                }
            }
        }
        stocktableview.setOnMouseClicked(event -> {
            if (event.getClickCount()==2&&stockSelected.size()==1){
                try {
                    application.stage.close();
                    getApp().gotoStockDetailUI();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    public void setstockstate(){
        // TODO 设置股票交易状态
        for (int i = 0; i < stockObservableList.size(); i++){
            if (!stockObservableList.get(i).isIsselect()){
                continue;
            }
            String oldState=stockObservableList.get(i).getStockState();
            if(oldState.equals("正常交易")){
                stockObservableList.get(i).setStockState("暂停交易");
                // TODO 在数据库中更改状态
                try {
                    // TODO 连接
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn= DriverManager.getConnection("jdbc:mysql://139.155.104.140:3306/" +
                            "?useSSL=false" +
                            "&serverTimezone=GMT" +
                            "&allowPublicKeyRetrieval=true", "root","0000");                    // TODO 到数据库中设置当前股票状态
                    PreparedStatement pStmt = conn.prepareStatement("UPDATE stock_trading_system.stock SET stock_state='暂停交易' WHERE stock_code=?");
                    pStmt.setString(1,stockObservableList.get(i).getStockCode());
                    pStmt.executeUpdate();
                    conn.close();
                } catch (SQLException e){
                    e.printStackTrace();
                } catch (ClassNotFoundException e2){
                    e2.printStackTrace();
                }
            }else{
                stockObservableList.get(i).setStockState("正常交易");
                // TODO 在数据库中更改状态
                try {
                    // TODO 连接
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn= DriverManager.getConnection("jdbc:mysql://139.155.104.140:3306/" +
                            "?useSSL=false" +
                            "&serverTimezone=GMT" +
                            "&allowPublicKeyRetrieval=true", "root","0000");                    // TODO 到数据库中设置当前股票状态
                    PreparedStatement pStmt = conn.prepareStatement("UPDATE stock_trading_system.stock SET stock_state='正常交易' WHERE stock_code=?");
                    pStmt.setString(1,stockObservableList.get(i).getStockCode());
                    pStmt.executeUpdate();
                    conn.close();
                } catch (SQLException e){
                    e.printStackTrace();
                } catch (ClassNotFoundException e2){
                    e2.printStackTrace();
                }
            }

        }
        System.out.println("设置交易状态成功");
    }

    public void setstocklimit() throws Exception{
        // TODO 设置股票涨跌幅
        double riseFallLimit=0;
        try{
            riseFallLimit=Double.parseDouble(JFXlimittext.getText());
            // TODO 防止将23e2这样的字符转化为2300，先检查一遍是否含有字母
            String str=JFXlimittext.getText();
            for(int i=0;i<str.length();i++){
                if ((str.charAt(i)>='a' && str.charAt(i)<='z')||(str.charAt(i)>='A'&&str.charAt(i)<='Z')){
                    throw new Exception();
                }
            }
            // TODO 超出1或者小于0的设置为1和0
            if (riseFallLimit>100 ||riseFallLimit<0){
                application.createConfirmWarningUI();
                JFXlimittext.clear();
                if (riseFallLimit>100){riseFallLimit=100;}
                else{riseFallLimit=0;}
            }
            riseFallLimit=riseFallLimit/100;
            for (int i = 0; i < stockObservableList.size(); i++) {
                if (!stockObservableList.get(i).isIsselect()){
                    continue;
                }
                double highPrice=(1+riseFallLimit)*stockObservableList.get(i).getStockPrice();
                double lowPrice=(1-riseFallLimit)*stockObservableList.get(i).getStockPrice();
                stockObservableList.get(i).setCeilingPrice(highPrice);
                stockObservableList.get(i).setFloorPrice(lowPrice);
                stockObservableList.get(i).setStockLimit();
                // TODO 在数据库中更改最大涨跌幅，即涨停价格和跌停价格
                try {
                    // TODO 连接
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn= DriverManager.getConnection("jdbc:mysql://139.155.104.140:3306/" +
                            "?useSSL=false" +
                            "&serverTimezone=GMT" +
                            "&allowPublicKeyRetrieval=true", "root","0000");                    // TODO 到数据库中设置当前股票状态
                    //String sql;
                    PreparedStatement pStmt = conn.prepareStatement("UPDATE stock_trading_system.stock SET ceiling_price=?, floor_price=? WHERE stock_code=?");
                    pStmt.setDouble(1,highPrice);
                    pStmt.setDouble(2,lowPrice);
                    pStmt.setString(3,stockObservableList.get(i).getStockCode());
                    pStmt.executeUpdate();
                    conn.close();
                } catch (SQLException e){
                    e.printStackTrace();
                } catch (ClassNotFoundException e2){
                    e2.printStackTrace();
                }
            }
            System.out.println("设置涨跌幅成功");

        }catch (Exception e){
            JFXlimittext.clear();
            application.createConfirmWarningUI();
            System.out.println("设置涨跌幅失败");
        }
    }

    public void displaystock(){
        // TODO 连接数据库，并将stock信息放到arraylist中
        try {
            // TODO 连接
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn= DriverManager.getConnection("jdbc:mysql://139.155.104.140:3306/" +
                    "?useSSL=false" +
                    "&serverTimezone=GMT" +
                    "&allowPublicKeyRetrieval=true", "root","0000");
            // TODO 到数据库中查询当前股票名称
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM stock_trading_system.stock";
            ResultSet rs = stmt.executeQuery(sql);
            // TODO 放到st中
            while(rs.next()){
                Stock st=new Stock();
                st.setStockCode(rs.getString("stock_code"));
                st.setStockName(rs.getString("stock_name"));
                st.setStockPrice(Double.valueOf(rs.getString("stock_price")));
                st.setStockState(rs.getString("stock_state"));
                st.setCeilingPrice(Double.valueOf(rs.getString("ceiling_price")));
                st.setFloorPrice(Double.valueOf(rs.getString("floor_price")));
                st.setStockLimit();
                st.setStockChange();
                stockObservableList.add(st);
            }
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e2){
            e2.printStackTrace();
        }
        // TODO 已经放到缓存StockObservableList中，然后显示到表格里
        System.out.println("已经将股票数据导入缓存");

    }

    public void displayindex(){
        // TODO 连接数据库，并将index信息放到arraylist中，与stock类似
        try {
            // TODO 连接
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn= DriverManager.getConnection("jdbc:mysql://139.155.104.140:3306/" +
                    "?useSSL=false" +
                    "&serverTimezone=GMT" +
                    "&allowPublicKeyRetrieval=true", "root","0000");
            Statement stmt = conn.createStatement();
            String sql;
            // TODO
            //  哪一个是指数的数据库？？？？？？？？？？？？？？？？？？？
            sql = "SELECT * FROM stock_trading_system.index";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Index in = new Index();
                in.setIndexCode(rs.getString("index_code"));
                in.setIndexName(rs.getString("index_name"));
                in.setIndexPrice(Double.valueOf(rs.getString("index_price")));
                indexObservableList.add(in);
            }
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e2){
            e2.printStackTrace();
        }
        // TODO 已经放到缓存IndexArraylist中，然后显示到表格里
        System.out.println("已经将指数数据导入到缓存");
    }
}