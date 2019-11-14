package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class RegComision extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegComision dialog = new RegComision();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegComision() {
		setTitle("Formando una Comisi\u00F3n");
		setBounds(100, 100, 899, 596);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setBorder(new TitledBorder(null, "Información", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setLayout(null);
			{
				JLabel lblId = new JLabel("ID:");
				lblId.setBounds(12, 58, 56, 16);
				panel.add(lblId);
			}
			{
				JLabel lblTipo = new JLabel("\u00C1rea:");
				lblTipo.setBounds(12, 106, 56, 16);
				panel.add(lblTipo);
			}
			
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Matem\u00E1ticas", "Qu\u00EDmica", "Biolog\u00EDa", "Historia", "F\u00EDsica", "Ingenier\u00EDa"}));
			comboBox.setBounds(70, 103, 166, 22);
			panel.add(comboBox);
			
			textField = new JTextField();
			textField.setBounds(70, 55, 166, 22);
			panel.add(textField);
			textField.setColumns(10);
			
			JLabel lblFechaDeCreacin = new JLabel("Fecha de creaci\u00F3n:");
			lblFechaDeCreacin.setBounds(12, 148, 109, 16);
			panel.add(lblFechaDeCreacin);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
