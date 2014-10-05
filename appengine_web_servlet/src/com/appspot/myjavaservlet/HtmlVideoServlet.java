package com.appspot.myjavaservlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Adds an html form to capture and display video metadata. 
 * Allows clients to send HTTP POST
 * requests with videos that are stored in memory using a list.
 * Clients can send HTTP GET requests to receive a 
 * listing of the videos that have been sent to the servlet
 * so far. Stopping the servlet will cause it to lose the history
 * of videos that have been sent to it because they are stored
 * in memory.
 * 
 * @author jules
 * @author Anonymous
 *
 */
public class HtmlVideoServlet extends HttpServlet // Servlets should inherit HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	
	public static final String NOTICE = "<br>Please enter the information in <b>English</b>. </br> <br>This application is for testing.Please do <font size= \"6\"> <b>NOT</b> </font size> enter important data since we cannot make sure the security.</br>";
	
    // An in-memory list that the servlet uses to store the
    // videos that are sent to it by clients
    private List<Student> students = new java.util.concurrent.CopyOnWriteArrayList<Student>();
    
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Make sure and set the content-type header so that the client
        // can properly (and securely!) display the content that you send
        // back
        resp.setContentType("text/html");

        // This PrintWriter allows us to write data to the HTTP 
        // response body that is going to be sent to the client.
        PrintWriter sendToClient = resp.getWriter();
        
        // UI form
        sendToClient.write(
            "<br><form name='formvideo' method='POST' target='_self'>" +
            "<fieldset><legend>Test Data</legend>" +
            "<table><tr>" +
            "<td><label for='name'>Name:&nbsp;</label></td>" +
            "<td><input type='text' name='name' id='name' size='64' maxlength='64' /></td>" +
            "</tr><tr>" +
            "<td><label for='school'>School:&nbsp;</label></td>" +
            "<td><input type='text' name='school' id='school' size='64' maxlength='256' /></td>" +
            "</tr><tr>" +
            "<td><label for='major'>Major:&nbsp;</label></td>" +
            "<td><input type='text' name='major' id='major' size='64' maxlength='256' /></td>" +
            "</tr><tr> "+
            
            "<td><label for='year'>Year of Admission:&nbsp;</label></td>" +
            "<td><input type='text' name='year' id='year' size='16' maxlength='256' /></td>" +
            
            "</tr><tr>" +
            
            "<td style='text-align: right;' colspan=2><input type='submit' value='Add' /></td>" +
            "</tr></table></fieldset></form></br>"      );
        
        // Loop through all of the stored videos and print them out
        // for the client to see.
        for (Student v : this.students) {
            
            // For each video, write its name and URL into the HTTP
            // response body
            sendToClient.write("<b>"+v.getName() +"</b>"+ " : " + v.getSchool() +"--"+v.getMajor()+ " (<i>" + v.getYear() + " </i>) at "+v.getIP() +"<br />" );
        }
        sendToClient.write("<font size=\"4\" > <i> This application is created by Meng Yang at UESTC on 2014-10-05.</i></font>");
        sendToClient.write("</body></html>");
    }
    
    /**
     * This method processes all of the HTTP GET requests routed to the
     * servlet by the web container. This method loops through the lists
     * of videos that have been sent to it and generates a plain/text 
     * list of the videos that is sent back to the client.
     * 
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	resp.getWriter().write("<html><body>"+NOTICE);
        processRequest(req, resp);
    }

    /**
     * This method handles all HTTP POST requests that are routed to the
     * servlet by the web container.
     * 
     * Sending a post to the servlet with 'name', 'duration', and 'url' 
     * parameters causes a new video to be created and added to the list of 
     * videos. 
     * 
     * If the client fails to send one of these parameters, the servlet generates 
     * an HTTP error 400 (Bad request) response indicating that a required request
     * parameter was missing.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        // First, extract the HTTP request parameters that we are expecting
        // from either the URL query string or the url encoded form body
        String name = req.getParameter("name");
        String school = req.getParameter("school");
        String major = req.getParameter("major");
        String yearstr = req.getParameter("year");
        String ipaddr = req.getRemoteAddr();
        // Check that the duration parameter provided by the client
        // is actually a number
        long yearnum = -1;
        try{
            yearnum = Long.parseLong(yearstr);
        }catch(NumberFormatException e){
            // The client sent us a duration value that wasn't a number!
        }

        // Now, the servlet has to look at each request parameter that the
        // client was expected to provide and make sure that it isn't null,
        // empty, etc.
        if (name == null || school == null || yearstr == null
                || name.trim().length() < 1 || school.trim().length() < 3
                || yearstr.trim().length() < 1
                || yearnum <= 0) {
            
            // If the parameters pass our basic validation, we need to 
            // send an HTTP 400 Bad Request to the client and give it
            // a hint as to what it got wrong.
            resp.setContentType("text/html");
            resp.sendError(400, "Missing ['name','school','major','year'].");
        } 
        else {
            // It looks like the client provided all of the data that
            // we need, use that data to construct a new Video object
            Student v = new Student(name, school, major,yearnum,ipaddr);
            
            // Add the video to our in-memory list of videos
            students.add(v);
            
            // Let the client know that we successfully added the video
            // by writing a message into the HTTP response body
            resp.getWriter().write("<html><body>" + NOTICE);
            
            processRequest(req, resp);
        }
    }
}

////http://127.0.0.1:8888/view/video
//http://myjavaservlet.appspot.com/simpletest?msg=This is message sent from XXX 