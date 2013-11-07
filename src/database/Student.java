package database;

public class Student {
    private Integer id;
    private String name;
    private String surname;
    private Integer groupId;  
    
    public Student(Integer id, String name, String surname, Integer groupId){
    	this.id = id;
    	this.name = name;
    	this.surname = surname;
    	this.groupId = groupId;
    }
    
    public Integer getId(){
    	return this.id;
    }
    
    public void setId(Integer id){
    	this.id = id;
    }
    
    public String getName(){
    	return this.name;
    }
    
    public void setName(String name){
    	this.name = name;
    }
    
    public String getSurname(){
    	return this.surname;
    }
    
    public void setSurname(String surname){
    	this.surname = surname;
    }
    
    public Integer getGroupId(){
    	return this.groupId;
    }
    
    public void setGroupId(Integer groupId){
    	this.groupId = groupId;
    }
}
