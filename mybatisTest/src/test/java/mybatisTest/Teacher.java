package mybatisTest;

import java.io.Serializable;

public class Teacher implements Serializable{
	
	
	private static final long serialVersionUID = -3234020915238072743L;
	private String name;  
	private String code;  
	 
	public Teacher(String name, String code) {  
		super();  
		this.name = name;  
		this.code = code;  
	}  
	 
	public String getName() {  
	return name;  
	}  
	public void setName(String name) {  
	this.name = name;  
	}  
	public String getCode() {  
	return code;  
	}  
	public void setCode(String code) {  
	this.code = code;  
	}  
}
