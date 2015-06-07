package Marina.Malashenko.inb.ch.makery.adress.view;
import java.io.File;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import org.controlsfx.dialog.Dialogs;
import Marina.Malashenko.inb.ch.makery.adress.MMainAppM;

public class MRootLayoutControllerM {
	private MMainAppM mmainAppm;

    public void setMainApp(MMainAppM mainApp) {
        this.mmainAppm = mainApp;
    }

   
    @FXML
    private void mhandleNewM() {
        mmainAppm.getPersonData().clear();
        mmainAppm.msetPersonFilePathM(null);
    }

 
    @FXML
    private void mhandleOpenM() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(mmainAppm.mgetPrimaryStageM());

        if (file != null) {
            mmainAppm.mloadPersonDataFromFileM(file);
        }
    }

    @FXML
    private void mhandleSaveM() {
        File personFile = mmainAppm.mgetPersonFilePathM();
        if (personFile != null) {
            mmainAppm.savePersonDataToFile(personFile);
        } else {
            mhandleSaveAsM();
        }
    }

    @FXML
    private void mhandleSaveAsM() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(mmainAppm.mgetPrimaryStageM());

        if (file != null) {
        	
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mmainAppm.savePersonDataToFile(file);
        }
    }

    
    @FXML
    private void mhandleAboutM() {
        Dialogs.create()
            .title("AdressApp")
            .masthead("About")
            .message("Author: Malashenko Marina")
            .showInformation();
    }

 
    @FXML
    private void mhandleExitM() {
        System.exit(0);
    }
    
    @FXML
    private void mhandleShowBirthdayStatisticsM() {
      mmainAppm.mshowBirthdayStatisticsM();
    }
}
