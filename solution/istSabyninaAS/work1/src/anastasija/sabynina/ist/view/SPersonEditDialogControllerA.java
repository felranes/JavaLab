package anastasija.sabynina.ist.view;

import anastasija.sabynina.ist.model.SPersonA;
import anastasija.sabynina.ist.util.SDateUtilA;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;

/**
 * Created by yukkasarasti on 22.04.15.
 */
public class SPersonEditDialogControllerA {

    @FXML
    private TextField sfirstNameFielda;
    @FXML
    private TextField slastNameFielda;
    @FXML
    private TextField sstreetFielda;
    @FXML
    private TextField spostalCodeFielda;
    @FXML
    private TextField scityFielda;
    @FXML
    private TextField sbirthdayFielda;

    private Stage sdialogStagea;
    private SPersonA spersona;
    private boolean sokClickeda;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.sdialogStagea = dialogStage;
    }


    public void setSpersona(SPersonA spersona) {
        this.spersona = spersona;
        sfirstNameFielda.setText(spersona.getFirstName());
        slastNameFielda.setText(spersona.getLastName());
        sstreetFielda.setText(spersona.getStreet());
        spostalCodeFielda.setText(String.valueOf(spersona.getPostalCode()));
        scityFielda.setText(spersona.getCity());
        sbirthdayFielda.setPromptText("dd.mm.yyyy");
    }

    public boolean isOkClicked() {
        return sokClickeda;
    }

    @FXML
    private void shandleOkA() {
        if (isInputValid()) {
            spersona.setFirstName(sfirstNameFielda.getText());
            spersona.setLastName(slastNameFielda.getText());
            spersona.setStreet(sstreetFielda.getText());
            spersona.setPostalCode(Integer.parseInt(spostalCodeFielda.getText()));
            spersona.setCity(scityFielda.getText());
            spersona.setBirthday(SDateUtilA.sparseA(sbirthdayFielda.getText()));
            sokClickeda = true;
            sdialogStagea.close();
        }
    }

    @FXML
    private void shandleCancelA() {
        sdialogStagea.close();
    }

    private boolean isInputValid() {
        String serrorMessagea = "";
        if (sfirstNameFielda.getText() == null || sfirstNameFielda.getText().length() == 0)
            serrorMessagea += "No valid first name!\n";
        if (slastNameFielda.getText() == null || slastNameFielda.getText().length() == 0)
            serrorMessagea += "No valid last name!\n";
        if (sstreetFielda.getText() == null || sstreetFielda.getText().length() == 0)
            serrorMessagea += "No valid street!\n";
        if (spostalCodeFielda.getText() == null || spostalCodeFielda.getText().length() == 0)
            serrorMessagea += "No valid postal code!\n";
        else
            try {
                Integer.parseInt(spostalCodeFielda.getText());
            } catch (NumberFormatException e) {
                serrorMessagea += "No valid postal code (must be an integer)!\n";
            }
        if (scityFielda.getText() == null || scityFielda.getText().length() == 0)
            serrorMessagea += "No valid city!\n";
        if (sbirthdayFielda.getText() == null || sbirthdayFielda.getText().length() == 0)
            serrorMessagea += "No valid birthday!\n";
        else
            if (!SDateUtilA.svalidDateA(sbirthdayFielda.getText()))
                serrorMessagea += "No valid birthday. Use the format dd.mm.yyyy!\n";
        if (serrorMessagea.length() == 0)
            return true;
        else {
            Dialogs.create()
                    .title("Invalid Fields")
                    .masthead("Please correct invalid fields")
                    .message(serrorMessagea)
                    .showError();
            return false;
        }
    }
}
