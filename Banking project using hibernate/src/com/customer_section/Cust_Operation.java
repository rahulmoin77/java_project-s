package com.customer_section;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.admin.Homepage;
import com.modal.Customer;
import com.most_common_methods_and_classes.Wait;

public class Cust_Operation {

	static String account_Number;
	 
	static SessionFactory sf1=Cust_Homepage.getSessionFactory();
	
	public static void cust_Login()
	{
		try
		{
		
		Session session = sf1.openSession();
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Your Account Number:");
		account_Number=sc.next();
		
		Scanner sc1=new Scanner(System.in);
		System.out.println("Enter Your Password:");
		String pass=sc1.next();
		
		Customer cust = session.get(Customer.class, account_Number);
		
		
		if(cust.getAcc_No().equals(account_Number) && cust.getPass().equals(pass) )
		{
			
			Wait.wait_Time();
			
			System.out.println("Login Successfully...");
			
			Cust_Homepage.name=cust.getCust_Name().getFirst_Name();
			
			Homepage.global_Name="Customer";
			
			session.close();
			
			Cust_Homepage.cust_Homepage();
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
	public static void show_Cust_Details()
	{
		Session session = sf1.openSession();
		Customer customer = session.get(Customer.class, account_Number);
		Wait.wait_Time();
		System.out.println("Your Account Details:-");
		System.out.println("Account Number :"+customer.getAcc_No());
		System.out.println("Name           :"+customer.getCust_Name().getFirst_Name()+" "+customer.getCust_Name().getMiddle_Name()+" "+customer.getCust_Name().getLast_Name());
		System.out.println("Address        :"+customer.getAddrs().getVillage()+", "+customer.getAddrs().getTaluka()+", "+customer.getAddrs().getDistrict()+", "+customer.getAddrs().getPincode());
		System.out.println("Aadhar Number  :"+customer.getAadhar_No());
		System.out.println("Pan Number     :"+customer.getPan_No());
		System.out.println("Mobile Number  :+91 "+customer.getMo_NO());
		System.out.println("Birth Date     :"+customer.getBirth_Date());
		
		
	}
	public static void withdrwal()
	{
		double amount=0;
		try
		{
			while(true)
			{
				Scanner sc=new Scanner(System.in);
				System.out.println("Enter Amount:");
				amount=sc.nextDouble();
				break;
			}
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter  Pin:");
			String pin=sc.next();
			
			
			Session session = sf1.openSession();
			Customer cust = session.get(Customer.class, account_Number);
			if(pin.equals(cust.getPin()))
			{
				if(cust.getAct_Type().equals("Savings"))
				{
					if(cust.getAmount()-amount>1000)
					{
						
						
						
						cust.setAmount(cust.getAmount()-amount);
						
						
						session.beginTransaction().commit();

						Wait.wait_Time();
						
						System.out.println("Amount "+amount +" Debited From Your Account...");
						
						System.out.println("Do you want show remaining Account Balance:\n 1:Yes\n2:No");
						Scanner sc12=new Scanner(System.in);
						int ch=sc12.nextInt();
						switch(ch)
						{
						case 1:
							
							
							System.out.println("Remaining Account Balance:- "+cust.getAmount());
							session.close();
							
							break;
						case 2:
							System.out.println("Thanksful for visiting our branch...");
							session.close();
							
							break;
							
						}
					}
					else
					{
						System.out.println("You reached limit of savings account");
					}
				}
				else
				{
					if(cust.getAmount()-amount>0)
					{
						
						
						cust.setAmount(cust.getAmount()-amount);
						
						
						session.beginTransaction().commit();
						
						Wait.wait_Time();
						
						System.out.println("Amount "+amount +" Debited From Your Account...");
						session.close();
						
					}
					else
					{
						System.out.println("Entered Amount is not Withdrawable...");
					}
				}
				
			}
			else
			{
				System.out.println("Invalid Pin...");
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Error: "+e.getMessage());
		}
	}
	public static void deposite()
	{
		try
		{
		String rcvr_Act;
		double amount;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Recievers Account Number:");
		rcvr_Act=sc.next();
		
		Session session = sf1.openSession();
		
		Customer reciever = session.get(Customer.class, rcvr_Act);
		
		if(reciever==null)
		{
			System.out.println("Entered Account Number is Not Found...");
		}
		else
		{
			if(rcvr_Act.equals(Cust_Operation.account_Number))
			{
				System.out.println("This Trnsaction is not Valid...");
			}
			else
			{
			Scanner sc1=new Scanner(System.in);
			System.out.println("Enter Amount:");
			amount=sc1.nextDouble();
			Customer customer = session.get(Customer.class, account_Number);
			if(customer.getAct_Type().equals("Savings"))
			{
				
				if(customer.getAmount()-amount>=1000)
				{
					System.out.println("To Confirm Transfer Enter Your Pin: ");
					Scanner scr=new Scanner(System.in);
					String pin=scr.next();
					if(customer.getPin().equals(pin))
					
					{
					reciever.setAmount(reciever.getAmount()+amount);
					session.beginTransaction().commit();
					
					
					customer.setAmount(customer.getAmount()-amount);
					session.beginTransaction().commit();
					
					Wait.wait_Time();
					
					System.out.println("Amount Deposited in "+rcvr_Act+" ...");
					
					
					session.close();
					}
					else
					{
						System.out.println("You have enter wrong pin...");
					}
				}
				else
				{
					System.out.println("You reached limit of savings account");
				}
			}
			else
			{
				
				if(customer.getAmount()-amount>0)
				{
				
					System.out.println("To Confirm Transfer Enter Your Pin: ");
					Scanner scr=new Scanner(System.in);
					String pin=scr.next();
					if(customer.getPin().equals(pin))
					
					{
					reciever.setAmount(reciever.getAmount()+amount);
					session.beginTransaction().commit();
				
				customer.setAmount(customer.getAmount()-amount);
				session.beginTransaction().commit();
				
				Wait.wait_Time();

				System.out.println("Amount Deposited in "+rcvr_Act+" ...");
				
				session.close();
					}
					
						else
						{
							System.out.println("You have enter wrong pin...");
						}
				}
				else
				{
					System.out.println("Entered Amount is not Withdrawable...");
				}
				
			}
			}
		}
		}
		catch(Exception e)
		{
			System.out.println("Error: "+e.getMessage());
		}
	}
	public static void balance_Enquiry()
	{ 
		try
		{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Your Pin:");
		String pin=sc.next();
		Session session = sf1.openSession();
		Customer cust = session.get(Customer.class, account_Number);
		if(cust.getPin().equals(pin))
		{
			
			
			
			Wait.wait_Time();
			
			
			System.out.println("Your Account Balance: "+cust.getAmount());
		
			session.close();
			
		}
		else
		{
			System.out.println("Invalid Pin...");
		}
		
		
		}
		catch(Exception e)
		{
			System.out.println("Error: "+e.getMessage());
		}
		
	}
	public static void pin_Change()
	{
		try
		{
		String crt_Pin,new_Pin;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Current Pin");
		crt_Pin=sc.next();
		Session session = sf1.openSession();
		Customer customer = session.get(Customer.class, account_Number);
		if(customer.getPin().equals(crt_Pin))
		{
			Scanner sc1=new Scanner(System.in);
			System.out.println("Enter New Pin:");
			new_Pin=sc.next();
			
			if(new_Pin.length()>3 && new_Pin.length()<5)
			{
			char c;
			int count1211=0;
			for(int i=0;i<new_Pin.length();i++)
			{
				c=new_Pin.charAt(i);
				if((c>=48 && c<=57))  
				{
					count1211++;
				}
				
			}
			if((count1211)==new_Pin.length())
			{
				
				
				customer.setPin(new_Pin);
				session.beginTransaction().commit();
				 Wait.wait_Time();
				
				System.out.println("Pin Changed Successfully...");
				session.close();
				
				
			}
			else
			{
				System.out.println("In Pin only Digits(0-9) are Allow...");
			}
		}
			else
			{
				System.out.println("Pin  Length must be 4...");
			}
		}
		else
		{
			System.out.println("Entered Pin is Invalid");
		}
		}
		catch(Exception e)
		{
			System.out.println("Error: "+e.getMessage());
		}
		
	}
	public static void cash_Deposite()
	{
		try
		{
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Customer Account Number:");
		String act_No=sc.next();
		
		Session session = sf1.openSession();
		Customer cust = session.get(Customer.class, act_No);
		if(cust==null)
		{
			System.out.println("Account Number Not Found...");
		}
		else
		{
			Scanner sc1=new Scanner(System.in);
			System.out.println("Enter Amount:");
			double amt=sc.nextDouble();
			
			cust.setAmount(cust.getAmount()+amt);
			session.beginTransaction().commit();
			
			Wait.wait_Time();
			
			System.out.println("Amount Deposited "+act_No+" Account Number...");
			
			session.close();
		}
		
		
		}
	
	catch(Exception e)
	{
		System.out.println("Error: "+e.getMessage());
	}
		
	}
	
}
