package service;

import models.Contact;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ContactServiceImpl implements ContactService {
    private Scanner scanner = new Scanner(System.in);
    private Scanner numscanner = new Scanner(System.in);

    @Override
    public void add_contact() {
        System.out.print("Enter name : ");
        String name = scanner.next();
        System.out.print("Enter surname : ");
        String surname = scanner.next();
        System.out.print("Enter phone : ");
        String phone = scanner.next();

        ContactRepository contactRepository = new ContactRepository();
        Contact exists = contactRepository.phoneIsHave(phone);
        if (exists != null) {
            System.out.println("phone is already exists!!");
        }else if (phone.length() == 12) {
            Contact contact = new Contact();
            contact.setName(name);
            contact.setSurname(surname);
            contact.setPhone(phone);
            contactRepository.add_contact(contact);
        } else {
            System.err.println("number length is not valid!!!");
        }
    }

    @Override
    public void delete_contact() {
        System.out.print("delete phone : ");
        String phone = scanner.next();
        if (phone.length() == 12) {
            ContactRepository contactRepository = new ContactRepository();
            contactRepository.delete_contact(phone);
        } else {
            System.err.println("something went wrong. MAZGIII!!!");
        }
    }

    @Override
    public void contact_list() {
        ContactRepository contactRepository = new ContactRepository();
        List<Contact> contactList = contactRepository.contact_list();
        for (Contact contact : contactList) {
            System.out.println(contact);
        }
    }

    @Override
    public void search_contact() {
        System.out.println("Search query : ");
        String query = scanner.next();

        ContactRepository repository = new ContactRepository();
        List<Contact> contactList = repository.search_contact(query);
        for (Contact contact : contactList) {
            System.out.println(contact);
        }

    }

    @Override
    public void show_menu() {
        DatabaseUtil.createTable();
        boolean b = true;
        while (b) {
            System.out.println("1 => add contact");
            System.out.println("2 => delete contact");
            System.out.println("3 => list of contact");
            System.out.println("4 => search contact");
            System.out.println("0 => Exit");
            int action = numscanner.nextInt();
            switch (action) {
                case 1:
                    add_contact();
                    break;
                case 2:
                    delete_contact();
                    break;
                case 3:
                    contact_list();
                    break;
                case 4:
                    search_contact();
                    break;
                case 0:
                    b = false;
                    break;
                default:
                    b = false;
                    break;
            }
        }
    }
}
