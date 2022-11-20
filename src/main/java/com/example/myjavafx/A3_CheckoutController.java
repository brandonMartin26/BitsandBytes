
package com.example.myjavafx;

import com.example.myjavafx.core.api.OrderApi;
import com.example.myjavafx.core.api.OrderApiImpl;
import com.example.myjavafx.core.models.PizzaRecord;
import com.example.myjavafx.core.models.enums.OrderStatus;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.example.myjavafx.A2_MenuController;

import java.net.URL;
import java.util.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;

import static com.example.myjavafx.core.models.PizzaUtils.pizzaRecordsToOrderList;

public class A3_CheckoutController implements Initializable {
    //FXML Variables
    @FXML
    private AnchorPane checkoutAnchor;
    @FXML
    private GridPane checkoutCartGridPane, asuIDPane;

    @FXML
    private ScrollPane checkoutCartScroll;
    @FXML
    private VBox checkoutCartVbox;
    @FXML
    private TextField asuIdField;
    @FXML
    private Label asuIdLabel, checkoutCartLabel, checkoutLabel, totalLabel;
    @FXML
    private Button backToMenuBtn, confirmOrderBtn;

    // Setting Variables
    private Stage stage;
    private Scene scene;
    private Parent root;

    // Order Variables
    private ActionEvent event;
    private String confirmedID;

    private CheckoutPayload checkoutOrder;
    private OrderApi api;

    public void setCheckoutOrder(CheckoutPayload payload) {
        this.checkoutOrder = payload;
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            //do stuff
            for (String pizza : pizzaRecordsToOrderList(checkoutOrder.pizzas)) {
                Label checkoutCartLabel = new Label();
                checkoutCartLabel.wrapTextProperty().setValue(true);
                checkoutCartLabel.setMaxWidth(100);
                checkoutCartLabel.setText(pizza);
                checkoutCartVbox.getChildren().add(checkoutCartLabel);
            }

        });

        backToMenuBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("b2_MenuView.fxml"));
                        Parent root = fxmlLoader.load();
                        A2_MenuController menuController = fxmlLoader.getController();
                        menuController.setOrder(checkoutOrder);
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

        confirmOrderBtn.setOnAction(e -> {
            // todo: check id is valid, else error message
            api = new OrderApiImpl();
            // todo: Scan "Users.txt" for ASU ID
            String fileName = "Database/users.txt";
            String asuID;
            Scanner scanner = null;
            try {
                scanner = new Scanner(Paths.get(fileName), StandardCharsets.UTF_8);
                scanner.useDelimiter(",");
                asuID = asuIdField.getText();
                String fileID;
                while(scanner.hasNextLine()) {
                    fileID = scanner.next();
                    if(asuID.equals(fileID)) {
                        confirmedID = asuID;
                        break;
                    } else {
                        scanner.nextLine();
                    }
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            //PRINT TEST:

            if(confirmedID.equals(asuID)) {
                // todo: send order to DB
                try {
                    api.addOrder(checkoutOrder.orderId,checkoutOrder.pizzas);
                    api.setOrderStatus(checkoutOrder.orderId, OrderStatus.CREATED);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                //  todo: move to updateView
                try {
                    FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("b5_ProcessUpdateView.fxml"));
                    root = fxmlloader.load();
                    A5_ProcessUpdateController processUpdateController = fxmlloader.getController();
                    processUpdateController.setCheckoutOrder(checkoutOrder);
                    stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid ASU ID");
                alert.setContentText("Please enter a valid ASU ID");
                alert.showAndWait();
            }

        });




    }
}
