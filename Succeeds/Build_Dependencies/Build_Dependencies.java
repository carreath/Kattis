import java.util.*;
import java.io.*;

public class Build_Dependencies {
	public Build_Dependencies() {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		sc.nextLine();
		HashMap<String, ArrayList<String>> depFor = new HashMap<>();
		for (int i=0; i<n; i++) {
			String[] file = sc.nextLine().split(" ");
			String fileName = file[0].substring(0, file[0].length()-1);
			if (!depFor.containsKey(fileName)) {
				depFor.put(fileName, new ArrayList<String>());
			}

			for (String dep : file) {
				if (dep.indexOf(":") != -1) continue;
				if (!depFor.containsKey(dep)) {
					depFor.put(dep, new ArrayList<String>(Arrays.asList(fileName)));
				} else {
					ArrayList<String> deps = depFor.get(dep);
					deps.add(fileName);
				}
			}
		}

		LinkedList<String> compileOrder = new LinkedList<>();
		String fileChanged = sc.nextLine();
		printDeps(depFor, compileOrder, fileChanged);
		for (Object file : compileOrder.toArray()) {
			System.out.println((String) file);
		}
	}

	public void printDeps(HashMap<String, ArrayList<String>> depFor, LinkedList<String> compileOrder, String fileName) {
		if (depFor.containsKey(fileName)) {
			ArrayList<String> files = depFor.get(fileName);
			depFor.remove(fileName);
			for (String file : files) {		
				printDeps(depFor, compileOrder, file);
			}
			compileOrder.addFirst(fileName);
		}
	}

	public static void main(String[] args) {
		new Build_Dependencies();
	}
}