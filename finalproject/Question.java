package finalproject;

public class Question {
	int type;
	String content;
	// initialize variables
	
	Question(){
		// empty constructor
	}
	
	public Question(int type, String content){
		// constructor to make question objects
		this.type = type;
		this.content = content;
	}
}