package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JProgressBar;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;

public class RegistrarParticipante extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private JTextField textID_1;
	private JPanel panel_1;
	private JPanel panel_2;
	private JButton btnRegistrar;
	private JTextField textNombre;
	private JTextField textTelefono;
	private JTextField textDireccion;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarParticipante dialog = new RegistrarParticipante();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarParticipante() {
		setTitle("Registrar persona");
		setBounds(100, 100, 772, 687);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 756, 618);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos necesarios", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 30, 746, 588);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JPanel panelj = new JPanel();
		panelj.setBounds(155, 104, 356, 473);
		panel.add(panelj);
		panelj.setLayout(new CardLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panelj.add(panel_1, "name_400988034972900");
		panel_1.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
		lblNombre.setBounds(10, 72, 102, 43);
		panel_1.add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(10, 111, 86, 20);
		panel_1.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
		lblSexo.setBounds(10, 142, 58, 43);
		panel_1.add(lblSexo);
		
		JRadioButton rdbtnFemenino = new JRadioButton("Femenino");
		rdbtnFemenino.setBounds(57, 153, 80, 23);
		panel_1.add(rdbtnFemenino);
		
		JRadioButton rdbtnMasculino = new JRadioButton("Masculino");
		rdbtnMasculino.setBounds(141, 153, 109, 23);
		panel_1.add(rdbtnMasculino);
		
		JLabel lblTlefono = new JLabel("T\u00E9lefono");
		lblTlefono.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
		lblTlefono.setBounds(10, 183, 102, 43);
		panel_1.add(lblTlefono);
		
		textTelefono = new JTextField();
		textTelefono.setBounds(10, 219, 86, 20);
		panel_1.add(textTelefono);
		textTelefono.setColumns(10);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
		lblDireccin.setBounds(10, 250, 102, 43);
		panel_1.add(lblDireccin);
		
		textDireccion = new JTextField();
		textDireccion.setBounds(10, 290, 86, 20);
		panel_1.add(textDireccion);
		textDireccion.setColumns(10);
		
		JLabel lblGradoAcadmico = new JLabel("Grado acad\u00E9mico");
		lblGradoAcadmico.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
		lblGradoAcadmico.setBounds(10, 321, 146, 43);
		panel_1.add(lblGradoAcadmico);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"<--Seleccione-->", "Bachiller", "Licenciado", "Ingeniero", "Doctorado", ""}));
		comboBox.setBounds(10, 364, 115, 20);
		panel_1.add(comboBox);
		
		JLabel lblldcdula = new JLabel("ID/C\u00E9dula");
		lblldcdula.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
		lblldcdula.setBounds(10, 11, 102, 43);
		panel_1.add(lblldcdula);
		
		textID = new JTextField();
		textID.setColumns(10);
		textID.setBounds(10, 52, 109, 20);
		panel_1.add(textID);
		
		JPanel panel_2 = new JPanel();
		panelj.add(panel_2, "name_400990532552800");
		panel_2.setLayout(null);
		
		JLabel lblIdcdula_1 = new JLabel("ID/C\u00E9dula");
		lblIdcdula_1.setBounds(16, 11, 102, 43);
		panel_2.add(lblIdcdula_1);
		lblIdcdula_1.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
		
		textID_1 = new JTextField();
		textID_1.setBounds(16, 52, 109, 20);
		panel_2.add(textID_1);
		textID_1.setColumns(10);
		
		JLabel label = new JLabel("Nombre");
		label.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
		label.setBounds(16, 82, 102, 43);
		panel_2.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(16, 120, 86, 20);
		panel_2.add(textField);
		
		JLabel label_1 = new JLabel("Sexo");
		label_1.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
		label_1.setBounds(16, 151, 58, 43);
		panel_2.add(label_1);
		
		JRadioButton radioButton = new JRadioButton("Femenino");
		radioButton.setBounds(60, 162, 80, 23);
		panel_2.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("Masculino");
		radioButton_1.setBounds(142, 162, 109, 23);
		panel_2.add(radioButton_1);
		
		JLabel label_2 = new JLabel("T\u00E9lefono");
		label_2.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
		label_2.setBounds(22, 192, 80, 43);
		panel_2.add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(16, 227, 86, 20);
		panel_2.add(textField_1);
		
		JLabel label_3 = new JLabel("Direcci\u00F3n");
		label_3.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
		label_3.setBounds(16, 255, 102, 43);
		panel_2.add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(16, 298, 86, 20);
		panel_2.add(textField_2);
		
		JLabel label_4 = new JLabel("Grado acad\u00E9mico");
		label_4.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
		label_4.setBounds(16, 323, 146, 43);
		panel_2.add(label_4);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"<--Seleccione-->", "Bachiller", "Licenciado", "Ingeniero", "Doctorado"}));
		comboBox_1.setBounds(25, 363, 115, 20);
		panel_2.add(comboBox_1);
		
		JLabel lblArea = new JLabel("Area");
		lblArea.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
		lblArea.setBounds(16, 391, 38, 43);
		panel_2.add(lblArea);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"<--Seleccione-->", "Matem\u00E1ticas", "", "Qu\u00EDmica", "", "Biolog\u00EDa", "", "Historia", "", "F\u00EDsica", "", "Ingenier\u00EDa"}));
		comboBox_2.setBounds(26, 430, 114, 20);
		panel_2.add(comboBox_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(155, 23, 329, 70);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JRadioButton rdbtnParticipante = new JRadioButton("Participante");
		rdbtnParticipante.setBounds(52, 21, 109, 23);
		panel_3.add(rdbtnParticipante);
		
		JRadioButton rdbtnJuez = new JRadioButton("Juez");
		rdbtnJuez.setBounds(185, 21, 109, 23);
		panel_3.add(rdbtnJuez);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 615, 756, 33);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				
				JButton btnRegistrar_1 = new JButton("Registrar");
				buttonPane.add(btnRegistrar_1);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
	
	}	
	/*public void limpiar() {
			textID_1.setText("");
			textDireccion1.setText("");
			textNombre1.setText("");
			textTelefono1.setText("");
		}*/
}
