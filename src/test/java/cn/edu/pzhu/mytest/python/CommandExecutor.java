package cn.edu.pzhu.mytest.python;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * CommandExecutor
 *
 * @author zhangcz
 * @since 20230824
 */
public class CommandExecutor {
    public static void main(String[] args) {

        String startPath = "/Users/zhangchao/Desktop/scripts/login/loginLauncher.py";
        String airPath = "/Users/zhangchao/Desktop/scripts/login/login.air/login.py";
        String logPath = "/private/var/folders/pk/qy79qp8d7vg8_0p043s0z1mr0000gn/T/AirtestIDE/scripts/log";

        String command = String.format("python3 %s %s --device android:///10.199.226.241:5555 --log %s --name 828888197406185702 --password  l", startPath, airPath, logPath); // 这里可以替换为你的命令行脚本
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", command); // 对于Windows使用 "cmd", "/c", command

        try {
            Process process = processBuilder.start();

            // 读取命令行输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // 等待命令执行完成
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
