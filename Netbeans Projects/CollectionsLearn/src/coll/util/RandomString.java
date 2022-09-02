package coll.util;

import java.nio.charset.Charset;
import java.util.Random;

public class RandomString {

    public static String getString(int length) {
        byte[] array = new byte[length]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return generatedString;
    }
    
}
