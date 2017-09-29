import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MyClassLoader extends ClassLoader {
	private String name;
	private String path = "c:\\";
	private final String fileType = ".class";

	public MyClassLoader(String name) {
		super();
		this.name = name;

	}

	public MyClassLoader(ClassLoader parent, String name) {
		super(parent);
		this.name = name;
	}

	public String toString() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	protected Class findClass(String name) throws ClassNotFoundException {
		byte[] data = loadClassData(name);
		return defineClass(name, data, 0, data.length);
	}

	private byte[] loadClassData(String name) throws ClassNotFoundException {
		FileInputStream fis = null;
		byte[] data = null;
		ByteArrayOutputStream baos = null;
		name = name.replaceAll("\\.", "\\\\");
		try {
			fis = new FileInputStream(new File(path + name + fileType));
			baos = new ByteArrayOutputStream();
			int ch = 0;
			while ((ch = fis.read()) != -1) {
				baos.write(ch);
			}
			data = baos.toByteArray();
		} catch (IOException e) {
			throw new ClassNotFoundException("class is not found:" + name);
		} finally {
			try {
				fis.close();
				baos.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return data;
	}

	public static void main(String args[]) throws Exception {

		MyClassLoader loader1 = new MyClassLoader("loader1");
		loader1.setPath("C:\\Users\\belv\\datastructure\\datastructure\\src\\");
		MyClassLoader loader2 = new MyClassLoader(loader1, "loader2");
		loader1.setPath("C:\\Users\\belv\\datastructure\\datastructure\\src\\");

		MyClassLoader loader3 = new MyClassLoader(null, "loader3");
		loader1.setPath("C:\\Users\\belv\\datastructure\\datastructure\\src\\");
		test(loader2);
		test(loader3);
	}

	private static void test(ClassLoader loader) throws Exception {
		// TODO Auto-generated method stub

		Class objClass = loader.loadClass("Test");
		Object obj = objClass.newInstance();

	}
}
