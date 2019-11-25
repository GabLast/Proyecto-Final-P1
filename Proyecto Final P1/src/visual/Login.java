package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

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
		setTitle("Iniciando Sesion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 335);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/imagen/titlelogin.png")));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnLogIn = new JButton("Iniciar sesión");
		btnLogIn.setBounds(176, 202, 141, 31);
		panel.add(btnLogIn);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(98, 90, 369, 29);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
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
		passwordField.setBounds(98, 147, 369, 29);
		panel.add(passwordField);
		
		JButton button = new JButton("");
		button.setEnabled(false);
		button.setIcon(new ImageIcon(Login.class.getResource("/imagen/loginusar.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.setBounds(66, 90, 33, 29);
		panel.add(button);
		
		JButton button_1 = new JButton("");
		button_1.setEnabled(false);
		button_1.setIcon(new ImageIcon(Login.class.getResource("/imagen/pass.png")));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setBounds(66, 147, 33, 29);
		panel.add(button_1);
		
		JLabel lblNewLabel = new JLabel("Olvido la contrase\u00F1a ? Click");
		
		lblNewLabel.setBounds(159, 237, 158, 14);
		panel.add(lblNewLabel);
		
		JLabel lblAqui = new JLabel("aqui");
		lblAqui.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "id: admin | clave: 123", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		lblAqui.setForeground(Color.BLUE);
		lblAqui.setBounds(318, 237, 46, 14);
		panel.add(lblAqui);
		
		JLabel lblPucmm = new JLabel("PUCMM");
		lblPucmm.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblPucmm.setBounds(200, 24, 127, 29);
		panel.add(lblPucmm);
		
		JLabel lblPlanificadoraDeEventos = new JLabel("Planificadora de Eventos");
		lblPlanificadoraDeEventos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPlanificadoraDeEventos.setBounds(156, 53, 183, 14);
		panel.add(lblPlanificadoraDeEventos);
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
					
					
				}
			}
		});
	}
}
