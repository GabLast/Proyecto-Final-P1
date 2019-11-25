package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logica.Empresa;
import logica.User;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import java.awt.Color;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				FileInputStream empresaIn;
				FileOutputStream empresaOut;
				ObjectInputStream empresaRead;
				ObjectOutputStream empresaWrite;
				
				try {
					
					empresaIn = new FileInputStream ("empresa.dat");
					empresaRead = new ObjectInputStream(empresaIn);
					
					Empresa temp = (Empresa)empresaRead.readObject();
					
					Empresa.setMiEmpresa(temp);
					empresaIn.close();
					empresaRead.close();
				} catch (FileNotFoundException e) {
					try {
						
						empresaOut = new  FileOutputStream("empresa.dat");
						empresaWrite = new ObjectOutputStream(empresaOut);
						
						User aux = new User("Administrador", "admin", "123");
						Empresa.getInstance().regUser(aux);
						
						empresaWrite.writeObject(Empresa.getInstance());
						
						empresaOut.close();
						empresaWrite.close();
						
					} catch (FileNotFoundException e1) {
					} catch (IOException e1) {
						// TODO Auto-generated catch block
					}
				} catch (IOException e) {
					
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("Planificador de eventos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 222);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Iniciando sesi\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnLogIn = new JButton("Iniciar sesión");
		btnLogIn.setBounds(326, 118, 141, 31);
		panel.add(btnLogIn);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(98, 35, 369, 29);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(17, 38, 69, 23);
		panel.add(lblUsuario);
		
		JLabel lblClave = new JLabel("Clave:");
		lblClave.setBounds(17, 80, 51, 23);
		panel.add(lblClave);
		
		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Empresa.getInstance().confirmLogin(txtUsuario.getText(),String.valueOf(passwordField.getPassword())))
				{
					Principal frame = new Principal();
					dispose();
					frame.setVisible(true);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Usuario y/o clave incorrecta", "Error", JOptionPane.INFORMATION_MESSAGE);
					JOptionPane.showMessageDialog(null, "id: admin | clave: 123", "Aviso", JOptionPane.INFORMATION_MESSAGE);
					
				}
			}
		});
		passwordField.setBounds(98, 77, 369, 29);
		panel.add(passwordField);
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Empresa.getInstance().confirmLogin(txtUsuario.getText(),String.valueOf(passwordField.getPassword())))
				{
					Principal frame = new Principal();
					dispose();
					frame.setVisible(true);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Usuario y/o clave incorrecta", "Error", JOptionPane.INFORMATION_MESSAGE);
					JOptionPane.showMessageDialog(null, "id: admin | clave: 123", "Aviso", JOptionPane.INFORMATION_MESSAGE);
					
				}
			}
		});
	}
}
