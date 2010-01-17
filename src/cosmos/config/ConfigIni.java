/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cosmos.config;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
/**
 *
 * @author masch
 */
public class ConfigIni extends Config {

    protected Properties cfg;

    public ConfigIni(String file) throws IOException
    {
        FileInputStream cfg_file = new FileInputStream(file);
        Properties config = new Properties();
        config.load(cfg_file);
        this.cfg = config;
    }

    @Override
    public String get(String key) {
        return this.cfg.getProperty(key);
    }

    @Override
    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(this.get(key));
    }

    @Override
    public float getFloat(String key) {
        return Float.parseFloat(this.get(key));
    }

    @Override
    public int getInt(String key) {
        return Integer.parseInt(this.get(key));
    }

}
