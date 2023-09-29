
package FTP_Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.management.timer.Timer;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.TimerTask;
import java.awt.event.ActionEvent;

public class LoginClient extends JFrame {

//	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tf_user;
	private JTextField tf_pass;
	private JTextField tf_IP;
	private JTextField tf_PORT;
	private static JTextArea ta_content_login;
	private static BufferedReader reader;
	private static PrintWriter writer;
	private Socket soc;
	private static String response;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginClient frame = new LoginClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 913, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		tf_user = new JTextField();
		tf_user.setText("user1");
		tf_user.setBounds(66, 9, 86, 25);
		contentPane.add(tf_user);
		tf_user.setColumns(10);

		JLabel lblNewLabel = new JLabel("User");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 14, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(198, 14, 87, 14);
		contentPane.add(lblPassword);

		tf_pass = new JTextField();
		tf_pass.setText("123");
		tf_pass.setColumns(10);
		tf_pass.setBounds(295, 9, 86, 25);
		contentPane.add(tf_pass);

		JLabel lblIp = new JLabel("IP");
		lblIp.setHorizontalAlignment(SwingConstants.CENTER);
		lblIp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblIp.setBounds(420, 14, 46, 14);
		contentPane.add(lblIp);

		tf_IP = new JTextField();
		tf_IP.setText("localhost");
		tf_IP.setColumns(10);
		tf_IP.setBounds(476, 9, 86, 25);
		contentPane.add(tf_IP);

		JLabel lblPort = new JLabel("PORT");
		lblPort.setHorizontalAlignment(SwingConstants.CENTER);
		lblPort.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPort.setBounds(609, 14, 46, 14);
		contentPane.add(lblPort);

		tf_PORT = new JTextField();
		tf_PORT.setText("5000");
		tf_PORT.setColumns(10);
		tf_PORT.setBounds(665, 9, 86, 25);
		contentPane.add(tf_PORT);

		ta_content_login = new JTextArea();
		ta_content_login.setBounds(10, 40, 877, 358);
		contentPane.add(ta_content_login);

		JButton btn_connect = new JButton("Connect");
		btn_connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = tf_user.getText();
				String pass = tf_pass.getText();
				String IP = tf_IP.getText();
				int PORT = Integer.parseInt(tf_PORT.getText());
				
				Connect(user, pass, IP, PORT);
			}
		});
		btn_connect.setBounds(798, 10, 89, 23);
		contentPane.add(btn_connect);
	}

	public void Connect(String user, String pass, String IP, int PORT) {
		try {
			soc = new Socket(IP, PORT);
//			System.out.println("Kết nối thành công");
			ta_content_login.append("Kết nối đến server thành công !!!!" + "\n");
			reader = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			writer = new PrintWriter(soc.getOutputStream());

			response = reader.readLine();
			System.out.println(response);
			ta_content_login.append(response + "\n");

			writer.println("USER " + user);
			writer.flush();
			response = reader.readLine();
			System.out.println(response);
			ta_content_login.append(response + "\n");

			writer.println("PASS " + pass);
			writer.flush();
			response = reader.readLine();
			System.out.println(response);
			ta_content_login.append(response + "\n");

			response = reader.readLine();
			System.out.println(response + "\n");
			if (response.equals("true")) {
				HomeClient home = new HomeClient();
				home.setVisible(true);
				
				
			} else {
				ta_content_login.append("Đăng nhập thất bại !!!" + "\n");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
