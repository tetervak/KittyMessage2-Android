package ca.tetervak.kittymessage2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ca.tetervak.kittymessage2.Constants.Companion.GET_MESSAGE_REQUEST
import ca.tetervak.kittymessage2.Constants.Companion.IS_MESSAGE_RECEIVED_KEY
import ca.tetervak.kittymessage2.Constants.Companion.IS_URGENT_KEY
import ca.tetervak.kittymessage2.Constants.Companion.MESSAGE_TEXT_KEY
import ca.tetervak.kittymessage2.databinding.ActivityOutputBinding


class OutputActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOutputBinding

    private var isMessageReceived = false
    private var isMessageUrgent = false
    private var messageText: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOutputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getButton.setOnClickListener { showInput() }

        if (savedInstanceState is Bundle) {
            isMessageReceived = savedInstanceState.getBoolean(IS_MESSAGE_RECEIVED_KEY)
            if (isMessageReceived) {
                isMessageUrgent = savedInstanceState.getBoolean(IS_URGENT_KEY)
                binding.urgentFlagOutput.text =
                        getString(if (isMessageUrgent) R.string.urgent else R.string.not_urgent)
                messageText = savedInstanceState.getString(MESSAGE_TEXT_KEY)
                binding.messageText.text = messageText
            }
        } else {
            isMessageReceived = false
        }
    }

    private fun showInput() {
        val intent = Intent(this, InputActivity::class.java)
        startActivityForResult(intent, GET_MESSAGE_REQUEST)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(IS_MESSAGE_RECEIVED_KEY, isMessageReceived)
        if (isMessageReceived) {
            outState.putBoolean(IS_URGENT_KEY, isMessageUrgent)
            outState.putString(MESSAGE_TEXT_KEY, messageText)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (requestCode == GET_MESSAGE_REQUEST && resultCode == RESULT_OK) {
            if (intent != null) {
                isMessageReceived = true
                isMessageUrgent = intent.getBooleanExtra(IS_URGENT_KEY, true)
                binding.urgentFlagOutput.text =
                        getString(if (isMessageUrgent) R.string.urgent else R.string.not_urgent)
                messageText = intent.getStringExtra(MESSAGE_TEXT_KEY)
                binding.messageText.text = messageText
            }
        }
    }

}