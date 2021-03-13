import java.util.ArrayList;

public class TableDsl {
    private static final String TABLE_NAME = "table_name";
    public static final String PRIMARY_KEY = "primary_key";
    public static final String TABLE_FILED = "table_filed";

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends AbstractTable<Builder> {
        private String sql = "";
        private ArrayList<String> where = new ArrayList<>();
        private ArrayList<Object> args = new ArrayList<>();
        private ArrayList<String> modified = new ArrayList<>();

        @Override
        public void setSql(String sql) {
            this.sql = sql;
        }

        @Override
        public void setWhere(ArrayList<String> where) {
            this.where = where;
        }

        @Override
        public void setArgs(ArrayList<Object> args) {
            this.args = args;
        }

        @Override
        public Builder getSelf() {
            return this;
        }

        @Override
        public String getTableName() {
            return TABLE_NAME;
        }

        @Override
        public String getSql() {
            return sql;
        }

        @Override
        public ArrayList<Object> getArgs() {
            return args;
        }

        @Override
        public ArrayList<String> getWhere() {
            return where;
        }

        @Override
        public ArrayList<String> getModified() {
            return modified;
        }

        @Override
        public void setModified(ArrayList<String> modified) {
            this.modified = modified;
        }

        private Builder() {
        }
    }
}
