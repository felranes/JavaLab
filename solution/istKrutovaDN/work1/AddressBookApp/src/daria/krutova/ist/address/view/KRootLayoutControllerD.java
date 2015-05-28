package daria.krutova.ist.address.view;


import java.io.File;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import org.controlsfx.dialog.Dialogs;

import daria.krutova.ist.address.KMainAppD;

/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 *
 * @author Marco Jakob
 */
public class KRootLayoutControllerD {

    // Reference to the main application
    private KMainAppD kMainAppd;

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param kMainAppd
     */
    public void kSetMainAppD(KMainAppD kMainAppd) {
        this.kMainAppd = kMainAppd;
    }

    /**
     * Creates an empty address book.
     */
    @FXML
    private void kHandleNewD() {
        kMainAppd.kGetPersonDataD().clear();
        kMainAppd.kSetPersonFilePathD(null);
    }

    /**
     * Opens a FileChooser to let the user select an address book to load.
     */
    @FXML
    private void kHandleOpenD() {
        FileChooser kFileChooserd = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter kExtFilterd = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        kFileChooserd.getExtensionFilters().add(kExtFilterd);

        // Show save file dialog
        File kFiled = kFileChooserd.showOpenDialog(kMainAppd.kGetPrimaryStageD());

        if (kFiled != null) {
            kMainAppd.loadPersonDataFromFile(kFiled);
        }
    }

    /**
     * Saves the file to the person file that is currently open. If there is no
     * open file, the "save as" dialog is shown.
     */
    @FXML
    private void kHandleSaveD() {
        File kPersonFileD = kMainAppd.kGetPersonFilePathD();
        if (kPersonFileD != null) {
            kMainAppd.kSavePersonDataToFileD(kPersonFileD);
        } else {
            kHandleSaveAsD();
        }
    }

    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void kHandleSaveAsD() {
        FileChooser kFileChooserD = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter kExtFilterD = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        kFileChooserD.getExtensionFilters().add(kExtFilterD);

        // Show save file dialog
        File kFiled = kFileChooserD.showSaveDialog(kMainAppd.kGetPrimaryStageD());

        if (kFiled != null) {
            // Make sure it has the correct extension
            if (!kFiled.getPath().endsWith(".xml")) {
                kFiled = new File(kFiled.getPath() + ".xml");
            }
            kMainAppd.kSavePersonDataToFileD(kFiled);
        }
    }

    /**
     * Opens an about dialog.
     */
    @FXML
    private void kHandleAboutD() {
        Dialogs.create()
                .title("AddressApp")
                .masthead("About")
                .message("Author: Marco Jakob\nWebsite: http://code.makery.ch")
                .showInformation();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void kHandleExitD() {
        System.exit(0);
    }

    /**
     * Opens the birthday statistics.
     */
    @FXML
    private void kHandleShowBirthdayStatisticsD() {
        kMainAppd.kShowBirthdayStatisticsD();
    }

}