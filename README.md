OASIS INFOBYTE INTERNSHIP
This repository contains the taaks I have completed for my Oasis Infobyte Internship over a month in Java Development

TASK 1 -  Online Reservation System

The basic idea behind this online train reservation system is to save data in a central database which can be accessed by any authorize person to get information and saves time and burden which are being faced by their customers.
Modules:
Login: The user has to enter correct login details. On successful login , users will be able access the system. With every unsuccessful login, a message regarding the same will be displayed and the user will be prompted to enter login details again.
Reservation System: After the user has logged in, they are given options as to whether they want to insert a record, delete a record or to exit the system.

If a user has chosen to insert a record, users will be assigned a PNR number and have to fill the necessary details such as their basic details, train number, train name will automatically come in the box, class type, date of journey, from (place) to destination and after which the details are recorded in the mySQL database and message for the same is displayed to the user. If an exception occurs, the details are displayed to the user.

If a user has chosen to delete a record, the user has to provide a PNR number. If the PNR number exists, then the train details are displayed to user. After the user confirms that they wish to delete a record, the record gets deleted from the mySQL database and the message for the successful deletion is displayed to the user. If the PNR number is not a valid one, the message of the same is displayed.


TASK 2 -  Number Guessing Game

Number Guessing Game is short and simple console game where the user attempts to guess a randomly generated number within a specified range. A random number between 1 and 100 is generated using the ‘random’ class. 
Rules regarding the game are displayed to the user. The user is given only 10 attempts and a score system is set in place in which the score is based on the number of attempts. More the attempts, lower the score and lesser the attempts, higher the score. The highest score is 100.

The user is prompted to enter a random guess. Messages are displayed as to whether the entered guess is higher or lower than the correct number. The game continues under the user guessing the number. On the correct guess, the message “Correct match” along with the score is displayed and the game ends. The game also ends after the user isn’t able to guess the number and thus has used all its attempts. 

TASK 3 -  ATM Interface

This ATM Interface project is a console-based application built using java and mySQL database. When the system starts the user is prompted with username and user pin. On entering the details successfully, then ATM functionalities are unlocked. With every unsuccessful login, a message regarding the same will be displayed and the user will be prompted to enter details again.

After the user has successfully logged in, they are given options as to whether they want to withdraw, deposit or transfer money, retrieve the transaction history or exit the system.
If a user has chosen to retrieve the transaction history, the datils of the same will be displayed.

If a user has chosen to withdraw money, the amount to be withdrawn is to be entered by the user. On successful withdrawal, the users balance is updated in the database and the message for the same as well as the updated balance, fetched from the database, is displayed to the user. Details of any exception that may occur is displayed.  If the withdrawal amount entered by the user exceeds their current balance, the message for the same is displayed and no change occurs in the database. 
If a user has chosen to deposit money, the amount to be deposited is to be entered by the user. On successful deposit, the users balance is updated in the database and the message for the same as well as the updated balance, fetched from the database, is displayed to the user. Details of any exception that may occur is displayed.

If a user has chosen to transfer money to another account, the accounts username the user wants to transfer money to and the amount to be deposited is to be entered by the user. On successful transfer, the balance of both the accounts involved in the transaction are updated in the database and the message for the same as well as the updated balance, fetched from the database, is displayed to the user. Details of any exception that may occur is displayed.
The user can exit the system whenever required.


TASK 4 -  Online Examination

This ATM Interface project is an application built using java and mySQL database. When the system starts the user is prompted with student ID and password. On entering the details successfully, then -  Online Examination functionalities are unlocked. With every unsuccessful login, a message regarding the same will be displayed and the user will be prompted to enter details again.

After the user has successfully logged in, they are given options as to whether they want to edit their profile, start the examination or exit the system i.e log out. These options are prompted throughout the program till the user decides to exit the system.
If a user has chosen to edit their details, they can modify their profile and password. If the modification is successful, a message regarding the same will be displayed. . Details of any exception that may occur is displayed.

If a user has chosen to start their examination, the instructions are displayedIf the user is unable to complete the exam within 20 minutes the exam will auto-submitted and the exam will end there. The questions are displayed one after the other and the answer for each are checked against the correct answers. The percentage is decided based on their answers and is displayed to the user on completion of the examination. If the user scores more than 40%, they pass the examination else they fail. After the completion of the exam, the user has to option to either edit their profile or exit the system.

The user can exit the system whenever required.










