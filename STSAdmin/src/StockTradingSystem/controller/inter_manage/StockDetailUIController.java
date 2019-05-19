package StockTradingSystem.controller.inter_manage;

import StockTradingSystem.Main;
import StockTradingSystem.controller.utils.AdminUIController;
import StockTradingSystem.data.Command;
import StockTradingSystem.data.Command;
import StockTradingSystem.data.Stock;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class StockDetailUIController extends AdminUIController {

    @FXML private TableView<Command> commandTableView;         //指令列表视图
    @FXML private TableColumn<Command,String> timeCol;         //时间列
    @FXML private TableColumn<Command,String> fundIdCol;       //资金账号列
    @FXML private TableColumn<Command,String> commandTypeCol;  //指令类型列
    @FXML private TableColumn<Command,String> stockCodeCol;    //股票代码列
    @FXML private TableColumn<Command,Integer> stockCountCol;   //股票数量列
    @FXML private Text stockNameField;  //股票名称
    @FXML private Text stockCodeField;  //股票代码

    @FXML private Text stockCountField;  //成交量，即股票数量？
    @FXML private Text stockPriceField;  //成交价
    @FXML private Text stockStateField;  //状态
    @FXML private Text stockLimitField;  //涨跌幅

    private ObservableList<Command> commandObservableList = FXCollections.observableArrayList();
    private ObservableList<Stock> stockObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        commandTableInit();
        stockDetailInit();
    }

    private void commandTableInit() {
        // TODO 显示指令信息
        fetchCommandData();

        // TODO 指令数据显示
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        fundIdCol.setCellValueFactory(new PropertyValueFactory<>("fundId"));
        commandTypeCol.setCellValueFactory(new PropertyValueFactory<>("commandType"));
        stockCodeCol.setCellValueFactory(new PropertyValueFactory<>("stockCode"));
        stockCountCol.setCellValueFactory(new PropertyValueFactory<>("stockCount"));

        commandTableView.setVisible(true);
        stockCodeCol.setVisible(true); //?
        commandTableView.setEditable(false);

        System.out.println(commandObservableList.get(0).stockCodeProperty());

        // TODO 将ArrayList里面的数据加到commandData中
        commandTableView.setItems(commandObservableList);
    }

    private void fetchCommandData() {
        // TODO 连接数据库，并将信息放到Arraylist中
        /* 待更新指令数据库 */
        try {
            // TODO 加密JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection("jdbc:mysql://139.155.104.140:3306/stock_trading_system" +
                    "?useSSL=false" +
                    "&serverTimezone=GMT" +
                    "&allowPublicKeyRetrieval=true", "root","0000");
            // TODO 到数据库中查询当前股票名称
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM stock_trading_system.command";
            ResultSet rs = stmt.executeQuery(sql);
            // TODO 放到st中
            while(rs.next()){
                Command st=new Command();
                st.setTime(rs.getString("time"));
                st.setFundID(rs.getString("fundIdCol"));
                st.setCommandType(rs.getString("commandType"));
                st.setStockCode(rs.getString("stockCode"));
                st.setStockCount(Integer.valueOf(rs.getString("stockCount")));
                commandObservableList.add(st);
            }
            conn.close();
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }

        // TODO 已经放到缓存CommandArraylist中，然后显示到表格里
        System.out.println("已经将指令数据导入缓存");

    }

    private void stockDetailInit(){
        // TODO 获取股票数据
        fetchStockData();

        // TODO 显示股票详细信息

    }

    private void fetchStockData(){
        // TODO 连接数据库，并将stock信息放到arraylist中

        try {
            // TODO 加密JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection("jdbc:mysql://139.155.104.140:3306/stock_trading_system" +
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
                stockObservableList.add(st);
            }
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /* 待实现两个功能 */
    public void setStockLimit() {
        // TODO 设置股票涨跌幅
    }

    public void setStockState(){
        // TODO 设置股票交易状态
    }

    public void gotoMainUI () throws Exception {
        super.getApp().gotoAdminMainUI();
    }

    public void gotoInterManage () throws Exception {
        super.getApp().gotoInternalManageUI();
    }

}
