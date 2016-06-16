# PI-oneer 

<p align="center">
<img src="https://github.com/caaguirreec/PI-oneer/blob/master/gitimages/logo.png" width="300">
>"The miracle of the creation is not the creation itself, but the joy of creating it." 
</p>

Magic and colaborative project full of imagination, design, and happiness. 
The project represents several years of wishing, desiring and hoping a big meeting between ideas,
and the realization despite the dispersed environment of us, the developers.

#PI- The one explorer robot
This project is designed to explore and monitor spaces with a beautiful and autonomous robot-car called PI-oneer. The main goal is to control the PI-oneer through internet while it returns data acquired from it's environment using a wide set of sensors. Moreover, the PI-oneer must have the capability to self explore the environment around it using machine learning algorithms.
To this, the PI-oneer is equiped with a Raspberry PI as it's brain and it has two engines to control the navigation. As for the sensors, it holds a HD camera, Microphone and Ultrasound sensors, among others.

#Team 

![Alt text](https://raw.githubusercontent.com/caaguirreec/PI-oneer/master/gitimages/team.jpg "PI-oneer team")
#Stage 1 (Completed!)

In this stage, the mechanical components are assembled, and the first prototype is ready to run. Its movements (forward, backward, left,right) are controlled by the keyboard of a client through a web browser. The server, all written in JavaScript using Nodejs is hosted in the raspberry, which is the brain of Pioneer. Once a key (w,a,s,d) is pressed (using the standard of most videogames), the server employs a REST webservices to public the order to the client, also hosted in the raspberry and written in java. This client is always listenning to the server and when an order comes from it, a signal to the engines is sent, according. The stage 1 is completed and tunned for "real time" perception. 
![Alt text](https://github.com/caaguirreec/PI-oneer/blob/master/gitimages/20160527_154540.jpg "PI-oneer stage 1 completed!")
#Stage 2
Video acquisition using the camera and video streaming through a web browser. The two servomotors attached to the camera are controlled by the analogous movement of the mouse in the web browser video window. By doing so, the robot camera is controlled with a movement alike the one of a FPV video camera. 
#Stage 3
Autonomous movement employing ultrasonic sensors to detect obstacles and environment variables acquisition using temperature sensor, humidity sensor, sound sensor (microphone) and a little speaker which can bark as a beautiful pet. 
#Stage 4
Objects recognition using machine learning algorithms, environment 3D mapping, database info recolection and voice commands response. 

