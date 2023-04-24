package com.javarush.task.task18.task1822;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Поиск данных внутри файла
C:\Users\Aleksandr Sergeev\Downloads\test_chars_3.txt
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            return;
        }

        String fileName = new BufferedReader(new InputStreamReader(System.in)).readLine();
        try (BufferedReader fileAsLinesReader = new BufferedReader(new FileReader(fileName))) {

            List<String> listOfLines = new ArrayList<>();
            while (fileAsLinesReader.ready()) {
                listOfLines.add(fileAsLinesReader.readLine());
            }

            Product foundProduct = null;
            for (String line : listOfLines) {
                String[] parameters = line.split(" ");
                if (parameters[0].equals(args[0])) {
                    foundProduct = new Product(Integer.parseInt(parameters[0]), parameters[1], Double.parseDouble(parameters[2]), Integer.parseInt(parameters[3]));
                }
            }

            System.out.printf("%d %s %.1f %d%n",
                    foundProduct.getId(),
                    foundProduct.getProductName(),
                    foundProduct.getPrice(),
                    foundProduct.getQuantity());
        }
    }

    public static class Product {
        private int id;
        private String productName;
        private double price;
        private int quantity;

        public Product(int id, String productName, double price, int quantity) {
            this.id = id;
            this.productName = productName;
            this.price = price;
            this.quantity = quantity;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
