#PROJECTNAME: KIDS LEARN

##DESCRIPTION: 
The app features an elegant and intuitive UI, enabling seamless navigation between screens. 
It includes fun and educational content for kids, 
providing parents with valuable tips to encourage their child's growth and exploration.
The application follows the MVVM architecture, ensuring maintainable and scalable code.
Data is locally stored in JSON format for efficient access and easy updates.

###SETUP:

INCLUDE THESE DEPENDENCIES 

implementation ("androidx.compose.material:material-icons-extended:1.5.0")
implementation ("androidx.navigation:navigation-compose:2.5.3")
implementation ("androidx.room:room-runtime:2.5.1")
kapt ("androidx.room:room-compiler:2.5.1")
implementation ("com.google.code.gson:gson:2.10")

plugins {
id("kotlin-kapt")
}