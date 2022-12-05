import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseViewModel : ViewModel() {
    companion object {
        val auth = Firebase.auth
    }
    val auth = Firebase.auth

    fun getUser() : FirebaseUser? {
        return auth.currentUser
    }

    fun isAuthenticated() : Boolean {
        return auth.currentUser != null
    }
}