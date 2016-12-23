package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    private BorderPane rootLayout;
    private Stage primaryStage;
    private ObservableList<CL> cryptName = FXCollections.observableArrayList();

    public Main() {
        this.cryptName.add(new CL("RC5"));
        this.cryptName.add(new CL("RSA"));
        this.cryptName.add(new CL("MD5"));
        this.cryptName.add(new CL("Digital signature"));
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("CryptAlg");

        rootLayout();
        showOverview();
    }

    public void rootLayout() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view\\Root.fxml"));
            rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void showOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view\\SelectCrypt.fxml"));
            AnchorPane personOverview = loader.load();
            rootLayout.setCenter(personOverview);
            Controller controller = loader.getController();
            controller.setTable(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<CL> getCryptName() {
        return cryptName;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
