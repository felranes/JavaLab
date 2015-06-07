package daria.krutova.ist.address.model;

import java.time.LocalDate;

import daria.krutova.ist.address.util.KLocalDateAdapterD;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class KPersonD {

    private final StringProperty kFirstNamed;
    private final StringProperty kLastNamed;
    private final StringProperty kStreetd;
    private final IntegerProperty kPostalCoded;
    private final StringProperty kCityd;
    private final ObjectProperty<LocalDate> kBirthdayd;

    /**
     * Default constructor.
     */
    public KPersonD() {
        this(null, null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param firstName
     * @param lastName
     */
    public KPersonD(String firstName, String lastName) {
        this.kFirstNamed = new SimpleStringProperty(firstName);
        this.kLastNamed = new SimpleStringProperty(lastName);

        // Some initial dummy data, just for convenient testing.
        this.kStreetd = new SimpleStringProperty("some street");
        this.kPostalCoded = new SimpleIntegerProperty(1234);
        this.kCityd = new SimpleStringProperty("some city");
        this.kBirthdayd = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }

    public String getFirstName() {
        return kFirstNamed.get();
    }

    public void setFirstName(String firstName) {
        this.kFirstNamed.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return kFirstNamed;
    }

    public String getLastName() {
        return kLastNamed.get();
    }

    public void setLastName(String lastName) {
        this.kLastNamed.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return kLastNamed;
    }

    public String getStreet() {
        return kStreetd.get();
    }

    public void setStreet(String street) {
        this.kStreetd.set(street);
    }

    public StringProperty streetProperty() {
        return kStreetd;
    }

    public int getPostalCode() {
        return kPostalCoded.get();
    }

    public void setPostalCode(int postalCode) {
        this.kPostalCoded.set(postalCode);
    }

    public IntegerProperty postalCodeProperty() {
        return kPostalCoded;
    }

    public String getCity() {
        return kCityd.get();
    }

    public void setCity(String city) {
        this.kCityd.set(city);
    }

    public StringProperty cityProperty() {
        return kCityd;
    }

    @XmlJavaTypeAdapter(KLocalDateAdapterD.class)
    public LocalDate getBirthday() {
        return kBirthdayd.get();
    }

    public void setBirthday(LocalDate birthday) {
        this.kBirthdayd.set(birthday);
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return kBirthdayd;
    }
}