/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package melissageorge.view_controller;

import java.io.IOException;
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import melissageorge.model.Inhouse;
import melissageorge.model.Inventory;
import melissageorge.model.Outsourced;
import melissageorge.model.Part;

public class ModifyPartController {

    @FXML
    private RadioButton inhouseRadio;

    @FXML
    private RadioButton outsourcedRadio;

    @FXML
    private Label inOutLabel;

    @FXML
    private TextField idField;

    @FXML
    private TextField inOutField;

    @FXML
    private TextField maxField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField invField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField minField;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;
    
    @FXML 
    ToggleGroup togGroup;
    
    private Part part;
    private boolean saveClicked = false;
    
    public void setPart(Part part) {
        this.part = part;
        
        idField.setText(Integer.toString(part.getPartID()));
        nameField.setText(part.getName());
        invField.setText(Integer.toString(part.getInStock()));
        priceField.setText(Double.toString(part.getPrice()));
        minField.setText(Integer.toString(part.getMin()));
        maxField.setText(Integer.toString(part.getMax()));
        if(part instanceof Inhouse) {
            inhouseRadio.setSelected(true);
            inOutField.setText(Integer.toString(((Inhouse) part).getMachineID()));
        } else {
            inOutField.setText(((Outsourced) part).getCompanyName());
            outsourcedRadio.setSelected(true);
        }
       
    }

    @FXML
    void cancelButtonHandler(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Cancel modifying part");
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
    void idFieldHandler(ActionEvent event) {

    }

    @FXML
    void inOutFieldHandler(ActionEvent event) {

    }

    @FXML
    void inhouseButtonHandler(ActionEvent event) {
        inOutLabel.setText("Machine ID");
        inOutField.setPromptText("Mach ID");
    }

    @FXML
    void invFieldHandler(ActionEvent event) {

    }

    @FXML
    void maxFieldHandler(ActionEvent event) {

    }

    @FXML
    void minFieldHandler(ActionEvent event) {

    }

    @FXML
    void nameFieldHandler(ActionEvent event) {

    }

    @FXML
    void outsourcedButtonHandler(ActionEvent event) {
        inOutLabel.setText("Company Name");
        inOutField.setPromptText("Comp Name");
    }

    @FXML
    void priceFieldHandler(ActionEvent event) {

    }

    @FXML
    void saveButtonHandler(ActionEvent event) throws IOException {
        if(isInputValid()) {
            part.setName(nameField.getText());
            part.setInStock(Integer.parseInt(invField.getText()));
            part.setPrice(Double.parseDouble(priceField.getText()));
            part.setMax(Integer.parseInt(maxField.getText()));
            part.setMin(Integer.parseInt(minField.getText()));
            if(part instanceof Inhouse) {
                inOutField.setText(Integer.toString(((Inhouse) part).getMachineID()));
            } else if (part instanceof Outsourced) {
                inOutField.setText(((Outsourced) part).getCompanyName());
            }

            saveClicked = true;

            Stage stage; 
            Parent root;       
            stage=(Stage) saveButton.getScene().getWindow();
            //load up OTHER FXML document
            FXMLLoader loader=new FXMLLoader();
            root = loader.load(getClass().getResource("Main.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
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
            errorMessage += "No valid price code!\n"; 
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
        if(inhouseRadio.isSelected()) {
            if (inOutField.getText() == null || inOutField.getText().length() == 0) {
            errorMessage += "No valid Machine ID!\n"; 
            } else {
                try {
                    Integer.parseInt(inOutField.getText());
                } catch (NumberFormatException e) {
                    errorMessage += "No valid Machine ID (must be an integer number)!\n"; 
                }
            }
        }
        
        if(outsourcedRadio.isSelected()) {
            if (inOutField.getText() == null || inOutField.getText().length() == 0) {
            errorMessage += "No valid Company Name!\n"; 
            } 
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
                

