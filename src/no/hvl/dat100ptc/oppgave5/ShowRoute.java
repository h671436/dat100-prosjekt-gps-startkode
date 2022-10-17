package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
// import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		
		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {
	
		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		
		double ystep = MAPYSIZE / (Math.abs(maxlat - minlat));
		
		return ystep;
	}

	public void showRouteMap(int ybase) {
		final int R = 2;
		double[] lon = GPSUtils.getLongitudes(gpspoints);
		double[] lat = GPSUtils.getLatitudes(gpspoints);
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		
		setColor(0, 255, 0); // grønn
		for(int i = 0; i < gpspoints.length; i++) {
			if ( i < gpspoints.length - 1) {
				// make circle
				int x = MARGIN + (int) ((lon[i] - minlon) * xstep());
				int y = ybase - (int) ((lat[i] - minlat) * ystep());
				fillCircle(x, y, R);
				
				// draw line to next circle
				int x2 = MARGIN + (int) ((lon[i + 1] - minlon) * xstep());
				int y2 = ybase - (int) ((lat[i + 1] - minlat) * ystep());
				drawLine(x, y, x2, y2);
			} else {
				// last circle
				int x = MARGIN + (int) ((lon[i] - minlon) * xstep());
				int y = ybase - (int) ((lat[i] - minlat) * ystep());
				fillCircle(x, y, R);
			}
		}
	}

	public void showStatistics() {
		int TEXTDISTANCE = 20;
		int i = 1;
		int KG = 80;
		
		String time = String.format("%-15s:", "Total Time") + GPSUtils.formatTime(gpscomputer.totalTime());
		String dis = String.format("%-15s:", "Total distance") + GPSUtils.formatDouble(gpscomputer.totalDistance()) + " km";
		String ele = String.format("%-15s:", "Total elevation") + GPSUtils.formatDouble(gpscomputer.totalElevation()) + " m";
		String maxs = String.format("%-15s:", "Max speed") + GPSUtils.formatDouble(gpscomputer.maxSpeed()) + " km/t";
		String aves = String.format("%-15s:", "Average speed") + GPSUtils.formatDouble(gpscomputer.averageSpeed()) + " km/t";
		String kcal = String.format("%-15s:", "Energy") + GPSUtils.formatDouble(gpscomputer.totalKcal(KG)) + " kcal";

		String[] strlist = {time, dis, ele, maxs, aves, kcal};
		
		setColor(0,0,0);
		setFont("Courier New",12);
		for (String x : strlist) {
			drawString(x, MARGIN, TEXTDISTANCE * i);
			i++;
		}
	}

}
