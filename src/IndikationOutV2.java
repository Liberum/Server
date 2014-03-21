import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

class IndicationOut extends Thread {
	private Socket socket;
	// private InputStream is;
	private BufferedReader in;
	private OutputStream os;
	private ObjectOutputStream oos;
	IndicationControl ic = new IndicationControl();
	int clientNumber;

	public IndicationOut(Socket s) throws IOException {
		socket = s;
		// is = socket.getInputStream();
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		os = socket.getOutputStream();
		// oos = new ObjectOutputStream(os);
		// Если любой из вышеприведенных вызовов приведет к
		// возникновению исключения, то вызывающий отвечает за
		// закрытие сокета. В противном случае, нить
		// закроет его.
		clientNumber++;
		start(); // вызываем run()
	}

	public void run() {
		try {
			oos = new ObjectOutputStream(os);

			while (true) {
				int str = in.read();
				if (str == -1)
					break;
				System.out.println("Echoing: " + str + " client:"
						+ clientNumber);

				oos.writeObject(ic.Inikation());

			}
			System.out.println("closing...");
		} catch (IOException e) {
			System.err.println("IO Exception");
			System.out.println("Client:" + clientNumber + " closed");
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				System.err.println("Socket not closed");
			}
		}
	}
}