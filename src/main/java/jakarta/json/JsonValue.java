package jakarta.json;

/**
 * Dummy interface to be able to make sure that
 * the classes run correctly after being updated by Javassist.
 *
 */
public interface JsonValue {

    public enum ValueType {
        ARRAY,
        OBJECT,
        STRING,
        NUMBER,
        TRUE,
        FALSE,
        NULL
    }
}
