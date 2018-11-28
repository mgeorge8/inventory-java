/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package melissageorge.view_controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;      
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import melissageorge.model.Inventory;
import melissageorge.model.Part;
import melissageorge.model.Product;
import melissageorge.model.Inhouse;
import melissageorge.model.Outsourced;
import melissageorge.MainApp;


/**
 * FXML Controller class
 *
 * @author melissageorge
 */
public class MainController {

    
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private SplitPane splitPane;

    @FXML
    private AnchorPane leftAnchorPane;

    @FXML
    private TableView<Part> partTable;

    @FXML
    private TableColumn<Part, Integer> partIDColumn;

    @FXML
    private TableColumn<Part, String> partNameColumn;

    @FXML
    private TableColumn<Part, Integer> partInvColumn;

    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    @FXML
    private Label partLabel;

    @FXML
    private Button partSearchButton;

    @FXML
    private Button addPartButton;

    @FXML
    private Button modifyPartButton;

    @FXML
    private Button removePartButton;

    @FXML
    private TextField partSearchText;

    @FXML
    private AnchorPane rightAnchorPane;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product, Integer> productIDColumn;

    @FXML
    private TableColumn<Product, String> productNameColumn;

    @FXML
    private TableColumn<Product, Integer> productInvColumn;

    @FXML
    private TableColumn<Product, Double> productPriceColumn;

    @FXML
    private Button productSearchButton;

    @FXML
    private Button removeProductButton;

    @FXML
    private Button modifyProductButton;

    @FXML
    private Button addProductButton;

    @FXML
    private TextField productSearchText;

    @FXML
    private Label inventoryLabel;

    @FXML
    private Button exitButton;

    @FXML
    private void addPartsHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent homePageParent = fxmlLoader.load(getClass().getResource("AddPart.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage addPartScene = (Stage) ((Node) event.getSource()).getScene().getWindow();
        addPartScene.setScene(homePageScene);
        addPartScene.show();
        
    }

    @FXML
    void addProductHandler(ActionEvent event) throws IOException {
        
        Stage stage; 
        Parent root;       
        stage=(Stage) addProductButton.getScene().getWindow();
        //load up OTHER FXML document
        FXMLLoader loader=new FXMLLoader(getClass().getResource(
               "AddProduct.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        AddProductController controller = loader.getController();
        controller.setParts();
    }

    @FXML
    void exitButtonHandler() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    void productSearchHandler() {
        String searchItem=productSearchText.getText();
        boolean found=false;
        
        try {
            int itemNumber=Integer.parseInt(searchItem);
            Product product = Inventory.lookupProduct(itemNumber);
            if (product != null) {
                found=true;
                productTable.getSelectionModel().select(product);
                productTable.scrollTo(product);
            }

        }
        catch (NumberFormatException nfe) {
                for(Product p: Inventory.products) {
                    if(p.getName().equals(searchItem)) {
                        found=true;
                        productTable.getSelectionModel().select(p);
                    }
                }
            }    
            if (found==false){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Product not found");

            alert.showAndWait();
            }
        }
    

    @FXML
    void handlePartSearchButton() {
        String searchItem=partSearchText.getText();
        boolean found=false;
        
        try {
            int itemNumber=Integer.parseInt(searchItem);
            Part part = Inventory.lookupPart(itemNumber);
            if (part != null) {
                found=true;
                partTable.getSelectionModel().select(part);
                partTable.scrollTo(part);
            }

        }
        catch (NumberFormatException nfe) {
                for(Part p: Inventory.allParts) {
                    if(p.getName().equals(searchItem)) {
                        found=true;
                        partTable.getSelectionModel().select(p);
                        partTable.scrollTo(p);
                    }
                }
            }    
            if (found==false){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Part not found");

            alert.showAndWait();
            }
        }
    

    @FXML
    void modifyPartHandler(ActionEvent event) throws IOException {
        Stage stage; 
        Parent root;       
        stage=(Stage) modifyPartButton.getScene().getWindow();
        //load up OTHER FXML document
        FXMLLoader loader=new FXMLLoader(getClass().getResource(
               "ModifyPart.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ModifyPartController controller = loader.getController();
        Part part=partTable.getSelectionModel().getSelectedItem();
        controller.setPart(part);
    }

    @FXML
    void modifyProductHandler() throws IOException {
        Stage stage; 
        Parent root;       
        stage=(Stage) modifyProductButton.getScene().getWindow();
        //load up OTHER FXML document
        FXMLLoader loader=new FXMLLoader(getClass().getResource(
               "ModifyProduct.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ModifyProductController controller = loader.getController();
        Product product=productTable.getSelectionModel().getSelectedItem();
        controller.setProductParts(product);
    }

   

    @FXML
    void removePartHandler() {
        Part part = partTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Delete part");
        alert.setContentText("Are you sure you want to delete " + part + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            int id = part.getPartID();
            Inventory.deletePart(id);
        } else {
            // ... user chose CANCEL or closed the dialog
        }
        
    }
    
    @FXML
    void removeProductHandler() {
        Product product = productTable.getSelectionModel().getSelectedItem();
        
        if(product.getAssociatedParts().size() > 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid process");
            alert.setHeaderText("Can't delete!");
            alert.setContentText("Products with associated parts can't be deleted.");
            
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Delete product");
            alert.setContentText("Are you sure you want to delete " + product + "?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                int id = product.getProductID();
                Inventory.removeProduct(id); 
            } else {
                // ... user chose CANCEL or closed the dialog
            }
            
        }
        
    }
    
    static boolean entered;
    
    public void initialize() {
        if(!entered){
            Part part1 = new Inhouse("Wheel", 3, 22.50, 0, 20, 4365);
            Part part2 = new Inhouse("Axel", 4, 13.50, 0, 15, 4388);
            Part part3 = new Outsourced("Pedal", 6, 8.75, 0, 30, "Parts.org");
           
            Inventory.addPart(part1);
            Inventory.addPart(part2);
            Inventory.addPart(part3);
            
            ObservableList<Part> associatedPart = FXCollections.observableArrayList();
//            associatedPart.add(part1);
//            associatedPart.add(part3);
            
            Inventory.addProduct(new Product(associatedPart, "Road Bike", 99.99, 1, 0, 4));
            associatedPart.add(part2);
            Inventory.addProduct(new Product(associatedPart, "Mountain Bike", 129.99, 2, 0, 3));
            entered=true;
        }
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        partTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        partTable.setItems(Inventory.getParts());
        partTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        partTable.getSelectionModel().selectFirst();
        
        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        productTable.setItems(Inventory.getProducts());
        productTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        productTable.getSelectionModel().selectFirst();
        
//        partIDColumn.setCellValueFactory(cellData->cellData.getValue().getPartIDProperty().asObject());
//        partNameColumn.setCellValueFactory(cellData->cellData.getValue().getPartNameProperty());
//        partInvColumn.setCellValueFactory(cellData->cellData.getValue().getStockProperty().asObject());
//        partPriceColumn.setCellValueFactory(cellData->cellData.getValue().getPartPriceProperty().asObject());
//        productIDColumn.setCellValueFactory(cellData->cellData.getValue().getProductIDProperty().asObject());
//        productNameColumn.setCellValueFactory(cellData->cellData.getValue().getProductNameProperty());
//        productInvColumn.setCellValueFactory(cellData->cellData.getValue().getProductStockProperty().asObject());
//        productPriceColumn.setCellValueFactory(cellData->cellData.getValue().getProductPriceProperty().asObject());
    }   
   
    
    
}
