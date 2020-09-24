package ca.tetervak.kittymessage2

class Constants {

    companion object{
        // used in save instance logic
        const val IS_MESSAGE_RECEIVED_KEY = "received"

        // used to pass data from InputActivity to MainActivity and to save the instance
        const val IS_URGENT_KEY = "urgent"
        const val MESSAGE_TEXT_KEY = "message"

        // the request code, used in starting InputActivity for result, and receiving the result
        const val GET_MESSAGE_REQUEST = 0
    }

}