package com.javarush.task.task16.task1631;

import com.javarush.task.task16.task1631.common.*;

public class ImageReaderFactory {
    public static ImageReader getImageReader(ImageTypes type) {
        try {
            switch (type.name()) {
                case "PNG" :
                    return new PngReader();
                case "JPG" :
                    return new JpgReader();
                case "BMP" :
                    return new BmpReader();
                default :
                    throw new IllegalArgumentException();
            }
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
