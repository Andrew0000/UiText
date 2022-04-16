package crocodile8008.ui_text

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _inProgress = MutableStateFlow(true)
    val inProgress: Flow<Boolean> = _inProgress

    private val _field01 = MutableStateFlow<UiText>(UiText.Str("Initial 1"))
    val field01: Flow<UiText> = _field01

    private val _field02 = MutableStateFlow<UiText>(UiText.Str("Initial 2"))
    val field02: Flow<UiText> = _field02

    private val _field03 = MutableStateFlow<UiText>(UiText.Str("Initial 3"))
    val field03: Flow<UiText> = _field03

    init {
        viewModelScope.launch {
            delay(2000)
            _field01.value = UiText.Str("Just string")
        }
        viewModelScope.launch {
            delay(3000)
            _field02.value = UiText.Res(R.string.plain_string)
        }
        viewModelScope.launch {
            delay(4000)
            _field03.value = UiText.Format(R.string.formatted_string, "first", 2, 3f)
            _inProgress.value = false
        }
    }
}