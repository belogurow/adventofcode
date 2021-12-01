package ru.belogurow;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ResourcesReaderUtils {

	public static List<String> readFileFromResources(int year, int dayNumber) {
		var fileResourcePath = String.format("%d/%d.txt", year, dayNumber);

		var classLoader = ResourcesReaderUtils.class.getClassLoader();
		var resource = classLoader.getResource(fileResourcePath);

		if (resource == null) {
			throw new IllegalStateException();
		} else {
			try {
				return Files.readAllLines(Path.of(resource.toURI()));
			} catch (URISyntaxException | IOException e) {
				throw new IllegalStateException(e);
			}
		}
	}
}
