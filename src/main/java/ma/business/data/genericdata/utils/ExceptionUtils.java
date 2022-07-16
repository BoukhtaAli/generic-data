package ma.business.data.genericdata.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils {

	private ExceptionUtils() {

	}

	public static String getStackTrace(final Throwable throwable) {
		final StringWriter stringWriter = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(stringWriter, true);
		throwable.printStackTrace(printWriter);
		return stringWriter.getBuffer().toString();
	}
}
