<img src="https://github.com/user-attachments/assets/b2dd403c-0100-4f98-88e6-c55e5b286104" alt="제목" width="100%" height="auto"> 


# 프로젝트 목적
서버-클라이언트 구조로 통신하는 안드로이드 어플 제작
<br></br>
# 프로젝트 소개

오늘은 무엇을 먹을지 고민하는 분들을 위한 사용자 맞춤형 음식 추천 어플입니다.

이 앱은 사용자의 지역에서 실시간으로 제공되는 날씨 정보와 개인의 음식 선호도를 바탕으로 가중치를 조정하여 최적의 음식을 추천합니다. 또한, 추천된 음식에 맞춰 근처 식당 정보도 함께 제공하여 편리한 외식 옵션을 제시합니다.
<br></br>
## 개발 기간

24.05.09 ~ 24.05.30 (3주)
<br></br>

## 개발 기여도


**기능 설계 및 기획** : 50%


**코드 작성 및 구현** : 100%


프로젝트 진행 시, 기획과 계획 단계는 팀원 1명과 함께 구상하였습니다. 이후 코드 작성 및 구현 작업은 실시간 통화를 통해 진행하였으며, 각자 당일의 계획에 맞게 독립적으로 코드를 작성했습니다. 필요한 경우, 코드 작성 중에 도움이 필요하면 조언을 요청하는 형태로 협업을 진행하였습니다.

<br></br>



 ## Use Case Diagram:
<img src="https://github.com/user-attachments/assets/81dec1a2-777d-4826-b384-343f67a4da01" alt="UCD" width="100%" height="auto"> 


<br></br>
 ## Communication Diagram:
<img src= "https://github.com/user-attachments/assets/1ee8e171-394c-4416-83a8-4dff7156bacd" alt="CD" width="100%" height="auto"> 

 ## 테이블 스키마:
 <img src= "https://github.com/user-attachments/assets/8a7d1278-1df2-417a-a179-80efa6994cc2" alt="DB" width="100%" height="auto"> 

<br></br>
# 기술 스택
<img src= "https://github.com/user-attachments/assets/48e33715-5828-450a-90dd-caf96ea346b4" alt="기술스택" width="100%" height="auto"> 

<br></br><br></br><br></br><br></br><br></br><br></br>
# 주요 기능

- **로그인 기능**: 
  - 사용자가 입력한 아이디와 비밀번호를 서버로 전송하여, 서버에서 데이터베이스에 저장된 사용자 정보와 일치하는지 여부를 검사합니다.
  - **동적 흐름**:
 
    
    1. 사용자가 입력한 ID와 비밀번호를 GET 요청을 통해 서버로 전송함니다.
 
    
    2. 서버는 데이터베이스에서 해당 아이디와 비밀 번호를 조회한뒤 결과를 반환합니다.
  
 <br></br>
- **회원 가입 기능**: 
  - 입력한 아이디를 서버에 전송하여 중복된 데이터의 여부를 검사하거나, 새 아이디와 비밀번호를 서버로 전송하여 데이터베이스에 저장합니다.
  - **동적 흐름**:
 
    
    1. 사용자가 입력한 ID를 GET 요청을 통해 서버로 전송하거나 혹은 ID와 비밀번호를 POST 요청을 통해 서버로 전송함니다.
 
    
    2. 서버는 데이터베이스에서 해당 아이디를 조회한뒤 결과를 반환하거나 ID와 비밀번호를 저장합니다.
 
<br></br>
- **날씨 정보 요청 기능**: 
  - 사용자가 로그인 한 후, 그 시점의 위치와 시간을 기반으로 최신 날씨 정보를 기상청 API 통신을 통해 받아옵니다.
<br></br>
- **음식 추천 알고리즘**: 
  - 날씨 정보와 유저 정보를 기반으로 음식 추천 가중치를 조정한 후, 최종적으로 음식을 랜덤으로 추천합니다.
  - **Ex)**: 더운 날씨에는 차가운 음식이, 추운 날씨에는 따뜻한 음식이 더 높은 확률로 추천됩니다.
  <br></br>
- **식당 정보 요청 기능**: 
  - 추천된 음식과 사용자의 위치를 쿼리로 하여, 주변 식당 정보를 네이버 Search API 통신을 통해 받아옵니다.

 <br></br>

#  API 명세
| **Method** | **Path**                       | **Query Parameters**              | **Body**  | **Return Type**               | **Description**                              |
|------------|--------------------------------|-----------------------------------|-----------|-------------------------------|----------------------------------------------|
| GET        | `/api/users/preferences`       | `id` (String)                     | -         | `List<User>`                  | 사용자 ID로 사용자 선호 정보를 반환합니다. |
| GET        | `/api/users/existsId`          | `id` (String)                     | -         | `Boolean`                     | 사용자 ID의 존재 여부를 반환합니다.        |
| GET        | `/api/users/Login`             | `id` (String), `password` (String) | -         | `Boolean`                     | 사용자 ID와 비밀번호로 로그인 성공 여부를 반환합니다. |
| POST       | `/api/users/create`            | -                                 | `User`    | `User`                        | 새 사용자를 생성합니다.                    |
| GET        | `/api/foodsRandomByCategory`   | `cate1` (String), `cate2` (String) | -         | `Food`                        | 음식 카테고리로 랜덤 음식을 반환합니다.    |

<br></br><br></br><br></br><br></br><br></br><br></br>
# Snapshot
| 로그인 화면 | 회원가입 화면 | 
|:-------:|:-------:|
| <img src="https://github.com/user-attachments/assets/eb266025-eb90-4f14-a581-fdd5b490aac2" alt="Login 2" width="50%" height="auto"> | <img src="https://github.com/user-attachments/assets/58bb06c7-59ed-4105-b10a-a34df3866146" alt="CreateAcount" width="50%" height="auto"> |
|로그인 화면에서는 로그인 기능과, 날씨 정보 요청 기능을 수행합니다.|회원 가입 화면에서는 회원가입 기능을 수행합니다. |
| **메인 화면**  | **추천 화면**  |
| <img src="https://github.com/user-attachments/assets/33886086-6a40-47b2-98d6-13082a73cb9b" alt="Main" width="50%" height="auto"> | <img src="https://github.com/user-attachments/assets/1106ad95-028b-401e-a598-f3962042beb9" alt="Recommand" width="50%" height="auto"> | 
|메인 화면은 버튼을 누를 시, 식당 정보 요청 기능을 수행합니다.|추천 화면은 저장된 음식 정보와 식당 정보를 사용자에게 보여주는 기능을 수행합니다. |
