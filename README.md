# RecoGenre – mobile app

The following project was implemented as part of the project "Stream Data Processing" (pl. „Przetwarzanie danych strumieniowych”) at the Wrocław University of Science and Technology.
In this repository you can find a mobile application made for the Android platform.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Installing

Clone this repository and import into Android Studio or you could install app using apk file stored [here](https://github.com/2018PZ/Recogenre/tree/master/app/release). Actual release info (also) stored at this directory in [file](https://github.com/2018PZ/Recogenre/blob/master/app/release/output.json).

When app's recording music - should look like:

![HOME PAGE](https://github.com/2018PZ/Recogenre/blob/master/app/src/main/res/drawable/homepage.png?raw=true)

After successfull classification the view change to:

![RESULT PAGE](https://github.com/2018PZ/Recogenre/blob/master/app/src/main/res/drawable/resultpage.png?raw=true)

## Deployment

Generating new apk file from Android Studio:

* Build menu
* Generate Signed APK...
* Fill in the keystore information (you only need to do this once manually and then let Android Studio remember it)

## Built With

* [JSch]( http://www.jcraft.com/jsch/) - JSch allows you to connect to an sshd server and transfer music file
* [Retrofit]( https://square.github.io/retrofit/) - HTTP API as a Java interface (to connect to REST API)
* [Android Ripple Background]( https://github.com/skyfishjy/android-ripple-background) - Used to ripple animation

## Authors

* **Anna Łyjak ** - *Initial work* - [kirjava113](https://github.com/kirjava113)

See also the list of [contributors](https://github.com/2018PZ/Recogenre/graphs/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* [Retrofit tutorial](http://www.vogella.com/tutorials/Retrofit/article.html)
* [Retrofit Android Example Tutorial](https://www.journaldev.com/13639/retrofit-android-example-tutorial)
* [JSch Documentation](http://www.jcraft.com/jsch/)
