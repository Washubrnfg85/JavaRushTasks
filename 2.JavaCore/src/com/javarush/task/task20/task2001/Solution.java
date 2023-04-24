package com.javarush.task.task20.task2001;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Читаем и пишем в файл: Human
D:\Java_Projects\test_chars_2.txt
*/

public class Solution {
    public static void main(String[] args) {
        //исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File fileName = File.createTempFile("D:\\Java_Projects\\test_chars_2.txt", null);
            OutputStream outputStream = new FileOutputStream(fileName);
            InputStream inputStream = new FileInputStream(fileName);

            Human ivanov = new Human("Ivanov", new Asset("home", 999_999.99), new Asset("car", 2999.99));
            ivanov.save(outputStream);
            outputStream.flush();

            Human somePerson = new Human();
            somePerson.load(inputStream);
            inputStream.close();
            //check here that ivanov equals to somePerson - проверьте тут, что ivanov и somePerson равны
            System.out.println(ivanov.equals(somePerson));

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class Human {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        public Human() {
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Human human = (Human) o;

            if (name != null ? !name.equals(human.name) : human.name != null) return false;
            return assets != null ? assets.equals(human.assets) : human.assets == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (assets != null ? assets.hashCode() : 0);
            return result;
        }

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedWriter contentWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            contentWriter.write(this.name);
            contentWriter.newLine();
            for (Asset item : assets) {
                contentWriter.write(item.getName());
                contentWriter.newLine();
                contentWriter.write(Double.toString(item.getPrice()));
                contentWriter.newLine();
            }
            contentWriter.flush();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader contentReader = new BufferedReader(new InputStreamReader(inputStream));
            this.name = contentReader.readLine();
            while (contentReader.ready()) {
                this.assets.add(new Asset(contentReader.readLine(), Double.parseDouble(contentReader.readLine())));
            }
        }
    }
}
