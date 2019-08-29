package yello.amo;

import java.io.IOException;
import java.util.Enumeration;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import BLL.RequestLogManager;
import Models.ActivityLog.RequestLogData;

@Provider
@PreMatching
@Priority(Priorities.AUTHENTICATION)
public class RequestInterceptor implements ContainerRequestFilter {

	@Context
    private HttpServletRequest sr;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println("Entered the filter!");
		// Get the Authorization header from the request
		//String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		System.out.println("IP Address: "+ sr.getRemoteAddr());
		System.out.println("URI PATH: "+ requestContext.getUriInfo().getAbsolutePath());
		System.out.println("REQUEST METHOD: " + requestContext.getRequest().getMethod().toString());
		RequestLogData logData = new RequestLogData();
		logData.setIpAddress(sr.getRemoteAddr());
		logData.setRequestURL(requestContext.getUriInfo().getAbsolutePath().toString());
		
		RequestLogManager.insertRequestData(logData);
		/*
		Enumeration<String> trialEnum = sr.getHeaderNames();
		
		Integer i = 0;
		while (trialEnum.hasMoreElements()) {
			String string = (String) trialEnum.nextElement();
			System.out.println("ENUM("+i+"): "+ string+" --> "+ sr.getHeader(string));
			i++;
		}
		*/
		
	}



}

