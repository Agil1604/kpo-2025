package kpo.infrastructure.adapters;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import kpo.domain.exception.FileNotFoundException;
import kpo.domain.exception.ServiceUnavailableException;
import kpo.domain.model.FileAnalysisContent;
import kpo.domain.port.FileAnalysisPort;
import kpo.grpc.file_analysis.AnalysisResult;
import kpo.grpc.file_analysis.AnalyzeFileRequest;
import kpo.grpc.file_analysis.FileAnalysisServiceGrpc;
import kpo.grpc.file_analysis.GetCloudResult;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

@Component
public class GrpcFileAnalysisAdapter implements FileAnalysisPort {

    @GrpcClient("file-analysis-service")
    private FileAnalysisServiceGrpc.FileAnalysisServiceBlockingStub fileAnalysisStub;

    @Override
    public FileAnalysisContent analyze(int id) {
        AnalyzeFileRequest request = AnalyzeFileRequest.newBuilder()
                .setFileId(id)
                .build();
        try {
            AnalysisResult response = fileAnalysisStub.analyzeFile(request);
            return new FileAnalysisContent(
                    response.getAnalysisId(),
                    response.getFileId(),
                    response.getStats().getParagraphs(),
                    response.getStats().getWords(),
                    response.getStats().getSymbols(),
                    response.getStats().getPlagiarism());
        } catch (StatusRuntimeException e) {
            if (e.getStatus().getCode() == Status.Code.NOT_FOUND) {
                throw new FileNotFoundException("File not found with id: " + id);
            }
            if (e.getStatus().getCode() == Status.Code.UNAVAILABLE) {
                throw new ServiceUnavailableException("Storage service unavailable");
            }
            throw new RuntimeException("Get file failed", e);
        }
    }

    @Override
    public byte[] getCloud(int id) throws FileNotFoundException, ServiceUnavailableException {
        kpo.grpc.file_analysis.GetCloudRequest request =
                kpo.grpc.file_analysis.GetCloudRequest.newBuilder()
                        .setFileId(id)
                        .build();

        try {
            GetCloudResult response = fileAnalysisStub.getCloud(request);
            return response.getContent().toByteArray();
        } catch (StatusRuntimeException e) {
            if (e.getStatus().getCode() == Status.Code.NOT_FOUND) {
                throw new FileNotFoundException("Cloud not found for file: " + id);
            }
            if (e.getStatus().getCode() == Status.Code.UNAVAILABLE) {
                throw new ServiceUnavailableException("Analysis service unavailable");
            }
            throw new RuntimeException("Get cloud failed", e);
        }
    }
}