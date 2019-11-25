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
import java.awt.Font;

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
		setTitle("Registrar recursos");
		setBounds(100, 100, 501, 213);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "Informaci\u00F3n del recurso", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblNewLabel.setBounds(191, 36, 83, 17);
		contentPanel.add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Roboto", Font.PLAIN, 12));
		txtNombre.setText("");
		txtNombre.setBounds(295, 34, 137, 20);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblCantidad.setBounds(191, 77, 83, 17);
		contentPanel.add(lblCantidad);
		
		spnCantidad = new JSpinner();
		spnCantidad.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spnCantidad.setBounds(295, 75, 137, 23);
		contentPanel.add(spnCantidad);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setFont(new Font("Roboto", Font.PLAIN, 12));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtNombre.getText().isEmpty())
				{
					if(!Empresa.getInstance().verificarRecursoUnico(txtNombre.getText()))
					{
						Recurso nuevoRecurso = new Recurso(txtNombre.getText(), (Integer) spnCantidad.getValue());
						Empresa.getInstance().insertarRecurso(nuevoRecurso);
						JOptionPane.showMessageDialog(null, "Recurso Registrado satisfactoriamente", "Notificación", JOptionPane.WARNING_MESSAGE);
						clean();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Este tipo de recurso ya esta registrado.\nModifique su disponibilidad en el listado de recursos.", "Error", JOptionPane.WARNING_MESSAGE);
					}
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Digite el nombre del recurso", "Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnRegistrar.setBounds(260, 140, 119, 23);
		contentPanel.add(btnRegistrar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Roboto", Font.PLAIN, 12));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(386, 140, 89, 23);
		contentPanel.add(btnSalir);
		
		JLabel lblFoto = new JLabel("");
		lblFoto.setBounds(47, 38, 46, 14);
		contentPanel.add(lblFoto);
	}
	
	public void clean()
	{
		txtNombre.setText("");
		spnCantidad.setValue(1);
	}
}
