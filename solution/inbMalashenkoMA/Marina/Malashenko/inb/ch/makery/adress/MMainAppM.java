package Marina.Malashenko.inb.ch.makery.adress;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.File;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import Marina.Malashenko.inb.ch.makery.adress.model.MPersonListWrapperM;
import Marina.Malashenko.inb.ch.makery.adress.model.MPersonM;
import Marina.Malashenko.inb.ch.makery.adress.view.MBirthdayStatisticsControllerM;
import Marina.Malashenko.inb.ch.makery.adress.view.MPersonEditDialogControllerM;
import Marina.Malashenko.inb.ch.makery.adress.view.MPersonOverviewControllerM;
import Marina.Malashenko.inb.ch.makery.adress.view.MRootLayoutControllerM;

import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.controlsfx.dialog.Dialogs;

public class MMainAppM extends Application {

	
	 private Stage mprimaryStagem;
	 private BorderPane mrootLayoutm;
	 private ObservableList<MPersonM> mpersonDatam = FXCollections.observableArrayList();
	  
	 public MMainAppM() {
			
			mpersonDatam.add(new MPersonM("Hans", "Muster"));
			mpersonDatam.add(new MPersonM("Ruth", "Mueller"));
			mpersonDatam.add(new MPersonM("Heinz", "Kurz"));
			mpersonDatam.add(new MPersonM("Cornelia", "Meier"));
			mpersonDatam.add(new MPersonM("Werner", "Meyer"));
			mpersonDatam.add(new MPersonM("Lydia", "Kunz"));
			mpersonDatam.add(new MPersonM("Anna", "Best"));
			mpersonDatam.add(new MPersonM("Stefan", "Meier"));
			mpersonDatam.add(new MPersonM("Martin", "Mueller"));
					}

	 public ObservableList<MPersonM> getPersonData() {
			return mpersonDatam;
		}
	 
	 @Override
	    public void start(Stage primaryStage) {
	        this.mprimaryStagem = primaryStage;
	        this.mprimaryStagem.setTitle("AdressApp");
	        this.mprimaryStagem.getIcons().add(new Image("file:resources/images/address_book_32.png"));
	        minitRootLayoutM();
	        mshowPersonOverviewM();

	    }

	    
	    public void minitRootLayoutM() {
	    	 try {
	    	       
	    	        FXMLLoader loader = new FXMLLoader();
	    	        loader.setLocation(MMainAppM.class
	    	                .getResource("view/RootLayoute.fxml"));
	    	        mrootLayoutm = (BorderPane) loader.load();

	    	        
	    	        Scene scene = new Scene(mrootLayoutm);
	    	        mprimaryStagem.setScene(scene);

	    	     
	    	        MRootLayoutControllerM controller = loader.getController();
	    	        controller.setMainApp(this);

	    	        mprimaryStagem.show();
	    	    } catch (IOException e) {
	    	        e.printStackTrace();
	    	    }

	    	    File file = mgetPersonFilePathM();
	    	    if (file != null) {
	    	        mloadPersonDataFromFileM(file);
	    	    }
	        }

	   
	    public void mshowPersonOverviewM() {
	        try {
	            FXMLLoader mloaderm = new FXMLLoader();
	            mloaderm.setLocation(MMainAppM.class.getResource("view/PersonOverviev.fxml"));
	           AnchorPane mpersonOverviewm = (AnchorPane) mloaderm.load();

	            mrootLayoutm.setCenter(mpersonOverviewm);
	            
	            MPersonOverviewControllerM controller = mloaderm.getController();
	            controller.setMainApp(this);

	            mprimaryStagem.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public Stage mgetPrimaryStageM() {
	        return mprimaryStagem;
	    }
	      
	    public static void main(String[] args) {
	        launch(args);
	    
	}
	    
	    
	    public boolean showPersonEditDialog(MPersonM person) {
	        try {
	            
	            FXMLLoader mloaderm = new FXMLLoader();
	            mloaderm.setLocation(MMainAppM.class.getResource("view/PersonEditDialogs.fxml"));
	            AnchorPane mpagem = (AnchorPane) mloaderm.load();

	            Stage mdialogStagem = new Stage();
	            mdialogStagem.setTitle("Edit Person");
	            mdialogStagem.initModality(Modality.WINDOW_MODAL);
	            mdialogStagem.initOwner(mprimaryStagem);
	            Scene mscenem = new Scene(mpagem);
	            mdialogStagem.setScene(mscenem);

	            MPersonEditDialogControllerM controller = mloaderm.getController();
	            controller.setDialogStage(mdialogStagem);
	            controller.setPerson(person);

	            mdialogStagem.showAndWait();

	            return controller.isOkClicked();
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    
	    public File mgetPersonFilePathM() {
	        Preferences mprefsm = Preferences.userNodeForPackage(MMainAppM.class);
	        String mfilePathm = mprefsm.get("filePath", null);
	        if (mfilePathm != null) {
	            return new File(mfilePathm);
	        } else {
	            return null;
	        }
	    }

	    
	    public void msetPersonFilePathM(File file) {
	        Preferences mprefsm = Preferences.userNodeForPackage(MMainAppM.class);
	        if (file != null) {
	            mprefsm.put("filePath", file.getPath());

	            
	            mprimaryStagem.setTitle("AddressApp - " + file.getName());
	        } else {
	            mprefsm.remove("filePath");

	            
	            mprimaryStagem.setTitle("AdressApp");
	        }
	    }
	    
	    
	    public void mloadPersonDataFromFileM(File file) {
	        try {
	            JAXBContext mcontextm = JAXBContext
	                    .newInstance(MPersonListWrapperM.class);
	            Unmarshaller mumm = mcontextm.createUnmarshaller();

	           
	            MPersonListWrapperM mwrapperm = (MPersonListWrapperM) mumm.unmarshal(file);

	            mpersonDatam.clear();
	            mpersonDatam.addAll(mwrapperm.getPersons());

	            
	            msetPersonFilePathM(file);

	        } catch (Exception e) {
	            Dialogs.create()
	                    .title("Error")
	                    .masthead("Could not load data from file:\n" + file.getPath())
	                    .showException(e);
	        }
	    }

	  
	    public void savePersonDataToFile(File file) {
	        try {
	            JAXBContext mcontextm = JAXBContext
	                    .newInstance(MPersonListWrapperM.class);
	            Marshaller mmm = mcontextm.createMarshaller();
	            mmm.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	         
	            MPersonListWrapperM mwrapperm = new MPersonListWrapperM();
	            mwrapperm.setPersons(mpersonDatam);

	            
	            mmm.marshal(mwrapperm, file);

	           
	            msetPersonFilePathM(file);
	        } catch (Exception e) { 
	            Dialogs.create().title("Error")
	                    .masthead("Could not save data to file:\n" + file.getPath())
	                    .showException(e);
	        }
	    }
	    
	    public void mshowBirthdayStatisticsM() {
	        try {
	           
	            FXMLLoader mloaderm = new FXMLLoader();
	            mloaderm.setLocation(MMainAppM.class.getResource("view/BirthdayStatistics.fxml"));
	            AnchorPane mpagem = (AnchorPane) mloaderm.load();
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("Birthday Statistics");
	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            dialogStage.initOwner(mprimaryStagem);
	            Scene scene = new Scene(mpagem);
	            dialogStage.setScene(scene);

	            MBirthdayStatisticsControllerM controller = mloaderm.getController();
	            controller.msetPersonDataM(mpersonDatam);

	            dialogStage.show();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}
