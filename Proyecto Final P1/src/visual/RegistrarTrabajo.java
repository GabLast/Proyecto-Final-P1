package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logica.Empresa;
import logica.Participante;
import logica.Trabajo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistrarTrabajo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JTextField txtParticipante;
	private JTextField txtIDTrabajo;
	private JTextField txtTema;
	private JTextField txtDescripcion;
	JComboBox cbxArea;

	private static Participante duenioGlobal = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Participante duenio = null;
			RegistrarTrabajo dialog = new RegistrarTrabajo(duenio);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarTrabajo(Participante duenio) 
	{
		this.duenioGlobal = duenio;
		setTitle("Registrar trabajo");
		setBounds(100, 100, 418, 498);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panelParticipante = new JPanel();
			panelParticipante.setBorder(new TitledBorder(null, "Datos del autor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelParticipante.setBounds(12, 13, 365, 126);
			contentPanel.add(panelParticipante);
			panelParticipante.setLayout(null);
			{
				JLabel lblIdcdula = new JLabel("ID/C\u00E9dula:");
				lblIdcdula.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
				lblIdcdula.setBounds(21, 13, 102, 43);
				panelParticipante.add(lblIdcdula);
			}
			{
				txtCedula = new JTextField();
				txtCedula.setEditable(false);
				txtCedula.setText(duenio.getCedula());
				txtCedula.setColumns(10);
				txtCedula.setBounds(114, 24, 116, 20);
				panelParticipante.add(txtCedula);
			}
			{
				JLabel lblParticipante = new JLabel("Participante:");
				lblParticipante.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
				lblParticipante.setBounds(21, 69, 102, 43);
				panelParticipante.add(lblParticipante);
			}
			{
				txtParticipante = new JTextField();
				txtParticipante.setEditable(false);
				txtParticipante.setText(duenio.getNombre());
				txtParticipante.setColumns(10);
				txtParticipante.setBounds(114, 80, 116, 20);
				panelParticipante.add(txtParticipante);
			}
		}
		
		JPanel panelTrabajo = new JPanel();
		panelTrabajo.setBorder(new TitledBorder(null, "Datos del trabajo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTrabajo.setBounds(12, 152, 365, 238);
		contentPanel.add(panelTrabajo);
		panelTrabajo.setLayout(null);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
		lblCdigo.setBounds(10, 15, 102, 43);
		panelTrabajo.add(lblCdigo);
		
		txtIDTrabajo = new JTextField();
		txtIDTrabajo.setEditable(false);
		txtIDTrabajo.setText("TRAB"+duenio.getGenIdTrabajo());
		txtIDTrabajo.setBounds(96, 26, 261, 20);
		panelTrabajo.add(txtIDTrabajo);
		txtIDTrabajo.setColumns(10);
		
		JLabel lblNombre = new JLabel("Tema:");
		lblNombre.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
		lblNombre.setBounds(10, 73, 102, 43);
		panelTrabajo.add(lblNombre);
		
		txtTema = new JTextField();
		txtTema.setBounds(96, 84, 261, 20);
		panelTrabajo.add(txtTema);
		txtTema.setColumns(10);
		
		JLabel lblrea = new JLabel("\u00C1rea:");
		lblrea.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
		lblrea.setBounds(10, 131, 59, 43);
		panelTrabajo.add(lblrea);
		
		cbxArea = new JComboBox();
		cbxArea.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Matem\u00E1ticas", "Qu\u00EDmica", "Biolog\u00EDa", "Historia", "F\u00EDsica", "Ingenier\u00EDa"}));
		cbxArea.setBounds(96, 142, 261, 20);
		panelTrabajo.add(cbxArea);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
		lblDescripcin.setBounds(10, 189, 102, 31);
		panelTrabajo.add(lblDescripcin);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(96, 189, 261, 31);
		panelTrabajo.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(txtTema.getText().isEmpty() || txtDescripcion.getText().isEmpty() ||
								cbxArea.getSelectedIndex() < 1)
						{
							JOptionPane.showMessageDialog(null, "Llenar todas las casillas"
									+ " y elegir un área de trabajo", "Error", JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							Trabajo nuevoTrabajo = new Trabajo(duenio, txtIDTrabajo.getText(),
									txtTema.getText(), cbxArea.getSelectedItem().toString(), txtDescripcion.getText());
							duenioGlobal.insertarTrabajo(nuevoTrabajo);
							
							JOptionPane.showMessageDialog(null, "Trabajo registrado satisfactoriamente"
									, "Notificación", JOptionPane.INFORMATION_MESSAGE);
							clean();
							
						}
					}
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
	}
	private void clean()
	{		
		txtDescripcion.setText("");
		txtIDTrabajo.setText("TRAB"+duenioGlobal.getGenIdTrabajo());
		cbxArea.setSelectedIndex(0);
		txtTema.setText("");
		
	}
}
