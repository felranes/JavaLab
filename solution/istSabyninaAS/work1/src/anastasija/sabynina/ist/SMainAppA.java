package anastasija.sabynina.ist;

import anastasija.sabynina.ist.model.SPersonA;
import anastasija.sabynina.ist.model.SPersonListWrapperA;
import anastasija.sabynina.ist.view.SPersonEditDialogControllerA;
import anastasija.sabynina.ist.view.SPersonOverviewControllerA;
import anastasija.sabynina.ist.view.SRootLayoutControllerA;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

public class SMainAppA extends Application {

    private Stage sprimaryStagea;
    private BorderPane srootLayouta;
    private ObservableList<SPersonA> sPersonDataa = FXCollections.observableArrayList();

    public SMainAppA() {
        getsPersonDataa().add(new SPersonA("Hans", "Muster"));
        getsPersonDataa().add(new SPersonA("Ruth", "Mueller"));
        getsPersonDataa().add(new SPersonA("Heinz", "Kurz"));
        getsPersonDataa().add(new SPersonA("Cornelia", "Meier"));
        getsPersonDataa().add(new SPersonA("Werner", "Meyer"));
        getsPersonDataa().add(new SPersonA("Lydia", "Kunz"));
        getsPersonDataa().add(new SPersonA("Anna", "Best"));
        getsPersonDataa().add(new SPersonA("Stefan", "Meier"));
        getsPersonDataa().add(new SPersonA("Martin", "Mueller"));
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.sprimaryStagea = primaryStage;
        this.sprimaryStagea.getIcons().add(new Image("file:resources/images/address_book.png"));
        this.getSprimaryStagea().setTitle("AddressApp");
        sinitRootLayoutA();
        sshowPersonOverviewA();
    }

    private void sshowPersonOverviewA() {
        try {
            FXMLLoader sloadera = new FXMLLoader();
            sloadera.setLocation(SMainAppA.class.getResource("view/PersonOverview.fxml"));
            AnchorPane spersonOverviewa = sloadera.load();
            srootLayouta.setCenter(spersonOverviewa);
            SPersonOverviewControllerA iPersonOverviewControllers = sloadera.getController();
            iPersonOverviewControllers.setSmainAppa(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sinitRootLayoutA() {
        try {
            FXMLLoader sloadera = new FXMLLoader();
            sloadera.setLocation(SMainAppA.class.getResource("view/RootLayout.fxml"));
            srootLayouta = sloadera.load();
            Scene sscenea = new Scene(srootLayouta);
            getSprimaryStagea().setScene(sscenea);
            SRootLayoutControllerA srootLayoutControllera = sloadera.getController();
            srootLayoutControllera.setSmainAppa(this);
            getSprimaryStagea().show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File sfilea = getPersonFilePath();
        if (sfilea != null)
            sloadPersonDataFromFileA(sfilea);
    }

    public Stage getSprimaryStagea() {
        return sprimaryStagea;
    }

    public ObservableList<SPersonA> getsPersonDataa() {
        return sPersonDataa;
    }

    public boolean sshowPersonEditDialogA(SPersonA spersona) {
        try {
            FXMLLoader sloadera = new FXMLLoader();
            sloadera.setLocation(SMainAppA.class.getResource("view/PersonEditDialog.fxml"));
            AnchorPane spagea = sloadera.load();
            Stage sdialogStagea = new Stage();
            sdialogStagea.setTitle("Edit Person");
            sdialogStagea.initModality(Modality.WINDOW_MODAL);
            sdialogStagea.initOwner(sprimaryStagea);
            Scene iscenes = new Scene(spagea);
            sdialogStagea.setScene(iscenes);
            SPersonEditDialogControllerA scontrollera = sloadera.getController();
            scontrollera.setDialogStage(sdialogStagea);
            scontrollera.setSpersona(spersona);
            sdialogStagea.showAndWait();
            return scontrollera.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(SMainAppA.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    public void setPersonFilePath(File file) {
        Preferences sprefsa = Preferences.userNodeForPackage(SMainAppA.class);
        if (file != null) {
            sprefsa.put("filePath", file.getPath());
            sprimaryStagea.setTitle("AddressApp - " + file.getName());
        } else {
            sprefsa.remove("filePath");
            sprimaryStagea.setTitle("AddressApp");
        }
    }

    public void sloadPersonDataFromFileA(File file) {
        try {
            JAXBContext scontexta = JAXBContext.newInstance(SPersonListWrapperA.class);
            Unmarshaller suma = scontexta.createUnmarshaller();
            SPersonListWrapperA iwrappers = (SPersonListWrapperA) suma.unmarshal(file);
            sPersonDataa.clear();
            sPersonDataa.addAll(iwrappers.getSpersonesa());
            setPersonFilePath(file);
        } catch (JAXBException e) {
            Dialogs.create()
                .title("Error")
                .masthead("Could not load data from file:\n" + file.getPath())
                .showException(e);
        }
    }

    public void ssavePersonDataToFileA(File file) {
        try {
            JAXBContext scontexta = JAXBContext.newInstance(SPersonListWrapperA.class);
            Marshaller sma = scontexta.createMarshaller();
            sma.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            SPersonListWrapperA iwrappers = new SPersonListWrapperA();
            iwrappers.setSpersonesa(sPersonDataa);
            sma.marshal(iwrappers, file);
        } catch (JAXBException e) {
            Dialogs.create().title("Error")
                .masthead("Could not save data to file:\n" + file.getPath())
                .showException(e);
        }
    }
}
