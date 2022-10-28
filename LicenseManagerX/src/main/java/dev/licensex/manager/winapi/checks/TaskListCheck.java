package dev.licensex.manager.winapi.checks;

import dev.licensex.manager.winapi.Check;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TaskListCheck extends Check {

    @Override
    public void check() {

        try {
            String line;
            String pidInfo ="";

            Process p =Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");

            BufferedReader input =  new BufferedReader(new InputStreamReader(p.getInputStream()));

            while ((line = input.readLine()) != null) {
                pidInfo+=line;
            }

            input.close();

            if(pidInfo.contains("Wireshark.exe")) {
                flag("Wireshark");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
