
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class server {

	// socket server port on which it will listen

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

		String user = "test";
		String pass = "test";
		// create the socket server object
		ServerSocket server = new ServerSocket(9876);
		// keep listens indefinitely until receives 'exit' call or program terminates
		while (true) {
			Socket socket = null;

			try {
				System.out.println("Waiting for new client");
				// creating socket and waiting for client connection
				socket = server.accept();
				// read from socket to ObjectInputStream object
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

				Thread t = new ClientHandler(socket, ois, oos);

				// Invoking the start() method
				t.start();

				// close resources

				// terminate the server if client sends exit request
			} catch (Exception e) {
				socket.close();
				e.printStackTrace();
			}
		}
	}

	static class ClientHandler extends Thread {

		final ObjectInputStream dis;
		final ObjectOutputStream dos;
		final Socket socket;

		// Constructor
		public ClientHandler(Socket s, ObjectInputStream dis, ObjectOutputStream dos) {
			this.socket = s;
			this.dis = dis;
			this.dos = dos;
		}

		@Override
		public void run() {
			MySQLAccessLogin dao = new MySQLAccessLogin();
			try {
				dao.readDataBase();
			} catch (Exception e) {
				System.out.println("Error with database");
				e.printStackTrace();
			}

			while (true) {
				String username = "";
				try {
					boolean authenticatedDone = false;
					while (!authenticatedDone) {
						command instruction = new command(1, "doAuth", null);
						dos.writeObject(instruction);
						userPass newUser = (userPass) dis.readObject();
						username = newUser.getUsername();
						String password = newUser.getPassword();
						ArrayList<userPass> users = dao.loginCheck();
						boolean authenticated = false;
						for (int i = 0; i < users.size(); i++) {
							userPass checkedUser = users.get(i);
							if (username.equals(checkedUser.getUsername())
									&& password.equals(checkedUser.getPassword())) {
								authenticated = true;
								break;
							}
						}
						if (authenticated) {
							ArrayList<String> names = new ArrayList<>();
							names = dao.getNames(username);
							instruction = new command(1, "authenticated", null);
							dos.reset();
							dos.writeObject(instruction);
							dos.reset();
							dos.writeObject(names);
							authenticatedDone = true;
						} else {
							instruction = new command(1, "authFailed", null);
							dos.reset();
							dos.writeObject(instruction);
						}
					}
					if (authenticatedDone) {
						while (true) {

							command newCommand = (command) dis.readObject();
							if (newCommand.getCommand().equals("Search")) {
								System.out.println("searching");
								commandParameter searchParams = (commandParameter) newCommand.getParameters();
								String searchWord = searchParams.getSearchString();
								int count = searchParams.getReturnCount();
								int zipcode = searchParams.getZipcode();
								String user = searchParams.getUserSearched();
								search newSearch = new search();
								newSearch.search(searchWord, zipcode, count, user);
								System.out.println("Search Done");
								
								MySQLAccessData dao2 = new MySQLAccessData();
								Report report = new Report();
								int id = dao2.getLastReportID("reports");
								report = dao2.getReport(id);
								dos.reset();
								dos.writeObject(report);
								
							}
							if (newCommand.getCommand().equals("None")) {

								Thread.sleep(100);
							}
							if (newCommand.getCommand().equals("getHistory")) {
								MySQLAccessData dao2 = new MySQLAccessData();
								ArrayList<Report> list = new ArrayList<>();
								for (int i = 1; i <= (dao2.getLastReportID("reports")); i++) 
								{
									Report report = new Report();
									report = dao2.getReport(i);
									list.add(report);
								}
								dos.reset();
								dos.writeObject(list);


							}
						}
					}

				} catch (IOException | ClassNotFoundException e) {
					// e.printStackTrace();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}
}
