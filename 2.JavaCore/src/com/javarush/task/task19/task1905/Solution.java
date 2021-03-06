package com.javarush.task.task19.task1905;

import java.util.HashMap;
import java.util.Map;

/* 
Закрепляем адаптер
*/

public class Solution {
    public static Map<String,String> countries = new HashMap<String,String>();

    static {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }

    public static void main(String[] args) {

    }

    public static class DataAdapter implements RowItem {
        private Customer customer;
        private Contact contact;

        public DataAdapter(Customer customer, Contact contact) {
            this.customer = customer;
            this.contact = contact;
        }

        @Override
        public String getCountryCode() {
            String result = null;
            for (Map.Entry<String, String> pair : countries.entrySet()) {
                if (pair.getValue().equals(this.customer.getCountryName())) {
                    result = pair.getKey();
                    break;
                }
            }
            return result;
        }

        @Override
        public String getCompany() {
            return this.customer.getCompanyName();
        }

        @Override
        public String getContactFirstName() {
//            String fullName[] = this.contact.getName().trim().split(", ");
            return this.contact.getName().split(", ")[1];
        }

        @Override
        public String getContactLastName() {
//            String fullName[] = this.contact.getName().trim().split(", ");
            return this.contact.getName().split(", ")[0];
        }

        @Override
        public String getDialString() {
//            String phoneNumber = null;
//            String[] phone = this.contact.getPhoneNumber().trim().split("\\D");
//            for(String s : phone) {
//                phoneNumber = phoneNumber + s;
//            }
//            phoneNumber = "callto://" + this.contact.getPhoneNumber().replaceAll("[()-]", "");
//            return "callto://" + this.contact.getPhoneNumber().replaceAll("[()-]", "");
            String result = "callto://+" + this.contact.getPhoneNumber().replaceAll("\\D", "");
            return result;
        }
    }

    public static interface RowItem {
        String getCountryCode();        //example UA
        String getCompany();            //example JavaRush Ltd.
        String getContactFirstName();   //example Ivan
        String getContactLastName();    //example Ivanov
        String getDialString();         //example callto://+380501234567
    }

    public static interface Customer {
        String getCompanyName();        //example JavaRush Ltd.
        String getCountryName();        //example Ukraine
    }

    public static interface Contact {
        String getName();               //example Ivanov, Ivan
        String getPhoneNumber();        //example +38(050)123-45-67
    }
}