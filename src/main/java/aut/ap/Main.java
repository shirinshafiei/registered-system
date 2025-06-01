package aut.ap;

import aut.ap.framework.SingletonSessionFactory;
import aut.ap.model.Person;
import aut.ap.service.personService;

import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter [L]ogin or [S]ignup: ");
        String command = sc.nextLine();
        switch (command)  {
            case "L" :
                System.out.println("Email: ");
                String email = sc.nextLine();
                System.out.println("password: ");
                String password = sc.nextLine();
                try {
                    Person p = personService.login(email, password);
                    System.out.println("welcome" + " " + p.getFirstName() + " " + p.getLastName());
                }catch (RuntimeException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case "S" :
                System.out.println("First name: ");
                String firstName = sc.nextLine();
                System.out.println("Last name: ");
                String lastName = sc.nextLine();
                System.out.println("age: ");
                int age = sc.nextInt();
                sc.nextLine();
                System.out.println("email: ");
                email = sc.nextLine();
                System.out.println("password: ");
                password = sc.nextLine();
                while (password.length() < 8) {
                System.out.println("Weak password");
                password = sc.nextLine();
                }
                try {
                    personService.signUp(firstName, lastName, age, email, password);
                }catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
                break;
            default:
                System.out.println("invalid option.");
        }
        SingletonSessionFactory.close();

    }
}