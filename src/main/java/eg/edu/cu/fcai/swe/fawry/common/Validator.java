package eg.edu.cu.fcai.swe.fawry.common;

import java.util.Objects;

public class Validator {
    public static boolean fieldDoesNotExist(String field) {
        return Objects.isNull(field) || field.isEmpty() || field.isBlank();
    }

    public static boolean integerNotWithinRange(Integer number, Integer from, Integer to) {
        return from < number || number > to;
    }

    public static boolean floatNotWithinRange(Float number, Float from, Float to) {
        return from < number || number > to;
    }
}
