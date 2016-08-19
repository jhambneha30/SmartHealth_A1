import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

public class SmartHealth {
	static ArrayList<User> userList = new ArrayList<User>();
	public static void main(String args[])
	{
		
		Scanner in = new Scanner(System.in);
		long currentTime = System.currentTimeMillis();
		Iterator<User> iterator1 = userList.iterator();
		int denominator = 1000*60*60*24;
		 while(iterator1.hasNext()) {
			 User userTime = iterator1.next();
			 long result=(currentTime - userTime.regnTime)/denominator;
			 if(result > 30 && result < 366)
			 {
				 userTime.userType = "middle";
			 }
			 else if(result >= 365)
			 {
				 userTime.userType = "old";
			 }
		 }
		int opt1=0;
		System.out.println("New user? Enter 1");
		System.out.println("Registered user? Enter 2");
		System.out.println("Enter 3 to exit");
		while(opt1!=3)
		{
			opt1=in.nextInt();
			in.nextLine();
			if(opt1==1)
			{
				createProfile();
			}
			else if(opt1==2)
			{
				
			}
		}
		
	}
	public static void createProfile()
	{
		Scanner in = new Scanner(System.in);
		User user = new User();
		System.out.println("Enter the following information.");
		System.out.println("Username: ");
		user.username = in.nextLine();
		System.out.println("Primary Email id: ");
		user.primaryEmail = in.nextLine();
		System.out.println("Secondary Email id: ");
		user.secondaryEmail = in.nextLine();
		System.out.println("Postal address: ");
		user.address = in.nextLine();
		System.out.println("About Me:");
		user.aboutMe = in.nextLine();
		user.regnTime = System.currentTimeMillis();
		user.userType = "new";
		for(int i=0;i<3;i++)
		{
			System.out.println("Enter URL for photo " + i);
			user.photoURL[i] = in.nextLine();
		}
		userList.add(user);
	}
}
