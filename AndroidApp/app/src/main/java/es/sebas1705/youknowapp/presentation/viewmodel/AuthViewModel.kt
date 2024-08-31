package es.sebas1705.youknowapp.presentation.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    fun signWithGoogleToken(
        tokenId: String,
        onSuccess: () -> Unit = {},
        onError: (String?) -> Unit = {}
    ) {
        val credential = GoogleAuthProvider.getCredential(tokenId, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener {
                if (it.isSuccessful) onSuccess()
                else onError(it.exception?.message)
            }

    }

    fun authWithEmail(
        create: Boolean,
        email: String,
        password: String,
        onSuccess: () -> Unit = {},
        onError: (String?) -> Unit = {}
    ) {
        val task = (if (create) firebaseAuth.createUserWithEmailAndPassword(email, password)
        else firebaseAuth.signInWithEmailAndPassword(email, password))
        task.addOnCompleteListener {
            if (it.isSuccessful) onSuccess()
            else onError(it.exception?.message)
        }
    }

    fun signOut(
        onSuccess: () -> Unit = {}, onError: () -> Unit = {}
    ) {
        firebaseAuth.signOut()
        if (firebaseAuth.currentUser != null) onSuccess()
        else onError()
    }

}