# Mobile-Quiz
<h2>INTRODUCTION</h2>

Its a quizzing app made over bluetooth and covers questions on topics like reasoning,aptitude,verbal ablility and few puzzles . The USP of this app is that it is able to connect around 4 devices to the main server device. The app is broadly divided into two segments:-Two Devices and Multipe Devices.

<h2>ABOUT CODE</h2>

The App uses Android's Bluetooth API and establishes RFCOMM channels. The server listens for various clients using a unique UUID and the client having the same UUID is connected to the server. In Multiple Devices server creates 4 RFCOMM channels with 4 different UUIDs and then each connection is maintained on a different thread by the server so that if anyhow connection with a particular client fails then the connection with other clients is uninterrupted.

<h2>INSTRUCTIONS</h2>

<h4>While connecting as server in two device</h4> Select any number questions from any category by selecting the checkbox beside each question and then set time in dialog box and go connecting to clients.As server is also a participant , it can't view questions ,it can just randomly select them.
<h4>While connecting as client in two devices</h4> You have to wait till server selects and then along with server click on discover clients.
Both server and client would be a part game.

<h4>In multiple devices</h4> Server will select questions and later would act as score board but will not be a part of quiz.As server does not participate in quiz therefore it can also view question. The quiz would be between all the clients

<h2>SCREENSHOTS</h2>
<img src="https://raw.githubusercontent.com/sdsmdg/Mobile-Quiz/1c1d413897edc614418e063bbb01078fe75bb2ae/app/src/main/assets/twodevice_screenshots/Screenshot_2016-06-30-00-15-24.png" width="300">
<img src="https://raw.githubusercontent.com/sdsmdg/Mobile-Quiz/1c1d413897edc614418e063bbb01078fe75bb2ae/app/src/main/assets/twodevice_screenshots/Screenshot_2016-06-30-00-15-43.png" width="300">
  <img src="https://raw.githubusercontent.com/sdsmdg/Mobile-Quiz/1c1d413897edc614418e063bbb01078fe75bb2ae/app/src/main/assets/twodevice_screenshots/Screenshot_2016-06-30-00-16-03.png" width="300">
    <img src="https://raw.githubusercontent.com/sdsmdg/Mobile-Quiz/1c1d413897edc614418e063bbb01078fe75bb2ae/app/src/main/assets/twodevice_screenshots/Screenshot_2016-06-30-00-16-13.png" width="300">
    <img src="https://raw.githubusercontent.com/sdsmdg/Mobile-Quiz/1c1d413897edc614418e063bbb01078fe75bb2ae/app/src/main/assets/twodevice_screenshots/Screenshot_2016-06-30-00-17-08.png" width="300">
    <img src="https://raw.githubusercontent.com/sdsmdg/Mobile-Quiz/1c1d413897edc614418e063bbb01078fe75bb2ae/app/src/main/assets/twodevice_screenshots/Screenshot_2016-06-30-00-18-07.png" width="300">
    <img src="https://raw.githubusercontent.com/sdsmdg/Mobile-Quiz/1c1d413897edc614418e063bbb01078fe75bb2ae/app/src/main/assets/twodevice_screenshots/Screenshot_2016-06-30-00-18-17.png" width="300">
<img src ="https://raw.githubusercontent.com/sdsmdg/Mobile-Quiz/1c1d413897edc614418e063bbb01078fe75bb2ae/app/src/main/assets/multidevice_screenshots/Screenshot_2016-06-30-02-14-03.png" width="300">
