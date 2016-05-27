# PI-oneer 

#PI- The one explorer robot
![Alt text](https://github.com/caaguirreec/PI-oneer/blob/master/gitimages/logo.png "PI-oneer")

>"The miracle of the creation is not the creation itself, but the joy of creating it." 

Magic and colaborative project full of imagination, design, and happiness. 
The project represents several years of wishing, desiring and hoping a big meeting between ideas,
and the realization despite the dispersed environment of us, the developers.
#Team 

![Alt text](https://raw.githubusercontent.com/caaguirreec/PI-oneer/master/gitimages/team.jpg "PI-oneer team")
#Stage 1 (Completed!)

In this stage, the mechanical components are assembled, and the first prototype is ready to run. Its movements (forward, backward, left,right) are controlled by the keyboard of a client through a web browser. The server, all written in JavaScript using Nodejs is host in the raspberry, which is the brain of Pioneer. Once a key (w,a,s,d) is pressed (using the standard of most videogames), the server employees a rest webservices to public the order to the client, also host in the raspberry and written in java. This client is always listenning the server and when an order is comming from it, a signal to the engines is sent, according. The stage 1 is completed and tunned for "real time" perception. 
![Alt text](https://github.com/caaguirreec/PI-oneer/blob/master/gitimages/20160527_154540.jpg "PI-oneer stage 1 completed!")
#Stage 2
Video acquisition using the camera, video streaming through a web browser, autonomous movement employing ultrasonic sensors to detect obstacles and environment variables acquisition using temperature sensor, humidity sensor, sound sensor (microphone) and a little speaker which can bark as a beautiful pet. 

#Stage 3

Objects recognition using machine learning algorithms, environment 3D mapping, database info recolection, real time camera movements using a servoengine combination coupled to the web browser in order to be moved using the mouse, voice commands response. 

