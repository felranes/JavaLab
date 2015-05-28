package daria.krutova.ist.address.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Helper functions for handling dates.
 *
 * @author Marco Jakob
 */
public class KDateUtilD {

    /** The date pattern that is used for conversion. Change as you wish. */
    private static final String KDATE_PATTERND = "dd.MM.yyyy";

    /** The date formatter. */
    private static final DateTimeFormatter KDATE_FORMATTERD =
            DateTimeFormatter.ofPattern(KDATE_PATTERND);

    /**
     * Returns the given date as a well formatted String. The above defined
     * {@link KDateUtilD#KDATE_PATTERND} is used.
     *
     * @param kDated the date to be returned as a string
     * @return formatted string
     */
    public static String kFormatD(LocalDate kDated) {
        if (kDated == null) {
            return null;
        }
        return KDATE_FORMATTERD.format(kDated);
    }

    /**
     * Converts a String in the format of the defined {@link KDateUtilD#KDATE_PATTERND}
     * to a {@link LocalDate} object.
     *
     * Returns null if the String could not be converted.
     *
     * @param kDateStringd the date as String
     * @return the date object or null if it could not be converted
     */
    public static LocalDate kParseD(String kDateStringd) {
        try {
            return KDATE_FORMATTERD.parse(kDateStringd, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Checks the String whether it is a valid date.
     *
     * @param kDateStringd
     * @return true if the String is a valid date
     */
    public static boolean kValidDateD(String kDateStringd) {
        // Try to parse the String.
        return KDateUtilD.kParseD(kDateStringd) != null;
    }
}