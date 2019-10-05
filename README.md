# GTM-Android-Application
Companion app for admins/employees at GTM Home Services. The companion app features a login and registration system, including the ability for a user to reset their password for security reasons or if their password has been forgotten. Reset password requests made prior to authentication will send a code to the user's email and will require the user to enter this code within a two-minute period. On the contrary, reset password requests made following authentication will conveniently only require the user to enter their old password, which they intend to overwrite, as well as their new password.

NOTE: Due to a change in project requirements by the sponsor, this repository has been deprecated and all functionality has been shifted over to the web application. 

![Login Screen](images/login_screen.png?raw=true "Login Screen")
![Navigation Menu](images/navigation_menu.png?raw=true "Navigation Menu")
![Settings Menu](images/settings_menu.png?raw=true "Settings Menu")

## Configuring the Database
To view instructions on how to configure the PHP back end for this project, refer to the README.md under the [following repository](https://github.com/HBedwell24/GTM-Android-Application-Back-End).

## Technologies Used
* **Gson** - deserializes JSON objects to Java objects
* **Hdodenhof Circle Image View** - rounds profile images in the navigation header
* **OAuth2** - authorization standard which provides secure delegated access to Spotify data
* **OkHttp** - handles HTTP network requests
* **Retrofit** - retrieves JSON data from the Spotify API 
* **Sille Bille Dynamic Calendar** - displays a data picker dialog box for scheduling
* **Volley** - manages audio playback via the Spotify Music app
