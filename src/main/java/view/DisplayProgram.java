package view;

import java.io.StringWriter;
import java.util.ArrayList;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import simulator.CPU;

public class DisplayProgram {
    
    private static Mustache cycleTemplate = createCycleTemplate();
    private static final ArrayList<String> cycleHtmls = new ArrayList<String>();

    private static Mustache createCycleTemplate() {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile("templates/cycle.mustache");
        return m;
    }

    public static void addCycleHtml(CPU cpu) {
        cycleHtmls.add(getCycleHtml(cpu));
    }

    private static String getCycleHtml(CPU cpu) {
        StringWriter writer = new StringWriter();
        cycleTemplate.execute(writer, cpu);
        return writer.toString();
    }

    public static ArrayList<String> getCycleHtmls() {
        return cycleHtmls;
    }
}
