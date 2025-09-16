# Java Android Quiz App (2024)

* An android quiz app written in Java that allows you to choose 3 quiz categories
* After selecting a category, 5 questions will be asked for this category - each with 4 options
* You're able to scroll up and down the options using your fingers if options are cut off at bottom of screen
* When the quiz is finished, a results page shows a summary of your correct answers

### How to create and run the program
* On Android studio, click file, new, new project
* Click "Empty views activity"
    * In options menu:
        * Ensure Name is "QuizApp"
		* Ensure Package name is "com.lawrence123.quizapp"
		* Choose a good save location
		* Ensure language is Java
		* Keep other options the same
		* Click Finish
		
* Wait for the gradle process to sync up (if applicable)

* Sometimes the Agp and activity version is out of date:
    * Open the gradle/libs.versions.toml file
	* Change "agp" version to "8.4.0"
	* Change first "activity" version line to "1.8.0"

* Remove the contents from app/src in your android studio project
	
* Copy the contents from "app/src" from Github to "app/src" in your android studio project

* Either restart android studio or press yes if it asks for a gradle sync

* I recommend selecting a "Pixel 4 API 28" Emulator from the Device Manager menu

* Ensure this Pixel device is selected at the top in the main Android studio window

* Then run the project

* A phone emulator should show and the app should run

### Help

* When loading program on emulator if you get "waiting for all devices to come online" message showing for a very long time:
    * On device manager, wipe data for the device you want to use
	* Then restart your computer and android studio

* Also you can use logcat to help debug errors if needed