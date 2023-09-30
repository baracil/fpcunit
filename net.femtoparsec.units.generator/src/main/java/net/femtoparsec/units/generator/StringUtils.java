package net.femtoparsec.units.generator;

/**
 * @author Bastien Aracil
 */
public class StringUtils {

    public static String decapitalizeFirstLetter(String str) {
        return str.substring(0,1).toLowerCase()+str.substring(1);
    }

    public static String remove(String str, String suffix) {
        if (str.endsWith(suffix)) {
            return str.substring(0,str.length()-suffix.length());
        }
        return str;
    }

    public static String deHump(String str) {
        StringBuilder sb = new StringBuilder();
        boolean capital = true;
        for (int i = 0; i < str.length(); i++) {
            final char c = str.charAt(i);
            if (c == '_') {
                capital = true;
                continue;
            }
            else {
                sb.append(capital?Character.toUpperCase(c):Character.toLowerCase(c));
                capital = false;
            }
        }
        return sb.toString();
    }

    public static String hump(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            final char c = str.charAt(i);
            if (Character.isUpperCase(c) && i != 0) {
                sb.append("_");
            }
            sb.append(Character.toUpperCase(c));
        }
        return sb.toString();
    }

    public static String humpAndRemove(String str, String suffix) {
        return remove(hump(str), suffix);
    }
}
