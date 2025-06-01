// FileStorageServiceImpl.java
package kpo.presentation.grpc;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import kpo.application.usecases.GetFileUseCase;
import kpo.application.usecases.UploadFileUseCase;
import kpo.domain.model.File;
import kpo.grpc.file_storage.*;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class FileStorageServiceImpl extends FileStorageServiceGrpc.FileStorageServiceImplBase {
    private final UploadFileUseCase uploadFileUseCase;
    private final GetFileUseCase getFileUseCase;

    @Override
    public void uploadFile(UploadFileRequest request, StreamObserver<UploadFileResponse> responseObserver) {
        try {
            File file = uploadFileUseCase.execute(request.getFilename(), request.getContent());
            responseObserver.onNext(UploadFileResponse.newBuilder().setFileId(file.getId()).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
        }
    }

    @Override
    public void getFile(GetFileRequest request, StreamObserver<GetFileResponse> responseObserver) {
        try {
            GetFileUseCase.FileContent content = getFileUseCase.execute(request.getFileId());
            responseObserver.onNext(GetFileResponse.newBuilder()
                    .setFilename(content.filename())
                    .setContent(content.content())
                    .build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException());
        }
    }
}