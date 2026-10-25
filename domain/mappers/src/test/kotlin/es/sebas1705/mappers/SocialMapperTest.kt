package es.sebas1705.mappers

import es.sebas1705.firestore.documents.NewDocument
import es.sebas1705.models.social.GroupModel
import es.sebas1705.models.social.NewModel
import es.sebas1705.realtime.jsons.GroupJson
import org.junit.Assert.assertEquals
import org.junit.Test

class SocialMapperTest {

    @Test
    fun `news document maps both languages`() {
        val model = NewDocument("Título", "Title", "Cuerpo", "Body").toNewModel()

        assertEquals(NewModel(titleEs = "Título", titleEn = "Title", bodyEs = "Cuerpo", bodyEn = "Body"), model)
    }

    @Test
    fun `group name is the id without the leader suffix`() {
        val model = GroupJson("desc", listOf("leader-uid", "other")).toGroupModel("my-group-leader")

        assertEquals("my-group", model.name)
        assertEquals("desc", model.description)
        assertEquals(listOf("leader-uid", "other"), model.members)
    }

    @Test
    fun `the first member leads the group`() {
        assertEquals("first", GroupJson("", listOf("first", "second")).toGroupModel("g-x").leaderUID)
    }

    @Test
    fun `a group without members has no leader`() {
        assertEquals("", GroupJson("", emptyList()).toGroupModel("g-x").leaderUID)
    }

    @Test
    fun `group model to json drops the derived fields`() {
        val json = GroupModel("name", "desc", listOf("a", "b"), "a").toGroupJson()

        assertEquals(GroupJson(description = "desc", members = listOf("a", "b")), json)
    }
}
