package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

// import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {
		double max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
	
		return max;
	}

	public static double findMin(double[] da) {
		double min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		
		return min;
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {
		double[] latitudes = new double[gpspoints.length];

		for (int i = 0; i < gpspoints.length; i++) {
			latitudes[i] = gpspoints[i].getLatitude();
		}
		
		return latitudes;
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {
		double[] longitudes = new double[gpspoints.length];
		
		for (int i = 0; i < gpspoints.length; i++) {
			longitudes[i] = gpspoints[i].getLongitude();
		}

		return longitudes;
	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {
		double radlat1 = toRadians(gpspoint1.getLatitude());
		double radlong1 = toRadians(gpspoint1.getLongitude());
		double radlat2 = toRadians(gpspoint2.getLatitude());
		double radlong2 = toRadians(gpspoint2.getLongitude());
		
		double e = pow(sin((radlat2 - radlat1) / 2), 2);
		double f = pow(sin((radlong2 - radlong1) / 2), 2);
		double a = e + cos(radlat1) * cos(radlat2) * f;
		
		double c = 2 * atan2(sqrt(a), sqrt(1 - a));
		
		double d = R * c;

		return d;
	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {
		int time = gpspoint2.getTime() - gpspoint1.getTime(); // i sekunder
		double dis = distance(gpspoint1, gpspoint2); // i meter
		
		double speed = ((dis / time) * 3600) / 1000;
		
		return speed; // km/t
	}

	public static String formatTime(int secs) {
		String TIMESEP = ":";
		String timestr = "";
		
		int hh = secs / 3600;
		int mm = (secs % 3600) / 60;
		int ss = (secs % 3600) % 60;
		
		int[] time = {hh, mm, ss};
		
		for (int x : time) {
			timestr += String.format("%02d", x);
			if (x != time[time.length - 1]) {
				timestr += TIMESEP;
			}
		}
		
		timestr = String.format("%1$10s", timestr);
		
		return timestr;
	}
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {
		String str = "";
		
		str += Math.round(d*100) / 100.0;
		str = String.format("%1$" + TEXTWIDTH + "s", str);
		
		return str;
	}
}
