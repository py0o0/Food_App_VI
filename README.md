# 목차


1. [프로젝트 소개](#프로젝트-소개)
2. [다이어그램](#다이어그램)
3. [기술 스택](#기술-스택)
4. [주요 기능](#주요-기능)
5. [API 명세](#api-명세)
6. [Snapshot](#snapshot)

<br></br>
# 프로젝트 소개

오늘은 무엇을 먹을지 고민하는 분들을 위한 사용자 맞춤형 음식 추천 어플입니다.

이 앱은 사용자의 지역에서 실시간으로 제공되는 날씨 정보와 개인의 음식 선호도를 바탕으로 가중치를 조정하여 최적의 음식을 추천합니다. 또한, 추천된 음식에 맞춰 근처 식당 정보도 함께 제공하여 편리한 외식 옵션을 제시합니다.
<br></br>
## 개발 기간

24.05.09 ~ 24.05.30 (3주)
<br></br>
## 팀원

| Backend | Backend | 
|:-------:|:-------:|
| <img src="https://github.com/user-attachments/assets/03b048bc-9299-4c6b-a084-57fbc3da9499" alt="증사 2" width="150" height="200"> | <img src="https://github.com/user-attachments/assets/44c5ca02-64c7-4a53-8e27-dc125462651d" alt="프로필" width="150" height="200"> | 
| [채승표](https://github.com/py0o0)  | [홍진우](https://github.com/wqp99w)  |

<br></br>
# 다이어그램

- **Use Case Diagram**:
<img src="https://github.com/user-attachments/assets/81dec1a2-777d-4826-b384-343f67a4da01" alt="UCD" width="100%" height="auto"> 

<br></br>
- **System Sequence Diagram**:
<img src= "https://github.com/user-attachments/assets/367de53f-bfc0-4d08-8df8-18dfbfac6caa" alt="SSD" width="100%" height="auto">

<br></br>
- **Communication Diagram**:
<img src= "https://github.com/user-attachments/assets/1ee8e171-394c-4416-83a8-4dff7156bacd" alt="CD" width="100%" height="auto"> 


<br></br>
# 기술 스택
<img src= "https://github.com/user-attachments/assets/48e33715-5828-450a-90dd-caf96ea346b4" alt="기술스택" width="100%" height="auto"> 

<br></br>
# 주요 기능

- **서버와 DB 연동**: 
  - 서버는 Spring 프레임워크를 사용하고, 데이터베이스는 MySQL을 사용하였습니다.
  - DB에는 사용자의 선호도를 저장하는 `User` 테이블과 추천할 음식 목록이 저장된 `Food` 테이블이 있습니다.

- **클라이언트-서버 통신**: 
  - REST API를 활용하여 클라이언트와 서버 간의 데이터를 주고받습니다.

- **기상청 API 활용**: 
  - 사용자의 현재 위치와 시간을 기반으로 최신 날씨 정보를 기상청 API 통신을 통해 받아옵니다.

- **네이버 Search API 활용**: 
  - 추천된 음식과 사용자의 현재 주소를 쿼리로 하여, 주변 식당 정보를 네이버 Search API 통신을 통해 받아옵니다.

- **음식 추천 알고리즘**: 
  - 현재 기온과 날씨 상태를 기반으로 음식 추천 가중치를 조정한 후, 최종적으로 음식을 랜덤으로 추천합니다.
  - **Ex)**: 더운 날씨에는 차가운 음식이, 추운 날씨에는 따뜻한 음식이 더 높은 확률로 추천됩니다.
 <br></br>

#  API 명세
| **Method** | **Path**                       | **Query Parameters**              | **Body**  | **Return Type**               | **Description**                              |
|------------|--------------------------------|-----------------------------------|-----------|-------------------------------|----------------------------------------------|
| GET        | `/api/users/preferences`       | `id` (String)                     | -         | `List<User>`                  | 사용자 ID로 사용자 선호 정보를 반환합니다. |
| GET        | `/api/users/existsId`          | `id` (String)                     | -         | `Boolean`                     | 사용자 ID의 존재 여부를 반환합니다.        |
| GET        | `/api/users/Login`             | `id` (String), `password` (String) | -         | `Boolean`                     | 사용자 ID와 비밀번호로 로그인 성공 여부를 반환합니다. |
| POST       | `/api/users/create`            | -                                 | `User`    | `User`                        | 새 사용자를 생성합니다.                    |
| GET        | `/api/foodsRandomByCategory`   | `cate1` (String), `cate2` (String) | -         | `Food`                        | 음식 카테고리로 랜덤 음식을 반환합니다.    |

<br></br>
# Snapshot
| 로그인 화면 | 회원가입 화면 | 
|:-------:|:-------:|
| <img src="https://github.com/user-attachments/assets/eb266025-eb90-4f14-a581-fdd5b490aac2" alt="Login 2" width="50%" height="auto"> | <img src="https://github.com/user-attachments/assets/58bb06c7-59ed-4105-b10a-a34df3866146" alt="CreateAcount" width="50%" height="auto"> | 
| **메인 화면**  | **추천 화면**  |
| <img src="https://github.com/user-attachments/assets/33886086-6a40-47b2-98d6-13082a73cb9b" alt="Main" width="50%" height="auto"> | <img src="https://github.com/user-attachments/assets/1106ad95-028b-401e-a598-f3962042beb9" alt="Recommand" width="50%" height="auto"> | 
