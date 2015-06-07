package Marina.Malashenko.inb.ch.makery.adress.model;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import Marina.Malashenko.inb.ch.makery.adress.util.MLocalDateAdapterM;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MPersonM {

    private final StringProperty mfirstNamem;
    private final StringProperty mlastNamem;
    private final StringProperty mstreetm;
    private final IntegerProperty mpostalCodem;
    private final StringProperty mcitym;
    private final ObjectProperty<LocalDate> mbirthdaym;

    public MPersonM() {
		this(null, null);
	}

    public MPersonM(String firstName, String lastName) {
        this.mfirstNamem = new SimpleStringProperty(firstName);
        this.mlastNamem = new SimpleStringProperty(lastName);
        this.mstreetm = new SimpleStringProperty("some street");
        this.mpostalCodem = new SimpleIntegerProperty(1234);
        this.mcitym = new SimpleStringProperty("some city");
        this.mbirthdaym = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }

    public String getFirstName() {
        return mfirstNamem.get();
    }

    public void setFirstName(String firstName) {
        this.mfirstNamem.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return mfirstNamem;
    }

    public String getLastName() {
        return mlastNamem.get();
    }

    public void setLastName(String lastName) {
        this.mlastNamem.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return mlastNamem;
    }

    public String getStreet() {
        return mstreetm.get();
    }

    public void setStreet(String street) {
        this.mstreetm.set(street);
    }

    public StringProperty streetProperty() {
        return mstreetm;
    }

    public int getPostalCode() {
        return mpostalCodem.get();
    }

    public void setPostalCode(int postalCode) {
        this.mpostalCodem.set(postalCode);
    }

    public IntegerProperty postalCodeProperty() {
        return mpostalCodem;
    }

    public String getCity() {
        return mcitym.get();
    }

    public void setCity(String city) {
        this.mcitym.set(city);
    }

    public StringProperty cityProperty() {
        return mcitym;
    }
    
    @XmlJavaTypeAdapter(MLocalDateAdapterM.class)
    public LocalDate getBirthday() {
    	    return mbirthdaym.get();
    	}
   
    public void setBirthday(LocalDate birthday) {
        this.mbirthdaym.set(birthday);
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return mbirthdaym;
    }
}