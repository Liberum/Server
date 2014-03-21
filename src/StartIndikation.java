import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class StartIndikation {

	public void StartInd() throws IOException {

		// -----------START INDIKATION-----------
		ServerSocket socetInd = new ServerSocket(Server.INDICATION_PORT);
		System.out.println("Server Started");
		try {
			while (true) {
				// Блокируется до возникновения нового соединения:
				Socket socket = socetInd.accept();
				try {
					new IndicationOut(socket);
				} catch (IOException e) {
					// Если завершится неудачей, закрывается сокет,
					// в противном случае, нить закроет его:
					socket.close();
				}
			}
		} finally {
			socetInd.close();
		}
		// --------------------------------------
	}

}
