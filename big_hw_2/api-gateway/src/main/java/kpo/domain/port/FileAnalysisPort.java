package kpo.domain.port;

import kpo.domain.exception.FileNotFoundException;
import kpo.domain.exception.ServiceUnavailableException;
import kpo.domain.model.FileAnalysisContent;

public interface FileAnalysisPort {
    FileAnalysisContent analyze(int id) throws FileNotFoundException, ServiceUnavailableException;
    byte[] getCloud(int id) throws FileNotFoundException, ServiceUnavailableException;
}
