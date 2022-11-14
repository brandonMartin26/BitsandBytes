package com.example.myjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class MenuController {

    private Stage stage;
    private Scene scene;
    private Parent root;


    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //BEGIN VARIABLES FOR MENU
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    static ArrayList<Order> orderList = OrderAPI.getOrderInfo();
    static Order newOrder = new Order();
    @FXML
    private Button addToOrderBtn;

    @FXML
    private RadioButton cheeseRadio;

    @FXML
    private CheckBox exCheeseCheckbox;

    @FXML
    private CheckBox mushCheckbox;

    @FXML
    private VBox cartVbox;
    @FXML
    private CheckBox olivesCheckbox;

    @FXML
    private CheckBox onionCheckbox;

    @FXML
    private RadioButton pepRadio;

    @FXML
    private ToggleGroup pizzaTypes;

    @FXML
    private RadioButton vegRadio;

    @FXML
    private GridPane cartGridPane;

    @FXML
    private Label cartLabel;

    @FXML
    private ScrollPane cartScroll;

    @FXML
    public Button checkoutBtn;

    @FXML
    private AnchorPane menuAnchor;

    @FXML
    private GridPane menuGridPane;

    @FXML
    private GridPane menuItemsGridPane;

    @FXML
    private Label menuLabel;

    @FXML
    private TextField searchField;

    @FXML
    private Label topLabel;
    @FXML
    private Button clearOrderBtn;
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //END VARIABLES FOR MENU

    //BEGIN VARIABLES FOR LOGIN
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @FXML
    private Button contAsGuestBtn;

    @FXML
    private AnchorPane loginAnchor;

    @FXML
    private Button loginBtn;

    @FXML
    private Label loginLabel;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label passwordLabel;

    @FXML
    private Button signUpBtn;
    @FXML
    private Label usernameLabel;

    @FXML
    private Label loginError;
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //END VARIABLES FOR LOGIN

    //START VARIABLES FOR SIGNUP
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @FXML
    private Button signUp;
    @FXML
    private Button backToLogin;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField asuID;
    @FXML
    private TextField asuEmail;
    @FXML
    private TextField password;
    @FXML
    private Label signUpError;
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //END VARIABLES FOR SIGNUP

    //START VARIABLES FOR CHECKOUT
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @FXML
    private GridPane asuIDPane;

    @FXML
    private VBox checkoutCartVbox;

    @FXML
    private TextField asuIdField;

    @FXML
    private Label asuIdLabel;

    @FXML
    private Button backToMenuBtn;

    @FXML
    private AnchorPane checkoutAnchor;

    @FXML
    private GridPane checkoutCartGridPane;

    @FXML
    private Label checkoutCartLabel;

    @FXML
    private ScrollPane checkoutCartScroll;

    @FXML
    private Label checkoutLabel;

    @FXML
    private Button confirmOrderBtn;

    @FXML
    private Label totalLabel;

    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //END VARIABLES FOR CHECKOUT

    //START VARIABLES FOR PROCESSING
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @FXML
    private Label yourOrderIsLabel;
    @FXML
    private Label acceptedLabel;
    @FXML
    private Label readyToCookLabel;
    @FXML
    private Label cookingLabel;

    @FXML
    private Button processLoadOrdersButton;

    @FXML
    private Label readyLabel;

    @FXML
    private TextArea processingTextArea;

    @FXML
    private Button Label1;
    @FXML
    private Button Label2;
    @FXML
    private Button Label3;
    @FXML
    private Button Label4;
    @FXML
    private Button refreshOrder;
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //END VARIABLES FOR PROCESSING

    //START VARIABLES FOR CHEF VIEW
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @FXML
    private Button acceptOrderButton;

    @FXML
    private RadioButton showOrder;

    @FXML
    private RadioButton selectOrder;

    @FXML
    private AnchorPane chefViewAnchor;

    @FXML
    private VBox displayOrder;

    @FXML
    private Button startOrderButton;
    @FXML
    private Button editOrderButton;

    @FXML
    private Button completeOrderButton;

    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //END VARIABLES FOR CHEF VIEW



    public void switchToMemberLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("memberLogin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToProcessingAgent(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("processAgentView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSignUp(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("signUp.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("bitsandbytes.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCheckout(ActionEvent event) throws IOException {
        System.out.println(newOrder.toString());
        newOrder.status = 0;
        orderList.add(newOrder);
        root = FXMLLoader.load(getClass().getResource("checkoutPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToProcessing(ActionEvent event) throws IOException {
        //newOrder.status = 0;
        //orderList.add(newOrder);
        root = FXMLLoader.load(getClass().getResource("processingPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToChefView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("chefView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addToOrder(ActionEvent event) throws IOException {
        String toppings = "";
        String type = "";
        double price = 0.00;
        if(pepRadio.isSelected()){
            type = "Pepperoni";
            price += 10.00;
        }
        else if(vegRadio.isSelected()){
            type = "Vegetable";
            price += 12.00;
        }
        else if(cheeseRadio.isSelected()){
            type = "Cheese";
            price += 9.00;
        }
        if(mushCheckbox.isSelected()){
            toppings += "Mushroom^";
            price += 1.50;
        }
        if(onionCheckbox.isSelected()){
            toppings += "Onion^";
            price += 1.50;
        }
        if(olivesCheckbox.isSelected()){
            toppings += "Olives^";
            price += 1.50;
        }
        if(exCheeseCheckbox.isSelected()){
            toppings += "ExtraCheese^";
            price += 1.50;
        }
        //toppings = toppings.substring(0,(toppings.length()));
        Item newItem = new Item(type, toppings, price);
        newItem.setPizzaType(type);
        newOrder.addToOrder(new CartItem(newItem, 1));

        System.out.println("ADDED NEW ITEM: " + type + ", " + toppings + ", " + price);
        updateCart(newItem);
    }

    public void updateCart(Item newItem){
        /*String tempOrder = newItem.toString();
        Label orderLabel = new Label();
        int iend = tempOrder.indexOf(";");
        String pizzaType = tempOrder.substring(0, iend);
        tempOrder = tempOrder.replace("" + pizzaType + ";", "");
        int iend2 = tempOrder.indexOf(";");
        tempOrder = tempOrder.replace("^", "\n\t");
        String toppings = tempOrder.substring(0, iend2+1);
        tempOrder = tempOrder.replace("" + toppings + ";", "");
        orderLabel.setText("" + pizzaType + "\n\t" + toppings + "\n");
        cartVbox.getChildren().add(orderLabel);*/
        populateCheckoutCart();
    }

    public void populateCheckoutCart(){
        cartVbox.getChildren().clear();
        Boolean moreOrders = true;
        String tempOrder = newOrder.toString();
        while(moreOrders) {
            Label orderLabel = new Label();
            int iend = tempOrder.indexOf(";");
            String pizzaType = tempOrder.substring(0,iend);
            tempOrder = tempOrder.replaceFirst("" + pizzaType + ";", "");
            int iend2 = tempOrder.indexOf(";");
            String toppings = tempOrder.substring(0, iend2 + 1);
            toppings = toppings.replace("^", "\n\t");
            toppings = toppings.replace(";", "--------------------");
            tempOrder = tempOrder.replaceFirst("" + toppings + ";", "");
            int iend3 = tempOrder.indexOf("/");
            if(iend3 == -1){
                moreOrders = false;
                tempOrder = tempOrder.replace(tempOrder, "");
            }
            else{
                tempOrder = tempOrder.replace(tempOrder.substring(0,iend3+1), "");
            }
            orderLabel.setText("" + pizzaType + "\n\t" + toppings + "\n");
            cartVbox.getChildren().add(orderLabel);

        }

    }

    public  void clearVBoxCart(ActionEvent event) throws IOException
    {
        newOrder.clearOrder();
        cartVbox.getChildren().clear();
    }

    public void populateFinalCheckoutCart(){
        checkoutCartVbox.getChildren().clear();
        Boolean moreOrders = true;
        String tempOrder = newOrder.toString();
        while(moreOrders) {
            Label orderLabel = new Label();
            int iend = tempOrder.indexOf(";");
            String pizzaType = tempOrder.substring(0, iend);
            tempOrder = tempOrder.replaceFirst("" + pizzaType + ";", "");
            int iend2 = tempOrder.indexOf(";");
            String toppings = tempOrder.substring(0, iend2 + 1);
            toppings = toppings.replace("^", "\n\t");
            toppings = toppings.replace(";", "--------------------");
            tempOrder = tempOrder.replaceFirst("" + toppings + ";", "");
            int iend3 = tempOrder.indexOf("/");
            if(iend3 == -1){
                moreOrders = false;
                tempOrder = tempOrder.replace(tempOrder, "");
            }
            else{
                tempOrder = tempOrder.replace(tempOrder.substring(0,iend3+1), "");
            }
            orderLabel.setText("" + pizzaType + "\n\t" + toppings + "\n");
            checkoutCartVbox.getChildren().add(orderLabel);
        }

    }

    // Brandon Signup ***********************************************
    public void signUpSave(ActionEvent event) throws IOException{
        if(firstName.getText().isEmpty()){ signUpError.setText("Please enter First Name!"); }
        else if(lastName.getText().isEmpty()){ signUpError.setText("Please Enter Last Name!"); }
        else if(asuID.getText().isEmpty()){ signUpError.setText("Please Enter ASU ID!"); }
        else if(asuEmail.getText().isEmpty()){ signUpError.setText("Please Enter ASU Email!"); }
        else if(password.getText().isEmpty()){ signUpError.setText("Please Enter ASU Password!"); }
        else {
            String fileName = "Database/users.txt";
            Scanner scanner = new Scanner(Paths.get(fileName), StandardCharsets.UTF_8.name());
            String data = scanner.useDelimiter("\\A").next();
            scanner.close();
            List<String> ids = Arrays.stream(data.split("\n")).map(line -> line.split(",")[0].trim()).collect(Collectors.toList());
            List<String> emails = Arrays.stream(data.split("\n")).map(line -> line.split(",")[1].trim()).collect(Collectors.toList());
            List<String> pass = Arrays.stream(data.split("\n")).map(line -> line.split(",")[2].trim()).collect(Collectors.toList());

            for (int i = 0; i < ids.size(); i++) {
                if (ids.contains(asuID.getText())) { signUpError.setText("ASU ID already in use, please try again."); asuID.clear();}
                else if(emails.contains(asuEmail.getText())){ signUpError.setText("ASU EMAIL already in use, please try again."); asuEmail.clear();}
                else if(pass.contains(password.getText())){ signUpError.setText("ASU PASSWORD already in use, please try again."); password.clear();}
                else
                {
                    String text = asuID.getText() + "," +
                            asuEmail.getText() + "," +
                            password.getText() + "," +
                            firstName.getText() + "," +
                            lastName.getText();
                    BufferedWriter buf = new BufferedWriter(new FileWriter(fileName, true));
                    buf.write(text + "\n");
                    buf.close();
                    signUpError.setText("You have successfully signed up!");

                    firstName.clear(); lastName.clear(); asuID.clear(); asuEmail.clear(); password.clear();
                    //***possible add, return user to the login page
                }
                break;
            }
        }
    }// END "signUpSave(ActionEvent event) throws IOException"


    public void LoginHandler(ActionEvent event) throws IOException {
        if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty())
        {
            loginError.setText("Please make sure all fields are full!");
        }
        String fileName = "Database/users.txt";
        Scanner scanner = new Scanner(Paths.get(fileName), StandardCharsets.UTF_8.name());
        scanner.useDelimiter(",");
        String username = usernameField.getText();
        String password = passwordField.getText();
        String fileID;
        String fileEmail;
        String filePassword;

        while(scanner.hasNextLine())
        {
            fileID = scanner.next();
            fileEmail = scanner.next();
            filePassword = scanner.next();
            loginError.setText(fileEmail);

            if(username.equals(fileEmail)) //can change to fileID to login with ID instead of email
            {
                if(password.equals(filePassword))
                {
                    //can pass username to elsewhere here, if needed for other parts
                    switchToCheckout(event);

                } else {
                    loginError.setText("Invalid username or password");
                    break;
                }
            }
            scanner.nextLine();
        }
        loginError.setText("Invalid username or password");
    }


    //Omar Processing Page********************************
    public void makeLabel1Green(ActionEvent event) throws IOException {
        acceptedLabel.setStyle("-fx-background-color: #3E872B;");
    }
    public void makeLabel2Green(ActionEvent event) throws IOException {
        readyToCookLabel.setStyle("-fx-background-color: #3E872B;");
    }
    public void makeLabel3Green(ActionEvent event) throws IOException {
        cookingLabel.setStyle("-fx-background-color: #3E872B;");
    }
    public void makeLabel4Green(ActionEvent event) throws IOException {
        readyLabel.setStyle("-fx-background-color: #3E872B;");
    }

    public void startOrderHandler(ActionEvent event) throws IOException {
        orderList.get(0).status = 2;
    }

    public void completeOrderHandler(ActionEvent event) throws IOException {
        orderList.get(0).status = 3;
    }

    public void updateChefView(ActionEvent event) throws IOException {
        Boolean moreOrders = true;
        String tempOrder = newOrder.toString();
        while(moreOrders) {
            Label orderLabel = new Label();
            int iend = tempOrder.indexOf(";");
            String pizzaType = tempOrder.substring(0, iend);
            tempOrder = tempOrder.replaceFirst("" + pizzaType + ";", "");
            int iend2 = tempOrder.indexOf(";");
            String toppings = tempOrder.substring(0, iend2 + 1);
            toppings = toppings.replace("^", "\n\t");
            toppings = toppings.replace(";", "--------------------");
            tempOrder = tempOrder.replaceFirst("" + toppings + ";", "");
            int iend3 = tempOrder.indexOf("/");
            if(iend3 == -1){
                moreOrders = false;
                tempOrder = tempOrder.replace(tempOrder, "");
            }
            else{
                tempOrder = tempOrder.replace(tempOrder.substring(0,iend3+1), "");
            }
            orderLabel.setText("" + pizzaType + "\n\t" + toppings + "\n");
            displayOrder.getChildren().add(orderLabel);
        }
    }

    /*public void selectOrderButtonPress(ActionEvent event) throws IOException {
        int i = 0;
        Order currentOrder = orderList.get(i);
        currentOrder.status = 1;
        //mess with order here
    }*/

    public void processingAgentAccept(ActionEvent event) throws IOException {
        orderList.get(0).status = 1;
        processingTextArea.clear();
    }
    public void processingViewOrder(ActionEvent event) throws IOException {
        processingTextArea.clear();
        Boolean moreOrders = true;
        String tempOrder = newOrder.toString();
        while(moreOrders) {
            Label orderLabel = new Label();
            int iend = tempOrder.indexOf(";");
            String pizzaType = tempOrder.substring(0, iend);
            tempOrder = tempOrder.replaceFirst("" + pizzaType + ";", "");
            int iend2 = tempOrder.indexOf(";");
            String toppings = tempOrder.substring(0, iend2 + 1);
            toppings = toppings.replace("^", "\n\t");
            toppings = toppings.replace(";", "--------------------");
            tempOrder = tempOrder.replaceFirst("" + toppings + ";", "");
            int iend3 = tempOrder.indexOf("/");
            if(iend3 == -1){
                moreOrders = false;
                tempOrder = tempOrder.replace(tempOrder, "");
            }
            else{
                tempOrder = tempOrder.replace(tempOrder.substring(0,iend3+1), "");
            }
            processingTextArea.setText("" + pizzaType + "\n\t" + toppings + "\n");
        }
    }

    public void refreshStatus(ActionEvent event) throws InterruptedException {
        int temp = orderList.get(0).status;
        System.out.println(orderList.get(0).status);
        if(temp == 1){
            acceptedLabel.setStyle("-fx-background-color: #3E872B;");
            //Thread.sleep(5000);
            readyToCookLabel.setStyle("-fx-background-color: #3E872B;");
        }
        if(temp == 2){
            acceptedLabel.setStyle("-fx-background-color: #3E872B;");
            readyToCookLabel.setStyle("-fx-background-color: #3E872B;");
            cookingLabel.setStyle("-fx-background-color: #3E872B;");
        }
        if(temp == 3){
            acceptedLabel.setStyle("-fx-background-color: #3E872B;");
            readyToCookLabel.setStyle("-fx-background-color: #3E872B;");
            cookingLabel.setStyle("-fx-background-color: #3E872B;");
            readyLabel.setStyle("-fx-background-color: #3E872B;");
        }
    }
}


