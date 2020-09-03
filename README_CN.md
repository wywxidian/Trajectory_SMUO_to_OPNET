# 概述
   Simulation of Urban Mobility（SUMO）作为一款交通仿真软件，其通过结合OpenStreetMap官网提供的真实道路网可以生成逼真的车辆交通轨迹。OPNET是一款流行的网络仿真软件，它通过建立网络设备、通信链路和协议模型并模拟流量的传输，来评估网络性能并对其进行设计优化，可以被很好应用于车辆网通信仿真。然而，SUMO生成的轨迹文件不直接提供对OPNET仿真的支持。为此，本项目采用JAVA语言开发了一款可以实现SUMO生成轨迹转换为OPNET仿真轨迹的工具，旨在实现二者的联合仿真以为车联网的研究提供更加真实的仿真场景。

# 部署环境
     Win 7 + java-1.8.0-openjdk-devel.x86_64   
     SUMO-1.6.0
     OpenStreetMap
     OPNET 14.5