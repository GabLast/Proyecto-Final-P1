package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logica.Comision;
import logica.Empresa;
import logica.Evento;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class ListaEventos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object[] row;
	private static DefaultTableModel model;
	private Dimension dim;
	String id = "";
	private JButton btnCrear;
	private JButton btnGestionarComisiones;
	JButton btnRecursosUtilizados;
	JButton btnModifcar;
	private JButton btnListarParticipantes;
	private JButton btnEliminar;
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
			ListaEventos dialog = new ListaEventos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListaEventos() {
		setTitle("Lista de Eventos");
		setBounds(100, 100, 648, 407);
		dim = super.getToolkit().getScreenSize();
		dim.width *= .70;
		dim.height *= .80;
		super.setSize(dim.width, dim.height);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(204, 255, 255));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListaEventos.class.getResource("/imagen/titleagregar.png")));
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(204, 255, 255));
			panel.setBorder(new TitledBorder(null, "Listado de eventos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					model = new DefaultTableModel() {
						@Override
					    public boolean isCellEditable(int row, int column) {
					       //all cells false
					       return false;
					    }
					};
					
					
					String[] header = {"ID", "Nombre", "Tipo", "Lugar", "Fecha de la actividad", "Cantidad de participantes","Estado"};
					model.setColumnIdentifiers(header);
					
					table = new JTable();
					
					loadEventos();
					
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if(table.getSelectedRow()>-1){
								int index = table.getSelectedRow();
								id = String.valueOf(table.getValueAt(index, 0));
								btnCrear.setEnabled(true);
								btnGestionarComisiones.setEnabled(true);
								btnModifcar.setEnabled(true);
								btnRecursosUtilizados.setEnabled(true);
								btnListarParticipantes.setEnabled(true);
								btnEliminar.setEnabled(true);
								
							}
						}
					});
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table.setModel(model);
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnCrear = new JButton("Crear comisi\u00F3n");
				btnCrear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(id != "")
						{
							Evento elEvento = Empresa.getInstance().searchEventoByID(id);
							
							RegComision comi = new RegComision(elEvento, false, null);
							comi.setModal(true);
							comi.setVisible(true);
						}
						
					}
				});
				{
					btnModifcar = new JButton("Modificar evento");
					btnModifcar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(id != "")
							{
								Evento elEvento = Empresa.getInstance().searchEventoByID(id);
								
								int option = JOptionPane.showConfirmDialog(null, "Está seguro que desea modificar el evento: " 
										   + elEvento.getNombre(), "Notificación",JOptionPane.WARNING_MESSAGE);
								
								if(option == JOptionPane.OK_OPTION && elEvento != null)
								{
									CreandoEvento window = new CreandoEvento(elEvento, true);
									window.setModal(true);
									window.setVisible(true);
								}
							}
						}
					});
					buttonPane.add(btnModifcar);
					btnModifcar.setEnabled(false);
				}
				{
					btnEliminar = new JButton("Eliminar evento");
					btnEliminar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(id != "")
							{
								Evento elEvento = Empresa.getInstance().searchEventoByID(id);
								
								int option = JOptionPane.showConfirmDialog(null, "Está seguro que desea eliminar el evento: " 
										   + elEvento.getNombre(), "Notificación",JOptionPane.WARNING_MESSAGE);
								
								if(option == JOptionPane.OK_OPTION && elEvento != null)
								{
									
									Empresa.getInstance().deleteEvento(elEvento);
									JOptionPane.showMessageDialog(null, "Evento eliminado satisfactoriamente"
											, "Notificación", JOptionPane.INFORMATION_MESSAGE);
									ListaEventos.loadEventos();
								}
							}
							
						}
					});
					btnEliminar.setEnabled(false);
					buttonPane.add(btnEliminar);
				}
				btnCrear.setActionCommand("OK");
				buttonPane.add(btnCrear);
				btnCrear.setEnabled(false);
				getRootPane().setDefaultButton(btnCrear);
			}
			{
				btnGestionarComisiones = new JButton("Listar comisiones");
				btnGestionarComisiones.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Evento elEvento = Empresa.getInstance().searchEventoByID(id);
						ListaComisiones window = new ListaComisiones(elEvento);
						window.setModal(true);
						window.setVisible(true);
					}
				});
				btnGestionarComisiones.setActionCommand("OK");
				buttonPane.add(btnGestionarComisiones);
				btnGestionarComisiones.setEnabled(false);
			}
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				{
					btnRecursosUtilizados = new JButton("Recursos utilizados");
					btnRecursosUtilizados.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							Evento elEvento = Empresa.getInstance().searchEventoByID(id);
							RecEvento window = new RecEvento(elEvento);
							window.setModal(true);
							window.setVisible(true);
						}
					});
					btnRecursosUtilizados.setEnabled(false);
					btnRecursosUtilizados.setActionCommand("OK");
					buttonPane.add(btnRecursosUtilizados);
				}
				{
					btnListarParticipantes = new JButton("Listar participantes");
					btnListarParticipantes.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Evento elEvento = Empresa.getInstance().searchEventoByID(id);
							ListPartiEvento window = new ListPartiEvento(elEvento);
							window.setModal(true);
							window.setVisible(true);
						}
					});
					btnListarParticipantes.setEnabled(false);
					btnListarParticipantes.setActionCommand("OK");
					buttonPane.add(btnListarParticipantes);
				}
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public static void loadEventos() 
	{
		Empresa.getInstance().insertionSort();
		model.setRowCount(0);
		Empresa miEmpresa = Empresa.getInstance();
		//{"ID", "Nombre", "Tipo", "lugar", "Fecha de la actividad", "Estado"};
		row = new Object[model.getColumnCount()];
		
		for (Evento eve : miEmpresa.getEventos()) 
		{
			row[0] = eve.getId();
			row[1] = eve.getNombre();
			row[2] = eve.getTipo();
			row[3] = eve.getLugar();
			row[4] = new SimpleDateFormat("dd/MM/yyyy").format(eve.getFecha());
			row[5] = eve.getParticipantes().size();
			
			if(eve.isEstado())
			{
				row[6] = "Disponible";
			}
			else
			{
				row[6] = "Finalizado";
			}
			
			model.addRow(row);
		}
		
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getTableHeader().setReorderingAllowed(false);
		
	}
}
