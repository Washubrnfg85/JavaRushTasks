package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/* 
Генератор паролей
*/

public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream outputStreamOfBytes = new ByteArrayOutputStream();        // сюда будем передвать готовый парольи и возвращать из метода
        byte[] symbols = new byte[8];           //массив на 8 ячеек - по одной для каждого символа пароля
        boolean validPassword = false;          //проверка на требования к паролю (три требования). Реализована в строке 32;
        while (!validPassword) {
            for (int i = 0; i < symbols.length; i++) {              //проход по каждой ячейке и генерация в ней валидного байта
                boolean digitOrLetter = Math.random() < 0.5;        //если рандом меньше 0.5, то будет цифра, если больше - то буква
                boolean lowCaseOrUpperCase = Math.random() > 0.4;   //если рандом больше 0.4, то будет большая буква, если меньше - то маленькая
                symbols[i] = digitOrLetter ? (byte) (Math.random() * (57 - 48 + 1) + 48) :      //срабатывает первый рандом - цифра или буква
                        (lowCaseOrUpperCase ? (byte) (Math.random() * (90 - 65 + 1) + 65) :     //срабатывает второй рандом - большая буква
                                (byte) (Math.random() * (122 - 97 + 1) + 97));                  //или маленькая
            }

            String password = new String(symbols, StandardCharsets.UTF_8);      //переводим архив байт в строку и проверяем регулярками что все требования исполнены
            if (password.matches(".*[0-1].*") && password.matches(".*[a-z].*") && password.matches(".*[A-Z].*")) {
                validPassword = true;           //если требования являются true, то переключаем рубильник и выходим из цикла
            }
        }
        //пишем архив байт, из которого получился пароль в ByteArrayOutputStream
        try {
            outputStreamOfBytes.write(symbols);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return outputStreamOfBytes;     //и возвращаем результат из метода
    }
}
