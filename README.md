# 스프링부트와 AWS로 혼자 구현하는 웹 서비스 
1. 도서와 Gradle 버전이 다르기에 버전 다운을 해줘야 한다.  
2. 인텔리제이 설치 후 바로 터미널에서 .\gradlew wrapper --gradle-version 4.10.2 실행 해줘야 한다.
3. bulild.gradle를 수정 후 버전 다운을 실행하면 빌드 에러가 난다.(이것 때문에 몇 시간을 소비했다 ㅡㅡ)  
4. 최신 버전(IntelliJ IDEA 2021.3.3 (Community Edition)) 이상에선 롬복이 이미 설치 되어 있음으로 따로 설치할 필요가 없음  
5. build.gradle 변경 후에는 반드시 새로고침으로 적용 시켜줘야 함. 그렇지 않으면 코드 추가시 자동 인식 불가능  
