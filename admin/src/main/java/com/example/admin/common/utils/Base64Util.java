package com.example.admin.common.utils;

import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class Base64Util {
    public static MultipartFile convertBase64ToMultipartFile(String base64, String filename) throws IOException {
        byte[] decodedBytes = Base64Utils.decodeFromString(base64);

        return new MultipartFile() {
            @Override
            public String getName() {
                return filename;
            }

            @Override
            public String getOriginalFilename() {
                return filename;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return decodedBytes.length == 0;
            }

            @Override
            public long getSize() {
                return decodedBytes.length;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return decodedBytes;
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return new ByteArrayInputStream(decodedBytes);
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {
                Files.write(dest.toPath(), decodedBytes);
            }
        };
    }
}
