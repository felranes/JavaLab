package Marina.Malashenko.inb.ch.makery.adress.model;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "persons")
public class MPersonListWrapperM {
	
	    private List<MPersonM> persons;

	    @XmlElement(name = "person")
	    public List<MPersonM> getPersons() {
	        return persons;
	    }

	    public void setPersons(List<MPersonM> persons) {
	        this.persons = persons;
	    }
}
