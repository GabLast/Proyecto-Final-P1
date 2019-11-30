package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logica.Persona;
import logica.Recurso;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Color;

public class ModifcarRecursos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Recurso miRecurso = null;
	private JTextField txNombre;
	private JSpinner txCantidad;
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
	public ModifcarRecursos(Recurso miRecurso) {
		this.miRecurso = miRecurso;
		setTitle("Modificando Recurso");
		setResizable(false);
		setBounds(100, 100, 426, 253);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegRecurso.class.getResource("/imagen/titleagregarrecurso.png")));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Recurso", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panel, BorderLayout.CENTER);
			{
				JLabel lblNombre = new JLabel("Nombre:");
				lblNombre.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				lblNombre.setBounds(17, 42, 73, 23);
				panel.add(lblNombre);
			}
			{
				JLabel lblCantidad = new JLabel("Cantidad:");
				lblCantidad.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				lblCantidad.setBounds(17, 107, 88, 23);
				panel.add(lblCantidad);
			}
			
			{
				txNombre = new JTextField();
				txNombre.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				txNombre.setColumns(10);
				txNombre.setBounds(107, 39, 130, 29);
				txNombre.setText(miRecurso.getTipo());
				panel.add(txNombre);
			}
			
			txCantidad = new JSpinner();
			txCantidad.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			txCantidad.setBounds(107, 104, 130, 29);
			panel.add(txCantidad);
			
			JLabel lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/imagen/boxes.png")));
			lblNewLabel_1.setBounds(247, 11, 153, 151);
			panel.add(lblNewLabel_1);
			
			
			
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
						if(txNombre.getText().length() >=1 && (int)txCantidad.getValue() >= 1 )
						{
//							
							miRecurso.setTipo(txNombre.getText());
							miRecurso.setCantidad((Integer) txCantidad.getValue());
							
							JOptionPane.showMessageDialog(null, "Modificación realizada", "Notificación", JOptionPane.INFORMATION_MESSAGE);
							ListaRecursos.loadRecursos();
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
