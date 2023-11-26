import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.cheesy.otapara.otaparaclient.util.Utils
import com.cheesy.otapara.otaparaclient.viewmodel.GlobalViewModel
import com.cheesy.otapara.otaparaclient.viewmodel.LoginViewModel

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    globalViewModel: GlobalViewModel,
) {
    val globalState by globalViewModel.state.collectAsState() // You know why I am doing this ( ͡° ͜ʖ ͡°) it's bad practice ? my reply: ( ͡° ͜ʖ ͡°)╭∩╮
    Row(modifier = Modifier.fillMaxSize()) {
        Text(text = Utils.decodeJwt(globalState.userMalToken).toString())
    }

}