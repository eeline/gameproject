package background;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

class MapLoader {
	private BufferedReader reader;
	private int lineCount = 0;
	private List<String> lines;
	private boolean isLinesSet = false;

	MapLoader(String fileLocation) {
		try {
			this.reader = new BufferedReader(new FileReader(new File(
					fileLocation)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String get() {
		String line = null;
		try {
			line = getLine();
			this.reader.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return line;
	}
	
	public boolean isReady(){
		return lines.size() < this.lineCount;
	}

	private String getLine() throws IOException {
		if (!this.isLinesSet) {
			while (this.reader.ready()) {
				for (int i = lineCount; i < lineCount; i++) {
					String line = reader.readLine();
					if (!line.startsWith("!"))
						lines.add(line);
				}
			}
			this.isLinesSet = !this.isLinesSet;
			reader.close();
		}
		String line = lines.get(this.lineCount);
		this.lineCount++;
		return line;
	}
}
