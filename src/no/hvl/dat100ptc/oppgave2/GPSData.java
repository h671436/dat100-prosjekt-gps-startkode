package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int n) {
		gpspoints = new GPSPoint[n];
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	protected boolean insertGPS(GPSPoint gpspoint) {
		boolean inserted = false;
		
		if (antall < gpspoints.length) {
			gpspoints[antall] = gpspoint;
			antall++;
			return !inserted;
		}
		
		return inserted;
	}

	public boolean insert(String timetxt, String latitudetxt, String longitudetxt, String elevationtxt) {
		boolean inserted = false;
		GPSPoint gpspoint = GPSDataConverter.convert(timetxt, latitudetxt, longitudetxt, elevationtxt);
		
		if (antall < gpspoints.length) {
			gpspoints[antall] = gpspoint;
			antall++;
			return !inserted;
		}
		
		return inserted;
	}

	public void print() {
		System.out.println("====== Konvertert GPS Data - START ======");

		for (GPSPoint x : gpspoints) {
			System.out.println(x.toString());
		}
		
		System.out.println("====== Konvertert GPS Data - SLUTT ======");
	}
}
