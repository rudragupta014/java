package mode;
import utils.CommonUtils;
import java.util.Scanner;
import users.User;
import users.Doctor;
import db.DataBase;
import filehandling.FileHandler;

public class AdminMode extends AbstractMode {

	@Override
	public void operate() {
		if (login()) {
			CommonUtils.showAdminOptions();
			DataBase.get();
			int option = CommonUtils.learnOptionFromUser();
			CommonUtils.println("----------------------------------------\n");
			switch (option) {
			case 1:
				registerDoctor();
				break;
			case 2:
				deleteDoctor();
				break;
			case 3:
				listRegisteredDoctors();
				break;
			case 4:
				logout();
				break;
			default:
				CommonUtils.print("\nPlease Choose a valid option : ");
				operate();
			}

		}

	}

	public Doctor[] getDoctors(){
		Doctor doctor[] = new Doctor[DataBase.users.length];
		int i=0;
		boolean doctorsExist=false;
		for (User u : DataBase.users) {
			if(u!=null){
							if ("doctor".equalsIgnoreCase(u.getType())) {
				doctorsExist = true;
				Doctor d = (Doctor)u;
				doctor[i] = d;
				i++;
			}
			}
		}
		return doctor;
	}

	private void listRegisteredDoctors() {
		boolean first = true, doctorsExist = false;
		for (User u : DataBase.users) {
			if(u!=null){
				if ("doctor".equalsIgnoreCase(u.getType())) {
				doctorsExist = true;
				Doctor d = (Doctor) u;
				if (first) {
					CommonUtils.println("REGISTERED DOCTORS :: ");
					CommonUtils.println("\t\t-----------------------------------------------------------------------------------------------------------------");
					CommonUtils.println("\t	|	DOCTOR ID		|	DOCTOR NAME		|	SPECIALIZATION	|	EXPERIENCE	|");
					CommonUtils.println("\t\t-----------------------------------------------------------------------------------------------------------------");
					first = false;
				}
				String id = d.getUserid().length() < 10 ? d.getUserid() + "\t"
						: d.getUserid();
				String name = d.getName().length() < 16 ? d.getName() + "\t"
						: d.getName();
				CommonUtils.println("\t	|	" + id + "		|	" + name + "	|	"
						+ d.getSpecialization() + "	|	" + d.getExperience()
						+ "		|");
				CommonUtils.println("\t\t-----------------------------------------------------------------------------------------------------------------");
			}
			}		
		}

		if (!doctorsExist) {
			CommonUtils.println("No Registered Doctors Available!!!");
		}
		operate();
	}

	private void deleteDoctor() {
		Scanner scan = CommonUtils.scanner();
		CommonUtils.print("Enter the doctor Id to DataBase.delete : ");
		String docId = scan.next();
		User u = DataBase.find(docId);
		if (u != null && "doctor".equalsIgnoreCase(u.getType())) {
			CommonUtils.println("Are you sure you want to DataBase.delete " + docId + "( "
					+ u.getName() + " ) : [y/n] ? ");
			String choice = scan.next();
			if ("y".equalsIgnoreCase(choice) || "yes".equalsIgnoreCase(choice)) {
				try{
									FileHandler.writeFile(getDoctors(), null, docId);
				if (DataBase.delete(docId)) {
					CommonUtils.println("Doctor deleted successfully");
				} else {
					CommonUtils.println("Failed to DataBase.delete doctor. Please input a valid doctor id..");
				}
				}catch(Exception e){

				}

			}
		}
		operate();
	}

	private void registerDoctor() {
		Scanner scan = CommonUtils.scanner();
		CommonUtils.println("-----------------------------------------");
		CommonUtils.println("********** ENTER DOCTOR DETAILS *********");
		CommonUtils.println("-----------------------------------------");
		CommonUtils.println("-------------------------------------------------------------------");
		CommonUtils.print("|  First name : 		| 		");
		String firstName = scan.nextLine();
		CommonUtils.println("-------------------------------------------------------------------");
		CommonUtils.print("|  Last name  : 		| 		");
		String lastName = scan.nextLine();
		CommonUtils.println("-------------------------------------------------------------------");
		CommonUtils.print("|  Specialization : 		| 		");
		String specialization = scan.nextLine();
		CommonUtils.println("-------------------------------------------------------------------");
		CommonUtils.print("|  Experience (int) : 		| 		");
		int experience = scan.nextInt();
		CommonUtils.println("-------------------------------------------------------------------");
		boolean sameId = false;
		String userid="";
		do{
			CommonUtils.print("|  User Id (int) : 			| 		");
			userid = scan.next();
			User u = DataBase.find(userid);
			if(u!=null){
				System.out.println("\nDoctor with this id exists, try using another id\n");
				sameId=true;
			}
		}while(sameId=true);
		
		CommonUtils.println("-------------------------------------------------------------------");
		CommonUtils.print("|  Age (int) :	 		| 		");
		int age = scan.nextInt();
		CommonUtils.println("-------------------------------------------------------------------\n");

		Doctor doctor = new Doctor(userid, userid, firstName + " " + lastName, age);
		doctor.setSpecialization(specialization);
		doctor.setExperience(experience);
		Doctor docs[] = getDoctors();
		Doctor docss[] = new Doctor[docs.length+1];
		for (int i=0;i<docs.length;i++) {
			docss[i] = docs[i];
		}
		FileHandler.writeFile(docss,doctor,"");
		DataBase.save(doctor);

		if (DataBase.exists(doctor)) {
			CommonUtils.println("Doctor registration done successfully");
		}

		operate();

	}

}
