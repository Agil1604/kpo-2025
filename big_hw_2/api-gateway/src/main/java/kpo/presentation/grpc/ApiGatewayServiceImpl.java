package kpo.presentation.grpc;

import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import kpo.domain.model.FileAnalysisContent;
import kpo.application.services.FileAnalysisUseCase;
import kpo.application.services.FileStorageUseCase;
import kpo.domain.model.FileContent;
import kpo.grpc.api_gateway.*;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class ApiGatewayServiceImpl extends ApiGatewayServiceGrpc.ApiGatewayServiceImplBase {

    private final FileStorageUseCase storageUseCase;
    private final FileAnalysisUseCase analysisUseCase;

    @Override
    public void uploadFile(UploadFileRequest request,
                           StreamObserver<UploadFileResponse> responseObserver) {
        try {
            int fileId = storageUseCase.uploadFile(
                    request.getFilename(),
                    request.getContent()
            );
            responseObserver.onNext(UploadFileResponse.newBuilder()
                    .setFileId(fileId)
                    .build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void getFile(GetFileRequest request, StreamObserver<GetFileResponse> responseObserver) {
        try {
            FileContent file = storageUseCase.getFile(request.getFileId());
            responseObserver.onNext(GetFileResponse.newBuilder()
                    .setContent(file.content())
                    .setFilename(file.filename())
                    .build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void analyzeFile(AnalyzeFileRequest request, StreamObserver<AnalysisResult> responseObserver) {
        try {
            FileAnalysisContent file = analysisUseCase.analyzeFile(request.getFileId());
            responseObserver.onNext(AnalysisResult.newBuilder()
                            .setAnalysisId(file.analysis_id())
                            .setFileId(file.file_id())
                            .setStats(TextStats.newBuilder()
                                    .setParagraphs(file.paragraphs())
                                    .setWords(file.words())
                                    .setSymbols(file.symbols())
                                    .setPlagiarism(file.plagiarism())
                                    .build())
                    .build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void getCloud(GetCloudRequest request, StreamObserver<GetCloudResult> responseObserver) {
        try {
            byte[] cloudImage = analysisUseCase.getCloud(request.getFileId());
            responseObserver.onNext(GetCloudResult.newBuilder()
                    .setContent(ByteString.copyFrom(cloudImage))
                    .build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }
}