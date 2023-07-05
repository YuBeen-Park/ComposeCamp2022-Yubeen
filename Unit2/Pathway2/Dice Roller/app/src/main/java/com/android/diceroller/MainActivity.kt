package com.android.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.android.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                DiceRollerApp()
            }
        }
    }
}

@Preview
@Composable
fun DiceRollerApp() {
    // 이 메서드는 구성요소가 사용 가능한 공간을 채우도록 지정합니다.
    // 주요 특징은 주사위와 버튼이 화면 중앙에 배치된 점입니다.
    // wrapContentSize() 메서드는 사용 가능한 공간이 최소한 내부에 있는 구성요소만큼 커야 한다고 지정합니다.
    // 하지만 fillMaxSize() 메서드가 사용되므로 레이아웃 내부의 구성요소가 사용 가능한 공간보다 작으면
    // Alignment 객체를 사용 가능한 공간 내에서 구성요소를 정렬해야 하는 방식을 지정하는 wrapContentSize() 메서드에 전달할 수 있습니다
    DiceWithButtonAndImage(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center))
}

// Modifier 를 전달해야하는 이유 : 컴포저블이 재구성(@Composable메서드의 코드블럭이 재실행)을 거칠 수 있기 때문
@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier){

}


