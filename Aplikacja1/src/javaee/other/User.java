package javaee.other;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String surname;
	
	public void setId(int id){
		this.id = id;
	}

	public void setName(String name){
		this.name = name;
	}
	
	public void setSurname(String surname){
		this.surname = surname;
	}
	
	public int getId(){
		return id;
	}

	public String getName(){
		return name;
	}
	
	public String getSurname(){
		return surname;
	}	
	
}
