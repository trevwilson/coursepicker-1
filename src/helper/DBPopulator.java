package helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import model.Course;
import model.Section;
import helper.Helper;

/**
 * This class pulls the latest section data from uga.edu and populates the section and meeting tables
 * @author David Sawyer
 */
public class DBPopulator {
	public static void main(String[] args) {

		System.out.println("Beginning DBPopulator Job");
		long startTime = System.currentTimeMillis();
		ArrayList<ArrayList<String>> infile = new ArrayList<ArrayList<String>>(0);

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
				infile.add(line);
			}
			//System.out.println(infile.get(0));
			//System.out.println(infile.get(infile.size()-1));	
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		Helper helper = new Helper();
		helper.resetSectionAndMeeting();
		ArrayList<ArrayList<String>> strippedDownInfile = new ArrayList<ArrayList<String>>(0);
		ArrayList<Course> courseList = null;
		ArrayList<String> usedCallNums = new ArrayList<String>(0);

		for (ArrayList<String> line : infile) {
			// add a Section to the database

			// if the callNum for this line has NOT been used yet
			if (!usedCallNums.contains(line.get(0))) {
				usedCallNums.add(line.get(0));
				//System.out.println(line.get(1));
				//System.out.println(line.get(2));
				courseList = helper.getCourseList(line.get(1), line.get(2));
				// if the section is found for this line, add it to a new list of lines
				if (courseList.size() != 0) {
					strippedDownInfile.add(line);
					// add a section with a reference to one of the returned Courses' id
					for (Course course : courseList) {
						try {
							if (helper.addSection(line.get(0), line.get(6), line.get(4), course.getId())) {
								//System.out.println("Successfully added section of course " + course.getCourseNum() + course.getCoursePrefix());
							}
							else {
								//System.out.println("Failed to add section of course " + course.getCourseNum() + course.getCoursePrefix());
							}
						} catch(Exception e) {
							e.printStackTrace();
							System.exit(1);
						}
					}
				}
			}
		}

		ArrayList<Section> sectionList = new ArrayList<Section>(0);
		ArrayList<String> days = new ArrayList<String>(0);
		String untokenizedDays = null;

		for (ArrayList<String> line : strippedDownInfile) {
			//checks for sections from a line in stripped down infile
			sectionList = helper.getSectionList(line.get(0)); // get the section with this strippedDownInfile's line's callNum 
			for (Section section : sectionList) {
				//add meetings for a section in list
				untokenizedDays = line.get(7);
				if (!untokenizedDays.equalsIgnoreCase("AR") && !untokenizedDays.equalsIgnoreCase("VR")
						&& !untokenizedDays.equalsIgnoreCase("thru") && !untokenizedDays.equals("")) {
					if (line.get(5).equalsIgnoreCase("Available")) {					
						days.addAll(Arrays.asList(untokenizedDays.split("")));
						days.remove(0); // strip off the first index because it is an empty string
					}
				}
				else {
					days.add(untokenizedDays);
				}
				for (String meetingDay : days) {
					//adds a meeting for a day
					helper.addMeeting(line.get(8), line.get(9), meetingDay, line.get(11), line.get(10), section.getId());
				}
				days.clear();
			}
		}

		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Finished DBPopulator Job");
		System.out.println("Total time of job: " + totalTime/1000.0 + " sec");
	}

	/**
	 * removes specific unneeded elements of data in a row
	 * @param list - a list of Section and Meeting data
	 * @author David Sawyer
	 */
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