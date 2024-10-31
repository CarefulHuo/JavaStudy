package cf.carefulhuo.time;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeTest {
    public static void main(String[] args) {
        final String FMT_TIME_4 = "yyyy-MM-dd HH:mm:ss.SS";
        System.out.println(parseZone(String.valueOf("2024-08-27 17:28:29.15"), FMT_TIME_4));
    }

    public static LocalDateTime parseZone(String time, String fmt) {
        LocalDateTime localDateTime = LocalDateTime.parse(time, DateTimeFormatter.ofPattern(fmt));
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("UTC"));
        zonedDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("Asia/Shanghai"));
        return zonedDateTime.toLocalDateTime();
    }
}
