package StockTradingSystem.controller.inter_manage;

import StockTradingSystem.Main;
import StockTradingSystem.controller.utils.ControllerUtils;
import StockTradingSystem.domain.entity.Command;
import StockTradingSystem.domain.entity.Stock;
import StockTradingSystem.domain.table.CommandProperty;
import StockTradingSystem.http_utils.CustomResp;
import StockTradingSystem.http_utils.HttpCommon;
import StockTradingSystem.http_utils.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.lang.reflect.Type;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StockDetailUIController implements Initializable {
    private static Stock stock = new Stock();
    static void setStock(Stock stock) {
        StockDetailUIController.stock = stock;
    }

    private Main application;
    public void setApp(Main app) {
        this.application = app;
    }
    public Main getApp() {return this.application; }

    @FXML private ChoiceBox<String> limitChoiceBox;
    @FXML private ChoiceBox<String> stateChoiceBox;
    @FXML private TableView<CommandProperty> inCmdTable;
    @FXML private TableColumn<CommandProperty, Date> inTimeCol;
    @FXML private TableColumn<CommandProperty, Integer> inIdCol;
    @FXML private TableColumn<CommandProperty, Integer> inStockCountCol;
    @FXML private TableColumn<CommandProperty, Double> inStockPriceCol;
    @FXML private TableView<CommandProperty> outCmdTable;
    @FXML private TableColumn<CommandProperty, Date> outTimeCol;
    @FXML private TableColumn<CommandProperty, Integer> outIdCol;
    @FXML private TableColumn<CommandProperty, Integer> outStockCountCol;
    @FXML private TableColumn<CommandProperty, Double> outStockPriceCol;
    private ObservableList<CommandProperty> inCmdObservableList = FXCollections.observableArrayList();
    private ObservableList<CommandProperty> outCmdObservableList = FXCollections.observableArrayList();

    @FXML private Text stockCodeTxt;
    @FXML private Text stockNameTxt;
    @FXML private Text stockAmountTxt;
    @FXML private Text stockPriceTxt;
    @FXML private Text stockStateTxt;
    @FXML private Text stockLimitTxt;

    /* JSON语句转换 */
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

    @Override
    public void initialize(URL url, ResourceBundle rb){
        ControllerUtils.initManageChoiceBox(stateChoiceBox, limitChoiceBox);
        initInCmdTable();
        initOutCmdTable();
        initStockInfo();
    }

    private void initStockInfo() {
        stockCodeTxt.setText(stock.getStockCode());
        stockNameTxt.setText(stock.getStockName());
        stockAmountTxt.setText("成交量: " + stock.getStockAmount());
        stockPriceTxt.setText("成交价: " + stock.getStockPrice());
        stockStateTxt.setText("状态: " + stock.getStockState());
        stockLimitTxt.setText("涨跌限制: " + stock.getStockLimit());
    }

    private void initOutCmdTable() {
        String stockCode = gson.toJson(stock.getStockCode());
        System.out.println(stockCode);
        CustomResp cr = new HttpCommon().doHttp("/command/out/", "POST", stockCode);
        Result result = new Gson().fromJson(cr.getResultJSON(), Result.class);
        if(!result.isStatus()) {
            ControllerUtils.showAlert("获取卖指令出错\n" + result.getReasons());
            return;
        }
        Type listType = new TypeToken<ArrayList<Command>>(){}.getType();
        List<Command> cmds = gson.fromJson(cr.getObjectJSON(), listType);
        for (Command cmd : cmds) outCmdObservableList.add(new CommandProperty(cmd));
        System.out.println("成功导入卖指令");
        outTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        outIdCol.setCellValueFactory(new PropertyValueFactory<>("fundId"));
        outStockCountCol.setCellValueFactory(new PropertyValueFactory<>("stockCount"));
        outStockPriceCol.setCellValueFactory(new PropertyValueFactory<>("stockPrice"));
        outCmdTable.setItems(outCmdObservableList);
    }

    private void initInCmdTable() {
        CustomResp cr = new HttpCommon().doHttp("/command/in/", "POST", gson.toJson(stock.getStockCode()));
        Result result = new Gson().fromJson(cr.getResultJSON(), Result.class);
        if(!result.isStatus()) {
            ControllerUtils.showAlert("获取买指令出错\n" + result.getReasons());
            return;
        }
        Type listType = new TypeToken<ArrayList<Command>>(){}.getType();
        List<Command> cmds = gson.fromJson(cr.getObjectJSON(), listType);
        for (Command cmd : cmds) inCmdObservableList.add(new CommandProperty(cmd));
        System.out.println("成功导入买指令");
        inTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        inIdCol.setCellValueFactory(new PropertyValueFactory<>("fundId"));
        inStockCountCol.setCellValueFactory(new PropertyValueFactory<>("stockCount"));
        inStockPriceCol.setCellValueFactory(new PropertyValueFactory<>("stockPrice"));
        inCmdTable.setItems(inCmdObservableList);
    }

    public void setStockLimit() {
        double limit = ControllerUtils.limit2Msg(limitChoiceBox);
        stockLimitTxt.setText("涨跌限制: " + limitChoiceBox.getValue());

        // 修改数据库中的信息
        List<Stock> stockList=new ArrayList<>();
        stockList.add(stock);
        String json = new Gson().toJson(stockList);
        CustomResp cr;
        if (limit <= 0)
            cr = new HttpCommon().doHttp("/stock/update_list/limit/-1", "POST", json);
        else
            cr = new HttpCommon().doHttp("/stock/update_list/limit/"+ limit*100, "POST", json);
        // 跳转到提示界面
        if (cr.getResultJSON().substring(10,14).equals("true")){
            ControllerUtils.showAlert("[成功] 修改股票涨跌幅成功！");
        }else {
            ControllerUtils.showAlert("[失败] 修改股票涨跌幅失败！");
        }

        System.out.println("设置涨跌幅成功");
    }

    public void setStockState() {
        String newState = stateChoiceBox.getValue();
        String setState = ControllerUtils.state2Msg(newState);
        stockStateTxt.setText("状态: " + stateChoiceBox.getValue());

        // 修改数据库中的信息
        List<Stock> stockList=new ArrayList<>();
        stockList.add(stock);
        String json = new Gson().toJson(stockList);
        CustomResp cr = new HttpCommon().doHttp("/stock/update_list/state/"+ setState, "POST", json);

        // 跳转到提示界面
        if (cr.getResultJSON().substring(10,14).equals("true")){
            ControllerUtils.showAlert("[成功] 修改股票涨跌幅成功！");
        }else {
            ControllerUtils.showAlert("[失败] 修改股票涨跌幅失败！");
        }

        System.out.println("设置涨跌幅成功");
    }



}
