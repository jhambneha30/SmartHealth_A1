import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

public class SmartHealth {
	static ArrayList<User> userList = new ArrayList<User>();
	static ArrayList<User> deletedUserList = new ArrayList<User>();
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

		System.out.println("New user? Enter 1");
		System.out.println("Registered user? Enter 2");
		System.out.println("Enter 3 to exit");
		int opt1=in.nextInt();
		in.nextLine();
		switch(opt1)
		{
			case 1: createProfile();
					break;
					
			case 2: userLogin();
					break;
			case 3: System.out.println("Thank you!!");
					break;
		}
		in.close();
	}
	
	private static void userLogin() {
		int flag = 0;
		Scanner in = new Scanner(System.in);

		System.out.println("Enter Credentials to Login. Enter 1 to go back to previous Menu.");
		int choice = in.nextInt();
		in.nextLine();
		if(choice == 1)
			main(null);
		else{
			System.out.println("Email Id: ");
			String loginId = in.nextLine();
			System.out.println("Password: ");
			String password = in.nextLine();
			Iterator<User> iterator2 = userList.iterator();
			while(iterator2.hasNext()) {
				User usr = iterator2.next();
				if(usr.primaryEmail.equals(loginId) && usr.password.equals(password)){
					
						flag = 1;
						userProfilePage(usr.username);
						break;
						 
				} 
			}
			if(flag==0){
				System.out.println("Invalid Login ID or Password!!");
				userLogin();
			}
		}
		in.close();
	}
	
	private static void userProfilePage(String username) {
		Scanner in = new Scanner(System.in);
		System.out.printf("Welcome "+ username);
		System.out.println();
		System.out.println("Update Details? Enter 1");
		System.out.println("Delete Profile? Enter 2");
		System.out.println("Enter 3 to Logout");
		User loggedInUser = new User();
		Iterator<User> iterator4 = userList.iterator();
		while(iterator4.hasNext()) {
			User user = iterator4.next();
			if(user.username.equals(username))
			{
				loggedInUser = user;
				break;
			}
		}
		
		int opt2=in.nextInt();
		in.nextLine();
		switch(opt2)
		{
			case 1: updateProfile(loggedInUser);
					break;
					
			case 2: deleteProfile(loggedInUser);
					break;
						
			case 3: main(null);
					break;
		}
		in.close();
	}
	
	private static void deleteProfile(User userToBeDeleted) {
		Scanner in = new Scanner(System.in);
		System.out.println("Are you sure you want to delete your Profile? [y/n]");
		String ans = in.nextLine();
		if(ans.equals("y") || ans.equals("Y"))
		{
				userList.remove(userToBeDeleted);
				deletedUserList.add(userToBeDeleted);
		}
		else
			userProfilePage(userToBeDeleted.username);
		
		in.close();	
	}

	private static void updateProfile(User loggedInUser) {
		Scanner in = new Scanner(System.in);
		System.out.println("To Update Username, press 1");
		System.out.println("To Update Primary EmailId, press 2");
		System.out.println("To Update Secondary EmailId, press 3");
		System.out.println("To Update Password, press 4");
		System.out.println("To Update Postal Address, press 5");
		System.out.println("To Update About Me, press 6");
		System.out.println("To Update Photos URL, press 7");
		
		int selection = in.nextInt();
		in.nextLine();
		switch(selection)
		{
			case 1: System.out.println("Enter new Username: ");
					loggedInUser.username = in.nextLine();
					break;
					
			case 2: System.out.println("Enter new Primary Email ID: ");
					loggedInUser.primaryEmail = in.nextLine();
					break;
			
			case 3: System.out.println("Enter new Secondary Email ID: ");
					loggedInUser.secondaryEmail = in.nextLine();
					break;
			
			case 4: System.out.println("Enter new Password: ");
					loggedInUser.password = in.nextLine();
					break;
			
			case 5: System.out.println("Enter new Postal Address: ");
					loggedInUser.address = in.nextLine();
					break;
			
			case 6: System.out.println("Update About Me: ");
					loggedInUser.aboutMe = in.nextLine();
					break;
			
			case 7: System.out.println("Update photo's URL: ");
					for(int i=0;i<3;i++)
					{
						System.out.println("Enter URL for photo " + (i+1) + ": ");
						loggedInUser.photoURL[i] = in.nextLine();
					}
					break;
		}
		userList.add(loggedInUser);
		System.out.println("More Updations?? Enter 1");
		int ch = in.nextInt();
		if(ch == 1)
		{
			updateProfile(loggedInUser);
		}
		else
		{
			System.out.println("All updations made successfully!!");
			userProfilePage(loggedInUser.username);
		}
		in.close();
	}

	public static void createProfile()
	{
		Scanner in = new Scanner(System.in);
		User user = new User();
		System.out.println("Enter the following information.");
		System.out.println("Username: ");
		user.username = in.nextLine();
		while(user.username.length() > 20){
			System.out.println("Username cannot be longer than 20 characters. Re-enter Username.");
			user.username = in.nextLine();
		}
		Iterator<User> iterator3 = userList.iterator();
		while(iterator3.hasNext()){
			 User registeredUsers = iterator3.next();
			 if(registeredUsers.username.equals(user.username)){
				 System.out.println("This username is already taken. Try some other Username.");
				 user.username = in.nextLine();
				 break;
			 }
		 }
		 
		System.out.println("Primary Email id: ");
		user.primaryEmail = in.nextLine();
		System.out.println("Secondary Email id: ");
		user.secondaryEmail = in.nextLine();
		System.out.println("Password: ");
		user.password = in.nextLine();
		System.out.println("Postal address: ");
		user.address = in.nextLine();
		System.out.println("About Me: ");
		user.aboutMe = in.nextLine();
		user.regnTime = System.currentTimeMillis();
		user.userType = "new";
		for(int i=0;i<3;i++)
		{
			System.out.println("Enter URL for photo " + (i+1) + ": ");
			user.photoURL[i] = in.nextLine();
		}
		userList.add(user);
		System.out.println("User Added Successfully!!");
		System.out.println("Enter 1 to Login");
		System.out.println("Enter 2 to go back to Menu");
		int op = in.nextInt();
		if(op == 1)
			userLogin();
		else if(op == 2)
			main(null);
		in.close();
	}
}
