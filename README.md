# MedibankCodeTestVibhu
The following contains an Android application designed to display News articles by using the newsapi.
The application contains 4 different screens 
1st screen - Headlines - This screen displays the current headlines displaying their Title, author, description and a thumbnail of the article. 
This screen uses a recycler view attached to a LiveData provided by the RoomSQL which stores the headlines fetched by the api according to the user selected sources.

2nd screen - Sources - This screen fetches all the different sources available using the api and displays them to the user, the user can select or remove sources of their 
choice and the selection of the user is saved in RoomSQL.

3rd screen - Saved - This screen shows the user's favourite articles that are saved in RoomSQl and are displayed using LiveData and a custom list adapter which is 
shared by both Saved and Headlines screen.

4th screen - WebView - This screen opens the new articles in a webview hosted inside the application, the user gets an option to save the article so that the 
user can view them later in the saved screen.

The entire application uses Android Jetpack navigation to navigate through different fragments and uses swaps fragments that contain different screens.
