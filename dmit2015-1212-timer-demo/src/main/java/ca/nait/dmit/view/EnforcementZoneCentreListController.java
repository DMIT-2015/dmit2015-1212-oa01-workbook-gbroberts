package ca.nait.dmit.view;

import ca.nait.dmit.entity.EnforcementZoneCentre;
import ca.nait.dmit.repository.EnforcementZoneCentreRepository;
import lombok.Getter;
import org.omnifaces.util.Messages;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("currentEnforcementZoneCentreListController")
@ViewScoped
public class EnforcementZoneCentreListController implements Serializable { // serializable: server writes to file and brings it to memory IF a request made
    private static final long serialVersionUID = 1L; // needs unique number

    @Inject
    private EnforcementZoneCentreRepository _enforcementzonecentreRepository; // can't tell when inject is done so need PostConstruct shown below

    @Getter
    private List<EnforcementZoneCentre> enforcementzonecentreList;

    @PostConstruct  // After @Inject is complete we can access the code below
    public void init() {
        try {
            enforcementzonecentreList = _enforcementzonecentreRepository.list();
        } catch (Exception ex) {
            Messages.addGlobalError(ex.getMessage()); // global error message in the system
        }
    }
}