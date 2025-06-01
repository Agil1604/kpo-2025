package kpo.presentation.grpc;

import com.google.protobuf.ByteString;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import kpo.domain.exception.CloudNotFoundException;
import kpo.domain.exception.FileNotFoundException;
import kpo.domain.exception.ServiceUnavailableException;
import kpo.domain.model.AnalyzeResult;
import kpo.grpc.file_analysis.*;
import kpo.application.usecases.AnalyzeFileUseCase;
import kpo.application.usecases.GetCloudUseCase;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class FileAnalysisServiceImpl extends FileAnalysisServiceGrpc.FileAnalysisServiceImplBase {

    private final AnalyzeFileUseCase analyzeFileUseCase;
    private final GetCloudUseCase getCloudUseCase;

    @Override
    public void analyzeFile(AnalyzeFileRequest request, StreamObserver<AnalysisResult> responseObserver) {
        try {
            AnalyzeResult analyzeResult = analyzeFileUseCase.execute(request.getFileId());
            responseObserver.onNext(AnalysisResult.newBuilder()
                    .setAnalysisId(analyzeResult.getAnalyzeId())
                    .setFileId(analyzeResult.getFileId())
                    .setStats(TextStats.newBuilder()
                            .setParagraphs(analyzeResult.getParagraphs())
                            .setWords(analyzeResult.getWords())
                            .setSymbols(analyzeResult.getSymbols())
                            .setPlagiarism(analyzeResult.getPlagiarism())
                            .build())
                    .build());
            responseObserver.onCompleted();
        } catch (FileNotFoundException e) {
            responseObserver.onError(Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException());
        } catch (ServiceUnavailableException e) {
            responseObserver.onError(Status.UNAVAILABLE.withDescription(e.getMessage()).asRuntimeException());
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL.withDescription("Internal error").asRuntimeException());
        }
    }

    @Override
    public void getCloud(GetCloudRequest request, StreamObserver<GetCloudResult> responseObserver) {
        try {
            byte[] cloudContent = getCloudUseCase.execute(request.getFileId());
            responseObserver.onNext(GetCloudResult.newBuilder()
                    .setContent(ByteString.copyFrom(cloudContent))
                    .build());
            responseObserver.onCompleted();
        } catch (CloudNotFoundException e) {
            responseObserver.onError(Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException());
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL.withDescription("Failed to get cloud").asRuntimeException());
        }
    }
}
