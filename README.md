Issue's Facesd

-Connected to the target VM, address: '127.0.0.1:52532', transport: 'socket'
-Disconnected from the target VM, address: '127.0.0.1:52532', transport: 'socket'
------>Solution: Add the given properties in the application.properties file (spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration)


-GitHub: "failed to connect to GitHub 443 windows/ Failed to connect to GitHub - No Error"
------>Solution: Turn off the Domain network firewall in the firewall setting

-Terminal is not able to be used in IntelliJ
------>Solution: go into setting into IntelliJ search terminal in setting add shell path as given (C:\Windows\System32\WindowsPowerShell\v1.0\powershell.exe)
-remote origin already exists
------->run this command with your required repository link (git remote set-url origin (your repository link))