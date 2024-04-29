package com.tugalsan.api.jetty.embedded;

import java.nio.file.Path;

public class Run {

    public static void main(String[] args) throws Exception {
        TS_JettyServer.main(8443, Path.of("D:\\dat\\SSL\\tomcat.p12"), "MyPass");
    }

}
