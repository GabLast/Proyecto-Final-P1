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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ModificarTrabajo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Trabajo miTrabajo = null;
	private JTextField txtID;
	private JTextField txtTema;
	private JTextField txtDescripcion;
	private JComboBox comboBox;

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
				JLabel lblCdigo = new JLabel("C\u00F3digo:");
				lblCdigo.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				lblCdigo.setBounds(17, 32, 73, 23);
				panel.add(lblCdigo);
			}
			{
				JLabel lblTema = new JLabel("Tema:");
				lblTema.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				lblTema.setBounds(17, 87, 88, 23);
				panel.add(lblTema);
			}
			{
				JLabel lblArea = new JLabel("\u00C1rea:");
				lblArea.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				lblArea.setBounds(17, 142, 88, 23);
				panel.add(lblArea);
			}
			{
				JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
				lblDescripcin.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				lblDescripcin.setBounds(17, 197, 109, 23);
				panel.add(lblDescripcin);
			}
			{
				txtID = new JTextField();
				txtID.setEditable(false);
				txtID.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				txtID.setColumns(10);
				txtID.setBounds(107, 29, 244, 29);
				txtID.setText(miTrabajo.getId());
				panel.add(txtID);
			}
			{
				txtTema = new JTextField();
				txtTema.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				txtTema.setColumns(10);
				txtTema.setBounds(107, 84, 244, 29);
				txtTema.setText(miTrabajo.getNombreTrabajo());
				panel.add(txtTema);
			}
			{
				txtDescripcion = new JTextField();
				txtDescripcion.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				txtDescripcion.setColumns(10);
				txtDescripcion.setBounds(124, 194, 227, 29);
				txtDescripcion.setText(miTrabajo.getArea());
				panel.add(txtDescripcion);
			}
			
			comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Matem\u00E1ticas", "Qu\u00EDmica", "Biolog\u00EDa", "Historia", "F\u00EDsica", "Ingenier\u00EDa"}));
			comboBox.setBounds(109, 146, 143, 20);
			panel.add(comboBox);
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
						if(txtID.getText().length() >=1 && txtTema.getText().length() >= 1 && txtDescripcion.getText().length() >= 1 && comboBox.getSelectedIndex() > 1)
						{
							txtTema.setText(txtTema.getText());
							
							txtDescripcion.setText(txtDescripcion.getText());
							txtID.setText(txtID.getText());
							
							miTrabajo.setId(txtID.getText());
							miTrabajo.setNombreTrabajo(txtTema.getText());
							miTrabajo.setDescripcion(txtDescripcion.getText());
							miTrabajo.setArea(comboBox.getSelectedItem().toString());

							
							
							JOptionPane.showMessageDialog(null, "Modificaci�n realizada", "Notificaci�n", JOptionPane.INFORMATION_MESSAGE);
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