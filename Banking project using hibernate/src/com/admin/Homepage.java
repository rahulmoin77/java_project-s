package com.admin;

import java.util.Scanner;

import com.customer_section.Cust_Homepage;
import com.customer_section.Cust_Operation;
import com.staff_section.Staff_Homepage;
import com.admin_section.Admin_Homepage;
import com.customer_section.Create_Account;

public class Homepage {
	
	public static String global_Name="Global";

	public static void homepage()
	{
		while(true)
		{
		try
		{
		Scanner sc=new Scanner(System.in);
		System.out.println("Select Choice:\n 1:Admin Section \n 2:Staff Section \n 3:Customer Section\n 4:Exit");
		int choice=sc.nextInt();
		
		switch(choice)
		{
		case 1:
			Admin_Homepage.admin_Section();
			break;
		case 2:
			Staff_Homepage.staff_Section();
			break;
		case 3:
			Cust_Homepage.cust_Section();
			break;
		case 4:
			System.exit(0);
			
			break;
		}
		}
		catch(Exception e)
		{
			System.out.println("Error: "+e.getMessage());
		}
		}
		
	}
	
	
}
