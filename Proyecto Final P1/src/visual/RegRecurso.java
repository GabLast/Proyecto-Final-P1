package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logica.Empresa;
import logica.Recurso;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegRecurso extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	JSpinner spnCantidad;
	JButton btnRegistrar;
	
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
			RegRecurso dialog = new RegRecurso();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegRecurso() {
		setTitle("Registro de recursos");
		setResizable(false);
		setBounds(100, 100, 402, 253);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegRecurso.class.getResource("/imagen/titleagregarrecurso.png")));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Informaci\u00F3n del recurso", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel label = new JLabel("Nombre:");
			label.setBounds(10, 49, 83, 17);
			panel.add(label);
			
			JLabel label_1 = new JLabel("Cantidad:");
			label_1.setBounds(10, 115, 83, 17);
			panel.add(label_1);
			
			txtNombre = new JTextField();
			txtNombre.setText("");
			txtNombre.setColumns(10);
			txtNombre.setBounds(85, 46, 137, 20);
			panel.add(txtNombre);
			
			spnCantidad = new JSpinner();
			spnCantidad.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spnCantidad.setBounds(85, 112, 137, 23);
			panel.add(spnCantidad);
			
			JLabel lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/imagen/boxes.png")));
			lblNewLabel_1.setBounds(232, 11, 148, 155);
			panel.add(lblNewLabel_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(!txtNombre.getText().isEmpty())
						{
							if(!Empresa.getInstance().verificarRecursoUnico(txtNombre.getText()))
							{
								Recurso nuevoRecurso = new Recurso(txtNombre.getText(), (Integer) spnCantidad.getValue());
								Empresa.getInstance().insertarRecurso(nuevoRecurso);
								JOptionPane.showMessageDialog(null, "Recurso Registrado satisfactoriamente", "Notificación", JOptionPane.INFORMATION_MESSAGE);
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
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton cancelButton = new JButton("Canelar");
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
	
	public void clean()
	{
		txtNombre.setText("");
		spnCantidad.setValue(1);
	}
}
