package com.sentrive.reliefnet.auth

import android.app.Activity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class FirebaseAuthManager(private val activity: Activity) {

    private val TAG = "FirebaseAuthManager"
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)

        auth.signInWithCredential(credential)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Log.d(TAG, "signInWithCredential:success → ${user?.email}")
                    // TODO: Navigate to HomeScreen
                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                }
            }
    }

    fun signOut() {
        auth.signOut()
        Log.d(TAG, "User signed out")
    }

    fun getCurrentUser() = auth.currentUser
}
