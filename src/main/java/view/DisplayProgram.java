package view;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import simulator.CPU;

public class DisplayProgram {
    private static final DisplayProgram instance = new DisplayProgram();
    private static Mustache cycleTemplate = createCycleTemplate();
    private final ArrayList<String> cycleHtmls = new ArrayList<String>();
    private String programName;
    private String programListing;

    private DisplayProgram() {
    }


    public static DisplayProgram getInstance() {
        return instance;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramListing() {
        return programListing;
    }

    public void setProgramListing(String programListing) {
        this.programListing = programListing;
    }
    private static Mustache createCycleTemplate() {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile("templates/cycle.mustache");
        return m;
    }

    public static void addCycleHtml(CPU cpu) {
        instance.cycleHtmls.add(getCycleHtml(cpu));
    }

    private static String getCycleHtml(CPU cpu) {
        StringWriter writer = new StringWriter();
        try {
            cycleTemplate.execute(writer, cpu).flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    public ArrayList<String> getCycleHtmls() {
        return cycleHtmls;
    }
}
