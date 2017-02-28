package eugenstaab.com.veggieguide.database;

class DatabaseUtils {

    /**
     * Create a list of question marks, e.g. "?, ?" for len = 2.
     * @param len number of parameter placeholders required
     * @return a string concatenation of comma-separated question marks
     */
    static String makePlaceholders(int len) {
        if (len < 1) {
            // It will lead to an invalid query anyway ..
            throw new RuntimeException("No placeholders");
        } else {
            StringBuilder sb = new StringBuilder(len * 2 - 1);
            sb.append("?");
            for (int i = 1; i < len; i++) {
                sb.append(",?");
            }
            return sb.toString();
        }
    }

}
