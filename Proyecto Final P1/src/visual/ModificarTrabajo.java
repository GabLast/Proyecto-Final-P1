package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logica.Participante;
import logica.Persona;
import logica.Trabajo;

public class ModificarTrabajo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Trabajo miTrabajo = null;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtTelefono;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			ModificarTrabajo dialog = new ModificarTrabajo(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ModificarTrabajo (Trabajo miTrabajo) {
		this.miTrabajo = miTrabajo;
		setTitle("Modificando Cliente");
		setResizable(false);
		setBounds(100, 100, 399, 349);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new TitledBorder(null, "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			{
				JLabel label = new JLabel("C\u00E9dula:");
				label.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				label.setBounds(17, 32, 73, 23);
				panel.add(label);
			}
			{
				JLabel label = new JLabel("Nombre:");
				label.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				label.setBounds(17, 87, 88, 23);
				panel.add(label);
			}
			{
				JLabel label = new JLabel("Direcci\u00F3n:");
				label.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				label.setBounds(17, 142, 88, 23);
				panel.add(label);
			}
			{
				JLabel label = new JLabel("Tel\u00E9fono:");
				label.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				label.setBounds(17, 197, 88, 23);
				panel.add(label);
			}
			{
				txtCedula = new JTextField();
				txtCedula.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				txtCedula.setColumns(10);
				txtCedula.setBounds(107, 29, 244, 29);
				txtCedula.setText(miTrabajo.getId());
				panel.add(txtCedula);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				txtNombre.setColumns(10);
				txtNombre.setBounds(107, 84, 244, 29);
				txtNombre.setText(miTrabajo.getNombreTrabajo());
				panel.add(txtNombre);
			}
			{
				txtDireccion = new JTextField();
				txtDireccion.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				txtDireccion.setColumns(10);
				txtDireccion.setBounds(107, 139, 244, 29);
				txtDireccion.setText(miTrabajo.getDescripcion());
				panel.add(txtDireccion);
			}
			{
				txtTelefono = new JTextField();
				txtTelefono.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				txtTelefono.setColumns(10);
				txtTelefono.setBounds(107, 194, 244, 29);
				txtTelefono.setText(miTrabajo.getArea());
				panel.add(txtTelefono);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnModi = new JButton("Modificar");
				btnModi.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				btnModi.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(txtCedula.getText().length() >=1 && txtNombre.getText().length() >= 1 && txtTelefono.getText().length() >= 1
								&& txtDireccion.getText().length() >= 1)
						{
							txtNombre.setText(txtNombre.getText());
							txtDireccion.setText(txtDireccion.getText());
							txtTelefono.setText(txtTelefono.getText());
							txtCedula.setText(txtCedula.getText());
							
							miTrabajo.setId(txtCedula.getText());
							miTrabajo.setNombreTrabajo(txtNombre.getText());
							miTrabajo.setDescripcion(txtTelefono.getText());
							miTrabajo.setArea(txtDireccion.getText());

							
							
							JOptionPane.showMessageDialog(null, "Modificación realizada", "Notificación", JOptionPane.INFORMATION_MESSAGE);
							ListarTrabajos.loadTrabajos(miTrabajo.getDuenio());
							dispose();
						}
					}
				});
				btnModi.setActionCommand("OK");
				buttonPane.add(btnModi);
				getRootPane().setDefaultButton(btnModi);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
