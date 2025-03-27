package org.fludland.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public final class Base64Utils {
    private Base64Utils() {}

    public static String fileToBase64(InputStream file) throws IOException{
        byte[] encoded = Base64.getEncoder().encode(file.readAllBytes());
        return new String(encoded);
    }
}
