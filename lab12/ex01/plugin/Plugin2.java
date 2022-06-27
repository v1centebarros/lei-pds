package lab12.ex01.plugin;

import lab12.ex01.plugin.IPlugin;

public class Plugin2 implements IPlugin {

    @Override
    public void fazQualQuerCoisa() {
        for (int i = 0; i < 100000; i++) {
            System.out.println("ADORO PDS :)");
        }
    }
}
