#PI-oneer 

<p align="center">
<img src="https://github.com/caaguirreec/PI-oneer/blob/master/gitimages/logo.png" width="300"><br>
"The miracle of the creation is not the creation itself, but the joy of creating it." 
</p>

Magical and colaborative project full of imagination, design, and happiness. 
The project represents several years of wishing, desiring and hoping a big meeting between ideas,
and their acomplishment despite the dispersed environment of us, the developers.

##PI- The one explorer robot
This project is designed to explore and monitor spaces with a beautiful and autonomous robot-car called PI-oneer. The main goal is to control the PI-oneer through internet while it returns data acquired from it's environment using a wide set of sensors. Moreover, the PI-oneer must have the capability to self explore the environment around it using machine learning algorithms.
To achieve it, the PI-oneer is equiped with a Raspberry PI as it's brain and it has two engines to control the navigation. As for the sensors, it holds a HD camera, Microphone, Ultrasound, among others.

##Team 
<p align="center">
<img src="https://raw.githubusercontent.com/caaguirreec/PI-oneer/master/gitimages/Team.jpg" width="500">
</p>
##Stage 1 (Completed!)

In this stage, the mechanical components are assembled, and the first prototype is ready to run. Its movements (forward, backward, left,right) are controlled by the keyboard of a user through a web browser. The server, all written in JavaScript using Nodejs is hosted in the raspberry, which is the brain of Pioneer. Once a key (w,a,s,d) is pressed (using the standard of most videogames), the server employs REST webservices to public the order to the client, also hosted in the raspberry and written in java. This client is always listenning to the server and when an order comes from it, a signal to the engines is sent, accordingly. The stage 1 is completed and tunned for "real time" perception. 
<p align="center">
<table>
<tr><td><img src="https://github.com/caaguirreec/PI-oneer/blob/master/gitimages/20160527_154540.jpg" width="300"alt="Version 1"></td>
<td><img src="https://github.com/caaguirreec/PI-oneer/blob/master/gitimages/pioneerV2/20160916_095802.jpg" width="350" alt="Version 2"></td></tr>
</table>
</p>
###Fig 2 This picture shows the iteration changes between v1(left) and v2(right) of Pioneer.
<br>
The most significant changes are related with the inclusion of the Ultrasonic sensor in the front, the improvement of the battery (1200mAh to 25mAh) giving it more power autonomy, a camera with two servo-engines to give it two degrees of freedom,  a new PCB circuit that controls 5V outputs to servo-engines and 5V input from the ultrasonic sensor and also two programable state leds. Besides, this new PCB exposes more 5V and GRD pins in order to be able to attach future sensors.
Because the new PCB requires space, the Raspberry Pi is now higher than version 1, giving the Pioneer more space and electronic readability. 
<p align="center">
<table>
<tr><td><img src="https://github.com/caaguirreec/PI-oneer/blob/master/gitimages/pioneerV2/20160916_095809.jpg" width="300"alt="Version 1"></td>
<td><img src="https://github.com/caaguirreec/PI-oneer/blob/master/gitimages/pioneerV2/20160916_095825.jpg" width="350" alt="Version 2"></td>
<td><img src="https://github.com/caaguirreec/PI-oneer/blob/master/gitimages/pioneerV2/20160916_095916.jpg" width="350" alt="Version 2"></td></tr>
</table>
</p>
###Fig 3 More pictures of Pioneer v2. 
##Stage 2
Video acquisition using the camera and video streaming through a web browser. The two servomotors attached to the camera are controlled by the analogous movement of the mouse in the web browser video window. By doing so, the robot camera is controlled with a movement alike the one of a FPV video camera. 
##Stage 3
Autonomous movement employing ultrasonic sensors to detect obstacles and environment variables acquisition using temperature sensor, humidity sensor, sound sensor (microphone) and a little speaker which can bark as a beautiful pet. 
##Stage 4
Objects recognition using machine learning algorithms, environment 3D mapping, database info recolection and voice commands response. 

