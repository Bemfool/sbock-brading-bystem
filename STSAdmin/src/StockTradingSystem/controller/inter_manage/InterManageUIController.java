package StockTradingSystem.controller.inter_manage;

import StockTradingSystem.Main;
import StockTradingSystem.controller.utils.AdminUIController;
import StockTradingSystem.controller.utils.ControllerUtils;
import StockTradingSystem.domain.entity.Index;
import StockTradingSystem.domain.entity.Stock;
import StockTradingSystem.domain.table.IndexProperty;
import StockTradingSystem.domain.table.StockProperty;
import StockTradingSystem.http_utils.CustomResp;
import StockTradingSystem.http_utils.HttpCommon;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.*;

import static StockTradingSystem.GlobalInfo.MANAGER_PRIV;

/**
 * Class InterManageUIController extend from AdminUIController.
 * The setApp method is used to set application.
 * The getApp method is used to set application.
 * The {@code modifyPassword} method is used to go to modifyPasswordUI.
 * The {@code logout} method is used to logout the account and go back to AdminLoginUI.
 * The {@code quit} method is used to quit application.
 * The {@code initialize} method is used to initialize application.
 * The {@code setChoiceBox} method is used to set ChoiceBox and add choice into it.
 * The {@code bindStock} method is used to bind stock information with TableView.
 * The {@code bindIndex} method is used to bind index information with TableView.
 * The {@code clickIntoDetail} method is used to go to the StockDetailUIController.
 * The {@code setStockState} method is used to set stock state.
 * The {@code setStockLimit} method is used to set stock limit.
 * The {@code displayStock} method is used to get stock information from database.
 * The {@code displayIndex} method is used to get index information from database.
 */

public class InterManageUIController extends AdminUIController {
    @FXML private ChoiceBox<String> choiceBoxLimit;
    @FXML private ChoiceBox<String> choiceBoxState;
    @FXML private TableView<StockProperty> stockTableView;
    @FXML private TableColumn<StockProperty,String> stockNameTableView;    //股票名称列
    @FXML private TableColumn<StockProperty,String> stockLimitTableView;    //股票涨跌幅限制列
    @FXML private TableColumn<StockProperty,String> stockCodeTableView;    //股票代码列
    @FXML private TableColumn<StockProperty,Double> stockCeilTableView;    //股票涨停价格列
    @FXML private TableColumn<StockProperty,Double> stockFloorTableView;    //股票跌停价格列
    @FXML private TableColumn<StockProperty,Double> stockPriceTableView;    //股票价格列
    @FXML private TableColumn<StockProperty,String> stockStateTableView;    //股票交易状态列
    @FXML private TableColumn<StockProperty,String> stockChangeTableView;    //股票涨跌幅（现）列
    @FXML private TableColumn<StockProperty,Double> stockCloseTableView;    //股票最新收盘价列
    @FXML private TableColumn<StockProperty,Integer> stockAmountTableView;    //股票交易量列
    @FXML private TableColumn<StockProperty,Double> stockTotalTableView;    //股票交易总额列
    @FXML private TableView<IndexProperty> indexTableView;
    @FXML private TableColumn<IndexProperty,String> indexNameTableView;    //指数名称列
    @FXML private TableColumn<IndexProperty,String> indexCodeTableView;    //指数代码列
    @FXML private TableColumn<IndexProperty,String> indexNumericTableView;    //指数数值列
    private ObservableList<StockProperty> stockObservableList = FXCollections.observableArrayList();
    private ObservableList<StockProperty> stockObservableListCache = FXCollections.observableArrayList();
    private ObservableList<IndexProperty> indexObservableList = FXCollections.observableArrayList();

    private Timer timer = new Timer();
    private TimerTask display = new TimerTask() {
        @Override
        public void run() {
            displayStock();
            //displayIndex();
            if(!Main.flashStockFlag)
                timer.cancel();
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayStock();
        bindStock();
        displayIndex();
        bindIndex();
        ControllerUtils.initManageChoiceBox(choiceBoxState, choiceBoxLimit);
        startTimerLoop();

        super.initialize(url, rb);
    }

    private void startTimerLoop(){
        timer.schedule(display,5000,5000);
    }

    private void bindStock(){
        stockNameTableView.setCellValueFactory(new PropertyValueFactory<>("stockName"));
        stockLimitTableView.setCellValueFactory(new PropertyValueFactory<>("stockLimit"));
        stockCodeTableView.setCellValueFactory(new PropertyValueFactory<>("stockCode"));
        stockCeilTableView.setCellValueFactory(new PropertyValueFactory<>("ceilingPrice"));
        stockFloorTableView.setCellValueFactory(new PropertyValueFactory<>("floorPrice"));
        stockPriceTableView.setCellValueFactory(new PropertyValueFactory<>("stockPrice"));
        stockStateTableView.setCellValueFactory(new PropertyValueFactory<>("stockState"));
        stockChangeTableView.setCellValueFactory(new PropertyValueFactory<>("stockChange"));
        stockCloseTableView.setCellValueFactory(new PropertyValueFactory<>("closingPrice"));
        stockAmountTableView.setCellValueFactory(new PropertyValueFactory<>("stockAmount"));
        stockTotalTableView.setCellValueFactory(new PropertyValueFactory<>("stockTotal"));
//        stockTableView.setVisible(true);
//        stockTableView.setEditable(false);
        stockTableView.setTableMenuButtonVisible(true);
        stockTableView.setItems(stockObservableListCache);
    }

    private void bindIndex(){
        indexNameTableView.setCellValueFactory(new PropertyValueFactory<>("indexName"));
        indexCodeTableView.setCellValueFactory(new PropertyValueFactory<>("indexCode"));
        indexNumericTableView.setCellValueFactory(new PropertyValueFactory<>("indexPrice"));

        indexTableView.setVisible(true);
        indexTableView.setEditable(false);
        indexTableView.setTableMenuButtonVisible(true);
        indexTableView.setItems(indexObservableList);
    }

    public void clickIntoDetail(){
        // 将选中股票的isSelect状态设置为选中
        // 单选、多选时先清空，再把选中的设置
        stockTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        List<StockProperty> stockSelected = stockTableView.getSelectionModel().getSelectedItems();
        stockTableView.setOnMouseClicked(event -> {
            if (event.getClickCount()==2&&stockSelected.size()==1){
                try {
                    StockDetailUIController.setStock(new Stock(stockSelected.get(0)));
                    getApp().createStockDetailUI();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public void setStockState() {
        String newState=choiceBoxState.getValue();
        String setState = ControllerUtils.state2Msg(newState);
        List<StockProperty> stockSelected=stockTableView.getSelectionModel().getSelectedItems();

        for (StockProperty aStockSelected : stockSelected) {
            for (StockProperty aStockObservableList : stockObservableList) {
                if (aStockSelected.getStockCode().equals(aStockObservableList.getStockCode())) {
                    aStockObservableList.setStockState(newState);
                    break;
                }
            }
        }

        // 修改数据库中的信息
        List<Stock> stockList=new ArrayList<>();
        for (StockProperty aStockSelected : stockSelected) {
            stockList.add(new Stock(aStockSelected));
        }
        String json = new Gson().toJson(stockList);
        CustomResp cr = new HttpCommon().doHttp("/stock/update_list/state/"+setState, "POST", json);

        // 跳转到提示界面
        if (cr.getResultJSON().substring(10,14).equals("true")){
            ControllerUtils.showAlert("[成功] 修改股票交易状态成功！");
        }else {
            ControllerUtils.showAlert("[失败] 修改股票交易状态失败！");
        }
        System.out.println("设置交易状态成功");
    }

    public void setStockLimit() {
        double riseFallLimit;
        riseFallLimit = ControllerUtils.limit2Msg(choiceBoxLimit);
        List<StockProperty> stockSelected=stockTableView.getSelectionModel().getSelectedItems();

        // 修改显示的信息
        for (StockProperty aStockSelected : stockSelected) {
            for (StockProperty aStockObservableList : stockObservableList) {
                if (aStockSelected.getStockCode().equals(aStockObservableList.getStockCode())) {
                    aStockObservableList.setStockLimit(riseFallLimit);
                    aStockObservableList.setCeilingPrice();
                    aStockObservableList.setFloorPrice();
                    break;
                }
            }
        }

        // 修改数据库中的信息
        List<Stock> stockList=new ArrayList<>();
        for (StockProperty aStockSelected : stockSelected) {
            stockList.add(new Stock(aStockSelected));
        }
        String json = new Gson().toJson(stockList);
        double riseFallLimitTemp;
        if (riseFallLimit<=0){
            riseFallLimitTemp=-1;
        }else{
            riseFallLimitTemp=riseFallLimit*100;
        }
        CustomResp cr = new HttpCommon().doHttp("/stock/update_list/limit/"+riseFallLimitTemp, "POST", json);

        // 跳转到提示界面
        if (cr.getResultJSON().substring(10,14).equals("true")){
            ControllerUtils.showAlert("[成功] 修改股票涨跌幅成功！");
        }else {
            ControllerUtils.showAlert("[失败] 修改股票涨跌幅失败！");
        }

        System.out.println("设置涨跌幅成功");
    }

    private void displayStock(){
        List<Integer> list = new ArrayList<>(stockTableView.getSelectionModel().getSelectedIndices());
        stockObservableList.clear();
        CustomResp cr = new HttpCommon().doHttp("/stock/"+MANAGER_PRIV, "GET", null);
        Type listType = new TypeToken<ArrayList<Stock>>(){}.getType();
        List<Stock> stocks = new Gson().fromJson(cr.getObjectJSON(), listType);
        for (Stock stock : stocks)
            stockObservableList.add(new StockProperty(stock));
        stockObservableListCache.setAll(stockObservableList);
//        if(!list.isEmpty()) {
//            Integer[] ints = list.toArray(new Integer[0]);
//            stockTableView.getSelectionModel().selectIndices(ints[0], );
//        }
        for(int aList : list) stockTableView.getSelectionModel().select(aList);
        // 已经放到缓存StockObservableList中，然后显示到表格里
        System.out.println("已经将股票数据导入缓存");
    }

    private void displayIndex(){
        CustomResp cr = new HttpCommon().doHttp("/index/all", "GET", null);
        Type listType = new TypeToken<ArrayList<Index>>(){}.getType();
        List<Index> indexes = new Gson().fromJson(cr.getObjectJSON(), listType);
        for (Index indexe : indexes) {
            indexObservableList.add(new IndexProperty(indexe));
        }

        // 已经放到缓存IndexObservableList中，然后显示到表格里
        System.out.println("已经将指数数据导入到缓存");
    }
}