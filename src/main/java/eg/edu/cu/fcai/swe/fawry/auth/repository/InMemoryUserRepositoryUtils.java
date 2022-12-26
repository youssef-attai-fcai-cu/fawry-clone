package eg.edu.cu.fcai.swe.fawry.auth.repository;

import java.util.UUID;

public class InMemoryUserRepositoryUtils {
    public static String newUserToken() {
        return Long.toHexString(Double.doubleToLongBits(Math.random()));
    }

    public static String newUserId() {
        return "USER-" + UUID.randomUUID();
    }

    public static String newAdminId() {
        return "ADMIN-" + UUID.randomUUID();
    }
}
