

# 레이아웃 (Layout)

## Column
세로(vertical)로 배치

```kotlin
@Composable
inline fun Column(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit
) {
    val measurePolicy = columnMeasurePolicy(verticalArrangement, horizontalAlignment)
    Layout(
        content = { ColumnScopeInstance.content() },
        measurePolicy = measurePolicy,
        modifier = modifier
    )
}
```
- `modifier` : 컴포저블의 크기, 동작과 같은 변경사항을 처리할 수 있도록 하는 변수
- `verticalArrangement` : 수직 배치를 결정하는 변수
  - Top
  - Center
  - Bottom
  - SpaceBetween
  - SpaceAround
  - SpaceEvenly

![image](https://github.com/YuBeen-Park/ComposeCamp2022-Yubeen/assets/74285061/1635613d-1d37-4fdd-958f-36590293dcaa)

- `horizontalAlignment` : 수평 정렬을 결정하는 변수
  - Start
  - CenterHorizontally
  - End
   
  
## Row
가로(horizontal)로 배치

```kotlin
@Composable
inline fun Row(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    content: @Composable RowScope.() -> Unit
) {
    val measurePolicy = rowMeasurePolicy(horizontalArrangement, verticalAlignment)
    Layout(
        content = { RowScopeInstance.content() },
        measurePolicy = measurePolicy,
        modifier = modifier
    )
}
```
- `modifier` : 컴포저블의 크기, 동작과 같은 변경사항을 처리할 수 있도록 하는 변수
- `horizontalArrangement` : 수평 배치를 결정하는 변수
    - Top
    - CenterVertically
    - Bottom

- `verticalAlignment` : 수직 정렬을 결정하는 변수
    - Start
    - Center
    - End
    - SpaceBetween
    - SpaceAround
    - SpaceEvenly
  
![image](https://github.com/YuBeen-Park/ComposeCamp2022-Yubeen/assets/74285061/1c4285ca-2f7c-44b6-a675-3d0e4042d5ff)


## Box
다른 항목들 위에 배치

```kotlin
@Composable
inline fun Box(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    propagateMinConstraints: Boolean = false,
    content: @Composable BoxScope.() -> Unit
) {
    val measurePolicy = rememberBoxMeasurePolicy(contentAlignment, propagateMinConstraints)
    Layout(
        content = { BoxScopeInstance.content() },
        measurePolicy = measurePolicy,
        modifier = modifier
    )
}
```
- `contentAlignment`
  - TopStart
  - TopCenter
  - TopEnd
  - CenterStart
  - Center
  - CenterEnd
  - BottomStart
  - BottomCenter
  - BottomEnd


## BoxWithConstraints
`Box` 기능을 확장해서 Constraint(최대 크기, 최소 크기)에 접근할 수 있도록 만든 레이아웃


```kotlin
@Composable
fun BoxWithConstraints(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    propagateMinConstraints: Boolean = false,
    content: @Composable BoxWithConstraintsScope.() -> Unit
) {
    val measurePolicy = rememberBoxMeasurePolicy(contentAlignment, propagateMinConstraints)
    SubcomposeLayout(modifier) { constraints ->
        val scope = BoxWithConstraintsScopeImpl(this, constraints)
        val measurables = subcompose(Unit) { scope.content() }
        with(measurePolicy) { measure(measurables, constraints) }
    }
}
```

## Constraint Layout
다른 항목들의 위치에 상대적으로 배치

기존에 xml에서 많이 활용한 레이아웃인데 xml에서는 성능상의 이점이 있었지만, 컴포즈에서는 성능상의 이점은 없다.

뷰가 복잡하거나 가독성이 떨어질 때만 사용하는 것을 권장

```kotlin
modifier = Modifier.constrainAs(txt){
        top.linkTo(parent.top, margin = 10.dp)
}
```
