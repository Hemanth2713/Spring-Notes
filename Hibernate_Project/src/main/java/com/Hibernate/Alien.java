package com.Hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
/*
What’s happening?: The @Entity annotation tells Hibernate this class is a data model. The entity name (e.g., Alien or AlienData) is used in HQL queries, not the database table name. It’s like the Java world’s identity for your data, separate from the database’s table structure.
When to use?: Use @Entity on any class you want Hibernate to manage as a data object. It’s your way of saying, “This is an alien I want to store or fetch from the database!”
*/
@Table
/*
What’s happening?: Hibernate maps the Alien class (entity) to a database table named alien_data. The table has columns (e.g., aId, aName, tech) based on the class’s fields. You can also rename columns using @Column(name = "alien_id") for more control.
When to use?: Use @Table when you want the database table name to differ from the entity name or class name. Handy when you’re working with an existing database or want specific table names for clarity.
*/
public class Alien {
    @Id
    @Column(name = "Alien_ID") //If we want to change the Column Name in the DataBase
    private int id;
    private String name;

    @Override
    public String toString() {
        return "Alien{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tech='" + tech + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTech() {
        return tech;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

    private String tech;
}
