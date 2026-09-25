package com.workshop.store.application.export;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileExporter implements Exporter {
    private final Path filePath;

    public FileExporter(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public void export(String string) throws ExporterException {
        try {
            Files.createDirectories(filePath.toAbsolutePath().getParent());
            Files.writeString(this.filePath, string);
        } catch (IOException e) {
            throw new ExporterException("Failed to export", e);
        }
    }
}
