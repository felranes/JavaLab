package Marina.Malashenko.inb.ch.makery.adress.view;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.controlsfx.dialog.Dialogs;

import Marina.Malashenko.inb.ch.makery.adress.model.MPersonM;
import Marina.Malashenko.inb.ch.makery.adress.util.MDateUtilM;
public class MPersonEditDialogControllerM {
	
	    @FXML
	    private TextField firstNameField;
	    @FXML
	    private TextField lastNameField;
	    @FXML
	    private TextField streetField;
	    @FXML
	    private TextField postalCodeField;
	    @FXML
	    private TextField cityField;
	    @FXML
	    private TextField birthdayField;


	    private Stage mdialogStagem;
	    private MPersonM mpersonm;
	    private boolean mokClickedm = false;

	  
	    @FXML
	    private void initialize() {
	    }

	    public void setDialogStage(Stage dialogStage) {
	        this.mdialogStagem = dialogStage;
	    }

	    public void setPerson(MPersonM person) {
	        this.mpersonm = person;

	        firstNameField.setText(person.getFirstName());
	        lastNameField.setText(person.getLastName());
	        streetField.setText(person.getStreet());
	        postalCodeField.setText(Integer.toString(person.getPostalCode()));
	        cityField.setText(person.getCity());
	        birthdayField.setText(MDateUtilM.mformatM(person.getBirthday()));
	        birthdayField.setPromptText("dd.mm.yyyy");
	    }

	
	    public boolean isOkClicked() {
	        return mokClickedm;
	    }

	    @FXML
	    private void handleOk() {
	        if (misInputValidM()) {
	            mpersonm.setFirstName(firstNameField.getText());
	            mpersonm.setLastName(lastNameField.getText());
	            mpersonm.setStreet(streetField.getText());
	            mpersonm.setPostalCode(Integer.parseInt(postalCodeField.getText()));
	            mpersonm.setCity(cityField.getText());
	            mpersonm.setBirthday(MDateUtilM.mparseM(birthdayField.getText()));

	            mokClickedm = true;
	            mdialogStagem.close();
	        }
	    }

	    @FXML
	    private void handleCancel() {
	        mdialogStagem.close();
	    }

	   
	    private boolean misInputValidM() {
	        String errorMessage = "";

	        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
	            errorMessage += "No valid first name!\n"; 
	        }
	        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
	            errorMessage += "No valid last name!\n"; 
	        }
	        if (streetField.getText() == null || streetField.getText().length() == 0) {
	            errorMessage += "No valid street!\n"; 
	        }

	        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
	            errorMessage += "No valid postal code!\n"; 
	        } else {
	           
	            try {
	                Integer.parseInt(postalCodeField.getText());
	            } catch (NumberFormatException e) {
	                errorMessage += "No valid postal code (must be an integer)!\n"; 
	            }
	        }

	        if (cityField.getText() == null || cityField.getText().length() == 0) {
	            errorMessage += "No valid city!\n"; 
	        }

	        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
	            errorMessage += "No valid birthday!\n";
	        } else {
	            if (!MDateUtilM.mvalidDateM(birthdayField.getText())) {
	                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
	            }
	        }

	        if (errorMessage.length() == 0) {
	            return true;
	        } else {
	            Dialogs.create()
	                .title("Invalid Fields")
	                .masthead("Please correct invalid fields")
	                .message(errorMessage)
	                .showError();
	            return false;
	        }
	    }
	}
