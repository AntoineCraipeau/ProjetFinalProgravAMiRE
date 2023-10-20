package org.amire.progav_finalproj.utils;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class ActionTypesUtils {

    public static ActionTypes getActionTypesFromRequest(HttpServletRequest request) {
        return ActionTypes.valueOf(Optional.ofNullable(request.getParameter("action")).orElse("ToLogin"));
    }

}
