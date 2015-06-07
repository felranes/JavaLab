package anastasija.sabynina.ist.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "persons")
public class SPersonListWrapperA {

    private List<SPersonA> spersonesa;

    @XmlElement(name = "person")
    public List<SPersonA> getSpersonesa() {
        return spersonesa;
    }

    public void setSpersonesa(List<SPersonA> spersonesa) {
        this.spersonesa = spersonesa;
    }
}
