/*
 * OpenMuOnline - a open source mu online server
 * Copyright (C) 2009 Mark Schmale
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package cosmos.control;

import cosmos.connect.ConnectServer;
import cosmos.login.LoginServer;
import cosmos.config.Config;
import cosmos.config.ConfigIni;
import cosmos.utils.log.Logger;
/**
 * Main server thread
 *
 * the ControlsServer is used to control the different servers
 *  - start / stop / restart them
 *  - supply information
 *  - monitoring
 *
 * @author Mark Schmale <ma.schmale@googlemail.com>
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
        /*FileInputStream cfg_file = new FileInputStream(cfg);
        Properties config = new Properties();
        config.load(cfg_file);*/
        Config config = new ConfigIni(cfg);

        this.start_cs = config.getBoolean("connectsrv.start");
        this.start_ls = config.getBoolean("loginsrv.start");

        //// create the servers
        // connect server
        if(this.start_cs)
        {
            int consrv_port = config.getInt("connectsrv.port");
            this.cs = new ConnectServer(consrv_port);
        }
        // login server
        if(this.start_ls)
        {
            int lgnsrv_port = config.getInt("loginsrv.port");
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
