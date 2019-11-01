package example.jakartaee;

import javax.json.JsonString;

public class MyJsonString implements JsonString {

    @Override
    public ValueType getValueType() {
        return ValueType.STRING;
    }
    
    @Override
    public String getString() {
        return "test";
    }
    
    @Override
    public CharSequence getChars() {
        return "test";
    }
}
