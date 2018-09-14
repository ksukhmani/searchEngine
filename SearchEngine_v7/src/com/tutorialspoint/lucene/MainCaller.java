package com.tutorialspoint.lucene;

import java.io.Serializable;

import com.tutorialspoint.lucene.MainClass;
 
public class MainCaller implements Serializable{
 
	MainClass m;
	
    private static final long serialVersionUID = 1L;
    private String name;
    private String address;
    private Long mobile;
    private String emailid;
    private String results[];
    
    public String[] getURLList() throws Exception
    {
       	return results;
    }
    public void setURLList(String text,String DS ,String Searchtype) throws Exception
    {	m= new MainClass();
    	results = m.main(text,DS,Searchtype);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Long getMobile() {
        return mobile;
    }
    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }
    public String getEmailid() {
        return emailid;
    }
    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }
     
}