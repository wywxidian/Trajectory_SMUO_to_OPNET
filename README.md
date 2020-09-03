[中文版](README_CN.md)

# Summary

   SUMO, as a traffic simulation software, can generate realistic traffic trajectory of vehicle by combining with the real road network provided by OpenStreetMap official website. OPNET, a popular network simulation software, establishes network equipment, communication link and protocol model and simulating traffic transmission to evaluates network performance and optimizes its design by, which has been applied to vehicle network communication simulation well. 
   
   However, the trajectory files generated by SUMO do not directly support OPNET simulation. Therefore, this project uses Java language to develop a tool that can convert trajectory generated by SUMO into OPNET simulation trajectory, aiming to realize the joint simulation of them, and provide a more real simulation scene for the research of VANET. 

# Deployment environment 

```
   Win 7 + java-1.8.0-openjdk-devel.x86_64   
   SUMO-1.6.0
   OpenStreetMap
   OPNET 14.5
```
