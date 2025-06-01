package kpo.domain.port;

import kpo.domain.model.FileContent;

public interface FileStoragePort {
    int upload(String filename, String content);
    FileContent get(int fileId);
}