package cosmos.control;

import cosmos.connect.ConnectServer;
import cosmos.login.LoginServer;
import java.util.Properties;
import java.io.FileInputStream;
import cosmos.utils.log.Logger;
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
    private boolean       start_cs = false;
    private LoginServer   ls;
    private boolean       start_ls = false;

    public ControlServer(String cfg) throws java.io.IOException
    {
        // threading stuff
        this.setName("ControlServer");
        // load the configuration
        FileInputStream cfg_file = new FileInputStream(cfg);
        Properties config = new Properties();
        config.load(cfg_file);


        this.start_cs = Boolean.parseBoolean(config.getProperty("connectsrv.start"));
        this.start_ls = Boolean.parseBoolean(config.getProperty("loginsrv.start"));

        //// create the servers
        // connect server
        if(this.start_cs)
        {
            int consrv_port = Integer.parseInt(config.getProperty("connectsrv.port"));
            this.cs = new ConnectServer(consrv_port);
        }
        // login server
        if(this.start_ls)
        {
            int lgnsrv_port = Integer.parseInt(config.getProperty("loginsrv.port"));
            this.ls = new LoginServer(lgnsrv_port);
        }
    }

    public void run()
    {
        // at first.. start all servers
        if(this.start_cs)
        {
            Logger.status("starting ConnectServer...");
            this.cs.start();
        }
        if(this.start_ls)
        {
            Logger.status("starting LoginServer...");
            this.ls.start();
        }

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
            if(this.start_cs && !this.cs.isAlive())
            {
                Logger.error("ConnectServer seems to be dead.. restarting!");
                this.cs.start();
            }
            // check ls
            if(this.start_ls && !this.ls.isAlive())
            {
                Logger.error("LoginServer seems to be dead.. restarting!");
                this.ls.start();
            }

        }
    }
}
