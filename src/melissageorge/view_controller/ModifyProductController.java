/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package melissageorge.view_controller;

import java.io.IOException;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import melissageorge.model.Inventory;
import melissageorge.model.Part;
import melissageorge.model.Product;

public class ModifyProductController {

    @FXML
    private TableView<Part> partsTable;

    @FXML
    private TableColumn<Part, Integer> partID;

    @FXML
    private TableColumn<Part, String> partName;

    @FXML
    private TableColumn<Part, Integer> partInv;

    @FXML
    private TableColumn<Part, Double> partPrice;

    @FXML
    private TableView<Part> relatedParts;

    @FXML
    private TableColumn<Part, Integer> relPartID;

    @FXML
    private TableColumn<Part, String> relPartName;

    @FXML
    private TableColumn<Part, Integer> relPartInv;

    @FXML
    private TableColumn<Part, Double> relPartPrice;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button saveButton;
    
    @FXML
    private TextField idField;
    
    @FXML
    private TextField nameField;
    
    @FXML
    private TextField invField;
    
    @FXML
    private TextField priceField;
    
    @FXML
    private TextField maxField;
    
    @FXML
    private TextField minField;

    @FXML
    private Button deleteButton;
    private ObservableList<Part> relatedPart;
    private boolean saveClicked= false;
    private Product product;
    
    public void setProductParts(Product product) {
        this.product = product;
        
        idField.setText(Integer.toString(product.getProductID()));
        nameField.setText(product.getName());
        invField.setText(Integer.toString(product.getInStock()));
        priceField.setText(Double.toString(product.getPrice()));
        minField.setText(Integer.toString(product.getMin()));
        maxField.setText(Integer.toString(product.getMax()));
        
        partID.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInv.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        partsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        partsTable.setItems(Inventory.getParts());
        partsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        partsTable.getSelectionModel().selectFirst();
        
        relPartID.setCellValueFactory(new PropertyValueFactory<>("partID"));
        relPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        relPartInv.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        relPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        
        relatedParts.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        relatedParts.setItems(product.getAssociatedParts());
        relatedParts.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    void AddButtonHandler(ActionEvent event) {
        Part part = partsTable.getSelectionModel().getSelectedItem();
        product.addAssociatedPart(part);
        relatedParts.setItems(product.getAssociatedParts());
    }

    @FXML
    void CancelButtonHandler(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Cancel modifying product");
        alert.setContentText("Are you sure you want to cancel? ");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Stage stage; 
            Parent root;       
            stage=(Stage) cancelButton.getScene().getWindow();
            //load up OTHER FXML document
            FXMLLoader loader=new FXMLLoader();
            root = FXMLLoader.load(getClass().getResource("Main.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    @FXML
    void DeleteButtonHandler(ActionEvent event) {
        Part part = relatedParts.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Delete part");
        alert.setContentText("Are you sure you want to delete " + part + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            product.removeAssociatedPart(part.getPartID());
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    @FXML
    void SaveButtonHandler(ActionEvent event) throws IOException {
        if(isInputValid()) {
            product.setName(nameField.getText());
            product.setInStock(Integer.parseInt(invField.getText()));
            product.setPrice(Double.parseDouble(priceField.getText()));
            product.setMax(Integer.parseInt(maxField.getText()));
            product.setMin(Integer.parseInt(minField.getText()));
            saveClicked = true;

            Stage stage; 
            Parent root;       
            stage=(Stage) saveButton.getScene().getWindow();
            //load up OTHER FXML document
            FXMLLoader loader=new FXMLLoader();
            root = FXMLLoader.load(getClass().getResource("Main.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    
    @FXML
    void SearchButtonHandler(ActionEvent event) {
        String searchItem=searchTextField.getText();
        boolean found=false;
        
        try {
            int itemNumber=Integer.parseInt(searchItem);
            Part part = Inventory.lookupPart(itemNumber);
            if (part != null) {
                found=true;
                partsTable.getSelectionModel().select(part);
                partsTable.scrollTo(part);
            }

        }
        catch (NumberFormatException nfe) {
                for(Part p: Inventory.allParts) {
                    if(p.getName().equals(searchItem)) {
                        found=true;
                        partsTable.getSelectionModel().select(p);
                    }
                }
            }    
            if (found==false){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Part not found");

            alert.showAndWait();
            }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid name!\n"; 
        }
        if (invField.getText() == null || invField.getText().length() == 0) {
            errorMessage += "No valid Inventory Stock!\n"; 
        } else {
            
            try {
                Integer.parseInt(invField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid Inventory (must be an integer number)!\n"; 
            }
        }

        if (priceField.getText() == null || priceField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n"; 
        } else {
            try {
                Double.parseDouble(priceField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid price (must be an decimal number)!\n"; 
            }
        }
        
        if (minField.getText() == null || minField.getText().length() == 0) {
            errorMessage += "No valid Minimun!\n"; 
        } else {
            try {
                Integer.parseInt(minField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid Minimum (must be an integer number)!\n"; 
            }
        }
        if (maxField.getText() == null || maxField.getText().length() == 0) {
            errorMessage += "No valid Maximum!\n"; 
        } else {
            try {
                Integer.parseInt(maxField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid Maximum (must be an integer number)!\n"; 
            }
        }
        
        if(product.getAssociatedParts().isEmpty()) {
            errorMessage += "Product must have associated part!\n"; 
        }
        
        if(errorMessage.length() == 0) {
            int inventory = Integer.parseInt(invField.getText());
            int min = Integer.parseInt(minField.getText());
            int max = Integer.parseInt(maxField.getText());
            if((inventory < min) || (inventory > max)) {
                errorMessage += "Inventory must be between Maximum and Minimum stock.\n";
            }
            if(max < min) {
                errorMessage += "Maximum stock must be greater than Minimum stock.\n";
            }
            double price = Double.parseDouble(priceField.getText());
            double partPrice = 0;
            for(Part p : product.getAssociatedParts()) {
                partPrice += p.getPrice();
            }
            if (price < partPrice) {
                errorMessage += "Price must exceed the price of all associated parts.";
            }
        }
        
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }

}

