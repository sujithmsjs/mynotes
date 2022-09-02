# MD File Generator
Generate MD Files Automatically.

### Index
```pre
1. \src\main\java\com\suji\mdfilegen\Application.java
2. \src\main\java\com\suji\mdfilegen\constants\Constants.java
3. \src\main\java\com\suji\mdfilegen\model\FileInFolder.java
4. \src\main\java\com\suji\mdfilegen\model\MDFile.java
5. \src\main\java\com\suji\mdfilegen\model\MDFileGenerator.java
6. \src\main\java\com\suji\mdfilegen\model\MDFileRepo.java
7. \src\test\java\com\suji\mdfilegen\ApplicationTest.java

```

---

### 1. Application.java

#### \src\main\java\com\suji\mdfilegen\Application.java

```java


package com.suji.mdfilegen;

import java.io.File;
import java.io.FileFilter;

import com.suji.mdfilegen.constants.Constants;
import com.suji.mdfilegen.model.FileInFolder;
import com.suji.mdfilegen.model.MDFileGenerator;
import com.suji.mdfilegen.model.MDFileRepo;

public class Application {

	public static FileFilter fileFilter;
	public static FileFilter mapFiles;
	public static MDFileRepo fileRepo = new MDFileRepo();
	
	static {

		fileFilter = f -> {
			String name = f.getName();
			int lastIndexOf = name.lastIndexOf(".");
			String extention = null;
			if (lastIndexOf > 0)
				extention = name.substring(lastIndexOf);
			return extention != null && !extention.isBlank() && Constants.INCLUDE_EXTENTIONS.contains(extention);
		};

		mapFiles = f -> {
			for (FileInFolder theFolder : Constants.MAPPER) {

				String path = f.getPath();
				if (path.contains(theFolder.getPath()) && path.endsWith(theFolder.getEx())) {
					fileRepo.addMD(f, theFolder.getOrder(), theFolder.getPath());
					return true;
				}
			}
			return false;
		};

	}

	public static void main(String[] args) {

		File files = new File(Constants.SOURCE_PATH);
		findAll(files);
		fileRepo.showAllMdFiles();
		File write = MDFileGenerator.write(fileRepo);
		System.out.println("File saved at "+write.getPath());
		System.out.println("______The End______");
	}

	public static void findAll(File file) {
		if (file.isFile()) {

			if (mapFiles.accept(file)) {
				//System.out.println("\t- " + file.getPath());
			}

		} else if (file.isDirectory()) {
			File[] files = file.listFiles();

			for (File theFile : files) {
				findAll(theFile);
			}
		}
	}

}

```

---

### 2. Constants.java

#### \src\main\java\com\suji\mdfilegen\constants\Constants.java

```java

package com.suji.mdfilegen.constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.suji.mdfilegen.model.FileInFolder;

public class Constants {
	
	public static final String APP_NAME = "MD File Generator";
	public static final String APP_DESC = "Generate MD Files Automatically.";
	
	public static final String SOURCE_PATH = "E:\\SpringBoot STS\\mdfilegen"; //"S:\\git\\mongo-restapi";
	public static final Set<String> INCLUDE_EXTENTIONS = Set.of(".java", ".xml", ".properties");
	public static final List<FileInFolder> MAPPER = new ArrayList<>();
	
	public static final String DESTINATION_PATH = "C:\\Users\\sujit\\OneDrive\\Desktop\\MD Files\\my-notes";
	
	public static final String DESTINATION_FILE_NAME = "MDFile Generator.md";
	
	
	static {
		
		MAPPER.add(new FileInFolder("\\src\\main\\java\\", ".java", 2));
		MAPPER.add(new FileInFolder("\\src\\test\\java\\", ".java", 3));
		MAPPER.add(new FileInFolder("\\src\\main\\resources\\", ".properties", 0));
		MAPPER.add(new FileInFolder("\\src\\main\\resources\\", ".sql", 1));
		MAPPER.add(new FileInFolder("mongo-restapi\\mongo-rest-api\\", ".xml", 1));
	}
}

```

---

### 3. FileInFolder.java

#### \src\main\java\com\suji\mdfilegen\model\FileInFolder.java

```java

package com.suji.mdfilegen.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data

public class FileInFolder {

	private String path;
	private List<String> extensions = new ArrayList<>();	
	private int order;
	
	public FileInFolder(String path, String ex) {
		this(path, ex, 0);
	}
	
	public FileInFolder(String path, String ex, int order) {
		this.path = path;
		this.order = order;
		extensions.add(ex);
	}
	
	
	public String getEx() {
		return extensions.get(0);
	}
}

```

---

### 4. MDFile.java

#### \src\main\java\com\suji\mdfilegen\model\MDFile.java

```java

package com.suji.mdfilegen.model;

import java.io.File;

import lombok.Data;

@Data
public class MDFile implements Comparable<MDFile> {
	
	private int order;
	private File file;
	private String title;
	private String ex;
	
	public MDFile(File file, int order) {
		this(file, order, "");
	}
	
	
	
	@Override
	public int compareTo(MDFile mdFile) {
		
		return (order - mdFile.getOrder() == 0)? title.compareTo(mdFile.title) : order - mdFile.getOrder();
	}



	public MDFile(File file,int order,String title) {
		this.order = order;
		this.file = file;
		int idx = file.getPath().indexOf(title);
		this.title = file.getPath().substring(idx);
		int lastIndex = file.getName().lastIndexOf(".");
		ex = file.getName().substring(lastIndex + 1);
	}
}

```

---

### 5. MDFileGenerator.java

#### \src\main\java\com\suji\mdfilegen\model\MDFileGenerator.java

```java

package com.suji.mdfilegen.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.suji.mdfilegen.constants.Constants;

public class MDFileGenerator {

	public static File write(MDFileRepo repo) {

		File dest = new File(Constants.DESTINATION_PATH + "\\" + Constants.DESTINATION_FILE_NAME);
		PrintWriter out = null;
		FileReader fr = null;
		BufferedReader br = null;

		
		try {

			out = new PrintWriter(dest);

			// Writing Titles
			out.println("# " + Constants.APP_NAME);
			out.println(Constants.APP_DESC);
			out.println();
			
			// Index
			out.println("### Index");
			out.println("```pre");
			int index = 0;
			
			for(MDFile mdf : repo.getAllMdFiles()) {
				out.println((++index)+". " + mdf.getTitle());
			}
			out.println();
			out.println("```");
			out.println();
			out.println("---");
			out.println();
			// Printing Files
			index = 0;
			for (MDFile mdFile : repo.getAllMdFiles()) {

				fr = new FileReader(mdFile.getFile());

				br = new BufferedReader(fr);

				out.println("### " + (++index) + ". " + mdFile.getFile().getName());
				out.println();
				out.println("#### " + mdFile.getTitle());
				out.println();
				out.println("```" + mdFile.getEx());
				out.println();
				
				int i;
				while ((i = br.read()) != -1) {
					System.out.print((char) i);
					out.write((char) i);
				}
				out.println();
				out.println("```");
				out.println();
				out.println("---");
				out.println();
				
				out.flush();
			}

			System.out.println("Is File Found: " + dest.exists());

		} catch (IOException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				br.close();
				fr.close();
				out.close();
			} catch (IOException ex) {
				System.err.println(ex.getMessage());
			}
		}
		return dest;
	}

}

```

---

### 6. MDFileRepo.java

#### \src\main\java\com\suji\mdfilegen\model\MDFileRepo.java

```java

package com.suji.mdfilegen.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MDFileRepo {
	private List<MDFile> files = new ArrayList<>();
	
	public void addMD(MDFile md) {
		files.add(md);
	}
	
	public List<MDFile> getAllMdFiles(){
		return files;
	}
	
	public void addMD(File file, int order) {
		files.add(new MDFile(file, order));
	}
	
	public void addMD(File file, int order, String title) {
		files.add(new MDFile(file, order, title));
	}
	
	public void showAllMdFiles(){
		Collections.sort(files);
		files.forEach(System.out :: println);
	}
}

```

---

### 7. ApplicationTest.java

#### \src\test\java\com\suji\mdfilegen\ApplicationTest.java

```java


package com.suji.mdfilegen;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Application")
public class ApplicationTest {

  @Test
  @DisplayName("Pointless test")
  void smokeTest() {
    assertThat(true).isEqualTo(true);
  }
}

```

---

