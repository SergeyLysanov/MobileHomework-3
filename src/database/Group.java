package database;

public class Group {
	public Integer id;
    public String name;
    public Integer semester;
    
    public Group(Integer id, String name, Integer semester){
    	this.id = id;
    	this.name = name;
    	this.semester = semester;
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
    
    public Integer getsemester(){
    	return this.semester;
    }
    
    public void setsemester(Integer semester){
    	this.id = semester;
    }
}
