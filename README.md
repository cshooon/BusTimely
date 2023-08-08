# BusTimely

서울시 버스 도착 정보 알려주는 웹사이트

## 개요
BusTimely는 대한민국 서울의 실시간 버스 도착 정보를 제공하는 웹사이트입니다. BusTimely는 공공데이터포털 서울의 대중 교통 API를 활용하여 사용자에게 정확한 최신 버스 도착 시간을 제공합니다.

## 구조
### model
api 호출에 필요한 데이터를 반환합니다. jpa로 연결된 데이터베이스에 버스 관련 정보가 저장되어 있고 이를 받아오기 위한 class입니다.
### repository
jpa 이용해 자동으로 완성된 query를 보내 정보를 받아 저장합니다.
### service
핵심 로직(api 호출, xml에서 정보 추출)를 수행합니다.
### controller
get 방식을 통해 mapping합니다.

## test code
repository의 경우 sample data를 이용하여 database에서 노선에 해당하는 값을 가져오는지 확인합니다. service의 경우 xml이 제데로 추출되었는지 확인합니다. controller의 경우 status와 body에 정보가 있는지 확인합니다.

<img width="355" alt="Screenshot 2023-08-05 at 9 10 58 PM" src="https://github.com/cshooon/BusTimely/assets/113033780/533cc37c-97ec-428d-87ae-aacdbb2afc8c">

## postman
### 1번 방식(노선명 검색)
<img width="971" alt="Screenshot 2023-08-05 at 9 44 26 PM" src="https://github.com/cshooon/BusTimely/assets/113033780/02b902b4-c2fc-4d28-8230-7bc805a5e431">
<img width="981" alt="Screenshot 2023-08-05 at 9 44 46 PM" src="https://github.com/cshooon/BusTimely/assets/113033780/7288dd84-1ba7-46c5-9b28-8925eda56119">


### 2번 방식(노선명, 정류장명 검색)
<img width="972" alt="Screenshot 2023-08-05 at 9 43 41 PM" src="https://github.com/cshooon/BusTimely/assets/113033780/5e3b9b3b-be74-4928-b0ea-831eb121b838">
<img width="974" alt="Screenshot 2023-08-05 at 9 43 50 PM" src="https://github.com/cshooon/BusTimely/assets/113033780/40c69179-c33c-4a0e-8647-60c42fc9425f">


## 한계점
```Java
@Repository
public interface BusStopRepository extends JpaRepository<BusStop, Long> {
    BusStop findFirstByRouteName(String routeName);
    BusStop findFirstByRouteNameAndStationName(String routeName, String stationName);

    // 가는 거 오는 거 2번 필요!!! noUniqueException
```

버스 정보를 가져오기 위해 [서울 열린 데이터 광장](https://data.seoul.go.kr/dataList/OA-1095/L/1/datasetView.do) 버스 정보 파일을 사용하고 있습니다. 갈 때, 올 때 2가지 정류장이 있어 jpa에서 정보를 불러올 때 noUniqueException이 납니다. 두 정보를 모두 사용해야 하므로 이 부분은 공부를 더 해야 할 것 같습니다. 🥲🥲

## front-end
### HTML 구조
1. 버스 도착 정보 검색: 메인 제목입니다.
1. 경로명(버스 번호)으로 검색: 사용자가 버스 번호만으로 검색할 수 있는 섹션입니다.
1. 경로명 & 정류장명으로 검색: 사용자가 버스 번호와 정류장명으로 검색할 수 있는 섹션입니다.
   
### Javascript 함수
1. searchByRoute(): 경로명(버스 번호)으로 정보를 검색합니다.
1. searchByRouteAndStation(): 경로명과 정류장명으로 정보를 검색합니다.
1. search(url, resultId): 실제로 서버에 검색 요청을 보내고 결과를 DOM에 표시하는 함수입니다.

### CSS
모바일에서도 브라우저 창 크기를 줄여도 잘 실행되도록 display을 flex로 설정했습니다.

## 배포
자동 배포하려고 했으나 build/libs/*.jar로 설정해도 jar 파일을 계속 찾지 못해 수동으로 jar 파일을 넣어주고 배포했습니다. 😢😢

## 링크 및 캡처
[BusTimely](https://bus-timely-b3ef15c015ed.herokuapp.com/)

<img width="800" alt="Screenshot 2023-08-07 at 5 29 17 PM" src="https://github.com/cshooon/BusTimely/assets/113033780/ae3c5dae-2565-4548-aa8a-29691055ef7f">

왼쪽은 우성아파트 정류장이 1분 전이라고 뜨지만 오른쪽은 곧 도착이라고 뜹니다(조금 나중에 search 누름). 이처럼 공공데이터 open api에서 교통상황에 따라 실시간으로 시간을 업데이트해줍니다.
