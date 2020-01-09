#gdzie-potanczymy.pl

>by Kajetan Kapela

##Opis serwisu

Serwis informacyjny ukazujący najważniejsze wydarzenia taneczne, które udostępnić mogą organizatorzy, a zapisywać się na nie mogą zrejestrowani użytkownicy.

##Opis klas

1. Event
    * Long id PK
    * String name
    * OffsetDateTime startDate
    * OffsetDateTime endDate
    * String description
    * Long address_id FK
    * OffsetDateTime createdAt
    * OffsetDateTime updatedAt
    
2. Participant
    * Long id PK
    * String name
    * String surname
    * String phoneNumber
    * Long address_id FK
    * Long user_id FK
    * OffsetDateTime createdAt
    * OffsetDateTime updatedAt
    
3. User
    * Long id PK
    * String login
    * String password
    * String email
    * OffsetDateTime createdAt
    * OffsetDateTime updatedAt
    
4. Organizer
    * Long id PK
    * String name
    * String phoneNumber
    * Long address_id FK
    * Long user_id FK
    * OffsetDateTime createdAt
    * OffsetDateTime updatedAt
    
5. Star
    * Long id PK
    * String name
    * OffsetDateTime createdAt
    * OffsetDateTime updatedAt
    
6. Address
    * Long id PK
    * String country
    * String postalCode
    * String city
    * String street
    * String number
    * OffsetDateTime createdAt
    * OffsetDateTime updatedAt
    
7. DanceType
    * Long id PK
    * String name
    * OffsetDateTime createdAt
    * OffsetDateTime updatedAt

8. Event_DanceType
    * Long id PK
    * Long event_id FK
    * Long danceType_id FK
    * OffsetDateTime createdAt
    * OffsetDateTime updatedAt

9. Event_Organizer
    * Long id PK
    * Long event_id FK
    * Long organizer_id FK
    * OffsetDateTime createdAt
    * OffsetDateTime updatedAt

10. Event_Participant
    * Long id PK
    * Long event_id FK
    * Long participant_id FK
    * OffsetDateTime createdAt
    * OffsetDatetime updatedAt
    
11. Event_Star
    * Long id PK
    * Long event_id FK
    * Long star_id FK
    * OffsetDateTime createdAt
    * OffsetDateTime updatedAt
    
##Użyte technologie
1. Java
2. Spring
3. Maven
4. Git
5. MySQL
6. Bootstrap
7. HTML
8. CSS
9. Swagger