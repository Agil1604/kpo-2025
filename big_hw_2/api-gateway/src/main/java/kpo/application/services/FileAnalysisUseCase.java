    package kpo.application.services;

    import kpo.domain.exception.FileNotFoundException;
    import kpo.domain.exception.ServiceUnavailableException;
    import kpo.domain.model.FileAnalysisContent;
    import kpo.domain.port.FileAnalysisPort;
    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Service;

    @Service
    @RequiredArgsConstructor
    public class FileAnalysisUseCase {

        private final FileAnalysisPort fileAnalysisPort;

        public FileAnalysisContent analyzeFile(int id) {
            try {
                return fileAnalysisPort.analyze(id);
            } catch (FileNotFoundException | ServiceUnavailableException e) {
                throw e;
            } catch (Exception e) {
                throw new RuntimeException("Analysis failed for file: " + id, e);
            }
        }

        public byte[] getCloud(int id) { // Теперь возвращает byte[]
            try {
                return fileAnalysisPort.getCloud(id);
            } catch (FileNotFoundException | ServiceUnavailableException e) {
                throw e;
            } catch (Exception e) {
                throw new RuntimeException("Failed to get cloud for file: " + id, e);
            }
        }
    }
