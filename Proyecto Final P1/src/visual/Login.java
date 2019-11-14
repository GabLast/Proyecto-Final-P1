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

import logica.Empresa;
import logica.User;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;

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
		setTitle("Iniciar Sesión");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 624, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(25, 84, 56, 16);
		contentPane.add(lblUsuario);
		
		JLabel lblClave = new JLabel("Clave:");
		lblClave.setBounds(25, 141, 56, 16);
		contentPane.add(lblClave);
		
		textField = new JTextField();
		textField.setBounds(93, 81, 151, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(93, 138, 151, 22);
		contentPane.add(textField_2);
		
		JButton btnLogIn = new JButton("Iniciar sesión");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Empresa.getInstance().confirmLogin(textField.getText(),textField_2.getText()))
				{
					Principal frame = new Principal();
					dispose();
					frame.setVisible(true);
				}
			}
		});
		btnLogIn.setBounds(163, 215, 97, 25);
		contentPane.add(btnLogIn);
	}
}
