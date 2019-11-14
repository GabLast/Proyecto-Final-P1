package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;

public class RegistrarRecursos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarRecursos dialog = new RegistrarRecursos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarRecursos() {
		setTitle("Registrar Recursos");
		setBounds(100, 100, 280, 153);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(29, 22, 63, 14);
		contentPanel.add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.setText("");
		txtNombre.setBounds(87, 19, 137, 20);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(29, 47, 77, 14);
		contentPanel.add(lblCantidad);
		
		JSpinner spnCantidad = new JSpinner();
		spnCantidad.setBounds(87, 44, 137, 20);
		contentPanel.add(spnCantidad);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(85, 77, 89, 23);
		contentPanel.add(btnRegistrar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(186, 77, 64, 23);
		contentPanel.add(btnSalir);
	}
}
