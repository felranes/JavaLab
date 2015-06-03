package anastasija.sabynina.ist.model;

import anastasija.sabynina.ist.util.SLocalDateAdapterA;
import javafx.beans.property.*;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

public class SPersonA {

    private final StringProperty sfirstNamea;
    private final StringProperty slastNamea;
    private final StringProperty sstreeta;
    private final IntegerProperty spostalCodea;
    private final StringProperty scitya;
    private final ObjectProperty<LocalDate> sbirthdaya;


    public SPersonA() {
        this(null, null);
    }

    public SPersonA(String firstName, String lastName) {
        this.sfirstNamea = new SimpleStringProperty(firstName);
        this.slastNamea = new SimpleStringProperty(lastName);

        this.sstreeta = new SimpleStringProperty("some street");
        this.spostalCodea = new SimpleIntegerProperty(1234);
        this.scitya = new SimpleStringProperty("some city");
        this.sbirthdaya = new SimpleObjectProperty<>(LocalDate.of(1999, 2, 21));
    }

    public String getFirstName() {
        return sfirstNamea.get();
    }

    public void setFirstName(String firstName) {
        this.sfirstNamea.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return sfirstNamea;
    }

    public String getLastName() {
        return slastNamea.get();
    }

    public void setLastName(String lastName) {
        this.slastNamea.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return slastNamea;
    }

    public String getStreet() {
        return sstreeta.get();
    }

    public void setStreet(String street) {
        this.sstreeta.set(street);
    }

    public StringProperty streetProperty() {
        return sstreeta;
    }

    public int getPostalCode() {
        return spostalCodea.get();
    }

    public void setPostalCode(int postalCode) {
        this.spostalCodea.set(postalCode);
    }

    public IntegerProperty postalCodeProperty() {
        return spostalCodea;
    }

    public String getCity() {
        return scitya.get();
    }

    public void setCity(String city) {
        this.scitya.set(city);
    }

    public StringProperty cityProperty() {
        return scitya;
    }

    @XmlJavaTypeAdapter(SLocalDateAdapterA.class)
    public LocalDate getBirthday() {
        return sbirthdaya.get();
    }

    public void setBirthday(LocalDate birthday) {
        this.sbirthdaya.set(birthday);
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return sbirthdaya;
    }
}
