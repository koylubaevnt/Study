package com.koylubaevnt.stepik.java.basecourse.functional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PersonDemo {

	public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();

        persons.add(new Person("Alise", 100, 22));
        persons.add(new Person("Bob", 50, 16));

        // sort by Name
        persons.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        System.out.println(persons);
        
        // sort by Age, new style
        persons.sort((o1, o2) -> Integer.compare(o1.getAge(), o2.getAge()));

        persons.sort(new IntComp());
        
        // sort by Age, old style
        persons.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        System.out.println(persons);
    }
	
}

class IntComp implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return o1.getAge() - o2.getAge();
    }
}

class Person {
    private String name;
    private int score;
    private int age;

    public Person(String name, int score, int age) {
        this.name = name;
        this.score = score;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
