# FileUploadsWork


### File Structure
```pre
+ FileUploadsWork\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\suji\fileuploads
		|---  FileUploadsWorkApplication.java
	+ \src\test\java\com\suji\fileuploads
		|---  FileUploadsWorkApplicationTests.java
	+ \src\main\java\com\suji\fileuploads\model
		|---  FileInfo.java
	+ \src\main\java\com\suji\fileuploads\controller
		|---  FilesController.java
	+ \src\main\java\com\suji\fileuploads\service
		|---  FilesStorageService.java
	+ \src\main\java\com\suji\fileuploads\exception
		|---  FileUploadExceptionAdvice.java
	+ \src\main\java\com\suji\fileuploads\message
		|---  ResponseMessage.java
	+ \src\main\java\com\suji\fileuploads\service\impl
		|---  FilesStorageServiceImpl.java
```
### Index
```pre
1. application.properties
2. model\FileInfo.java
3. controller\FilesController.java
4. service\FilesStorageService.java
5. src\main\java\com\suji\fileuploads\FileUploadsWorkApplication.java
6. src\main\java\com\suji\fileuploads\exception\FileUploadExceptionAdvice.java
7. src\main\java\com\suji\fileuploads\message\ResponseMessage.java
8. src\main\java\com\suji\fileuploads\service\impl\FilesStorageServiceImpl.java
9. src\test\java\com\suji\fileuploads\FileUploadsWorkApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties


spring.servlet.multipart.max-file-size=500KB
spring.servlet.multipart.max-request-size=500KB
```

---

### 2. FileInfo.java

#### model\FileInfo.java

```java

package com.suji.fileuploads.model;

public class FileInfo {
	
	private String name;
	private String url;

	public FileInfo(String name, String url) {
		this.name = name;
		this.url = url;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
```

---

### 3. FilesController.java

#### controller\FilesController.java

```java

package com.suji.fileuploads.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.suji.fileuploads.message.ResponseMessage;
import com.suji.fileuploads.model.FileInfo;
import com.suji.fileuploads.service.FilesStorageService;

@Controller
//@CrossOrigin("http://localhost:8081")
public class FilesController {
	@Autowired
	FilesStorageService storageService;

	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			storageService.save(file);
			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@GetMapping("/files")
	public ResponseEntity<List<FileInfo>> getListFiles() {
		List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
			String filename = path.getFileName().toString();
			String url = MvcUriComponentsBuilder
					.fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();
			return new FileInfo(filename, url);
		}).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = storageService.load(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
}
```

---

### 4. FilesStorageService.java

#### service\FilesStorageService.java

```java

package com.suji.fileuploads.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
	public void init();

	public void save(MultipartFile file);

	public Resource load(String filename);

	public void deleteAll();

	public Stream<Path> loadAll();
}

```

---

### 5. FileUploadsWorkApplication.java

#### src\main\java\com\suji\fileuploads\FileUploadsWorkApplication.java

```java

package com.suji.fileuploads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.suji.fileuploads.service.FilesStorageService;

@SpringBootApplication
public class FileUploadsWorkApplication implements CommandLineRunner {

	@Autowired
	private FilesStorageService filesStorageService;
	
	public static void main(String[] args) {
		SpringApplication.run(FileUploadsWorkApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		filesStorageService.deleteAll();
		filesStorageService.init();
	}
	

}

```

---

### 6. FileUploadExceptionAdvice.java

#### src\main\java\com\suji\fileuploads\exception\FileUploadExceptionAdvice.java

```java

package com.suji.fileuploads.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.suji.fileuploads.message.ResponseMessage;

@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<ResponseMessage> handleMaxSizeException(MaxUploadSizeExceededException exc) {
	
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("File too large!"));
	
	}
}

```

---

### 7. ResponseMessage.java

#### src\main\java\com\suji\fileuploads\message\ResponseMessage.java

```java

package com.suji.fileuploads.message;

public class ResponseMessage {
	private String message;

	public ResponseMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
```

---

### 8. FilesStorageServiceImpl.java

#### src\main\java\com\suji\fileuploads\service\impl\FilesStorageServiceImpl.java

```java

package com.suji.fileuploads.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.suji.fileuploads.service.FilesStorageService;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {
	
	private final Path root = Paths.get("uploads");

	@Override
	public void init() {
		try {
			Files.createDirectory(root);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize folder for upload!");
		}
	}

	@Override
	public void save(MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
	}

	@Override
	public Resource load(String filename) {
		try {
			Path file = root.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(root.toFile());
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
		} catch (IOException e) {
			throw new RuntimeException("Could not load the files!");
		}
	}
}
```

---

### 9. FileUploadsWorkApplicationTests.java

#### src\test\java\com\suji\fileuploads\FileUploadsWorkApplicationTests.java

```java

package com.suji.fileuploads;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FileUploadsWorkApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

