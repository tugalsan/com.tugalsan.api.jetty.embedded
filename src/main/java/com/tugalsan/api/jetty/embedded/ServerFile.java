package com.tugalsan.api.jetty.embedded;

import com.tugalsan.api.unsafe.client.TGS_UnSafe;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.*;

public class ServerFile {

    public static void main(String[] args) throws Exception {
        //config
        var port = 9082;
        var shutdown_token = "shutdown_token";

        //create server
        var server = new Server(new TS_JettyThreadPool());
        var connector = new ServerConnector(server);
        connector.setPort(port);
        server.setConnectors(new Connector[]{connector});

        //create handler
        var handler = new ResourceHandler();
        handler.setDirectoriesListed(true);
        handler.setWelcomeFiles(new String[]{"index.html"});
        handler.setResourceBase("src");

        //add handlers
        //http://localhost:9082/shutdown?token=shutdown_token
        var handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{
            new ShutdownHandler(shutdown_token, false, false),
            new DefaultHandler(),
            handler
        });
        server.setHandler(handlers);
//        server.setHandler(handler);

        // Start the server! 🚀
        TGS_UnSafe.run(()->{
        });
        try {
            server.start();
            System.out.println("Server started @ " + port);
        } catch (Exception e) {
            server.stop();
            System.out.println("Server failed @ " + port);
//            TGS_UnSafe.throwIfInterruptedException(e);
            TGS_UnSafe.thrw(e);
        }
        server.join();
    }
}
