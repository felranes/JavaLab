package daria.krutova.ist.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.controlsfx.dialog.Dialogs;

import daria.krutova.ist.address.model.KPersonD;
import daria.krutova.ist.address.util.KDateUtilD;

/**
 * Dialog to edit details of a person.
 *
 * @author Marco Jakob
 */
public class KPersonEditDialogControllerD {

    @FXML
    private TextField kFirstNameFieldd;
    @FXML
    private TextField kLastNameFieldd;
    @FXML
    private TextField kStreetFieldd;
    @FXML
    private TextField kPostalCodeFieldd;
    @FXML
    private TextField kCityFieldd;
    @FXML
    private TextField kBbirthdayFieldd;


    private Stage kDialogStaged;
    private KPersonD kPersond;
    private boolean kOkClickedd = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param kDialogStaged
     */
    public void kSetDialogStageD(Stage kDialogStaged) {
        this.kDialogStaged = kDialogStaged;
    }

    /**
     * Sets the person to be edited in the dialog.
     *
     * @param kPersond
     */
    public void kSetPersonD(KPersonD kPersond) {
        this.kPersond = kPersond;

        kFirstNameFieldd.setText(kPersond.getFirstName());
        kLastNameFieldd.setText(kPersond.getLastName());
        kStreetFieldd.setText(kPersond.getStreet());
        kPostalCodeFieldd.setText(Integer.toString(kPersond.getPostalCode()));
        kCityFieldd.setText(kPersond.getCity());
        kBbirthdayFieldd.setText(KDateUtilD.kFormatD(kPersond.getBirthday()));
        kBbirthdayFieldd.setPromptText("dd.mm.yyyy");
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean kIsOkClickedD() {
        return kOkClickedd;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void kHandleOkD() {
        if (kIsInputValidD()) {
            kPersond.setFirstName(kFirstNameFieldd.getText());
            kPersond.setLastName(kLastNameFieldd.getText());
            kPersond.setStreet(kStreetFieldd.getText());
            kPersond.setPostalCode(Integer.parseInt(kPostalCodeFieldd.getText()));
            kPersond.setCity(kCityFieldd.getText());
            kPersond.setBirthday(KDateUtilD.kParseD(kBbirthdayFieldd.getText()));

            kOkClickedd = true;
            kDialogStaged.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void kHandleCancelD() {
        kDialogStaged.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean kIsInputValidD() {
        String kErrorMessaged = "";

        if (kFirstNameFieldd.getText() == null || kFirstNameFieldd.getText().length() == 0) {
            kErrorMessaged += "No valid first name!\n";
        }
        if (kLastNameFieldd.getText() == null || kLastNameFieldd.getText().length() == 0) {
            kErrorMessaged += "No valid last name!\n";
        }
        if (kStreetFieldd.getText() == null || kStreetFieldd.getText().length() == 0) {
            kErrorMessaged += "No valid street!\n";
        }

        if (kPostalCodeFieldd.getText() == null || kPostalCodeFieldd.getText().length() == 0) {
            kErrorMessaged += "No valid postal code!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(kPostalCodeFieldd.getText());
            } catch (NumberFormatException e) {
                kErrorMessaged += "No valid postal code (must be an integer)!\n";
            }
        }

        if (kCityFieldd.getText() == null || kCityFieldd.getText().length() == 0) {
            kErrorMessaged += "No valid city!\n";
        }

        if (kBbirthdayFieldd.getText() == null || kBbirthdayFieldd.getText().length() == 0) {
            kErrorMessaged += "No valid birthday!\n";
        } else {
            if (!KDateUtilD.kValidDateD(kBbirthdayFieldd.getText())) {
                kErrorMessaged += "No valid birthday. Use the format dd.mm.yyyy!\n";
            }
        }

        if (kErrorMessaged.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Dialogs.create()
                    .title("Invalid Fields")
                    .masthead("Please correct invalid fields")
                    .message(kErrorMessaged)
                    .showError();
            return false;
        }
    }
}