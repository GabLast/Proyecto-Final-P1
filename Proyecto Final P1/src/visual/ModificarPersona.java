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

import logica.Empresa;
import logica.Jurado;
import logica.Participante;
import logica.Persona;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.CardLayout;


public class ModificarPersona extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Persona miPersona = null;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JRadioButton rdbtnMasculin;
	private JRadioButton rdbtnFemenino;
	private JComboBox comboBox;
	private JPanel panel;
	private JPanel panelJuez;
	

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
			ModificarPersona dialog = new ModificarPersona(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the dialog.
	 */
	public ModificarPersona(Persona miPersona) {
		this.miPersona = miPersona;
		if (miPersona instanceof Participante) {
			setTitle("Modificando participante");
			//panelJuez.setVisible(false);
			//panel.setVisible(true);
			
			
		}
		else if(miPersona instanceof Jurado) {
			setTitle("Modificando juez");
			//panelJuez.setVisible(true);
			//panel.setVisible(false);
		}
		
		//setTitle("Modificando participante");
		setResizable(false);
		setBounds(100, 100, 522, 491);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new CardLayout(0, 0));
		{
			panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Participante", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panel, "name_174943819359200");
			{
				JLabel label = new JLabel("C\u00E9dula:");
				label.setFont(new Font("Tahoma", Font.PLAIN, 13));
				label.setBounds(17, 41, 73, 23);
				panel.add(label);
			}
			{
				JLabel label = new JLabel("Nombre:");
				label.setFont(new Font("Tahoma", Font.PLAIN, 13));
				label.setBounds(17, 105, 88, 23);
				panel.add(label);
			}
			{
				JLabel label = new JLabel("Direcci\u00F3n:");
				label.setFont(new Font("Tahoma", Font.PLAIN, 13));
				label.setBounds(17, 169, 88, 23);
				panel.add(label);
			}
			{
				JLabel label = new JLabel("Tel\u00E9fono:");
				label.setFont(new Font("Tahoma", Font.PLAIN, 13));
				label.setBounds(17, 233, 88, 23);
				panel.add(label);
			}
			{
				txtCedula = new JTextField();
				txtCedula.setFont(new Font("Tahoma", Font.PLAIN, 13));
				txtCedula.setColumns(10);
				txtCedula.setBounds(107, 38, 244, 29);
				txtCedula.setText(miPersona.getCedula());
				panel.add(txtCedula);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
				txtNombre.setColumns(10);
				txtNombre.setBounds(107, 102, 244, 29);
				txtNombre.setText(miPersona.getNombre());
				panel.add(txtNombre);
			}
			{
				txtDireccion = new JTextField();
				txtDireccion.setFont(new Font("Tahoma", Font.PLAIN, 13));
				txtDireccion.setColumns(10);
				txtDireccion.setBounds(107, 166, 244, 29);
				txtDireccion.setText(miPersona.getDireccion());
				panel.add(txtDireccion);
			}
			{
				txtTelefono = new JTextField();
				txtTelefono.setFont(new Font("Tahoma", Font.PLAIN, 13));
				txtTelefono.setColumns(10);
				txtTelefono.setBounds(107, 230, 244, 29);
				txtTelefono.setText(miPersona.getTelefono());
				panel.add(txtTelefono);
			}
			
			JLabel label = new JLabel("Sexo:");
			label.setFont(new Font("Tahoma", Font.PLAIN, 13));
			label.setBounds(17, 297, 72, 16);
			panel.add(label);
			
			rdbtnMasculin = new JRadioButton("Masculino");
			rdbtnMasculin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnMasculin.setSelected(true);
					rdbtnFemenino.setSelected(false);
				}
			});
			rdbtnMasculin.setBounds(107, 293, 100, 25);
			panel.add(rdbtnMasculin);
			
			rdbtnFemenino = new JRadioButton("Femenino");
			rdbtnFemenino.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnMasculin.setSelected(false);
					rdbtnFemenino.setSelected(true);
				}
			});
			rdbtnFemenino.setBounds(230, 293, 112, 25);
			panel.add(rdbtnFemenino);
			
			JLabel label_1 = new JLabel("Grado Acad\u00E9mico:");
			label_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
			label_1.setBounds(10, 354, 112, 16);
			panel.add(label_1);
			
			comboBox = new JComboBox();
			comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Bachiller", "Licenciado", "Mag\u00EDster", "Profesional", "Doctorado"}));
			comboBox.setBounds(132, 351, 219, 22);
			panel.add(comboBox);
		}
		{
			panelJuez = new JPanel();
			panelJuez.setLayout(null);
			panelJuez.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Juez", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panelJuez, "name_174943835546300");
			{
				JLabel label = new JLabel("Tel\u00E9fono:");
				label.setBounds(17, 200, 88, 23);
				panelJuez.add(label);
				label.setFont(new Font("Tahoma", Font.PLAIN, 11));
			}
			{
				JLabel label = new JLabel("C\u00E9dula:");
				label.setFont(new Font("Tahoma", Font.PLAIN, 11));
				label.setBounds(17, 32, 73, 23);
				panelJuez.add(label);
			}
			{
				JLabel label = new JLabel("Nombre:");
				label.setFont(new Font("Tahoma", Font.PLAIN, 11));
				label.setBounds(17, 87, 88, 23);
				panelJuez.add(label);
			}
			{
				JLabel label = new JLabel("Direcci\u00F3n:");
				label.setFont(new Font("Tahoma", Font.PLAIN, 11));
				label.setBounds(17, 142, 88, 23);
				panelJuez.add(label);
			}
			{
				txtCedula = new JTextField();
				txtCedula.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				txtCedula.setColumns(10);
				txtCedula.setBounds(107, 29, 244, 29);
				txtCedula.setText(miPersona.getCedula());
				panelJuez.add(txtCedula);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				txtNombre.setColumns(10);
				txtNombre.setBounds(107, 84, 244, 29);
				txtNombre.setText(miPersona.getNombre());
				panelJuez.add(txtNombre);
			}
			{
				txtDireccion = new JTextField();
				txtDireccion.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				txtDireccion.setColumns(10);
				txtDireccion.setBounds(107, 139, 244, 29);
				txtDireccion.setText(miPersona.getDireccion());
				panelJuez.add(txtDireccion);
			}
			{
				txtTelefono = new JTextField();
				txtTelefono.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				txtTelefono.setColumns(10);
				txtTelefono.setBounds(107, 194, 244, 29);
				txtTelefono.setText(miPersona.getTelefono());
				panelJuez.add(txtTelefono);
			}
			
			JLabel label = new JLabel("Sexo:");
			label.setBounds(17, 253, 72, 16);
			panelJuez.add(label);
			
			rdbtnMasculin = new JRadioButton("Masculino");
			rdbtnMasculin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnMasculin.setSelected(true);
					rdbtnFemenino.setSelected(false);
				}
			});
			rdbtnMasculin.setBounds(107, 253, 100, 25);
			panelJuez.add(rdbtnMasculin);
			
			rdbtnFemenino = new JRadioButton("Femenino");
			rdbtnFemenino.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnMasculin.setSelected(false);
					rdbtnFemenino.setSelected(true);
				}
			});
			rdbtnFemenino.setBounds(230, 253, 112, 25);
			panelJuez.add(rdbtnFemenino);
			
			JLabel label_1 = new JLabel("Grado Acad\u00E9mico:");
			label_1.setBounds(10, 298, 112, 16);
			panelJuez.add(label_1);
			
			comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Bachiller", "Licenciado", "Mag\u00EDster", "Profesional", "Doctorado"}));
			comboBox.setBounds(132, 295, 238, 22);
			panelJuez.add(comboBox);
			{
				JLabel label_2 = new JLabel("\u00C1rea de estudio:");
				label_2.setBounds(10, 348, 112, 16);
				panelJuez.add(label_2);
			}
			{
				JComboBox comboBox_1 = new JComboBox();
				comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Matem\u00E1ticas", "Qu\u00EDmica", "Biolog\u00EDa", "Historia", "F\u00EDsica", "Ingenier\u00EDa"}));
				comboBox_1.setBounds(134, 345, 238, 22);
				panelJuez.add(comboBox_1);
			}
		}
		if (miPersona instanceof Participante) {
		//	setTitle("Modificando participante");
			panelJuez.setVisible(false);
			panel.setVisible(true);
			
			
		}
		else if(miPersona instanceof Jurado) {
			//setTitle("Modificando juez");
			panelJuez.setVisible(true);
			panel.setVisible(false);
		}
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnModi = new JButton("Modificar");
				btnModi.setFont(new Font("Tahoma", Font.PLAIN, 11));
				btnModi.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(txtCedula.getText().length() >=1 && txtNombre.getText().length() >= 1 && txtTelefono.getText().length() >= 1
								&& txtDireccion.getText().length() >= 1)
						{
							
							
							miPersona.setCedula(txtCedula.getText());
							miPersona.setNombre(txtNombre.getText());
							miPersona.setTelefono(txtTelefono.getText());
							miPersona.setDireccion(txtDireccion.getText());
							miPersona.setGradoAcademico(comboBox.getSelectedItem().toString());
							
							{if (rdbtnMasculin.isSelected()) {
									miPersona.setSexo("Masculino");
							
								}
								else if(rdbtnFemenino.isSelected()) {
									miPersona.setSexo("Femenino");
								}
							}
								
					
						
							
							
							JOptionPane.showMessageDialog(null, "Modificación realizada", "Notificación", JOptionPane.INFORMATION_MESSAGE);
							ListarPersonas.loadPersonas();
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
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
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
