package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Customer> customerList = new ArrayList<>();

        //Customer 1 initialization - has street 2 address
        Address shippingAddress = new Address("2414 Leighton St","Unit A","Fort Lee","NJ","07024");
        Address billingAddress = new Address("42 Gentry Dr.","Suite B","Englewood","NJ","07631");
        Customer customer1 = new Customer("Hyunseok","Kahng","hkahng92@gmail.com","201-245-7837",true,shippingAddress,billingAddress);

        //customer 2 intialization - no street 2 address
        Address shipping2Address = new Address("12 Haring St","Closter","NJ","07004");
        //Address billing2Address = shipping2Address;
        Customer customer2 = new Customer("Hyunseok","Kahng","hkahng92@gmail.com","201-245-7837",true,shipping2Address,shipping2Address);

        //adding customer(s) to the customer list
        customerList.add(customer1);
        customerList.add(customer2);

        //iterator to go through the entire customerLIst
        Iterator<Customer> iter = customerList.iterator();
        while(iter.hasNext()){
            Customer customer = iter.next();
            System.out.println("========Customer Information========");
            System.out.println("Name: " + customer.getFirstName() + " " + customer.getLastName());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("Phone: " + customer.getPhoneNumber());
            System.out.println("Rewards Member: " + (customer.isRewardsMember() ? "Yes" : "No"));
            System.out.println("Shipping Address: ");
            Address shipping = customer.getShippingAddress();
            System.out.print(shipping.getStreet1() + " ");
            if(shipping.getStreet2() != null) {
                System.out.print(shipping.getStreet2() + " ");
            }
            System.out.print(shipping.getCity() + " ");
            System.out.print(shipping.getState() + " ");
            System.out.print(shipping.getZip() + " ");

           // System.out.println("------------------------------------");
            System.out.println("\nBilling Address:");
            Address billing = customer.getBillingAddress();
            System.out.print(billing.getStreet1() + " ");
            if(shipping.getStreet2() != null) {
                System.out.print(billing.getStreet2() + " ");
            }
            System.out.print(billing.getCity() + " ");
            System.out.print(billing.getState() + " ");
            System.out.print(billing.getZip() + " ");
            System.out.println("\n====================================\n");


        }
//        Address billAddress = customer1.getBillingAddress();
//        System.out.println(billAddress.getStreet1());
//        System.out.println(billAddress.getCity());
//        System.out.println(billAddress.getState());
//        System.out.println(billAddress.getZip());

    }
}
