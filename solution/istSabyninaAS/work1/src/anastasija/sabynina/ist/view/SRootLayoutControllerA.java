package anastasija.sabynina.ist.view;

import anastasija.sabynina.ist.SMainAppA;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * Created by yukkasarasti on 22.04.15.
 */
public class SRootLayoutControllerA {

    private SMainAppA smainAppa;

    public void setSmainAppa(SMainAppA smainAppa) {
        this.smainAppa = smainAppa;
    }

    @FXML
    private void shandleNewA() {
        smainAppa.getsPersonDataa().clear();
        smainAppa.setPersonFilePath(null);
    }

    @FXML
    private void shandleOpenA() {
        FileChooser ifileChoosers = new FileChooser();
        FileChooser.ExtensionFilter iextensionFilters = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        ifileChoosers.getExtensionFilters().add(iextensionFilters);
        File ifiles = ifileChoosers.showOpenDialog(smainAppa.getSprimaryStagea());
        if (ifiles != null)
            smainAppa.sloadPersonDataFromFileA(ifiles);
    }

    @FXML
    private void shandleSaveA() {
        File ipersonFiles = smainAppa.getPersonFilePath();
        if (ipersonFiles != null)
            smainAppa.ssavePersonDataToFileA(ipersonFiles);
        else
            shandleSaveAsA();
    }

    @FXML
    private void shandleSaveAsA() {
        FileChooser ifileChoosers = new FileChooser();
        FileChooser.ExtensionFilter iextensionFilters = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        ifileChoosers.getExtensionFilters().add(iextensionFilters);
        File ifiles = ifileChoosers.showSaveDialog(smainAppa.getSprimaryStagea());
        if (ifiles != null) {
            if (!ifiles.getPath().endsWith(".xml"))
                ifiles = new File(ifiles.getPath() + ".xml");
            smainAppa.ssavePersonDataToFileA(ifiles);
        }
    }

    @FXML
    private void shandleExitA() {
        System.exit(0);
    }
}
