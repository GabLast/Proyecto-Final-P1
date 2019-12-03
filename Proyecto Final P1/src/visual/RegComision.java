package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logica.Comision;
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
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;

public class RegComision extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtID;
	private JTextField txtFechaCreacion;
	
	JButton btnRegistrar;
	JComboBox cbxPresidente;
	JComboBox cbxArea;
	DefaultComboBoxModel cbxModel;
	
	JList listJuecesDisponibles;
	JList listJuecesSeleccionados;
	JList listTrabajosDisponibles;
	JList listTrabajosSeleccionados;
	
	DefaultListModel modelJuecesDisp;
	DefaultListModel modelJuecesSelect;
	DefaultListModel modelJobDisp;
	DefaultListModel modelJobSelect;
	
	private ArrayList<Jurado> jueces = new ArrayList<>();
	private ArrayList<Trabajo> trabajos = new ArrayList<>();
	
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
			Evento miEvento = null;
			Comision aModificar = null;
			RegComision dialog = new RegComision(miEvento, false, aModificar);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegComision(Evento miEvento, boolean modificar, Comision aModificar) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegComision.class.getResource("/imagen/RegCom.png")));
		if(!modificar)
			setTitle("Formando una comisi\u00F3n");
		else
		{
			setTitle("Modificando la comisión: " + aModificar.getId());
		}
			
		setBounds(100, 100, 559, 735);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(204, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(204, 255, 255));
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
			
			cbxArea = new JComboBox();
			if(modificar)
			{
				cbxArea.setSelectedItem(aModificar.getArea());
			}
			modelJuecesSelect = new DefaultListModel();
			modelJobSelect = new DefaultListModel();
			
			cbxArea.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					//Mostrando los jueces disponibles para el jurado
					
					modelJuecesDisp = new DefaultListModel();
				
					for(Persona juez : Empresa.getInstance().getPersonasRegistradas())
					{
						if(juez instanceof Jurado)
						{
							if(cbxArea.getSelectedItem().toString().equalsIgnoreCase(((Jurado) juez).getAreaEstudio()))
							{
								modelJuecesDisp.addElement(juez.getNombre());
							}
						}
					}
					listJuecesDisponibles.setModel(modelJuecesDisp);
					
					
					//Mostrando los trabajos disponibles para la comision de los participantes ya registrados en el evento
					
					modelJobDisp = new DefaultListModel();
					
					for(Participante parti : miEvento.getParticipantes())
					{
						for(Trabajo job : parti.getMisTrabajos())
						{
							if(job.getArea().equalsIgnoreCase(cbxArea.getSelectedItem().toString()))
							{
								modelJobDisp.addElement(job.getTema());
							}
						}
						
					}
					listTrabajosDisponibles.setModel(modelJobDisp);
					
					jueces.removeAll(jueces);
					trabajos.removeAll(trabajos);
					modelJuecesSelect = new DefaultListModel();
					modelJobSelect = new DefaultListModel();
					listJuecesSeleccionados.setModel(modelJuecesSelect);
					listTrabajosSeleccionados.setModel(modelJobSelect);
//					listJuecesSeleccionados.setModel(new DefaultListModel());
//					listTrabajosSeleccionados.setModel(new DefaultListModel());
					
				}
			});
			cbxArea.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Matem\u00E1ticas", "Qu\u00EDmica", "Biolog\u00EDa", "Historia", "F\u00EDsica", "Ingenier\u00EDa"}));
			cbxArea.setBounds(156, 73, 166, 24);
			panel.add(cbxArea);
			
			txtID = new JTextField();
			txtID.setEditable(false);
			txtID.setBounds(156, 27, 166, 24);
			if(!modificar)
				txtID.setText("COM"+Empresa.getInstance().getGenIDComision());
			else
				txtID.setText(aModificar.getId());
			panel.add(txtID);
			txtID.setColumns(10);
			
			JLabel lblFechaDeCreacin = new JLabel("Fecha de creaci\u00F3n:");
			lblFechaDeCreacin.setBounds(12, 122, 166, 16);
			panel.add(lblFechaDeCreacin);
			
			txtFechaCreacion = new JTextField();
			txtFechaCreacion.setEditable(false);
			txtFechaCreacion.setColumns(10);
			txtFechaCreacion.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
			txtFechaCreacion.setBounds(156, 118, 166, 24);
			
			panel.add(txtFechaCreacion);
			
			JPanel panelJurado = new JPanel();
			panelJurado.setBackground(new Color(204, 255, 255));
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
			
			modelJuecesSelect = new DefaultListModel();
			
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
						if(jueces.size() < 4)
						{
							//agregar valor a la lista derecha
							int value = listJuecesDisponibles.getSelectedIndex();
							modelJuecesSelect.addElement(string);
							listJuecesSeleccionados.setModel(modelJuecesSelect);
			
							Jurado buscando = Empresa.getInstance().buscarJuezByName(string);
							//System.out.println("Print desde boton RightJueces:" + buscando.getNombre());
							jueces.add(buscando);
							
							cbxModel = new DefaultComboBoxModel(); 
							for(Jurado juez : jueces)
							{
								cbxModel.addElement(juez.getNombre());
							}
							
							cbxPresidente.setModel(cbxModel);
							
							//removiendo valor de la lista de la izquierda
							if(modelJuecesDisp.getSize() != 0)
							{
								modelJuecesDisp.removeElementAt(value);
							}
							
							listJuecesDisponibles.setModel(modelJuecesDisp);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Solo se permiten 4 jueces", "Notificación", JOptionPane.WARNING_MESSAGE);
						}

					}
				}
			});
			btnRightJurado.setBounds(218, 78, 71, 24);
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
						
						modelJuecesDisp.addElement(string);
						listJuecesDisponibles.setModel(modelJuecesDisp);
						
						Jurado buscando = Empresa.getInstance().buscarJuezByName(string);
						//System.out.println("Print desde boton LeftJueces:" + buscando.getNombre());
						jueces.remove(buscando);
						
						cbxModel = new DefaultComboBoxModel(); 
						for(Jurado juez : jueces)
						{
							cbxModel.addElement(juez.getNombre());
						}
						cbxPresidente.setModel(cbxModel);

						
						//removiendo valor de la lista de la derecha
						if(modelJuecesSelect.getSize() != 0)
						{
							modelJuecesSelect.removeElementAt(value);
						}
						
						listJuecesSeleccionados.setModel(modelJuecesSelect);
					}
				}
			});
			btnLeftJurado.setBounds(218, 151, 71, 24);
			panelJurado.add(btnLeftJurado);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(306, 61, 184, 133);
			panelJurado.add(scrollPane_1);
			
			listJuecesSeleccionados = new JList();
			scrollPane_1.setViewportView(listJuecesSeleccionados);
			
			JLabel lblPresidente = new JLabel("Presidente:");
			lblPresidente.setBounds(17, 213, 94, 16);
			panelJurado.add(lblPresidente);
			
			cbxPresidente = new JComboBox();
			cbxPresidente.setBounds(116, 209, 374, 24);
			panelJurado.add(cbxPresidente);
			
			
			JLabel lblJuecesSeleccionados = new JLabel("Jueces seleccionados:");
			lblJuecesSeleccionados.setBounds(306, 26, 184, 16);
			panelJurado.add(lblJuecesSeleccionados);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBackground(new Color(204, 255, 255));
			panel_1.setLayout(null);
			panel_1.setBorder(new TitledBorder(null, "Selección de trabajos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(12, 431, 508, 211);
			panel.add(panel_1);
			
			JLabel lblTrabajosDelrea = new JLabel("Trabajos del \u00E1rea:");
			lblTrabajosDelrea.setBounds(17, 26, 152, 23);
			panel_1.add(lblTrabajosDelrea);
			
			modelJobSelect = new DefaultListModel();
			
			JButton btnRightTrabajos = new JButton(">");
			btnRightTrabajos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					//MOVIENDO IZQUIERDA A DERECHA
					String string = (String) listTrabajosDisponibles.getSelectedValue();
					
					if(listTrabajosDisponibles.getSelectedIndex() == -1)
					{
						JOptionPane.showMessageDialog(null, "Seleccione algún trabajo", "Notificación", JOptionPane.WARNING_MESSAGE);
					}
					else
					{
						//agregar valor a la lista derecha
						int value = listTrabajosDisponibles.getSelectedIndex();
						modelJobSelect.addElement(string);
						listTrabajosSeleccionados.setModel(modelJobSelect);
						
						
						Trabajo buscando = miEvento.buscandoTrabajoEntreMisParticipantesByName(string);
						//System.out.println("Print desde boton rightTrabajos:" + buscando.getTema());
						trabajos.add(buscando);
						
						//removiendo valor de la lista de la izquierda
						if(modelJobDisp.getSize() != 0)
						{
							modelJobDisp.removeElementAt(value);
						}
						
						listTrabajosDisponibles.setModel(modelJobDisp);
					}
				}
			});
			btnRightTrabajos.setBounds(218, 74, 71, 24);
			panel_1.add(btnRightTrabajos);
			
			JButton btnLeftTrabajos = new JButton("<");
			btnLeftTrabajos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					//MOVIENDO DE DERECHA A IZQUIERDA
					String string = (String) listTrabajosSeleccionados.getSelectedValue();
					
					if(listTrabajosSeleccionados.getSelectedIndex() == -1)
					{
						JOptionPane.showMessageDialog(null, "Seleccione algún trabajo", "Notificación", JOptionPane.WARNING_MESSAGE);
					}
					else
					{
						//agregar valor a la lista izquierda
						
						int value = listTrabajosSeleccionados.getSelectedIndex();
						
						modelJobDisp.addElement(string);
						listTrabajosDisponibles.setModel(modelJobDisp);
						
						Trabajo buscando = miEvento.buscandoTrabajoEntreMisParticipantesByName(string);
						System.out.println("Print desde boton LeftTrabajos:" + buscando.getTema());
						trabajos.remove(buscando);
						
						//removiendo valor de la lista de la derecha
						if(modelJobSelect.getSize() != 0)
						{
							modelJobSelect.removeElementAt(value);
						}
						
						listTrabajosSeleccionados.setModel(modelJobSelect);
					}
					
				}
			});
			btnLeftTrabajos.setBounds(218, 151, 71, 24);
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
			
			JLabel label_1 = new JLabel("");
			label_1.setBounds(332, 11, 188, 160);
			label_1.setIcon(new ImageIcon(Login.class.getResource("/imagen/RegCom.png")));
			panel.add(label_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			buttonPane.setBackground(new Color(224, 255, 255));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistrar = new JButton("Registrar");
				btnRegistrar.setSize(77, 24);
				if(modificar)
				{
					btnRegistrar.setText("Modificar");
				}
					
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if(!modificar)
						{
							if(jueces.size() > 0 && trabajos.size() > 0 && cbxArea.getSelectedIndex() > 0)
							{
								Jurado presidente = null;
								
								for(Jurado juez : jueces)
								{
									if((juez.getNombre()).equalsIgnoreCase(cbxPresidente.getSelectedItem().toString()))
									{
										presidente = juez;
										juez.setPresidente(true);
									}
								}
								
//								System.out.println("BEFORE INSERTING Reg Comision - Trabajos:");
//								for(Trabajo job : trabajos)
//								{
//									System.out.println(job.getTema());
//									//System.out.println(job.getDuenio().getNombre());
//									//System.out.println(job.getArea());
//									//System.out.println(job.getDescripcion());
//								}
//								
//								System.out.println("BEFORE INSERTING Reg Comision - Jueces:");
//								for(Jurado juez : jueces)
//								{
//									//System.out.println(juez.getCedula());
//									System.out.println(juez.getNombre());
//									//System.out.println(juez.getSexo());
//									//System.out.println(juez.getAreaEstudio());
//								}
								//System.out.println("BEFORE INSERTING Reg Comision - Jueces/Trabajos: " + jueces.size() + "/" + trabajos.size());
								Comision nuevaComision = new Comision(txtID.getText(), presidente, cbxArea.getSelectedItem().toString());
								nuevaComision.setTrabajosParticipantes(trabajos);
								nuevaComision.setMiJurado(jueces);
								
								//System.out.println("AFTER INSERTING Reg Comision - Jueces/Trabajos: " + nuevaComision.getMiJurado().size() + "/" + nuevaComision.getTrabajosParticipantes().size());
//								System.out.println("AFTER INSERTING Reg Comision - Trabajos: " + nuevaComision.getTrabajosParticipantes().size());
//								for(Trabajo job : nuevaComision.getTrabajosParticipantes())
//								{
//									System.out.println(job.getTema());
//									//System.out.println(job.getDuenio().getNombre());
//									//System.out.println(job.getArea());
//									//System.out.println(job.getDescripcion());
//								}
//								
//								System.out.println("AFTER INSERTING Reg Comision - Jueces: " + nuevaComision.getMiJurado().size());
//								for(Jurado juez : nuevaComision.getMiJurado())
//								{
//									//System.out.println(juez.getCedula());
//									System.out.println(juez.getNombre());
//									//System.out.println(juez.getSexo());
//									//System.out.println(juez.getAreaEstudio());
//								}
								
								miEvento.insertarComision(nuevaComision);
								//new ListaEventos().setVisible(true);
								//new ListaTrabajosComi(miEvento.getMisComisiones().get(0)).setVisible(true);
								//new ListarMiembrosComi(miEvento.getMisComisiones().get(0)).setVisible(true);
								JOptionPane.showMessageDialog(null, "Comisión Registrada Satisfactoriamente", "Notificación", JOptionPane.INFORMATION_MESSAGE);
								dispose();
								//clean();
								
							}
							else
							{
								if(jueces.size() < 1)
								{
									JOptionPane.showMessageDialog(null, "Seleccione más jueces", "Error de registro", JOptionPane.WARNING_MESSAGE);
								}
								else if(trabajos.size() <= 0)
								{
									JOptionPane.showMessageDialog(null, "Seleccione más trabajos", "Error de registro", JOptionPane.WARNING_MESSAGE);
								}
								else
									JOptionPane.showMessageDialog(null, "Operación Errónea. Revise los campos nuevamente.", "Error de registro", JOptionPane.WARNING_MESSAGE);
							}
						}
						else
						{
							if(aModificar != null)
							{
								if(jueces.size() < 1)
								{
									JOptionPane.showMessageDialog(null, "Seleccione más jueces", "Error de registro", JOptionPane.WARNING_MESSAGE);
								}
								else if(trabajos.size() < 0)
								{
									JOptionPane.showMessageDialog(null, "Seleccione más trabajos", "Error de registro", JOptionPane.WARNING_MESSAGE);
								}
								else
								{
									
									aModificar.setMiJurado(jueces);
									Jurado presidente = null;
									for(Jurado juez : jueces)
									{
										if((juez.getNombre()).equalsIgnoreCase(cbxPresidente.getSelectedItem().toString()))
										{
											presidente = juez;
											juez.setPresidente(true);
										}
									}
									aModificar.setPresidente(presidente);
									aModificar.setTrabajosParticipantes(trabajos);
									aModificar.setArea(cbxArea.getSelectedItem().toString());
									
									//System.out.println("Reg Comision MODIFICANDO: PostCreation: Trabajos: " + aModificar.getTrabajosParticipantes().get(0).getTema());
									//System.out.println("Reg Comision MODIFICANDO: jueces: " + aModificar.getMiJurado().get(0).getNombre());
									
									JOptionPane.showMessageDialog(null, "Modificación realizada", "Notificación", JOptionPane.INFORMATION_MESSAGE);
									dispose();
									ListaComisiones.loadComision(miEvento.getMisComisiones());
								}
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Hubo un error al tratar de modificar la comisión", "Notificación", JOptionPane.INFORMATION_MESSAGE);
							}
						}
						
					}
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton btnCancel = new JButton("Cancelar");
				btnCancel.setSize(75, 24);
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
	}
	
	private void clean()
	{	
		txtID.setText("COM"+Empresa.getInstance().getGenIDComision());
		cbxArea.setSelectedIndex(0);
		jueces = new ArrayList<>();
		trabajos = new ArrayList<>();
		modelJuecesDisp = new DefaultListModel();
		modelJuecesSelect = new DefaultListModel();
		modelJobDisp = new DefaultListModel();
		modelJobSelect = new DefaultListModel();
		cbxModel = new DefaultComboBoxModel();
		listJuecesDisponibles.setModel(modelJobDisp);
		listJuecesSeleccionados.setModel(modelJuecesSelect);
		listTrabajosDisponibles.setModel(modelJobDisp);
		listTrabajosSeleccionados.setModel(modelJobSelect);
		cbxPresidente.setModel(cbxModel);
	}
}
