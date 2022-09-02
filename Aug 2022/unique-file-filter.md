# unique-file-filter


### File Structure
```pre
+ unique-file-filter\ 
	+ \src\main\java\com\suji\unique_file_filter
		|---  Application.java
	+ \src\test\java\com\suji\unique_file_filter
		|---  ApplicationTest.java
```
### Index
```pre
1. src\main\java\com\suji\unique_file_filter\Application.java
2. src\test\java\com\suji\unique_file_filter\ApplicationTest.java

```

---

### 1. Application.java

#### src\main\java\com\suji\unique_file_filter\Application.java

```java


package com.suji.unique_file_filter;

import java.io.File;
import java.util.Set;
import java.util.TreeSet;

public class Application {
	public static void main(String[] args) {

		String source = "C:\\Users\\sujith.manchala\\Documents\\git\\my-notes";

		File sourceFile = new File(source);
		FileFinder ff = new FileFinder(sourceFile);
		ff.uniqueFiles();

	}
}

class FileDetails implements Comparable<FileDetails> {
	private String name;
	private long size;
	
	public FileDetails(String name, long size) {
		this.name = name;
		this.size = size;
	}

	@Override
	public int compareTo(FileDetails fd) {
		return this.name.compareTo(fd.name) == 0 ? this.size == fd.size ? 0 : 1 : -1;
	}
}

class FileFinder {

	private File file;

	public FileFinder(File file) {
		this.file = file;
	}

	public void uniqueFiles() {
		var set = new TreeSet<FileDetails>();
		total = 0;
		uniqueFilesShow(file,set);
		
		System.out.println("Total files: "+total);
		System.out.println("Unique files: "+set.size());
	}
	
	private static int total = 0;
	private void uniqueFilesShow(final File file, final Set<FileDetails> set) {
		
		if (file.isFile()) {
			total ++;
			boolean add = set.add(new FileDetails(file.getName(), file.length()));
			System.out.println(file);
			
		} else if (file.isDirectory()) {

			File[] files = file.listFiles();
			for (File f : files) {
				uniqueFilesShow(f, set);
			}
		}
	}

	public void showAllFiles() {

		show(file);

	}

	private void show(File file) {

		if (file.isFile()) {
			System.out.println(file);
		} else if (file.isDirectory()) {

			File[] files = file.listFiles();
			for (File f : files) {
				show(f);
			}
		}
	}

}

```

---

### 2. ApplicationTest.java

#### src\test\java\com\suji\unique_file_filter\ApplicationTest.java

```java


package com.suji.unique_file_filter;

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

