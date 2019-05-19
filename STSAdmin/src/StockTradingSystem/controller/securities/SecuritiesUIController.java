package StockTradingSystem.controller.securities;

import StockTradingSystem.controller.utils.AdminUIController;
import StockTradingSystem.data.SecuritiesAccount.SecuritiesAccount;
import StockTradingSystem.data.SecuritiesAccount.PersonalAccount;
import StockTradingSystem.database.SecuritiesAccountDBManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

import java.time.LocalDate;



public class SecuritiesUIController extends AdminUIController {

    
    SecuritiesAccountDBManager db = new SecuritiesAccountDBManager();

    @FXML
    private MenuBar menuBar;

    @FXML
    private Text welcome;

    

    @FXML
    private StackPane pagecc;

    @FXML
    private StackPane pagechose;

    @FXML
    private StackPane pagefr;

    @FXML
    private StackPane pagers;

    @FXML
    private StackPane pagecprs;

    @FXML
    private StackPane pagems;

    @FXML
    private StackPane pagepsrs;

    //个人注册
    @FXML
    private DatePicker date;

    @FXML
    private TextField psname;

    @FXML
    private TextField psid;

    @FXML
    private Button pscancelbtn;

    @FXML
    private Label message;

    @FXML
    private TextField prof;

    @FXML
    private ToggleGroup group1;

    @FXML
    private Button psokbutton;

    @FXML
    private TextField psaddr;

    @FXML
    private TextField pstel;

    @FXML
    private TextField psjob;

    @FXML
    private TextField rppsid;

    @FXML
    private RadioButton man;

    @FXML
    private TextField psacc;

    @FXML
    private TextField diplome;

    @FXML
    private RadioButton women;


    private SecuritiesAccount account;


    private boolean checktextField(TextField str){
        return(str.getText() != null && !str.getText().isEmpty());
    }

    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
    }

    public void initTime(){
        LocalDate date1=LocalDate.now();
        date.setValue(date1);
    }

    @FXML
    public void personalregister(ActionEvent event) {
        if(!checktextField(psname)){
            message.setText("请输入你的名字");
            message.setVisible(true);
            return;
        }

        if(!checktextField(psid)){
            message.setText("请输入你的身份证号");
            message.setVisible(true);
            return;
        }
        if(!checktextField(prof)){
            message.setVisible(true);
            message.setText("请输入你的职业");
            return;
        }
        if(!checktextField(psaddr)){
            message.setVisible(true);
            message.setText("请输入你的地址");
            return;
        }
        if(!checktextField(pstel)){
            message.setVisible(true);
            message.setText("请输入你的电话");
            return;
        }
        if(!checktextField(psjob)){
            message.setVisible(true);
            message.setText("请输入你的工作");
            return;
        }
        if(!checktextField(diplome)){
            message.setVisible(true);
            message.setText("请输入你的学历");
            return;
        }
        if(!man.isSelected() && !women.isSelected()){
            message.setVisible(true);
            message.setText("请选择你的性别");
            return;
        }
       // String pattern = "yyyy年MM月dd日 HH时mm分ss秒";
        //SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        //String date1=sdf.format(date.getValue());
        boolean sex;
        if(man.isSelected()){
            sex=true;
        }else{
            sex=false;
        }//todo

        if(checktextField(rppsid)){
            PersonalAccount account1= new PersonalAccount(java.sql.Date.valueOf(date.getValue()), psname.getText(), sex, psid.getText(), psaddr.getText(), prof.getText(), diplome.getText(), psjob.getText(), pstel.getText(), rppsid.getText());
            db.newAccount(account1, 0);
            this.goToMessage("恭喜注册成功",String.valueOf(account1.getSecurities_id()));
        }else{
            PersonalAccount account1= new PersonalAccount(java.sql.Date.valueOf(date.getValue()), psname.getText(), sex, psid.getText(), psaddr.getText(), prof.getText(), diplome.getText(), psjob.getText(), pstel.getText());
            db.newAccount(account1, 1);
            this.goToMessage("恭喜注册成功", String.valueOf(account1.getSecurities_id()));
        }
    }


    @FXML
    public void cancelBtn(ActionEvent event) {
        pagepsrs.setVisible(false);
        pagers.setVisible(true);
    }

    //公司注册
    @FXML
    private TextField cptradername;

    @FXML
    private TextField cptradertel;

    @FXML
    private Button cpregisterBtn;

    @FXML
    private TextField cpname;

    @FXML
    private TextField cpid;

    @FXML
    private Button cpbackBtn;

    @FXML
    private TextField cptel;

    @FXML
    private TextField cpaddr;

    @FXML
    private TextField cptraderid;

    @FXML
    private TextField cptraderaddr;

    @FXML
    private Label message1;

    @FXML
    void companyregister(ActionEvent event) {
        if(!checktextField(cptradername)){
            message1.setText("请输入交易者名字");
            message1.setVisible(true);
            return;
        }
   
        if(!checktextField(cptradertel)){
            message1.setText("请输入交易者的电话");
            message1.setVisible(true);
            return;
        }

        if(!checktextField(cpname)){
            message1.setText("请输入公司名称");
            message1.setVisible(true);
            return;
        }

        if(!checktextField(cpid)){
            message1.setText("请输入公司注册号");
            message1.setVisible(true);
            return;
        }

        if(!checktextField(cptel)){
            message1.setText("请输入公司电话");
            message1.setVisible(true);
            return;
        }

        if(!checktextField(cpaddr)){
            message1.setText("请输入公司地址");
            message1.setVisible(true);
            return;
        }

        if(!checktextField(cptraderid)){
            message1.setText("请输入交易者的身份证号");
            message1.setVisible(true);
            return;
        }

        if(!checktextField(cptraderaddr)){
            message1.setText("请输入交易者的地址");
            message1.setVisible(true);
            return;
        }//todo营业执照 法人身份证号贴加

        //CorporateAccount account1=new CorporateAccount(securites_id, cpid.getText(), cplicence.getText(),cprpid.getText(), cpname.getText(), cptel.getText(), cpaddr.getText(), cptradername.getText(), cptraderid.getText(), cptradertel.getText(), cptraderaddr.getText());
        //db.newAccount(account1);
        //this.goToMessage("恭喜注册成功",account.getSecurities_id());

    }

    @FXML
    void goback(ActionEvent event) {
        pagecprs.setVisible(false);
        pagers.setVisible(true);
    }

    //选择注册的方向
    @FXML
    private Button cprs;

    @FXML
    private Button psrs;

    @FXML
    void jumppsrs(ActionEvent event) {
         pagepsrs.setVisible(true);
        pagers.setVisible(false);

    }

    @FXML
    void jumpcprs(ActionEvent event) {
        pagecprs.setVisible(true);
        pagers.setVisible(false);
    }


    //选择功能
    @FXML
    private Button rsBtn;

    @FXML
    private Button frBtn;

    @FXML
    private Button ccBTn;

    @FXML
    void jumprs(ActionEvent event) {
        pagechose.setVisible(false);
        pagers.setVisible(true);
    }

    @FXML
    void jumptofr(ActionEvent event) {
        pagechose.setVisible(false);
        pagefr.setVisible(true);
    }

    @FXML
    void jumptocc(ActionEvent event) {
        pagechose.setVisible(false);
        pagecc.setVisible(true);
    }

    //注销
     @FXML
    private TextField ccaccnb;

    @FXML
    private Button ccbackBtn;

    @FXML
    private Button ccokBtn;

    @FXML
    private TextField ccidNb;

    @FXML
    public void cancelok(ActionEvent event) {
        if(!checktextField(ccaccnb)){
            message.setText("请输入你的账户");
            message.setVisible(true);
            return;
        }

        if(!checktextField(ccidNb)){
            message.setText("请输入你的身份证号");
            message.setVisible(true);
            return;
        }
        //todo
//        if(ccidnb.getText().length)
//        CorporateAccount account1=new CorporateAccount;
//
//        this.goToMessage("注销成功",account.getSecurities_id());
    }
   
   
    //冻结
     @FXML
    private Button frbackBtn;

    @FXML
    private Label frms;

    @FXML
    private Button frokBtn;

    @FXML
    private TextField fridNb;

    @FXML
    void fronzen(ActionEvent event) {
         if(!checktextField(fridNb)){
            frms.setText("请输入身份证号");
            frms.setVisible(true);
            return;
        }
        //todo
        //this.goToMessage("冻结成功",account.getSecurities_id());


    }

    @FXML
    void goBack(ActionEvent event) {
        pagechose.setVisible(true);
        pagefr.setVisible(false);
        pagecc.setVisible(false);
    }

    //信息弹窗
    @FXML // fx:id="message"
    private Label msms; // Value injected by FXMLLoader

    @FXML // fx:id="okBtn"
    private Button msokBtn; // Value injected by FXMLLoader

    @FXML // fx:id="account"
    private Label msacc; // Value injected by FXMLLoader

    public void setText1(String message1,String account1){
        this.msms.setText(message1);
        this.msacc.setText("账号: "+account1);
    }

    @FXML
    public void backtoMain(ActionEvent event) {
        pagechose.setVisible(true);
        pagems.setVisible(false);
    }

    public void goToMessage(String str,String str1){
        setText1(str,str1);
        pagems.setVisible(true);
        pagefr.setVisible(false);
        pagecc.setVisible(false);
        pagepsrs.setVisible(false);
        pagecprs.setVisible(false);
    }



    


   
   

  

}
