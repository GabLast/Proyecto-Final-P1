package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logica.Empresa;
import logica.Evento;
import logica.Jurado;
import logica.Participante;
import logica.Persona;
import logica.Trabajo;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class RegComision extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtID;
	private JTextField txtFechaCreacion;
	
	JList listJuecesDisponibles;
	JList listJuecesSeleccionados;
	JList listTrabajosDisponibles;
	JList listTrabajosSeleccionados;
	
	DefaultListModel jListJuecesDispModel;
	DefaultListModel jListJuecesSelectModel;
	DefaultListModel jListJobDispModel;
	DefaultListModel jListJobSelectModel;
	
	ArrayList<Jurado> jueces = new ArrayList();
	ArrayList<Trabajo> trabajos = new ArrayList();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Evento miEvento = null;
			RegComision dialog = new RegComision(miEvento);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegComision(Evento miEvento) {
		setTitle("Formando una comisi\u00F3n");
		setBounds(100, 100, 571, 767);
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
				lblId.setBounds(12, 31, 56, 16);
				panel.add(lblId);
			}
			{
				JLabel lblTipo = new JLabel("\u00C1rea:");
				lblTipo.setBounds(12, 77, 56, 16);
				panel.add(lblTipo);
			}
			
			JComboBox cbxArea = new JComboBox();
			
			jListJuecesSelectModel = new DefaultListModel();
			
			cbxArea.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					//Mostrando los jueces disponibles para el jurado
					
					jListJuecesDispModel = new DefaultListModel();
				
					for(Persona juez : Empresa.getInstance().getPersonasRegistradas())
					{
						if(juez instanceof Jurado)
						{
							if(cbxArea.getSelectedItem().toString().equalsIgnoreCase(((Jurado) juez).getAreaEstudio()))
							{
								jListJuecesDispModel.addElement(juez.getCedula());
							}
						}
					}
					listJuecesDisponibles.setModel(jListJuecesDispModel);
					
					
					//Mostrando los trabajos disponibles para la comision de los participantes ya registrados en el evento
					
					jListJobDispModel = new DefaultListModel();
					
					for(Participante parti : miEvento.getParticipantes())
					{
						for(Trabajo job : parti.getMisTrabajos())
						{
							if(job.getArea().equalsIgnoreCase(cbxArea.getSelectedItem().toString()))
							{
								jListJobSelectModel.addElement(job.getNombreTrabajo());
							}
						}
						
					}
					listTrabajosDisponibles.setModel(jListJobDispModel);
					
				}
			});
			cbxArea.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Matem\u00E1ticas", "Qu\u00EDmica", "Biolog\u00EDa", "Historia", "F\u00EDsica", "Ingenier\u00EDa"}));
			cbxArea.setBounds(156, 74, 166, 22);
			panel.add(cbxArea);
			
			txtID = new JTextField();
			txtID.setEditable(false);
			txtID.setBounds(156, 28, 166, 22);
			panel.add(txtID);
			txtID.setColumns(10);
			
			JLabel lblFechaDeCreacin = new JLabel("Fecha de creaci\u00F3n:");
			lblFechaDeCreacin.setBounds(12, 122, 166, 16);
			panel.add(lblFechaDeCreacin);
			
			txtFechaCreacion = new JTextField();
			txtFechaCreacion.setEditable(false);
			txtFechaCreacion.setColumns(10);
			txtFechaCreacion.setBounds(156, 119, 166, 22);
			
			panel.add(txtFechaCreacion);
			
			JPanel panelJurado = new JPanel();
			panelJurado.setBorder(new TitledBorder(null, "Selección del jurado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelJurado.setBounds(12, 157, 508, 255);
			panel.add(panelJurado);
			panelJurado.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(17, 61, 184, 133);
			panelJurado.add(scrollPane);
			
			listJuecesDisponibles = new JList();
			scrollPane.setViewportView(listJuecesDisponibles);
			
			JLabel label = new JLabel("Jueces disponibles:");
			label.setBounds(17, 26, 184, 16);
			panelJurado.add(label);
			
			JButton btnRightJurado = new JButton(">");
			btnRightJurado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					//MOVIENDO IZQUIERDA A DERECHA
					String string = (String) listJuecesDisponibles.getSelectedValue();
					
					if(listJuecesDisponibles.getSelectedIndex() == -1)
					{
						JOptionPane.showMessageDialog(null, "Seleccione algún juez", "Notificación", JOptionPane.WARNING_MESSAGE);
					}
					else
					{
						//agregar valor a la lista derecha
						int value = listJuecesDisponibles.getSelectedIndex();
						jListJuecesSelectModel.addElement(string);
						listJuecesSeleccionados.setModel(jListJuecesSelectModel);
						
//						System.out.println("Moviendo a la derecha:");
//						System.out.println("1"+list_ViewingQuesos.getSelectedValue());
//						System.out.println("2"+list_ViewingQuesos.getName());
//						System.out.println("3"+list_ViewingQuesos.getSelectedValue().toString());
						
						Jurado buscando = Empresa.getInstance().buscarJuezByID(listJuecesDisponibles.getSelectedValue().toString());
						jueces.add(buscando);
						
						//removiendo valor de la lista de la izquierda
						if(jListJuecesDispModel.getSize() != 0)
						{
							jListJuecesDispModel.removeElementAt(value);
						}
						
						listJuecesDisponibles.setModel(jListJuecesDispModel);
					}
				}
			});
			btnRightJurado.setBounds(218, 68, 71, 31);
			panelJurado.add(btnRightJurado);
			
			JButton btnLeftJurado = new JButton("<");
			btnLeftJurado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					//MOVIENDO DE DERECHA A IZQUIERDA
					String string = (String) listJuecesSeleccionados.getSelectedValue();
					
					if(listJuecesSeleccionados.getSelectedIndex() == -1)
					{
						JOptionPane.showMessageDialog(null, "Seleccione algún juez", "Notificación", JOptionPane.WARNING_MESSAGE);
					}
					else
					{
						//agregar valor a la lista izquierda
						
						int value = listJuecesSeleccionados.getSelectedIndex();
						
						jListJuecesDispModel.addElement(string);
						listJuecesDisponibles.setModel(jListJuecesDispModel);
						
//						System.out.println("Moviendo a la izquierda:");
//						System.out.println("1:"+list_CompraQuesos.getSelectedValue());
//						System.out.println("2"+list_CompraQuesos.getName());
//						System.out.println("3:"+list_CompraQuesos.getSelectedValue().toString());
						
						Jurado buscando = Empresa.getInstance().buscarJuezByID(listJuecesDisponibles.getSelectedValue().toString());
						jueces.remove(buscando);
						
						//removiendo valor de la lista de la derecha
						if(jListJuecesSelectModel.getSize() != 0)
						{
							jListJuecesSelectModel.removeElementAt(value);
						}
						
						listJuecesSeleccionados.setModel(jListJuecesSelectModel);
					}
				}
			});
			btnLeftJurado.setBounds(218, 151, 71, 31);
			panelJurado.add(btnLeftJurado);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(306, 61, 184, 133);
			panelJurado.add(scrollPane_1);
			
			listJuecesSeleccionados = new JList();
			scrollPane_1.setViewportView(listJuecesSeleccionados);
			
			JLabel lblPresidente = new JLabel("Presidente:");
			lblPresidente.setBounds(17, 213, 94, 16);
			panelJurado.add(lblPresidente);
			
			JComboBox cbxPresidente = new JComboBox();
			cbxPresidente.setBounds(128, 210, 166, 22);
			panelJurado.add(cbxPresidente);
			cbxPresidente.setModel(new DefaultComboBoxModel(new String[] {"."}));
			
			JLabel lblJuecesSeleccionados = new JLabel("Jueces seleccionados:");
			lblJuecesSeleccionados.setBounds(306, 26, 184, 16);
			panelJurado.add(lblJuecesSeleccionados);
			
			JPanel panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.setBorder(new TitledBorder(null, "Selección de trabajos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(12, 431, 508, 211);
			panel.add(panel_1);
			
			JLabel lblTrabajosDelrea = new JLabel("Trabajos del \u00E1rea:");
			lblTrabajosDelrea.setBounds(17, 26, 152, 23);
			panel_1.add(lblTrabajosDelrea);
			
			JButton btnRightTrabajos = new JButton(">");
			btnRightTrabajos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					//MOVIENDO IZQUIERDA A DERECHA
					String string = (String) listTrabajosDisponibles.getSelectedValue();
					
					if(listJuecesDisponibles.getSelectedIndex() == -1)
					{
						JOptionPane.showMessageDialog(null, "Seleccione algún trabajo", "Notificación", JOptionPane.WARNING_MESSAGE);
					}
					else
					{
						//agregar valor a la lista derecha
						int value = listTrabajosDisponibles.getSelectedIndex();
						jListJobSelectModel.addElement(string);
						listJuecesSeleccionados.setModel(jListJobSelectModel);
						
//						System.out.println("Moviendo a la derecha:");
//						System.out.println("1"+list_ViewingQuesos.getSelectedValue());
//						System.out.println("2"+list_ViewingQuesos.getName());
//						System.out.println("3"+list_ViewingQuesos.getSelectedValue().toString());
						
						Trabajo buscando = miEvento.buscandoTrabajoEntreMisParticipantesByName(listTrabajosDisponibles.getSelectedValue().toString());
						trabajos.add(buscando);
						
						//removiendo valor de la lista de la izquierda
						if(jListJobDispModel.getSize() != 0)
						{
							jListJobDispModel.removeElementAt(value);
						}
						
						listTrabajosDisponibles.setModel(jListJobDispModel);
					}
				}
			});
			btnRightTrabajos.setBounds(218, 68, 71, 31);
			panel_1.add(btnRightTrabajos);
			
			JButton btnLeftTrabajos = new JButton("<");
			btnLeftTrabajos.setBounds(218, 151, 71, 31);
			panel_1.add(btnLeftTrabajos);
			
			JLabel lblTrabajosSeleccionados = new JLabel("Trabajos seleccionados:");
			lblTrabajosSeleccionados.setBounds(306, 26, 152, 16);
			panel_1.add(lblTrabajosSeleccionados);
			
			JScrollPane scrollPane_3 = new JScrollPane();
			scrollPane_3.setBounds(17, 59, 184, 133);
			panel_1.add(scrollPane_3);
			
			listTrabajosDisponibles = new JList();
			scrollPane_3.setViewportView(listTrabajosDisponibles);
			
			JScrollPane scrollPane_2 = new JScrollPane();
			scrollPane_2.setBounds(306, 59, 184, 133);
			panel_1.add(scrollPane_2);
			
			listTrabajosSeleccionados = new JList();
			scrollPane_2.setViewportView(listTrabajosSeleccionados);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton btnCancel = new JButton("Cancelar");
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
	}
}
