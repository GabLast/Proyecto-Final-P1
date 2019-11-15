package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import com.toedter.calendar.JCalendar;

import logica.Empresa;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

public class CreandoEvento extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JButton btnCrear;
	private JTextField txtID;
	private JTextField txtNombre;
	JCalendar calendar;
	
	JList listRecursos;
	JList listRecursosElegidos;
	JList listPartiElegidos;
	JList listParticipantes;
	
	DefaultListModel modelRecursos;
	DefaultListModel modelRecElegidos;
	DefaultListModel modelPartis;
	DefaultListModel modelPartiElegidos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreandoEvento dialog = new CreandoEvento();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreandoEvento() {
		setTitle("Organizaci\u00F3n de un evento");
		setBounds(100, 100, 769, 930);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Información del Evento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBounds(17, 31, 701, 306);
				//panel_1.setBorder(new TitledBorder(null, "Información general", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel.add(panel_1);
				panel_1.setLayout(null);
				{
					JLabel lblNewLabel = new JLabel("Nombre:");
					lblNewLabel.setBounds(20, 53, 74, 23);
					panel_1.add(lblNewLabel);
				}
				{
					JLabel lblId = new JLabel("ID:");
					lblId.setBounds(20, 15, 82, 23);
					panel_1.add(lblId);
				}
				{
					JLabel lblFecha = new JLabel("Fecha:");
					lblFecha.setBounds(20, 91, 82, 23);
					panel_1.add(lblFecha);
				}
				
				calendar = new JCalendar();
				calendar.setBounds(17, 129, 667, 162);
				panel_1.add(calendar);
				
				txtID = new JTextField();
				txtID.setEditable(false);
				txtID.setText("EV"+Empresa.getInstance().getGenIDEvento());
				txtID.setBounds(97, 12, 252, 29);
				panel_1.add(txtID);
				txtID.setColumns(10);
				
				txtNombre = new JTextField();
				txtNombre.setColumns(10);
				txtNombre.setBounds(97, 50, 252, 29);
				panel_1.add(txtNombre);
				{
					JLabel lblTipo = new JLabel("Tipo:");
					lblTipo.setBounds(366, 15, 74, 23);
					panel_1.add(lblTipo);
				}
				{
					JComboBox comboBox = new JComboBox();
					comboBox.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Panel", "Ponencia", "Conferencia", "Jornada", "Mesa Redonda"}));
					comboBox.setBounds(416, 12, 268, 29);
					panel_1.add(comboBox);
				}
			}
			{
				JPanel panelParticipantes = new JPanel();
				panelParticipantes.setBorder(new TitledBorder(null, "Registro de Participantes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panelParticipantes.setBounds(17, 356, 701, 247);
				panel.add(panelParticipantes);
				panelParticipantes.setLayout(null);
				{
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBounds(17, 61, 295, 167);
					panelParticipantes.add(scrollPane);
					{
						listParticipantes = new JList();
						scrollPane.setViewportView(listParticipantes);
					}
				}
				{
					JLabel lblPosiblesParticipantes = new JLabel("Posibles participantes:");
					lblPosiblesParticipantes.setBounds(17, 31, 219, 23);
					panelParticipantes.add(lblPosiblesParticipantes);
				}
				{
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBounds(389, 61, 295, 167);
					panelParticipantes.add(scrollPane);
					{
						listPartiElegidos = new JList();
						scrollPane.setViewportView(listPartiElegidos);
					}
				}
				{
					JButton btnDerechaParti = new JButton(">");
					btnDerechaParti.setBounds(325, 90, 47, 31);
					panelParticipantes.add(btnDerechaParti);
				}
				{
					JButton btnIzquierdaParti = new JButton("<");
					btnIzquierdaParti.setBounds(325, 170, 47, 31);
					panelParticipantes.add(btnIzquierdaParti);
				}
				{
					JLabel lblParticipantesSeleccionados = new JLabel("Participantes seleccionados:");
					lblParticipantesSeleccionados.setBounds(389, 31, 295, 23);
					panelParticipantes.add(lblParticipantesSeleccionados);
				}
			}
			{
				JPanel panelRecursos = new JPanel();
				panelRecursos.setBounds(17, 607, 701, 189);
				panelRecursos.setBorder(new TitledBorder(null, "Registro de Recursos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel.add(panelRecursos);
				panelRecursos.setLayout(null);
				{
					JLabel lblRecursosDisponibles = new JLabel("Recursos disponibles:");
					lblRecursosDisponibles.setBounds(14, 29, 219, 23);
					panelRecursos.add(lblRecursosDisponibles);
				}
				{
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBounds(14, 60, 298, 102);
					panelRecursos.add(scrollPane);
					{
						listRecursos = new JList();
						scrollPane.setViewportView(listRecursos);
					}
				}
				{
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBounds(387, 60, 298, 102);
					panelRecursos.add(scrollPane);
					{
						listRecursosElegidos = new JList();
						scrollPane.setViewportView(listRecursosElegidos);
					}
				}
				{
					JLabel lblRecursosAUtilizar = new JLabel("Recursos a utilizar:");
					lblRecursosAUtilizar.setBounds(386, 29, 219, 23);
					panelRecursos.add(lblRecursosAUtilizar);
				}
				{
					JButton btnDerechaRecursos = new JButton(">");
					btnDerechaRecursos.setBounds(326, 71, 47, 31);
					panelRecursos.add(btnDerechaRecursos);
				}
				{
					JButton btnIzquierdaRecursos = new JButton("<");
					btnIzquierdaRecursos.setBounds(326, 113, 47, 31);
					panelRecursos.add(btnIzquierdaRecursos);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnCrear = new JButton("Crear");
				btnCrear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				btnCrear.setActionCommand("OK");
				buttonPane.add(btnCrear);
				getRootPane().setDefaultButton(btnCrear);
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
}
