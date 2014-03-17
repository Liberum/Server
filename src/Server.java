import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.security.KeyStore;
import java.util.List;

import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;

public class Server {
	static String keystore = "C:/Users/al/serverkeys"; // ключ к сертификату
	// static String keystore = "./src/keys/serverkeys"; // ключ к сертификату
	static char keystorepass[] = "qwerty".toCharArray();
	static char keypassword[] = "123456".toCharArray();

	public static final int HTTPS_PORT = 4443;

	public static void main(String[] args) throws Exception {
		KeyStore ks = KeyStore.getInstance("JKS");

		ks.load(new FileInputStream(keystore), keystorepass);
		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
		kmf.init(ks, keypassword);
		SSLContext sslcontext = SSLContext.getInstance("SSLv3");
		sslcontext.init(kmf.getKeyManagers(), null, null);
		ServerSocketFactory ssf = sslcontext.getServerSocketFactory();
		final SSLServerSocket serversocket = (SSLServerSocket) ssf
				.createServerSocket(HTTPS_PORT);
		new Thread() {
			public void run() {
				while (true) {
					try {
						Socket s = serversocket.accept();
						new ProcessingThread(s).start();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	static class ProcessingThread extends Thread {

		private Socket s;

		public ProcessingThread(Socket s) {
			this.s = s;
		}

		public void run() {
			try {
				InputStream is = s.getInputStream();
				OutputStream os = s.getOutputStream();

				ObjectInputStream ois = new ObjectInputStream(is);

				List<String> lst = (List<String>) ois.readObject();
				System.out.println("Server read {" + lst + "}");
				int fin = -1;
				os.write(fin);
				os.close();
				os.flush();
				// int b;
				// while ((b = is.read()) != -1) {
				// b += 10;
				// os.write(b);
				// os.flush();
				// }
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}