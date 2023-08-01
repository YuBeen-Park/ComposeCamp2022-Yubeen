package com.example.reply.test

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.reply.data.local.LocalEmailsDataProvider
import com.example.reply.ui.ReplyApp
import org.junit.Rule
import org.junit.Test

class ReplyAppStateRestorationTest {

    //소형 화면에서 state 변경 테스트
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun compactDevice_selectedEmailEmailRetained_afterConfigChange() {
        val stateRestorationTester = StateRestorationTester(composeTestRule)
        stateRestorationTester.setContent {
            ReplyApp(windowSize = WindowWidthSizeClass.Compact)
        }

        composeTestRule.onNodeWithText(LocalEmailsDataProvider.allEmails[2].body)
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(LocalEmailsDataProvider.allEmails[2].subject)
            .performClick()

        composeTestRule.onNodeWithContentDescriptionForStringId(com.example.reply.R.string.navigation_back)
            .assertExists()
        composeTestRule.onNodeWithText(LocalEmailsDataProvider.allEmails[2].body).assertExists()

        stateRestorationTester.emulateSavedInstanceStateRestore()

        composeTestRule.onNodeWithContentDescriptionForStringId(com.example.reply.R.string.navigation_back)
            .assertExists()
        composeTestRule.onNodeWithText(LocalEmailsDataProvider.allEmails[2].body)
            .assertExists()
    }

}