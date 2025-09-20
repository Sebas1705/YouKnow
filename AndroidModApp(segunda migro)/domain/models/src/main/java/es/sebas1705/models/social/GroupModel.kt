package es.sebas1705.models.social


/**
 * Model to represent a group
 *
 * @property name [String]: Name of the group
 * @property description [String]: Description of the group
 * @property members [List]<[Long]>: List of the members of the group
 * @property leaderUID [String]: UID of the leader of the group
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
data class GroupModel(
    val name: String,
    val description: String,
    val members: List<String>,
    val leaderUID: String,
) {

    val groupId: String get() = this.name + "-" + this.leaderUID.split("-")[0]
}