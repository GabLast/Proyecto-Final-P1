package logica;

import org.jfree.chart.ChartFactory;
import org.jfree.data.general.DefaultPieDataset;

import visual.Principal;

public class HiloTrabajosPorArea extends Thread {

	public HiloTrabajosPorArea() {
		super();
	}
	
	
	@Override
	public void run()
	{

		try {
			while(true) 
			{
				int[] totales = Empresa.getInstance().cantidadTrabajosPorArea();
				
				DefaultPieDataset defaultpiedataset = Principal.defaultpiedataset;

				defaultpiedataset.setValue("Matem�ticas", totales[0]); 
		        defaultpiedataset.setValue("Qu�mica", totales[1]); 
		        defaultpiedataset.setValue("Biolog�a", totales[2]); 
		        defaultpiedataset.setValue("Historia", totales[3]); 
		        defaultpiedataset.setValue("F�sica", totales[4]); 
		        defaultpiedataset.setValue("Ingenier�a", totales[5]); 


				Principal.chart3 = ChartFactory.createPieChart3D("Trabajos por �rea de investigaci�n", defaultpiedataset, true, true, false); 

				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Sleep del hilo de Trabajo/Area se detuvo con error: "+e.getMessage());
			e.printStackTrace();
		}
	}
}
