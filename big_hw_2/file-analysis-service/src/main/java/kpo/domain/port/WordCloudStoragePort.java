package kpo.domain.port;

import java.io.IOException;

public interface WordCloudStoragePort {
    String storeWordCloud(byte[] imageData, String fileExtension) throws IOException;
    byte[] getWordCloud(String filePath) throws IOException;
}
