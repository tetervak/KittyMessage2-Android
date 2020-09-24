package ca.tetervak.kittymessage2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import ca.tetervak.kittymessage2.Constants.Companion.IS_URGENT_KEY
import ca.tetervak.kittymessage2.Constants.Companion.MESSAGE_TEXT_KEY
import ca.tetervak.kittymessage2.databinding.ActivityInputBinding

class InputActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // make the buttons work
        binding.cancelButton.setOnClickListener { cancel() }
        binding.sendButton.setOnClickListener { send() }
    }

    private fun cancel() {
        setResult(RESULT_CANCELED)
        finish()
    }

    private fun send() {
        // get urgent flag value
        val urgent: Boolean = binding.urgentCheckBox.isChecked
        // get the selected message text
        val message = when (binding.messageGroup.checkedRadioButtonId) {
            R.id.purr_button -> getString(R.string.cat_purr)
            R.id.mew_button -> getString(R.string.cat_mew)
            R.id.hiss_button -> getString(R.string.cat_hiss)
            else -> getString(R.string.undefined)
        }
        // return back with the message data
        val intent = Intent()
        intent.putExtra(IS_URGENT_KEY, urgent)
        intent.putExtra(MESSAGE_TEXT_KEY, message)
        setResult(RESULT_OK, intent)
        finish()
    }
}