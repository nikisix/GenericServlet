package com.ign.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ign.utils.*;
//import com.ign.apiInterface;
//import com.ign.format.BuildResponse;
//import com.ign.format.json.JSONObject;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet1 implementation class Mahout
 * @author ntomasino
 */

public class Servlet1 extends HttpServlet {
	HashMap model = new HashMap(); //model may be of any type

    private enum RecCalls {call1, call2, call3}
	private enum AllCalls {call1, call2, call3, ping, healthcheck, metrics, help}
	AllCalls allCalls;
	RecCalls recCalls;
	String howMany;
	long DurationMillis;
	final static String n = "\n";
	final static String HELP ="HELP MSG - explain the calls and the parameters each takes";
    /**
     * @see HttpServlet#HttpServlet()
     */
	public Servlet1() throws Exception {
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterNames().hasMoreElements()) return; 		//weed out requests with zero parameters
        Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

        Status status = new Status();
		try	{
			DurationMillis = -1;
			GregorianCalendar begin = new GregorianCalendar();
			{
				String paramName;
				try{
					paramName = getParamValueIgnoreCase(request,"do");
				}
				catch(ServletException se){
					throw new ServletException("\nThe do parameter must not be empty.\n " +
							"Possible values include: do= call1, call2, call3.\n"+HELP+n);
				}
				
				if (allEnumContains(paramName)){

					if(paramName.equalsIgnoreCase("call1")){
						try {
                            String param1 = getParamValueIgnoreCase(request, "param1");
                            String param2 = getParamValueIgnoreCase(request, "param2");
                            status.setType(Status.StatusType.Success);
						} catch (ServletException se){ 
							status.setMsg("\nInvalid parameters to the call1." +
							"\n Required parameters are param1, param3\n"+n+HELP);
						}
					}
					
					else if(paramName.equalsIgnoreCase("call2")){
						try {
							String param1 = getParamValueIgnoreCase(request,"param1");
							String param2 = getParamValueIgnoreCase(request,"param2");
							String param3 = getParamValueIgnoreCase(request,"param3");
                            status.setType(Status.StatusType.Success);
						} catch (ServletException se){
							status.setMsg("\nInvalid parameters to the call2." +
							"\n Required parameters are param2, param3\n"+n+HELP);
						}
					}
					
					else if(paramName.equalsIgnoreCase("help") ||
							paramName.equalsIgnoreCase("")){
						status.setMsg(HELP);
                        status.setType(Status.StatusType.NoResults);
					}

                    else if(paramName.equalsIgnoreCase("healthcheck")){
                       //todo do healthcheck
                        status.setType(Status.StatusType.NoResults);
                    }

                    else if(paramName.equalsIgnoreCase("ping")){
                       //todo do healthcheck
                        status.setType(Status.StatusType.NoResults);
                    }
					
				}//not one of the recognized api calls
				else {
					String s = "\nThe operation parameter: "+paramName+ " wasn't recognized.\n" +n+ HELP;
					status.setMsg(s);
                    status.setType(Status.StatusType.InvalidParameter);
				}				
			}
			GregorianCalendar end = new GregorianCalendar();
			DurationMillis = end.getTimeInMillis() - begin.getTimeInMillis();
		}
		catch (Exception e)	{
			String stackTrace = ""; int count = 0; int maxDepth = 6;
			for(StackTraceElement ste : e.getStackTrace()){
				stackTrace = stackTrace.concat(ste.toString()+"\n");
				if(count++>maxDepth){	break; }
			}
			status.setType(Status.StatusType.Failure);
            status.setDetails(e.toString());
		}
        response.getOutputStream().println(gson.toJson(status));
    }
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private boolean allEnumContains(String paramName){
		for(AllCalls call : AllCalls.values()){
			if(call.toString().trim().equalsIgnoreCase(paramName)){ 
				return true;
			}
		}
		return false;
	}
	
	private String getParamValueIgnoreCase(HttpServletRequest request, String paramName) throws ServletException{
		String current = "";
		Enumeration e = request.getParameterNames();
		while(e.hasMoreElements()){
			current = e.nextElement().toString();
			if(current.equalsIgnoreCase(paramName)){
				return request.getParameter(current);
			}
		}
		throw new ServletException("parameter "+ paramName + " was not found in the request \n" + HELP);
	}
}

/*
int count = 0;
@SuppressWarnings("unchecked")
Enumeration <String> paramNames = request.getParameterNames();

while(paramNames.hasMoreElements()){
	paramName = paramNames.nextElement();
	paramValue = request.getParameter(paramName);
}
*/

