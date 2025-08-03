package com.Hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


//TIP: To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Alien alien = new Alien();
        alien.setId(103);
        alien.setName("Hem");
        alien.setTech("CSS");

//        Configuration config = new Configuration();
//        config.addAnnotatedClass(com.Hibernate.Alien.class);
//        config.configure("hibernate.cfg.xml"); // If we are using any name instead of hibernate.cfg.xml, we have to mention that XML file name here.

        // We can do all the above code in a single line
        SessionFactory factory = new Configuration()
                .addAnnotatedClass(com.Hibernate.Alien.class)
                .configure()
                .buildSessionFactory();

        Session session = factory.openSession();

        /* `By mentioning all the above details correctly, we can run the program,
           but we can't store the data in the database because, in database or RDBMS concepts,
           whenever we perform transactions (i.e., when we try to save or delete data),
           it must be done within a transaction. Here, we are opening the session and saving the data,
           but we also have to commit the transaction — committing is essential.
           A commit is a part of the transaction, so we need to include the transaction details. */

        Transaction transaction = session.beginTransaction();

        session.persist(alien); // Here persist means save()

        session.merge(alien); // To update the data. If the data is not present, it will create a new entry.

        Alien removeAlien = session.find(Alien.class, 103);
        session.remove(removeAlien); // To delete the data from the database.
        transaction.commit();

        // For Fetching the Data we don't need to use the Transaction
        // To get the Data
        Alien fetchedAlien1 = session.find(Alien.class, 101);
        // We can also fetch the data by using another format
        Alien fetchedAlien2 = session.byId(Alien.class).load(102);
        System.out.println("Fetched using find: " + fetchedAlien1);
        System.out.println("Fetched using byId.load: " + fetchedAlien2);

        /*
        **Lazy Fetching**:
        - **What is it?**: Lazy fetching is when Hibernate waits to grab data from the database until you actually need it. It’s like saying, “Hey, I’ll only fetch this data when you ask for it!” The query doesn’t run until you try to use the object.
        - **How it works**: You use methods like `getReference` or `byId` (in Hibernate 7). No query is fired until you access the object’s properties, like printing it or using its values.
        - **Code Example**:
          ```java
          Alien a1 = session.getReference(Alien.class, 103); // No query fired yet, just chilling
          System.out.println(a1); // Boom! Query fires now because you’re accessing a1
          ```
        - **What’s happening?**: If you don’t touch the object (e.g., don’t print or use it), Hibernate won’t bother hitting the database. It’s super efficient if you’re not sure you’ll need the data.
        - **When to use?**: Use lazy fetching when you’re like, “I might not need this data, so let’s not waste a database call.” Perfect for big datasets where you don’t want to load everything upfront.

        **Eager Fetching**:
        - **What is it?**: Eager fetching is when Hibernate grabs the data from the database right away, as soon as you call the fetch method. It’s like, “I’m getting this data now, whether you use it or not!”
        - **How it works**: You use methods like `get` or `find` (in Hibernate 7). The query fires immediately, pulling the data into memory even if you don’t use it later.
        - **Code Example**:
          ```java
          Alien a1 = session.find(Alien.class, 103); // Query fires right away, no waiting!
          // Data is already in memory, even if you don’t use a1
          ```
        - **What’s happening?**: Hibernate runs the query as soon as you call `find` or `get`. It doesn’t care if you use the object later; the data is fetched and ready.
        - **When to use?**: Use eager fetching when you’re sure you’ll need the data, like when you know you’re going to display or process it. No need to wait, just get it done!

        **Key Difference, Alien Style**:
        - **Lazy Fetching** (`getReference`, `byId`): Hibernate’s like, “I’m a chill alien, I’ll fetch data only when you poke me.” Saves resources if you might skip using the object.
        - **Eager Fetching** (`get`, `find`): Hibernate’s like, “I’m an eager alien, grabbing data now because I know you’ll want it!” Great when you’re certain you need the data pronto.

        **Pro Tip**: Lazy fetching is awesome for performance when you’re unsure about using the data, but eager fetching is your go-to when you know you’ll need it. Choose wisely, fellow alien coder! 🚀
        */

        session.close();
        factory.close();
    }
}
