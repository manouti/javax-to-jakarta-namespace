package example.jakartaee;

import javax.json.JsonString;
import javax.json.JsonValue;

public class JakartaEESample {

    static JsonValue jsonValue = new MyJsonString();

    public static void main(String[] args) {
        JsonValue jsonV = new MyJsonString();
        System.out.println(((JsonString)jsonV).getString());
    }
}
