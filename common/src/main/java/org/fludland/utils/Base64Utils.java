package org.fludland.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public final class Base64Utils {
    private Base64Utils() {}

    public static String fileToBase64(final InputStream file) throws IOException{
        byte[] encoded = Base64.getEncoder().encode(file.readAllBytes());
        return new String(encoded);
    }

    public static InputStream base64ToFile(final String base64) {
        byte[] decode = Base64.getDecoder().decode(base64);

        return new ByteArrayInputStream(decode);
    }
}
