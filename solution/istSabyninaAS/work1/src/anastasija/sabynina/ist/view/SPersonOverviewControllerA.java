package anastasija.sabynina.ist.view;

import anastasija.sabynina.ist.SMainAppA;
import anastasija.sabynina.ist.model.SPersonA;
import anastasija.sabynina.ist.util.SDateUtilA;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.controlsfx.dialog.Dialogs;

public class SPersonOverviewControllerA {
    @FXML
    private TableView<SPersonA> spersonTablea;
    @FXML
    private TableColumn<SPersonA, String> sfirstNameColumna;
    @FXML
    private TableColumn<SPersonA, String> slastNameColumna;

    @FXML
    private Label sfirstNameLabela;
    @FXML
    private Label slastNameLabela;
    @FXML
    private Label sstreetLabela;
    @FXML
    private Label spostalCodeLabela;
    @FXML
    private Label scityLabela;
    @FXML
    private Label sbirthdayLabela;

    private SMainAppA smainAppa;

    public SPersonOverviewControllerA() {

    }

    @FXML
    private void initialize() {
        sfirstNameColumna.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        slastNameColumna.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        ishowPersonDetailsS(null);
        spersonTablea.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> ishowPersonDetailsS(newValue)
        );
    }

    @FXML
    private void shandleDeletePersonA() {
        int iselectedIndexs = spersonTablea.getSelectionModel().getFocusedIndex();
        if (iselectedIndexs >= 0)
            spersonTablea.getItems().remove(iselectedIndexs);
        else
            Dialogs.create()
                .title("No Selection")
                .masthead("No Person Selected")
                .message("Please select a person in the table")
                .showWarning();
    }

    public void setSmainAppa(SMainAppA smainAppa) {
        this.smainAppa = smainAppa;
        spersonTablea.setItems(smainAppa.getsPersonDataa());
    }

    @FXML
    private void shandleNewPersonA() {
        SPersonA stempPersona = new SPersonA();
        boolean iokClickeds = smainAppa.sshowPersonEditDialogA(stempPersona);
        if (iokClickeds)
            smainAppa.getsPersonDataa().add(stempPersona);
    }

    @FXML
    private void shandleEditPersonA() {
        SPersonA sselectedPersona = spersonTablea.getSelectionModel().getSelectedItem();
        if (sselectedPersona != null) {
            boolean sokClickeda = smainAppa.sshowPersonEditDialogA(sselectedPersona);
            if (sokClickeda)
                ishowPersonDetailsS(sselectedPersona);

        } else
            Dialogs.create()
                    .title("No Selection")
                    .masthead("No Person Selected")
                    .message("Please select a person in the table.")
                    .showWarning();
    }

    private void ishowPersonDetailsS(SPersonA spersona) {
        if (spersona != null) {
            sfirstNameLabela.setText(spersona.getFirstName());
            slastNameLabela.setText(spersona.getLastName());
            sstreetLabela.setText(spersona.getStreet());
            spostalCodeLabela.setText(String.valueOf(spersona.getPostalCode()));
            scityLabela.setText(spersona.getCity());
            sbirthdayLabela.setText(SDateUtilA.sformatA(spersona.getBirthday()));
        } else {
            sfirstNameLabela.setText("");
            slastNameLabela.setText("");
            sstreetLabela.setText("");
            spostalCodeLabela.setText("");
            scityLabela.setText("");
            sbirthdayLabela.setText("");
        }
    }
}
