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
    private lateinit var textView04: TextView
    private lateinit var textView05: TextView
    private lateinit var textView06: TextView
    private lateinit var textView07: TextView
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
        textView04 = findViewById(R.id.textView04)
        textView05 = findViewById(R.id.textView05)
        textView06 = findViewById(R.id.textView06)
        textView07 = findViewById(R.id.textView07)
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
        viewModel.field04.observeWhenStarted(this) {
            textView04.setUiText(it)
        }
        viewModel.field05.observeWhenStarted(this) {
            textView05.setUiText(it)
        }
        viewModel.field06.observeWhenStarted(this) {
            textView06.setUiText(it)
        }
        viewModel.field07.observeWhenStarted(this) {
            textView07.setUiText(it)
        }
        viewModel.inProgress.observeWhenStarted(this) {
            progressBar.isInvisible = !it
        }
    }
}