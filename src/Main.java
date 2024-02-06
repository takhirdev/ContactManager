import service.ContactServiceImpl;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ContactServiceImpl contactService = new ContactServiceImpl();
        contactService.show_menu();
    }
}
