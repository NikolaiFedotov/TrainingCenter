package com.trainingcenter;

import java.time.Duration;
import java.util.Arrays;

public enum Curriculum {
    JavaDeveloper("Java Developer", Module.Java, Module.JDBC, Module.Spring),
    AQE("AQE", Module.TestDesign, Module.PageObject, Module.Selenium),
    J2EEDeveloper("J2EE Developer", JavaDeveloper, AQE);

    private final String programName;
    private final long programDuration;
    private Module module;

    public String getProgramName() {
        return programName;
    }

    public long getProgramDuration() {
        return programDuration;
    }

    public Module getModule() {
        return module;
    }

    Curriculum(String programName, Module ... modules) {
        this.programName = programName;
        this.programDuration = Arrays.stream(modules).mapToInt(m-> (int) m.getModuleDuration()).sum();
    }

    Curriculum(String programName, Curriculum ... curriculums) {
        this.programName = programName;
        this.programDuration = Arrays.stream(curriculums).mapToInt(c-> (int) c.getProgramDuration()).sum();
    }

    public enum Module {
        Java("Java", Duration.ofHours(16)),
        JDBC("JDBC", Duration.ofHours(24)),
        Spring("Spring", Duration.ofHours(16)),
        TestDesign("Test Design", Duration.ofHours(10)),
        PageObject("Page Object", Duration.ofHours(16)),
        Selenium("Selenium", Duration.ofHours(16));

        private final String moduleName;
        private final Duration moduleDuration;

        Module(String moduleName, Duration moduleDuration) {
            this.moduleName = moduleName;
            this.moduleDuration = moduleDuration;
        }

        public String getModuleName() {
            return moduleName;
        }

        public long getModuleDuration() {
            return moduleDuration.toHours();
        }
    }
}
