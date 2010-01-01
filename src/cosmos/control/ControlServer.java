package cosmos.control;

import cosmos.connectserver.ConnectServer;
import cosmos.loginserver.LoginServer;
import java.util.Properties;
import java.io.FileInputStream;
import cosmos.utils.log.Logger;
import java.io.IOException;
/**
 * Main server thread
 *
 * the ControlsServer is used to control the different servers
 *  - start / stop / restart them
 *  - supply information
 *  - monitoring
 *
 * @author Mark Schmale
 */
public class ControlServer extends Thread {

    private ConnectServer cs;
    private LoginServer   ls;

    public ControlServer(String cfg) throws java.io.IOException
    {
        // threading stuff
        this.setName("ControlServer");
        // load the configuration
        FileInputStream cfg_file = new FileInputStream(cfg);
        Properties config = new Properties();
        config.load(cfg_file);
        //// create the servers
        // connect server
        int consrv_port = Integer.parseInt(config.getProperty("connectsrv.port"));
        this.cs = new ConnectServer(consrv_port);
        // login server
        int lgnsrv_port = Integer.parseInt(config.getProperty("loginsrv.port"));
        this.ls = new LoginServer(lgnsrv_port);
    }

    public void run()
    {
        // at first.. start all servers
        Logger.status("starting ConnectServer...");
        this.cs.start();
        Logger.status("starting LoginServer...");
        this.ls.start();

        while(true)
        {
            try {
                // wait a second to safe cpu time
                sleep(1000);
            }
            catch(java.lang.InterruptedException e)
            {
                // oh.. interrupt.. who cares?
            }
            //// check servers
            // check cs
            if(!this.cs.isAlive())
            {
                Logger.error("ConnectServer seems to be dead.. restarting!");
                this.cs.start();
            }
            // check ls
            if(!this.ls.isAlive())
            {
                Logger.error("LoginServer seems to be dead.. restarting!");
                this.ls.start();
            }

        }
    }
}
