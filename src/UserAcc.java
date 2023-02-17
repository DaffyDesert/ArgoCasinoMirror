

import java.util.Scanner;

public class UserAcc {

	public static void main(String[] args) {
			
			Scanner input=new Scanner (System.in);
			String name;
			String nickName;
				

	
System.out.println("Please Enter Your Name");
name = input.nextLine();
System.out.println("Please Enter the Name that will be displayed on your profile (nickname)");
nickName = input.nextLine();

	
	System.out.println("Name: " + name);
	System.out.println("Nickname: " + nickName);
	
	System.out.println("Your User Account has been created");
	
	
	}	
} 
