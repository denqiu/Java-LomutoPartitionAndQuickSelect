package homework4;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import getFile.GetFile;
import getFile.Sort;
import getFile.WriteFile;

public class Data {
	private GetFile[] getFiles = new GetFile[2];

	public Data(Class<?> data) {
		String[] fileNames = new String[] {data.getSimpleName(), "ouput" + data.getSimpleName()};
		for (int i = 0; i < fileNames.length; i++) {
			getFiles[i] = new GetFile("Data", new GetFile(data.getSimpleName(), fileNames[i]));
			File read = getFiles[i].readFile();
			if (read.exists()) {
				read.delete();
			}	
		}
		String fileName = (String) getFiles[0].getFileName();
		System.out.println(problem(fileName));
	}

	public void write(Object write, boolean isProblem) {
		(getFiles[(isProblem) ? 0 : 1]).execute(new WriteFile(true) {
			@Override
			public Object setItems() {
				return write;
			}
			@Override
			public Sort getSort() {
				return null;
			}
		});
	}
	
	public void createGraph() {
		try {
			File graph = new File(getFiles[0].getFilePath().replace("txt", "xls"));
			if (!graph.exists()) {
				graph.createNewFile();
			}
			Desktop.getDesktop().open(graph);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String problem(String problemName) {
		String s = "";
		for (int i = 0; i < problemName.length(); i++) {
			s += "-";
		}
		return s + "\n" + problemName + "\n" + s;
	}
}
