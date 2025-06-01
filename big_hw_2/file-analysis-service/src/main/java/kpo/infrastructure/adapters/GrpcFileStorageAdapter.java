package kpo.infrastructure.adapters;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import kpo.domain.exception.FileNotFoundException;
import kpo.domain.exception.ServiceUnavailableException;
import kpo.domain.model.FileContent;
import kpo.grpc.file_storage.GetFileRequest;
import kpo.grpc.file_storage.GetFileResponse;
import kpo.grpc.file_storage.FileStorageServiceGrpc;
import kpo.domain.port.FileStoragePort;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

@Component
public class GrpcFileStorageAdapter implements FileStoragePort {

    @GrpcClient("file-storing-service")
    private FileStorageServiceGrpc.FileStorageServiceBlockingStub fileStorageStub;

    @Override
    public FileContent get(int fileId) throws FileNotFoundException, ServiceUnavailableException {
        GetFileRequest request = GetFileRequest.newBuilder().setFileId(fileId).build();
        try {
            GetFileResponse response = fileStorageStub.getFile(request);
            return new FileContent(fileId, response.getFilename(), response.getContent());
        } catch (StatusRuntimeException e) {
            if (e.getStatus().getCode() == Status.Code.NOT_FOUND) {
                throw new FileNotFoundException("File not found: " + fileId);
            }
            if (e.getStatus().getCode() == Status.Code.UNAVAILABLE) {
                throw new ServiceUnavailableException("File storage unavailable");
            }
            throw new RuntimeException("gRPC error", e);
        }
    }
}