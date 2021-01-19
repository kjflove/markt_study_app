package markt_study.storage;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class FileData {
	
	  private String name;
	  private String url;
	  
	  
	  public FileData(){
		  
	  }
	  
	  public String getFileExtension(String fileName) {
	        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
	        return fileName.substring(fileName.lastIndexOf(".")+1);
	        else return "";
	   }

	  public FileData(String name, String url) {
	    this.name = name;
	    this.url = url;
	  }

	  public String getName() {
	    return this.name;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }

	  public String getUrl() {
	    return this.url;
	  }

	  public void setUrl(String url) {
	    this.url = url;
	  }
}
