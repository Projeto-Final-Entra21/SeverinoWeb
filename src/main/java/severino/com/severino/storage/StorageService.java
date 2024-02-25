package severino.com.severino.storage;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

	void init();

	void store(MultipartFile file);

	Stream<Path> loadAll();

	Path load(String filename);

	Resource loadAsResource(String filename);

	void deleteAll();

    @SpringBootApplication
    @EnableConfigurationProperties(StorageProperties.class)
    class UploadingFilesApplication {

        public static void main(String[] args) {
            SpringApplication.run(UploadingFilesApplication.class, args);
        }

        @Bean
        CommandLineRunner init(StorageService storageService) {
            return (args) -> {
                storageService.init();
            };
        }
    }
}
