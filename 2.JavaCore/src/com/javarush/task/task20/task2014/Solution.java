package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/* 
Serializable Solution
*/

public class Solution implements Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file = new File("D:\\Java_Projects\\Properties.properties");
        InputStream inputStream = new FileInputStream(file);
        ObjectInputStream loader = new ObjectInputStream(inputStream);
        OutputStream outputStream = new FileOutputStream(file);
        ObjectOutputStream saver = new ObjectOutputStream(outputStream);

        Solution savedObject = new Solution(5);
        saver.writeObject(savedObject);
        saver.flush();
        outputStream.close();
        saver.close();

        Solution loadedObject = (Solution) loader.readObject();
        inputStream.close();
        loader.close();
        System.out.println(savedObject.string.equals(loadedObject.string));

    }

    private transient final String pattern = "dd MMMM yyyy, EEEE";
    private transient Date currentDate;
    private transient int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and the current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}
