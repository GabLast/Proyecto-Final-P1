package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logica.Empresa;
import logica.Jurado;
import logica.Participante;

import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegPersona extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JTextField txtCedulaParti;
	private JTextField txtNombreParti;
	private JTextField txtTeleParti;
	private JTextField txtDireParti;
	JComboBox cbxGrado;
	JComboBox cbxAreaEstudio;
	JComboBox cbxGradoParti;
	JRadioButton rdbtnParticipante;
	JRadioButton rdbtnJuez;
	JPanel panelJuez;
	JPanel panelParticipante;

	JRadioButton rdbtnMasculin;
	JRadioButton rdbtnFemenino;
	JRadioButton rdbtnMascParti;
	JRadioButton rdnbtnFemParti;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegPersona dialog = new RegPersona();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegPersona() {
		setTitle("Registrar Persona");
		setBounds(100, 100, 446, 543);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Információn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			rdbtnParticipante = new JRadioButton("Participante");
			rdbtnParticipante.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnJuez.setSelected(false);
					rdbtnParticipante.setSelected(true);
					
					panelParticipante.setVisible(true);
					panelJuez.setVisible(false);
					
				}
			});
			rdbtnParticipante.setBounds(78, 40, 127, 25);
			panel.add(rdbtnParticipante);
			
			rdbtnJuez = new JRadioButton("Juez");
			rdbtnJuez.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnJuez.setSelected(true);
					rdbtnParticipante.setSelected(false);
					
					panelJuez.setVisible(true);
					panelParticipante.setVisible(false);
				}
			});
			rdbtnJuez.setBounds(231, 40, 127, 25);
			panel.add(rdbtnJuez);
			
			panelJuez = new JPanel();
			panelJuez.setVisible(false);
			panelJuez.setBorder(new TitledBorder(null, "Datos del juez", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelJuez.setBounds(12, 85, 395, 356);
			panel.add(panelJuez);
			panelJuez.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("C\u00E9dula/ID:");
			lblNewLabel.setBounds(12, 30, 72, 16);
			panelJuez.add(lblNewLabel);
			
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(12, 76, 72, 16);
			panelJuez.add(lblNombre);
			
			JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
			lblTelfono.setBounds(12, 122, 72, 16);
			panelJuez.add(lblTelfono);
			
			JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
			lblDireccin.setBounds(12, 168, 72, 16);
			panelJuez.add(lblDireccin);
			
			JLabel lblSexo = new JLabel("Sexo:");
			lblSexo.setBounds(12, 214, 72, 16);
			panelJuez.add(lblSexo);
			
			JLabel lblGradoAcadmico = new JLabel("Grado Acad\u00E9mico:");
			lblGradoAcadmico.setBounds(12, 260, 112, 16);
			panelJuez.add(lblGradoAcadmico);
			
			rdbtnMasculin = new JRadioButton("Masculino");
			rdbtnMasculin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnMasculin.setSelected(true);
					rdbtnFemenino.setSelected(false);
				}
			});
			rdbtnMasculin.setBounds(139, 210, 100, 25);
			panelJuez.add(rdbtnMasculin);
			
			rdbtnFemenino = new JRadioButton("Femenino");
			rdbtnFemenino.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnMasculin.setSelected(false);
					rdbtnFemenino.setSelected(true);
				}
			});
			rdbtnFemenino.setBounds(262, 210, 112, 25);
			panelJuez.add(rdbtnFemenino);
			
			txtCedula = new JTextField();
			txtCedula.setColumns(10);
			txtCedula.setBounds(136, 28, 238, 20);
			panelJuez.add(txtCedula);
			
			txtNombre = new JTextField();
			txtNombre.setColumns(10);
			txtNombre.setBounds(136, 74, 238, 20);
			panelJuez.add(txtNombre);
			
			txtTelefono = new JTextField();
			txtTelefono.setColumns(10);
			txtTelefono.setBounds(136, 120, 238, 20);
			panelJuez.add(txtTelefono);
			
			txtDireccion = new JTextField();
			txtDireccion.setColumns(10);
			txtDireccion.setBounds(136, 166, 238, 20);
			panelJuez.add(txtDireccion);
			
			cbxGrado = new JComboBox();
			cbxGrado.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Bachiller", "Licenciado", "Mag\u00EDster", "Profesional", "Doctorado"}));
			cbxGrado.setBounds(136, 257, 238, 22);
			panelJuez.add(cbxGrado);
			
			cbxAreaEstudio = new JComboBox();
			cbxAreaEstudio.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Matem\u00E1ticas", "Qu\u00EDmica", "Biolog\u00EDa", "Historia", "F\u00EDsica", "Ingenier\u00EDa"}));
			cbxAreaEstudio.setBounds(136, 303, 238, 22);
			panelJuez.add(cbxAreaEstudio);
			
			JLabel label_7 = new JLabel("\u00C1rea de estudio:");
			label_7.setBounds(12, 306, 112, 16);
			panelJuez.add(label_7);
			
			panelParticipante = new JPanel();
			panelParticipante.setVisible(false);
			panelParticipante.setBorder(new TitledBorder(null, "Datos del participante", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelParticipante.setBounds(12, 85, 395, 356);
			panel.add(panelParticipante);
			panelParticipante.setLayout(null);
			
			JLabel label = new JLabel("C\u00E9dula/ID:");
			label.setBounds(12, 37, 72, 16);
			panelParticipante.add(label);
			
			JLabel label_1 = new JLabel("Nombre:");
			label_1.setBounds(12, 90, 72, 16);
			panelParticipante.add(label_1);
			
			JLabel label_2 = new JLabel("Tel\u00E9fono:");
			label_2.setBounds(12, 143, 72, 16);
			panelParticipante.add(label_2);
			
			JLabel label_3 = new JLabel("Direcci\u00F3n:");
			label_3.setBounds(12, 196, 72, 16);
			panelParticipante.add(label_3);
			
			JLabel label_4 = new JLabel("Sexo:");
			label_4.setBounds(12, 249, 72, 16);
			panelParticipante.add(label_4);
			
			JLabel label_5 = new JLabel("Grado Acad\u00E9mico:");
			label_5.setBounds(12, 302, 112, 16);
			panelParticipante.add(label_5);
			
			rdbtnMascParti = new JRadioButton("Masculino");
			rdbtnMascParti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnMascParti.setSelected(true);
					rdnbtnFemParti.setSelected(false);
				}
			});
			rdbtnMascParti.setBounds(139, 245, 100, 25);
			panelParticipante.add(rdbtnMascParti);
			
			rdnbtnFemParti = new JRadioButton("Femenino");
			rdnbtnFemParti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnMascParti.setSelected(false);
					rdnbtnFemParti.setSelected(true);
				}
			});
			rdnbtnFemParti.setBounds(262, 245, 112, 25);
			panelParticipante.add(rdnbtnFemParti);
			
			txtCedulaParti = new JTextField();
			txtCedulaParti.setColumns(10);
			txtCedulaParti.setBounds(136, 35, 238, 20);
			panelParticipante.add(txtCedulaParti);
			
			txtNombreParti = new JTextField();
			txtNombreParti.setColumns(10);
			txtNombreParti.setBounds(136, 88, 238, 20);
			panelParticipante.add(txtNombreParti);
			
			txtTeleParti = new JTextField();
			txtTeleParti.setColumns(10);
			txtTeleParti.setBounds(136, 141, 238, 20);
			panelParticipante.add(txtTeleParti);
			
			txtDireParti = new JTextField();
			txtDireParti.setColumns(10);
			txtDireParti.setBounds(136, 194, 238, 20);
			panelParticipante.add(txtDireParti);
			
			cbxGradoParti = new JComboBox();
			cbxGradoParti.setBounds(136, 299, 238, 22);
			cbxGradoParti.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Bachiller", "Licenciado", "Mag\u00EDster", "Profesional", "Doctorado"}));
			panelParticipante.add(cbxGradoParti);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(rdbtnParticipante.isSelected())
						{
							if(txtCedulaParti.getText().isEmpty() || txtNombreParti.getText().isEmpty()
									|| txtDireParti.getText().isEmpty() || txtTeleParti.getText().isEmpty()
									|| !(rdnbtnFemParti.isSelected() || rdbtnMascParti.isSelected()) 
									&& cbxGradoParti.getSelectedIndex() < 1)
							{
								JOptionPane.showMessageDialog(null, "Llene las casillas apropiadamente"
										, "Error", JOptionPane.INFORMATION_MESSAGE);
								
							}
							else
							{
								if(rdbtnMascParti.isSelected())
								{
									Participante nuevoParti = new Participante(txtCedulaParti.getText(), 
											txtNombreParti.getText(), txtTeleParti.getText(), 
											txtDireParti.getText(), "Masculino", 
											cbxGradoParti.getSelectedItem().toString(), null,null);
									Empresa.getInstance().insertarPersona(nuevoParti);
									JOptionPane.showMessageDialog(null, "Participante registrado satisfactoriamente"
											, "Notificación", JOptionPane.INFORMATION_MESSAGE);
									clean();
								}
								else if(rdnbtnFemParti.isSelected())
								{
									Participante nuevoParti = new Participante(txtCedulaParti.getText(), 
											txtNombreParti.getText(), txtTeleParti.getText(), 
											txtDireParti.getText(), ""
													+ "Femenino", cbxGradoParti.getSelectedItem().toString(), null,null);
									Empresa.getInstance().insertarPersona(nuevoParti);
									JOptionPane.showMessageDialog(null, "Participante registrado satisfactoriamente"
											, "Notificación", JOptionPane.INFORMATION_MESSAGE);
									clean();
								}
								else
									JOptionPane.showMessageDialog(null, "Seleccione su género"
											, "Error", JOptionPane.INFORMATION_MESSAGE);
							}
						}
						else if(rdbtnJuez.isSelected())
						{
							if(txtCedula.getText().isEmpty() || txtDireccion.getText().isEmpty()
									|| txtTelefono.getText().isEmpty() || txtNombre.getText().isEmpty()
									|| !(rdbtnFemenino.isSelected() || rdbtnMasculin.isSelected())
									&& cbxAreaEstudio.getSelectedIndex() < 1
									&& cbxGrado.getSelectedIndex() < 1)
							{
								JOptionPane.showMessageDialog(null, "Llene las casillas apropiadamente"
										, "Error", JOptionPane.INFORMATION_MESSAGE);
								
							}
							else
							{
								if(rdbtnMasculin.isSelected())
								{
									Jurado nuevoJuez = new Jurado(txtCedula.getText(), txtNombre.getText(), 
											txtTelefono.getText(), txtDireccion.getText(), 
											"Masculino", cbxGrado.getSelectedItem().toString(), 
											null, cbxAreaEstudio.getSelectedItem().toString(), false);
									
									Empresa.getInstance().insertarPersona(nuevoJuez);
									JOptionPane.showMessageDialog(null, "Participante registrado satisfactoriamente"
											, "Notificación", JOptionPane.INFORMATION_MESSAGE);
									clean();
								}
								else if(rdbtnFemenino.isSelected())
								{
									Jurado nuevoJuez = new Jurado(txtCedula.getText(), txtNombre.getText(), 
											txtTelefono.getText(), txtDireccion.getText(), 
											"Femenino", cbxGrado.getSelectedItem().toString(), 
											null, cbxAreaEstudio.getSelectedItem().toString(), false);
									Empresa.getInstance().insertarPersona(nuevoJuez);
									JOptionPane.showMessageDialog(null, "Participante registrado satisfactoriamente"
											, "Notificación", JOptionPane.INFORMATION_MESSAGE);
									clean();
								}
								else
									JOptionPane.showMessageDialog(null, "Seleccione su género"
											, "Error", JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
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
	private void clean() {

		txtCedula.setText("");
		txtCedulaParti.setText("");
		txtDireccion.setText("");
		txtDireParti.setText("");
		txtNombre.setText("");
		txtNombreParti.setText("");
		txtTeleParti.setText("");
		txtTelefono.setText("");
		cbxAreaEstudio.setSelectedIndex(0);
		cbxGrado.setSelectedIndex(0);
		cbxGradoParti.setSelectedIndex(0);
		
	}
}
