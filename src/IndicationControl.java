import java.io.File;
import java.util.ArrayList;

public class IndicationControl {

	public ArrayList<String> Inikation() {

		ArrayList<String> lst = new ArrayList<>();

		int[] a = new int[] { 2, 2, 2, 2, 2 };

		boolean o = true;
		boolean p = true;
		boolean r = true;
		boolean s = true;
		boolean t = true;

		File[] arrayRoots = File.listRoots();
		for (File root : arrayRoots) {
			if (root.getPath().equalsIgnoreCase("O:\\")) {
				a[0] = 1;
				o = false;
			}

			if (root.getPath().equalsIgnoreCase("P:\\")) {
				a[1] = 1;
				p = false;
			}

			if (root.getPath().equalsIgnoreCase("R:\\")) {
				a[2] = 1;
				r = false;
			}

			if (root.getPath().equalsIgnoreCase("S:\\")) {
				a[3] = 1;
				s = false;
			}

			if (root.getPath().equalsIgnoreCase("T:\\")) {
				a[4] = 1;
				t = false;
			}

		}

		if (o) {
			a[0] = 2;
		}
		if (p) {
			a[1] = 2;
		}
		if (r) {
			a[2] = 2;
		}
		if (s) {
			a[3] = 2;
		}
		if (t) {
			a[4] = 2;
		}

		for (int i = 0; i < a.length; i++) {

			lst.add(i, String.valueOf(a[i]));
		}

		return lst;

	}

}
