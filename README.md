# 프로젝트 '한잔'

RESTful 백엔드 시스템 개발 및 CI/CD 인프라 구축

<div align=left><h1>📚 STACKS</h1></div>

<div align=left> 
  <img src="https://img.shields.io/badge/vue.js-4FC08D?style=for-the-badge&logo=vue.js&logoColor=white"> 
  <img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white">
  <br>
  <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> 
  <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"> 
  <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> 
  <img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=white">
  <br>

  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> 
  <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> 
  <img src="https://img.shields.io/badge/mariaDB-003545?style=for-the-badge&logo=mariaDB&logoColor=white"> 
  <br>

  <img src="https://img.shields.io/badge/linux-FCC624?style=for-the-badge&logo=linux&logoColor=black"> 
  <img src="https://img.shields.io/badge/jenkins-gray?style=for-the-badge&logo=jenkins&logoColor=white"> 
  <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"> 
  <br>
</div>

### 프론트: Vue.js, android, ios 등 클라이언트 추가 예정

> RESTful APIs 설계로 다중 클라이언트를 지원하도록 설계

### 백엔드: SpringFramework, Java, JPA(ORM), MariaDB

> RESTful APIs 설계로 다중 클라이언트를 지원하도록 설계

### 인프라: Ubuntu20.0.4(Linux), Docker, Jenkins, Slack

> Docker, Jenkins를 활용해 자동 빌드 및 무중단 배포, slack을 활용한 푸쉬알림
> ![

<br><br>

## 주요기능 변동 사항

- 2022.06.16 프로젝트 생성

<br><br>

## 문제발생 및 해결
* jenkins(Docker 컨테이너로 실행)시 스프링 컨테이너 배포문제
  > 도커로 실행된 jenkins에서 스프링 서비스를 추가적인 도커 컨테이너로 실행하고자 할때 이중화 되는 문제(Docker in Docker)
  > DinD는 사용 가능한 구조긴 하지만 공식적으론 DinD보다 DooD방식을 권장하고 있음
  > DinD로 실행시 컨테이너가 절대적인 권한을 부여받는 등 보안상 문제가 방생함
  >
  > __해결책: docker.sock 파일을 jenkins 컨테이너와 볼륨으로 맵핑시켜 jenkins컨테이너 에서 Host Docker 엔진을 사용할 수 있게 설계(Docker out of Docker)__
  
* jenkins 와 github 연동중 민감정보 파일 관리
  > 공개된 git repo에 키, 비밀번호 등 파일을 업로드 하는것은 매우 위험한 행동이다.
  > 하지만 jenkins는 연결된 git repo에서 소스코드를 클론하여 빌드 동작을 수행한다.
  > 그래서 민감정보 파일을 형상관리에서 제외 시키면 jenkins는 제대로된 빌드를 수행할 수 없다.
  > 
  > __해결책: 민감정보 파일을 jenkins 컨테이너에 생성하고 빌드가 트리거될때 파일을 주입시켜 빌드에 포함되도록 구현__
  
  * docker-compose 작성시 networks 설정
  
  
https://spring.io/guides/gs/spring-boot-docker/
docker build --build-arg JAR_FILE=build/libs/\*.jar -t hanjan-backend .
Github 트리거 테스트 5회차
  
