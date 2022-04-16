package crocodile8008.ui_text

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var textView01: TextView
    private lateinit var textView02: TextView
    private lateinit var textView03: TextView
    private lateinit var progressBar: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        observeVM()
    }

    private fun setupView() {
        textView01 = findViewById(R.id.textView01)
        textView02 = findViewById(R.id.textView02)
        textView03 = findViewById(R.id.textView03)
        progressBar = findViewById(R.id.progressBar)
    }

    private fun observeVM() {
        viewModel.field01.observeWhenStarted(this) {
            textView01.setUiText(it)
        }
        viewModel.field02.observeWhenStarted(this) {
            textView02.setUiText(it)
        }
        viewModel.field03.observeWhenStarted(this) {
            textView03.setUiText(it)
        }
        viewModel.inProgress.observeWhenStarted(this) {
            progressBar.isInvisible = !it
        }
    }
}