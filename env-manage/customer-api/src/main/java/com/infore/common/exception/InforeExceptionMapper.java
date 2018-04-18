package com.infore.common.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

@Provider
public class InforeExceptionMapper implements ExceptionMapper<Exception> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Response toResponse(Exception e) {
        if (e instanceof IllegalAccessException) {
            Map<String, Integer> tokenExceptionMap = new HashMap<>();
            tokenExceptionMap.put("code", 2);
            logger.error("Error msg: {}", e.getMessage());
            return Response.status(Response.Status.FORBIDDEN).entity(tokenExceptionMap).build();
        } else if (e instanceof IllegalArgumentException) {
            logger.error("Error msg: {}", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            e.printStackTrace();
        }

        logger.error("Error msg: {}", e);
        return Response.status(500).build();
    }
}
