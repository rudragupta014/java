package users;
import users.User;

public class Doctor extends User {

	public  String specialization;
	public  int experience;

	public Doctor(String userid, String pwd, String name, int age) {
		super(userid, pwd, "doctor", name, age);
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

}

