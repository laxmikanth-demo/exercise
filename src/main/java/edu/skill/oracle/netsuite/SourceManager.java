package edu.skill.oracle.netsuite;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SourceManager {

    private String fileName;

    public SourceManager(String fileName) {
        this.fileName = fileName;
    }

    public List<String> getContent() throws Exception {
        return Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource(fileName).toURI()));
    }
}
