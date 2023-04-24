package com.javarush.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2
С и I решены, осталось дорешать U и D
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat dateFormater1 = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        SimpleDateFormat dateFormater2 = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        switch (args[0]) {
            case "-c" :
                Person person;
                synchronized (allPeople) {
                    for (int i = 0; i <= args.length - 4; i += 3) {
                        if (args[2 + i].equals("м")) {
                            person = Person.createMale(args[1 + i], dateFormater1.parse(args[3 + i]));
                        } else {
                            person = Person.createFemale(args[1 + i], dateFormater1.parse(args[3 + i]));
                        }
                        allPeople.add(person);
                        System.out.println(allPeople.indexOf(person));
                    }
                }
                break;
            case "-u" :
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i = i + 4) {
                        int id = Integer.parseInt(args[i]);
                        String name = args[i + 1];
                        String sex = args[i + 2];
                        Date date = dateFormater1.parse(args[i + 3]);
                        allPeople.get(id).setName(name);
                        allPeople.get(id).setSex("м".equals(sex) ? Sex.MALE : Sex.FEMALE);
                        allPeople.get(id).setBirthDate(date);
                    }
                }
                break;
            case "-d" :
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i++) {
                        person = allPeople.get(Integer.parseInt(args[i]));
                        person.setName(null);
                        person.setSex(null);
                        person.setBirthDate(null);
                    }
                }
                break;
            case "-i" :
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i++) {
                        person = allPeople.get(Integer.parseInt(args[i]));
                        String sex = person.getSex().equals(Sex.MALE) ? "м" : "ж";
                        System.out.println(person.getName() + " " + sex + " " + dateFormater2.format(person.getBirthDate()));
                    }
                }
                break;
        }
    }

    public static Sex makeSex (String letter) {
        return letter.equals("m") ? Sex.MALE : Sex.FEMALE;
    }
}
