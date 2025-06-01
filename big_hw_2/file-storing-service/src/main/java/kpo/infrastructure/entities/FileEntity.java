package kpo.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "files")
@NoArgsConstructor
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false, unique = true)
    private String filePath;

    @Column(length = 64, unique = true)
    private String fileHash;

    public FileEntity(String fileName, String filePath, String fileHash) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileHash = fileHash;
    }
}