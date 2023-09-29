package FTP_Client;

import java.awt.EventQueue;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeClient extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeClient frame = new HomeClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public HomeClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 913, 544);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Connect");
		btnNewButton.setBounds(798, 10, 89, 23);
		contentPane.add(btnNewButton);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(453, 40, 433, 358);
		contentPane.add(textArea_1);

		JButton btn_up = new JButton("Upload");
		btn_up.setBounds(172, 439, 89, 23);
		contentPane.add(btn_up);

		JButton btnDownload = new JButton("Download");
		btnDownload.setBounds(333, 439, 89, 23);
		contentPane.add(btnDownload);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(481, 439, 89, 23);
		contentPane.add(btnDelete);

		JButton btnNewFolder = new JButton("New Folder");
		btnNewFolder.setBounds(625, 439, 89, 23);
		contentPane.add(btnNewFolder);

		JLabel lblNewLabel = new JLabel("User");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 0, 142, 23);
		contentPane.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 40, 423, 358);
		contentPane.add(scrollPane);

		// Tạo cây và thêm vào JScrollPane
		String path = "D:\\Hoc ki 2"; // Đường dẫn của thư mục bạn muốn hiển thị
		DefaultMutableTreeNode root = listAllFiles(path);
		JTree tree = new JTree(root);
		scrollPane.setViewportView(tree);

		this.setVisible(true);

		// Sự kiện nút "Connect"
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Thực hiện kết nối đến FTP server
				// Code kết nối vào đây
			}
		});

		// Sự kiện nút "Upload"
		btn_up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Thực hiện upload file lên server
				// Code upload vào đây
			}
		});

		// Sự kiện nút "Download"
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Thực hiện download file từ server
				// Code download vào đây
			}
		});

		// Sự kiện nút "Delete"
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Thực hiện xóa file hoặc thư mục trên server
				// Code xóa vào đây
			}
		});

		// Sự kiện nút "New Folder"
		btnNewFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Thực hiện tạo thư mục mới trên server
				// Code tạo thư mục mới vào đây
			}
		});
	}

	public DefaultMutableTreeNode listAllFiles(String path) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(new File(path).getName());
		File myFile = new File(path);
		if (!myFile.exists()) {
			System.out.println("Đường dẫn không tồn tại");
		} else {
			if (!myFile.isFile()) {
				for (File f : myFile.listFiles()) {
					DefaultMutableTreeNode node = new DefaultMutableTreeNode(f.getName());
					if (f.isDirectory()) {
						node.add(listAllFiles(f.getAbsolutePath())); // Đệ quy nếu là thư mục
					}
					root.add(node);
				}
			}
		}
		return root;
	}
}
