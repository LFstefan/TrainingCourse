import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The interface Table.
 *
 * @param <T> the type parameter
 */
public interface Table<T> {
    /**
     * Gets sql.
     *
     * @return the sql
     */
    String getSql();

    /**
     * Gets args.
     *
     * @return the args
     */
    ArrayList<Object> getArgs();

    /**
     * Gets where.
     *
     * @return the where
     */
    ArrayList<String> getWhere();


    /**
     * Gets modified.
     *
     * @return the modified
     */
    ArrayList<String> getModified();

    /**
     * Sets modified.
     *
     * @param modified the modified
     */
    void setModified(ArrayList<String> modified);

    /**
     * Sets sql.
     *
     * @param sql the sql
     */
    void setSql(String sql);

    /**
     * Sets where.
     *
     * @param where the where
     */
    void setWhere(ArrayList<String> where);

    /**
     * Sets args.
     *
     * @param args the args
     */
    void setArgs(ArrayList<Object> args);

    /**
     * Gets self.
     *
     * @return the self
     */
    T getSelf();

    /**
     * Gets table name.
     *
     * @return the table name
     */
    String getTableName();

    /**
     * Select t.
     *
     * @param fields the fields
     * @return the t
     */
    T select(String... fields);

    /**
     * Update t.
     *
     * @return the t
     */
    T update();

    /**
     * Set t.
     *
     * @param field the field
     * @param value the value
     * @return the t
     */
    T set(String field, Object value);

    /**
     * Select asterisk t.
     *
     * @return the t
     */
    T selectAsterisk();

    /**
     * Where t.
     *
     * @return the t
     */
    T where();

    /**
     * Concat t.
     *
     * @param table the table
     * @return the t
     */
    T concat(Table<T> table);

    /**
     * Order by t.
     *
     * @param fields the fields
     * @return the t
     */
    T orderBy(String... fields);

    /**
     * Order by desc t.
     *
     * @param fields the fields
     * @return the t
     */
    T orderByDesc(String... fields);

    /**
     * Pagination t.
     *
     * @param offset the offset
     * @param count  the count
     * @return the t
     */
    T pagination(long offset, int count);

    /**
     * Like t.
     *
     * @param field the field
     * @param value the value
     * @return the t
     */
    T like(String field, String value);

    /**
     * Equal t.
     *
     * @param field the field
     * @param value the value
     * @return the t
     */
    T equal(String field, Object value);

    /**
     * Not equal t.
     *
     * @param field the field
     * @param value the value
     * @return the t
     */
    T notEqual(String field, Object value);

    /**
     * Less than t.
     *
     * @param field the field
     * @param value the value
     * @return the t
     */
    T lessThan(String field, Object value);

    /**
     * Greater than t.
     *
     * @param field the field
     * @param value the value
     * @return the t
     */
    T greaterThan(String field, Object value);

    /**
     * Le t.
     *
     * @param field the field
     * @param value the value
     * @return the t
     */
    T le(String field, Object value);

    /**
     * Ge t.
     *
     * @param field the field
     * @param value the value
     * @return the t
     */
    T ge(String field, Object value);

    /**
     * In t.
     *
     * @param field  the field
     * @param values the values
     * @return the t
     */
    T in(String field, Collection<?> values);


    /**
     * Compose with and t.
     *
     * @return the t
     */
    T composeWithAnd();

    /**
     * Compose with or t.
     *
     * @return the t
     */
    T composeWithOr();

    /**
     * Query collection.
     *
     * @param <C>       the type parameter
     * @param template  the template
     * @param rowMapper the row mapper
     * @return the collection
     */
    <C> Collection<C> query(JdbcTemplate template, RowMapper<C> rowMapper);

    /**
     * Query for object c.
     *
     * @param <C>      the type parameter
     * @param template the template
     * @param clazz    the clazz
     * @return the c
     */
    <C> C queryForObject(JdbcTemplate template, Class<C> clazz);

    /**
     * Execute.
     *
     * @param template the template
     */
    void execute(JdbcTemplate template);

}
