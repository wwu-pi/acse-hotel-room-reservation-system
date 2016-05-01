CREATE TABLE Client(
        clientNo  INT   ,  
        firstName  VARCHAR(20)   ,  
        lastName  VARCHAR(20)   ,  
        phoneNo  VARCHAR(20)    
      );
CREATE TABLE Reservation(
  arrival  DATE   ,  
        departure  DATE   ,  
        clientNo INT
  , 
        roomNo INT
  , 
        reservationNo  INT    
      );
CREATE TABLE Room(
        size  VARCHAR(20)   ,  
        price  INT   ,  
        roomNo  INT    
      );
