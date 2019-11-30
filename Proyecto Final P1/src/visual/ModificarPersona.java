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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ModificarPersona extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Persona miPersona = null;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtCedulaJuez;
	private JTextField txtNombreJuez;
	private JTextField txtDireccionJuez;
	private JTextField txtTelefonoJuez;
	private JRadioButton rdbtnMasculin;
	private JRadioButton rdbtnMasculin_1;
	private JRadioButton rdbtnFemenino;
	private JRadioButton rdbtnFemenino_1;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;
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
				label.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				label.setBounds(17, 39, 73, 23);
				panel.add(label);
			}
			{
				JLabel label = new JLabel("Nombre:");
				label.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				label.setBounds(17, 101, 88, 23);
				panel.add(label);
			}
			{
				JLabel label = new JLabel("Direcci\u00F3n:");
				label.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				label.setBounds(17, 163, 88, 23);
				panel.add(label);
			}
			{
				JLabel label = new JLabel("Tel\u00E9fono:");
				label.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				label.setBounds(17, 225, 88, 23);
				panel.add(label);
			}
			{
				txtCedula = new JTextField();
				txtCedula.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				txtCedula.setColumns(10);
				txtCedula.setBounds(196, 34, 293, 29);
				txtCedula.setText(miPersona.getCedula());
				panel.add(txtCedula);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				txtNombre.setColumns(10);
				txtNombre.setBounds(196, 98, 293, 29);
				txtNombre.setText(miPersona.getNombre());
				panel.add(txtNombre);
			}
			{
				txtDireccion = new JTextField();
				txtDireccion.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				txtDireccion.setColumns(10);
				txtDireccion.setBounds(196, 160, 293, 29);
				txtDireccion.setText(miPersona.getDireccion());
				panel.add(txtDireccion);
			}
			{
				txtTelefono = new JTextField();
				txtTelefono.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				txtTelefono.setColumns(10);
				txtTelefono.setBounds(196, 222, 293, 29);
				txtTelefono.setText(miPersona.getTelefono());
				panel.add(txtTelefono);
			}
			
			JLabel label = new JLabel("Sexo:");
			label.setFont(new Font("Times New Roman", Font.PLAIN, 19));
			label.setBounds(17, 287, 72, 16);
			panel.add(label);
			
			rdbtnMasculin = new JRadioButton("Masculino");
			rdbtnMasculin.setFont(new Font("Times New Roman", Font.PLAIN, 19));
			rdbtnMasculin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnMasculin.setSelected(true);
					rdbtnFemenino.setSelected(false);
				}
			});
			rdbtnMasculin.setBounds(202, 283, 134, 25);
			panel.add(rdbtnMasculin);
			
			rdbtnFemenino = new JRadioButton("Femenino");
			rdbtnFemenino.setFont(new Font("Times New Roman", Font.PLAIN, 19));
			rdbtnFemenino.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnMasculin.setSelected(false);
					rdbtnFemenino.setSelected(true);
				}
			});
			rdbtnFemenino.setBounds(345, 283, 124, 25);
			panel.add(rdbtnFemenino);
			
			JLabel label_1 = new JLabel("Grado Acad\u00E9mico:");
			label_1.setFont(new Font("Times New Roman", Font.PLAIN, 19));
			label_1.setBounds(17, 342, 142, 16);
			panel.add(label_1);
			
			comboBox = new JComboBox();
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					miPersona.setGradoAcademico(comboBox.getSelectedItem().toString());
				}
				
			});
			
			comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 19));
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Bachiller", "Licenciado", "Mag\u00EDster", "Profesional", "Doctorado"}));
			comboBox.setBounds(196, 339, 293, 22);
			panel.add(comboBox);
		}
		{
			panelJuez = new JPanel();
			panelJuez.setLayout(null);
			panelJuez.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Juez", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panelJuez, "name_174943835546300");
			{
				JLabel label = new JLabel("Tel\u00E9fono:");
				label.setBounds(17, 197, 88, 23);
				panelJuez.add(label);
				label.setFont(new Font("Times New Roman", Font.PLAIN, 19));
			}
			{
				JLabel label = new JLabel("C\u00E9dula:");
				label.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				label.setBounds(17, 32, 73, 23);
				panelJuez.add(label);
			}
			{
				JLabel label = new JLabel("Nombre:");
				label.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				label.setBounds(17, 87, 88, 23);
				panelJuez.add(label);
			}
			{
				JLabel label = new JLabel("Direcci\u00F3n:");
				label.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				label.setBounds(17, 142, 88, 23);
				panelJuez.add(label);
			}
			{
				txtCedulaJuez = new JTextField();
				txtCedulaJuez.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				txtCedulaJuez.setColumns(10);
				txtCedulaJuez.setBounds(192, 29, 297, 29);
				txtCedulaJuez.setText(miPersona.getCedula());
				panelJuez.add(txtCedulaJuez);
			}
			{
				txtNombreJuez = new JTextField();
				txtNombreJuez.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				txtNombreJuez.setColumns(10);
				txtNombreJuez.setBounds(192, 84, 297, 29);
				txtNombreJuez.setText(miPersona.getNombre());
				panelJuez.add(txtNombreJuez);
			}
			{
				txtDireccionJuez = new JTextField();
				txtDireccionJuez.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				txtDireccionJuez.setColumns(10);
				txtDireccionJuez.setBounds(192, 139, 297, 29);
				txtDireccionJuez.setText(miPersona.getDireccion());
				panelJuez.add(txtDireccionJuez);
			}
			{
				txtTelefonoJuez = new JTextField();
				txtTelefonoJuez.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				txtTelefonoJuez.setColumns(10);
				txtTelefonoJuez.setBounds(192, 194, 297, 29);
				txtTelefonoJuez.setText(miPersona.getTelefono());
				panelJuez.add(txtTelefonoJuez);
			}
			
			JLabel label = new JLabel("Sexo:");
			label.setFont(new Font("Times New Roman", Font.PLAIN, 19));
			label.setBounds(17, 252, 72, 16);
			panelJuez.add(label);
			
			rdbtnMasculin_1 = new JRadioButton("Masculino");
			rdbtnMasculin_1.setFont(new Font("Times New Roman", Font.PLAIN, 19));
			rdbtnMasculin_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnMasculin.setSelected(true);
					rdbtnFemenino.setSelected(false);
				}
			});
			rdbtnMasculin_1.setBounds(192, 248, 124, 25);
			panelJuez.add(rdbtnMasculin_1);
			
			rdbtnFemenino_1 = new JRadioButton("Femenino");
			rdbtnFemenino_1.setFont(new Font("Times New Roman", Font.PLAIN, 19));
			rdbtnFemenino_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnMasculin.setSelected(false);
					rdbtnFemenino.setSelected(true);
				}
			});
			rdbtnFemenino_1.setBounds(347, 248, 142, 25);
			panelJuez.add(rdbtnFemenino_1);
			
			JLabel label_1 = new JLabel("Grado Acad\u00E9mico:");
			label_1.setFont(new Font("Times New Roman", Font.PLAIN, 19));
			label_1.setBounds(17, 300, 165, 16);
			panelJuez.add(label_1);
			
			comboBox_2 = new JComboBox();
			comboBox_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					miPersona.setGradoAcademico(comboBox_2.getSelectedItem().toString());
				}
			});
			
			comboBox_2.setFont(new Font("Times New Roman", Font.PLAIN, 19));
			comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Bachiller", "Licenciado", "Mag\u00EDster", "Profesional", "Doctorado"}));
			comboBox_2.setBounds(192, 297, 297, 22);
			panelJuez.add(comboBox_2);
			{
				JLabel label_2 = new JLabel("\u00C1rea de estudio:");
				label_2.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				label_2.setBounds(17, 348, 165, 16);
				panelJuez.add(label_2);
			}
			{
				comboBox_1 = new JComboBox();
				comboBox_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Jurado aux = (Jurado)miPersona;
						aux.setAreaEstudio(comboBox_1.getSelectedItem().toString());
					}
				});
				
				comboBox_1.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Matem\u00E1ticas", "Qu\u00EDmica", "Biolog\u00EDa", "Historia", "F\u00EDsica", "Ingenier\u00EDa"}));
				comboBox_1.setBounds(192, 345, 297, 22);
				panelJuez.add(comboBox_1);
			}
		}
		
		
		if (miPersona instanceof Participante) {
			setTitle("Modificando participante");
			panelJuez.setVisible(false);
			panel.setVisible(true);
			
			
			
			
		}
		else if(miPersona instanceof Jurado) {
			setTitle("Modificando juez");
			panelJuez.setVisible(true);
			panel.setVisible(false);
			
		}
		String cedula = miPersona.getCedula();
		String gradoAcademico = miPersona.getGradoAcademico();
	
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnModi = new JButton("Modificar");
				
				btnModi.setFont(new Font("Tahoma", Font.PLAIN, 11));
				btnModi.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
								if(panel.isVisible() && txtCedula.getText().length() >=1 && txtNombre.getText().length() >= 1 && txtTelefono.getText().length() >= 1
										&& txtDireccion.getText().length() >= 1)
								{ 
									
										
								if(cedula.equalsIgnoreCase(txtCedula.getText())) {
									
									miPersona.setCedula(txtCedula.getText());
									miPersona.setNombre(txtNombre.getText());
									miPersona.setTelefono(txtTelefono.getText());
									miPersona.setDireccion(txtDireccion.getText());
									
									
									{if (rdbtnMasculin.isSelected()) {
											miPersona.setSexo("Masculino");
											rdbtnFemenino.setSelected(false);
									
										}
									if(rdbtnFemenino.isSelected()) {
											miPersona.setSexo("Femenino");
											rdbtnMasculin.setSelected(false);
										}
									}
									
										JOptionPane.showMessageDialog(null, "Modificación realizada", "Notificación", JOptionPane.INFORMATION_MESSAGE);
										ListarPersonas.loadPersonas();
									
									dispose();
								}
								else if (cedula!= txtCedula.getText()){
									if((Empresa.getInstance().verificarCedulaUnica(txtCedula.getText()))) {
								}
									JOptionPane.showMessageDialog(null, "Esta cédula ha sido registrada anteriormente", "Notificación", JOptionPane.WARNING_MESSAGE);
									
								}
								else {
									miPersona.setCedula(txtCedula.getText());
									miPersona.setNombre(txtNombre.getText());
									miPersona.setTelefono(txtTelefono.getText());
									miPersona.setDireccion(txtDireccion.getText());
									/*if(gradoAcademico!=comboBox.getSelectedItem().toString()) {
										miPersona.setGradoAcademico(comboBox.getSelectedItem().toString());
										}
										else {
											miPersona.setGradoAcademico(gradoAcademico);
										}*/
									
									{if (rdbtnMasculin.isSelected()) {
											miPersona.setSexo("Masculino");
											rdbtnFemenino.setSelected(false);
									
										}
									if(rdbtnFemenino.isSelected())  {
											miPersona.setSexo("Femenino");
											rdbtnMasculin.setSelected(false);
										}
									}
									
										JOptionPane.showMessageDialog(null, "Modificación realizada", "Notificación", JOptionPane.INFORMATION_MESSAGE);
										ListarPersonas.loadPersonas();
									
									dispose();
								}
								
								}
								else if(panelJuez.isVisible() && txtCedulaJuez.getText().length() >=1 && txtNombreJuez.getText().length() >= 1 && txtTelefonoJuez.getText().length() >= 1
										&& txtDireccionJuez.getText().length() >= 1)
								{ 
									Jurado aux = (Jurado) miPersona;
									
									String areaEstudio = aux.getAreaEstudio();
										
								if (cedula.equalsIgnoreCase(txtCedulaJuez.getText())){
									aux.setCedula(txtCedulaJuez.getText());
									aux.setNombre(txtNombreJuez.getText());
									aux.setTelefono(txtTelefonoJuez.getText());
									aux.setDireccion(txtDireccionJuez.getText());
									//aux.setGradoAcademico(comboBox_2.getSelectedItem().toString());
									//aux.setAreaEstudio(comboBox_1.getSelectedItem().toString());
									
									/*if(gradoAcademico!=comboBox_2.getSelectedItem().toString()) {
										aux.setGradoAcademico(comboBox_2.getSelectedItem().toString());
										}
										else {
											aux.setGradoAcademico(gradoAcademico);
										}*/
									
									/*{
										if(areaEstudio.equalsIgnoreCase(comboBox_1.getSelectedItem().toString())){
											aux.setAreaEstudio(comboBox_1.getSelectedItem().toString());
										}
										else{
											aux.setAreaEstudio(areaEstudio);
										}
									}*/
									
									{if (rdbtnMasculin_1.isSelected()) {
											miPersona.setSexo("Masculino");
											rdbtnFemenino_1.setSelected(false);
									
										}
									if(rdbtnFemenino_1.isSelected()) {
											miPersona.setSexo("Femenino");
											rdbtnMasculin_1.setSelected(false);
										}
									}
									
									
									
									JOptionPane.showMessageDialog(null, "Modificación realizada", "Notificación", JOptionPane.INFORMATION_MESSAGE);
									ListarPersonas.loadPersonas();
									dispose();
								}
								
								else if(cedula!= txtCedulaJuez.getText()) {
									if (Empresa.getInstance().verificarCedulaUnica(txtCedulaJuez.getText())) {
									JOptionPane.showMessageDialog(null, "Esta cédula ha sido registrada anteriormente", "Notificación", JOptionPane.WARNING_MESSAGE);
									
								}
									else {
										aux.setCedula(txtCedulaJuez.getText());
										aux.setNombre(txtNombreJuez.getText());
										aux.setTelefono(txtTelefonoJuez.getText());
										aux.setDireccion(txtDireccionJuez.getText());
										//aux.setGradoAcademico(comboBox_2.getSelectedItem().toString());
										//aux.setAreaEstudio(comboBox_1.getSelectedItem().toString());
									
										
										/*if(gradoAcademico!=comboBox_2.getSelectedItem().toString()) {
											aux.setGradoAcademico(comboBox_2.getSelectedItem().toString());
											}
											else {
												aux.setGradoAcademico(gradoAcademico);
											}*/
										/*{
											if(areaEstudio.equalsIgnoreCase(comboBox_1.getSelectedItem().toString())){
												aux.setAreaEstudio(comboBox_1.getSelectedItem().toString());
											}
											else{
												aux.setAreaEstudio(areaEstudio);
											}
										}*/
										
									 
										{if (rdbtnMasculin_1.isSelected()) {
											miPersona.setSexo("Masculino");
											rdbtnFemenino_1.setSelected(false);
									
										}
									if(rdbtnFemenino_1.isSelected()) {
											miPersona.setSexo("Femenino");
											rdbtnMasculin_1.setSelected(false);
										}
									}
										
										
										
										JOptionPane.showMessageDialog(null, "Modificación realizada", "Notificación", JOptionPane.INFORMATION_MESSAGE);
										ListarPersonas.loadPersonas();
										dispose();
									}
							}
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
