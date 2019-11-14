package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class RegistrarTrabajo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textParticipante;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarTrabajo dialog = new RegistrarTrabajo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarTrabajo() {
		setTitle("Registrar trabajo");
		setBounds(100, 100, 705, 510);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panelParticipante = new JPanel();
			panelParticipante.setBorder(new TitledBorder(null, "Datos del participante", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelParticipante.setBounds(31, 10, 517, 126);
			contentPanel.add(panelParticipante);
			panelParticipante.setLayout(null);
			{
				JLabel label = new JLabel("ID/C\u00E9dula");
				label.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
				label.setBounds(21, 21, 102, 43);
				panelParticipante.add(label);
			}
			{
				textField = new JTextField();
				textField.setColumns(10);
				textField.setBounds(114, 33, 109, 20);
				panelParticipante.add(textField);
			}
			{
				JButton btnBuscar = new JButton("Buscar");
				btnBuscar.setBounds(237, 32, 89, 23);
				panelParticipante.add(btnBuscar);
			}
			{
				JLabel lblParticipante = new JLabel("Participante");
				lblParticipante.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
				lblParticipante.setBounds(21, 64, 102, 43);
				panelParticipante.add(lblParticipante);
			}
			{
				textParticipante = new JTextField();
				textParticipante.setEditable(false);
				textParticipante.setColumns(10);
				textParticipante.setBounds(114, 75, 109, 20);
				panelParticipante.add(textParticipante);
			}
		}
		
		JPanel panelTrabajo = new JPanel();
		panelTrabajo.setBorder(new TitledBorder(null, "Datos del trabajo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTrabajo.setBounds(43, 163, 505, 229);
		contentPanel.add(panelTrabajo);
		panelTrabajo.setLayout(null);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
		lblCdigo.setBounds(10, 28, 102, 43);
		panelTrabajo.add(lblCdigo);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(77, 40, 86, 20);
		panelTrabajo.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
		lblNombre.setBounds(10, 80, 102, 43);
		panelTrabajo.add(lblNombre);
		
		textField_2 = new JTextField();
		textField_2.setBounds(77, 92, 86, 20);
		panelTrabajo.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblrea = new JLabel("\u00C1rea");
		lblrea.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
		lblrea.setBounds(10, 134, 59, 43);
		panelTrabajo.add(lblrea);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(77, 146, 86, 20);
		panelTrabajo.add(comboBox);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
		lblDescripcin.setBounds(232, 28, 102, 43);
		panelTrabajo.add(lblDescripcin);
		
		textField_3 = new JTextField();
		textField_3.setBounds(232, 68, 246, 109);
		panelTrabajo.add(textField_3);
		textField_3.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
