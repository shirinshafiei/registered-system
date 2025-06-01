package aut.ap.service;

import aut.ap.framework.SingletonSessionFactory;
import aut.ap.model.Person;

import java.util.List;

public class personService {
    public static void signUp(String firstName, String lastName, Integer age, String email, String password) {
        boolean emailExists = SingletonSessionFactory.get()
                .fromTransaction(session ->
                        session.createQuery(
                                        "select count(p) > 0 from Person p where p.email = :email",
                                        Boolean.class
                                )
                                .setParameter("email", email)
                                .getSingleResult()
                );

        if (emailExists) {
            throw new IllegalArgumentException("An account with this email already exists");
        }
        Person newPerson = new Person(firstName, lastName, age, email, password);
        SingletonSessionFactory.get()
                .inTransaction(session -> session.persist(newPerson));
    }
        public static Person login(String email, String password) {
            Person person = SingletonSessionFactory.get()
                    .fromTransaction(session ->
                            (Person) session.createNativeQuery(
                                            "select * from persons where email = :email", Person.class)
                                    .setParameter("email", email)
                                    .getSingleResult()
                    );
            if (person == null) {
                throw new RuntimeException("email not found");
            }
            else if (!person.getPassword().equals(password)) {
                throw new RuntimeException("password is incorrect");
            }else {
                return person;
            }

        }
}
