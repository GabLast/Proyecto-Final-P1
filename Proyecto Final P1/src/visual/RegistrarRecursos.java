package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Empresa;
import logica.Recurso;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

public class RegistrarRecursos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JSpinner spnCantidad;

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
		setBounds(100, 100, 285, 197);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "Informaci\u00F3n del recurso", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(17, 32, 83, 17);
		contentPanel.add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.setText("");
		txtNombre.setBounds(116, 30, 137, 20);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(17, 77, 83, 17);
		contentPanel.add(lblCantidad);
		
		JSpinner spnCantidad = new JSpinner();
		spnCantidad.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spnCantidad.setBounds(116, 74, 137, 23);
		contentPanel.add(spnCantidad);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtNombre.getText().isEmpty())
				{
					Recurso nuevoRecurso = new Recurso(txtNombre.getText(), (Integer) spnCantidad.getValue());
					Empresa.getInstance().insertarRecurso(nuevoRecurso);
					JOptionPane.showMessageDialog(null, "Recurso Registrado satisfactoriamente", "Notificación", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Digite el nombre del recurso", "Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnRegistrar.setBounds(17, 120, 119, 23);
		contentPanel.add(btnRegistrar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(164, 120, 89, 23);
		contentPanel.add(btnSalir);
	}
}
