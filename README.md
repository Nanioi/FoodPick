# FoodPick
맛집리스트 제공, 찜목록, 상세페이지, 지도 제공


## 앱 기능

- 맛집리스트 제공
- 자주가는가게(찜가게) 목록 제공
- 지도 위치 정보 제공

## 앱 구조

클린 아키텍처를 적용해 Presentation, Domain, Data 레이어를 분리했습니다.
Hilt를 이용해 의존성 주입을 관리하고, ViewModel과 StateFlow로 상태를 처리했습니다.

- **Presentation Layer**: UI(Compose), ViewModel, 화면별 컴포넌트
- **Domain Layer**: UseCase, 비즈니스 로직, 도메인 모델, Repository 인터페이스 정의
- **Data Layer**: Repository 구현, 로컬 저장소, 외부 API 통신, DTO 등 데이터 소스 관리
- **DI**: Hilt 기반 의존성 주입 모듈 정의, 계층 간 연결
- **Common**: 공통 유틸리티, 상수, 인텐트 도우미 등 전 계층에서 공용으로 쓰이는 코드
- **UI Theme**: Compose 테마, 컬러, 타이포그래피 등 스타일 정의


## 📸 ScreenShot

<img src="https://github.com/user-attachments/assets/35fdddc6-19c5-4860-bf63-bfe1789a967f" height="450">
<img src="https://github.com/user-attachments/assets/3884e36b-2ed3-417d-9c10-02c69b86c455" height="450">
<img src="https://github.com/user-attachments/assets/fedccff5-2e2f-471e-91a9-348a6535e476" height="450">
<img src="https://github.com/user-attachments/assets/f7ca5b42-38df-4e68-b663-9f931950866b" height="450">



## 주요 구현사항

- 클린 아키텍처로 레이어 간 결합도 최소화
- Jetpack Compose 기반 선언적 UI 작성,
- ViewModel과 StateFlow를 이용한 상태 관리 및 실시간 UI 반영
- Hilt로 의존성 주입 관리
- 공통 매퍼/유틸/상수 분리해 재사용성 향상

## 사용 기술

- Kotlin, Jetpack Compose, Jetpack 라이브러리
- Clean Architecture, Hilt
- Kakao SDK 
- Coroutines, Retrofit, OkHttp, Gson
