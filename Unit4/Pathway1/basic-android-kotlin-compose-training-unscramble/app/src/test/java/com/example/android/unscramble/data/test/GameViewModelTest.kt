package com.example.android.unscramble.data.test

import com.example.android.unscramble.data.MAX_NO_OF_WORDS
import com.example.android.unscramble.data.SCORE_INCREASE
import com.example.android.unscramble.data.getUnscrambledWord
import com.example.android.unscramble.ui.GameViewModel
import org.junit.Assert.*
import org.junit.Test

class GameViewModelTest {

    /* 성공 경로의 단위 테스트
       GameViewModel의 인스턴스가 초기화된 경우 updateUserGuess() 메서드가 올바른 추측 단어로 호출된 후
       checkUserGuess() 가 호출되면 다음 사항을 확인해야한다.
       - 올바른 추측 단어는 updateUserGuess() 메서드에 전달된다.
       - checkUserGuess() 메서드가 호출된다.
       - score 및 isGuessedWordWrong 상태 값이 올바르게 업데이트된다.
     */
    private val viewModel = GameViewModel()

    // thingUnderTest_TriggerOfTest_ResultOfTest 형식을 사용
    @Test
    fun gameViewModel_CorrectWordGuessed_ScoreUpdatedAndErrorFlagUnset() {
        var currentGameUiState = viewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)

        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess() // 추측한 단어 확인

        currentGameUiState = viewModel.uiState.value
        // checkUserGuess() 가 isGuessedWordWrong변수를 올바르게 업데이트 한다는 사실을 확인하기
        assertFalse(currentGameUiState.isGuessedWordWrong)
        // score가 올바르게 업데이트 된 것을 assert하기
        assertEquals(SCORE_AFTER_FIRST_CORRECT_ANSWER, currentGameUiState.score)
    }

    /* 오류 경로의 단위 테스트
       잘못된 단어가 viewModel.updateUserGuess() 메서드에 인수로 전달되고 viewModel.checkUserGuess() 메서드가 호출되면
       다음 사항을 확인해야 한다.
       - currentGameUiState.score 속성 값이 그대로 유지
       - 추측이 잘못되었으므로 currentGameUiState.isGuessedWordWrong 속성의 값이 true로 설정됨
    */

    @Test
    fun gameViewModel_IncorrectGuess_ErrorFlagSet(){
        val incorrectPlayerWord = "and"
        viewModel.updateUserGuess(incorrectPlayerWord)
        viewModel.checkUserGuess()

        val currentGameUiState = viewModel.uiState.value
        assertEquals(0, currentGameUiState.score)
        assertTrue(currentGameUiState.isGuessedWordWrong)
    }

    /* 경계 사례의 단위 테스트 #1
       UI 초기상태를 테스트하려면 GameViewModel 클래스의 단위 테스트를 작성해야 한다.
       테스트에서는 GameViewModel을 초기화하면 다음 사항이 true임을 확인해야 한다.
       - currentWordCount 속성 = 1
       - score = 0
       - isGuessedWordWrong = false
       - isGameOver = false
     */

    @Test
    fun gameViewModel_Initialization_FirstWordLoaded(){
        val gameUiState = viewModel.uiState.value
        val unScrambledWord = getUnscrambledWord(gameUiState.currentScrambledWord)

        assertNotEquals(unScrambledWord, gameUiState.currentScrambledWord)
        assertTrue(gameUiState.currentWordCount == 1)
        assertTrue(gameUiState.score == 0)
        assertFalse(gameUiState.isGuessedWordWrong)
        assertFalse(gameUiState.isGameOver)
    }

    /* 경계 사례의 단위 테스트 #2
       사용자가 모든 단어를 추측한 후 UI상태를 테스트하는 것.
       사용자가 모든 단어를 올바르게 추측하면 다음 사항이 true임을 확인해야 한다.
       - 점수가 최신상태
       - currentGameUiState.currentWordCount 속성이 MAX_NO_OF_WORDS 값과 같다
       - currentGameUiState.isGameOver 속성이 true로 설정됨
     */

    @Test
    fun gameViewModel_AllWordsGuessed_UiStateUpdatedCorrectly() {
        var expectedScore = 0
        var currentGameUiState = viewModel.uiState.value
        var correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)
        repeat(MAX_NO_OF_WORDS){
            expectedScore += SCORE_INCREASE
            viewModel.updateUserGuess(correctPlayerWord)
            viewModel.checkUserGuess()
            currentGameUiState = viewModel.uiState.value
            correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)
            assertEquals(expectedScore, currentGameUiState.score)
        }
        assertEquals(MAX_NO_OF_WORDS, currentGameUiState.currentWordCount)
        assertTrue(currentGameUiState.isGameOver)
    }

    companion object{
        private const val SCORE_AFTER_FIRST_CORRECT_ANSWER = SCORE_INCREASE
    }
}