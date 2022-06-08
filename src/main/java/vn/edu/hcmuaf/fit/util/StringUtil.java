package vn.edu.hcmuaf.fit.util;

import javax.servlet.ServletInputStream;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtil {
    public static final String ENCODING_UTF8 = "UTF-8";

    private static final Pattern SPLIT_PATTERN = Pattern.compile("\"([^\"]*)\"|(\\S+)");

    private static final String[][] MIME_TYPES = {
            {"gif", "image/gif"},
            {"jpg", "image/jpeg"},
            {"jpeg", "image/jpeg"},
            {"png", "image/png"},
            {"bmp", "image/bmp"},
    };

    private static final String[] FILE_SYSTEM_UNSAFE = {"/", "\\", "..", ":", "\"", "?", "*", "|"};

    /**
     * Disallow external instantiation.
     */
    private StringUtil() {
    }

    public static String getMimeType(String suffix) {
        for (String[] map : MIME_TYPES) {
            if (map[0].equalsIgnoreCase(suffix) || ('.' + map[0]).equalsIgnoreCase(suffix)) {
                return map[1];
            }
        }
        return "application/octet-stream";
    }

    public static String getMimeType(String suffix, boolean sonos) {
        String result = getMimeType(suffix);

        // Sonos doesn't work with "audio/mp4" but needs "audio/aac" for ALAC and AAC (in MP4 container)
        return sonos && "audio/mp4".equals(result) ? "audio/aac" : result;
    }

    public static String getSuffix(String mimeType) {
        for (String[] map : MIME_TYPES) {
            if (map[1].equalsIgnoreCase(mimeType)) {
                return map[0];
            }
        }
        return null;
    }

    /**
     * Formats a duration with minutes and seconds, e.g., "4:34" or "93:45"
     */
    public static String formatDurationMSS(int seconds) {
        if (seconds < 0) {
            throw new IllegalArgumentException("seconds must be >= 0");
        }
        return String.format("%d:%02d", seconds / 60, seconds % 60);
    }

    /**
     * Formats a duration with H:MM:SS, e.g., "1:33:45"
     */
    public static String formatDurationHMMSS(int seconds) {
        int hours = seconds / 3600;
        seconds -= hours * 3600;

        return String.format("%d:%s%s", hours, seconds < 600 ? "0" : "", formatDurationMSS(seconds));
    }

    /**
     * Formats a duration to M:SS or H:MM:SS
     */
    public static String formatDuration(int seconds) {
        if (seconds >= 3600) {
            return formatDurationHMMSS(seconds);
        }
        return formatDurationMSS(seconds);
    }

    /**
     * Splits the input string. White space is interpreted as separator token. Double quotes
     * are interpreted as grouping operator. <br/>
     * For instance, the input <code>"u2 rem "greatest hits""</code> will return an array with
     * three elements: <code>{"u2", "rem", "greatest hits"}</code>
     *
     * @param input The input string.
     * @return Array of elements.
     */
    public static String[] split(String input) {
        if (input == null) {
            return new String[0];
        }

        List<String> result = new ArrayList<>();
        Matcher m = SPLIT_PATTERN.matcher(input);
        while (m.find()) {
            if (m.group(1) != null) {
                result.add(m.group(1)); // quoted string
            } else {
                result.add(m.group(2)); // unquoted string
            }
        }

        return result.toArray(new String[result.size()]);
    }

    public static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, StringUtil.ENCODING_UTF8);
        } catch (UnsupportedEncodingException x) {
            throw new RuntimeException(x);
        }
    }

    /**
     * URL-decodes the input value using UTF-8.
     */
    public static String urlDecode(String s) {
        try {
            return URLDecoder.decode(s, StringUtil.ENCODING_UTF8);
        } catch (UnsupportedEncodingException x) {
            throw new RuntimeException(x);
        }
    }


    public static String fileSystemSafe(String filename) {
        for (String s : FILE_SYSTEM_UNSAFE) {
            filename = filename.replace(s, "-");
        }
        return filename;
    }

    public static String removeMarkup(String s) {
        if (s == null) {
            return null;
        }
        return s.replaceAll("<.*?>", "");
    }

    public static String stripAccents(String s) {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }

    public static String normalizeUri(String input) {
        input = input.replaceAll("đ", "d").replace("Đ", "D");

        input = stripAccents(input);

        return input.trim().replaceAll("[^a-zA-Z0-9]+", "-").toLowerCase();
    }

    public static String trimOrNull(String value) {
        if (value == null || value.trim().isEmpty())
            return null;

        return value.trim();
    }

    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static Integer parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return null;
        }
    }

    public static String RandomString(int length) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1).limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }

    public static String getFileNameWithoutExtension(String fullname) {
        if (fullname == null)
            return null;

        return fullname.replaceFirst("[.][^.]+$", "");
    }

    public static String toStringWithoutSpaces(String s) {
        return s.replaceAll("\\s+", "");
    }

    // get string with utf-8 encoding
    public static String getStringFromInputStream(ServletInputStream inputStream) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
