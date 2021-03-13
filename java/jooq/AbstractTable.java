import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.*;

public abstract class AbstractTable<T> implements Table<T> {
    private static String SELECT = "SELECT %s FROM %s ";
    private static String UPDATE = "UPDATE %s SET ";
    private static String UPDATE_SET_VALUE = " %s = ? ";
    private static final String PLEASE_HOLDER = " %s ";
    private static String WHERE = " WHERE %s ";
    private static String ORDER_BY = " ORDER BY %s ";
    private static String ORDER_BY_DESC = " ORDER BY %s DESC ";
    private static final String LIKE = " LIKE ? ";
    private static final String EQUAL = " = ? ";
    private static final String NOT_EQUAL = " != ? ";
    private static final String LESS_THAN = " < ? ";
    private static final String GREATER_THAN = " > ? ";
    private static final String LE = " <= ? ";
    private static final String GE = " >= ? ";
    private static String IN = " IN ( %s ) ";
    private static final String AND = " AND ";
    private static final String OR = " OR ";
    private static final String PAGINATION = " LIMIT ?,? ";
    private static final String COMPOSE_WHERE_CONDITION = "( %s )";

    @Override
    public <C> Collection<C> query(JdbcTemplate template, RowMapper<C> rowMapper) {
        return template.query(String.format(getSql(), concatWhere()), getArgs().toArray(), rowMapper);
    }

    @Override
    public <C> C queryForObject(JdbcTemplate template, Class<C> clazz) {
        return template.queryForObject(String.format(getSql(), concatWhere()), getArgs().toArray(), clazz);
    }

    @Override
    public void execute(JdbcTemplate template) {
        template.update(String.format(getSql(), String.join(COMMA, getModified()), concatWhere()), getArgs().toArray());
    }

    @Override
    public T orderBy(String... fields) {
        setSql(getSql() + String.format(ORDER_BY, String.join(COMMA, fields)));
        return getSelf();
    }

    @Override
    public T orderByDesc(String... fields) {
        setSql(getSql() + String.format(ORDER_BY_DESC, String.join(COMMA, fields)));
        return getSelf();
    }

    @Override
    public T pagination(long offset, int count) {
        setSql(getSql() + PAGINATION);
        getArgs().add(offset);
        getArgs().add(count);
        return getSelf();
    }

    @Override
    public T composeWithAnd() {
        ArrayList<String> where = getWhere();
        if (CollectionUtils.isEmpty(where)) return getSelf();
        String composeWhere = String.format(COMPOSE_WHERE_CONDITION, String.join(AND, where));
        where.clear();
        where.add(composeWhere);
        setWhere(where);
        return getSelf();
    }

    @Override
    public T composeWithOr() {
        ArrayList<String> where = getWhere();
        if (CollectionUtils.isEmpty(where)) return getSelf();
        String composeWhere = String.format(COMPOSE_WHERE_CONDITION, String.join(OR, where));
        where.clear();
        where.add(composeWhere);
        setWhere(where);
        return getSelf();
    }

    @Override
    public T concat(Table<T> table) {
        setSql(getSql() + table.getSql());
        getWhere().addAll(table.getWhere());
        getArgs().addAll(table.getArgs());
        return getSelf();
    }

    @Override
    public T like(String field, String value) {
        like(getWhere(), getArgs(), field, value);
        return getSelf();
    }

    @Override
    public T equal(String field, Object value) {
        equal(getWhere(), getArgs(), field, value);
        return getSelf();
    }

    @Override
    public T notEqual(String field, Object value) {
        notEqual(getWhere(), getArgs(), field, value);
        return getSelf();
    }

    @Override
    public T lessThan(String field, Object value) {
        lessThan(getWhere(), getArgs(), field, value);
        return getSelf();
    }

    @Override
    public T greaterThan(String field, Object value) {
        greaterThan(getWhere(), getArgs(), field, value);
        return getSelf();
    }

    @Override
    public T le(String field, Object value) {
        le(getWhere(), getArgs(), field, value);
        return getSelf();
    }

    @Override
    public T ge(String field, Object value) {
        ge(getWhere(), getArgs(), field, value);
        return getSelf();
    }

    @Override
    public T in(String field, Collection<?> values) {
        in(getWhere(), getArgs(), field, values);
        return getSelf();
    }

    @Override
    public T select(String... fields) {
        setSql(getSql() + String.format(SELECT, String.join(COMMA, fields), getTableName()));
        return getSelf();
    }

    @Override
    public T selectAsterisk() {
        setSql(getSql() + String.format(SELECT, ASTERISK, getTableName()));
        return getSelf();
    }

    @Override
    public T update() {
        setSql(getSql() + String.format(UPDATE, getTableName()) + PLEASE_HOLDER);
        return getSelf();
    }

    @Override
    public T set(@NotNull String field, Object value) {
        if (Objects.nonNull(value)) {
            getModified().add(String.format(UPDATE_SET_VALUE, field));
            getArgs().add(value);
        }
        return getSelf();
    }

    @Override
    public T where() {
        setSql(getSql() + PLEASE_HOLDER);
        return getSelf();
    }

    private void like(List<String> where, ArrayList<Object> args, String field, String value) {
        if (emptyCondition(value)) return;
        where.add(field + LIKE);
        args.add(likeConcat(value));
    }

    private void equal(List<String> where, ArrayList<Object> args, String field, Object value) {
        if (emptyCondition(value)) return;
        where.add(field + EQUAL);
        args.add(value);
    }

    private void notEqual(List<String> where, ArrayList<Object> args, String field, Object value) {
        if (emptyCondition(value)) return;
        where.add(field + NOT_EQUAL);
        args.add(value);
    }

    private void lessThan(List<String> where, ArrayList<Object> args, String field, Object value) {
        if (emptyCondition(value)) return;
        where.add(field + LESS_THAN);
        args.add(value);
    }

    private void greaterThan(List<String> where, ArrayList<Object> args, String field, Object value) {
        if (emptyCondition(value)) return;
        where.add(field + GREATER_THAN);
        args.add(value);
    }

    private void le(List<String> where, ArrayList<Object> args, String field, Object value) {
        if (emptyCondition(value)) return;
        where.add(field + LE);
        args.add(value);
    }

    private void ge(List<String> where, ArrayList<Object> args, String field, Object value) {
        if (emptyCondition(value)) return;
        where.add(field + GE);
        args.add(value);
    }

    private void in(List<String> where, ArrayList<Object> args, String field, Collection<?> values) {
        if (emptyCondition(values)) return;
        where.add(field + String.format(IN, buildPreSqlArgs(values.size())));
        args.addAll(values);
    }

    private String likeConcat(String arg) {
        return PERCENT_SIGN + arg + PERCENT_SIGN;
    }

    private String concatWhere() {
        ArrayList<String> where = getWhere();
        return CollectionUtils.isEmpty(where) ? StringUtils.EMPTY : String.format(WHERE, String.join(AND, where));
    }

    /**
     * 构建预编译sql参数，结果样式为：?,?,?...,?
     */
    private String buildPreSqlArgs(int markSize) {
        String[] str = new String[markSize];
        Arrays.fill(str, QUESTION_MARK);
        return String.join(COMMA, str);
    }

    private <U> boolean emptyCondition(U u) {
        if (u instanceof String) {
            return StringUtils.isBlank((String) u);
        }
        if (u instanceof Collection) {
            return CollectionUtils.isEmpty((Collection<?>) u);
        }
        return Objects.isNull(u);
    }

}
