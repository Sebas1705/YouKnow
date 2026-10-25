package es.sebas1705.settings

import app.cash.turbine.test
import es.sebas1705.common.theme.ThemeContrast
import es.sebas1705.models.SettingsModel
import es.sebas1705.resources.games.Languages
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class SettingsUseCasesTest {

    @Test
    fun `readGameLanguage maps the stored ordinal and follows changes`() = runTest {
        val repository = FakeSettingsRepository(FakeSettingsRepository.DEFAULTS.copy(language = Languages.ES.ordinal))

        ReadGameLanguage(repository)().test {
            assertEquals(Languages.ES, awaitItem())
            repository.update(repository.state.value.copy(language = Languages.EN.ordinal))
            assertEquals(Languages.EN, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `readGameLanguage defaults to ANY`() = runTest {
        ReadGameLanguage(FakeSettingsRepository())().test {
            assertEquals(Languages.ANY, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `readSettings maps the stored data onto the model`() = runTest {
        val repository = FakeSettingsRepository(
            FakeSettingsRepository.DEFAULTS.copy(musicVolume = 0.2f, appContrast = ThemeContrast.High.ordinal, language = Languages.EN.ordinal)
        )

        ReadSettingsUseCase(repository)().test {
            val model = awaitItem()
            assertEquals(0.2f, model.musicVolume)
            assertEquals(ThemeContrast.High, model.appContrast)
            assertEquals(Languages.EN, model.language)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `updateSettings stores the model and marks the defaults as set`() = runTest {
        val repository = FakeSettingsRepository()
        val model = SettingsModel(firstTime = true, musicVolume = 0.1f, soundVolume = 0.9f, appContrast = ThemeContrast.Medium, language = Languages.ES)

        UpdateSettingsUseCase(repository)(model)

        val stored = repository.state.value
        assertEquals(true, stored.firstTime)
        assertEquals(0.1f, stored.musicVolume)
        assertEquals(0.9f, stored.soundVolume)
        assertEquals(ThemeContrast.Medium.ordinal, stored.appContrast)
        assertEquals(Languages.ES.ordinal, stored.language)
        assertEquals(true, stored.defaultSet)
    }
}
