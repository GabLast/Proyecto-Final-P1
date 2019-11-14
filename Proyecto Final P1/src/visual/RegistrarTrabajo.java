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
import javax.swing.DefaultComboBoxModel;

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
		setBounds(100, 100, 547, 446);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panelParticipante = new JPanel();
			panelParticipante.setBorder(new TitledBorder(null, "Datos del participante", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelParticipante.setBounds(12, 13, 505, 126);
			contentPanel.add(panelParticipante);
			panelParticipante.setLayout(null);
			{
				JLabel lblIdcdula = new JLabel("ID/C\u00E9dula:");
				lblIdcdula.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
				lblIdcdula.setBounds(21, 13, 102, 43);
				panelParticipante.add(lblIdcdula);
			}
			{
				textField = new JTextField();
				textField.setEditable(false);
				textField.setColumns(10);
				textField.setBounds(114, 24, 116, 20);
				panelParticipante.add(textField);
			}
			{
				JLabel lblParticipante = new JLabel("Participante:");
				lblParticipante.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
				lblParticipante.setBounds(21, 69, 102, 43);
				panelParticipante.add(lblParticipante);
			}
			{
				textParticipante = new JTextField();
				textParticipante.setEditable(false);
				textParticipante.setColumns(10);
				textParticipante.setBounds(114, 80, 116, 20);
				panelParticipante.add(textParticipante);
			}
		}
		
		JPanel panelTrabajo = new JPanel();
		panelTrabajo.setBorder(new TitledBorder(null, "Datos del trabajo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTrabajo.setBounds(12, 152, 505, 212);
		contentPanel.add(panelTrabajo);
		panelTrabajo.setLayout(null);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
		lblCdigo.setBounds(10, 20, 102, 43);
		panelTrabajo.add(lblCdigo);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(77, 31, 143, 20);
		panelTrabajo.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNombre = new JLabel("Tema:");
		lblNombre.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
		lblNombre.setBounds(10, 83, 102, 43);
		panelTrabajo.add(lblNombre);
		
		textField_2 = new JTextField();
		textField_2.setBounds(77, 94, 143, 20);
		panelTrabajo.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblrea = new JLabel("\u00C1rea:");
		lblrea.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
		lblrea.setBounds(10, 146, 59, 43);
		panelTrabajo.add(lblrea);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Matem\u00E1ticas", "Qu\u00EDmica", "Biolog\u00EDa", "Historia", "F\u00EDsica", "Ingenier\u00EDa"}));
		comboBox.setBounds(77, 157, 143, 20);
		panelTrabajo.add(comboBox);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
		lblDescripcin.setBounds(232, 20, 102, 43);
		panelTrabajo.add(lblDescripcin);
		
		textField_3 = new JTextField();
		textField_3.setBounds(232, 55, 261, 122);
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
