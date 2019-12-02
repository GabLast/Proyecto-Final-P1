package logica;

import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import visual.Principal;

public class HiloJuecesPopulares extends Thread {

	public HiloJuecesPopulares() {
		super();
	}
	
	@Override
	public void run()
	{

		try {
			while(true) 
			{
				ArrayList<Jurado> juecesMasPopulares = Empresa.getInstance().juecesMasPopulares();
				int contador = 0;
				
				DefaultCategoryDataset grafico4 = Principal.grafico4;
				
				for(Jurado juez : juecesMasPopulares)
				{
					grafico4.setValue(juez.getParticipaciones(), "Jueces", juez.getNombre());
					contador++;
					
					if(contador >= 5)
					{
						break;
					}
					
				}

				contador = 0;
				Principal.chart4 = ChartFactory.createBarChart3D("Jueces más populáres en las comisiones","Jueces", "Participaciones", grafico4, PlotOrientation.VERTICAL, true,true, false);
//				Principal.chart4.fireChartChanged();
				Thread.sleep(1000);
				Principal.grafico4.clear();
				
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Sleep del hilo de JuecesPopulares se detuvo con error: "+e.getMessage());
			e.printStackTrace();
		}
	}
}
