package com.workshop.store.infrastructure.http.textDecorator;

import java.nio.file.Path;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.workshop.store.application.export.Exporter;
import com.workshop.store.application.export.FileExporter;
import com.workshop.store.application.textDecorator.TextDecoratorService;

@Configuration
public class TextDecoratorConfiguration {

    @Bean
    Exporter exporter() {
        return new FileExporter(Path.of("output", "decorated.txt"));
    }

    @Bean
    TextDecoratorService textDecoratorService(Exporter exporter) {
        return new TextDecoratorService(exporter);
    }
}
