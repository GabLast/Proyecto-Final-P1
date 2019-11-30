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
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Font;

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

	ArrayList<Jurado> jueces = new ArrayList();
	ArrayList<Trabajo> trabajos = new ArrayList();
	//Evento eventoGL = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		//this.eventoGL = miEvento;
		if(!modificar)
			setTitle("Formando una comisi\u00F3n");
		else
		{
			setTitle("Modificando la comision");
		}

		setBounds(100, 100, 571, 740);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(5, 5, 550, 661);
			contentPanel.add(panel);
			panel.setBorder(new TitledBorder(null, "Informaci�n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setLayout(null);
			{
				JLabel lblId = new JLabel("ID:");
				lblId.setFont(new Font("Roboto", Font.PLAIN, 12));
				lblId.setBounds(12, 31, 56, 16);
				panel.add(lblId);
			}
			{
				JLabel lblTipo = new JLabel("\u00C1rea:");
				lblTipo.setFont(new Font("Roboto", Font.PLAIN, 12));
				lblTipo.setBounds(12, 77, 56, 16);
				panel.add(lblTipo);
			}

			cbxArea = new JComboBox();
			cbxArea.setFont(new Font("Roboto", Font.PLAIN, 12));

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
					listJuecesSeleccionados.setModel(new DefaultListModel());
					listTrabajosSeleccionados.setModel(new DefaultListModel());

				}
			});
			cbxArea.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Matem\u00E1ticas", "Qu\u00EDmica", "Biolog\u00EDa", "Historia", "F\u00EDsica", "Ingenier\u00EDa"}));
			cbxArea.setBounds(156, 74, 166, 22);
			panel.add(cbxArea);

			txtID = new JTextField();
			txtID.setFont(new Font("Roboto", Font.PLAIN, 12));
			txtID.setEditable(false);
			txtID.setBounds(156, 28, 166, 22);
			if(!modificar)
				txtID.setText("COM"+Empresa.getInstance().getGenIDComision());
			else
				txtID.setText(aModificar.getId());
			panel.add(txtID);
			txtID.setColumns(10);

			JLabel lblFechaDeCreacin = new JLabel("Fecha de creaci\u00F3n:");
			lblFechaDeCreacin.setFont(new Font("Roboto", Font.PLAIN, 12));
			lblFechaDeCreacin.setBounds(12, 122, 166, 16);
			panel.add(lblFechaDeCreacin);

			txtFechaCreacion = new JTextField();
			txtFechaCreacion.setFont(new Font("Roboto", Font.PLAIN, 12));
			txtFechaCreacion.setEditable(false);
			txtFechaCreacion.setColumns(10);
			txtFechaCreacion.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
			txtFechaCreacion.setBounds(156, 119, 166, 22);

			panel.add(txtFechaCreacion);

			JPanel panelJurado = new JPanel();
			panelJurado.setBorder(new TitledBorder(null, "Selecci�n del jurado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelJurado.setBounds(12, 165, 508, 255);
			panel.add(panelJurado);
			panelJurado.setLayout(null);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(17, 61, 184, 133);
			panelJurado.add(scrollPane);

			listJuecesDisponibles = new JList();
			listJuecesDisponibles.setFont(new Font("Roboto", Font.PLAIN, 12));
			scrollPane.setViewportView(listJuecesDisponibles);

			JLabel label = new JLabel("Jueces disponibles:");
			label.setFont(new Font("Roboto", Font.PLAIN, 12));
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
						JOptionPane.showMessageDialog(null, "Seleccione alg�n juez", "Notificaci�n", JOptionPane.WARNING_MESSAGE);
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
							JOptionPane.showMessageDialog(null, "Solo se permiten 4 jueces", "Notificaci�n", JOptionPane.WARNING_MESSAGE);
						}

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
						JOptionPane.showMessageDialog(null, "Seleccione alg�n juez", "Notificaci�n", JOptionPane.WARNING_MESSAGE);
					}
					else
					{
						//agregar valor a la lista izquierda

						int value = listJuecesSeleccionados.getSelectedIndex();

						modelJuecesDisp.addElement(string);
						listJuecesDisponibles.setModel(modelJuecesDisp);

						Jurado buscando = Empresa.getInstance().buscarJuezByName(string);
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
			btnLeftJurado.setBounds(218, 151, 71, 31);
			panelJurado.add(btnLeftJurado);

			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(306, 61, 184, 133);
			panelJurado.add(scrollPane_1);

			listJuecesSeleccionados = new JList();
			listJuecesSeleccionados.setFont(new Font("Roboto", Font.PLAIN, 12));
			scrollPane_1.setViewportView(listJuecesSeleccionados);

			JLabel lblPresidente = new JLabel("Presidente:");
			lblPresidente.setFont(new Font("Roboto", Font.PLAIN, 12));
			lblPresidente.setBounds(17, 213, 94, 16);
			panelJurado.add(lblPresidente);

			cbxPresidente = new JComboBox();
			cbxPresidente.setFont(new Font("Roboto", Font.PLAIN, 12));
			cbxPresidente.setBounds(116, 210, 374, 22);
			panelJurado.add(cbxPresidente);


			JLabel lblJuecesSeleccionados = new JLabel("Jueces seleccionados:");
			lblJuecesSeleccionados.setFont(new Font("Roboto", Font.PLAIN, 12));
			lblJuecesSeleccionados.setBounds(306, 26, 184, 16);
			panelJurado.add(lblJuecesSeleccionados);

			JPanel panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.setBorder(new TitledBorder(null, "Selecci�n de trabajos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(12, 431, 508, 211);
			panel.add(panel_1);

			JLabel lblTrabajosDelrea = new JLabel("Trabajos del \u00E1rea:");
			lblTrabajosDelrea.setFont(new Font("Roboto", Font.PLAIN, 12));
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
						JOptionPane.showMessageDialog(null, "Seleccione alg�n trabajo", "Notificaci�n", JOptionPane.WARNING_MESSAGE);
					}
					else
					{
						//agregar valor a la lista derecha
						int value = listTrabajosDisponibles.getSelectedIndex();
						modelJobSelect.addElement(string);
						listTrabajosSeleccionados.setModel(modelJobSelect);


						Trabajo buscando = miEvento.buscandoTrabajoEntreMisParticipantesByName(string);
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
			btnRightTrabajos.setBounds(218, 68, 71, 31);
			panel_1.add(btnRightTrabajos);

			JButton btnLeftTrabajos = new JButton("<");
			btnLeftTrabajos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					//MOVIENDO DE DERECHA A IZQUIERDA
					String string = (String) listTrabajosSeleccionados.getSelectedValue();

					if(listTrabajosSeleccionados.getSelectedIndex() == -1)
					{
						JOptionPane.showMessageDialog(null, "Seleccione alg�n trabajo", "Notificaci�n", JOptionPane.WARNING_MESSAGE);
					}
					else
					{
						//agregar valor a la lista izquierda

						int value = listTrabajosSeleccionados.getSelectedIndex();

						modelJobDisp.addElement(string);
						listTrabajosDisponibles.setModel(modelJobDisp);

						Trabajo buscando = miEvento.buscandoTrabajoEntreMisParticipantesByName(string);
						trabajos.add(buscando);

						//removiendo valor de la lista de la derecha
						if(modelJobSelect.getSize() != 0)
						{
							modelJobSelect.removeElementAt(value);
						}

						listTrabajosSeleccionados.setModel(modelJobSelect);
					}

				}
			});
			btnLeftTrabajos.setBounds(218, 151, 71, 31);
			panel_1.add(btnLeftTrabajos);

			JLabel lblTrabajosSeleccionados = new JLabel("Trabajos seleccionados:");
			lblTrabajosSeleccionados.setFont(new Font("Roboto", Font.PLAIN, 12));
			lblTrabajosSeleccionados.setBounds(306, 26, 152, 16);
			panel_1.add(lblTrabajosSeleccionados);

			JScrollPane scrollPane_3 = new JScrollPane();
			scrollPane_3.setBounds(17, 59, 184, 133);
			panel_1.add(scrollPane_3);

			listTrabajosDisponibles = new JList();
			listTrabajosDisponibles.setFont(new Font("Roboto", Font.PLAIN, 12));
			scrollPane_3.setViewportView(listTrabajosDisponibles);

			JScrollPane scrollPane_2 = new JScrollPane();
			scrollPane_2.setBounds(306, 59, 184, 133);
			panel_1.add(scrollPane_2);

			listTrabajosSeleccionados = new JList();
			listTrabajosSeleccionados.setFont(new Font("Roboto", Font.PLAIN, 12));
			scrollPane_2.setViewportView(listTrabajosSeleccionados);

			JLabel label_1 = new JLabel("");
			label_1.setBounds(332, 0, 208, 170);
			label_1.setIcon(new ImageIcon(Login.class.getResource("/imagen/RegCom.png")));
			panel.add(label_1);
		}
		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 662, 555, 33);
		contentPanel.add(buttonPane);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setFont(new Font("Roboto", Font.PLAIN, 12));

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
							}
						}
						System.out.println("Trabajos: " + trabajos.size()
						+"\nJueces: " + jueces.size());
						Comision nuevaComision = new Comision(txtID.getText(),jueces, presidente, trabajos, cbxArea.getSelectedItem().toString());
						nuevaComision.setTrabajosParticipantes(trabajos);
						miEvento.insertarComision(nuevaComision);
						clean();
						JOptionPane.showMessageDialog(null, "Comisi�n Registrada Satisfactoriamente", "Notificaci�n", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						if(jueces.size() < 1)
						{
							JOptionPane.showMessageDialog(null, "Seleccione m�s jueces", "Error de registro", JOptionPane.WARNING_MESSAGE);
						}
						else if(trabajos.size() < 0)
						{
							JOptionPane.showMessageDialog(null, "Seleccione m�s trabajos", "Error de registro", JOptionPane.WARNING_MESSAGE);
						}
						else
							JOptionPane.showMessageDialog(null, "Operaci�n Err�nea. Revise los campos nuevamente.", "Error de registro", JOptionPane.WARNING_MESSAGE);
					}
				}
				else
				{
					if(aModificar != null)
					{
						if(jueces.size() < 1)
						{
							JOptionPane.showMessageDialog(null, "Seleccione m�s jueces", "Error de registro", JOptionPane.WARNING_MESSAGE);
						}
						else if(trabajos.size() < 0)
						{
							JOptionPane.showMessageDialog(null, "Seleccione m�s trabajos", "Error de registro", JOptionPane.WARNING_MESSAGE);
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
								}
							}
							aModificar.setPresidente(presidente);
							aModificar.setTrabajosParticipantes(trabajos);
							aModificar.setArea(cbxArea.getSelectedItem().toString());
							JOptionPane.showMessageDialog(null, "Modificaci�n realizada", "Notificaci�n", JOptionPane.INFORMATION_MESSAGE);
							dispose();
							ListaComisiones.loadComision(miEvento.getMisComisiones());
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Hubo un error al tratar de modificar la comisi�n", "Notificaci�n", JOptionPane.WARNING_MESSAGE);
					}
				}

			}
		});
		btnRegistrar.setActionCommand("OK");
		buttonPane.add(btnRegistrar);
		getRootPane().setDefaultButton(btnRegistrar);
		{
			JButton btnCancel = new JButton("Cancelar");
			btnCancel.setFont(new Font("Roboto", Font.PLAIN, 12));
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnCancel.setActionCommand("Cancel");
			buttonPane.add(btnCancel);
		}
		{
			{
				if(modificar)
				{
					btnRegistrar.setText("Modificar");
				}
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
