package lab12.ex01.plugin;

import lab12.ex01.plugin.IPlugin;

public class Plugin1 implements IPlugin {

    @Override
    public void fazQualQuerCoisa() {
        System.out.println("OLÁ MUNDO!");
    }
}
