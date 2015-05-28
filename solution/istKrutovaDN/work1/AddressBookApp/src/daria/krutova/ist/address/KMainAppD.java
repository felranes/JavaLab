package daria.krutova.ist.address;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import daria.krutova.ist.address.model.KPersonD;
import daria.krutova.ist.address.model.KPersonListWrapperD;
import daria.krutova.ist.address.view.KBirthdayStatisticsControllerD;
import daria.krutova.ist.address.view.KPersonEditDialogControllerD;
import daria.krutova.ist.address.view.KPersonOverviewControllerD;
import daria.krutova.ist.address.view.KRootLayoutControllerD;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class KMainAppD extends Application {

    private Stage kPrimaryStaged;
    private BorderPane kRootLayoutd;

    /**
     * The data as an observable list of Persons.
     */
    private ObservableList<KPersonD> kPersonDatad = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public KMainAppD() {
        // Add some sample data
        kPersonDatad.add(new KPersonD("Hans", "Muster"));
        kPersonDatad.add(new KPersonD("Ruth", "Mueller"));
        kPersonDatad.add(new KPersonD("Heinz", "Kurz"));
        kPersonDatad.add(new KPersonD("Cornelia", "Meier"));
        kPersonDatad.add(new KPersonD("Werner", "Meyer"));
        kPersonDatad.add(new KPersonD("Lydia", "Kunz"));
        kPersonDatad.add(new KPersonD("Anna", "Best"));
        kPersonDatad.add(new KPersonD("Stefan", "Meier"));
        kPersonDatad.add(new KPersonD("Martin", "Mueller"));
    }

    /**
     * Returns the data as an observable list of Persons.
     * @return
     */
    public ObservableList<KPersonD> kGetPersonDataD() {
        return kPersonDatad;
    }

    @Override
    public void start(Stage kPrimaryStaged) {
        this.kPrimaryStaged = kPrimaryStaged;

        this.kPrimaryStaged.setTitle("AddressApp");

        this.kPrimaryStaged.getIcons().add(new Image("file:resources/images/address_book_32.png"));

        kInitRootLayoutD();

        kShowPersonOverviewD();
    }

    /**
     * Initializes the root layout.
     */
    /**
     * Initializes the root layout and tries to load the last opened
     * person file.
     */
    public void kInitRootLayoutD() {
        try {
            // Load root layout from fxml file.
            FXMLLoader kLoaderd = new FXMLLoader();
            kLoaderd.setLocation(KMainAppD.class.getResource("view/KRootLayoutD.fxml"));
            kRootLayoutd = (BorderPane) kLoaderd.load();

            // Show the scene containing the root layout.
            kPrimaryStaged.setScene(new Scene(kRootLayoutd));

            // Give the controller access to the main app.
            KRootLayoutControllerD kControllerd = kLoaderd.getController();
            kControllerd.kSetMainAppD(this);

            kPrimaryStaged.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Try to load last opened person file.
        File file = kGetPersonFilePathD();
        if (file != null) {
            loadPersonDataFromFile(file);
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void kShowPersonOverviewD() {
        try {
            // Load person overview.
            FXMLLoader kLoaderd = new FXMLLoader();
            kLoaderd.setLocation(KMainAppD.class.getResource("view/KPersonOverviewD.fxml"));
            AnchorPane kPersonOverviewd = (AnchorPane) kLoaderd.load();

            // Set person overview into the center of root layout.
            kRootLayoutd.setCenter(kPersonOverviewd);

            // Give the controller access to the main app.
            KPersonOverviewControllerD kControllerd = kLoaderd.getController();
            kControllerd.kSetMainAppD(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     *
     * @param kPersond the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean kShowPersonEditDialogD(KPersonD kPersond) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader kLoaderd = new FXMLLoader();
            kLoaderd.setLocation(KMainAppD.class.getResource("view/KPersonEditDialogD.fxml"));
            AnchorPane page = (AnchorPane) kLoaderd.load();

            // Create the dialog Stage.
            Stage kDialogStaged = new Stage();
            kDialogStaged.setTitle("Edit Person");
            kDialogStaged.initModality(Modality.WINDOW_MODAL);
            kDialogStaged.initOwner(kPrimaryStaged);
            kDialogStaged.setScene(new Scene(page));

            // Set the person into the controller.
            KPersonEditDialogControllerD kControllerd = kLoaderd.getController();
            kControllerd.kSetDialogStageD(kDialogStaged);
            kControllerd.kSetPersonD(kPersond);

            // Show the dialog and wait until the user closes it
            kDialogStaged.showAndWait();

            return kControllerd.kIsOkClickedD();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Opens a dialog to show birthday statistics.
     */
    public void kShowBirthdayStatisticsD() {
        try {
            // Load the fxml file and create a new stage for the popup.
            FXMLLoader kLoaderd = new FXMLLoader();
            kLoaderd.setLocation(KMainAppD.class.getResource("view/KBirthdayStatisticsD.fxml"));
            AnchorPane kPaged = (AnchorPane) kLoaderd.load();
            Stage kDialogStaged = new Stage();
            kDialogStaged.setTitle("Birthday Statistics");
            kDialogStaged.initModality(Modality.WINDOW_MODAL);
            kDialogStaged.initOwner(kPrimaryStaged);
            kDialogStaged.setScene(new Scene(kPaged));

            // Set the persons into the controller.
            KBirthdayStatisticsControllerD kControllerd = kLoaderd.getController();
            kControllerd.setPersonData(kPersonDatad);

            kDialogStaged.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the person file preference, i.e. the file that was last opened.
     * The preference is read from the OS specific registry. If no such
     * preference can be found, null is returned.
     *
     * @return
     */
    public File kGetPersonFilePathD() {
        Preferences kPrefsd = Preferences.userNodeForPackage(KMainAppD.class);
        String kFilePathd = kPrefsd.get("filePath", null);
        if (kFilePathd != null) {
            return new File(kFilePathd);
        } else {
            return null;
        }
    }

    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     *
     * @param kFiled the file or null to remove the path
     */
    public void kSetPersonFilePathD(File kFiled) {
        Preferences kPrefsd = Preferences.userNodeForPackage(KMainAppD.class);
        if (kFiled != null) {
            kPrefsd.put("filePath", kFiled.getPath());

            // Update the stage title.
            kPrimaryStaged.setTitle("AddressApp - " + kFiled.getName());
        } else {
            kPrefsd.remove("filePath");

            // Update the stage title.
            kPrimaryStaged.setTitle("AddressApp");
        }
    }

    /**
     * Loads person data from the specified file. The current person data will
     * be replaced.
     *
     * @param kFiled
     */
    public void loadPersonDataFromFile(File kFiled) {
        try {
            JAXBContext kContextd = JAXBContext
                    .newInstance(KPersonListWrapperD.class);
            Unmarshaller um = kContextd.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            KPersonListWrapperD kWrapperd = (KPersonListWrapperD) um.unmarshal(kFiled);

            kPersonDatad.clear();
            kPersonDatad.addAll(kWrapperd.getPersons());

            // Save the file path to the registry.
            kSetPersonFilePathD(kFiled);

        } catch (Exception e) { // catches ANY exception
            Dialogs.create()
                    .title("Error")
                    .masthead("Could not load data from file:\n" + kFiled.getPath())
                    .showException(e);
        }
    }

    /**
     * Saves the current person data to the specified file.
     *
     * @param kFiled
     */
    public void kSavePersonDataToFileD(File kFiled) {
        try {
            JAXBContext kContextd = JAXBContext
                    .newInstance(KPersonListWrapperD.class);
            Marshaller m = kContextd.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            KPersonListWrapperD kWrapperd = new KPersonListWrapperD();
            kWrapperd.setPersons(kPersonDatad);

            // Marshalling and saving XML to the file.
            m.marshal(kWrapperd, kFiled);

            // Save the file path to the registry.
            kSetPersonFilePathD(kFiled);
        } catch (Exception e) { // catches ANY exception
            Dialogs.create().title("Error")
                    .masthead("Could not save data to file:\n" + kFiled.getPath())
                    .showException(e);
        }
    }


    /**
     * Returns the main stage.
     * @return
     */
    public Stage kGetPrimaryStageD() {
        return kPrimaryStaged;
    }

    public static void main(String[] args) {
        launch(args);
    }
}