package es.sebas1705.realtime.datasources

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import es.sebas1705.analytics.datasources.LogEventDataSource
import es.sebas1705.common.classes.TaskFlow
import es.sebas1705.common.states.DataState
import es.sebas1705.common.utlis.alias.DataEmptyFlow
import es.sebas1705.realtime.config.SettingsRT
import es.sebas1705.realtime.jsons.MessageJson
import javax.inject.Inject

/**
 * Data source for managing Message JSON data in Firebase Realtime Database.
 *
 * @property database [FirebaseDatabase]: Firebase database
 * @property logEventDataSource [LogEventDataSource]: Data source for logging events
 *
 * @since 0.1.0
 * @author Sebas1705 22/09/2025
 */
class MessageRealtimeDataSource @Inject constructor(
    private val database: FirebaseDatabase,
    private val logEventDataSource: LogEventDataSource
) : ClassLogData() {

    //Managers:
    private val taskFlow = TaskFlow(
        this,
        logEventDataSource::logError,
        SettingsRT.ERROR_GENERIC_MESSAGE_FAIL,
        SettingsRT.ERROR_GENERIC_MESSAGE_EX
    )

    //References:
    private val globalChatReference = database.getReference(SettingsRT.CHAT_GLOBAL_REFERENCE)

    //Listeners:
    private var messagesListener: ValueEventListener? = null

    //Tasks:
    /**
     * Add a message to the global chat
     *
     * @param messageId [String]: Message id
     * @param value [MessageJson]: Message to add
     *
     * @return [DataEmptyFlow] with the response of the operation
     *
     * @since 1.0.0
     * @author Sebas1705 22/09/2025
     */
    fun addMessageToGlobalChat(
        messageId: String,
        value: MessageJson
    ): DataEmptyFlow = taskFlow.taskFlowProducer(
        taskAction = { globalChatReference.child(messageId).setValue(value) },
        onSuccessListener = { DataState.EmptySuccess },
    )

    //Sets Listeners:
    /**
     * Get the messages from the global chat
     *
     * @property onDataChange ([List]<[MessageJson]>) -> [Unit]: Function to execute when the data changes
     * @property onCancelled ([String]) -> [Unit]: Function to execute when the data is cancelled
     *
     * @since 1.0.0
     * @author Sebas1705 22/09/2025
     */
    fun setMessagesListener(
        onDataChange: (List<Pair<MessageJson, String>>) -> Unit,
        onCancelled: (String) -> Unit
    ) {
        messagesListener = globalChatReference.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val messages = mutableListOf<Pair<MessageJson, String>>()
                    snapshot.children.forEach {
                        val message = it.getValue(MessageJson::class.java)
                        if (message != null) {
                            messages.add(message to it.key!!)
                        }
                    }
                    if (messages.size > SettingsRT.MAX_MESSAGES_ON_GLOBAL_CHAT) {
                        val key = messages[0].second
                        globalChatReference.child(key).removeValue()
                    }
                    onDataChange(messages)
                }

                override fun onCancelled(error: DatabaseError) {
                    onCancelled(error.message)
                }

            }
        )
    }

    //Removes Listeners:
    /**
     * Remove the listener from the messages
     *
     * @since 1.0.0
     * @author Sebas1705 22/09/2025
     */
    fun removeMessagesListener() {
        messagesListener?.let {
            globalChatReference.removeEventListener(it)
            messagesListener = null
        }
    }

}