package org.amire.progav_finalproj.dto;

import java.util.Arrays;

public class ContratSetDto {

    private boolean cdi;
    private boolean cdd;

    public ContratSetDto(String contrats) {
        // contrats is a string of the form "[cdi,cdd]"
        contrats = contrats.replace("[", "");
        contrats = contrats.replace("]", "");
        contrats = contrats.replace(" ", "");
        String[] contratArray = contrats.split(",");
        this.cdi = Arrays.asList(contratArray).contains("cdi");
        this.cdd = Arrays.asList(contratArray).contains("cdd");
    }
}
