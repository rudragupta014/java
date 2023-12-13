package mode;
import java.util.Scanner;
import utils.CommonUtils;
import users.User;
import db.DataBase;

public abstract class AbstractMode implements OperationMode {
    
	protected boolean loggedIn;// = true; // enable this flag to bypass authentication
	
	@Override
	public boolean login() {
		if(!loggedIn) {
			Scanner scan = CommonUtils.scanner();
			CommonUtils.print("Enter username : ");
			String username = scan.next(); 
			CommonUtils.print("Enter password : ");
			String password = scan.next();
			if(DataBase.exists(new User(username, password, "", "", 1))) {
				CommonUtils.println(">>>>>>>>>>>>>>>> Login Successful!");
				loggedIn = true;
			}else {
				CommonUtils.println(">>>>>>>>>>>>>> Invalid Credentials!! Please try again!!!");
				loggedIn = false;
			}	
		}
		return loggedIn;
	}

	@Override
	public void logout() {
		loggedIn = false;
		CommonUtils.println(">>>>>>>>>>>>>>>>> Logout Successful!");
	}

}

