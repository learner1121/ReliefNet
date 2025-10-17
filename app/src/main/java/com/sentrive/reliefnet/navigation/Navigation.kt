package com.sentrive.reliefnet.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sentrive.reliefnet.userInterface.BookingScreen1
import com.sentrive.reliefnet.userInterface.BookingScreenRecord
import com.sentrive.reliefnet.userInterface.DiscoverScreen
import com.sentrive.reliefnet.userInterface.DoctorChatScreen
import com.sentrive.reliefnet.userInterface.DoctorRegistrationScreen
import com.sentrive.reliefnet.userInterface.HomePage
import com.sentrive.reliefnet.userInterface.LinearProgress
import com.sentrive.reliefnet.userInterface.LoginScreen
import com.sentrive.reliefnet.userInterface.ProfileScreen
import com.sentrive.reliefnet.userInterface.RelieScreen
import com.sentrive.reliefnet.userInterface.SplashScreen
import com.sentrive.reliefnet.userInterface.MentalHealthSupport
import com.sentrive.reliefnet.userInterface.ProfessionalLoginScreen
import com.sentrive.reliefnet.userInterface.RelieChat

@Composable
fun Navigation(navHostController: NavHostController){
    NavHost(navHostController,"SplashScreen"){
        composable("Home") { HomePage(navHostController) }
        composable("LoginScreen") { LoginScreen(navHostController) }
        composable("LinerarProgress") { LinearProgress(navHostController) }
        composable("DiscoverScreen") { DiscoverScreen(navHostController) }
        composable("ProfileScreen") { ProfileScreen(navHostController) }
        composable("SplashScreen") { SplashScreen(navHostController) }
        composable("MentalHealthSupport") { MentalHealthSupport(navHostController) }
        composable("BookingScreen") { BookingScreen1(navHostController) }
        composable("BookingScreenRecord") { BookingScreenRecord(navHostController) }
        composable("RelieScreen") { RelieScreen() }
        composable("AdvanceBooking") { BookingScreenRecord(navHostController) }


        //test
        composable("DoctorRegistrationScreen") { RelieChat() }
    }
}