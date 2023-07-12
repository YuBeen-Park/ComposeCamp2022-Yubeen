package com.android.tiptime

import junit.framework.Assert.assertEquals
import org.junit.Test
import java.text.NumberFormat

class TipCalculatorTests {

    @Test
    fun calculate_20_percent_tip_no_roundup(){
        // 청구금액 10달러의 팁 20퍼센트 계산을 테스트하는 메서드 생성 = 예상 결과 : 2달러
        // 앱 코드에서 기기의 언어에 따라 형식이 지정된다.
        // 동일한 형식 지정이 테스트에서도 예상 팁 금액을 확인할 때 사용되어야 한다.

        val amount = 10.00
        val tipPercent = 20.00
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        val actualTip = calculateTip(amount = amount, tipPercent = tipPercent)
        assertEquals(expectedTip, actualTip)
    }
}