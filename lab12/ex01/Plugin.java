package lab12.ex01;

import lab12.ex01.plugin.IPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;


abstract class PluginManager {
    public static IPlugin load(String name) throws Exception {
        Class<?> c = Class.forName(name);
        return(IPlugin) c.getDeclaredConstructor().newInstance();
    }
}
public class Plugin {
    public static void main(String[] args) throws Exception {
        File proxyList = new File("/home/vicente/Documents/universidade/lei-pds/out/production/lei-pds/lab12/ex01/plugin");
        ArrayList<IPlugin> plgs = new ArrayList<IPlugin>();
        for(String f: proxyList.list()) {
            if (f.endsWith(".class")) {
                try {
                    plgs.add(PluginManager.load("lab12.ex01.plugin."+ f.substring(0, f.lastIndexOf('.'))));
                } catch (Exception e) {
                    System.out.println("\t" + f + ": Componente ignorado. Não é IPlugin.");
                }
            }
        }
        Iterator<IPlugin> it = plgs.iterator();
        while(it.hasNext()) {
            it.next().fazQualQuerCoisa();
        }
    }
}