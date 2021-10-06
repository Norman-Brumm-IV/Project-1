package tools;

public class Test {
private String userName;
private String passWord;
public String getUserName() {
	return userName;
}
public Test(String userName, String passWord) {
	super();
	this.userName = userName;
	this.passWord = passWord;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPassWord() {
	return passWord;
}
public void setPassWord(String passWord) {
	this.passWord = passWord;
}
}
