package Marina.Malashenko.inb.ch.makery.adress.view;

import org.controlsfx.dialog.Dialogs;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Marina.Malashenko.inb.ch.makery.adress.MMainAppM;
import Marina.Malashenko.inb.ch.makery.adress.model.MPersonM;
import Marina.Malashenko.inb.ch.makery.adress.util.MDateUtilM;

public class MPersonOverviewControllerM {
    @FXML
    private TableView<MPersonM> personTable;
    @FXML
    private TableColumn<MPersonM, String> firstNameColumn;
    @FXML
    private TableColumn<MPersonM, String> secondNameColumn;
    
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    private MMainAppM mmainAppm;

    public MPersonOverviewControllerM() {
    }

    @FXML
    private void initialize() {
    	
        firstNameColumn.setCellValueFactory(
        		cellData -> cellData.getValue().firstNameProperty());
        secondNameColumn.setCellValueFactory(
        		cellData -> cellData.getValue().lastNameProperty());        
       
        mshowPersonDetailsM(null);
        
		personTable .getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> mshowPersonDetailsM(newValue));
    }

   
    public void setMainApp(MMainAppM mainApp) {
        this.mmainAppm = mainApp;        
        personTable.setItems(mainApp.getPersonData());
    }
 
    private void mshowPersonDetailsM(MPersonM person) {
    	if (person != null) {
    		
    		firstNameLabel.setText(person.getFirstName());
    		lastNameLabel.setText(person.getLastName());
    		streetLabel.setText(person.getStreet());
    		postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
    		cityLabel.setText(person.getCity());
    		birthdayLabel.setText(MDateUtilM.mformatM(person.getBirthday()));
    		
    	} else {
    		
    		firstNameLabel.setText("");
    		lastNameLabel.setText("");
    		streetLabel.setText("");
    		postalCodeLabel.setText("");
    		cityLabel.setText("");
    		birthdayLabel.setText("");
    	}
    }
    
    @FXML
    private void mhandleDeletePersonM() {
        int mselectedIndexm = personTable.getSelectionModel().getSelectedIndex();
        if (mselectedIndexm >= 0) {
            personTable.getItems().remove(mselectedIndexm);
        } else {
        
            Dialogs.create().title("No Selection")
                .masthead("No Person Selected")
                .message("Please select a person in the table.")
                .showWarning();
        }
    }
    
    @FXML
	private void mhandleNewPersonM() {
		MPersonM tempPerson = new MPersonM();
		boolean okClicked = mmainAppm.showPersonEditDialog(tempPerson);
		if (okClicked) {
			mmainAppm.getPersonData().add(tempPerson);
		}
	}

	@FXML
	private void mhandleEditPersonM() {
		MPersonM selectedPerson = personTable.getSelectionModel()
				.getSelectedItem();
		if (selectedPerson != null) {
			boolean okClicked = mmainAppm.showPersonEditDialog(selectedPerson);
			if (okClicked) {
				mshowPersonDetailsM(selectedPerson);
			}

		} else {
		
			Dialogs.create().title("No Selection")
					.masthead("No Person Selected")
					.message("Please select a person in the table.")
					.showWarning();
		}
	}
}