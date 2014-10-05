package com.appspot.myjavaservlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class simpleServlet extends HttpServlet{
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String req_ipaddr = req.getRemoteAddr();
		String req_host = req.getRemoteHost();
		long req_port = req.getRemotePort();
		long req_port2 = req.getLocalPort();
		String req_info2 = req.getMethod();
		String req_header = req.getHeader(getServletName());
		
		// Set the content type header that is going to be returned in the
		// HTTP response so that the client will know how to display the
		// result.
		resp.setContentType("text/plain");
		
		// Look inside of the HTTP request for either a query parameter or
		// a url encoded form parameter in the body that is named "msg"
		String msg = req.getParameter("msg");
		
		// http://foo.bar?msg=asdf
		
		// Echo a response back to the client with the msg that was sent
		resp.getWriter().write("Client IP : "+req_ipaddr +"\n");
		resp.getWriter().write("    Client Port : "+ req_port + "\n");
		resp.getWriter().write("    Server Port : " + req_port2 + "\n");
		resp.getWriter().write("    Echo:"+ msg+"\n");
		resp.getWriter().write("    Client request method is  : "+req_info2 + "\n");
		resp.getWriter().write("    Client request header is  : "+req_header + "\n");
		resp.getWriter().write("--This app is created by Meng Yang At UESTC on 2014-10-04 \n");
		
		/*
		System.out.println("Client IP address : "+req_info+"\n");
		System.out.println("    The message from the client is : "+msg);
		System.out.println("    method : "+req_info2);
		System.out.println("    Header : "+req_header);
		*/
		
	}
}

//http://127.0.0.1:8888/simpletest?msg=This message is from local IE.
//http://myjavaservlet.appspot.com/simpletest?msg=This is message sent from XXX 
