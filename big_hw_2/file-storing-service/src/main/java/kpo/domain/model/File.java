package kpo.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class File {
    private Integer id;
    private String fileName;
    private String filePath;
    private String fileHash;

    public File(String fileName, String filePath, String fileHash) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileHash = fileHash;
    }
}