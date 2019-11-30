package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import logica.Empresa;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

public class Principal extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//UIDefaults uiDefaults = UIManager.getDefaults();
		//UIManager.put("activeCaption", new javax.swing.plaf.ColorUIResource(Color.white));
		//UIManager.put("PopupMenu.border", BorderFactory.createLineBorder(Color.red, 4));
		//JFrame.setDefaultLookAndFeelDecorated(true);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//UIManager.put("PopupMenu.border", BorderFactory.createLineBorder(Color.black, 1));
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
				} catch (Throwable e) {
					e.printStackTrace();
				}
				try {

					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setForeground(Color.LIGHT_GRAY);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				FileOutputStream EmpresaOut;
				ObjectOutputStream EmpresaWrite;
				try {
					EmpresaOut = new  FileOutputStream("empresa.dat");
					EmpresaWrite = new ObjectOutputStream(EmpresaOut);
					EmpresaWrite.writeObject(Empresa.getInstance());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		setTitle("Planificaci\u00F3n de eventos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		dim = super.getToolkit().getScreenSize();
		dim.width *= .92;
		dim.height *= .92;
		//super.setSize(dim.width, dim.height);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.BLACK);
		menuBar.setBackground(Color.WHITE);
		setJMenuBar(menuBar);
		
		JMenu mnGestinDeEventos = new JMenu("Gesti\u00F3n de Eventos");
		mnGestinDeEventos.setIcon(new ImageIcon(Principal.class.getResource("/imagen/evento.png")));
		mnGestinDeEventos.setFont(new Font("Roboto", Font.PLAIN, 14));
		menuBar.add(mnGestinDeEventos);
		
		JMenuItem mntmRegistrarEvento = new JMenuItem("Registrar evento");
		mntmRegistrarEvento.setFont(new Font("Roboto", Font.PLAIN, 14));
		mntmRegistrarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreandoEvento window = new CreandoEvento(null, false);
				window.setModal(true);
				window.setVisible(true);
			}
		});
		mnGestinDeEventos.add(mntmRegistrarEvento);
		mntmRegistrarEvento.setIcon(new ImageIcon(Principal.class.getResource("/imagen/add.png")));

		JMenuItem mntmListarEventos = new JMenuItem("Listar eventos");
		mntmListarEventos.setIcon(new ImageIcon(Principal.class.getResource("/imagen/listaeventos.png")));
		mntmListarEventos.setFont(new Font("Roboto", Font.PLAIN, 14));
		mntmListarEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaEventos window = new ListaEventos();
				window.setModal(true);
				window.setVisible(true);
			}
		});
		mnGestinDeEventos.add(mntmListarEventos);
		
		JMenu mnGestionDePersonal = new JMenu("Gesti\u00F3n del personal");
		mnGestionDePersonal.setIcon(new ImageIcon(Login.class.getResource("/Imagen/Person.png")));
		mnGestionDePersonal.setFont(new Font("Roboto", Font.PLAIN, 14));
		menuBar.add(mnGestionDePersonal);
		
		JMenuItem mntmRegistrarPersona = new JMenuItem("Registrar persona");
		mntmRegistrarPersona.setIcon(new ImageIcon(Principal.class.getResource("/Imagen/addPerson.png")));
		mntmRegistrarPersona.setFont(new Font("Roboto", Font.PLAIN, 14));
		mntmRegistrarPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegPersona window = new RegPersona();
				window.setModal(true);
				window.setVisible(true);
			}
		});
		mnGestionDePersonal.add(mntmRegistrarPersona);
		
		JMenuItem mntmListarPersonal = new JMenuItem("Listar personal");
		mntmListarPersonal.setIcon(new ImageIcon(Principal.class.getResource("/Imagen/listPerson.png")));
		mntmListarPersonal.setFont(new Font("Roboto", Font.PLAIN, 14));
		mntmListarPersonal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ListarPersonas window = new ListarPersonas();
				window.setModal(true);
				window.setVisible(true);
				
			}
		});
		mnGestionDePersonal.add(mntmListarPersonal);
		
		JMenu mnGestinDeRecursos = new JMenu("Gesti\u00F3n de Recursos");
		mnGestinDeRecursos.setIcon(new ImageIcon(Principal.class.getResource("/imagen/recursos.png")));
		mnGestinDeRecursos.setFont(new Font("Roboto", Font.PLAIN, 14));
		menuBar.add(mnGestinDeRecursos);
		
		JMenuItem mntmRegistrarRecurso = new JMenuItem("Registrar recurso");
		mntmRegistrarRecurso.setIcon(new ImageIcon(Principal.class.getResource("/imagen/agregarrecurso.png")));
		mntmRegistrarRecurso.setFont(new Font("Roboto", Font.PLAIN, 14));
		mntmRegistrarRecurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegRecurso window = new RegRecurso();
				window.setModal(true);
				window.setVisible(true);
			}
		});
		mnGestinDeRecursos.add(mntmRegistrarRecurso);
		
		JMenuItem mntmListarRecursos = new JMenuItem("Listar recursos");
		mntmListarRecursos.setIcon(new ImageIcon(Principal.class.getResource("/imagen/redlist.png")));
		mntmListarRecursos.setFont(new Font("Roboto", Font.PLAIN, 14));
		mntmListarRecursos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListaRecursos window = new ListaRecursos();
				window.setModal(true);
				window.setVisible(true);
			}
		});
		mnGestinDeRecursos.add(mntmListarRecursos);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		
		contentPane.add(panel, BorderLayout.CENTER);
//**********************************************************************************************************************************************************************************		
		JPanel panelEventosPopu = new JPanel();
		panelEventosPopu.setBounds(10, 11, 359, 307);
		//.setBounds(10, 11, panel.getSize().width/4, panel.getSize().height/4);
		panel.setLayout(null);
		panel.add(panelEventosPopu);
		panelEventosPopu.setLayout(new BorderLayout(0, 0));
//**********************************************************************************************************************************************************************************		
		JPanel panelGeneros = new JPanel();
		panelGeneros.setBounds(405, 11, 359, 307);
		panel.add(panelGeneros);
		panelGeneros.setLayout(new BorderLayout(0, 0));
//**********************************************************************************************************************************************************************************		
		JPanel panelAreaYTrabajo = new JPanel();
		panelAreaYTrabajo.setBounds(10, 390, 359, 307);
		//panelAreaYTrabajo.setBounds(10, 390, panel.getSize().width/4, panel.getSize().height/4);
		panel.add(panelAreaYTrabajo);
		panelAreaYTrabajo.setLayout(new BorderLayout(0, 0));
		
		int[] totales = Empresa.getInstance().cantidadTrabajosPorArea();
		
		// Fuente de Datos
        DefaultPieDataset defaultpiedataset = new DefaultPieDataset(); 
        defaultpiedataset.setValue("Matem�ticas", totales[0]); 
        defaultpiedataset.setValue("Qu�mica", totales[1]); 
        defaultpiedataset.setValue("Biolog�a", totales[2]); 
        defaultpiedataset.setValue("Historia", totales[3]); 
        defaultpiedataset.setValue("F�sica", totales[4]); 
        defaultpiedataset.setValue("Ingenier�a", totales[5]); 
        
 
        // Creando el Grafico
        JFreeChart chart = ChartFactory.createPieChart3D("Trabajos por �rea de investigaci�n", defaultpiedataset, true, true, false); 
        PiePlot3D pieplot3d = (PiePlot3D)chart.getPlot(); 
        pieplot3d.setDepthFactor(0.5); 
        pieplot3d.setStartAngle(290D); 
        pieplot3d.setDirection(Rotation.CLOCKWISE); 
        pieplot3d.setForegroundAlpha(0.5F); 
        
        // Mostrar Grafico
        ChartPanel chartPanel = new ChartPanel(chart);
        panelAreaYTrabajo.add(chartPanel);
//**********************************************************************************************************************************************************************************		
		JPanel panelJuecesPopulares = new JPanel();
		panelJuecesPopulares.setBounds(405, 390, 359, 307);
		panel.add(panelJuecesPopulares);
		panelJuecesPopulares.setLayout(new BorderLayout(0, 0));
	}
}
