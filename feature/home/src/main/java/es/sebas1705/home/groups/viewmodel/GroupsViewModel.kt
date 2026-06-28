package es.sebas1705.home.groups.viewmodel


import android.app.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.common.classes.mvi.MVIBaseViewModel
import es.sebas1705.common.utlis.extensions.composables.printTextInToast
import es.sebas1705.groups.GroupUsesCases
import es.sebas1705.user.UserUsesCases
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * ViewModel of the groups feature.
 *
 * @property groupUsesCases [GroupUsesCases]: Uses cases of the groups.
 * @property userUsesCases [UserUsesCases]: Uses cases of the user.
 * @property application [Application]: Application context.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@HiltViewModel
class GroupsViewModel @Inject constructor(
    private val groupUsesCases: GroupUsesCases,
    private val userUsesCases: UserUsesCases,
    private val application: Application
) : MVIBaseViewModel<GroupsState, GroupsIntent>() {

    override fun initState(): GroupsState = GroupsState.default()

    override fun intentHandler(intent: GroupsIntent) {
        when (intent) {
            is GroupsIntent.CreateGroup -> createGroup(intent)
            is GroupsIntent.JoinGroup -> joinGroup(intent)
            is GroupsIntent.OutGroup -> outGroup(intent)
            is GroupsIntent.KickGroup -> kickGroup(intent)
            is GroupsIntent.LoadGroups -> loadGroups()
            is GroupsIntent.ClearGroups -> clearGroups()
        }
    }

    //Actions:
    /**
     * Action associated with [GroupsIntent.CreateGroup] that will create a group.
     *
     * @see [GroupsIntent.CreateGroup]
     */
    private fun createGroup(
        intent: GroupsIntent.CreateGroup
    ) = execute(Dispatchers.IO) {
        userUsesCases.addCreditsToUser(
            user = intent.userModel,
            creditsToAdd = -2000,
            onLoading = { startLoading() },
            onSuccess = {
                execute(Dispatchers.IO) {
                    groupUsesCases.createGroup(
                        intent.name,
                        intent.description,
                        intent.userModel,
                        onSuccess = { group ->
                            userUsesCases.setGroupToUser(
                                group,
                                intent.userModel,
                                true,
                                onSuccess = {
                                    stopLoading()
                                },
                                onError = {
                                    stopAndError(
                                        it,
                                        application::printTextInToast
                                    )
                                }
                            )
                        },
                        onError = {
                            stopAndError(
                                it,
                                application::printTextInToast
                            )
                        }
                    )
                }
            },
            onError = { stopAndError(it, application::printTextInToast) }
        )
    }

    /**
     * Action associated with [GroupsIntent.JoinGroup] that will join a group.
     *
     * @see [GroupsIntent.JoinGroup]
     */
    private fun joinGroup(
        intent: GroupsIntent.JoinGroup
    ) = execute(Dispatchers.IO) {
        userUsesCases.setGroupToUser(
            group = intent.groupModel,
            user = intent.userModel,
            creator = false,
            onSuccess = {
                stopLoading()
            },
            onError = {
                stopAndError(it, application::printTextInToast)
            }
        )
    }

    /**
     * Action associated with [GroupsIntent.OutGroup] that will out of a group.
     *
     * @see [GroupsIntent.OutGroup]
     */
    private fun outGroup(
        intent: GroupsIntent.OutGroup
    ) = execute(Dispatchers.IO) {
        userUsesCases.removeGroupToUser(
            group = intent.groupModel,
            firebaseId = intent.userModel.firebaseId,
            onLoading = { startLoading() },
            onSuccess = {
                if (intent.groupModel.leaderUID == intent.userModel.firebaseId || intent.groupModel.members.isEmpty()) {
                    execute(Dispatchers.IO) {
                        groupUsesCases.removeGroup(
                            intent.groupModel,
                            onSuccess = {
                                stopLoading()
                            },
                            onError = {
                                stopAndError(
                                    it,
                                    application::printTextInToast
                                )
                            }
                        )
                    }
                } else {
                    stopLoading()
                }
            },
            onError = {
                stopAndError(it, application::printTextInToast)
            }
        )
    }

    /**
     * Action associated with [GroupsIntent.KickGroup] that will kick a user from a group.
     *
     * @see [GroupsIntent.KickGroup]
     */
    private fun kickGroup(
        intent: GroupsIntent.KickGroup
    ) = execute(Dispatchers.IO) {
        userUsesCases.removeGroupToUser(
            group = intent.groupModel,
            firebaseId = intent.userToKickMemberId,
            onLoading = { startLoading() },
            onSuccess = { stopLoading() },
            onError = {
                stopAndError(it, application::printTextInToast)
            }
        )
    }

    /**
     * Action associated with [GroupsIntent.LoadGroups] that will load the social data.
     *
     * @see [GroupsIntent.LoadGroups]
     */
    private fun loadGroups() {
        groupUsesCases.setGroupsListener(
            onSuccess = { data ->
                updateUi {
                    it.copy(
                        groups = data
                    )
                }
            },
            onError = {
                stopAndError(it, application::printTextInToast)
            }
        )
    }

    /**
     * Action associated with [GroupsIntent.ClearGroups] that will clear the social data.
     *
     * @see [GroupsIntent.ClearGroups]
     */
    private fun clearGroups() {
        groupUsesCases.removeGroupsListener()
    }

    //Privates:
    private fun startLoading() {
        updateUi { it.copy(isLoading = true) }
    }

    private fun stopLoading() {
        updateUi { it.copy(isLoading = false) }
    }

    private fun stopAndError(error: String, onError: (String) -> Unit) {
        stopLoading()
        execute { onError(error) }
    }
}



