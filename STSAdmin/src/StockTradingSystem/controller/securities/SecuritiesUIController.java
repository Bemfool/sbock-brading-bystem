package StockTradingSystem.controller.securities;

import StockTradingSystem.controller.utils.AdminUIController;
import StockTradingSystem.database.SecuritiesAccountDBManager;
import StockTradingSystem.domain.entity.securities_account.CorporateAccount;
import StockTradingSystem.domain.entity.securities_account.PersonalAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static StockTradingSystem.controller.utils.ControllerUtils.showAlert;

public class SecuritiesUIController extends ControllerUtilsforButton {
    private SecuritiesAccountDBManager db = new SecuritiesAccountDBManager();

    @FXML
    private StackPane pageressui;

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

    private boolean checktextField(TextField str){
        return(str.getText() != null && !str.getText().isEmpty());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pstel.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                pstel.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        cptel.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                cptel.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        cptradertel.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                cptradertel.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        ccaccnb.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                ccaccnb.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        //
    }

    private void initTime(){
        LocalDate date1=LocalDate.now();
        date.setValue(date1);
    }

    private boolean personalcheck(){
        boolean check = false;
        if(!checktextField(psname)){
            message.setText("请输入你的名字");
            message.setVisible(true);
            return check;
        }

        if(!checktextField(psid)){
            message.setText("请输入你的身份证号");
            message.setVisible(true);
            return check;
        }
        if(psid.getText().length()!=18){
            message.setText("请输入正确的身份证号");
            message.setVisible(true);
            return check;
        }
        if(!checktextField(prof)){
            message.setVisible(true);
            message.setText("请输入你的职业");
            return check;
        }
        if(!checktextField(psaddr)){
            message.setVisible(true);
            message.setText("请输入你的地址");
            return check;
        }
        if(!checktextField(pstel)){
            message.setVisible(true);
            message.setText("请输入你的电话");
            return check;
        }
        if(!checktextField(psjob)){
            message.setVisible(true);
            message.setText("请输入你的工作");
            return check;
        }
        if(!checktextField(diplome)){
            message.setVisible(true);
            message.setText("请输入你的学历");
            return check;
        }
        if(!man.isSelected() && !women.isSelected()){
            message.setVisible(true);
            message.setText("请选择你的性别");
            return check;
        }
        return true;
    }

    @FXML
    public void personalregister() {

        if(!this.personalcheck()){
            return;
        }//todo还需验证账户是否存在以及删除等情况

        PersonalAccount temp = new PersonalAccount();
        if(!db.getPersonalAccount(psid.getText(), temp)) {
            if (db.getMsg().startsWith("数据库异常")) {
                showAlert(db.getMsg());
                return;
            }
        }
        if(db.getPersonalAccount(psid.getText(), temp)){
            showAlert("您已经注册过证券账户！");
            return;
        }

        boolean sex;
        if(man.isSelected()){
            sex=true;
        }else{
            sex=false;
        }

        if(checktextField(rppsid)){
            PersonalAccount account1= new PersonalAccount(java.sql.Date.valueOf(date.getValue()), psname.getText(), sex, psid.getText(), psaddr.getText(), prof.getText(), diplome.getText(), psjob.getText(), pstel.getText(), rppsid.getText());
            if(!db.newPersonalAccount(account1)){
                showAlert(db.getMsg());
                return;
            }
            if(!db.getPersonalAccount(psid.getText(), account1)){
                showAlert(db.getMsg());
                return;
            }
            showAlert("恭喜注册成功，"+"您的账号："+String.valueOf(account1.getSecuritiesId()));
            gotoMain();
        }else{
            PersonalAccount account1= new PersonalAccount(java.sql.Date.valueOf(date.getValue()), psname.getText(), sex, psid.getText(), psaddr.getText(), prof.getText(), diplome.getText(), psjob.getText(), pstel.getText());
            if(!db.newPersonalAccount(account1)){
                showAlert(db.getMsg());
                return;
            }
            if(!db.getPersonalAccount(psid.getText(), account1)){
                showAlert(db.getMsg());
                return;
            }
            showAlert1("恭喜注册成功，"+"您的账号："+String.valueOf(account1.getSecuritiesId()));

        }
    }


    @FXML
    public void cancelBtn(ActionEvent event) {
        pagepsrs.setVisible(false);
        pagechose.setVisible(true);
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
    private TextField cplicence;

    @FXML
    private TextField cprpid;

    public void AllClear(){
        cpid.clear();
        cptradername.clear();
        cprpid.clear();
        cplicence.clear();
        cpaddr.clear();
        cpname.clear();
        cptraderaddr.clear();
        cptraderid.clear();
        cptradertel.clear();
        cptel.clear();
        prof.clear();
        diplome.clear();
        psaddr.clear();
        psid.clear();
        psjob.clear();
        psname.clear();
        pstel.clear();
        rppsid.clear();
        man.setSelected(false);
        women.setSelected(false);
        ccidNb.clear();
        ccaccnb.clear();
        fridNb.clear();

    }

    public boolean companycheck(){
        if(!checktextField(cpid)){
            message1.setText("请输入法人注册登记号");
            message1.setVisible(true);
            return  false;
        }

        if(!checktextField(cplicence)){
            message1.setText("请输入营业执照编号");
            message1.setVisible(true);
            return false;
        }

        if(!checktextField(cprpid)){
            message1.setText("请输入法人身份证号");
            message1.setVisible(true);
            return false;
        }

        if(!checktextField(cptradername)){
            message1.setText("请输入授权人姓名");
            message1.setVisible(true);
            return false;
        }

        if(!checktextField(cptradertel)){
            message1.setText("请输入授权人电话");
            message1.setVisible(true);
            return false;
        }

        if(!checktextField(cpname)){
            message1.setText("请输入法人姓名");
            message1.setVisible(true);
            return false;
        }


        if(cpid.getText().length()!=9){
            message1.setText("请输入正确的法人注册登记号");
            message1.setVisible(true);
            return false;
        }

        if(!checktextField(cptel)){
            message1.setText("请输入法人联系电话");
            message1.setVisible(true);
            return false;
        }

        if(!checktextField(cpaddr)){
            message1.setText("请输入法人联系地址");
            message1.setVisible(true);
            return false;
        }

        if(!checktextField(cptraderid)){
            message1.setText("请输入授权人有效身份证号");
            message1.setVisible(true);
            return false;
        }

        if(cptraderid.getText().length()!=18){
            cptraderid.clear();
            message1.setText("请输入正确的授权人有效身份证号");
            message1.setVisible(true);
            return false;
        }

        if(!checktextField(cptraderaddr)){
            message1.setText("请输入授权人地址");
            message1.setVisible(true);
            return false;
        }

        if(cprpid.getText().length()!=18){
            message1.setText("请输入法人身份证号");
            message1.setVisible(true);
            return false;
        }
        return true;

    }

    @FXML
    void companyregister(ActionEvent event) {

        if(!companycheck()){
            return;
        }
        CorporateAccount temp = new CorporateAccount();
        if(!db.getCorporateAccount(cpid.getText(), temp)) {
            if (db.getMsg().startsWith("数据库异常")) {
                showAlert(db.getMsg());
                return;
            }
        }
        if(db.getCorporateAccount(cpid.getText(), temp)){
            showAlert("该法人注册账号已经注册过证券账户！");
            return ;
        }
        //todo还需验证账户存在等问题
        temp = new CorporateAccount(cpid.getText(),cplicence.getText(), cprpid.getText(),cpname.getText(), cptel.getText(),  cpaddr.getText(), cptradername.getText(), cptraderid.getText(), cptradertel.getText(), cptraderaddr.getText());
        if(!db.newCorporateAccount(temp)) {
            showAlert(db.getMsg());
            return;
        }
        if(!db.getCorporateAccount(cpid.getText(), temp)) {
            showAlert(db.getMsg());
            return;
        }
        showAlert1("恭喜注册成功"+"您的账号："+String.valueOf(temp.getSecuritiesId()));

    }

    @FXML
    void goback(ActionEvent event) {
        pagecprs.setVisible(false);
        pagechose.setVisible(true);
    }

    //选择注册的方向
    @FXML
    private Button cprs;

    @FXML
    private Button psrs;

    @FXML
    void jumppsrs() {
        btnRelease(psrs);
        this.initTime();
        pagepsrs.setVisible(true);
        pagers.setVisible(false);
        psressuieBtn.setVisible(false);
        psokbutton.setVisible(true);

    }

    @FXML
    void jumpcprs() {
        btnRelease(cprs);
        pagecprs.setVisible(true);
        pagers.setVisible(false);
        cpressuieBtn.setVisible(false);
        cpregisterBtn.setVisible(true);
    }


    //选择功能
    @FXML
    private Button rsBtn;

    @FXML
    private Button frBtn;

    @FXML
    private Button ccBtn;

    @FXML
    private Button ressuieBtn;

    @FXML
    void jumprs() {
        AllClear();
        btnRelease(rsBtn);
        pagechose.setVisible(false);
        pagers.setVisible(true);
    }

    @FXML
    void jumptofr() {
        AllClear();
        btnRelease(frBtn);
        pagechose.setVisible(false);
        pagefr.setVisible(true);
    }

    @FXML
    void jumptocc() {
        AllClear();
        btnRelease(ccBtn);
        pagechose.setVisible(false);
        pagecc.setVisible(true);
    }

    @FXML
    void jumptoressuie() {
        AllClear();
        btnRelease(ressuieBtn);
        pagechose.setVisible(false);
        pageressui.setVisible(true);
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
    private Label ccms;



    @FXML
    public void cancelok(ActionEvent event) {
        if(!checktextField(ccaccnb)){
            ccms.setText("请输入你的账户");
            ccms.setVisible(true);
            return;
        }

        if(!checktextField(ccidNb)){
            ccms.setText("请输入你的身份证号或法人注册号");
            ccms.setVisible(true);
            return;
        }
        if(ccidNb.getText().length()!=18&&ccidNb.getText().length()!=9){
            ccms.setText("请输入正确的身份证号或法人注册号");
            ccms.setVisible(true);
            return ;
        }

        int flag = 0;
        PersonalAccount personal_temp = new PersonalAccount();
        CorporateAccount corporate_temp = new CorporateAccount();
        if(!db.getSecuritiesStock(Integer.valueOf( ccaccnb.getText()))) {
            if (db.getMsg().startsWith("数据库异常")) {
                showAlert(db.getMsg());
                return;
            }
        }
        if(db.getSecuritiesStock(Integer.valueOf( ccaccnb.getText()))){
            showAlert("您有证劵未卖出，无法注销");
            return ;
        }

        if(!db.getCorporateAccount(ccidNb.getText(), corporate_temp)){
            flag = 1; // 法人账户不存在
        }else{
            if(corporate_temp.getState() == 1){
                showAlert("该账户目前已被冻结，请选择补办");
                return;
            }
            boolean delete_result = db.deleteCorporateAccount(ccidNb.getText());
            if(delete_result == false){
                showAlert(db.getMsg());
            }else{
                showAlert1("恭喜删除成功"+"您的账号："+String.valueOf(corporate_temp.getSecuritiesId()));
            }
            return ;
        }
        if(!db.getPersonalAccount(ccidNb.getText(), personal_temp)){
            flag = 2; // 个人账户不存在
        }else{
            if(personal_temp.getState() == 1){
                showAlert("该账户目前已被冻结，请选择补办");
                return;
            }
            boolean delete_result = db.deletePersonalAccount(ccidNb.getText());
            if(delete_result == false){
                showAlert(db.getMsg());
            }else{
                showAlert1("恭喜删除成功" +"您的账号："+String.valueOf(personal_temp.getSecuritiesId()));
            }
            return ;
        }


        if(flag == 1 || flag == 2){
            showAlert(db.getMsg());
            return;
        }
        //todo还需验证账户存在等问题

        //this.goToMessage("注销成功",account.getSecuritiesId());
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
        if(fridNb.getText().length()!=18&&fridNb.getText().length()!=9){
            frms.setText("请输入正确的身份证号或法人注册号");
            frms.setVisible(true);
            return ;
        }
        int flag = 0;
        PersonalAccount personal_temp = new PersonalAccount();
        CorporateAccount corporate_temp = new CorporateAccount();
        if(!db.getCorporateAccount(fridNb.getText(), corporate_temp)){
            flag = 1; // 法人账户不存在
        }else{
            db.modifyCorporateState(fridNb.getText(), 1);
            showAlert1("冻结成功"+"您的账号："+String.valueOf(corporate_temp.getSecuritiesId()));
            return ;
        }
        if(!db.getPersonalAccount(fridNb.getText(), personal_temp)){
            flag = 2; // 个人账户不存在
        }else{
            db.modifyPersonalState(fridNb.getText(), 1);
            showAlert1("冻结成功"+"您的账号："+String.valueOf(personal_temp.getSecuritiesId()));
            return ;
        }


        if(flag == 1 || flag == 2){
            showAlert(db.getMsg());
            return;
        }
        //todo


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

    public void setText1(String message5,String account1){
        this.msms.setText(message5);
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
    //补办

    @FXML
    private Button cprs1;

    @FXML
    private Button psrs1;

    @FXML
    private Button psressuieBtn;

    @FXML
    private Button cpressuieBtn;

    @FXML
    void jumppsrs1() {
        btnRelease(psrs1);
        this.initTime();
        pagepsrs.setVisible(true);
        pageressui.setVisible(false);
        psressuieBtn.setVisible(true);
        psokbutton.setVisible(false);

    }

    @FXML
    void jumpcprs1() {
        btnRelease(cprs1);
        pagecprs.setVisible(true);
        pageressui.setVisible(false);
        cpressuieBtn.setVisible(true);
        cpregisterBtn.setVisible(false);
    }

    @FXML
    void companyressiue(ActionEvent event){
        if(!companycheck()){
            return;
        }
        CorporateAccount account = new CorporateAccount();
        if(!db.getCorporateAccount(psid.getText(), account)) {
            if (db.getMsg().startsWith("数据库异常")) {
                showAlert(db.getMsg());
                return;
            }
        }
        account = new CorporateAccount(cpid.getText(),cplicence.getText(), cprpid.getText(),cpname.getText(), cptel.getText(),  cpaddr.getText(), cptradername.getText(), cptraderid.getText(), cptradertel.getText(), cptraderaddr.getText());
        CorporateAccount old_account = new CorporateAccount();
        if(!db.getCorporateAccount(account.getRegisterNo(), old_account)){
            showAlert("您还没有注册过证券账户！");
            return;
        }else{
            if(old_account.getState() == 0){
                showAlert("您的账户目前是正常状态，无须补办！");
                return;
            }else {
                db.deleteCorporateAccount(old_account.getRegisterNo());
                db.newCorporateAccount(account);
                CorporateAccount temp = new CorporateAccount();
                db.getCorporateAccount(account.getRegisterNo(), temp);
                db.modifySecuritiesFunds(old_account.getSecuritiesId(), temp.getSecuritiesId());
                showAlert1("恭喜补办成功"+"您的账号："+String.valueOf(temp.getSecuritiesId()));
            }
        }
        //todo
    }

    @FXML
    void psressuie(ActionEvent event){
        if(!personalcheck()){
            return;
        }

        boolean sex;
        if(man.isSelected()){
            sex=true;
        }else{
            sex=false;
        }

        int flag = 0;
        PersonalAccount account = new PersonalAccount();
        if(!db.getPersonalAccount(psid.getText(), account)) {
            if (db.getMsg().startsWith("数据库异常")) {
                showAlert(db.getMsg());
                return;
            }
        }
        if(checktextField(rppsid)){
            account= new PersonalAccount(java.sql.Date.valueOf(date.getValue()), psname.getText(), sex, psid.getText(), psaddr.getText(), prof.getText(), diplome.getText(), psjob.getText(), pstel.getText(), rppsid.getText());
            flag = 0;
        }else{
            account= new PersonalAccount(java.sql.Date.valueOf(date.getValue()), psname.getText(), sex, psid.getText(), psaddr.getText(), prof.getText(), diplome.getText(), psjob.getText(), pstel.getText());
            flag = 1;
        }
        PersonalAccount old_account = new PersonalAccount();
        if(!db.getPersonalAccount(account.getIdNo(), old_account)){
            showAlert("您还没有注册过证券账户！");
            return;
        }else{
            if(old_account.getState() == 0){
               showAlert("您的账户目前是正常状态，无须补办！");

                return;
            }else {
                db.deletePersonalAccount(old_account.getIdNo());
                db.newPersonalAccount(account);
                PersonalAccount temp = new PersonalAccount();
                db.getPersonalAccount(account.getIdNo(), temp);
                db.modifySecuritiesFunds(old_account.getSecuritiesId(), temp.getSecuritiesId());
                showAlert1("恭喜补办成功"+"您的账号："+String.valueOf(temp.getSecuritiesId()));
            }
        }

        //todo
    }
    //动画效果
    public void BtnMoved(MouseEvent event){  Object obj=event.getSource();
        if(obj instanceof Button){
            Button btn=(Button)obj;
            btnMove(btn);

        }
        return;
    }
    public void Btnpressed(MouseEvent event){Object obj=event.getSource();
        if(obj instanceof Button){
            Button btn=(Button)obj;
            btnPress(btn);
        }
        return;
    }
    public void BtnExited(MouseEvent event){Object obj=event.getSource();
        if(obj instanceof Button){
            Button btn=(Button)obj;
            btnRelease(btn);
        }}

    public void gotoMain(){
        pagechose.setVisible(true);
        pagefr.setVisible(false);
        pagecc.setVisible(false);
        pagepsrs.setVisible(false);
        pagecprs.setVisible(false);
    }
    public void showAlert1(String str){
        showAlert(str);
        gotoMain();
    }




}
