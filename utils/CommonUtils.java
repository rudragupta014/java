package utils;
import java.util.Scanner;

public class CommonUtils {

	public static void showBanner() {
		println("********************************************************************");
		println("***************** BML SUPER SPECIALITY HOSPITAL ******************");
		println("********************************************************************\n");
	}

	public static void showApplicationEntryOptions() {
		println("\n[1] -> Administrator Login");

		println("----------------------------------------");
	}
	
	public static void showAdminOptions() {
		println("----------------------------------------\n");
		println("**** ADMIN OPERATIONS ****");
		println("\n[1] -> Register a Doctor");
		println("[2] -> Delete Doctor Info");
		println("[3] -> List Registered Doctors");
		println("[4] -> Logout");
		println("----------------------------------------");
	}

	public static Scanner scanner() {
		return new Scanner(System.in);
	}
	
	public static void println(String content) {
		System.out.println(content);
	}
	public static void print(String content) {
		System.out.print(content);
	}
	
	public static int learnOptionFromUser() {
		System.out.print("Please enter your choice : ");
		return scanner().nextInt();
	}
}
