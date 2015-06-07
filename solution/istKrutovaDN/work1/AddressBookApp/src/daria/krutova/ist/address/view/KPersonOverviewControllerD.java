package daria.krutova.ist.address.view;

import daria.krutova.ist.address.util.KDateUtilD;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import daria.krutova.ist.address.KMainAppD;
import daria.krutova.ist.address.model.KPersonD;
import org.controlsfx.dialog.Dialogs;

public class KPersonOverviewControllerD {
    @FXML
    private TableView<KPersonD> kPersonTabled;
    @FXML
    private TableColumn<KPersonD, String> kFirstNameColumnd;
    @FXML
    private TableColumn<KPersonD, String> kLastNameColumnd;

    @FXML
    private Label kFirstNameLabeld;
    @FXML
    private Label kLastNameLabeld;
    @FXML
    private Label kStreetLabeld;
    @FXML
    private Label kPostalCodeLabeld;
    @FXML
    private Label kCityLabeld;
    @FXML
    private Label kBirthdayLabeld;

    // Reference to the main application.
    private KMainAppD kMainAppd;

    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     *
     * @param kPersond the person or null
     */
    private void kShowPersonDetailsD(KPersonD kPersond) {
        if (kPersond != null) {
            // Fill the labels with info from the person object.
            kFirstNameLabeld.setText(kPersond.getFirstName());
            kLastNameLabeld.setText(kPersond.getLastName());
            kStreetLabeld.setText(kPersond.getStreet());
            kPostalCodeLabeld.setText(Integer.toString(kPersond.getPostalCode()));
            kCityLabeld.setText(kPersond.getCity());

            kBirthdayLabeld.setText(KDateUtilD.kFormatD(kPersond.getBirthday()));

        } else {
            // Person is null, remove all the text.
            kFirstNameLabeld.setText("");
            kLastNameLabeld.setText("");
            kStreetLabeld.setText("");
            kPostalCodeLabeld.setText("");
            kCityLabeld.setText("");
            kBirthdayLabeld.setText("");
        }
    }


    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public KPersonOverviewControllerD() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        kFirstNameColumnd.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        kLastNameColumnd.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        // Clear person details.
        kShowPersonDetailsD(null);

        // Listen for selection changes and show the person details when changed.
        kPersonTabled.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> kShowPersonDetailsD(newValue));
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void kHandleNewPersonD() {
        KPersonD kTempPersond = new KPersonD();
        boolean kOkClickedd = kMainAppd.kShowPersonEditDialogD(kTempPersond);
        if (kOkClickedd) {
            kMainAppd.kGetPersonDataD().add(kTempPersond);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void kHandleEditPersonD() {
        KPersonD kSelectedPersond = kPersonTabled.getSelectionModel().getSelectedItem();
        if (kSelectedPersond != null) {
            boolean kOkClickedd = kMainAppd.kShowPersonEditDialogD(kSelectedPersond);
            if (kOkClickedd) {
                kShowPersonDetailsD(kSelectedPersond);
            }

        } else {
            // Nothing selected.
            Dialogs.create()
                    .title("No Selection")
                    .masthead("No Person Selected")
                    .message("Please select a person in the table.")
                    .showWarning();
        }
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void kHhandleDeletePersonD() {
        int kSelectedIndexd = kPersonTabled.getSelectionModel().getSelectedIndex();
        if (kSelectedIndexd >= 0) {
            kPersonTabled.getItems().remove(kSelectedIndexd);
        } else {
            // Nothing selected.
            Dialogs.create()
                    .title("No Selection")
                    .masthead("No Person Selected")
                    .message("Please select a person in the table.")
                    .showWarning();
        }
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param kMainAppd
     */
    public void kSetMainAppD(KMainAppD kMainAppd) {
        this.kMainAppd = kMainAppd;

        // Add observable list data to the table
        kPersonTabled.setItems(kMainAppd.kGetPersonDataD());
    }
}