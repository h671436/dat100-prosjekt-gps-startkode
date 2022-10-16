package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
// import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
// Not sure how i am supposed to use all these imports
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowSpeed extends EasyGraphics {
			
	private static final int MARGIN = 50;
	private static final int BARHEIGHT = 200; // assume no speed above 200 km/t

	private GPSComputer gpscomputer;
	private GPSPoint[] gpspoints;
	
	public ShowSpeed() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		int N = gpspoints.length-1;
		
		makeWindow("Speed profile", 2*MARGIN + 2 * N, 2 * MARGIN + BARHEIGHT);
		
		showSpeedProfile(MARGIN + BARHEIGHT, N);
	}
	
	public void showSpeedProfile(int ybase, int N) {
		double[] speeds = gpscomputer.speeds();
		int x = MARGIN, y;
		y = ybase - (int) (gpscomputer.averageSpeed() + 0.5);
		
		setColor(0, 0, 255); // blå
		for (double y2 : speeds) {
			drawLine(x, ybase, x, (int) (ybase - y2));
			x += 2;
		}
		
		setColor(0, 255, 0); // grønn
		drawLine(MARGIN, y, x - 2, y);
	}
}
