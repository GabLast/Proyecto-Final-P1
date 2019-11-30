package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import com.toedter.calendar.JCalendar;

import logica.Empresa;
import logica.Evento;
import logica.Jurado;
import logica.Participante;
import logica.Persona;
import logica.Recurso;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import java.awt.Color;

public class CreandoEvento extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JButton btnCrear;
	private JTextField txtID;
	private JTextField txtNombre;
	private JComboBox cbxRecurso;
	private JComboBox cbxTipo;
	JSpinner spnCantidad;
	JCalendar calendar;
	JList listRecursosElegidos;
	JList listPartiElegidos;
	JList listParticipantes;
	
	DefaultComboBoxModel cbxModel;
	DefaultListModel modelRecElegidos;
	DefaultListModel modelPartis;
	DefaultListModel modelPartiElegidos;
	
	JButton btnDerechaParti;
	JButton btnIzquierdaParti;
	JButton btnAgregarRec;
	JButton btnRemover;
	
	ArrayList<Recurso> recursos = new ArrayList<>();
	ArrayList<Participante> participantes = new ArrayList<>();
	ArrayList<Recurso> recBackUp = new ArrayList<>();
	int valorRecurso;
	String string;
	private JTextField txtCant;
	private JTextField txtLugar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Evento modi = null;
			CreandoEvento dialog = new CreandoEvento(modi, false);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreandoEvento(Evento modiEvento, boolean modificar) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CreandoEvento.class.getResource("/imagen/titleadd.png")));
		if(!modificar)
			setTitle("Organizaci\u00F3n de un evento");
		else
		{
			setTitle("Modificando el evento: " + modiEvento.getNombre());
			recBackUp = modiEvento.getRecursosUsados();
			Empresa.getInstance().returnAllResourcesBeforeModifyingEvent(modiEvento);
		}
		setBounds(100, 100, 1130, 693);
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
				panel_1.setBounds(17, 21, 570, 316);
				panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos b\u00E1sicos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel.add(panel_1);
				panel_1.setLayout(null);
				{
					JLabel lblNewLabel = new JLabel("Nombre:");
					lblNewLabel.setBounds(20, 59, 74, 23);
					panel_1.add(lblNewLabel);
				}
				{
					JLabel lblId = new JLabel("ID:");
					lblId.setBounds(20, 19, 82, 23);
					panel_1.add(lblId);
				}
				{
					JLabel lblFecha = new JLabel("Fecha:");
					lblFecha.setBounds(20, 99, 82, 23);
					panel_1.add(lblFecha);
				}
				
				calendar = new JCalendar();
				if(modificar)
					calendar.setDate(modiEvento.getFecha());
				calendar.setBounds(20, 137, 540, 162);
				panel_1.add(calendar);
				
				txtID = new JTextField();
				txtID.setEditable(false);
				if(modificar)
				{
					txtID.setText(modiEvento.getId());
				}else
					txtID.setText("EV"+Empresa.getInstance().getGenIDEvento());
				txtID.setBounds(80, 16, 203, 29);
				panel_1.add(txtID);
				txtID.setColumns(10);
				
				txtNombre = new JTextField();
				if(modificar)
				{
					txtNombre.setText(modiEvento.getNombre());
				}
				txtNombre.setColumns(10);
				txtNombre.setBounds(80, 56, 203, 29);
				panel_1.add(txtNombre);
				{
					JLabel lblTipo = new JLabel("Tipo:");
					lblTipo.setBounds(305, 19, 74, 23);
					panel_1.add(lblTipo);
				}
				{
					cbxTipo = new JComboBox();	
					cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Panel", "Ponencia", "Conferencia", "Jornada", "Mesa Redonda"}));
					cbxTipo.setBounds(357, 16, 203, 29);
					if(modificar)
					{
						cbxTipo.setSelectedItem(modiEvento.getTipo());
					}
					panel_1.add(cbxTipo);
				}
				
				JLabel lblLugar = new JLabel("Lugar:");
				lblLugar.setBounds(305, 59, 56, 23);
				panel_1.add(lblLugar);
				
				txtLugar = new JTextField();
				if(modificar)
					txtLugar.setText(modiEvento.getLugar());
				txtLugar.setColumns(10);
				txtLugar.setBounds(357, 56, 203, 29);
				panel_1.add(txtLugar);
			}
			{
				JPanel panelParticipantes = new JPanel();
				panelParticipantes.setBorder(new TitledBorder(null, "Registro de Participantes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panelParticipantes.setBounds(17, 356, 570, 247);
				panel.add(panelParticipantes);
				panelParticipantes.setLayout(null);
				{
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBounds(17, 61, 235, 167);
					panelParticipantes.add(scrollPane);
					{
						listParticipantes = new JList();
						scrollPane.setViewportView(listParticipantes);
						
						modelPartis = new DefaultListModel();
						
						//Mostrando en jlist
						for(Persona person : Empresa.getInstance().getPersonasRegistradas())
						{
							if(person instanceof Participante)
							{
								modelPartis.addElement(person.getNombre());
							}
						}
						listParticipantes.setModel(modelPartis);
					}
				}
				{
					JLabel lblPosiblesParticipantes = new JLabel("Posibles participantes:");
					lblPosiblesParticipantes.setBounds(17, 31, 219, 23);
					panelParticipantes.add(lblPosiblesParticipantes);
				}
				{
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBounds(319, 61, 235, 167);
					panelParticipantes.add(scrollPane);
					{
						listPartiElegidos = new JList();
						scrollPane.setViewportView(listPartiElegidos);
					}
				}
				{
					modelPartiElegidos = new DefaultListModel();
					btnDerechaParti = new JButton(">");
					btnDerechaParti.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							//MOVIENDO IZQUIERDA A DERECHA
							String string = (String) listParticipantes.getSelectedValue();
							
							if(listParticipantes.getSelectedIndex() == -1)
							{
								JOptionPane.showMessageDialog(null, "Seleccione algún participante", "Notificación", JOptionPane.WARNING_MESSAGE);
							}
							else
							{
								//agregar valor a la lista derecha
								int value = listParticipantes.getSelectedIndex();
								modelPartiElegidos.addElement(string);
								listPartiElegidos.setModel(modelPartiElegidos);
									
								Participante buscando = Empresa.getInstance().buscarParticipanteByName(listParticipantes.getSelectedValue().toString());
								participantes.add(buscando);
									
								//removiendo valor de la lista de la izquierda
								if(modelPartis.getSize() != 0)
								{
									modelPartis.removeElementAt(value);
								}
									
								listParticipantes.setModel(modelPartis);
							}
						}
					});
					btnDerechaParti.setBounds(262, 94, 47, 31);
					panelParticipantes.add(btnDerechaParti);
				}
				{
					btnIzquierdaParti = new JButton("<");
					btnIzquierdaParti.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							String string = (String) listPartiElegidos.getSelectedValue();
							
							if(listPartiElegidos.getSelectedIndex() == -1)
							{
								JOptionPane.showMessageDialog(null, "Seleccione algún participante", "Notificación", JOptionPane.WARNING_MESSAGE);
							}
							else
							{
								//agregar valor a la lista izquierda
								
								int value = listPartiElegidos.getSelectedIndex();
								
								modelPartis.addElement(string);
								listParticipantes.setModel(modelPartis);
								

								Participante buscando = Empresa.getInstance().buscarParticipanteByName(string);
								participantes.remove(buscando);

								//removiendo valor de la lista de la derecha
								if(modelPartiElegidos.getSize() != 0)
								{
									modelPartiElegidos.removeElementAt(value);
								}
								
								listPartiElegidos.setModel(modelPartiElegidos);
							}
						}
					});
					btnIzquierdaParti.setBounds(262, 163, 47, 31);
					panelParticipantes.add(btnIzquierdaParti);
				}
				{
					JLabel lblParticipantesSeleccionados = new JLabel("Participantes seleccionados:");
					lblParticipantesSeleccionados.setBounds(319, 31, 175, 23);
					panelParticipantes.add(lblParticipantesSeleccionados);
				}
			}
			{
				JPanel panelRecursos = new JPanel();
				panelRecursos.setBounds(597, 21, 490, 582);
				panelRecursos.setBorder(new TitledBorder(null, "Registro de Recursos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel.add(panelRecursos);
				panelRecursos.setLayout(null);
				{
					JLabel lblRecursosDisponibles = new JLabel("Recursos disponibles:");
					lblRecursosDisponibles.setBounds(14, 376, 219, 23);
					panelRecursos.add(lblRecursosDisponibles);
				}
				{
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBounds(226, 406, 235, 130);
					panelRecursos.add(scrollPane);
					{
						listRecursosElegidos = new JList();
						modelRecElegidos = new DefaultListModel();
						scrollPane.setViewportView(listRecursosElegidos);
					}
				}
				{
					JLabel lblRecursosAUtilizar = new JLabel("Recursos a utilizar:");
					lblRecursosAUtilizar.setBounds(226, 376, 219, 23);
					panelRecursos.add(lblRecursosAUtilizar);
				}
				{
					btnAgregarRec = new JButton("Agregar");						
					btnAgregarRec.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) 
						{
							string = cbxRecurso.getSelectedItem().toString();
							Recurso buscando = Empresa.getInstance().searchRecursoByTipo(string);

							if(cbxRecurso.getSelectedIndex() < 0)
							{
								JOptionPane.showMessageDialog(null, "Seleccione algún recurso", "Notificación", JOptionPane.WARNING_MESSAGE);
							}
							else if( buscando.getCantidad() < (Integer) spnCantidad.getValue())
							{
								JOptionPane.showMessageDialog(null, "No hay suficientes ["  + buscando.getTipo()+ "] disponibles", "Notificación", JOptionPane.WARNING_MESSAGE);
							}
							else if(buscandoEstaEnLaLista(buscando))
							{
								JOptionPane.showMessageDialog(null, "Remueva ["+ buscando.getTipo()+ "] de la lista y añádalo nuevamente para alterar la cantidad a usar", "Notificación", JOptionPane.WARNING_MESSAGE);
							}
							else
							{
								//agregar valor a la lista derecha
								
								valorRecurso = (Integer) spnCantidad.getValue();
								buscando.setCantUsadaEvento(valorRecurso);
								modelRecElegidos.addElement(string);
								listRecursosElegidos.setModel(modelRecElegidos);
								
								buscando.verificarDisponibilidad(valorRecurso);
								recursos.add(buscando);
								
								cbxModel = new DefaultComboBoxModel(); 
								for(Recurso recurso : Empresa.getInstance().getRecursos())
								{
									cbxModel.addElement(recurso.getTipo());
								}
								cbxRecurso.setModel(cbxModel);
								
								spnCantidad.setValue(0);
								txtCant.setText("");
								
									
							}
						}

						
					});
					btnAgregarRec.setBounds(123, 494, 81, 31);
					panelRecursos.add(btnAgregarRec);
				}
				{
					btnRemover = new JButton("Remover recurso seleccionado");
					btnRemover.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							String string = (String) listRecursosElegidos.getSelectedValue();
							Recurso buscando = Empresa.getInstance().searchRecursoByTipo(string);
							System.out.println(buscando.getCantidad());
							System.out.println(buscando.getCantUsadaEvento());
						
							if(listRecursosElegidos.getSelectedIndex() == -1)
							{
								JOptionPane.showMessageDialog(null, "Seleccione algún recurso", "Notificación", JOptionPane.WARNING_MESSAGE);
							}
							else
							{
								int value = listRecursosElegidos.getSelectedIndex();
								buscando.devolverRecursoTomado(buscando.getCantUsadaEvento());
								
								cbxModel = new DefaultComboBoxModel(); 
								for(Recurso recurso : Empresa.getInstance().getRecursos())
								{
									cbxModel.addElement(recurso.getTipo());
								}
								cbxRecurso.setModel(cbxModel);
								
								recursos.remove(buscando);
								
								//removiendo valor de la lista
								if(modelRecElegidos.getSize() != 0)
								{
									modelRecElegidos.removeElementAt(value);
								}
								
								listRecursosElegidos.setModel(modelRecElegidos);
								
								spnCantidad.setValue(0);
								txtCant.setText("");
							}
						}
					});
					btnRemover.setBounds(226, 540, 235, 31);
					panelRecursos.add(btnRemover);
				}
				{
					cbxRecurso = new JComboBox();
					cbxRecurso.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							string = cbxRecurso.getSelectedItem().toString();
							Recurso aux = Empresa.getInstance().searchRecursoByTipo(string);
							txtCant.setText("x"+ aux.getCantidad());
						}
					});
					cbxRecurso.setBounds(10, 410, 134, 29);
					cbxModel = new DefaultComboBoxModel(); 
					for(Recurso recurso : Empresa.getInstance().getRecursos())
					{
						cbxModel.addElement(recurso.getTipo());
					}
					cbxRecurso.setModel(cbxModel);
					panelRecursos.add(cbxRecurso);
				}
				
				spnCantidad = new JSpinner();
				spnCantidad.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
				spnCantidad.setBounds(14, 494, 99, 30);
				panelRecursos.add(spnCantidad);
				{
					txtCant = new JTextField();
					txtCant.setEditable(false);
					txtCant.setColumns(10);
					txtCant.setBounds(154, 410, 50, 29);
					panelRecursos.add(txtCant);
				}
				
				JLabel lblNewLabel_1 = new JLabel("");
				lblNewLabel_1.setBounds(86, 26, 291, 339);
				lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/imagen/eventplan.png")));
				panelRecursos.add(lblNewLabel_1);
				{
					JLabel lblCantidadAUsar = new JLabel("Cantidad a usar:");
					lblCantidadAUsar.setBounds(14, 462, 170, 23);
					panelRecursos.add(lblCantidadAUsar);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnCrear = new JButton("Crear");
				if(modificar)
				{
					btnCrear.setText("Modificar");
				}
				btnCrear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if(!modificar)
						{
							if(txtNombre.getText().isEmpty() || cbxTipo.getSelectedIndex() < 1 || txtLugar.getText().isEmpty() || 
									participantes.size() < 1)
							{
								JOptionPane.showMessageDialog(null, "Asegúrese de llenar las casillas, elegir el tipo de evento y sus participantes", "Error", JOptionPane.WARNING_MESSAGE);
							}
							else
							{
								Evento nuevoEvento = new Evento(recursos, participantes, txtNombre.getText(), txtID.getText(), 
										cbxTipo.getSelectedItem().toString(), txtLugar.getText(), calendar.getDate());
								
								nuevoEvento.verificarFin();
								Empresa.getInstance().insertarEvento(nuevoEvento);
								JOptionPane.showMessageDialog(null, "Evento Registrado", "Notificación", JOptionPane.INFORMATION_MESSAGE);
								dispose();
								RegComision comi = new RegComision(nuevoEvento, false, null);
								comi.setModal(true);
								comi.setVisible(true);
							}
						}
						else
						{
							if(modiEvento != null)
							{
								if(txtNombre.getText().isEmpty() || cbxTipo.getSelectedIndex() < 1 || txtLugar.getText().isEmpty() || 
										participantes.size() < 1)
								{
									JOptionPane.showMessageDialog(null, "Asegúrese de llenar las casillas, elegir el tipo de evento y sus participantes", "Error", JOptionPane.WARNING_MESSAGE);
								}
								else
								{
									modiEvento.setFecha(calendar.getDate());
									modiEvento.setLugar(txtLugar.getText());
									modiEvento.setNombre(txtNombre.getText());
									modiEvento.setParticipantes(participantes);
									modiEvento.setRecursosUsados(recursos);
									modiEvento.setTipo(cbxTipo.getSelectedItem().toString());
									modiEvento.verificarFin();
									JOptionPane.showMessageDialog(null, "Modificación realizada de manera satisfactoria", "Notificación", JOptionPane.INFORMATION_MESSAGE);
									dispose();
									ListaEventos.loadEventos();
								}
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Hubo un error al tratar de modificar el evento", "Notificación", JOptionPane.WARNING_MESSAGE);
							}
						}
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
						if(modificar)
						{
							Empresa.getInstance().returnAllResourcesBeforeModifyingEventCANCEL(modiEvento);
						}
						
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
		txtID.setText("EV"+Empresa.getInstance().getGenIDEvento());
		txtNombre.setText("");
		cbxTipo.setSelectedIndex(-1);
		cbxRecurso.setSelectedIndex(-1);
		spnCantidad.setValue(0);
		txtLugar.setText("");
		
		modelPartiElegidos = new DefaultListModel();
		modelPartis = new DefaultListModel();
		modelRecElegidos = new DefaultListModel();
		
		listParticipantes.setModel(modelPartis);
		listPartiElegidos.setModel(modelPartiElegidos);
		listRecursosElegidos.setModel(modelRecElegidos);
		
	}
	
	private boolean buscandoEstaEnLaLista(Recurso buscando) 
	{
		boolean yaEsta = false;
		
		for(Recurso rec : recursos)
		{
			if(rec.getTipo().equalsIgnoreCase(buscando.getTipo()))
			{
				yaEsta = true;
				return yaEsta;
			}
		}
		
		return yaEsta;
	}
}
