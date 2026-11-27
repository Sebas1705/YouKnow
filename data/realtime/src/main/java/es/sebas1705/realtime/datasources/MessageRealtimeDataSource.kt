package es.sebas1705.realtime.datasources

import com.google.android.gms.tasks.Tasks
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import es.sebas1705.analytics.datasources.LogEventDataSource
import es.sebas1705.common.classes.TaskFlow
import es.sebas1705.common.states.DataState
import es.sebas1705.common.utlis.alias.DataEmptyFlow
import es.sebas1705.common.utlis.extensions.types.logE
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
) {

    //Managers:
    private val taskFlow = TaskFlow(
        this.javaClass.kotlin,
        SettingsRT.ERROR_GENERIC_MESSAGE_FAIL,
        SettingsRT.ERROR_GENERIC_MESSAGE_EX
    ) { clazz, error ->
        logE(error)
        logEventDataSource.logError(clazz, error)
    }

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

    /**
     * Delete every message the given user wrote in the global chat.
     *
     * Messages are keyed `<authorUid>-<time>`, so they are found with a key-range query on that
     * prefix (a uid never contains `-`, so the prefix cannot match another user) and removed in one
     * multi-path update. The rules let any signed-in client delete a message, so this needs no more
     * than the user's own session.
     *
     * @param authorId [String]: Firebase uid of the author
     *
     * @return [DataEmptyFlow] with the response of the operation; having no messages is a success
     *
     * @since 1.6.0
     * @author Sebas1705 11/11/2026
     */
    fun removeMessagesOf(
        authorId: String
    ): DataEmptyFlow = taskFlow.taskFlowProducer(
        taskAction = {
            val prefix = "$authorId-"
            globalChatReference.orderByKey().startAt(prefix).endAt(prefix + KEY_RANGE_END)
                .get()
                .continueWithTask { query ->
                    if (!query.isSuccessful) throw query.exception ?: IllegalStateException(
                        SettingsRT.ERROR_GENERIC_MESSAGE_FAIL
                    )
                    val removals: Map<String, Any?> =
                        query.result.children.mapNotNull { it.key }.associateWith { null }
                    if (removals.isEmpty()) Tasks.forResult<Void?>(null)
                    else globalChatReference.updateChildren(removals)
                }
        },
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


    private companion object {
        /** Highest code point, so `startAt(p).endAt(p + KEY_RANGE_END)` covers every key starting with `p`. */
        const val KEY_RANGE_END = "\uf8ff"
    }

}
