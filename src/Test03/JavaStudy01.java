package Test03;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JavaStudy01 {

    public static void main(String[] args) throws Exception {
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine engine = sem.getEngineByName("javascript");

        engine.put("msg","123");
        String str = "var user = {name:'123'};";
        str += "print(user.name);";

        engine.eval(str);
        engine.eval("msg = '123'");

        System.out.println(engine.get("msg"));
    }

}
