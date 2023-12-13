import utils.CommonUtils;
import mode.AdminMode;

public class HMSApp {

	public static void main(String[] args) {
		CommonUtils.showBanner();

		HMSApp hmsApp = new HMSApp();
		hmsApp.manage();
				
	}

	private void manage() {
		CommonUtils.showApplicationEntryOptions();
		int mode = CommonUtils.learnOptionFromUser();
		
		switch(mode) {
			case 1  : startAdminMode(); break;
			case 2  : System.out.println("2"); break;
			case 3  : System.out.println("3"); break;
			case 4  : System.out.println("4"); break;
			default : System.out.println("default"); break;	
		}
	}

	private void startAdminMode() {
		AdminMode adminMode = new AdminMode();
		adminMode.operate();
	}

	
}
