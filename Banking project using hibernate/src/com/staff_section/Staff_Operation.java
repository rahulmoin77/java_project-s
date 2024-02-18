package com.staff_section;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.admin.Homepage;
import com.customer_section.Create_Account;
import com.customer_section.Cust_Homepage;
import com.modal.Customer;
import com.modal.Staff;
import com.most_common_methods_and_classes.Wait;

public class Staff_Operation {

	static String id; 
	public static SessionFactory sf=Staff_Homepage.getSessionFactory();
	public static SessionFactory sf1=Cust_Homepage.getSessionFactory();
	
	public static void login()
	{
		try
		{
			Scanner sc=new  Scanner(System.in);
			System.out.println("Enter Your Id:");
			id=sc.next();
			
			Scanner sc1=new Scanner(System.in);
			System.out.println("Enter Your Password:");
			String pass=sc1.next();
			
			Session session = sf.openSession();
			Staff staff = session.get(Staff.class, id);
			
			if(staff.getS_Id().equals(id) && staff.getPass().equals(pass))
			{
				Wait.wait_Time();
				
				System.out.println("Login Successflly...");
				
				
				Staff_Homepage.staff_Name=staff.getS_name().getFirst_Name();
				Homepage.global_Name="Staff";
				session.close();
				Staff_Homepage.staff_Homepage();
			}
			else
			{
				System.out.println("Invalid Credentials...");
			}
			
			
			
		}
		catch(Exception e)
		{
			System.out.println("Error: "+e.getMessage());
		}
	}
	public static void  show_Self_Details()
	{
		try 
		{
			Wait.wait_Time();
			Session session = sf.openSession();
			Staff staff = session.get(Staff.class, id);
			System.out.println("Your Details:-\n");
			System.out.println("Name          :"+staff.getS_name().getFirst_Name()+" "+staff.getS_name().getMiddle_Name()+" "+staff.getS_name().getLast_Name());
			System.out.println("Address       :"+staff.getAddrs().getVillage()+", "+staff.getAddrs().getTaluka()+", "+staff.getAddrs().getDistrict()+", "+staff.getAddrs().getPincode());
			System.out.println("Aadhar Number :"+staff.getAadhar_No());
			System.out.println("Pan Number    :"+staff.getPan_No());
			System.out.println("Mobile Number :+91 "+staff.getMo_No());
			System.out.println("Birth Date    :"+staff.getBirth_Date());
			System.out.println("Id            :"+staff.getS_Id());
			System.out.println("Password      :"+staff.getPass());
			
			
		}
		catch(Exception e)
		{
			System.out.println("Error: "+e.getMessage());
		}
		
	}
	public static void  withdrawl()
	{
		try
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter Account Number:");
			String act_No=sc.next();
			Session session = sf1.openSession();
			Customer customer = session.get(Customer.class, act_No);
			if(customer.getAcc_No().equals(act_No))
			{
				System.out.println("Account Type     :"+customer.getAct_Type());
				System.out.println("Available Amount :"+customer.getAmount());
				
				Scanner scr=new Scanner(System.in);
				System.out.println("Enter Amount");
				double amt=scr.nextDouble();
				
				customer.setAmount(customer.getAmount()-amt);
				session.beginTransaction().commit();
				Wait.wait_Time();
				System.out.println("Amount Debited Successfully...");
				
				session.close();
				
				
				
			}
			else
			{
				System.out.println("Enter Account Number is not Found...");
			}
			
			
		}
		catch(Exception e)
		{
			System.out.println("Error: "+e.getMessage());
		}
	}
	
	public static void  account_Deposite()
	{
		try
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter Recievers Account Number:");
			String rcr_Act=sc.next();
			Session session = sf1.openSession();
			Customer reciever = session.get(Customer.class, rcr_Act);
			if(reciever.getAcc_No().equals(rcr_Act))
			{
				Scanner sc65=new Scanner(System.in);
				System.out.println("Enter Sender Account Number:");
				String sdr_Act=sc65.next();
				Customer sender = session.get(Customer.class, sdr_Act);
				if(sender.getAcc_No().equals(sdr_Act))
				{
					System.out.println("Account Type      :"+sender.getAct_Type());
					System.out.println("Available Balance :"+sender.getAmount());
					
					Scanner s1=new Scanner(System.in);
					System.out.println("Enter Amount:");
					double amt=s1.nextDouble();
					
					
					
					sender.setAmount(sender.getAmount()-amt);
					session.beginTransaction().commit();
					
					reciever.setAmount(reciever.getAmount()+amt);
					session.beginTransaction().commit();
					Wait.wait_Time();
					System.out.println("Amount Transfer Successfully...");
					session.close();
					
				}
				else
				{
					System.out.println("Sender Account Number Not Found...");
				}
				
				
			}
			else
			{
				System.out.println("Reciever Account Number Not Found...");
			}
			
			
			
		}
		catch(Exception e)
		{
			System.out.println("Error: "+e.getMessage());
		}
	}
	public static void  single_Customer_Details()
	{
		try
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter Account Number:");
			String act_No=sc.next();
			Session session = sf1.openSession();
			Customer customer = session.get(Customer.class, act_No);
			if(customer.getAcc_No().equals(act_No))
			{
				Wait.wait_Time();
				System.out.println("Your Account Details:-");
				System.out.println("Account Number :"+customer.getAcc_No());
				System.out.println("Name           :"+customer.getCust_Name().getFirst_Name()+" "+customer.getCust_Name().getMiddle_Name()+" "+customer.getCust_Name().getLast_Name());
				System.out.println("Address        :"+customer.getAddrs().getVillage()+", "+customer.getAddrs().getTaluka()+", "+customer.getAddrs().getDistrict()+", "+customer.getAddrs().getPincode());
				System.out.println("Aadhar Number  :"+customer.getAadhar_No());
				System.out.println("Pan Number     :"+customer.getPan_No());
				System.out.println("Mobile Number  :+91 "+customer.getMo_NO());
				System.out.println("Birth Date     :"+customer.getBirth_Date());
				System.out.println("Pin            :"+customer.getPin());
				System.out.println("Amount         :"+customer.getAmount());
				System.out.println("Password       :"+customer.getPass());
				
				session.close();
			}
			else
			{
				System.out.println("Account Number Not Found...");
			}
		}
		catch(Exception e)
		{
			System.out.println("Error: "+e.getMessage());
		}
	}
	public static void  all_Customer_Details()
	{
		try
		{
			
			
			Session session = sf1.openSession();
			List<Customer> customer1 = session.createQuery("from Customer").list();
			if(customer1.isEmpty())
			{
				System.out.println("No record found...");
			}
			else
			{
			Wait.wait_Time();
			System.out.println("All Account Details:-\n");
			for(Customer customer:customer1)
			{
				
				
				System.out.println("Account Number :"+customer.getAcc_No());
				System.out.println("Name           :"+customer.getCust_Name().getFirst_Name()+" "+customer.getCust_Name().getMiddle_Name()+" "+customer.getCust_Name().getLast_Name());
				System.out.println("Address        :"+customer.getAddrs().getVillage()+", "+customer.getAddrs().getTaluka()+", "+customer.getAddrs().getDistrict()+", "+customer.getAddrs().getPincode());
				System.out.println("Aadhar Number  :"+customer.getAadhar_No());
				System.out.println("Pan Number     :"+customer.getPan_No());
				System.out.println("Mobile Number  :+91 "+customer.getMo_NO());
				System.out.println("Birth Date     :"+customer.getBirth_Date());
				System.out.println("Pin            :"+customer.getPin());
				System.out.println("Amount         :"+customer.getAmount());
				System.out.println("Password       :"+customer.getPass());
				System.out.println("********************************************************");
				
				session.close();
			}
		  }
		}
		catch(Exception e)
		{
			System.out.println("Error: "+e.getMessage());
		}
	}
	
	public static void  delete_Customer()
	{
		try
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter Account Number:");
			String act_No=sc.next();
			Session session = sf1.openSession();
			Customer customer = session.get(Customer.class, act_No);
			if(customer.getAcc_No().equals(act_No))
			{
				session.delete(customer);
				session.beginTransaction().commit();
				Wait.wait_Time();
				System.out.println("Customer Account Deleted Successfully...");
				session.close();
			}
			else
			{
				System.out.println("Account Number Not Found...");
			}
		}
		catch(Exception e)
		{
			System.out.println("Error: "+e.getMessage());
		}
	}
	public static void  show_Cust_Amount()
	{
		try
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter Account Number:");
			String act_No=sc.next();
			Session session = sf1.openSession();
			Customer customer = session.get(Customer.class, act_No);
			if(customer.getAcc_No().equals(act_No))
			{
				Wait.wait_Time();
				System.out.println("Available Balance: "+customer.getAmount());
			}
			else
			{
				System.out.println("Account Number Not Found...");
			}
		}
		catch(Exception e)
		{
			System.out.println("Error: "+e.getMessage());
		}
	}
}
