# Maze Puzzle

Java Swing을 활용하여 구현한 그리드 기반 미로 찾기 시뮬레이터

커스텀 미로를 설계하고, **BFS**와 **DFS** 통해 경로를 찾는 과정을 비교

## 🛠 기술 스택 및 구조

### 1. 사용 기술

- Java 21
- Java Swing

### 2. 프로젝트 클래스 구조

| 클래스/인터페이스    | 설명                                         |
| -------------------- | -------------------------------------------- |
| `Maze`               | 메인 UI 클래스. 이벤트 처리 및 그리드 렌더링 |
| `AbstractPathFinder` | 공통 탐색 로직을 포함한 추상 클래스          |
| `BFSFinder`          | BFS를 활용한 최단 경로 탐색                  |
| `DFSFinder`          | DFS를 활용한 최단 경로 탐색                  |
| `DemoMaps`           | 맵 프리셋 데이터를 관리하는 클래스           |
| `PathFinder`         | 탐색 알고리즘 확장을 위한 인터페이스         |

## 🕹 사용 방법

1. **실행**: `Main.Maze` 클래스를 실행
2. **모드 변경 (숫자 키)**:

- `1`: **벽 토글 모드** - 클릭 시 벽/길(빈칸) 토글
- `2`: **출발지 설정**
- `3`: **도착지 설정**

3. **미로**: 직접 그리드를 클릭하여 미로를 만들거나 하단의 **데모** 버튼을 눌러 맵을 불러올 수 있음
4. **탐색**: 알고리즘(BFS/DFS)을 선택하고 **[탐색 시작]** 버튼을 클릭

## 📝 알고리즘

### BFS (Breadth-First Search)

- 출발지에서 가까운 노드부터 차례대로 탐색
- 그리드 방식에서 항상 **최단 거리**를 보장함

### DFS (Depth-First Search)

- 한 방향으로 갈 수 있는 끝까지 가본 후 막히면 되돌아옴
- 가장 먼저 발견되는 경로를 반환하므로 최단 거리를 보장하지 않음

## 🚀 확장

새로운 알고리즘(예: A\*, Dijkstra)을 추가하려면 `AbstractPathFinder`를 상속받아 `findPath` 메서드만 구현하면 즉시 적용 가능

```java
class AStarFinder extends AbstractPathFinder {
    @Override
    public SearchResult findPath(int[][] grid, Point start, Point end) {
        // 알고리즘 구현
    }
}

```
