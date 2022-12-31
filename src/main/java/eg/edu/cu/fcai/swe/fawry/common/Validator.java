package eg.edu.cu.fcai.swe.fawry.common;

import eg.edu.cu.fcai.swe.fawry.auth.model.User;
import eg.edu.cu.fcai.swe.fawry.common.repository.UserRepository;
import eg.edu.cu.fcai.swe.fawry.payment.exception.InvalidAmount;
import eg.edu.cu.fcai.swe.fawry.common.exception.InvalidToken;
import eg.edu.cu.fcai.swe.fawry.common.exception.MissingFieldException;

import java.util.Objects;

public class Validator {
    private static final boolean DEBUG_MODE = false;

    public static User validateUserToken(UserRepository userRepository, String token) {
        if (DEBUG_MODE) return userRepository.getUserByToken("dummyuser");
        User user = userRepository.getUserByToken(extractToken(token));
        if (Objects.isNull(user)) throw new InvalidToken();
        return user;
    }

    public static User validateAdminToken(UserRepository userRepository, String token) {
        if (DEBUG_MODE) return userRepository.getAdminByToken("dummyadmin");
        User admin = userRepository.getAdminByToken(extractToken(token));
        if (Objects.isNull(admin)) throw new InvalidToken();
        return admin;
    }

    public static void assertFieldExists(String field, String value) {
        if (Objects.isNull(value) || value.isEmpty() || value.isBlank()) throw new MissingFieldException(field);
    }

    public static void assertFieldExists(String field, Object value) {
        if (Objects.isNull(value)) throw new MissingFieldException(field);
    }

    public static void assertFieldExists(String field, Integer value) {
        if (Objects.isNull(value)) throw new MissingFieldException(field);
    }

    public static void assertFieldExists(String field, Float value) {
        if (Objects.isNull(value)) throw new MissingFieldException(field);
    }

    public static void assertNumberWithinRange(String field, Float number, Float from, Float to) {
        if (number < from || to < number) throw new InvalidAmount(field, number);
    }

    private static String extractToken(String token) {
        assertFieldExists("token", token);
        token = token.replaceFirst("Bearer ", "");
        return token;
    }
}
