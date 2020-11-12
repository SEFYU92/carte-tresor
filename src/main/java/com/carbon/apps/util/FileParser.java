package com.carbon.apps.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import com.carbon.apps.model.mapobjects.Adventurer;
import com.carbon.apps.model.mapobjects.MapObjectInterface;

/**
 *Parse input file into list of map object and list of adventurers.
 *Then pass it to the map model to set up its internal representation.
 *@see ArrayMapModel
 */
public class FileParser {

	private Pair<Integer, Integer> dimensions = new ImmutablePair<Integer, Integer>(0, 0);

	private List<MapObjectInterface> mapObjects;

	private List<Adventurer> adventurers;

	private Path path;
	
	/**
	 * Will ignore any line that does not match regex
	 * any io exception will make the application exit on error
	 * @param file absolute path of input file
	 */
	public FileParser(String file) {

		path = Paths.get(file);
		mapObjects = new ArrayList<MapObjectInterface>();
		adventurers = new ArrayList<Adventurer>();

		try (Stream<String> lines = Files.lines(path)) {

			MapObjectFactory factory = new MapObjectFactory();
			Iterator<String> iterator = lines.iterator();

			while (iterator.hasNext()) {
				
				String line = iterator.next();
				if (checkLineFormat(line)) {

					String[] lineData = line.split(" - ", 2);
					char type = lineData[0].toUpperCase().charAt(0);

					String[] linePayLoad = lineData[1].split(" - ");

					if (type == 'C') {

						int x = Integer.parseInt(linePayLoad[0]);
						int y = Integer.parseInt(linePayLoad[1]);
						dimensions = new ImmutablePair<Integer, Integer>(x, y);

					} else if (type == 'M' || type == 'T' || type == 'A') {

						MapObjectInterface object = factory.createMapObject(type, linePayLoad);

						if (object instanceof Adventurer) {
							adventurers.add((Adventurer) object);
						} else {
							mapObjects.add(object);
						}

					}
				}

			}
			
			if(isOverlapingPositions()) {
				System.err.println("overlaping positions detected");
				System.exit(1);
			}

		} catch (IOException e) {
			System.err.println("An IO error occurred with file : " + path.toString());
			e.printStackTrace();
			System.exit(1);
		}

	}

	public Path getPath() {

		return path;

	}

	public Pair<Integer, Integer> getDimensions() {
		return this.dimensions;
	}

	public List<MapObjectInterface> getMapObjects() {
		return mapObjects;
	}

	public List<Adventurer> getAdventurers() {
		return adventurers;
	}

	private boolean checkLineFormat(String line) {

		if (line.matches("^[CM]\\s-\\s\\d+\\s-\\s\\d+$")) {
			return true;
		} else if (line.matches("^[T]\\s-\\s\\d+\\s-\\s\\d+\\s-\\s\\d+$")) {
			return true;
		} else if (line.matches("^[A]\\s-\\s\\w+\\s-\\s\\d+\\s-\\s\\d+\\s-\\s[NESO]\\s-\\s[ADG]+$")) {
			return true;
		} else {
			return false;
		}

	}
	
	private boolean isOverlapingPositions () {
		List<MapObjectInterface> unikMapObjects = new ArrayList<MapObjectInterface>(new HashSet<MapObjectInterface>(this.mapObjects));
		List<Adventurer> unikAdventurers = new ArrayList<Adventurer>(new HashSet<Adventurer>(this.adventurers));
		if((unikAdventurers.size() < this.adventurers.size()) || (unikMapObjects.size() < this.mapObjects.size())) {
			return true;
		}else {
			return false;
		}
	}

}
