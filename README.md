OASIS INFOBYTE INTERNSHIP
TASK 1 -  Online Reservation System
The basic idea behind this online train reservation system is to save data in a central database which can be accessed by any authorize person to get information and saves time and burden which are being faced by their customers.
Modules:
Login: The user has to enter correct login details. On successful login , users will be able access the system. With every unsuccessful login, a message regarding the same will be displayed and the user will be prompted to enter login details again.
Reservation System: After the user has logged in, they are given options as to whether they want to insert a record, delete a record or to exit the system.
If a user has chosen to insert a record, users will be assigned a PNR number and have to fill the necessary details such as their basic details, train number, train name will automatically come in the box, class type, date of journey, from (place) to destination and after which the details are recorded in the mySQL database and message for the same is displayed to the user. If an exception occurs, the details are displayed to the user.
If a user has chosen to delete a record, the user has to provide a PNR number. If the PNR number exists, then the train details are displayed to user. After the user confirms that they wish to delete a record, the record gets deleted from the mySQL database and the message for the successful deletion is displayed to the user. If the PNR number is not a valid one, the message of the same is displayed.
