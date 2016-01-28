package ca.jonathanfritz.budgey.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

/**
 * Utility class for loading files from the classpath in unit tests
 */
public class ClasspathLoader {
	public static String load(String filename) {
		try (final InputStream in = ClasspathLoader.class.getClassLoader().getResourceAsStream(filename)) {
			try (final StringWriter writer = new StringWriter()) {
				IOUtils.copy(in, writer, StandardCharsets.UTF_8);
				return writer.toString();
			} catch (final IOException e) {
				throw new RuntimeException(e);
			}
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
}
