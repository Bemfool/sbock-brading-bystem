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
    @FXML private TableColumn<CommandProperty, String> inStockCodeCol;
    @FXML private TableColumn<CommandProperty, Integer> inStockCountCol;
    @FXML private TableView<CommandProperty> outCmdTable;
    @FXML private TableColumn<CommandProperty, Date> outTimeCol;
    @FXML private TableColumn<CommandProperty, Integer> outIdCol;
    @FXML private TableColumn<CommandProperty, String> outStockCodeCol;
    @FXML private TableColumn<CommandProperty, Integer> outStockCountCol;
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
        initChoiceBox();
        initInCmdTable();
        initOutCmdTable();
        initStockInfo();
    }

    private void initStockInfo() {
        stockCodeTxt.setText(stock.getStockCode());
        stockNameTxt.setText(stock.getStockName());
//        stockAmountTxt.setText("成交量: ");
        stockPriceTxt.setText("成交价: " + stock.getStockPrice());
        stockStateTxt.setText("状态: " + stock.getStockState());
        stockLimitTxt.setText("涨跌限制: " + stock.getStockLimit());
    }

    private void initOutCmdTable() {
        CustomResp cr = new HttpCommon().doHttp("/command/out", "GET", null);
        Result result = new Gson().fromJson(cr.getResultJSON(), Result.class);
        if(!result.isStatus()) {
            ControllerUtils.showAlert("获取卖指令出错");
            return;
        }
        Type listType = new TypeToken<ArrayList<Command>>(){}.getType();
        List<Command> cmds = gson.fromJson(cr.getObjectJSON(), listType);
        for (Command cmd : cmds) outCmdObservableList.add(new CommandProperty(cmd));
        System.out.println("成功导入卖指令");
        outTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        outIdCol.setCellValueFactory(new PropertyValueFactory<>("fundId"));
        outStockCodeCol.setCellValueFactory(new PropertyValueFactory<>("stockCode"));
        outStockCountCol.setCellValueFactory(new PropertyValueFactory<>("stockCount"));
        outCmdTable.setItems(outCmdObservableList);
    }

    private void initInCmdTable() {
        CustomResp cr = new HttpCommon().doHttp("/command/in", "GET", null);
        Result result = new Gson().fromJson(cr.getResultJSON(), Result.class);
        if(!result.isStatus()) {
            ControllerUtils.showAlert("获取买指令出错");
            return;
        }
        Type listType = new TypeToken<ArrayList<Command>>(){}.getType();
        List<Command> cmds = gson.fromJson(cr.getObjectJSON(), listType);
        for (Command cmd : cmds) inCmdObservableList.add(new CommandProperty(cmd));
        System.out.println("成功导入买指令");
        inTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        inIdCol.setCellValueFactory(new PropertyValueFactory<>("fundId"));
        inStockCodeCol.setCellValueFactory(new PropertyValueFactory<>("stockCode"));
        inStockCountCol.setCellValueFactory(new PropertyValueFactory<>("stockCount"));
        inCmdTable.setItems(inCmdObservableList);
    }

    private void initChoiceBox() {
        stateChoiceBox.getItems().add("正常交易");
        stateChoiceBox.getItems().add("暂停交易");
        stateChoiceBox.getItems().add("停牌三天");
        stateChoiceBox.setValue("正常交易");
        limitChoiceBox.getItems().add("5%");
        limitChoiceBox.getItems().add("10%");
        limitChoiceBox.getItems().add("无限制");
        limitChoiceBox.setValue("10%");
    }


}
