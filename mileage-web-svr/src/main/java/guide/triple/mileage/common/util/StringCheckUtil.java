package guide.triple.mileage.common.util;

public class StringCheckUtil {

    private StringCheckUtil(){}

    public static boolean isNonNull(String data) {
        return data != null && data.length() > 0;
    }
}
