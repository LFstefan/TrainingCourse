import javax.validation.constraints.NotNull;

public class DSL {

    public static <T> T selectFrom(Table<T> table, @NotNull String... fields) {
        return table.select(fields);
    }

    public static <T> T selectAsterisk(Table<T> table) {
        return table.selectAsterisk();
    }

    public static String count(String field) {
        return String.format("COUNT( %s )", field);
    }

    public static String max(String field) {
        return String.format("MAX( %s )", field);
    }

    public static String min(String field) {
        return String.format("MIN( %s )", field);
    }

    public static <T> T update(Table<T> table) {
        return table.update();
    }

}
