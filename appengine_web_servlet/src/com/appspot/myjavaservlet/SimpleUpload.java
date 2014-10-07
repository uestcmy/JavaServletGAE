package com.appspot.myjavaservlet;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import javax.jdo.PersistenceManager;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;


import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileReadChannel;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;
import com.google.appengine.api.files.GSFileOptions.GSFileOptionsBuilder;


public class SimpleUpload extends HttpServlet{
	private InputStream is = null;
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
    private static final Logger logger = Logger.getLogger(SimpleUpload.class.getCanonicalName());

			 
    protected void doGet(HttpServletRequest r , HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub

    }
		 

	public void doPost(HttpServletRequest req, HttpServletResponse res)   throws ServletException, IOException {   
	    Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
	    BlobKey blobKey = blobs.get("uploadfile"); 
	    
	    if (blobKey == null) {
	         res.sendRedirect("/");
	         System.out.println("file read fail" +blobKey.getKeyString() );
	    } else {  
	    	/*
	    	BlobKey blobKey2 = blobstoreService.createGsBlobKey(
	    		    "/gs/" + "buckname" + "/" + "test.txt");
	    		blobstoreService.serve(blobKey, res);
*/

	    	//res.sendRedirect("/servefile?blob-key="+blobKey.getKeyString());
	        
	    	System.out.println("file" +blobKey.getKeyString() );
	    } 
	}
			 

	/*
	public void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		InputStream is = req.getInputStream();
		
		byte[] buffer = new byte[4096*100];
		is.read(buffer);
		is.close();
		
		Blob data = new Blob(buffer);
		String content = "Meng Yang's content";
		Date date = new Date();
		Image img = new Image(user,content,date,data);
	}
	*/
}

	
