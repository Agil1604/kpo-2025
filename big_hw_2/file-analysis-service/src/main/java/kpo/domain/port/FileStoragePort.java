package kpo.domain.port;

import kpo.domain.exception.FileNotFoundException;
import kpo.domain.exception.ServiceUnavailableException;
import kpo.domain.model.FileContent;

public interface FileStoragePort {
    FileContent get(int fileId) throws FileNotFoundException, ServiceUnavailableException;
}