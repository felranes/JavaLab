package daria.krutova.ist.address.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of persons. This is used for saving the
 * list of persons to XML.
 *
 * @author Marco Jakob
 */
@XmlRootElement(name = "persons")
public class KPersonListWrapperD {

    private List<KPersonD> kPersonsd;

    @XmlElement(name = "person")
    public List<KPersonD> getPersons() {
        return kPersonsd;
    }

    public void setPersons(List<KPersonD> kPersonsd) {
        this.kPersonsd = kPersonsd;
    }
}