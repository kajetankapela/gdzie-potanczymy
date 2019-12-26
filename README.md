#gdzie-potanczymy.pl
>by Kajetan Kapela

##Opis serwisu
Serwis informacyjny ukazujący najważniejsze wydarzenia taneczne, które udostępnić mogą organizatorzy, a zapisywać się na nie mogą zrejestrowani użytkownicy.

##Opis klas
1. Event
    * Integer id PK
    * String name
    * OffsetDateTime startDate
    * OffsetDateTime endDate
    * String description
    * Integer address_id FK
    * OffsetDateTime createdAt
    * OffsetDateTime updatedAt
    
2. Participant
    * Integer id PK
    * String name
    * String surname
    * Integer phoneNumber
    * Integer address_id FK
    * Integer user_id FK
    * OffsetDateTime createdAt
    * OffsetDateTime updatedAt
    
3. User
    * Integer id PK
    * String login
    * String password
    * String email
    * OffsetDateTime createdAt
    * OffsetDateTime updatedAt
    
4. Organizer
    * Integer id PK
    * String name
    * Integer phoneNumber
    * Integer address_id FK
    * Integer user_id FK
    * OffsetDateTime createdAt
    * OffsetDateTime updatedAt
    
5. Star
    * Integer id PK
    * String name
    * OffsetDateTime createdAt
    * OffsetDateTime updatedAt
    
6. Address
    * Integer id PK
    * String postalCode
    * String city
    * String street
    * String number
    * OffsetDateTime createdAt
    * OffsetDateTime updatedAt
    
7. DanceType
    * Integer id PK
    * String name
    * OffsetDateTime createdAt
    * OffsetDateTime updatedAt

8. Event_DanceType
    * Integer id PK
    * Integer event_id FK
    * Integer danceType_id FK
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