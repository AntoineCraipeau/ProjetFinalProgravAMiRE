package org.amire.progav_finalproj.dto;

import java.util.Arrays;

public class ContratSetDto {

    private boolean cdi;
    private boolean cdd;

    public ContratSetDto(String contrats) {
        // contrats is a string of the form "[cdi,cdd]"
        if(contrats == null) {
            this.cdi = false;
            this.cdd = false;
            return;
        }
        contrats = contrats.replace("[", "");
        contrats = contrats.replace("]", "");
        contrats = contrats.replace(" ", "");
        String[] contratArray = contrats.split(",");
        this.cdi = Arrays.asList(contratArray).contains("cdi");
        this.cdd = Arrays.asList(contratArray).contains("cdd");
    }

    public boolean getCdi() {
        return cdi;
    }
    public boolean getCdd() {
        return cdd;
    }

    public boolean hasMatch(ContratSetDto other) {
        return (this.cdi && other.cdi) || (this.cdd && other.cdd);
    }
}
