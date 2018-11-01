package admin;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

class AdminUtils {

    private final static Logger LOGGER
            = Logger.getLogger(AdminUtils.class.getCanonicalName());

    static long getDateFromRequest(String scheduleDayString, String scheduleTimeString) {
        DateTimeFormatter scheduleDayFormat = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        LocalDate scheduleDay = LocalDate.parse(scheduleDayString, scheduleDayFormat);
        LocalTime scheduleTime = LocalTime.parse(scheduleTimeString);

        LocalDateTime scheduleDaytime = LocalDateTime.of(scheduleDay, scheduleTime);

        LocalDateTime now = LocalDateTime.now();
        return now.until(scheduleDaytime, ChronoUnit.MILLIS);
    }

    static String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    static String getImagesFolderPath() throws IOException {
        Properties prop = new Properties();
        String propFileName = "config" + File.separator + "filepath.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try (InputStream resourceStream = loader.getResourceAsStream(propFileName)) {
            prop.load(resourceStream);
            return prop.getProperty("imagesfilepath");
        }
    }

}
