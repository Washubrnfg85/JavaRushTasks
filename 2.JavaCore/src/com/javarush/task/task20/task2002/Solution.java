package com.javarush.task.task20.task2002;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Читаем и пишем в файл: JavaRush
*/

public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or adjust outputStream/inputStream according to your file's actual location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File yourFile = File.createTempFile("D:\\Java_Projects\\test_chars_2.txt", null);
            OutputStream outputStream = new FileOutputStream(yourFile);
            InputStream inputStream = new FileInputStream(yourFile);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            User jason = new User();
            jason.setFirstName("Jason");
            jason.setLastName("Born");
            jason.setBirthDate(new Date(85, 3, 26));
            jason.setMale(true);
            jason.setCountry(User.Country.OTHER);

            User vera = new User();
            vera.setFirstName("Vera");
            vera.setLastName("Smith");
            vera.setBirthDate(new Date(93, 8, 4));
            vera.setMale(false);
            vera.setCountry(User.Country.RUSSIA);

            User ivan = new User();
            ivan.setFirstName("Ivan");
            ivan.setLastName("Povetkin");
            ivan.setBirthDate(new Date(103, 10, 30));
            ivan.setMale(true);
            ivan.setCountry(User.Country.UKRAINE);

            javaRush.users.add(jason);
            javaRush.users.add(vera);
            javaRush.users.add(ivan);


            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //here check that the javaRush object is equal to the loadedObject object - проверьте тут, что javaRush и loadedObject равны
            System.out.println(javaRush.equals(loadedObject));

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Oops, something is wrong with my file");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Oops, something is wrong with the save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            PrintWriter contentWriter = new PrintWriter(outputStream);
            if (users.size() > 0) {
                for (User user : users) {
                    contentWriter.println(user.getFirstName());
                    contentWriter.println(user.getLastName());
                    contentWriter.println(user.getBirthDate().getTime());
                    contentWriter.println(user.isMale());
                    contentWriter.println(user.getCountry());
                }
                contentWriter.flush();
            }
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader contentReader = new BufferedReader(new InputStreamReader(inputStream));
            while (contentReader.ready()) {
                User newUser = new User();
                newUser.setFirstName(contentReader.readLine());
                newUser.setLastName(contentReader.readLine());
                newUser.setBirthDate(new Date (Long.parseLong(contentReader.readLine())));
                newUser.setMale(Boolean.parseBoolean(contentReader.readLine()));
                newUser.setCountry(User.Country.valueOf(contentReader.readLine()));
                this.users.add(newUser);
                continue;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
