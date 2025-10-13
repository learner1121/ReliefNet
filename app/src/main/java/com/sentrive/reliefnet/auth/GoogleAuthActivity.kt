package com.sentrive.reliefnet.auth

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.sentrive.reliefnet.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GoogleAuthActivity: ComponentActivity() {

    private lateinit var firebaseAuthManager: FirebaseAuthManager
    private lateinit var credentialManager: CredentialManager
    private val TAG = "GoogleAuthActivity"// Tag for logging

    override fun onCreate(savedInstanceState: Bundle?) {    // Called when Activity is created
        super.onCreate(savedInstanceState)
        firebaseAuthManager = FirebaseAuthManager(this) // Init FirebaseAuth manager
        credentialManager = CredentialManager.create(this)  // Init Credential Manager

        // Start Google Sign-In immediately (instead of waiting for button click)
        startGoogleSignIn()
    }

    private fun startGoogleSignIn(){
        // Instantiate a Google sign-in request
        val googleIdOption = GetGoogleIdOption.Builder()
            // Your server's client ID, not your Android client ID.
            .setServerClientId(getString(R.string.default_web_client_id))
            // Only show accounts previously used to sign in.
            .setFilterByAuthorizedAccounts(false)
            .build()

// Create the Credential Manager request
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
Log.d("Credential","$request")
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val result = credentialManager.getCredential(this@GoogleAuthActivity, request)

                //Debug
                Log.d("Result","$result")
                handleSignIn(result.credential) // Handle credential after success
            } catch (e: GetCredentialException) {
                Log.e(TAG, "Google Sign-In failed: ${e.localizedMessage}")
            }
        }
    }

    private fun handleSignIn(credential: Credential) {
        // Check if credential is of type Google ID
        if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            // Create Google ID Token
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)

            // Sign in to Firebase with using the token
            firebaseAuthManager.firebaseAuthWithGoogle(googleIdTokenCredential.idToken)
        } else {
            Log.w(TAG, "Credential is not of type Google ID!")
        }
    }

}