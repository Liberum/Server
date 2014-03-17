import java.io.IOException;
import java.util.List;

public class RunDisk {

	String programPath = "C:\\Program Files\\TrueCrypt\\truecrypt.exe";

	// запуск диска с передачей параметров
	public void mountDskCpt(List<String> mount) {

		String dPath = mount.get(1);
		String dsk = mount.get(0);
		String password = mount.get(3);
		List<String> params = java.util.Arrays.asList(programPath, "/v", dPath,
				"/l", dsk, "/p", password, "/e", "/b", "/w", "/q");

		ProcessBuilder b = new ProcessBuilder(params);
		try {
			b.start();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	// размонтирование диска
	public void UnMount(String nDisk) {
		List<String> params = java.util.Arrays.asList(
				"C:\\Program Files\\TrueCrypt\\truecrypt.exe", "/d", nDisk,
				"/q");
		ProcessBuilder b = new ProcessBuilder(params);
		try {
			b.start();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
