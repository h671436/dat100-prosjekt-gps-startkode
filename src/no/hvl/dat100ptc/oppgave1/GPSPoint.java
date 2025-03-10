package no.hvl.dat100ptc.oppgave1;

// import no.hvl.dat100ptc.TODO;

public class GPSPoint {
	private int time; // i sekunder
	private double latitude;
	private double longitude;
	private double elevation; // i meter
		
	public GPSPoint(int time, double latitude, double longitude, double elevation) {
		setTime(time);
		setLatitude(latitude);
		setLongitude(longitude);
		setElevation(elevation);

	}
	
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getElevation() {
		return elevation;
	}

	public void setElevation(double elevation) {
		this.elevation = elevation;
	}
	
	public String toString() {
		String str;
		
		str = time + " (" + latitude + "," + longitude + ") " + elevation + "\n";

		return str;
	}
}
