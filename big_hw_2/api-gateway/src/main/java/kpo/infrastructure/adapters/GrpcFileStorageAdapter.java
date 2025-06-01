package kpo.infrastructure.adapters;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import kpo.domain.model.FileContent;
import kpo.domain.exception.FileNotFoundException;
import kpo.domain.exception.ServiceUnavailableException;
import kpo.domain.port.FileStoragePort;
import kpo.grpc.file_storage.*;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

@Component
public class GrpcFileStorageAdapter implements FileStoragePort {

    @GrpcClient("file-storing-service")
    private FileStorageServiceGrpc.FileStorageServiceBlockingStub fileStorageStub;

    @Override
    public int upload(String filename, String content) {
        UploadFileRequest request = UploadFileRequest.newBuilder()
                .setFilename(filename)
                .setContent(content)
                .build();
        try {
            UploadFileResponse response = fileStorageStub.uploadFile(request);
            return response.getFileId();
        } catch (StatusRuntimeException e) {
            if (e.getStatus().getCode() == Status.Code.UNAVAILABLE) {
                throw new ServiceUnavailableException("Storage service unavailable");
            }
            throw new RuntimeException("Upload failed", e);
        }
    }

    @Override
    public FileContent get(int fileId) {
        GetFileRequest request = GetFileRequest.newBuilder()
                .setFileId(fileId)
                .build();
        try {
            GetFileResponse response = fileStorageStub.getFile(request);
            return new FileContent(fileId, response.getFilename(), response.getContent());
        } catch (StatusRuntimeException e) {
            if (e.getStatus().getCode() == Status.Code.NOT_FOUND) {
                throw new FileNotFoundException("File not found with id: " + fileId);
            }
            if (e.getStatus().getCode() == Status.Code.UNAVAILABLE) {
                throw new ServiceUnavailableException("Storage service unavailable");
            }
            throw new RuntimeException("Get file failed", e);
        }
    }
}