package helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class pulls the latest section data from uga.edu and populates the section and meeting tables
 * @author David Sawyer
 *
 */
public class DBPopulator {
	public static void main(String[] args) {

		ArrayList<ArrayList<String>> sections = new ArrayList<ArrayList<String>>(0);

		// read in the csv file
		try {
			URL url = new URL("https://apps.reg.uga.edu/reporting/static_reports/course_offering_UNIV_201308.csv");

			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String str;
			// read a line
			while ((str = in.readLine()) != null) {
				// turn a line in the csv into a list
				ArrayList<String> line = new ArrayList<String>(0);
				line.addAll(Arrays.asList(str.split("\\s*,\\s*")));

				// trim whitespace for all Strings in the line
				for (int j = 0; j < line.size(); j++) {
					line.set(j, line.get(j).replaceAll("\\s",""));
					line.set(j, line.get(j).substring(1,line.get(j).length()-1));
				}
				removeUselessColumns(line);
				sections.add(line);
			}
			System.out.println(sections.get(0));
			System.out.println(sections.get(sections.size()-1));	
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private static void removeUselessColumns(ArrayList<String> list) {
		
		list.remove(22);
		list.remove(21);
		list.remove(18);
		list.remove(17);
		list.remove(16);
		list.remove(15);
		list.remove(14);
		list.remove(13);
		list.remove(12);
		list.remove(8);
		list.remove(0);

		if (list.get(4).equals("null")) {
			list.set(4, "STAFF");
		}
		
		list.set(6, list.get(6).substring(0,1));	
	}
}
