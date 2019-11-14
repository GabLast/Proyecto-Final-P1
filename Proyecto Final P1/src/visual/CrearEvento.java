package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Button;

public class CrearEvento extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearEvento dialog = new CrearEvento();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CrearEvento() {
		setTitle("Crear Evento");
		setBounds(100, 100, 454, 319);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblCodigo = new JLabel("C\u00F3digo:");
			lblCodigo.setBounds(26, 34, 54, 14);
			contentPanel.add(lblCodigo);
		}
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(69, 31, 86, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(26, 63, 54, 14);
		contentPanel.add(lblNombre);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(69, 60, 86, 20);
		contentPanel.add(textField_1);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(26, 89, 54, 14);
		contentPanel.add(lblFecha);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(69, 88, 86, 20);
		contentPanel.add(textField_2);
		
		JButton btnIcono = new JButton("");
		btnIcono.setBounds(155, 89, 19, 19);
		contentPanel.add(btnIcono);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 236, 160, -97);
		contentPanel.add(scrollPane);
		
		Button button = new Button("Pasar");
		button.setBounds(204, 137, 70, 22);
		contentPanel.add(button);
		
		Button button_1 = new Button("Devolver");
		button_1.setBounds(204, 176, 70, 22);
		contentPanel.add(button_1);
		
		Button button_2 = new Button("Crear");
		button_2.setBounds(278, 252, 70, 22);
		contentPanel.add(button_2);
		
		Button button_3 = new Button("Salir");
		button_3.setBounds(355, 252, 70, 22);
		contentPanel.add(button_3);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(291, 236, 134, -97);
		contentPanel.add(scrollPane_1);
		
		JList ListaRecursos = new JList();
		ListaRecursos.setBounds(79, 208, 1, 1);
		contentPanel.add(ListaRecursos);
		ListaRecursos = new JList<String>();
		scrollPane.setViewportView(ListaRecursos);
		
		JList ListaPasar = new JList();
		ListaPasar.setBounds(79, 208, 1, 1);
		contentPanel.add(ListaPasar);
		ListaPasar = new JList<String>();
		scrollPane_1.setViewportView(ListaPasar);
	}
}
