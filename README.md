# DishesApp
Stack: MVI, Flow, Coroutines, Room, Nav component, Dagger <br />
[APK](https://github.com/KostyaGig/DishesApp/releases/tag/v1.0)

Main cases:

1. Internet connection is available: fetching the dishes from mock server happens and then they will be saved to the Room database <br />
If all the dishes have deleted "There are no dishes in the database" error message appears and you can tap on "Refresh" button to again fetch the dishes <br />from mock server and put them into the database <br /><br />

2. Internet connection is not available: fetching the dishes from the Room database happens. If there are no dishes in the database <br />
"You're offline" message appears and you can tap on "Refresh" button. When network connection is Available and you tap on "Refresh" the first main case is going<br /> to happen. If the are dishes in the database you see them in the recycler view.

