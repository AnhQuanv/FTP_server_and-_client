package FTP_server;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.JTextArea;

public class view_server extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static HashMap<String, String> userCredentials = new HashMap<>();
	private static String user;
	private static String pass;
	private static BufferedReader reader;
	private static PrintWriter writer;
	private static JTextArea ta_content_server;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view_server frame = new view_server();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		userCredentials.put("user1", "123");
		userCredentials.put("user2", "password2");
		try {
			ServerSocket serverSocket = new ServerSocket(5000);
//			ta_content_server.append("FTP Server is listening on port 21...");
			System.out.println("server đang chạy..");

			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("client kết nối thành công");
				ta_content_server.append("Client connected: " + clientSocket.getInetAddress());

				handleClient(clientSocket);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Create the frame.
	 */
	public view_server() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 628, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Server");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 11, 102, 35);
		contentPane.add(lblNewLabel);

		ta_content_server = new JTextArea();
		ta_content_server.setBounds(10, 52, 592, 372);
		contentPane.add(ta_content_server);
	}

	private static void handleClient(Socket soc) {
		try {
			reader = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			writer = new PrintWriter(soc.getOutputStream());
			String clientCommand;

			writer.println("220 Welcome to My FTP Server");
			writer.flush();
			boolean check = false;

			while (!check) {
				clientCommand = reader.readLine();
				if (clientCommand.startsWith("USER")) {
					user = clientCommand.substring(5);
					if (userCredentials.containsKey(user)) {
						writer.println("331 User name okay, need password");
						writer.flush();
					} else {
						writer.println("530 Not logged in");
						writer.flush();
					}
				} else if (clientCommand.startsWith("PASS")) {
					System.out.println(clientCommand);
					pass = clientCommand.substring(5);
					if (userCredentials.get(user).equals(pass)) {
						writer.println("230 User logged in");
						System.out.println("230");
						writer.flush();

						check = true;
						writer.println(check + "");
						writer.flush();
						System.out.println("Tài khoản đúng");
					} else {
						writer.println("530 Not logged in");
						writer.flush();
						check = false;
						writer.println("false");
						writer.flush();
						System.out.println("Tài khoản sai");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
