package sample;


import cryptAlgrtm.CryptAlgrtm;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class Controller {

    @FXML
    private TableView<CL> cryptTable;
    @FXML
    private TableColumn<CL, String> cryptColumn;
    @FXML
    private Label keyLabel;
    @FXML
    private Label lenKeyLabel;
    @FXML
    private Label Label1;
    @FXML
    private Label Label2;
    @FXML
    private Label Label3;
    @FXML
    private Label outLabel;
    @FXML
    private TextField keyTextField;
    @FXML
    private TextField lenKeyTextField;
    @FXML
    private TextField TextField1;
    @FXML
    private TextField TextField2;
    @FXML
    private TextField TextField3;
    @FXML
    private TextField outTextField;



    private CryptAlgrtm cryptAlgrtm;
    private int num;


    private Main main;

    public Controller() {

    }

    private void selectRC5() {

        this.keyLabel.setVisible(true);
        this.lenKeyLabel.setVisible(true);
        this.Label1.setVisible(true);
        this.Label2.setVisible(true);
        this.keyLabel.setText("Введите ключ");
        this.lenKeyLabel.setText("Введите кол-во раундов");
        this.Label1.setText("Введите текст");
        this.Label2.setVisible(false);
        this.Label3.setVisible(false);

        this.keyTextField.setText("");
        this.lenKeyTextField.setText("");
        this.TextField1.setText("");
        this.keyTextField.setVisible(true);
        this.lenKeyTextField.setVisible(true);
        this.TextField1.setVisible(true);
        this.TextField2.setVisible(false);
        this.TextField3.setVisible(false);
        this.outTextField.setText("");
        num = 0;
    }

    private void selectRSA() {

        this.keyLabel.setVisible(true);
        this.lenKeyLabel.setVisible(true);
        this.Label1.setVisible(true);
        this.Label2.setVisible(true);
        this.keyLabel.setText("Введите публичный ключ");
        this.lenKeyLabel.setText("Введите размер ключа");
        this.Label1.setText("Введите приватный ключ");
        this.Label2.setText("Введите текст");
        this.Label3.setText("Введите mod");
        this.Label3.setVisible(true);

        this.keyTextField.setText("");
        this.lenKeyTextField.setText("");
        this.TextField1.setText("");
        this.TextField2.setText("");
        this.TextField3.setText("");
        this.keyTextField.setVisible(true);
        this.lenKeyTextField.setVisible(true);
        this.TextField1.setVisible(true);
        this.TextField2.setVisible(true);
        this.TextField3.setVisible(true);
        this.outTextField.setText("");

        num = 1;
    }

    private void selectMD5() {

        this.keyTextField.setVisible(true);
        this.keyLabel.setVisible(true);
        this.keyLabel.setText("Введите текст");
        this.lenKeyLabel.setVisible(false);
        this.Label1.setVisible(false);
        this.Label2.setVisible(false);
        this.Label3.setVisible(false);
        this.lenKeyTextField.setVisible(false);
        this.TextField1.setVisible(false);
        this.TextField2.setVisible(false);
        this.TextField3.setVisible(false);
        this.outTextField.setText("");
        this.keyTextField.setText("");


        num = 2;

    }

    private void selectDS() {

        this.keyLabel.setVisible(true);
        this.keyTextField.setVisible(true);
        this.keyLabel.setText("Введите текст");
        this.lenKeyLabel.setVisible(false);
        this.Label1.setVisible(false);
        this.Label2.setVisible(false);
        this.Label3.setVisible(false);
        this.lenKeyTextField.setVisible(false);
        this.TextField1.setVisible(false);
        this.TextField2.setVisible(false);
        this.TextField3.setVisible(false);
        this.outTextField.setText("");

        num = 3;
    }

    @FXML
    private void handleOK() {

        this.outLabel.setVisible(true);
        this.outTextField.setVisible(true);
        cryptAlgrtm = new CryptAlgrtm();
        String out = "";
        if (num == 0)
        {
            out = cryptAlgrtm.runRC5(TextField1.getText(), lenKeyTextField.getText(), keyTextField.getText());
        }
        if (num == 1)
        {
            out = cryptAlgrtm.runRSA(keyLabel.getText(), TextField1.getText(), lenKeyTextField.getText(), TextField3.getText(), TextField2.getText());
        }
        if (num == 2)
        {
            out = cryptAlgrtm.runMD5(keyTextField.getText());
        }
        if (num == 3)
        {
            out = cryptAlgrtm.runDS(keyTextField.getText());
        }

        outTextField.setText(out);
    }

    @FXML
    private void handleCancel() {

        Platform.exit();
    }


    @FXML
    private void initialize(){

        cryptColumn.setCellValueFactory(cellData -> cellData.getValue().getCryptName());

        cryptTable.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                        if (newValue.intValue() == 0)
                        {
                            selectRC5();
                        }
                        else if (newValue.intValue() == 1)
                        {
                            selectRSA();
                        }
                        else if (newValue.intValue() == 2)
                        {
                            selectMD5();
                        }
                        else
                        {
                            selectDS();
                        }
                    }
                }
        );


    }

    public void setTable(Main main) {

        this.main = main;
        cryptTable.setItems(main.getCryptName());

    }
}
