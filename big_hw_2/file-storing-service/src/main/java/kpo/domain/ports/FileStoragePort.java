package kpo.domain.ports;

import java.io.IOException;

public interface FileStoragePort {
    String storeFile(String filename, String content) throws IOException;
    String readFileContent(String filePath) throws IOException;
}