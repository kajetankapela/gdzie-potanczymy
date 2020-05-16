## gdzie-potanczymy.pl

>by Kajetan Kapela

## Opis serwisu

Serwis informacyjny ukazujący najważniejsze wydarzenia taneczne, które udostępnić mogą organizatorzy, a zapisywać się na nie mogą zrejestrowani użytkownicy.

## Opis klas

1. Event
    * Long id PK
    * String name
    * LocalDateTime startDate
    * LocalDateTime endDate
    * String description
    * String comments
    * OffsetDateTime createdAt
    * OffsetDateTime updatedAt
    * Address address
    * NumberOfSeats numberOfSeats
    * List<danceType> danceTypes
    * List<Star> stars
    * List<Participant> participants
    * Organizer organizer
    
2. Participant
    * String surname
    * String gender
    * List<Event> events

3. User
    * Long id PK
    * String name
    * String login
    * String password
    * String email
    * String phoneNumber
    * OffsetDateTime createdAt
    * OffsetDateTime updatedAt
    * Address address
    
4. Organizer
    * List<Event> events
    
5. Star
    * Long id PK
    * String name
    * String country
    * String description
    * String comments
    * OffsetDateTime createdAt
    * OffsetDateTime updatedAt
    * List<Event> events
    
6. Address
    * String country
    * String postalCode
    * String city
    * String street
    * String number
    
7. DanceType
    * Long id PK
    * String name
    * String description
    * String comments
    * OffsetDateTime createdAt
    * OffsetDateTime updatedAt
    * list<Event> events
    
8. NumberOfSeats
    * Integer allSeats
    * Integer freeSeats
    * Integer unconfirmedSeats
    * Integer confirmedSeats     

## Użyte technologie
1. Java
2. Spring
3. Maven
4. Git
5. MySQL
6. Bootstrap
7. HTML
8. CSS
9. Swagger