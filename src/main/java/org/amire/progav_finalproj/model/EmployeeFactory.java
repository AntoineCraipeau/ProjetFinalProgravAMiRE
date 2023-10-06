package org.amire.progav_finalproj.model;

import jakarta.servlet.http.HttpServletRequest;

public class EmployeeFactory {

    public static EmployesEntity getEmployeeInfoFromRequest(HttpServletRequest request) {
        EmployesEntity employeAEditer = new EmployesEntity();
        employeAEditer.setId(Integer.parseInt(request.getParameter("id")));
        employeAEditer.setNom(request.getParameter("nom"));
        employeAEditer.setPrenom(request.getParameter("prenom"));
        employeAEditer.setTeldom(request.getParameter("teldom"));
        employeAEditer.setTelport(request.getParameter("telport"));
        employeAEditer.setTelpro(request.getParameter("telpro"));
        employeAEditer.setAdresse(request.getParameter("adresse"));
        employeAEditer.setCodepostal(request.getParameter("codepostal"));
        employeAEditer.setVille(request.getParameter("ville"));
        employeAEditer.setEmail(request.getParameter("email"));
        return employeAEditer;
    }

}
